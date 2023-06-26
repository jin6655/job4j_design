package ru.job4j;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Caty {

    private int x;

    private String str;

    public Caty() {
    }

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

    private static class Item {
        private String name;

        public Item(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Item item = (Item) o;
            return Objects.equals(name, item.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    public static List<Item> findBy(List<Item> list, Predicate<Item> filter) {
        List<Item> rsl = list.stream().filter(filter).collect(Collectors.toList());
        return rsl;
    }

    public static void main(String[] args) throws Exception {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_YEAR, -10);
        Calendar d2 = Calendar.getInstance();
        d2.add(Calendar.DAY_OF_YEAR, -5);
        System.out.println(now.getTime());
        System.out.println(d2.getTime());
    }

}
