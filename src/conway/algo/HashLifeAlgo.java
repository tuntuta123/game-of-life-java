package conway.algo;

import java.util.HashMap;

import conway.logic.*;


//possible errors causing the wrong generation : node levels not correctly initialising, neighbor init's in other classes, wrong recursion (unlikely), 

//conway.java'yi kontrol et oradaki uygulamaya bak. bunu ilk olarak dene sonra digerlerine bak

//ustekkinin faydasi nodelistesi kullanmamasi kodu kisaltiyor ve COK FAZLA yazmaktan olusan potansiyel problemleri ekarte ediyor

//

public class HashLifeAlgo {
    private static final HashMap<Node, Node> cache = new HashMap<>();
    public Conway rule;

    public HashLifeAlgo() {
        this.rule = new Conway();
    }

    public Node getNextState(Node node) {
        if (cache.containsKey(node)) {
            return cache.get(node);
        }

        if (node.isLeaf()) {
            Node evolvedNode = evolve(node);
            cache.put(node, evolvedNode);
            return evolvedNode;
        }

        Node nextNW = (node.nw != null) ? getNextState(node.nw) : null;
        Node nextNE = (node.ne != null) ? getNextState(node.ne) : null;
        Node nextSW = (node.sw != null) ? getNextState(node.sw) : null;
        Node nextSE = (node.se != null) ? getNextState(node.se) : null;

        Node nextNode = Node.create(nextNW, nextNE, nextSW, nextSE);
        cache.put(node, nextNode);
        return nextNode;
    }

    public Node evolve(Node node) {
        if (node == null) {
            System.out.println("null node!");
            return new Node(false); 
        }

        boolean nextNW = (node.nw != null) && rule.applyRule(node.nw);
        boolean nextNE = (node.ne != null) && rule.applyRule(node.ne);
        boolean nextSW = (node.sw != null) && rule.applyRule(node.sw);
        boolean nextSE = (node.se != null) && rule.applyRule(node.se);

        return Node.create(
            node.nw != null ? evolve(node.nw) : null,
            node.ne != null ? evolve(node.ne) : null,
            node.sw != null ? evolve(node.sw) : null,
            node.se != null ? evolve(node.se) : null
        );
    }

    public void generate(Grid grid) {
        Node root = grid.toQuadtree();
        if (root == null) {
            System.out.println("Root node null.");
            return;
        }

        Node nextRoot = getNextState(root);
        if (nextRoot == null) {
            System.out.println("Next root null.");
            return;
        }

        grid.fromQuadtree(nextRoot);
    }
}
