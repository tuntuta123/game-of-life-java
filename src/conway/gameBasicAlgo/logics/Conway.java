package conway.gameBasicAlgo.logics;

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
        int liveNeighbors = 0;

        if (node.getNorthEast() != null && node.getNorthEast().isAlive()) 
        	liveNeighbors++;
        if (node.getNorth() != null && node.getNorth().isAlive()) 
        	liveNeighbors++;
        if (node.getNorthWest() != null && node.getNorthWest().isAlive()) 
        	liveNeighbors++;
        if (node.getWest() != null && node.getWest().isAlive()) 
        	liveNeighbors++;
        if (node.getEast() != null && node.getEast().isAlive()) 
        	liveNeighbors++;
        if (node.getSouthEast() != null && node.getSouthEast().isAlive()) 
        	liveNeighbors++;
        if (node.getSouth() != null && node.getSouth().isAlive()) 
        	liveNeighbors++;
        if (node.getSouthWest() != null && node.getSouthWest().isAlive()) 
        	liveNeighbors++;

        if (node.isAlive()) {
            return liveNeighbors == 2 || liveNeighbors == 3;
        } else {
            return liveNeighbors == 3;
        }
    }
}

