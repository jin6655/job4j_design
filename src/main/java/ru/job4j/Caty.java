package ru.job4j;

import ru.job4j.ood.parcking.Car;
import ru.job4j.ood.parcking.Truck;

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
        System.out.println("!");
        Car car = new Truck(10);
        String x = car.getClass().getName();
        String y = Truck.class.getName();
        System.out.println(car.getClass().getName());
        System.out.println(Truck.class.getName());
        System.out.println(x.equals(y));
    }

}
