package conway.demo;

import conway.logic.*;
import conway.algo.*;

public class TerminalDemo {

    /*private int size = 8;*/
    private int size = 4;
    private Grid grid;
    private HashLifeAlgo hashLifeAlgo;
    private int generationCount = 0;

    public TerminalDemo() {
        this.grid = new Grid(size);
        this.hashLifeAlgo = new HashLifeAlgo();
        initializeBlinker();
    }

    private void initializeBlinker() {
        /*grid.setNode(2, 3, true);
        grid.setNode(3, 3, true);
        grid.setNode(4, 3, true);*/
        grid.setNode(1, 0, true);
        grid.setNode(2, 0, true);
        grid.setNode(3, 0, true);
    }

    private void printGrid() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (grid.getNode(x, y).isAlive()) {
                    System.out.print("O ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private void updateAliveCellCount() {
        int aliveCount = 0;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (grid.getNode(x, y).isAlive()) {
                    aliveCount++;
                }
            }
        }
    }

    public void run() {
        while (true) {
            System.out.println("generation: " + generationCount);
            printGrid();
            updateAliveCellCount();

            System.out.println("1. next");
            System.out.println("2. quit");
            System.out.print("Choisissez une option: ");
            int choice = new java.util.Scanner(System.in).nextInt();

            switch (choice) {
                case 1:
                    hashLifeAlgo.generate(grid);
                    generationCount++;
                    break;
                case 2:
                    System.out.println("exit");
                    return;
                default:
                    System.out.println("  ");
            }
        }
    }

    public static void main(String[] args) {
        TerminalDemo demo = new TerminalDemo();
        demo.run();
    }
}
