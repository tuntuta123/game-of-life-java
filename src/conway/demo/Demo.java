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

import conway.graphics.menu.Menu;


//je dois ajouter peut etre compteur de nombre des cellules vivant, de nombre des niveaux, button pour le etat precident, et change le placement de les buttons

public class Demo extends JFrame {

    private int size;
    private Grid grid;
    private HashLifeAlgo hashLifeAlgo;
    private GridPanel gridPanel;
    private JButton start, next, play, stop, toMenu, exit;
    private JToggleButton togle;
    private JSlider speedSlider;
    private JLabel generationLabel, aliveCellLabel;
    private boolean active = false;
    private boolean manualMode = false;
    private Color liveCellColor;
    private Color deadCellColor;
    private boolean emojisEnabled;
    private Timer simulationTimer;
    private int speed = 2000;

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

//Buttons commencer, suivant, avance(continue d'avance parmi chaque niveaux jusqu'au la  personne clicque le buttons pause), pause, changwr le mode, slider pour le vitesse, retourner d;menu, sortir
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
        
        this.simulationTimer = new Timer(speed, e -> {
            hashLifeAlgo.generate();
            gridPanel.repaint();
        });

        this.play = new JButton("Avance");
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

	
        this.togle = new JToggleButton("Manual");
        this.togle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (currentMode) {
                    case RANDOM:
                        currentMode = GridMode.PLAYER_CHOOSES;
			togle.setText("Random");
                        initializeEmptyGrid();
                        break;
                    case PLAYER_CHOOSES:
                        currentMode = GridMode.RANDOM;
			togle.setText("Manual");
                        initializeRandomGrid();
                        break;
                }
                gridPanel.repaint();
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

        buttonPanel.add(start);
        buttonPanel.add(next);
        buttonPanel.add(play);
        buttonPanel.add(stop);
        buttonPanel.add(togle);
        buttonPanel.add(new JLabel("Speed:"));
        buttonPanel.add(speedSlider);
        buttonPanel.add(toMenu);
        buttonPanel.add(exit);
        this.add(buttonPanel, BorderLayout.NORTH);

        this.pack();
        this.setVisible(true);
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

