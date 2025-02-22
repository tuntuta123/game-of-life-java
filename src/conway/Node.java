package conway;

/**
 * La classe Node représente un noeud dans la grille du jeu de la vie de Conway.
 * Chaque noeud a un état et des références vers ses voisins directs.
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
     * Constructeur pour initialiser un noeud avec son état .
     *
     * @param alive L'état initial du noeud.
     */
    public Node(boolean alive) {
        this.alive = alive;
    }

    /**
     * Vérifie si le noeud est vivant.
     *
     * @return true si le noeud est vivant, false sinon.
     */
    public boolean isAlive() {
        return this.alive;
    }

    /**
     * Modifie l'état du noeud .
     *
     * @param alive Le nouvel état du noeud.
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Récupère le voisin nord-est du noeud.
     *
     * @return Le voisin nord-est.
     */
    public Node getNorthEast() {
        return northEast;
    }

    /**
     * Définit le voisin nord-est du noeud.
     *
     * @param northEast Le voisin nord-est à définir.
     */
    public void setNorthEast(Node northEast) {
        this.northEast = northEast;
    }

    /**
     * Récupère le voisin nord-ouest du noeud.
     *
     * @return Le voisin nord-ouest.
     */
    public Node getNorthWest() {
        return northWest;
    }

    /**
     * Définit le voisin nord-ouest du noeud.
     *
     * @param northWest Le voisin nord-ouest à définir.
     */
    public void setNorthWest(Node northWest) {
        this.northWest = northWest;
    }

    /**
     * Récupère le voisin nord du noeud.
     *
     * @return Le voisin nord.
     */
    public Node getNorth() {
        return north;
    }

    /**
     * Définit le voisin nord du noeud.
     *
     * @param north Le voisin nord à définir.
     */
    public void setNorth(Node north) {
        this.north = north;
    }

    /**
     * Récupère le voisin sud-est du noeud.
     *
     * @return Le voisin sud-est.
     */
    public Node getSouthEast() {
        return southEast;
    }

    /**
     * Définit le voisin sud-est du noeud.
     *
     * @param southEast Le voisin sud-est à définir.
     */
    public void setSouthEast(Node southEast) {
        this.southEast = southEast;
    }

    /**
     * Récupère le voisin sud-ouest du noeud.
     *
     * @return Le voisin sud-ouest.
     */
    public Node getSouthWest() {
        return southWest;
    }

    /**
     * Définit le voisin sud-ouest du noeud.
     *
     * @param southWest Le voisin sud-ouest à définir.
     */
    public void setSouthWest(Node southWest) {
        this.southWest = southWest;
    }

    /**
     * Récupère le voisin sud du noeud.
     *
     * @return Le voisin sud.
     */
    public Node getSouth() {
        return south;
    }

    /**
     * Définit le voisin sud du noeud.
     *
     * @param south Le voisin sud à définir.
     */
    public void setSouth(Node south) {
        this.south = south;
    }

    /**
     * Récupère le voisin est du noeud.
     *
     * @return Le voisin est.
     */
    public Node getEast() {
        return east;
    }

    /**
     * Définit le voisin est du noeud.
     *
     * @param east Le voisin est à définir.
     */
    public void setEast(Node east) {
        this.east = east;
    }

    /**
     * Récupère le voisin ouest du noeud.
     *
     * @return Le voisin ouest.
     */
    public Node getWest() {
        return west;
    }

    /**
     * Définit le voisin ouest du noeud.
     *
     * @param west Le voisin ouest à définir.
     */
    public void setWest(Node west) {
        this.west = west;
    }
}

