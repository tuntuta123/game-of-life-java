package conway;

public class Rule{

	
	public Grid grid;

	public Rule(Grid g) {
		this.grid = g;
	}
	
	public void updateCell(Cell c) {
		int nb = grid.countAliveNeighbours(c);
		if (c.isAlive()){
			if ((nb < 2) || (nb > 3)) {
				c.setState(false);
			}		
		}
		
		else {
			if (nb == 3) {
				c.setState(true);
			}
		}	
	}

	
}
