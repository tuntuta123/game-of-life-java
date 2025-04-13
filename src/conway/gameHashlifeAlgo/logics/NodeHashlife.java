package conway.gameHashlifeAlgo.logics;

import java.util.HashMap;
import java.util.Map;

public class NodeHashlife {

    public boolean[][] state;
    public int size;
    public int level;
    public NodeHashlife ne, nw, sw, se;
    public NodeHashlife result;

    private static final Map<String, NodeHashlife> memo = new HashMap<>();

    public NodeHashlife(int size, boolean[][] state) {
        this.size = size;
        this.level = (int) (Math.log(size) / Math.log(2));
        this.state = state;
        this.ne = this.nw = this.sw = this.se = null;
    }

    public NodeHashlife(int level, NodeHashlife nw, NodeHashlife ne, NodeHashlife sw, NodeHashlife se) {
        this.level = level;
        this.size = nw.size * 2;
        this.nw = nw;
        this.ne = ne;
        this.sw = sw;
        this.se = se;
        this.state = null;
    }

    public boolean[][] getState() {
        return this.state;
    }

    public void setState(boolean[][] state) {
        this.state = state;
    }

    public boolean isLeaf() {
        return ne == null && nw == null && sw == null && se == null;
    }

    public NodeHashlife next() {
        if (this.result != null) return this.result;

        if (this.level == 0) {
            this.result = this;
            return result;
        }

        if (this.level == 1) {
            this.result = simulateBaseCase();
            return result;
        }

        NodeHashlife n00 = nw.center();
        NodeHashlife n01 = centerHorizontal(nw, ne);
        NodeHashlife n02 = ne.center();

        NodeHashlife n10 = centerVertical(nw, sw);
        NodeHashlife n11 = center();
        NodeHashlife n12 = centerVertical(ne, se);

        NodeHashlife n20 = sw.center();
        NodeHashlife n21 = centerHorizontal(sw, se);
        NodeHashlife n22 = se.center();

        NodeHashlife nwNext = createNode(n00, n01, n10, n11).next();
        NodeHashlife neNext = createNode(n01, n02, n11, n12).next();
        NodeHashlife swNext = createNode(n10, n11, n20, n21).next();
        NodeHashlife seNext = createNode(n11, n12, n21, n22).next();

        this.result = createNode(nwNext, neNext, swNext, seNext);
        return result;
    }

    private NodeHashlife simulateBaseCase() {
        boolean[][] next = new boolean[2][2];
        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 2; x++) {
                int gx = x + 1;
                int gy = y + 1;
                int count = 0;
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dx = -1; dx <= 1; dx++) {
                        if (dx == 0 && dy == 0) continue;
                        if (gy + dy >= 0 && gy + dy < 4 && gx + dx >= 0 && gx + dx < 4) {
                            if (state[gy + dy][gx + dx]) count++;
                        }
                    }
                }
                next[y][x] = state[gy][gx] ? (count == 2 || count == 3) : (count == 3);
            }
        }
        return new NodeHashlife(2, next);
    }

    public static NodeHashlife createNode(NodeHashlife nw, NodeHashlife ne, NodeHashlife sw, NodeHashlife se) {
        String key = nw.hashCode() + "," + ne.hashCode() + "," + sw.hashCode() + "," + se.hashCode();
        if (memo.containsKey(key)) return memo.get(key);
        NodeHashlife node = new NodeHashlife(nw.level + 1, nw, ne, sw, se);
        memo.put(key, node);
        return node;
    }

    public NodeHashlife center() {
        return createNode(nw.se, ne.sw, sw.ne, se.nw);
    }

    public static NodeHashlife centerHorizontal(NodeHashlife left, NodeHashlife right) {
        return createNode(left.ne.se, right.nw.sw, left.se.ne, right.sw.nw);
    }

    public static NodeHashlife centerVertical(NodeHashlife top, NodeHashlife bottom) {
        return createNode(top.sw.se, top.se.sw, bottom.nw.ne, bottom.ne.nw);
    }
    public static NodeHashlife splitToRecursive(NodeHashlife node) {
        if (node.level <= 1) return node;

        int half = node.size / 2;
        boolean[][] topLeft = new boolean[half][half];
        boolean[][] topRight = new boolean[half][half];
        boolean[][] bottomLeft = new boolean[half][half];
        boolean[][] bottomRight = new boolean[half][half];

        for (int i = 0; i < half; i++) {
            for (int j = 0; j < half; j++) {
                topLeft[i][j] = node.state[i][j];
                topRight[i][j] = node.state[i][j + half];
                bottomLeft[i][j] = node.state[i + half][j];
                bottomRight[i][j] = node.state[i + half][j + half];
            }
        }

        NodeHashlife nw = splitToRecursive(new NodeHashlife(half, topLeft));
        NodeHashlife ne = splitToRecursive(new NodeHashlife(half, topRight));
        NodeHashlife sw = splitToRecursive(new NodeHashlife(half, bottomLeft));
        NodeHashlife se = splitToRecursive(new NodeHashlife(half, bottomRight));

        return NodeHashlife.createNode(nw, ne, sw, se);
    }
}
