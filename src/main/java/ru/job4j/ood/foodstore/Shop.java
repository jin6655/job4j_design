package ru.job4j.ood.foodstore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Shop implements Store<Food> {

    private String name;

    private final List<Food> STORAGE = new ArrayList<>();
    private final Predicate<Double> CONDITIONS_CENTS = s -> s >= 0.25 && s <= 1;

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void add(Food model) {
        STORAGE.add(model);
    }

    @Override
    public void remove(Food model) {
        STORAGE.remove(model);
    }

    @Override
    public List<Food> getSTORAGE() {
        return STORAGE;
    }

    public Predicate<Double> getCONDITIONS_CENTS() {
        return CONDITIONS_CENTS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Shop shop = (Shop) o;
        return Objects.equals(name, shop.name) && Objects.equals(STORAGE, shop.STORAGE) && Objects.equals(CONDITIONS_CENTS, shop.CONDITIONS_CENTS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, STORAGE, CONDITIONS_CENTS);
    }
}
