package conway;
import javax.swing.*;
import java.awt.*;

public class Executable extends JPanel {

    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 

        int cellSize = 5; 
        int gridSize = 500; 

        
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                int x = col * cellSize;  
                int y = row * cellSize;  

                g.drawRect(x, y, cellSize, cellSize);
            }
        }
    }

    public static void main(String[] args) {
        
        JFrame frame = new JFrame("4x4 Grid with Borders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 2000); 

       
        Executable panel = new Executable();
        frame.add(panel);

        
        frame.setVisible(true);
    }
}

