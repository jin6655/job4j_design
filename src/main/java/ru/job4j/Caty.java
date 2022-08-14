package ru.job4j;

import java.util.*;

public class Caty {

    private int x;
    private String str;

    public Caty(int x, String str) {
        this.x = x;
        this.str = str;
    }

    public int getX() {
        return x;
    }

    public String getStr() {
        return str;
    }

    @Override
    public String toString() {
        return "Caty{"
                + "x=" + x
                + ", str='"
                + str + '\''
                + '}';
    }

    public static void main(String[] args) {
        List<Caty> cats = List.of(
                new Caty(10, "Lucy"),
                new Caty(20, "Loi"),
                new Caty(30, "Tom")
        );
        int z  = 3;
        System.out.println(Integer.valueOf(2) + z);
    }

}
