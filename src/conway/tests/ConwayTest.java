package conway.tests;

import java.lang.*;
import conway.logic.*;

public class ConwayTest{

    public ConwayTest(){}
    
    public static void runTests(){
        System.out.println("\n Running tests - conway");
        
        Conway conway = new Conway();

	Grid grid = new Grid(10);
        grid.setNeighbors();
	Node center = grid.getNode(5,5);
	center.setAlive(true);	
	
	Node nw = center.getNorthWest();
 	nw.setAlive(false);
        Node ne = center.getNorthEast();
 	ne.setAlive(true);
 	Node n = center.getNorth();
 	n.setAlive(true);
 	Node w = center.getWest();
 	w.setAlive(true);
 	Node s = center.getSouth();
 	s.setAlive(true);
 	Node e = center.getEast();
 	e.setAlive(false);
 	Node sw = center.getSouthWest();
 	sw.setAlive(false);
 	Node se = center.getSouthEast();
 	se.setAlive(false);
        
        grid.setNeighbors();
        
        assert !conway.applyRule(center) : "Test for applyRule() failed!";
   	assert conway.applyRule(center.getNorthWest()) : "Test for applyRule() failed!";
   	assert conway.applyRule(center.getNorthEast()) : "Test for applyRule() failed!";
   	assert conway.applyRule(center.getSouthWest()) : "Test for applyRule() failed!";
   	assert !conway.applyRule(center.getSouthEast()) : "Test for applyRule() failed!";
   	assert conway.applyRule(center.getNorth()) : "Test for applyRule() failed!";
   	assert conway.applyRule(center.getSouth()) : "Test for applyRule() failed!";
   	assert conway.applyRule(center.getWest()) : "Test for applyRule() failed!";
   	assert !conway.applyRule(center.getEast()) : "Test for applyRule() failed!";
   	
        System.out.println("applyRule() tests passed");

    }


}
