package conway.gameHashlifeAlgo.logics;

import java.util.Arrays;
import java.util.Objects;

public class NodeHashlife implements Comparable<NodeHashlife> {

    public boolean[][] state;
    public int size;
    public NodeHashlife ne, nw, sw, se;

    public NodeHashlife(int size, boolean[][] state) {
        this.size = size;
        this.state = state;
        this.ne = this.nw = this.sw = this.se = null;
    }

    public NodeHashlife(int size, NodeHashlife ne, NodeHashlife nw, NodeHashlife sw, NodeHashlife se) {
        this.size = size;
        this.ne = ne;
        this.nw = nw;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeHashlife that = (NodeHashlife) o;
        return size == that.size && Arrays.deepEquals(state, that.state);
    }
    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.deepHashCode(state); 
        return result;
    }

    @Override
    public int compareTo(NodeHashlife other) {
        return Integer.compare(this.size, other.size);
    }

    public static NodeHashlife split(NodeHashlife root) {
        if (root.size == 1) return root;  

        int halfSize = root.size / 2;
        boolean[][] topLeft = new boolean[halfSize][halfSize];
        boolean[][] topRight = new boolean[halfSize][halfSize];
        boolean[][] bottomLeft = new boolean[halfSize][halfSize];
        boolean[][] bottomRight = new boolean[halfSize][halfSize];

        for (int i = 0; i < halfSize; i++) {
            for (int j = 0; j < halfSize; j++) {
                topLeft[i][j] = root.state[i][j];
                topRight[i][j] = root.state[i][halfSize + j];
                bottomLeft[i][j] = root.state[halfSize + i][j];
                bottomRight[i][j] = root.state[halfSize + i][halfSize + j];
            }
        }

        NodeHashlife ne = new NodeHashlife(halfSize, topRight);
        NodeHashlife nw = new NodeHashlife(halfSize, topLeft);
        NodeHashlife sw = new NodeHashlife(halfSize, bottomLeft);
        NodeHashlife se = new NodeHashlife(halfSize, bottomRight);

        return new NodeHashlife(root.size, ne, nw, sw, se);
    }

    public static NodeHashlife applyConwayRule(NodeHashlife leaf) {
        int size = leaf.size;
        boolean[][] nextState = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int liveNeighbors = countLiveNeighbors(leaf, i, j);
                if (leaf.state[i][j]) {
                    nextState[i][j] = liveNeighbors == 2 || liveNeighbors == 3;
                } else {
                    nextState[i][j] = liveNeighbors == 3;
                }
            }
        }

        return new NodeHashlife(size, nextState);
    }

    private static int countLiveNeighbors(NodeHashlife leaf, int x, int y) {
        int[][] neighbors = {
            {-1, -1}, {-1, 0}, {-1, 1},
            { 0, -1},           { 0, 1},
            { 1, -1}, { 1, 0}, { 1, 1}
        };
        int count = 0;
        for (int[] neighbor : neighbors) {
            int nx = x + neighbor[0];
            int ny = y + neighbor[1];
            if (nx >= 0 && nx < leaf.size && ny >= 0 && ny < leaf.size && leaf.state[nx][ny]) {
                count++;
            }
        }
        return count;
    }

    public static NodeHashlife combine(NodeHashlife ne, NodeHashlife nw, NodeHashlife sw, NodeHashlife se) {
        int size = ne.size * 2;
        boolean[][] combinedState = new boolean[size][size];

        if (ne.isLeaf() && nw.isLeaf() && sw.isLeaf() && se.isLeaf()) {
            for (int i = 0; i < ne.size; i++) {
                for (int j = 0; j < ne.size; j++) {
                    combinedState[i][j] = nw.state[i][j];
                    combinedState[i][ne.size + j] = ne.state[i][j];
                    combinedState[ne.size + i][j] = sw.state[i][j];
                    combinedState[ne.size + i][ne.size + j] = se.state[i][j];
                }
            }
        } else {
            for (int i = 0; i < ne.size; i++) {
                for (int j = 0; j < ne.size; j++) {
                    combinedState[i][j] = nw.state[i][j];
                    combinedState[i][ne.size + j] = ne.state[i][j];
                    combinedState[ne.size + i][j] = sw.state[i][j];
                    combinedState[ne.size + i][ne.size + j] = se.state[i][j];
                }
            }
        }

        return new NodeHashlife(size, ne, nw, sw, se);
    }
}

