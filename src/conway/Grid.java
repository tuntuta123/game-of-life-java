package conway;

public class Grid {
    private Node[][] grid;
    private int width;
    private int height;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Node[width][height];
        this.initializeGrid();
    }

    private void initializeGrid() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.grid[x][y] = new Node(false); 
            }
        }
    }

    public Node getNode(int x, int y) {
        return this.grid[x][y]; 
    }

    public void setNode(int x, int y, boolean alive) {
        this.grid[x][y].setAlive(alive); 
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setNeighbors() {
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                Node node = this.grid[x][y];
                node.setNeighborsNode(this.getNeighbors(x, y));
            }
        }
    }

    private Node[] getNeighbors(int x, int y) {
        Node[] neighbors = new Node[8];
        int index = 0;

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx != 0 || dy != 0) {

		            int nx = x + dx;
		            int ny = y + dy;

		            if (nx >= 0 && nx < this.width && ny >= 0 && ny < this.height) {
		                neighbors[index++] = this.grid[nx][ny];
		            } else {
		                neighbors[index++] = null; 
		            }
		        }
            }
        }

        return neighbors;
    }
}

