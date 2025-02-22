package conway;

public class Conway implements Rule {

    @Override
    public boolean applyRule(Node node) {
        int liveNeighbors = 0;

        for (Node neighbor : node.getNeighborsNode()) {
            if (neighbor != null && neighbor.isAlive()) {
                liveNeighbors++;
            }
        }

        if (node.isAlive()) {
            return liveNeighbors == 2 || liveNeighbors == 3;
        } else {
            return liveNeighbors == 3;
        }
    }
}

