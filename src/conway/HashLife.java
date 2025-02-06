package conway;

import java.util.HashMap;
import java.util.ArrayList;

public class HashLife {
    //not sure
    private static final HashMap<Node, Node> cache = new HashMap<>();

	public HashLife() {
	}

	public ArrayList<Node> createList(Node n1, Node n2, Node n3){
		ArrayList<Node> list = new ArrayList<>();
		list.add(n1);
		list.add(n2);
		list.add(n3);
		return list;
	}

    public Node getNextState(Node node) {
        // check if in cache
        if (cache.containsKey(node)) {
            return cache.get(node);
        }

        boolean nextSw = getNextCellState(node.sw, createList(node.nw, node.se, node.ne));
        boolean nextSe = getNextCellState(node.se, createList(node.ne, node.sw, node.nw));
        boolean nextNw = getNextCellState(node.nw, createList(node.sw, node.ne, node.se));
        boolean nextNe = getNextCellState(node.ne, createList(node.nw, node.se, node.sw));

        Node nextNode = new Node(
            new Node(null, null, null, null, 0, nextSw),
            new Node(null, null, null, null, 0, nextSe),
            new Node(null, null, null, null, 0, nextNw),
            new Node(null, null, null, null, 0, nextNe),
            node.level, 
            false
        );

        //cache it
        cache.put(node, nextNode);
        return nextNode;
    }

    public boolean getNextCellState(Node cell, ArrayList<Node> neighbors) {
        int aliveNeighbors = 0;

        for (Node neighbor : neighbors) {
          if (neighbor != null && neighbor.alive) {
              aliveNeighbors++;
          }
        }

        if (cell != null && cell.alive) {
          if (aliveNeighbors == 2 || aliveNeighbors == 3) {
             return true;
          } 
          else {
             return false;
          }
        }
          else {
            if (aliveNeighbors == 3) {
              return true;
          } 
          else {
            return false;
          }
        }  
    }    

}
