package ru.job4j;

import java.util.ArrayList;
import java.util.List;

public class Caty {

    private int x;

    private String str;

    private static List<Caty> xats = new ArrayList<>();

    public Caty() {
    }

    public Caty(int x, String str) {
        this.x = x;
        this.str = str;
    }

    @Override
    public String toString() {
        return "Caty{"
                + "x=" + x
                + ", str='"
                + str + '\''
                + '}';
    }

    public static void main(String[] args) throws Exception {
        System.out.println("!");
        }

}
