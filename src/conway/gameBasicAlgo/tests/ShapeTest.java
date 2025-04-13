package conway.gameBasicAlgo.tests;

import conway.gameBasicAlgo.shapes.*;
import conway.gameBasicAlgo.logics.*;

public class ShapeTest{
	
	public ShapeTest(){}
		
	public static void runTests(){
        System.out.println("\n Running tests - shapes");
        Shapes glider = new Glider();
        Grid grid = new Grid(3);
        glider.applyShape(grid, 0, 0);
        Grid grid2 = new Grid(3);
        for(int i=0;i<3;i++){
        	for(int j=0;j<3;j++){
        		grid2.setNode(i, j, false);
        	}
        }
        grid2.setNode(0, 1, true);
        grid2.setNode(1, 2, true);
        grid2.setNode(2, 0, true);
        grid2.setNode(2, 1, true);
        grid2.setNode(2, 2, true);
        
        for(int i=0;i<3;i++){
        	for(int j=0;j<3;j++){
        			assert (grid.getNode(i,j) != grid2.getNode(i,j)) : "Test for applyShape(Grid grid, int x, int y) failed!";
        	}
        }
        System.out.println("applyShape(Grid grid, int x, int y) tests passed");
    }
	
}
