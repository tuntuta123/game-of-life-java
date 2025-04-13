package conway.shapes;

public class ButterflyHashlife extends ShapesHashlife {
    public ButterflyHashlife() {
        super(new int[][]{
            {0, 1, 1, 0},
            {1, 0, 0, 1},
            {0, 1, 1, 0},
            {1, 0, 0, 1}
        });
    }
}

