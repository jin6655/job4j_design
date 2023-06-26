package ru.job4j.ood.foodstore;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Warehouse implements Store<Food>{

    private final List<Food> storage = new ArrayList<>();
    private final Predicate<Double> CONDITIONS_CENTS = s -> s < 0.25;

    public Warehouse() {
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

    public Predicate<Double> getCONDITIONS_CENTS() {
        return CONDITIONS_CENTS;
    }

}
