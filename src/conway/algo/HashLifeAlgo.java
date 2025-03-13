package conway.algo;

import java.util.HashMap;
import conway.logic.Node;
import conway.logic.Grid;

public class HashLifeAlgo {
    private static final HashMap<Node, Node> cache = new HashMap<>();

    public Node getNextState(Node node) {
        if (cache.containsKey(node)) {
            //System.out.println("containsKey");
            return cache.get(node);
        }

        if (node.isLeaf()) {
            //System.out.println("node is leaf");
            return evolve(node);
        }

        //System.out.println("getnextstate");
        Node nextNW = getNextState(node.nw);
        Node nextNE = getNextState(node.ne);
        Node nextSW = getNextState(node.sw);
        Node nextSE = getNextState(node.se);

        /*Node middle = getNextState(Node.create(
            node.nw.se, node.ne.sw,
            node.sw.ne, node.se.nw
        ));*/
        
        if (nextNW == null || nextNE == null || nextSW == null || nextSE == null) {
        	System.out.println("one of the next state is null");
    	}

        Node nextNode = Node.create(nextNW, nextNE, nextSW, nextSE);

        cache.put(node, nextNode);
        return nextNode;
    }

    private Node evolve(Node node) {
        boolean nextNW = getNextCellState(node.nw, new Node[]{node.ne, node.sw, node.se});
        boolean nextNE = getNextCellState(node.ne, new Node[]{node.nw, node.se, node.sw});
        boolean nextSW = getNextCellState(node.sw, new Node[]{node.nw, node.se, node.ne});
        boolean nextSE = getNextCellState(node.se, new Node[]{node.ne, node.sw, node.nw});

        return Node.create(new Node(nextNW), new Node(nextNE),
                           new Node(nextSW), new Node(nextSE));
    }

    private boolean getNextCellState(Node cell, Node[] neighbors) {
        int aliveNeighbors = 0;

        for (Node neighbor : neighbors) {
            if (neighbor != null && neighbor.alive) {
                aliveNeighbors++;
            }
        }

        if (cell != null && cell.alive) {
            return (aliveNeighbors == 2 || aliveNeighbors == 3);
        } else {
            return aliveNeighbors == 3;
        }
    }

    public void generate(Grid grid) {
        Node root = grid.toQuadtree();
        Node nextRoot;

	if (root == null) {
        	System.out.println("error generate");
        }
        if (cache.containsKey(root)) {
            nextRoot = cache.get(root);
        } else {
            nextRoot = getNextState(root);
            cache.put(root, nextRoot);
        }
        
        if (nextRoot == null) {
        	System.out.println("error generate .");
        }

        grid.fromQuadtree(nextRoot);
    }

    
}
