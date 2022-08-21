package ru.job4j;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.job4j.collection.ForwardLinked;
import ru.job4j.iterator.ListUtils;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println(list);
    }

}
