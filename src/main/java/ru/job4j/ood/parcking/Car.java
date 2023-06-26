package ru.job4j.ood.parcking;

public abstract class Car {

    private int size;

    public Car(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Car{" +
                "size=" + size +
                '}';
    }
}
