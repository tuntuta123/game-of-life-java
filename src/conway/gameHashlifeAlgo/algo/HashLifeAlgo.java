package conway.gameHashlifeAlgo.algo;

import java.util.HashMap;
import java.util.Map;
import conway.gameHashlifeAlgo.logics.*;

public class HashLifeAlgo {

    public static NodeHashlife generateNextState(Quadtree quadtree) {
        NodeHashlife root = quadtree.getRoot();

        if (root.isLeaf()) {
            root = NodeHashlife.splitToRecursive(root);
        }

        NodeHashlife expanded = expandUniverse(root);
        NodeHashlife next = expanded.next();

        return next;
    }

    private static NodeHashlife expandUniverse(NodeHashlife node) {
        int level = node.level;
        NodeHashlife empty = createEmptyNode(level - 1);
        NodeHashlife topLeft = NodeHashlife.createNode(empty, empty, empty, node.nw);
        NodeHashlife topRight = NodeHashlife.createNode(empty, empty, node.ne, empty);
        NodeHashlife bottomLeft = NodeHashlife.createNode(empty, node.sw, empty, empty);
        NodeHashlife bottomRight = NodeHashlife.createNode(node.se, empty, empty, empty);
        return NodeHashlife.createNode(topLeft, topRight, bottomLeft, bottomRight);
    }

    private static NodeHashlife createEmptyNode(int level) {
        if (level == 0) {
            return new NodeHashlife(1, new boolean[2][2]);
        }
        NodeHashlife empty = createEmptyNode(level - 1);
        return NodeHashlife.createNode(empty, empty, empty, empty);
    }
}
