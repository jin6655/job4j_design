package ru.job4j.ood.foodstore;

import java.util.List;
import java.util.function.Predicate;

public interface Store<T extends Food> {

    String getName();

    void add(T model);

    void remove(T model);

    List<T> getSTORAGE();

    Predicate<Double> getCONDITIONS_CENTS();

}
