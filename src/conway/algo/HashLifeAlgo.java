package conway.algo;

import java.util.HashMap;
import java.util.Map;
import conway.logic.*;

public class HashLifeAlgo {

    private static Map<String, NodeHashlife> cache = new HashMap<>();

    public static NodeHashlife generateNextState(Quadtree quadtree) {
        NodeHashlife root = quadtree.getRoot();
        
        if (isCached(root)) {
            return cache.get(getCacheKey(root));  
        }
        
        if (root.isLeaf()) {
            NodeHashlife nextState = NodeHashlife.applyConwayRule(root);
            cache.put(getCacheKey(root), nextState);  
            return nextState;
        }

        NodeHashlife ne = generateNextState(new Quadtree(root.ne.size, root.ne.state));
        NodeHashlife nw = generateNextState(new Quadtree(root.nw.size, root.nw.state));
        NodeHashlife sw = generateNextState(new Quadtree(root.sw.size, root.sw.state));
        NodeHashlife se = generateNextState(new Quadtree(root.se.size, root.se.state));

        NodeHashlife nextRoot = NodeHashlife.combine(ne, nw, sw, se);
        cache.put(getCacheKey(nextRoot), nextRoot);  
        return nextRoot;
    }

    public static String getCacheKey(NodeHashlife NodeHashlife) {
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < NodeHashlife.size; i++) {
            for (int j = 0; j < NodeHashlife.size; j++) {
                key.append(NodeHashlife.state[i][j] ? "1" : "0");
            }
        }
        return key.toString();
    }

    public static boolean isCached(NodeHashlife NodeHashlife) {
        return cache.containsKey(getCacheKey(NodeHashlife));
    }
}

