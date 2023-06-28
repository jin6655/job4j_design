package ru.job4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        String string = "rat";
        String string02 = "bat";
        int x = 1;
        String str01 = String.format("%s %s", string, string02);
        String str02 = String.format("%1$" + x + "s", "");
        System.out.println(str02 + str01);
    }

}
