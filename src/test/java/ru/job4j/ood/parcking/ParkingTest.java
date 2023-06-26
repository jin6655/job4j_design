package ru.job4j.ood.parcking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParkingTest {

    @Test
    public void whenAddParkingPlace() {
        List<Place> places = new ArrayList<>();
        Place truckPlace = new TruckPlace(30);
        Place sedanPlace = new CarPlace(7);
        places.add(truckPlace);
        places.add(sedanPlace);
        Parking parking = new Parking(places);
        assertEquals(places, parking.getParking());
    }

    @Test
    public void whenParkSedanCarTrue() {
        List<Place> places = new ArrayList<>();
        Place sedanPlace = new CarPlace(7);
        places.add(sedanPlace);
        Parking parking = new Parking(places);
        Car sedan = new Sedan();
        parking.parkCar(sedan);
        assertTrue(parking.getParking().get(0).getCars().contains(sedan));
    }

    @Test
    public void whenTruckTrueToTruckPlace() {
        List<Place> places = new ArrayList<>();
        Place truckPlace = new CarPlace(7);
        places.add(truckPlace);
        Parking parking = new Parking(places);
        Car truck = new Truck(4);
        assertTrue(parking.parkCar(truck));
    }

    @Test
    public void whenTruckTrueToSedanPlace() {
        List<Place> places = new ArrayList<>();
        Place truckPlace = new TruckPlace(0);
        Place sedanPlace = new CarPlace(8);
        places.add(truckPlace);
        places.add(sedanPlace);
        Parking parking = new Parking(places);
        Car truck = new Truck(4);
        assertTrue(parking.parkCar(truck));
    }

    @Test
    public void whenParkSedanCarNoParking() {
        List<Place> places = new ArrayList<>();
        Place sedanPlace = new CarPlace(0);
        places.add(sedanPlace);
        Parking parking = new Parking(places);
        Car sedan = new Sedan();
        assertFalse(parking.parkCar(sedan));
    }

    @Test
    public void whenParkTruckCarNoParking() {
        List<Place> places = new ArrayList<>();
        Place truckPlace = new TruckPlace(2);
        places.add(truckPlace);
        Parking parking = new Parking(places);
        Car truck01 = new Truck(6);
        Car truck02 = new Truck(5);
        Car truck03 = new Truck(4);
        parking.parkCar(truck01);
        parking.parkCar(truck02);
        assertFalse(parking.parkCar(truck03));
    }

}