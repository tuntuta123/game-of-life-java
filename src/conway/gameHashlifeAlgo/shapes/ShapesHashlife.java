package conway.gameHashlifeAlgo.shapes;

import conway.gameHashlifeAlgo.logics.*;
import conway.gameHashlifeAlgo.iterator.ShapeIterator;

public class ShapesHashlife {

    private int[][] figure;  

    public ShapesHashlife(int[][] figure) {
        this.figure = figure;
    }

    public void applyShape(GridHashlife gridHashlife, int x, int y) {
        NodeHashlife root = gridHashlife.getRoot();
        
        ShapeIterator iterator = new ShapeIterator(figure);
        while (iterator.hasNext()) {
            int[] cell = iterator.next();
            int i = cell[0];
            int j = cell[1];

            int row = y + i;
            int col = x + j;

            if (row >= 0 && row < root.state.length && col >= 0 && col < root.state[0].length) {
                root.state[row][col] = true;
            }
        }
    }
}

