package conway;
import javax.swing.*;
import java.awt.*;

/**
 * La classe GridPanel est responsable de l'affichage graphique de la grille du jeu de la vie.
 */
public class GridPanel extends JPanel {

    private Grid grid;

    /**
     * Constructeur de la classe GridPanel.
     * 
     * Initialise le panneau avec la grille à afficher.
     * 
     * @param grid La grille représentant l'état actuel du jeu de la vie.
     */
    public GridPanel(Grid grid) {
        this.grid = grid;
        setPreferredSize(new Dimension(500, 500)); 
    }

    /**
     * Redéfinit la méthode paintComponent pour dessiner la grille et les cellules.
     * 
     * @param g Le contexte graphique utilisé pour dessiner sur le panneau.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g); 
    }

    /**
     * Dessine la grille et les cellules sur le panneau.
     * 
     * @param g Le contexte graphique utilisé pour dessiner sur le panneau.
     */
    private void drawGrid(Graphics g) {
        int cellWidth = getWidth() / grid.getWidth();  
        int cellHeight = getHeight() / grid.getHeight(); 

        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                Node node = grid.getNode(x, y);
                
                if (node.isAlive()) {
                    g.setColor(Color.red);
                    g.fillRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
                } 
                else {
                    g.setColor(Color.pink);
                    g.fillRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
                }
                
                g.setColor(Color.BLACK);
                g.drawRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
            }
        }
    }
}

