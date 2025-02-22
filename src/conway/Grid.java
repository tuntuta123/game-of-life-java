package conway;

/**
 * La classe Grid représente une grille de noeuds pour le jeu de la vie de Conway.
 * Chaque cellule peut être vivante ou morte, et la grille contient des méthodes pour manipuler les cellules
 * et définir leurs voisins.
 */
public class Grid {
    private Node[][] grid; 
    private int width; 
    private int height;

    /**
     * Constructeur pour initialiser une grille avec une largeur et une hauteur données.
     *
     * @param width La largeur de la grille.
     * @param height La hauteur de la grille.
     */
    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Node[width][height];
        initializeGrid(); 
    }

    /**
     * Méthode privée pour initialiser la grille avec des cellules mortes.
     */
    private void initializeGrid() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.grid[x][y] = new Node(false); 
            }
        }
    }

    /**
     * Récupère un noeud à une position donnée.
     *
     * @param x La coordonnée x du noeud.
     * @param y La coordonnée y du noeud.
     * @return Le noeud situé à la position (x, y).
     */
    public Node getNode(int x, int y) {
        return grid[x][y]; 
    }

    /**
     * Modifie l'état d'un noeud à une position donnée.
     *
     * @param x La coordonnée x du noeud.
     * @param y La coordonnée y du noeud.
     * @param alive L'état à attribuer au noeud .
     */
    public void setNode(int x, int y, boolean alive) {
        this.grid[x][y].setAlive(alive); 
    }

    /**
     * Récupère la largeur de la grille.
     *
     * @return La largeur de la grille.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Récupère la hauteur de la grille.
     *
     * @return La hauteur de la grille.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Définit les voisins de chaque noeud dans la grille.
     */
    public void setNeighbors() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
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
     * Récupère les voisins d'un noeud à une position donnée.
     *
     * @param x La coordonnée x du noeud.
     * @param y La coordonnée y du noeud.
     * @return Un tableau contenant les 8 voisins du noeud, ou null si un voisin est en dehors des limites de la grille.
     */
    private Node[] getNeighbors(int x, int y) {
        Node[] neighbors = new Node[8];
        int index = 0;

        // Parcours des voisins dans un rayon de 1 autour du noeud.
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) 
                    continue; // Ignore le noeud lui-même.

                int nx = x + dx;
                int ny = y + dy;

                // Vérifie si les coordonnées du voisin sont dans les limites de la grille.
                if (nx >= 0 && nx < this.width && ny >= 0 && ny < this.height) {
                    neighbors[index++] = this.grid[nx][ny];
                } else {
                    neighbors[index++] = null; // Si le voisin est hors limites, le met à null.
                }
            }
        }

        return neighbors;
    }
}

