package conway.logic;

/**
 * La classe Grid représente une grille de noeuds pour le jeu de la vie.
 * Chaque cellule peut être vivante ou morte, et cette classe contient des méthodes pour manipuler les cellules.
 */
public class Grid{

    public Node[][] grid;
    public int size;
    /**
     * Constructeur pour initialiser une grille avec une largeur et une hauteur.
     *
     * @param size La largeur de la grille.
     */
    public Grid(int size) {
        /*super(false);*/
        this.size = size;
        this.grid = new Node[size][size];
        
        // Initialisation d'une grille seulement avec des cellules mortes.
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
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
    public int getSize() {
        return this.size;
    }

    /**
     * Cette méthode définit les voisins de chaque noeud dans la grille.
     */
    public void setNeighbors() {
        for (int x = 0; x < this.size; x++) {
            for (int y = 0; y < this.size; y++) {
                Node node = this.grid[x][y];
                Node[] neighbors = getNeighbors(x, y);

                node.setNorthWest(neighbors[0]);
                node.setNorth(neighbors[1]);
                node.setNorthEast(neighbors[2]);
                node.setWest(neighbors[3]);
                node.setEast(neighbors[4]);
                node.setSouthWest(neighbors[5]);
                node.setSouth(neighbors[6]);
                node.setSouthEast(neighbors[7]);
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
    public Node[] getNeighbors(int x, int y) {
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
                if (nx >= 0 && nx < this.size && ny >= 0 && ny < this.size)
                    neighbors[index++] = this.grid[nx][ny];
                else
                    neighbors[index++] = null;
            }
        }
        return neighbors;
    }

    public Node toQuadtree() {
        return toQuadtree(0, 0, this.getSize());
    }

    public Node toQuadtree(int x, int y, int size) {

        if (size == 1) {
            System.out.println("toQuadtree: x=" + x + ", y=" + y + ", size=" + size + ", alive=" + getNode(x, y).isAlive());
            return new Node(getNode(x, y).isAlive());
    }
    System.out.println("toQuadtree size " + size);
        int halfSize = size / 2;

    /*correctly itarates thru*/

        Node nw = toQuadtree(x, y, halfSize);
        Node ne = toQuadtree(x, y+ halfSize, halfSize);
        Node sw = toQuadtree(x+ halfSize, y , halfSize);
        Node se = toQuadtree(x + halfSize, y + halfSize, halfSize);

        return Node.create(nw, ne, sw, se);
     }


    public void fromQuadtree(Node root) {
       fromQuadtree(root, 0, 0, this.getSize());
    }

    public void fromQuadtree(Node node, int x, int y, int size) {
        if (node.isLeaf()) {
        System.out.println("fromQuadtree size " + size);
        for (int i = 0; i < size; i++) {
		for (int j = 0; j < size; j++) {
		    Node leaf = getLeafAt(node, j, i, size);
		    System.out.println("fromQuadtree: x=" + (x + j) + ", y=" + (y + i) + ", alive=" + leaf.isAlive());
		    setNode(x + j, y + i, leaf.isAlive());
		}
        }
        return;
    }
    	System.out.println("fromQuadtree size " + size);
        int halfSize = size / 2;

        fromQuadtree(node.nw, x, y, halfSize);
        fromQuadtree(node.ne, x , y+ halfSize, halfSize);
        fromQuadtree(node.sw, x+ halfSize, y , halfSize);
        fromQuadtree(node.se, x + halfSize, y + halfSize, halfSize);
    }
    public Node getLeafAt(Node node, int i, int j, int size) {
        if (node == null) return new Node(false);

        if (node.level == 0) return node;

        int half = size / 2;

        if (i < half && j < half) {
        return getLeafAt(node.nw, i, j, half);
        } else if (i < half && j >= half) {
        return getLeafAt(node.ne, i, j - half, half);
        } else if (i >= half && j < half) {
        return getLeafAt(node.sw, i - half, j, half);
        } else {
        return getLeafAt(node.se, i - half, j - half, half);
        }
    }

}
