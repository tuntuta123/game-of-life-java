package conway.demo;
import conway.logic.*;
import conway.algo.HashLifeAlgo;
import conway.graphics.GridPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * La classe Demo représente l'interface graphique et la logique de simulation du Jeu de la Vie.
 */
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
        initializeRandomGrid();  // Initialisation aléatoire de la grille

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
            /**
             * Démarre la simulation du Jeu de la Vie.
             * 
             * @param e L'événement généré par le clic sur le bouton "Commencer".
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                active = true;
                startSimulation();
            }
        });

        this.next = new JButton("Suivant");
        this.next.addActionListener(new ActionListener() {
            /**
             * Passe à l'étape suivante de la simulation et met à jour l'affichage.
             * 
             * @param e L'événement généré par le clic sur le bouton "Suivant".
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (active) {
                    hashLifeAlgo.generate();  
                    gridPanel.repaint();  
                }
            }
        });

        this.toggleMode = new JButton("Utilisateur <--> Aleatoire");
        this.toggleMode.addActionListener(new ActionListener() {
            /**
             * Bascule entre le mode manuel et automatique.
             * Si le mode manuel est activé, la grille est initialisée vide.
             * Si le mode automatique est activé, la grille est initialisée de manière aléatoire.
             * 
             * @param e L'événement généré par le clic sur le bouton "Utilisateur <--> Aleatoire".
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                manualMode = !manualMode;
                if (manualMode) {
                    initializeEmptyGrid();  
                } else {
                    initializeRandomGrid();  
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
            /**
             * Gère les actions de clic de souris en mode manuel.
             * Si le mode manuel est activé, l'utilisateur peut cliquer sur les cellules pour les activer ou les désactiver.
             * 
             * @param e L'événement généré par le clic de souris sur la grille.
             */
            @Override
            public void mousePressed(MouseEvent e) {
                if (manualMode) {
                    int cellWidth = gridPanel.getWidth() / grid.getWidth();
                    int cellHeight = gridPanel.getHeight() / grid.getHeight();
                    int x = e.getX() / cellWidth;
                    int y = e.getY() / cellHeight;

                    Node node = grid.getNode(x, y);
                    node.setAlive(!node.isAlive());  
                    gridPanel.repaint();  
                }
            }
        });
    }

    /**
     * Initialise la grille avec des cellules vivantes de manière aléatoire.
     * Chaque cellule a une chance de 20% d'être vivante.
     */
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

    /**
     * Initialise la grille avec toutes les cellules mortes (inactives).
     */
    private void initializeEmptyGrid() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                grid.setNode(x, y, false);  
            }
        }
    }

    /**
     * Démarre la simulation en configurant les voisins de chaque cellule.
     */
    private void startSimulation() {
        grid.setNeighbors();  
    }
    
    public static void main(String[] args) {
        new Demo();  
    }
}

