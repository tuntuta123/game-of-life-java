package conway;

public class Rule{

	
	public Grid grid;

	public Rule(Grid g) {
		this.grid = g;
	}
	
	public boolean calculateState(Cell c){
	int nb = grid.countAliveNeighbours(c);
		if (c.isAlive()){
			if ((nb < 2) || (nb > 3)) {
				return false;
			}		
		}
		
		else {
			if (nb == 3) {
				return true;
			}
		}
		return c.isAlive();
	}

	
	public void updateState() {
		for(int i=0;i<this.grid.getSize();i++){
			for(int j = 0; j < this.grid.getSize(); j++){
				this.calculateState(this.grid.getCell(i,j));
			}
		}
	}
	
	

	
}
