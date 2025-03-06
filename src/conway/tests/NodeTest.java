package conway.tests;

import conway.logic.Node;


// Je dois ajout le tests pour les corners et les borders 

public class NodeTest {

    public NodeTest() {}

    public static void runTests() {
        System.out.println("\n Runing tests - Node");

// test pour le fonction isAlive()
        Node node = new Node(true);  
        assert node.isAlive() : "Node should be alive!";
        System.out.println("[ isAlive() ] passed");

// test pour le fonction setAlive
        node.setAlive(false); 
        assert !node.isAlive() : "Node should be dead after setting alive state to false!";
        System.out.println("[ setAlive() ] passed");

// tests pour les voisin d'un cellule
		  Node north = new Node(true);
        node.setNorth(north);
    	  assert node.getNorth() == north : "setNorth() or getNorth() failed! The node's north neighbor should be 'north'.";
        System.out.println("[setNorth() and getNorth()] passed");

		  Node west = new Node(false);
        node.setWest(west);
    	  assert node.getWest() == west : "setWest() or getWest() failed! The node's west neighbor should be 'west'.";
        System.out.println("[setWest() and getWest()] passed");

		  Node east = new Node(true);
        node.setEast(east);
    	  assert node.getEast() == east : "setEast() or getEast() failed! The node's east neighbor should be 'east'.";
        System.out.println("[setEast() and getEast()] passed");

	     Node south = new Node(true);
        node.setSouth(south);
    	  assert node.getSouth() == south : "setSouth() or getSouth() failed! The node's south neighbor should be 'south'.";
        System.out.println("[setSouth() and getSouth()] passed");

		  Node northEast = new Node(false);
        node.setNorthEast(northEast);
    	  assert node.getNorthEast() == northEast : "setNorthEast() or getNorthEast() failed! The node's northEast neighbor should be 'northEast'.";
        System.out.println("[setNorthEast() and getNorthEast()] passed");

		  Node northWest = new Node(true);
        node.setNorthWest(northWest);
    	  assert node.getNorthWest() == northWest : "setNorthWest() or getNorthWest() failed! The node's northWest neighbor should be 'northWest'.";
        System.out.println("[setNorthWest() and getNorthWest()] passed");

		  Node southEast = new Node(false);
        node.setSouthEast(southEast);
    	  assert node.getSouthEast() == southEast : "setSouthEast() or getSouthEast() failed! The node's southEast neighbor should be 'southEast'.";
        System.out.println("[setSouthEast() and getSouthEast()] passed");

		  Node southWest = new Node(true);
        node.setSouthWest(southWest);
    	  assert node.getSouthWest() == southWest : "setSouthWest() or getSouthWest() failed! The node's southWest neighbor should be 'southWest'.";
        System.out.println("[setSouthWest() and getSouthWest()] passed");


    }

}

