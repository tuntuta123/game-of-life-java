package conway.gameHashlifeAlgo.logics;

import conway.gameHashlifeAlgo.algo.*;
import java.util.Objects;

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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quadtree quadtree = (Quadtree) o;
        return size == quadtree.size && Objects.equals(root, quadtree.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, root);
    }
}

