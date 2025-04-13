package conway.shapes;

import conway.logic.GridHashlife;
import conway.logic.NodeHashlife;

public class ShapesHashlife {

    private int[][] figure;  

    public ShapesHashlife(int[][] figure) {
        this.figure = figure;
    }

    public void applyShape(GridHashlife gridHashlife, int x, int y) {
		NodeHashlife root = gridHashlife.getRoot();
		for (int i = 0; i < figure.length; i++) {
		    for (int j = 0; j < figure[i].length; j++) {
		        if (figure[i][j] == 1) {
		            int row = y + i;
		            int col = x + j;
		            if (row >= 0 && row < root.state.length && col >= 0 && col < root.state[0].length) {
		                root.state[row][col] = true;
		            }
		        }
		    }
		}
	}

}

