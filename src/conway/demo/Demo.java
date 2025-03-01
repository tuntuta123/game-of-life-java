package conway.demo;

import conway.logic.*;
import conway.algo.*;
import conway.graphics.GridPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Demo extends JFrame {

    private int size;
    private Grid grid;
    private HashLifeAlgo hashLifeAlgo;
    private GridPanel gridPanel;
    private JButton start, next, toggleMode;
    private boolean active = false;
    private boolean manualMode = false; 
    private Color liveCellColor;  
    private Color deadCellColor;
    private boolean emojisEnabled; 

    private enum GridMode { RANDOM, PLAYER_CHOOSES }
    private GridMode currentMode = GridMode.RANDOM;  

    public Demo(int size, Color liveCellColor, Color deadCellColor, boolean emojisEnabled) {
        this.size = size;
        this.liveCellColor = liveCellColor;
        this.deadCellColor = deadCellColor;
        this.emojisEnabled = emojisEnabled;

        this.grid = new Grid(size);
        initializeRandomGrid(); 

        Rule game = new Conway();
        this.hashLifeAlgo = new HashLifeAlgo(grid, game);
        this.grid.setNeighbors();

        this.setTitle("Jeu de la vie");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.gridPanel = new GridPanel(grid, liveCellColor, deadCellColor, emojisEnabled);
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

        this.toggleMode = new JButton("Changer de mode");
        this.toggleMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (currentMode) {
                    case RANDOM:
                        currentMode = GridMode.PLAYER_CHOOSES;
                        initializeEmptyGrid();
                        break;
                    case PLAYER_CHOOSES:
                        currentMode = GridMode.RANDOM;
                        initializeRandomGrid();
                        break;
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

        gridPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (currentMode == GridMode.PLAYER_CHOOSES) {
                    int cellWidth = gridPanel.getWidth() / grid.getSize();
                    int cellHeight = gridPanel.getHeight() / grid.getSize();
                    int x = e.getX() / cellWidth;
                    int y = e.getY() / cellHeight;

                    Node node = grid.getNode(x, y);
                    node.setAlive(!node.isAlive());
                    gridPanel.repaint();
                }
            }
        });
    }

    private void initializeRandomGrid() {
        Random rand = new Random();
        for (int x = 0; x < this.size; x++) {
            for (int y = 0; y < this.size; y++) {
                if (rand.nextDouble() < 0.5) {  
                    grid.setNode(x, y, true);
                }
            }
        }
    }

    private void initializeEmptyGrid() {
        for (int x = 0; x < this.size; x++) {
            for (int y = 0; y < this.size; y++) {
                grid.setNode(x, y, false); 
            }
        }
    }

    public void startSimulation() {
        grid.setNeighbors();  
    }
}

