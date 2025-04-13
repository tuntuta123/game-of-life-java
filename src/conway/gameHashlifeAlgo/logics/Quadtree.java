package conway.gameHashlifeAlgo.logics;

import conway.gameHashlifeAlgo.algo.*;

public class Quadtree {
    private NodeHashlife root;
    private int size;

    public Quadtree(int size, boolean[][] initialState) {
        this.size = size;
        this.root = new NodeHashlife(size, initialState);
    }

    public void generate() {
        this.root = HashLifeAlgo.generateNextState(this);
    }

    public void printState() {
        for (boolean[] row : this.root.state) {
            for (boolean cell : row) {
                System.out.print(cell ? "1" : "0");
            }
            System.out.println();
        }
    }

    public NodeHashlife getRoot() {
        return this.root;
    }

    public void setRoot(NodeHashlife root) {
        this.root = root;
    }
}

