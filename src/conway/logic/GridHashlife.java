package conway.logic;

import conway.algo.*;

public class GridHashlife {

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

}

