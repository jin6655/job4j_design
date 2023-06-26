package ru.job4j.ood.parcking;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TruckPlace implements Place<Car> {

    private int placeSize;
    private List<Car> cars = new ArrayList<>();

    Predicate<Car> predicate = car -> car.getClass().getName() != Sedan.class.getName() && placeSize > 0;

    public TruckPlace(int placeSize) {
        this.placeSize = placeSize;
    }

    @Override
    public void Parking(Car car) {
        cars.add(car);
        placeSize--;
    }

    @Override
    public int getPlaceSize() {
        return placeSize;
    }

    @Override
    public List<Car> getCars() {
        return cars;
    }

    @Override
    public Predicate<Car> getPredicate() {
        return predicate;
    }
}
