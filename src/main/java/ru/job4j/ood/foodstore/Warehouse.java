package ru.job4j.ood.foodstore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Warehouse implements Store<Food>{

    private String name;

    private final List<Food> STORAGE = new ArrayList<>();
    private final Predicate<Double> CONDITIONS_CENTS = s -> s < 0.25;

    public Warehouse(String name) {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warehouse warehouse = (Warehouse) o;
        return Objects.equals(name, warehouse.name) && Objects.equals(STORAGE, warehouse.STORAGE) && Objects.equals(CONDITIONS_CENTS, warehouse.CONDITIONS_CENTS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, STORAGE, CONDITIONS_CENTS);
    }
}
