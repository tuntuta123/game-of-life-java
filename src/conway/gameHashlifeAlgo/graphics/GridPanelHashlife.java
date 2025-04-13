package conway.gameHashlifeAlgo.graphics;

import conway.gameHashlifeAlgo.logics.*;
import javax.swing.*;
import java.awt.*;

public class GridPanelHashlife extends JPanel {

    private GridHashlife grid;
    private Color liveCellColor;
    private Color deadCellColor;
    private boolean emojisEnabled;

    public GridPanelHashlife(GridHashlife grid, Color liveCellColor, Color deadCellColor, boolean emojisEnabled) {
        this.grid = grid;
        this.liveCellColor = liveCellColor;
        this.deadCellColor = deadCellColor;
        this.emojisEnabled = emojisEnabled;
        setPreferredSize(new Dimension(1000, 1000)); 
    }

	public void setGrid(GridHashlife newGrid) {
		this.grid = newGrid;
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
    }

    private void drawGrid(Graphics g) {
        int cellWidth = getWidth() / grid.getRoot().size;
        int cellHeight = getHeight() / grid.getRoot().size;

        Font emojiFont = new Font("Arial", Font.PLAIN, Math.min(cellWidth, cellHeight) - 5);
        g.setFont(emojiFont);

        NodeHashlife root = grid.getRoot();

        drawSubgrid(g, root, 0, 0, cellWidth, cellHeight);
    }

    private void drawSubgrid(Graphics g, NodeHashlife node, int xOffset, int yOffset, int cellWidth, int cellHeight) {
        if (node.isLeaf()) {
            for (int y = 0; y < node.size; y++) {
                for (int x = 0; x < node.size; x++) {
                    if (node.state[y][x]) {
                        g.setColor(liveCellColor);
                        if (emojisEnabled) {
                            String emoji = "😊";
                            FontMetrics fm = g.getFontMetrics();
                            int emojiWidth = fm.stringWidth(emoji);
                            int emojiHeight = fm.getHeight();
                            int xPosition = xOffset + x * cellWidth + (cellWidth - emojiWidth) / 2;
                            int yPosition = yOffset + y * cellHeight + (cellHeight - emojiHeight) / 2 + fm.getAscent();
                            g.drawString(emoji, xPosition, yPosition);
                        } else {
                            g.fillRect(xOffset + x * cellWidth, yOffset + y * cellHeight, cellWidth, cellHeight);
                        }
                    } else {
                        g.setColor(deadCellColor);
                        g.fillRect(xOffset + x * cellWidth, yOffset + y * cellHeight, cellWidth, cellHeight);
                    }
                    g.setColor(Color.BLACK);
                    g.drawRect(xOffset + x * cellWidth, yOffset + y * cellHeight, cellWidth, cellHeight);
                }
            }
        } else {
            int subSize = node.ne.size;
            drawSubgrid(g, node.nw, xOffset, yOffset, cellWidth * 2, cellHeight * 2);
            drawSubgrid(g, node.ne, xOffset + subSize * cellWidth, yOffset, cellWidth * 2, cellHeight * 2);
            drawSubgrid(g, node.sw, xOffset, yOffset + subSize * cellHeight, cellWidth * 2, cellHeight * 2);
            drawSubgrid(g, node.se, xOffset + subSize * cellWidth, yOffset + subSize * cellHeight, cellWidth * 2, cellHeight * 2);
        }
    }
}

