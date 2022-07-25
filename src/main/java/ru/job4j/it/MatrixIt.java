package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {

    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean rsl = false;
        if (column >= data[row].length) {
            column = 0;
        }
        while (data[row].length == 0 && row < data.length - 1) {
            row++;
        }
        return !(data[row].length == 0);
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return column >= data[row].length - 1 ? data[row++][column] : data[row][column++];
    }

}
