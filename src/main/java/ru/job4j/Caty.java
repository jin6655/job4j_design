package ru.job4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.List.*;

public class Caty implements Comparable<Caty> {

    private int x;
    private String str;

    public Caty(int x, String str) {
        this.x = x;
        this.str = str;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "Caty{"
                + "x=" + x
                + ", str='" + str + '\''
                + '}';
    }

    @Override
    public int compareTo(Caty o) {
        int rsl = 0;
        if (this.x > o.getX()) {
            rsl = 1;
        } else if (this.x < o.getX()) {
            rsl = -1;
        } else {
            rsl = 0;
        }
        return rsl;
    }

    public static void main(String[] args) {
        Integer z = 2;
    }

}
