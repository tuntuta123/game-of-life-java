package conway;

public class Grid {

	public Cell[][] cells;
	public int size;

	public Grid(int s) {
		this.size = s;
		this.cells = new Cell[s][s];
		for(int i = 0; i < s; i ++) {
			for (int j = 0; j < s; j++) {
				this.cells[i][j] = new Cell(false, i, j);
			}
		}
	}
	
	public int getSize() {
		return this.size;
	}

	public Cell getCell(int x, int y) {
		return this.cells[x][y];
	}
	
	public void setCell(int x, int y, Cell cell) {
		this.cells[x][y] = cell;
	}
	
	public int countAliveNeighbours(Cell c){
		int aliveCount = 0;
		for (int dx = -1; dx <= 1; dx++) {
		    for (int dy = -1; dy <= 1; dy++) {
		        if (dx == 0 && dy == 0) {
		            continue;
		        }

		        int neighborX = c.getX() + dx;
		        int neighborY = c.getY() + dy;

		        if (neighborX >= 0 && neighborX < this.size && neighborY >= 0 && neighborY < this.size) {
		            Cell neighbor = cells[neighborX][neighborY];
		            if (neighbor.isAlive()) {
		                aliveCount++;
		            }
		        }
		    }
		}

		return aliveCount;
	}
	
}
