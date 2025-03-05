package conway.tests;

import conway.logic.Node;

public class NodeTest {

    public NodeTest() {}

    public static void runTests() {
        System.out.println("\n Running tests - Node");


        Node node = new Node(true);  
        assert node.isAlive() : "Node should be alive!";
        System.out.println("Node initialization with 'alive' state passed");


        node.setAlive(false); 
        assert !node.isAlive() : "Node should be dead after setting alive state to false!";
        System.out.println("setAlive() and isAlive() tests passed");


        Node northNode = new Node(false);
        Node southNode = new Node(true);
        node.setNorth(northNode);
        node.setSouth(southNode);

        assert node.getNorth() == northNode : "getNorth() failed!";
        assert node.getSouth() == southNode : "getSouth() failed!";
        System.out.println("setNorth() and setSouth() tests passed");


        Node northEastNode = new Node(true);
        Node southWestNode = new Node(false);
        node.setNorthEast(northEastNode);
        node.setSouthWest(southWestNode);

        assert node.getNorthEast() == northEastNode : "getNorthEast() failed!";
        assert node.getSouthWest() == southWestNode : "getSouthWest() failed!";
        System.out.println("Diagonal neighbors tests passed");


        Node westNode = new Node(true);
        node.setWest(westNode);
        assert node.getWest() == westNode : "getWest() failed!";
        System.out.println("getWest() test passed");


        Node cornerNode = new Node(false);
        cornerNode.setNorth(null);  
        cornerNode.setWest(null);  
        assert cornerNode.getNorth() == null : "(0,0) should not have a north neighbor!";
        assert cornerNode.getWest() == null : "(0,0) should not have a west neighbor!";
        System.out.println("Corner node neighbor test passed");


        Node center = new Node(true);
        Node north = new Node(false);
        Node south = new Node(true);
        Node east = new Node(false);
        Node west = new Node(true);
        Node northEast = new Node(false);
        Node northWest = new Node(true);
        Node southEast = new Node(false);
        Node southWest = new Node(true);

        center.setNorth(north);
        center.setSouth(south);
        center.setEast(east);
        center.setWest(west);
        center.setNorthEast(northEast);
        center.setNorthWest(northWest);
        center.setSouthEast(southEast);
        center.setSouthWest(southWest);

        assert center.getNorth() == north : "North direction test failed!";
        assert center.getSouth() == south : "South direction test failed!";
        assert center.getEast() == east : "East direction test failed!";
        assert center.getWest() == west : "West direction test failed!";
        assert center.getNorthEast() == northEast : "NorthEast direction test failed!";
        assert center.getNorthWest() == northWest : "NorthWest direction test failed!";
        assert center.getSouthEast() == southEast : "SouthEast direction test failed!";
        assert center.getSouthWest() == southWest : "SouthWest direction test failed!";

        System.out.println("All directional tests passed!");
    }

}

