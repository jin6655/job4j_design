package ru.job4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.List.*;

public class Caty {

    private int x;
    private String str;

    public Caty(int x, String str) {
        this.x = x;
        this.str = str;
    }

    @Override
    public String toString() {
        return "Caty{"
                + "x=" + x
                + ", str='" + str + '\''
                + '}';
    }

    public static void main(String[] args) {
        int row = 0;
        int column = 0;
        int[][] data = {{}, {7}, {}, {}, {5, 4}};
        while (data[row].length == 0) {
            if (column < data[row].length) {
                column++;
            } else {
                row++;
            }
        }
        System.out.println(data[row][column]);
    }

}
