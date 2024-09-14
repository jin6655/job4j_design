package ru.job4j.ood.parcking;

import java.util.List;
import java.util.function.Predicate;

public interface Place<T extends Car> {

    void parking(T car);

    int getPlaceSize();

    List<T> getCars();

    Predicate<T> getPredicate();

}
