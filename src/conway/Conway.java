package conway;

public class Conway{

	public Grid grid;

	public Conway (Grid g) {
		this.grid = g;
	
	}
	
	public void initialize() {
	
		this.grid.getCell(1, 1).setState(true);
    		this.grid.getCell(1, 2).setState(true);
    		this.grid.getCell(2, 1).setState(true);
    		this.grid.getCell(3, 4).setState(true);
	
	}
	
	public static void runGeneration(Grid grid, Rule rules) {
	    for (int x = 0; x < grid.getSize(); x++) {
		for (int y = 0; y < grid.getSize(); y++) {
		    rules.updateCell(grid.getCell(x, y)); 
		}
	    }
	}
	
	public static void printGrid(Grid grid) {
	
	    for (int y = 0; y < grid.getSize(); y++) {
		for (int x = 0; x < grid.getSize(); x++) {
		    System.out.print(grid.getCell(x, y).isAlive() ? "1 " : "0 ");
		}
		System.out.println();
	    }
	    System.out.println();  	
	}
	
	

} 
