package ru.job4j;

import java.util.*;

public class Caty {

    private int x;

    private String str;

    private static final List<Caty> cats = new ArrayList<>();

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

    public void setStr(String str) {
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

    public static void main(String[] args) throws Exception {
        Calendar now = Calendar.getInstance();
        Calendar d1 = Calendar.getInstance();
        d1.add(Calendar.DAY_OF_YEAR, 10);
        System.out.println(now.getTime());
        System.out.println(d1.getTime());
        }

}
