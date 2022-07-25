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
        int[] z  = {1, 3, 4, 5, 7, 6, 9};
        int i = 0;
        while ((z[i] % 2) != 0) {
            i++;
        }
        System.out.println(z[i]);
    }

}
