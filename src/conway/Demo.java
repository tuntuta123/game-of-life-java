package conway;

public class Demo{

	public static void main(String[] args) {
	
		Grid grid = new Grid(5);
    
	    Rule rules = new Rule(grid);
	    
	    
	    Conway jeu = new Conway(grid);
	    jeu.initialize();
	jeu.printGrid(grid);
	    for (int gen = 0; gen < 10; gen++) {  
		System.out.println("Generation " + gen);
		jeu.runGeneration(grid, rules);
		jeu.printGrid(grid);  
	    }
	}

}



