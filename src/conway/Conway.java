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
    	this.grid.getCell(4, 4).setState(true);
    	this.grid.getCell(4, 3).setState(true);
	}
	
	public Grid copy(){
		Grid gCopy = new Grid(this.grid.getSize());
		for(int i=0;i<this.grid.getSize();i++) {
			for(int j=0;j<this.grid.getSize();j++) {
				gCopy.setCell(i,j,this.grid.getCell(i,j));
			}
		}
		return gCopy;	
	}
	
	public void runGeneration(Grid grid, HashLife rules) {
		Grid tmp = new Grid(this.grid.getSize());
		Rule r = new Rule(this.grid);
		for(int i=0;i<this.grid.getSize();i++) {
			for(int j=0;j<this.grid.getSize();j++) {
				tmp.getCell(i,j).setState(r.calculateState(this.grid.getCell(i,j)));
			}
		}
		for(int i=0;i<this.grid.getSize();i++) {
			for(int j=0;j<this.grid.getSize();j++) {
				this.grid.getCell(i,j).setState(tmp.getCell(i,j).isAlive());
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
