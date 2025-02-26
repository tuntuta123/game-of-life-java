package conway.logic;

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
     * Constructeur pour initialiser un noeud avec son état .
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

    /**
     * Cette méthode récupère le voisin qui est au nord-est du noeud.
     *
     * @return Le voisin nord-est du noeud.
     */
    public Node getNorthEast() {
        return northEast;
    }

    /**
     * Cette méthode modifie le voisin nord-est du noeud.
     *
     * @param northEast est le voisin nord-est à définir.
     */
    public void setNorthEast(Node northEast) {
        this.northEast = northEast;
    }

    /**
     * Cette méthode récupère le voisin nord-ouest du noeud.
     *
     * @return Le voisin nord-ouest du noeud.
     */
    public Node getNorthWest() {
        return northWest;
    }

    /**
     * Cette méthode définit le voisin nord-ouest du noeud.
     *
     * @param northWest est le voisin nord-ouest du noeud.
     */
    public void setNorthWest(Node northWest) {
        this.northWest = northWest;
    }

    /**
     * Cette méthode récupère le voisin nord du noeud.
     *
     * @return Le voisin nord du noeud.
     */
    public Node getNorth() {
        return north;
    }

    /**
     * Cette méthode définit le voisin nord du noeud.
     *
     * @param north est le voisin nord do noeud qui doit etre définit.
     */
    public void setNorth(Node north) {
        this.north = north;
    }

    /**
     * Cette méthode récupère le voisin sud-est du noeud.
     *
     * @return Le voisin sud-est du noeud.
     */
    public Node getSouthEast() {
        return southEast;
    }

    /**
     * Cette méthode définit le voisin sud-est du noeud.
     *
     * @param southEast est le voisin sud-est à définir.
     */
    public void setSouthEast(Node southEast) {
        this.southEast = southEast;
    }

    /**
     * Cette méthode récupère le voisin sud-ouest du noeud.
     *
     * @return Le voisin qui est au sud-ouest du noeud.
     */
    public Node getSouthWest() {
        return southWest;
    }

    /**
     * Cette méthode définit le voisin sud-ouest du noeud.
     *
     * @param southWest est le voisin sud-ouest à définir.
     */
    public void setSouthWest(Node southWest) {
        this.southWest = southWest;
    }

    /**
     * Cette méthode récupère le voisin sud du noeud.
     *
     * @return Le voisin qui est au sud du noeud.
     */
    public Node getSouth() {
        return south;
    }

    /**
     * Cette méthode définit le voisin sud du noeud.
     *
     * @param south est le voisin sud à définir.
     */
    public void setSouth(Node south) {
        this.south = south;
    }

    /**
     * Cette méthode récupère le voisin est du noeud.
     *
     * @return Le voisin est du noeud.
     */
    public Node getEast() {
        return east;
    }

    /**
     * Cette méthode définit le voisin est du noeud.
     *
     * @param east est le voisin est à définir.
     */
    public void setEast(Node east) {
        this.east = east;
    }

    /**
     * Cette méthode récupère le voisin ouest du noeud.
     *
     * @return Le voisin ouest du noeud.
     */
    public Node getWest() {
        return west;
    }

    /**
     * Cette méthode définit le voisin ouest du noeud.
     *
     * @param west est le voisin ouest à définir.
     */
    public void setWest(Node west) {
        this.west = west;
    }
}

