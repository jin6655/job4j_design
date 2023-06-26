package ru.job4j.ood.parcking;

import java.util.ArrayList;
import java.util.List;

public class Parking {

    private List<Place> parking;

    public Parking(List<Place> parking) {
        this.parking = parking;
    }

    public List<Place> getParking() {
        return parking;
    }

    public boolean parkCar(Car car) {
        boolean rsl = false;
        for (Place place : parking) {
            if (place.getPredicate().test(car)) {
                System.out.println("Парковка");
                place.Parking(car);
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    public static void main(String[] args) {
        List<Place> places = new ArrayList<>();
        Place truckPlace = new TruckPlace(0);
        Place sedanPlace = new CarPlace(8);
        places.add(truckPlace);
        places.add(sedanPlace);
        Parking parking = new Parking(places);
        Car truck = new Truck(4);
        boolean rsl = parking.parkCar(truck);
        System.out.println(rsl);
    }

}
