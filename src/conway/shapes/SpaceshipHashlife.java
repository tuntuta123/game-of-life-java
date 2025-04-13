package conway.shapes;

public class SpaceshipHashlife extends ShapesHashlife {
    public SpaceshipHashlife() {
        super(new int[][]{
            {0,1,1,0,0,1,1,0},
            {0,0,0,1,1,0,0,0},
            {0,0,0,1,1,0,0,0},
            {1,0,1,0,0,1,0,1},
            {1,0,0,0,0,0,0,1},
            {0,0,0,0,0,0,0,0},
            {1,0,0,0,0,0,0,1},
            {0,1,1,0,0,1,1,0},
            {0,0,1,1,1,1,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,1,1,0,0,0},
            {0,0,0,1,1,0,0,0},
        });
    }
}
