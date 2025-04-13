package conway.gameBasicAlgo.logics;

import java.util.*;

/**
 * La classe Grid représente une grille de noeuds pour le jeu de la vie.
 * Chaque cellule peut être vivante ou morte, et cette classe contient des méthodes pour manipuler les cellules.
 */
public class Grid implements Iterable<Node> {

    private Map<String, Node> nodeMap;  
    private int size;

    /**
     * Constructeur pour initialiser une grille avec une largeur et une hauteur.
     *
     * @param size La largeur de la grille.
     */
    public Grid(int size) {
        this.size = size;
        this.nodeMap = new HashMap<>();

        // Initialisation d'une grille seulement avec des cellules mortes.
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Node node = new Node(false);
                this.nodeMap.put(x + "," + y, node);  
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
        return this.nodeMap.get(x + "," + y);
    }

    /**
     * Cette méthode modifie l'état d'un noeud à une position donnée.
     *
     * @param x est un entier qui représente la coordonnée x du noeud.
     * @param y est un entier qui représente la coordonnée y du noeud.
     * @param alive est un boolean qui représente l'état à attribuer au noeud (true ---> vivant, false ---> mort).
     */
    public void setNode(int x, int y, boolean alive) {
        Node node = this.getNode(x, y);
        if (node != null) {
            node.setAlive(alive);
        }
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
                Node node = this.getNode(x, y);
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
                    neighbors[index++] = this.getNode(nx, ny);
                else
                    neighbors[index++] = null;
            }
        }
        return neighbors;
    }

    /**
     * Clear the grid (set all nodes to dead).
     */
    public void clearGrid() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                this.getNode(x, y).setAlive(false);  
            }
        }
    }

    /**
     * Returns an iterator to traverse the grid nodes.
     */
    @Override
    public Iterator<Node> iterator() {
        return new Iterator<Node>() {
            private Iterator<Map.Entry<String, Node>> it = nodeMap.entrySet().iterator();

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Node next() {
                return it.next().getValue();
            }
        };
    }

    /**
     * Comparator for sorting nodes based on their state (alive or dead).
     */
    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return Boolean.compare(o1.isAlive(), o2.isAlive());
        }
    }

    /**
     * Checks if two Grid objects are equal (based on nodeMap).
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Grid grid = (Grid) obj;
        return Objects.equals(nodeMap, grid.nodeMap);
    }

    /**
     * Generates a hashCode for the Grid object based on nodeMap.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nodeMap);
    }

    /**
     * Compares this Grid to another Grid based on size and node states.
     */
    public int compareTo(Grid other) {
        return Integer.compare(this.size, other.size);
    }
}

