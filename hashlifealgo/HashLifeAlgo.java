package conway.algo;

import java.util.HashMap;
import conway.logic.*;

public class HashLifeAlgo {
    private static final HashMap<Node, Node> cache = new HashMap<>();
    public Conway rule;

    public HashLifeAlgo() {
        this.rule = new Conway();
    }

    public Node getNextState(Node node) {
        if (node == null || !node.isAlive()) return node;

        if (cache.containsKey(node)) {
            return cache.get(node);
        }

        if (node.level == 2) {
            Node result = evolve(node);
            cache.put(node, result);
            return result;
        }

        Node c1 = getNextState(Node.create(node.nw.nw, node.nw.ne, node.nw.sw, node.nw.se));
        Node c2 = getNextState(Node.create(node.nw.ne, node.ne.nw, node.nw.se, node.ne.sw));
        Node c3 = getNextState(Node.create(node.ne.nw, node.ne.ne, node.ne.sw, node.ne.se));

        Node c4 = getNextState(Node.create(node.nw.sw, node.nw.se, node.sw.nw, node.sw.ne));
        Node c5 = getNextState(Node.create(node.nw.se, node.ne.sw, node.sw.ne, node.se.nw));
        Node c6 = getNextState(Node.create(node.ne.sw, node.ne.se, node.se.nw, node.se.ne));

        Node c7 = getNextState(Node.create(node.sw.nw, node.sw.ne, node.sw.sw, node.sw.se));
        Node c8 = getNextState(Node.create(node.sw.ne, node.se.nw, node.sw.se, node.se.sw));
        Node c9 = getNextState(Node.create(node.se.nw, node.se.ne, node.se.sw, node.se.se));

        Node result = Node.create(
            getNextState(Node.create(c1, c2, c4, c5)),
            getNextState(Node.create(c2, c3, c5, c6)),
            getNextState(Node.create(c4, c5, c7, c8)),
            getNextState(Node.create(c5, c6, c8, c9))
        );

        cache.put(node, result);
        return result;
    }

    public Node evolve(Node node) {
        return life4x4(node);
    }

    private Node life4x4(Node m) {
        Node a = m.nw;
        Node b = m.ne;
        Node c = m.sw;
        Node d = m.se;

        boolean ad = rule.evaluateCell(
            a.nw.alive, a.ne.alive, b.nw.alive,
            a.sw.alive, a.se.alive, b.sw.alive,
            c.nw.alive, c.ne.alive, d.nw.alive
        );

        boolean bc = rule.evaluateCell(
            a.ne.alive, b.nw.alive, b.ne.alive,
            a.se.alive, b.sw.alive, b.se.alive,
            c.ne.alive, d.nw.alive, d.ne.alive
        );

        boolean cb = rule.evaluateCell(
            a.sw.alive, a.se.alive, b.sw.alive,
            c.nw.alive, c.ne.alive, d.nw.alive,
            c.sw.alive, c.se.alive, d.sw.alive
        );

        boolean da = rule.evaluateCell(
            a.se.alive, b.sw.alive, b.se.alive,
            c.ne.alive, d.nw.alive, d.ne.alive,
            c.se.alive, d.sw.alive, d.se.alive
        );

        return Node.create(
            new Node(ad),
            new Node(bc),
            new Node(cb),
            new Node(da)
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
