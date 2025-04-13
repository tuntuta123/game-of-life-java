package conway.demo;

import conway.logic.*;

public class DemoTerminal {

    public static void main(String[] args) {
        boolean[][] initialState = {
            {false, false, false, false},
            {false, true, true, false},
            {false, true, true, false},
            {false, false, false, false}
        };

        GridHashlife grid = new GridHashlife(4, initialState);

        System.out.println("Initial State:");
        grid.printState();

        grid.generate();

        System.out.println("\nNext State:");
        grid.printState();
    }
}

