package ru.job4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
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
        List<String> list = new ArrayList<>();
        list.add("Jon Jinov;jinov666@gmail.com;");
        list.add("Ivan Ivanov;iivanov@gmail.com");
        String ss = "Petr Arsentev;parsentev@yandex.ru";
        List<String> rsl = new ArrayList<>();
        list.stream().forEach(s -> System.out.println(rsl.add(s.split(";")[0] + "" + s.split(";")[1])));
        System.out.println(ss.split(";")[0]);
    }

}
