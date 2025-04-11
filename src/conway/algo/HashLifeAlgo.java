package conway.algo;

import java.util.HashMap;
import java.util.Map;
import conway.logic.*;

public class HashLifeAlgo {

    private static Map<String, Node> cache = new HashMap<>();

    public static Node generateNextState(Quadtree quadtree) {
        Node root = quadtree.getRoot();
        
        if (isCached(root)) {
            return cache.get(getCacheKey(root));  
        }
        
        if (root.isLeaf()) {
            Node nextState = Node.applyConwayRule(root);
            cache.put(getCacheKey(root), nextState);  
            return nextState;
        }

        Node ne = generateNextState(new Quadtree(root.ne.size, root.ne.state));
        Node nw = generateNextState(new Quadtree(root.nw.size, root.nw.state));
        Node sw = generateNextState(new Quadtree(root.sw.size, root.sw.state));
        Node se = generateNextState(new Quadtree(root.se.size, root.se.state));

        Node nextRoot = Node.combine(ne, nw, sw, se);
        cache.put(getCacheKey(nextRoot), nextRoot);  
        return nextRoot;
    }

    public static String getCacheKey(Node node) {
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < node.size; i++) {
            for (int j = 0; j < node.size; j++) {
                key.append(node.state[i][j] ? "1" : "0");
            }
        }
        return key.toString();
    }

    public static boolean isCached(Node node) {
        return cache.containsKey(getCacheKey(node));
    }
}

