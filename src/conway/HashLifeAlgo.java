package conway;

import java.util.HashMap;

/**
 * La classe HashLifeAlgo implémente l'algorithme HashLife pour simuler le jeu de la vie de Conway
 * en utilisant une cache pour améliorer les performances. L'algorithme utilise un HashMap pour mémoriser 
 * les états précédents des cellules afin d'éviter les recalculs inutiles.
 */
public class HashLifeAlgo {
    private Grid grid; 
    private Rule rule; 
    private HashMap<Integer, Boolean> cache; 

    /**
     * Constructeur pour initialiser l'algorithme HashLife avec une grille et une règle spécifiée.
     *
     * @param grid La grille représentant l'état actuel du jeu.
     * @param rule La règle utilisée pour déterminer l'état des cellules.
     */
    public HashLifeAlgo(Grid grid, Rule rule) {
        this.grid = grid;
        this.rule = rule;
        this.cache = new HashMap<>();
    }

    /**
     * Calculer la prochaine génération de cellules en appliquant la règle.
     * Utilise le cache pour mémoriser les états des cellules déjà calculées.
     */
    public void generate() {
        Grid newGrid = new Grid(grid.getWidth(), grid.getHeight()); 
        grid.setNeighbors(); 
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                Node current = grid.getNode(x, y); 
                int key = getKey(x, y); 
                Boolean stored = cache.get(key); 

                boolean state;
                if (stored != null) {
                    // Si l'état du noeud est déjà dans le cache, on l'utilise.
                    state = stored;
                } else {
                    // Sinon, on applique la règle pour calculer l'état du noeud.
                    state = rule.applyRule(current);
                    cache.put(key, state); // On mémorise l'état dans le cache.
                }

                newGrid.setNode(x, y, state);
            }
        }

        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                grid.setNode(x, y, newGrid.getNode(x, y).isAlive());
            }
        }

        grid.setNeighbors(); 
    }

    /**
     * Génère un code de hachage unique pour un noeud basé sur son état et celui de ses voisins.
     *
     * @param x La coordonnée x du noeud.
     * @param y La coordonnée y du noeud.
     * @return Un code de hachage représentant l'état du noeud et de ses voisins.
     */
	private int getKey(int x, int y) { Node node = grid.getNode(x, y); int hashCode = 31 * (node.isAlive() ? 1 : 0); 
		hashCode = 31 * hashCode + getNeighborKey(node.getNorth()); 
		hashCode = 31 * hashCode + getNeighborKey(node.getSouth());
 		hashCode = 31 * hashCode + getNeighborKey(node.getEast());
  		hashCode = 31 * hashCode + getNeighborKey(node.getWest());
   		hashCode = 31 * hashCode + getNeighborKey(node.getNorthEast());
    	hashCode = 31 * hashCode + getNeighborKey(node.getNorthWest());
     	hashCode = 31 * hashCode + getNeighborKey(node.getSouthEast());
      	hashCode = 31 * hashCode + getNeighborKey(node.getSouthWest());
       return hashCode; 
       } 
       
	private int getNeighborKey(Node neighbor) { 
    	if (neighbor != null) {
    		if (neighbor.isAlive()){
    			return 1;
    		}else {
    			return 0;
    		} 
    	}	
    	return 0; 
    }

}

