package conway
import java.util.HashMap;

public class HashLifeAlgo {
    private Grid grid;
    private Rule rule;
    private HashMap<Integer, Boolean> cache; 

    public HashLifeAlgo(Grid grid, Rule rule) {
        this.grid = grid;
        this.rule = rule;
        this.cache = new HashMap<>();
    }

    public void nextGeneration() {
        Grid newGrid = new Grid(grid.getWidth(), grid.getHeight());

        grid.setNeighbors();
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                Node currentNode = grid.getNode(x, y);
                int nodeKey = getNodeHashCode(x, y); 
                Boolean cachedState = cache.get(nodeKey);

                boolean newState;
                if (cachedState != null) {
                    newState = cachedState;
                } else {
                    newState = rule.applyRule(currentNode);
                    cache.put(nodeKey, newState);
                }

                newGrid.setNode(x, y, newState);
            }
        }

        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                grid.setNode(x, y, newGrid.getNode(x, y).isAlive());
            }
        }

        grid.setNeighbors();  
    }

private int getNodeHashCode(int x, int y) {
    Node node = grid.getNode(x, y);

    int hashCode = 17 * (node.isAlive() ? 1 : 0); 

    if (node.getNorth() != null) {
        if (node.getNorth().isAlive()) {
            hashCode = 17 * hashCode + 1;
        } else {
            hashCode = 17 * hashCode + 0;
        }
    } else {
        hashCode = 17 * hashCode + 0;
    }

    if (node.getSouth() != null) {
        if (node.getSouth().isAlive()) {
            hashCode = 17 * hashCode + 1;
        } else {
            hashCode = 17 * hashCode + 0;
        }
    } else {
        hashCode = 17 * hashCode + 0;
    }

    if (node.getEast() != null) {
        if (node.getEast().isAlive()) {
            hashCode = 17 * hashCode + 1;
        } else {
            hashCode = 17 * hashCode + 0;
        }
    } else {
        hashCode = 17 * hashCode + 0;
    }

    if (node.getWest() != null) {
        if (node.getWest().isAlive()) {
            hashCode = 17 * hashCode + 1;
        } else {
            hashCode = 17 * hashCode + 0;
        }
    } else {
        hashCode = 17 * hashCode + 0;
    }

    if (node.getNorthEast() != null) {
        if (node.getNorthEast().isAlive()) {
            hashCode = 17 * hashCode + 1;
        } else {
            hashCode = 17 * hashCode + 0;
        }
    } else {
        hashCode = 17 * hashCode + 0;
    }

    if (node.getNorthWest() != null) {
        if (node.getNorthWest().isAlive()) {
            hashCode = 17 * hashCode + 1;
        } else {
            hashCode = 17 * hashCode + 0;
        }
    } else {
        hashCode = 17 * hashCode + 0;
    }

    if (node.getSouthEast() != null) {
        if (node.getSouthEast().isAlive()) {
            hashCode = 17 * hashCode + 1;
        } else {
            hashCode = 17 * hashCode + 0;
        }
    } else {
        hashCode = 17 * hashCode + 0;
    }

    if (node.getSouthWest() != null) {
        if (node.getSouthWest().isAlive()) {
            hashCode = 17 * hashCode + 1;
        } else {
            hashCode = 17 * hashCode + 0;
        }
    } else {
        hashCode = 17 * hashCode + 0;
    }

    return hashCode;  
}

}

