package conway.gameHashlifeAlgo.demo;

import conway.gameHashlifeAlgo.logics.*;
import conway.gameHashlifeAlgo.graphics.GridPanelHashlife;
import conway.menu.Menu;
import conway.gameHashlifeAlgo.shapes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class DemoHashlife {
    public int size;
    public JFrame frame;
    public GridPanelHashlife panel;
    public GridHashlife grid;
    public boolean isRunning = false;
    public Timer timer;
    private JLabel aliveCellLabel;
    private JLabel generationLabel;
    private Color liveCellColor;
    private Color deadCellColor;
    private boolean emojisEnabled;
    private int speed = 1000;
    private int generation = 0;

    private JButton start, next, play, stop, toMenu, exit;
    private JSlider speedSlider;
    private JComboBox<String> modeSelector;
    private JComboBox<String> figureComboBox;

    public DemoHashlife(int size, Color liveCellColor, Color deadCellColor, boolean emojisEnabled) {
        this.size = size;
        this.liveCellColor = liveCellColor;
        this.deadCellColor = deadCellColor;
        this.emojisEnabled = emojisEnabled;
        this.grid = initializeGrid(size);
        setupUI();
    }

    public GridHashlife initializeGrid(int size) {
        boolean[][] initialState = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                initialState[i][j] = Math.random() < 0.5;
            }
        }
        return new GridHashlife(size, initialState);
    }

    public GridHashlife initializeEmptyGrid(int size) {
        return new GridHashlife(size, new boolean[size][size]);
    }

    public void setupUI() {
        frame = new JFrame("Game of Life (HashLife Algorithm)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 800);
        frame.setLayout(null);

        panel = new GridPanelHashlife(grid, liveCellColor, deadCellColor, emojisEnabled);
        panel.setBounds(100, 25, 700, 700);
        panel.setBackground(Color.WHITE);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int cellSize = panel.getWidth() / size;
                int col = e.getX() / cellSize;
                int row = e.getY() / cellSize;

                if (row >= 0 && row < size && col >= 0 && col < size) {
                    String selectedMode = (String) modeSelector.getSelectedItem();
                    if ("Le joueur choisit".equals(selectedMode)) {
                        toggleCellState(row, col);
                    } else if ("Figures".equals(selectedMode)) {
                        String selectedFigure = (String) figureComboBox.getSelectedItem();
                        if (selectedFigure != null) {
                            applyFigure(selectedFigure, col, row);
                        }
                    }
                    panel.repaint();
                    updateLabels();
                }
            }
        });

        aliveCellLabel = new JLabel("Cellules vivantes: 0");
        generationLabel = new JLabel("Génération: 0");
        aliveCellLabel.setBounds(850, 50, 150, 50);
        generationLabel.setBounds(1150, 50, 150, 50);

        start = new JButton("Commencer");
        next = new JButton("Suivant");
        play = new JButton("Avancer");
        stop = new JButton("Pause");
        toMenu = new JButton("Menu");
        exit = new JButton("Sortir");

        start.setBounds(850, 200, 150, 50);
        next.setBounds(1150, 200, 150, 50);
        play.setBounds(850, 275, 150, 50);
        stop.setBounds(1150, 275, 150, 50);
        toMenu.setBounds(850, 425, 150, 50);
        exit.setBounds(1150, 425, 150, 50);

        JLabel vitesse = new JLabel("Vitesse:");
        vitesse.setBounds(850, 350, 150, 50);

        speedSlider = new JSlider(JSlider.HORIZONTAL, 1000, 3000, speed);
        speedSlider.setMajorTickSpacing(1000);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.setBounds(1150, 350, 150, 50);

        modeSelector = new JComboBox<>(new String[]{"Mode aléatoire", "Le joueur choisit", "Figures"});
        modeSelector.setBounds(850, 125, 150, 50);
        modeSelector.addActionListener(e -> {
            String selectedMode = (String) modeSelector.getSelectedItem();
            if ("Mode aléatoire".equals(selectedMode)) {
                grid = initializeGrid(size);
            } else {
                grid = initializeEmptyGrid(size);
            }
            panel.setGrid(grid);
            panel.repaint();
            generation = 0;
            updateLabels();
        });

        figureComboBox = new JComboBox<>(new String[]{
            "Blinker", "Block", "Butterfly", "Cloverleaf", "Glider", "Lightweight", "Pulsar", "Spaceship"
        });
        figureComboBox.setBounds(1150, 125, 150, 50);

        next.setEnabled(false);
        play.setEnabled(false);
        stop.setEnabled(false);

        start.addActionListener(e -> {
            next.setEnabled(true);
            play.setEnabled(true);
            stop.setEnabled(true);
            start.setEnabled(false);
        });

        next.addActionListener(e -> advanceOneStep());
        play.addActionListener(e -> startSimulation());
        stop.addActionListener(e -> stopSimulation());

        toMenu.addActionListener(e -> {
            frame.dispose();
            Menu mainMenu = new Menu("Menu principal", "Commencer le jeu", "Les règles du jeu", "Quitter");
            mainMenu.display();
        });

        exit.addActionListener(e -> System.exit(0));

        speedSlider.addChangeListener(e -> {
            speed = speedSlider.getValue();
            if (isRunning) {
                stopSimulation();
                startSimulation();
            }
        });

        frame.add(panel);
        frame.add(aliveCellLabel);
        frame.add(generationLabel);
        frame.add(start);
        frame.add(next);
        frame.add(play);
        frame.add(stop);
        frame.add(vitesse);
        frame.add(speedSlider);
        frame.add(toMenu);
        frame.add(exit);
        frame.add(modeSelector);
        frame.add(figureComboBox);

        updateLabels();
        frame.setVisible(true);
    }

    public void toggleCellState(int row, int col) {
        boolean[][] state = grid.getRoot().state;
        state[row][col] = !state[row][col];
    }

    public void startSimulation() {
        if (isRunning) return;
        isRunning = true;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                grid.generate();
                generation++;
                SwingUtilities.invokeLater(() -> {
                    panel.repaint();
                    updateLabels();
                });
            }
        }, 0, speed);
    }

    public void stopSimulation() {
        if (!isRunning) return;
        isRunning = false;
        timer.cancel();
    }

    public void advanceOneStep() {
        grid.generate();
        generation++;
        panel.repaint();
        updateLabels();
    }

    public void updateLabels() {
        int count = 0;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (grid.getRoot().state[y][x]) count++;
            }
        }
        aliveCellLabel.setText("Cellules vivantes: " + count);
        generationLabel.setText("Génération: " + generation);
    }

    private void applyFigure(String figure, int x, int y) {
        try {
        
        	int figureWidth = getFigureWidth(figure);
		    int figureHeight = getFigureHeight(figure);

		    if (x + figureWidth > this.size || y + figureHeight > this.size) {
		        throw new ArrayIndexOutOfBoundsException("Il n'y a pas assez de place pour placer la figure.");
		    }
		    
            switch (figure) {
                case "Glider": new GliderHashlife().applyShape(grid, x, y); break;
                case "Block": new BlockHashlife().applyShape(grid, x, y); break;
                case "Butterfly": new ButterflyHashlife().applyShape(grid, x, y); break;
                case "Lightweight": new LightweightHashlife().applyShape(grid, x, y); break;
                case "Cloverleaf": new CloverleafHashlife().applyShape(grid, x, y); break;
                case "Pulsar": new PulsarHashlife().applyShape(grid, x, y); break;
                case "Spaceship": new SpaceshipHashlife().applyShape(grid, x, y); break;
                case "Blinker": new BlinkerHashlife().applyShape(grid, x, y); break;
                default: throw new IllegalArgumentException("Unknown figure type: " + figure);
            }
            panel.repaint();
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            JOptionPane.showMessageDialog(frame, "Erreur: " + e.getMessage(), "Erreur de placement ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int getFigureWidth(String figure) {
        switch (figure) {
            case "Glider": return 3;
            case "Block": return 2;
            case "Butterfly": return 4;
            case "Lightweight": return 4;
            case "Cloverleaf": return 3;
            case "Pulsar": return 9;
            case "Spaceship": return 8;
            case "Blinker": return 3;
            default: return 0;
        }
    }

    private int getFigureHeight(String figure) {
        switch (figure) {
            case "Glider": return 3;
            case "Block": return 2;
            case "Butterfly": return 4;
            case "Lightweight": return 3;
            case "Cloverleaf": return 3;
            case "Pulsar": return 9;
            case "Spaceship": return 12;
            case "Blinker": return 1;
            default: return 0;
        }
    }
}
