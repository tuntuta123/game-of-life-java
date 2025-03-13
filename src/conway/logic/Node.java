package conway.logic;
import java.util.HashMap;
import java.util.Objects;
/**
 * La classe Node représente un noeud dans la grille du jeu de la vie.
 *
 * Chaque noeud a un état et des références vers ses voisins directs qui nous aident dans l'algorithme Hashlife.
 */
public class Node {

    public boolean alive; 
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

    private static final HashMap<Node, Node> uniqueNodes = new HashMap<>();
    public int level; 
    
    
    public Node nw, ne, sw, se;
    
    public Node(Node nw, Node ne, Node sw, Node se, int level, boolean alive) {
        this.nw = nw;
        this.ne = ne;
        this.sw = sw;
        this.se = se;
        this.level = level;
        this.alive = alive;
    }

    public Node(boolean alive) {
        this.alive = alive;
        this.level = 0;
        this.nw = this.ne = this.sw = this.se = null;
    }


    public boolean isLeaf() {
        return level == 0;
    }
    
    public static Node create(Node nw, Node ne, Node sw, Node se) {
    	Node temp = new Node(nw, ne, sw, se, nw.level + 1, false);
    
    	if (uniqueNodes.containsKey(temp)) {
        	return uniqueNodes.get(temp);
    	} 
    	else {
        	uniqueNodes.put(temp, temp);
        	return temp;
    	}
    }
    public boolean isEmpty() {
        if (isLeaf()) {
            return !alive;
        }
        return nw.isEmpty() && ne.isEmpty() && sw.isEmpty() && se.isEmpty();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node node = (Node) obj;
        return level == node.level &&
               alive == node.alive &&
               Objects.equals(nw, node.nw) &&
               Objects.equals(ne, node.ne) &&
               Objects.equals(sw, node.sw) &&
               Objects.equals(se, node.se);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nw, ne, sw, se, level, alive);
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

