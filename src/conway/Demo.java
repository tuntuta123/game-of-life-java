package conway;

public class Demo{

	public static void main(String[] args) {
	
		Grid grid = new Grid(5);
    
	    Rule rules = new Rule(grid);
	    
	    Conway jeu = new Conway(grid);
	    
	    jeu.initialize();
		jeu.printGrid(grid);
		
		HashLife algo = new HashLife();
		
		for (int gen = 0; gen < 10; gen++) {  
			System.out.println("Generation " + gen);
			jeu.runGeneration(grid, algo);
			jeu.printGrid(grid);  
	    }
		
	    Node node1 = new Node(null, null, null, null, 0, true);
	    Node node2 = new Node(null, null, null, null, 0, false);
	    Node node3 = new Node(null, null, null, null, 0, false);
	    Node node4 = new Node(null, null, null, null, 0, false);
	    
	   node1.setNe(node2);
	   node1.setSw(node3);
	   node1.setSe(node4);
	   
	   node2.setNw(node1);
	   node2.setSw(node3);
	   node2.setSe(node4);
	   
	   node3.setNw(node1);
	   node3.setNe(node2);
	   node3.setSe(node4);
	   
	   node4.setNw(node1);
	   node4.setSw(node3);
	   node4.setNe(node2);
	   
	   Node node0 = new Node(node1, node2, node3, node4, 1, false);
	   
	   Node nodetest = algo.getNextState(node0);
	   System.out.println(nodetest.getNw().isAlive());
	   System.out.println(nodetest.getSe().isAlive());
	   System.out.println(nodetest.getSw().isAlive());
	   System.out.println(nodetest.getNe().isAlive());
	   
	   
	}
	
}



