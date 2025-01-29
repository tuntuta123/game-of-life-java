package conway;

public class Grid{
	
	public int rows;
	public int columns;
	public String[][] grid;
	
	public Grid(int rows, int columns){
		this.rows = rows;
		this.columns = columns;
		this.grid = new String[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.grid[i][j] = ".";
            }
        }
	}
	
	
	
}
