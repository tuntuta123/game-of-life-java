package conway;

import java.util.Random;

/**
 * La classe Demo permet de simuler le jeu de la vie de Conway en générant une grille initiale aléatoire 
 * et en affichant les générations successives.
 * Elle utilise la classe HashLifeAlgo pour appliquer l'algorithme de simulation avec la règle de Conway.
 */
public class Demo {

    public static void main(String[] args) {
    
        int width = 10; 
        int height = 10; 
        
        Grid grid = new Grid(width, height); 
        Random rand = new Random(); 

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (rand.nextDouble() < 0.2) { // 20% de chance qu'une cellule soit vivante.
                    grid.setNode(x, y, true);
                }
            }
        }

        Rule conwayRule = new Conway(); 
        HashLifeAlgo hashLifeAlgo = new HashLifeAlgo(grid, conwayRule); 

        System.out.println("Initial grid:");
        printGrid(grid);

        for (int i = 0; i < 10; i++) {
            System.out.println("Step " + (i + 1) + ":");
            hashLifeAlgo.generate(); 
            printGrid(grid); 
        }
    }

    /**
     * Affiche l'état de la grille dans la console.
     * Les cellules vivantes sont représentées par "O" et les cellules mortes par ".".
     *
     * @param grid La grille à afficher.
     */
    private static void printGrid(Grid grid) {
        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                if (grid.getNode(x, y).isAlive()) {
                    System.out.print("O"); 
                } else {
                    System.out.print("."); 
                }
            }
            System.out.println(); 
        }
        System.out.println(); 
    }
}

