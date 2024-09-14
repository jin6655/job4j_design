package ru.job4j.ood.foodstore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Warehouse implements Store<Food> {

    private String name;

    private final List<Food> storage = new ArrayList<>();
    private final Predicate<Double> conditionsCents = s -> s < 0.25;

    public Warehouse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void add(Food model) {
        storage.add(model);
    }

    @Override
    public void remove(Food model) {
        storage.remove(model);
    }

    @Override
    public List<Food> getStorage() {
        return storage;
    }

    public Predicate<Double> getConditionsCents() {
        return conditionsCents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Warehouse warehouse = (Warehouse) o;
        return Objects.equals(name, warehouse.name) && Objects.equals(storage, warehouse.storage) && Objects.equals(conditionsCents, warehouse.conditionsCents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, storage, conditionsCents);
    }
}
