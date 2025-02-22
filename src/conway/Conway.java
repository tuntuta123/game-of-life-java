package conway;

public class Conway implements Rule {

    @Override
    public boolean applyRule(Node node) {
        int liveNeighbors = 0;

        if (node.getNorthEast() != null && node.getNorthEast().isAlive()) 
        	liveNeighbors++;
        if (node.getNorth() != null && node.getNorth().isAlive()) 
        	liveNeighbors++;
        if (node.getNorthWest() != null && node.getNorthWest().isAlive()) 
        	liveNeighbors++;
        if (node.getWest() != null && node.getWest().isAlive()) 
        	liveNeighbors++;
        if (node.getEast() != null && node.getEast().isAlive()) 
        	liveNeighbors++;
        if (node.getSouthEast() != null && node.getSouthEast().isAlive()) 
        	liveNeighbors++;
        if (node.getSouth() != null && node.getSouth().isAlive()) 
        	liveNeighbors++;
        if (node.getSouthWest() != null && node.getSouthWest().isAlive()) 
        	liveNeighbors++;

        if (node.isAlive()) {
            return liveNeighbors == 2 || liveNeighbors == 3;
        } else {
            return liveNeighbors == 3;
        }
    }
}

