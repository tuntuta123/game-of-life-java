package conway.gameHashlifeAlgo.iterator;

import java.util.Iterator;

public class ShapeIterator implements Iterator<int[]> {

    private int[][] figure;
    private int i = 0;
    private int j = 0;

    public ShapeIterator(int[][] figure) {
        this.figure = figure;
    }

    @Override
    public boolean hasNext() {
        return i < figure.length;
    }

    @Override
    public int[] next() {
        int[] cell = new int[] { i, j };
        j++;

        if (j >= figure[i].length) {
            j = 0;
            i++;
        }

        return cell;
    }
}

