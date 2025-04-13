package conway.gameBasicAlgo.iterator;

import java.util.Iterator;

public class ArrayIterator implements Iterator<int[]> {
    private int[][] array;
    private int currentIndex = 0;

    public ArrayIterator(int[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < array.length;
    }

    @Override
    public int[] next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException();
        }
        return array[currentIndex++];
    }
}

