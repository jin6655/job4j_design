package ru.job4j.ood.parcking;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CarPlace implements Place {

    private int placeSize;
    private List<Car> cars = new ArrayList<>();

    Predicate<Car> predicate = car -> car.getSize() > 0 && placeSize > car.getSize();

    public CarPlace(int placeSize) {
        this.placeSize = placeSize;
    }

    @Override
    public void parking(Car car) {
        cars.add(car);
        placeSize  -= car.getSize();
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
    public Predicate getPredicate() {
        return predicate;
    }

}
