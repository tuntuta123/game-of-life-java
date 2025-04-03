package conway.tests;

import conway.logic.Node;

public class NodeTest {

    public NodeTest() {}

    public void runTests() {
        System.out.println("\n Runing tests - Node");
        testAliveState();
        testNeighbors();
        testCorners();
        testBorders();
        System.out.println("Tout tests por le Node sont passé \n");
    }

// test pour le fonction isAlive() et setAlive()
    public void testAliveState() {
        Node node = new Node(true);  
        assert node.isAlive() : "Node doit etre vivant!";
        System.out.println("[ isAlive() ] est passé");

        node.setAlive(false); 
        assert !node.isAlive() : "Node doit être mort après avoir défini l'état vivant sur false !";
        System.out.println("[ setAlive() ] est passé");
}

// tests pour les voisin d'un cellule
    public void testNeighbors() {
        System.out.println("\n Testing Neighbors");
        Node node = new Node(false);

	Node north = new Node(true);
        node.setNorth(north);
    	assert node.getNorth() == north : "setNorth() ou getNorth() échoué! Le voisin nord du noeud doit être 'nord'.";

	Node west = new Node(false);
        node.setWest(west);
    	assert node.getWest() == west : "setWest() ou getWest() échoué! Le voisin ouest du noeud doit être 'ouest'.";

	Node east = new Node(true);
        node.setEast(east);
    	assert node.getEast() == east : "setEast() or getEast() échoué! Le voisin ouest du noeud doit être 'ouest'.";

	Node south = new Node(true);
        node.setSouth(south);
    	assert node.getSouth() == south : "setSouth() ou getSouth() échoué! Le voisin sud du noeud doit être 'sud'.";

	Node northEast = new Node(false);
        node.setNorthEast(northEast);
    	assert node.getNorthEast() == northEast : "setNorthEast() ou getNorthEast() échoué! Le voisin nord-est du noeud doit être 'nord-est'.";

	Node northWest = new Node(true);
        node.setNorthWest(northWest);
    	assert node.getNorthWest() == northWest : "setNorthWest() ou getNorthWest() échoué! Le voisin nord-ouest du noeud doit être 'nord-ouest'.";

	Node southEast = new Node(false);
        node.setSouthEast(southEast);
    	assert node.getSouthEast() == southEast : "setSouthEast() ou getSouthEast() échoué! Le voisin sud-est du noeud doit être 'sud-est'.";

	Node southWest = new Node(true);
        node.setSouthWest(southWest);
    	assert node.getSouthWest() == southWest : "setSouthWest() ou getSouthWest() échoué! Le voisin sud-ouest du noeud doit être 'sud-ouest'.";

        System.out.println("[ Tout 8 voisins tests sont passé ]");
    }

// tests pour les 4 corners
    public void testCorners() {
        System.out.println("\n Testing Corners");

        Node topLeft = new Node(false);
        topLeft.setEast(new Node(true));
        topLeft.setSouth(new Node(false));
        topLeft.setSouthEast(new Node(true));
        assert topLeft.getNorth() == null;
        assert topLeft.getNorthWest() == null;
        assert topLeft.getWest() == null;
        assert topLeft.getSouthWest() == null;
        assert topLeft.getNorthEast() == null;
        System.out.println("[ Top-left corner ] est passé");

        Node topRight = new Node(false);
        topRight.setWest(new Node(true));
        topRight.setSouth(new Node(false));
        topRight.setSouthWest(new Node(true));
        assert topRight.getNorth() == null;
        assert topRight.getNorthEast() == null;
        assert topRight.getEast() == null;
        assert topRight.getSouthEast() == null;
        assert topRight.getNorthWest() == null;
        System.out.println("[ Top-right corner ] est passé");

        Node bottomLeft = new Node(false);
        bottomLeft.setNorth(new Node(true));
        bottomLeft.setEast(new Node(false));
        bottomLeft.setNorthEast(new Node(true));
        assert bottomLeft.getSouth() == null;
        assert bottomLeft.getSouthEast() == null;
        assert bottomLeft.getSouthWest() == null;
        assert bottomLeft.getWest() == null;
        assert bottomLeft.getNorthWest() == null;
        System.out.println("[ Bottom-left corner ] est passé");

        Node bottomRight = new Node(false);
        bottomRight.setNorth(new Node(true));
        bottomRight.setWest(new Node(false));
        bottomRight.setNorthWest(new Node(true));
        assert bottomRight.getSouth() == null;
        assert bottomRight.getSouthEast() == null;
        assert bottomRight.getSouthWest() == null;
        assert bottomRight.getEast() == null;
        assert bottomRight.getNorthEast() == null;
        System.out.println("[ Bottom-right corner ] est passé");
    }

// tests pour le borders 
    public void testBorders() {
        System.out.println("\n Test pour les Borders");

        Node topBorder = new Node(false);
        topBorder.setWest(new Node(true));
        topBorder.setEast(new Node(true));
        topBorder.setSouth(new Node(true));
        topBorder.setSouthEast(new Node(false));
        topBorder.setSouthWest(new Node(true));
        assert topBorder.getNorth() == null;
        assert topBorder.getNorthEast() == null;
        assert topBorder.getNorthWest() == null;
        System.out.println("[ Top border ] est passé");

        Node bottomBorder = new Node(false);
        bottomBorder.setWest(new Node(true));
        bottomBorder.setEast(new Node(false));
        bottomBorder.setNorth(new Node(true));
        bottomBorder.setNorthEast(new Node(true));
        bottomBorder.setNorthWest(new Node(false));
        assert bottomBorder.getSouth() == null;
        assert bottomBorder.getSouthEast() == null;
        assert bottomBorder.getSouthWest() == null;
        System.out.println("[ Bottom border ] est passé");

        Node leftBorder = new Node(false);
        leftBorder.setNorth(new Node(true));
        leftBorder.setSouth(new Node(false));
        leftBorder.setEast(new Node(true));
        leftBorder.setNorthEast(new Node(false));
        leftBorder.setSouthEast(new Node(true));
        assert leftBorder.getWest() == null;
        assert leftBorder.getNorthWest() == null;
        assert leftBorder.getSouthWest() == null;
        System.out.println("[ Left border ] est passé");

        Node rightBorder = new Node(false);
        rightBorder.setNorth(new Node(true));
        rightBorder.setSouth(new Node(false));
        rightBorder.setWest(new Node(true));
        rightBorder.setNorthWest(new Node(true));
        rightBorder.setSouthWest(new Node(false));
        assert rightBorder.getEast() == null;
        assert rightBorder.getNorthEast() == null;
        assert rightBorder.getSouthEast() == null;
        System.out.println("[ Right border ] est passé");
    }
}

