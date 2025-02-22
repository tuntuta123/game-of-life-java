package conway;

public class Node {

    private boolean alive;
    private Node[] neighbors;
    
    public Node(boolean alive) {
        this.alive = alive;
        this.neighbors = new Node[8]; 
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Node[] getNeighborsNode() {
        return this.neighbors;
    }
    
    public void setNeighborsNode(Node[] neighbors){
    	this.neighbors = neighbors;
    }
}
