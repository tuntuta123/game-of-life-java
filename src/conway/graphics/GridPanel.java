package conway.graphics;

import conway.logic.*;
import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {

    private Grid grid;
    private Color liveCellColor; 
    private Color deadCellColor; 
    private boolean emojisEnabled;  

    public GridPanel(Grid grid, Color liveCellColor, Color deadCellColor, boolean emojisEnabled) {
        this.grid = grid;
        this.liveCellColor = liveCellColor;
        this.deadCellColor = deadCellColor;
        this.emojisEnabled = emojisEnabled;
        setPreferredSize(new Dimension(1000, 1000)); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g); 
    }

    private void drawGrid(Graphics g) {
        int cellWidth = getWidth() / grid.getSize();  
        int cellHeight = getHeight() / grid.getSize(); 

        Font emojiFont = new Font("Arial", Font.PLAIN, Math.min(cellWidth, cellHeight) - 5);
        g.setFont(emojiFont);

        for (int y = 0; y < grid.getSize(); y++) {
            for (int x = 0; x < grid.getSize(); x++) {
                Node node = grid.getNode(x, y);
                
                if (node.isAlive()) {
                    g.setColor(liveCellColor); 
                    if (emojisEnabled) {
                        String emoji = "😊";
                        FontMetrics fm = g.getFontMetrics();
                        int emojiWidth = fm.stringWidth(emoji);
                        int emojiHeight = fm.getHeight();
                        int xPosition = x * cellWidth + (cellWidth - emojiWidth) / 2;
                        int yPosition = y * cellHeight + (cellHeight - emojiHeight) / 2 + fm.getAscent();
                        g.drawString(emoji, xPosition, yPosition);
                    } else {
                        g.fillRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
                    }
                } else {
                    g.setColor(deadCellColor);  
                    g.fillRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
                }
                g.setColor(Color.BLACK);
                g.drawRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
            }
        }
    }
}

