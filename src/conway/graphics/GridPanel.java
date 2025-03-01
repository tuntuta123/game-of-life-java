package conway.graphics;
import conway.logic.*;
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
     * @param grid La grille représentant l'état actuel du jeu de la vie.
     */
    public GridPanel(Grid grid) {
        this.grid = grid;
        setPreferredSize(new Dimension(1000, 1000)); 
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

    Font emojiFont = new Font("Arial", Font.PLAIN, Math.min(cellWidth, cellHeight) - 5);
    g.setFont(emojiFont);

    for (int y = 0; y < grid.getHeight(); y++) {
        for (int x = 0; x < grid.getWidth(); x++) {
            Node node = grid.getNode(x, y);
            
            if (node.isAlive()) {
                g.setColor(Color.RED);  
                String emoji = "😊";
                FontMetrics fm = g.getFontMetrics();
                int emojiWidth = fm.stringWidth(emoji);
                int emojiHeight = fm.getHeight();
                int xPosition = x * cellWidth + (cellWidth - emojiWidth) / 2;
                int yPosition = y * cellHeight + (cellHeight - emojiHeight) / 2 + fm.getAscent();
                
                g.drawString(emoji, xPosition, yPosition);
            } 
            else {
                g.setColor(Color.PINK);
                g.fillRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
            }
            g.setColor(Color.BLACK);
            g.drawRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
        }
    }
}

}

