package conway.gameBasicAlgo.shapes;

public class Pulsar extends Shapes{
    public Pulsar(){
        super(new int[][]{
            {0, 0, 1, 1, 1, 0, 0},
            {0, 1, 0, 0, 0, 1, 0},
            {1, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 1},
            {0, 1, 0, 0, 0, 1, 0},
            {0, 0, 1, 1, 1, 0, 0}
        });
    }
}
