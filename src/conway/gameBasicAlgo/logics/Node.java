package conway.gameBasicAlgo.logics;

/**
 * La classe Node représente un noeud dans la grille du jeu de la vie.
 *
 * Chaque noeud a un état et des références vers ses voisins directs qui nous aident dans l'algorithme Hashlife.
 */
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

    /**
     * Constructeur pour initialiser un noeud avec son état.
     *
     * @param alive est l'état initial du noeud.
     */
    public Node(boolean alive) {
        this.alive = alive;
    }

    /**
     * Cette méthode vérifie si le noeud est vivant.
     *
     * @return true si le noeud est vivant, false sinon.
     */
    public boolean isAlive() {
        return this.alive;
    }

    /**
     * Cette méthode modifie l'état du noeud.
     *
     * @param alive est le nouvel état du noeud (true -> vivant, false -> mort).
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Node getNorthEast() { return northEast; }
    public void setNorthEast(Node northEast) { this.northEast = northEast; }

    public Node getNorthWest() { return northWest; }
    public void setNorthWest(Node northWest) { this.northWest = northWest; }

    public Node getNorth() { return north; }
    public void setNorth(Node north) { this.north = north; }

    public Node getSouthEast() { return southEast; }
    public void setSouthEast(Node southEast) { this.southEast = southEast; }

    public Node getSouthWest() { return southWest; }
    public void setSouthWest(Node southWest) { this.southWest = southWest; }

    public Node getSouth() { return south; }
    public void setSouth(Node south) { this.south = south; }

    public Node getEast() { return east; }
    public void setEast(Node east) { this.east = east; }

    public Node getWest() { return west; }
    public void setWest(Node west) { this.west = west; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return alive == node.alive &&
                equalsNeighbors(node);
    }

    private boolean equalsNeighbors(Node node) {
        return (northEast == node.northEast) &&
                (northWest == node.northWest) &&
                (north == node.north) &&
                (southEast == node.southEast) &&
                (southWest == node.southWest) &&
                (south == node.south) &&
                (east == node.east) &&
                (west == node.west);
    }

    @Override
    public int hashCode() {
        int result = Boolean.hashCode(alive);
        result = 31 * result + hashNeighbors();
        return result;
    }

    private int hashNeighbors() {
        int result = 1;
        result = 31 * result + (northEast != null ? northEast.hashCode() : 0);
        result = 31 * result + (northWest != null ? northWest.hashCode() : 0);
        result = 31 * result + (north != null ? north.hashCode() : 0);
        result = 31 * result + (southEast != null ? southEast.hashCode() : 0);
        result = 31 * result + (southWest != null ? southWest.hashCode() : 0);
        result = 31 * result + (south != null ? south.hashCode() : 0);
        result = 31 * result + (east != null ? east.hashCode() : 0);
        result = 31 * result + (west != null ? west.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Node{" +
                "alive=" + alive +
                ", northEast=" + northEast +
                ", northWest=" + northWest +
                ", north=" + north +
                ", southEast=" + southEast +
                ", southWest=" + southWest +
                ", south=" + south +
                ", east=" + east +
                ", west=" + west +
                '}';
    }
}

