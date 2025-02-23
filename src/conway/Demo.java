package conway;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Demo extends JFrame {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private Grid grid;
    private HashLifeAlgo hashLifeAlgo;
    private GridPanel gridPanel;
    private JButton start, next, toggleMode;
    private boolean active = false;
    private boolean manualMode = false; 

    public Demo() {
        this.grid = new Grid(WIDTH, HEIGHT);
        initializeRandomGrid();

        Rule game = new Conway();
        this.hashLifeAlgo = new HashLifeAlgo(grid, game);
        this.grid.setNeighbors();

        this.setTitle("Jeu de la vie");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.gridPanel = new GridPanel(grid);
        this.add(gridPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        this.start = new JButton("Commencer");
        this.start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                active = true;
                startSimulation();
            }
        });

        this.next = new JButton("Suivant");
        this.next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (active) {
                    hashLifeAlgo.generate();
                    gridPanel.repaint(); 
                }
            }
        });

        this.toggleMode = new JButton("Toggle Mode");
        this.toggleMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manualMode = !manualMode; // Toggle manual mode.
                if (manualMode) {
                    initializeEmptyGrid(); // Clear grid when switching to manual mode.
                } else {
                    initializeRandomGrid(); // Reinitialize randomly when switching back.
                }
                gridPanel.repaint();
            }
        });

        buttonPanel.add(start);
        buttonPanel.add(next);
        buttonPanel.add(toggleMode);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);

        // Add mouse listener to allow manual cell toggling
        gridPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (manualMode) {
                    int cellWidth = gridPanel.getWidth() / grid.getWidth();
                    int cellHeight = gridPanel.getHeight() / grid.getHeight();
                    int x = e.getX() / cellWidth;
                    int y = e.getY() / cellHeight;

                    // Toggle the state of the clicked cell
                    Node node = grid.getNode(x, y);
                    node.setAlive(!node.isAlive());
                    gridPanel.repaint();
                }
            }
        });
    }

    private void initializeRandomGrid() {
        Random rand = new Random();
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (rand.nextDouble() < 0.2) {
                    grid.setNode(x, y, true);
                }
            }
        }
    }

    private void initializeEmptyGrid() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                grid.setNode(x, y, false);
            }
        }
    }

    private void startSimulation() {
        grid.setNeighbors();
    }

    public static void main(String[] args) {
        new Demo();
    }
}

