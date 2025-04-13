package conway.gameBasicAlgo.demo;

import conway.gameBasicAlgo.logics.*;
import conway.gameBasicAlgo.shapes.*;
import conway.gameBasicAlgo.algo.*;
import conway.gameBasicAlgo.graphics.GridPanel;
import conway.challenge.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import conway.graphics.menu.Menu;

public class Demo extends JFrame {

    public int size;
    public Grid grid;
    public BasicAlgo BasicAlgo;
    public GridPanel gridPanel;
    public JButton start, next, play, stop, toMenu, exit;
    //Andrea
    public JComboBox<String> modeComboBox; 
    public JComboBox<String> figureComboBox; 
    //Andrea
    public JSlider speedSlider;
    public JLabel generationLabel, aliveCellLabel, vitesse;
    public int generationCount = 0;
    public boolean active = false;
    public boolean manualMode = false;
    public Color liveCellColor;
    public Color deadCellColor;
    public boolean emojisEnabled;
    public Timer simulationTimer;
    public int speed = 2000;
    public JComboBox<String> puzzleFigureComboBox; //Puzzle mode

    public enum GridMode { RANDOM, PLAYER_CHOOSES, FIGURES, PUZZLE } //Puzzle mode
    public GridMode currentMode = GridMode.RANDOM;
    public PuzzleChallenge puzzleChallenge; //Puzzle mode

    public Demo(int size, Color liveCellColor, Color deadCellColor, boolean emojisEnabled) {
        this.size = size; 
        this.liveCellColor = liveCellColor;
        this.deadCellColor = deadCellColor;
        this.emojisEnabled = emojisEnabled;

        this.grid = new Grid(size);
        initializeRandomGrid(); 

        Rule game = new Conway();
        this.BasicAlgo = new BasicAlgo(grid, game);
        this.grid.setNeighbors();

        this.setTitle("Jeu de la vie (algorithme de base)");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        this.gridPanel = new GridPanel(grid, liveCellColor, deadCellColor, emojisEnabled);
        gridPanel.setBounds(100, 25, 700, 700);
        this.add(gridPanel);

        this.aliveCellLabel = new JLabel("Cellules vivantes ");
        this.generationLabel = new JLabel("Génération: 0");

		//Andrea et Mila
        String[] modes = {"Aléatoire", "Le joueur choisit", "Figures", "Puzzle"};
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
		        		puzzleFigureComboBox.setEnabled(false);
                        figureComboBox.setEnabled(false);  
						removePuzzlePanel();
                        play.setEnabled(true);
                        break;
                    case "Le joueur choisit":
                        currentMode = GridMode.PLAYER_CHOOSES;
                        generationLabel.setText("Génération: 0");
                        generationCount = 0;
                        initializeEmptyGrid();
		        		puzzleFigureComboBox.setEnabled(false);
                        figureComboBox.setEnabled(false);  
						removePuzzlePanel();
                        play.setEnabled(true);
                        break;
                    case "Figures":
                        currentMode = GridMode.FIGURES;
                        generationLabel.setText("Génération: 0");
                        generationCount = 0;
		        		puzzleFigureComboBox.setEnabled(false);
                        figureComboBox.setEnabled(true);  
                        initializeEmptyGrid();  
						removePuzzlePanel();
                        play.setEnabled(true);
                        break;
                    //Puzzle mode
					case "Puzzle":
						currentMode = GridMode.PUZZLE;
						generationLabel.setText("Génération: 0");
						generationCount = 0;
						puzzleChallenge = new PuzzleChallenge(size, liveCellColor, deadCellColor, emojisEnabled, 20, 10);
						initializeEmptyGrid();
						addPuzzlePanel(puzzleChallenge.getTargetPanel());
						puzzleFigureComboBox.setEnabled(true);
						figureComboBox.setEnabled(false);
						play.setEnabled(false);
						break;
				            //Puzzle mode
				        }
				        gridPanel.repaint();
				        updateAliveCellCount(); 
				    }
				});

	    //Andrea
        String[] figures = {"Glider", "Block", "Cloverleaf", "Butterfly", "Lightweight", "Pulsar", "Spaceship", "Blinker"};
        figureComboBox = new JComboBox<>(figures);
        figureComboBox.setEnabled(false); 
        figureComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFigure = (String) figureComboBox.getSelectedItem();
            }
        });
        //Andrea

    //Puzzle mode
	String[] puzzleFigures = {"Glider", "Block", "Blinker"};
	puzzleFigureComboBox = new JComboBox<>(puzzleFigures);
	puzzleFigureComboBox.setEnabled(false);
	puzzleFigureComboBox.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (currentMode == GridMode.PUZZLE && puzzleChallenge != null) {
		    String selectedPuzzle = (String) puzzleFigureComboBox.getSelectedItem();
		    puzzleChallenge.setTargetPattern(selectedPuzzle);
		}
	    }
	});
    //Puzzle mode

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
                    BasicAlgo.generate();
                    generationCount++;             
                    updateAliveCellCount();
                    generationLabel.setText("Génération: " + generationCount); 
                    gridPanel.repaint();
         // Puzzle mode
		    if (currentMode == GridMode.PUZZLE) {
		        if (puzzleChallenge.checkPuzzleSolution(grid)) {
		            JOptionPane.showMessageDialog(Demo.this, "Puzzle trouve! Pour rejoue clique sur le puzzle button");
		            active = false;
		            simulationTimer.stop();
		            removePuzzlePanel(); 
		        } else if (generationCount >= puzzleChallenge.getGenerationLimit()) {
		            JOptionPane.showMessageDialog(Demo.this, "Puzzle error, pour resayer clique encore fois sur puzzle button!");
		            active = false;
		            simulationTimer.stop();
		            removePuzzlePanel();
		        }
		    }
         // Puzzle mode
                }
            }
        });
        
        this.simulationTimer = new Timer(speed, e -> {
            BasicAlgo.generate();
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
            // Puzzle mode
		if (currentMode == GridMode.PUZZLE) {
			if (puzzleChallenge != null && puzzleChallenge.canToggleCell()) {
		 		int cellWidth = gridPanel.getWidth() / grid.getSize();
				int cellHeight = gridPanel.getHeight() / grid.getSize();
			        int x = e.getX() / cellWidth;
			        int y = e.getY() / cellHeight;
			        Node node = grid.getNode(x, y);
			        node.setAlive(!node.isAlive());
			        puzzleChallenge.incrementMoveCount();
			        gridPanel.repaint();
			        updateAliveCellCount();
		    } else {
			        JOptionPane.showMessageDialog(Demo.this, "Vous avez atteint le nombre maximal de modifications ("+ (puzzleChallenge != null ? puzzleChallenge.getMoveLimit() : "") + ") pour ce puzzle.");
			  }
		}
             // Puzzle mode
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

        aliveCellLabel.setBounds(850, 50, 150, 50);
        generationLabel.setBounds(1150, 50, 150, 50);
        modeComboBox.setBounds(850, 125, 150, 50);
        figureComboBox.setBounds(1150, 125, 150, 50);
        start.setBounds(850, 200, 150, 50);
        next.setBounds(1150, 200, 150, 50);
        play.setBounds(850, 275, 150, 50);
        stop.setBounds(1150, 275, 150, 50);
        vitesse.setBounds(850, 350, 150, 50);
        speedSlider.setBounds(1150, 350, 150, 50);
        toMenu.setBounds(850, 425, 150, 50);
        exit.setBounds(1150, 425, 150, 50);
	puzzleFigureComboBox.setBounds(850, 550, 150, 50);    // Puzzle mode


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
	this.add(puzzleFigureComboBox);    // Puzzle mode

        this.pack();
        this.setSize(1600, 800);
        this.setVisible(true);
    }


    // Puzzle mode
    public void addPuzzlePanel(JPanel panel) {
        panel.setBounds(1150, 550, 150, 150);
        this.add(panel);
        this.repaint();
    }

    public void removePuzzlePanel() {
        if (puzzleChallenge != null) {
            JPanel panel = puzzleChallenge.getTargetPanel();
            this.remove(panel);
            puzzleChallenge = null;
            this.repaint();
        }
    }
    // Puzzle mode

    public void initializeRandomGrid() {
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

    public void initializeEmptyGrid() {
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
    public void placeFigureOnGrid(int x, int y, String figure) {
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
            case "Blinker":
                figureWidth = 3;
                figureHeight = 1;
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
            case "Blinker":
                Shapes blinker = new Blinker();
                blinker.applyShape(this.grid, x, y);
                break;
           
        }
        
        gridPanel.repaint();
    } catch (ArrayIndexOutOfBoundsException e) {
        JOptionPane.showMessageDialog(this, "Erreur: " + e.getMessage(), "Erreur de placement ", JOptionPane.ERROR_MESSAGE);
    }
}
}

