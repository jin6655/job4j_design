package ru.job4j.ood.ocp;

import java.util.List;

public class CarsInheritance {

    private static class Car {
        public String sound() {
            String str = "beep-beep";
            System.out.println(str);
            return str;
        }
    }

    private static class NoSoundCar extends Car {
        @Override
        public String sound() {
            String str = "...";
            System.out.println(str);
            return str;
        }
    }

    public static void main(String[] args) {
        List<Car> cars = List.of(new Car(), new Car(), new NoSoundCar());
        cars.forEach(Car::sound);
    }


}
