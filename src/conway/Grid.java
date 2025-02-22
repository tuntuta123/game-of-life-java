package conway;

public class Grid {
    private Node[][] grid;
    private int width;
    private int height;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Node[width][height];
        initializeGrid();
    }

    private void initializeGrid() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.grid[x][y] = new Node(false); 
            }
        }
    }

    public Node getNode(int x, int y) {
        return grid[x][y]; 
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
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Node node = this.grid[x][y];
                Node[] neighbors = getNeighbors(x, y);

                node.setNorthEast(neighbors[0]);
                node.setNorth(neighbors[1]);
                node.setNorthWest(neighbors[2]);
                node.setWest(neighbors[3]);
                node.setEast(neighbors[4]);
                node.setSouthEast(neighbors[5]);
                node.setSouth(neighbors[6]);
                node.setSouthWest(neighbors[7]);
            }
        }
    }

    private Node[] getNeighbors(int x, int y) {
        Node[] neighbors = new Node[8];
        int index = 0;

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) 
                    continue; 

                int nx = x + dx;
                int ny = y + dy;

                if (nx >= 0 && nx < this.width && ny >= 0 && ny < this.height) {
                    neighbors[index++] = this.grid[nx][ny];
                } else {
                    neighbors[index++] = null; 
                }
            }
        }

        return neighbors;
    }
}

