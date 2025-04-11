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
    public boolean[][] state; 
    public int size;  
    public Node ne, nw, sw, se;
    public Node(int size, boolean[][] state) {
        this.size = size;
        this.state = state;
        this.ne = this.nw = this.sw = this.se = null;
    }

    public Node(int size, Node ne, Node nw, Node sw, Node se) {
        this.size = size;
        this.ne = ne;
        this.nw = nw;
        this.sw = sw;
        this.se = se;
        this.state = null;  
    }
    
    public boolean[][] getState() {
        return this.state;
    }

    public void setState(boolean[][] state) {
        this.state = state;
    }

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
    
    public boolean isLeaf() {
        return ne == null && nw == null && sw == null && se == null;
    }

    public static Node split(Node root) {
        int halfSize = root.size / 2;
        boolean[][] topLeft = new boolean[halfSize][halfSize];
        boolean[][] topRight = new boolean[halfSize][halfSize];
        boolean[][] bottomLeft = new boolean[halfSize][halfSize];
        boolean[][] bottomRight = new boolean[halfSize][halfSize];

        for (int i = 0; i < halfSize; i++) {
            for (int j = 0; j < halfSize; j++) {
                topLeft[i][j] = root.state[i][j];
                topRight[i][j] = root.state[i][halfSize + j];
                bottomLeft[i][j] = root.state[halfSize + i][j];
                bottomRight[i][j] = root.state[halfSize + i][halfSize + j];
            }
        }

        Node ne = new Node(halfSize, topRight);
        Node nw = new Node(halfSize, topLeft);
        Node sw = new Node(halfSize, bottomLeft);
        Node se = new Node(halfSize, bottomRight);

        return new Node(root.size, ne, nw, sw, se);
    }

    public static Node applyConwayRule(Node leaf) {
        int size = leaf.size;
        boolean[][] nextState = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int liveNeighbors = countLiveNeighbors(leaf, i, j);
                if (leaf.state[i][j]) {
                    nextState[i][j] = liveNeighbors == 2 || liveNeighbors == 3;
                } else {
                    nextState[i][j] = liveNeighbors == 3;
                }
            }
        }

        return new Node(size, nextState);
    }

    private static int countLiveNeighbors(Node leaf, int x, int y) {
        int[][] neighbors = {
            {-1, -1}, {-1, 0}, {-1, 1},
            { 0, -1},           { 0, 1},
            { 1, -1}, { 1, 0}, { 1, 1}
        };
        int count = 0;
        for (int[] neighbor : neighbors) {
            int nx = x + neighbor[0];
            int ny = y + neighbor[1];
            if (nx >= 0 && nx < leaf.size && ny >= 0 && ny < leaf.size && leaf.state[nx][ny]) {
                count++;
            }
        }
        return count;
    }

    public static Node combine(Node ne, Node nw, Node sw, Node se) {
        int size = ne.size * 2;
        boolean[][] combinedState = new boolean[size][size];

        if (ne.isLeaf() && nw.isLeaf() && sw.isLeaf() && se.isLeaf()) {
            for (int i = 0; i < ne.size; i++) {
                for (int j = 0; j < ne.size; j++) {
                    combinedState[i][j] = nw.state[i][j];
                    combinedState[i][ne.size + j] = ne.state[i][j];
                    combinedState[ne.size + i][j] = sw.state[i][j];
                    combinedState[ne.size + i][ne.size + j] = se.state[i][j];
                }
            }
        } else {
            for (int i = 0; i < ne.size; i++) {
                for (int j = 0; j < ne.size; j++) {
                    combinedState[i][j] = nw.state[i][j];
                    combinedState[i][ne.size + j] = ne.state[i][j];
                    combinedState[ne.size + i][j] = sw.state[i][j];
                    combinedState[ne.size + i][ne.size + j] = se.state[i][j];
                }
            }
        }

        return new Node(size, ne, nw, sw, se);
    }
}

