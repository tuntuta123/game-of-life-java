package conway.gameBasicAlgo.shapes;

public class Glider extends Shapes{
    public Glider(){
        super(new int[][]{
            {0,1,0},
            {0,0,1},
            {1,1,1},
        });
    }
}
