package conway.shapes;
import conway.logic.*;

public abstract class Shapes{

    public int[][] figure;

    public Shapes(int[][] figure){
        this.figure = figure;
    }

    public void applyShape(Grid grid, int x, int y){
        for (int i = 0 ; i < figure.length ; i++){
            for (int j = 0 ; j < figure[i].length ; j++)
                if(figure[i][j] == 1){
                    grid.setNode(x+i,y+j,true);
                }
        }
    }

}