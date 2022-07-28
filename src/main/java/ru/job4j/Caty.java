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
        List<Integer> x = List.of(1, 2, 3, -1);
        int y = 0;
        for (Integer i : x) {
            if (i < 0) {
                y = i;
            }
        }
        System.out.println(y);
    }

}
