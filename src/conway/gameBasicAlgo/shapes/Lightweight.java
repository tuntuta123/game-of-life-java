package conway.gameBasicAlgo.shapes;

public class Lightweight extends Shapes{
    public Lightweight(){
        super(new int[][]{
            {1, 0, 0, 0, 1, 0, 0},
            {0, 1, 0, 0, 0, 1, 0},
            {0, 0, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 1, 0, 0, 0}
        });
    }
}
