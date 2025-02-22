package conway;

public class Node {

    private boolean alive;
    private Node northEast;
    private Node northWest;
    private Node north;
    private Node southEast;
    private Node southWest;
    private Node south;
    private Node east;
    private Node west;
    
    public Node(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Node getNorthEast() {
        return northEast;
    }

    public void setNorthEast(Node northEast) {
        this.northEast = northEast;
    }

    public Node getNorthWest() {
        return northWest;
    }

    public void setNorthWest(Node northWest) {
        this.northWest = northWest;
    }

    public Node getNorth() {
        return north;
    }

    public void setNorth(Node north) {
        this.north = north;
    }

    public Node getSouthEast() {
        return southEast;
    }

    public void setSouthEast(Node southEast) {
        this.southEast = southEast;
    }

    public Node getSouthWest() {
        return southWest;
    }

    public void setSouthWest(Node southWest) {
        this.southWest = southWest;
    }

    public Node getSouth() {
        return south;
    }

    public void setSouth(Node south) {
        this.south = south;
    }

    public Node getEast() {
        return east;
    }

    public void setEast(Node east) {
        this.east = east;
    }

    public Node getWest() {
        return west;
    }

    public void setWest(Node west) {
        this.west = west;
    }
}

