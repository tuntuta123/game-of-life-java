package conway.demo;

import conway.logic.*;
import conway.shapes.*;
import conway.algo.*;
import conway.graphics.GridPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import conway.graphics.menu.Menu;

//je dois ajouter peut etre compteur de nombre des cellules vivant, de nombre des niveaux, button pour le etat precident, et change le placement de les buttons

public class Demo extends JFrame {

    private int size;
    private Grid grid;
    private HashLifeAlgo hashLifeAlgo;
    private GridPanel gridPanel;
    private JButton start, next, play, stop, toMenu, exit;
    //Andrea
    private JComboBox<String> modeComboBox; 
    private JComboBox<String> figureComboBox; 
    //Andrea
    private JSlider speedSlider;
    private JLabel generationLabel, aliveCellLabel, vitesse;
    private int generationCount = 0;
    private boolean active = false;
    private boolean manualMode = false;
    private Color liveCellColor;
    private Color deadCellColor;
    private boolean emojisEnabled;
    private Timer simulationTimer;
    private int speed = 2000;

    private enum GridMode { RANDOM, PLAYER_CHOOSES, FIGURES }
    private GridMode currentMode = GridMode.RANDOM;

    public Demo(int size, Color liveCellColor, Color deadCellColor, boolean emojisEnabled) {
        this.size = size; 
        this.liveCellColor = liveCellColor;
        this.deadCellColor = deadCellColor;
        this.emojisEnabled = emojisEnabled;

        this.grid = new Grid(size);
        initializeRandomGrid(); 

        Rule game = new Conway();
        this.hashLifeAlgo = new HashLifeAlgo();
        this.grid.setNeighbors();

        this.setTitle("Jeu de la vie");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        this.gridPanel = new GridPanel(grid, liveCellColor, deadCellColor, emojisEnabled);
        gridPanel.setBounds(100, 50, 600, 600); 
        this.add(gridPanel);

        this.aliveCellLabel = new JLabel("Cellules vivantes ");
        this.generationLabel = new JLabel("Génération: 0");

		//Andrea et Mila
        String[] modes = {"Aléatoire", "Le joueur choisit", "Figures"};
        modeComboBox = new JComboBox<>(modes);
        modeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedMode = (String) modeComboBox.getSelectedItem();
                switch (selectedMode) {
                    case "Aléatoire":
                        currentMode = GridMode.RANDOM;
                        generationLabel.setText("Génération: 0");
                        generationCount = 0;
                        initializeRandomGrid();
                        figureComboBox.setEnabled(false);  
                        break;
                    case "Le joueur choisit":
                        currentMode = GridMode.PLAYER_CHOOSES;
                        generationLabel.setText("Génération: 0");
                        generationCount = 0;
                        initializeEmptyGrid();
                        figureComboBox.setEnabled(false);  
                        break;
                    case "Figures":
                        currentMode = GridMode.FIGURES;
                        generationLabel.setText("Génération: 0");
                        generationCount = 0;
                        figureComboBox.setEnabled(true);  
                        initializeEmptyGrid();  
                        break;
                }
                gridPanel.repaint();
                updateAliveCellCount(); 
            }
        });

	    //Andrea
        String[] figures = {"Glider", "Block", "Cloverleaf", "Butterfly", "Lightweight", "Pulsar", "Spaceship"};
        figureComboBox = new JComboBox<>(figures);
        figureComboBox.setEnabled(false); 
        figureComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFigure = (String) figureComboBox.getSelectedItem();
            }
        });
        //Andrea

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
                    hashLifeAlgo.generate(grid);
                    generationCount++;             
                    updateAliveCellCount();
                    generationLabel.setText("Génération: " + generationCount); 
                    gridPanel.repaint();
                }
            }
        });
        
        this.simulationTimer = new Timer(speed, e -> {
            hashLifeAlgo.generate(grid);
            generationCount++; 
            updateAliveCellCount();  
            generationLabel.setText("Génération: " + generationCount); 
            gridPanel.repaint();
        });

        this.play = new JButton("Avancer");
        this.play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (active) {
                    if (!simulationTimer.isRunning()) {
                        simulationTimer.start();
                    }        
                }
            }
        });
        
        this.stop = new JButton("Pause");
        this.stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulationTimer.stop();
            }
        });

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
                    updateAliveCellCount();
                }

                if (currentMode == GridMode.FIGURES) {
                    int cellWidth = gridPanel.getWidth() / grid.getSize();
                    int cellHeight = gridPanel.getHeight() / grid.getSize();
                    int x = e.getX() / cellWidth;
                    int y = e.getY() / cellHeight;

                    String selectedFigure = (String) figureComboBox.getSelectedItem();
                    placeFigureOnGrid(x, y, selectedFigure); 
                    updateAliveCellCount(); 
                }
            }
        });

        this.speedSlider = new JSlider(1000, 3000, speed);
        this.speedSlider.setMajorTickSpacing(1000);
        this.speedSlider.setMinorTickSpacing(500); 
        this.speedSlider.setPaintTicks(true);
        this.speedSlider.setPaintLabels(true);
        this.speedSlider.addChangeListener(e ->  {
            speed = speedSlider.getValue();
            simulationTimer.setDelay(speed);
        });

        this.toMenu = new JButton("Menu");
        this.toMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                Menu mainMenu = new Menu("Menu principal", "Commencer le jeu", "Les règles du jeu", "Quitter");
                mainMenu.display(); 
            }
        });

        this.exit = new JButton("Sortir");
        this.exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
	
	this.vitesse = new JLabel("Vitesse:");

        aliveCellLabel.setBounds(750, 100, 150, 50);
        generationLabel.setBounds(950, 100, 150, 50);
        modeComboBox.setBounds(750, 200, 150, 50);
        figureComboBox.setBounds(950, 200, 150, 50);
        start.setBounds(750, 300, 150, 50);
        next.setBounds(950, 300, 150, 50);
        play.setBounds(750, 400, 150, 50);
        stop.setBounds(950, 400, 150, 50);
		vitesse.setBounds(750, 500, 150, 50);
        speedSlider.setBounds(950, 500, 150, 50);
        toMenu.setBounds(750, 600, 150, 50);
        exit.setBounds(950, 600, 150, 50);


        this.add(aliveCellLabel);
        this.add(generationLabel); 
        this.add(modeComboBox);  
        this.add(figureComboBox);
        this.add(start);
        this.add(next);
        this.add(play);
        this.add(stop);
        this.add(vitesse);
        this.add(speedSlider);
        this.add(toMenu);
        this.add(exit);

        this.pack();
        this.setSize(1200, 800);
        this.setVisible(true);
    }

    private void initializeRandomGrid() {
        Random rand = new Random();
        for (int x = 0; x < this.size; x++) {
            for (int y = 0; y < this.size; y++) {
                if (rand.nextDouble() < 0.5) {  
                    grid.setNode(x, y, true);
                } else {
                    grid.setNode(x, y, false);
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

    public void updateAliveCellCount() {
        int aliveCount = 0;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (grid.getNode(x, y).isAlive()) {
                    aliveCount++;
                }
            }
        }
        aliveCellLabel.setText("Cellules vivantes: " + aliveCount);
    }

        //Andrea
    private void placeFigureOnGrid(int x, int y, String figure) {
    try {
        int figureWidth = 0;
        int figureHeight = 0;

        switch (figure) {
            case "Glider":
                figureWidth = 3;
                figureHeight = 3;
                break;
            case "Block":
                figureWidth = 2;
                figureHeight = 2;
                break;
            case "Butterfly":
                figureWidth = 5;
                figureHeight = 5;
                break;
            case "Lightweight":
                figureWidth = 2;
                figureHeight = 3;
                break;
            case "Cloverleaf":
                figureWidth = 3;
                figureHeight = 3;
                break;
            case "Pulsar":
                figureWidth = 13;
                figureHeight = 13;
                break;
            case "Spaceship":
                figureWidth = 5;
                figureHeight = 5;
                break;
        }

        if (x + figureWidth > grid.getSize() || y + figureHeight > grid.getSize()) {
            throw new ArrayIndexOutOfBoundsException("Il n'y a pas assez de place pour placer la figure.");
        }

        switch (figure) {
            case "Glider":
                Shapes glider = new Glider();
                glider.applyShape(this.grid, x, y);
                break;
            case "Block":
                Shapes block = new Block();
                block.applyShape(this.grid, x, y);
                break;
            case "Butterfly":
                Shapes butter = new Butterfly();
                butter.applyShape(this.grid, x, y);
                break;
            case "Lightweight":
                Shapes lightweight = new Lightweight();
                lightweight.applyShape(this.grid, x, y);
                break;
            case "Cloverleaf":
                Shapes cloverleaf = new Cloverleaf();
                cloverleaf.applyShape(this.grid, x, y);
                break;
            case "Pulsar":
                Shapes pulsar = new Pulsar();
                pulsar.applyShape(this.grid, x, y);
                break;
            case "Spaceship":
                Shapes spaceship = new Spaceship();
                spaceship.applyShape(this.grid, x, y);
                break;
        }
        
        gridPanel.repaint();
    } catch (ArrayIndexOutOfBoundsException e) {
        JOptionPane.showMessageDialog(this, "Erreur: " + e.getMessage(), "Erreur de placement ", JOptionPane.ERROR_MESSAGE);
    }
}
}

