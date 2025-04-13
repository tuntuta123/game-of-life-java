package conway.gameBasicAlgo.tests;

import java.lang.*;
import conway.gameBasicAlgo.logics.*;

public class GridTest{
    public GridTest(){}
    public static void runTests(){
        System.out.println("running tests - grid");
        
        Grid grid = new Grid(5);
        grid.setNode(2,2, true);

        assert grid.getNode(2,2).isAlive() : "Test for setNode(), getNode(), isAlive() failed!";
        System.out.println("setNode(), getNode(), isAlive() tests passed");
        
        grid.setNeighbors();
        Node[] neighbors = grid.getNeighbors(2,2);
        for (int i = 0 ; i<8 ; i++){
            assert neighbors[i] != null : "Neighbor test failed! atleast one neighbor missing!";
        }
        System.out.println("Neighbors tests passed");

        Node center = grid.getNode(1,1);

        assert center.getNorth() == grid.getNode(0,1) : "getNorth() failed";
        assert center.getSouth() == grid.getNode(2,1) : "getSouth() failed";
        assert center.getEast() == grid.getNode(1,2) : "getEast() failed";
        assert center.getWest() == grid.getNode(1,0) : "getWest() failed";

        assert center.getNorthEast() == grid.getNode(0,2) : "getNorthEast() failed";
        assert center.getNorthWest() == grid.getNode(0,0) : "getNorthWest() failed";
        assert center.getSouthEast() == grid.getNode(2,2) : "getSOuthEast() failed";
        assert center.getSouthWest() == grid.getNode(2,0) : "getSouthWest() failed";
        
        System.out.println("getDirection() for center node passed");

        Node corner = grid.getNode(0,0);
        assert corner.getNorth() == null : "(0,0) has a north neighbor assigned to it! Test failed.";
        System.out.println("getDirection() for corner node passed");

    }


}
