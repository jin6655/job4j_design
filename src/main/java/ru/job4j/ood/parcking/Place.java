package ru.job4j.ood.parcking;

import java.util.List;
import java.util.function.Predicate;

public interface Place<T extends Car> {

    void Parking(T car);

    int getPlaceSize();

    List<T> getCars();

    Predicate<T> getPredicate();

}
