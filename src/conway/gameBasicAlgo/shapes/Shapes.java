package conway.gameBasicAlgo.shapes;

import conway.gameBasicAlgo.logics.*;
import conway.gameBasicAlgo.iterator.*;
import java.util.Iterator;

public abstract class Shapes {
    public int[][] figure;

    public Shapes(int[][] figure) {
        this.figure = figure;
    }

    public void applyShape(Grid grid, int x, int y) {
        Iterator<int[]> iterator = new ArrayIterator(figure);
        
        while (iterator.hasNext()) {
            int[] row = iterator.next();
            for (int j = 0; j < row.length; j++) {
                if (row[j] == 1) {
                    grid.setNode(x, y, true);
                }
            }
        }
    }
}
