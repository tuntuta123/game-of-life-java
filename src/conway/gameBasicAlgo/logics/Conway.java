package conway.gameBasicAlgo.logics;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Conway implémente l'interface Rule et définit la règle du jeu de la vie de Conway.
 * Cette règle détermine si un noeud doit être vivant ou mort dans la prochaine itération,
 * en fonction de ses voisins.
 */
public class Conway implements Rule {

    /**
     * Applique la règle de Conway pour déterminer l'état suivant d'un noeud.
     * Un noeud vivant reste en vie s'il a 2 ou 3 voisins vivants,
     * sinon il meurt. Un noeud mort devient vivant s'il a exactement 3 voisins vivants.
     *
     * @param node Le noeud sur lequel la règle sera appliquée.
     * @return true si le noeud doit être vivant dans la prochaine itération, 
     *         false sinon.
     */
    @Override
    public boolean applyRule(Node node) {
        List<Node> neighbors = getNeighbors(node);
        int liveNeighbors = 0;

        for (Node neighbor : neighbors) {
            if (neighbor != null && neighbor.isAlive()) {
                liveNeighbors++;
            }
        }

        if (node.isAlive()) {
            return liveNeighbors == 2 || liveNeighbors == 3;
        } else {
            return liveNeighbors == 3;
        }
    }

    /**
     * Get the neighbors of the node as a list
     *
     * @param node The node for which we are finding neighbors
     * @return List of neighbors
     */
    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        neighbors.add(node.getNorthWest());
        neighbors.add(node.getNorth());
        neighbors.add(node.getNorthEast());
        neighbors.add(node.getWest());
        neighbors.add(node.getEast());
        neighbors.add(node.getSouthWest());
        neighbors.add(node.getSouth());
        neighbors.add(node.getSouthEast());
        return neighbors;
    }
}

