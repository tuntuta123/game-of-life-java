package conway.gameBasicAlgo.algo;

import conway.gameBasicAlgo.logics.*;
import java.util.*;

/**
 * La classe BasicAlgo implémente l'algorithme BasicAlgo pour simuler le jeu de la vie
 * en utilisant une cache pour améliorer les performances. L'algorithme utilise un HashMap pour mémoriser
 * les états précédents des cellules afin d'éviter les recalculs inutiles.
 */
public class BasicAlgo {
    private Grid grid;
    private Rule rule;
    private Map<Integer, Boolean> cache;

    /**
     * Constructeur pour initialiser l'algorithme BasicAlgo avec une grille et une règle spécifiée.
     *
     * @param grid La grille représentant l'état actuel du jeu.
     * @param rule La règle utilisée pour déterminer l'état des cellules.
     */
    public BasicAlgo(Grid grid, Rule rule) {
        this.grid = grid;
        this.rule = rule;
        this.cache = new HashMap<>();
    }

    /**
     * Calculer la prochaine génération de cellules en appliquant la règle.
     * Utilise le cache pour mémoriser les états des cellules déjà calculées.
     */
    public void generate() {
        Grid newGrid = new Grid(grid.getSize());
        grid.setNeighbors();
        for (int x = 0; x < grid.getSize(); x++) {
            for (int y = 0; y < grid.getSize(); y++) {
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

        for (int x = 0; x < grid.getSize(); x++) {
            for (int y = 0; y < grid.getSize(); y++) {
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
    private int getKey(int x, int y) {
        Node node = grid.getNode(x, y);
        
        // Création d'un tableau représentant l'état du noeud et de ses voisins.
        Object[] states = {
            node.isAlive(), 
            getNeighborKey(node.getNorth()), 
            getNeighborKey(node.getSouth()),
            getNeighborKey(node.getEast()),
            getNeighborKey(node.getWest()),
            getNeighborKey(node.getNorthEast()),
            getNeighborKey(node.getNorthWest()),
            getNeighborKey(node.getSouthEast()),
            getNeighborKey(node.getSouthWest())
        };
       
        return Arrays.hashCode(states);
    }

    /**
     * Retourne 1 si le voisin est vivant, sinon retourne 0.
     *
     * @param neighbor Le voisin à vérifier.
     * @return 1 si le voisin est vivant, sinon 0.
     */
    private int getNeighborKey(Node neighbor) {
    	if (neighbor != null && neighbor.isAlive()) 
    		return 1;
    	return 0;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BasicAlgo that = (BasicAlgo) obj;
        return grid.equals(that.grid) && rule.equals(that.rule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grid, rule);
    }
}
