package conway;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Demo extends JFrame {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private Grid grid;
    private HashLifeAlgo hashLifeAlgo;
    private GridPanel gridPanel;
    private JButton start, next;
    private boolean active = false;

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

        buttonPanel.add(start);
        buttonPanel.add(next);
        this.add(buttonPanel, BorderLayout.SOUTH);


        this.pack();
        this.setVisible(true);
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


    private void startSimulation() {
        grid.setNeighbors();

    }

    public static void main(String[] args) {
        new Demo();
    }
}

