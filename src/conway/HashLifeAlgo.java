package conway;
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

    public void generate() {
        Grid newGrid = new Grid(grid.getWidth(), grid.getHeight());
        grid.setNeighbors(); 
        
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
            
                Node current = grid.getNode(x, y);
                int key = this.getKey(x, y);  
                Boolean stored = cache.get(key);

                boolean state;
                if (stored != null) {
                    state = stored;
                } else {
                    state = rule.applyRule(current);
                    cache.put(key, state);
                }

                newGrid.setNode(x, y, state);
            }
        }

        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                grid.setNode(x, y, newGrid.getNode(x, y).isAlive());
            }
        }

        grid.setNeighbors();  
    }

	private int getKey(int x, int y) {
		Node node = grid.getNode(x, y);
		int key = node.hashCode();  

		for (Node neighbor : node.getNeighborsNode()) {
			if (neighbor != null) {
				key = 17 * key + neighbor.hashCode();
			} else {
				key = 17 * key + 0;  
			}
		}
		return key;  
	}

}

