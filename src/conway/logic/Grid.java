package conway.logic;

/**
 * La classe Grid représente une grille de noeuds pour le jeu de la vie.
 * Chaque cellule peut être vivante ou morte, et cette classe contient des méthodes pour manipuler les cellules.
 */
public class Grid extends Node{

    private Node[][] grid; 
    private int width; 
    private int height;

    /**
     * Constructeur pour initialiser une grille avec une largeur et une hauteur.
     *
     * @param width La largeur de la grille.
     * @param height La hauteur de la grille.
     */
    public Grid(int width, int height) {
    	super(false);
        this.width = width;
        this.height = height;
        this.grid = new Node[width][height];
        
        // Initialisation d'une grille seulement avec des cellules mortes.
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.grid[x][y] = new Node(false); 
            }
        }
    }

    /**
     * Cette méthode récupère un noeud à une position donnée.
     *
     * @param x est un entier qui représente la coordonnée x du noeud.
     * @param y est un entier qui représente la coordonnée y du noeud.
     * @return Le noeud qui se trouve à la position (x, y).
     */
    public Node getNode(int x, int y) {
        return this.grid[x][y]; 
    }

    /**
     * Cette méthode modifie l'état d'un noeud à une position donnée.
     *
     * @param x est un entier qui représente la coordonnée x du noeud.
     * @param y est un entier qui représente la coordonnée y du noeud.
     * @param alive est un boolean qui représente l'état à attribuer au noeud (true ---> vivant, false ---> mort).
     */
    public void setNode(int x, int y, boolean alive) {
        this.grid[x][y].setAlive(alive); 
    }

    /**
     * Cette méthode récupère la largeur de la grille.
     *
     * @return La largeur de la grille.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Cette méthode récupère la hauteur de la grille.
     *
     * @return La hauteur de la grille.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Cette méthode définit les voisins de chaque noeud dans la grille.
     */
    public void setNeighbors() {
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                Node node = this.grid[x][y];
                Node[] neighbors = getNeighbors(x, y);

                node.setNorthEast(neighbors[0]);
                node.setNorth(neighbors[1]);
                node.setNorthWest(neighbors[2]);
                node.setWest(neighbors[3]);
                node.setEast(neighbors[4]);
                node.setSouthEast(neighbors[5]);
                node.setSouth(neighbors[6]);
                node.setSouthWest(neighbors[7]);
            }
        }
    }

    /**
     * Cette méthode récupère les voisins d'un noeud à une position donnée.
     *
     * @param x est un entier qui représente la coordonnée x du noeud.
     * @param y est un entier qui représente la coordonnée y du noeud.
     * @return Un tableau contenant les 8 voisins du noeud, ou null si un voisin est en dehors des limites de la grille.
     */
    private Node[] getNeighbors(int x, int y) {
        Node[] neighbors = new Node[8];
        int index = 0;

        // Parcours des voisins dans un rayon de 1 autour du noeud courant.
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) 
                    continue; // Ignore si c'est le noeud lui-même.

                int nx = x + dx;
                int ny = y + dy;

                // Vérifie si les coordonnées du voisin sont dans les limites de la grille. Si le voisin est hors limites, le met à null.
                if (nx >= 0 && nx < this.width && ny >= 0 && ny < this.height) 
                    neighbors[index++] = this.grid[nx][ny];
                else 
                    neighbors[index++] = null; 
            }
        }
        return neighbors;
    }
}

