package conway;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {

    private Grid grid;

    public GridPanel(Grid grid) {
        this.grid = grid;
        setPreferredSize(new Dimension(500, 500)); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
    }

    private void drawGrid(Graphics g) {
        int cellWidth = getWidth() / grid.getWidth();
        int cellHeight = getHeight() / grid.getHeight();

        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                Node node = grid.getNode(x, y);
                if (node.isAlive()) {
                    g.setColor(Color.red);
                    g.fillRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
                } else {
                    g.setColor(Color.pink);
                    g.fillRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
                }
                g.setColor(Color.BLACK);
                g.drawRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight); 
            }
        }
    }
}

