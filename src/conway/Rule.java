package conway;

/**
 * L'interface Rule définit une méthode pour appliquer une règle à un noeud 
 * dans le contexte du jeu de la vie de Conway.
 */
public interface Rule {
    
    /**
     * Applique une règle à un noeud pour déterminer son état suivant.
     *
     * @param node Le noeud sur lequel la règle sera appliquée.
     * @return true si le noeud doit être vivant dans la prochaine itération, 
     *         false si le noeud doit être mort.
     */
    boolean applyRule(Node node);
}

