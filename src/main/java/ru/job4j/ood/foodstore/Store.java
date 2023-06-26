package ru.job4j.ood.foodstore;

import java.util.List;
import java.util.function.Predicate;

public interface Store<T extends Food> {

    void add(T model);

    void remove(T model);

    List<T> getStorage();

    Predicate<Double> getCONDITIONS_CENTS();

}
