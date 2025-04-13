package conway.gameHashlifeAlgo.logics;

import conway.gameHashlifeAlgo.algo.*;
import java.util.Objects;

public class GridHashlife implements Comparable<GridHashlife> {

    private int size;
    private NodeHashlife root;

    public GridHashlife(int size, boolean[][] initialState) {
        this.size = size;
        this.root = new NodeHashlife(size, initialState);
    }

    public void generate() {
        Quadtree quadtree = gridToQuadtree();
        quadtree.setRoot(HashLifeAlgo.generateNextState(quadtree));
        this.root = quadtree.getRoot();
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
    private Quadtree gridToQuadtree() {
        return new Quadtree(this.size, this.root.state);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GridHashlife that = (GridHashlife) o;
        return size == that.size && Objects.equals(root, that.root);
    }
    @Override
    public int hashCode() {
        return Objects.hash(size, root);
    }

    @Override
    public int compareTo(GridHashlife other) {
        return Integer.compare(this.size, other.size);
    }
}

