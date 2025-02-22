package conway;
import java.util.Random;

public class Demo {

    public static void main(String[] args) {
    
        int width = 10;
        int height = 10;
        
        Grid grid = new Grid(width, height);
        Random rand = new Random();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (rand.nextDouble() < 0.2) { 
                    grid.setNode(x, y, true);
                }
            }
        }

        Rule conwayRule = new Conway();
        HashLifeAlgo hashLifeAlgo = new HashLifeAlgo(grid, conwayRule);

        System.out.println("Initial grid");
        printGrid(grid);

        for (int i = 0; i < 10; i++) {
            System.out.println("Step " + (i + 1));
            hashLifeAlgo.generate();
            printGrid(grid);
        }
    }

    private static void printGrid(Grid grid) {
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
            	if (grid.getNode(x, y).isAlive())
                	System.out.print("O");
                else 
                	System.out.print(".");
            }
            System.out.println();
        }
        System.out.println();
    }
}

