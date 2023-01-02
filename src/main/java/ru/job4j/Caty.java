package ru.job4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String black = "bl";
        String white  = "wh";
        String rsl = String.format("open %s and open %s and close %s", "red", black, white);
        System.out.println(rsl);
    }

}
