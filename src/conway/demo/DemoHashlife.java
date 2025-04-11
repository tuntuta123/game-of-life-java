package conway.demo;

import conway.logic.*;
import conway.algo.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class DemoHashlife {
    public static final int CELL_SIZE = 10;
    public static final int GRID_SIZE = 64; 

    public JFrame frame;
    public JPanel panel;
    public Grid grid;
    public boolean isRunning = false;
    public Timer timer;
    private HashLifeAlgo hashLifeAlgo;

    private JLabel liveCellLabel;
    private JLabel simulationStateLabel;

    private Color liveCellColor = Color.BLACK; 
    private Color deadCellColor = Color.WHITE; 

    private int speed = 100; 

    public DemoHashlife() {
        this.grid = initializeGrid(GRID_SIZE);
        this.hashLifeAlgo = new HashLifeAlgo();  
        setupUI();
    }

    public Grid initializeGrid(int size) {
        boolean[][] initialState = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                initialState[i][j] = Math.random() < 0.5;
            }
        }
        return new Grid(size, initialState);
    }

    public void setupUI() {
        frame = new JFrame("Game of Life (HashLife Algorithm)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(new BorderLayout());

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                renderGrid(g);
            }
        };

        panel.setPreferredSize(new Dimension(GRID_SIZE * CELL_SIZE, GRID_SIZE * CELL_SIZE));
        panel.setBackground(Color.WHITE);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                toggleCellState(e.getX(), e.getY());
                panel.repaint();
            }
        });

        JPanel controlsPanel = new JPanel();
        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");
        JButton resetButton = new JButton("Reset");
        JButton suivantButton = new JButton("Suivant");
        JButton avancerButton = new JButton("Avancer");

        startButton.addActionListener(e -> startSimulation());
        stopButton.addActionListener(e -> stopSimulation());
        resetButton.addActionListener(e -> resetGrid());
        suivantButton.addActionListener(e -> avancerSimulation());
        avancerButton.addActionListener(e -> chooseSpeed());

        controlsPanel.add(startButton);
        controlsPanel.add(stopButton);
        controlsPanel.add(resetButton);
        controlsPanel.add(suivantButton);
        controlsPanel.add(avancerButton);

        liveCellLabel = new JLabel("Live Cells: 0");
        simulationStateLabel = new JLabel("Simulation State: Stopped");

        JPanel infoPanel = new JPanel();
        infoPanel.add(liveCellLabel);
        infoPanel.add(simulationStateLabel);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(controlsPanel, BorderLayout.SOUTH);
        frame.add(infoPanel, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    public void renderGrid(Graphics g) {
        boolean[][] state = grid.getRoot().state;
        int liveCellCount = 0;
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                if (state[i][j]) {
                    g.setColor(liveCellColor);
                    liveCellCount++;
                } else {
                    g.setColor(deadCellColor);
                }
                g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.GRAY);
                g.drawRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
        liveCellLabel.setText("Live Cells: " + liveCellCount);  
    }

    public void toggleCellState(int x, int y) {
        int row = y / CELL_SIZE;
        int col = x / CELL_SIZE;
        boolean[][] state = grid.getRoot().state;
        state[row][col] = !state[row][col];
        grid.getRoot().state = state;
    }

    public void startSimulation() {
        if (isRunning) return;
        isRunning = true;
        simulationStateLabel.setText("Simulation State: Running");

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                grid.generate(); 
                panel.repaint();
            }
        }, 0, speed);
    }

    public void stopSimulation() {
        if (!isRunning) return;
        isRunning = false;
        simulationStateLabel.setText("Simulation State: Stopped");
        timer.cancel();
    }

    public void resetGrid() {
        grid = initializeGrid(GRID_SIZE);
        panel.repaint();
        simulationStateLabel.setText("Simulation State: Reset");
    }

    public void avancerSimulation() {
        grid.generate();  
        panel.repaint();
    }

    public void chooseSpeed() {
        String input = JOptionPane.showInputDialog(frame, "Enter speed (ms per generation):", "100");
        if (input != null) {
            try {
                speed = Integer.parseInt(input);
                if (isRunning) {
                    stopSimulation();
                    startSimulation();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid speed input.");
            }
        }
    }

    public void chooseColor() {
        Color newLiveColor = JColorChooser.showDialog(frame, "Choose Live Cell Color", liveCellColor);
        if (newLiveColor != null) {
            liveCellColor = newLiveColor;
        }

        Color newDeadColor = JColorChooser.showDialog(frame, "Choose Dead Cell Color", deadCellColor);
        if (newDeadColor != null) {
            deadCellColor = newDeadColor;
        }
    }
}

