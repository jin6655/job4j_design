package ru.job4j.ood.foodstore;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ControllQualityTest {

    @Test
    public void whenAddFoodInStoreWarehouse() {
        Calendar now = Calendar.getInstance();
        Food expected = new Meat("beef", now, now, 1200, 0.8);
        List<Food> list = new ArrayList<>();
        list.add(new Meat("beef", now, now, 1200, 0.8));
        list.add(new Meat("pork", now, now, 1200, 0.8));
        list.add(new Cabbage("broccoli", now, now, 1200, 0.8));
        list.add(new Fish("salmon", now, now, 1200, 0.8));
        list.add(new Fish("carp", now, now, 1200, 0.8));
        list.add(new Potato("colombianPotato", now, now, 1200, 0.8));
        Store warehouse = new Warehouse();
        warehouse.getStorage().addAll(list);
        assertTrue(warehouse.getStorage().contains(expected));
    }

    @Test
    public void whenAddFoodInStoreShop() {
        Calendar d1 = Calendar.getInstance();
        d1.add(Calendar.DAY_OF_YEAR, -5);
        Calendar d2 = Calendar.getInstance();
        d2.add(Calendar.DAY_OF_YEAR, 3);
        Food expected = new Meat("beef", d2, d1, 1200, 0.8);
        List<Food> list = new ArrayList<>();
        list.add(new Meat("beef", d2, d1, 1200, 0.8));
        list.add(new Fish("carp", d2, d1, 1200, 0.8));
        Store warehouse = new Warehouse();
        warehouse.getStorage().addAll(list);
        Store shop = new Shop();
        ControllQuality distribution = new ControllQuality();
        distribution.addStore("Warehouse", warehouse);
        distribution.addStore("Shop", shop);
        distribution.redistribution();
        assertTrue(shop.getStorage().contains(expected));
    }

    @Test
    public void whenAddFoodInTrash() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_YEAR, -10);
        Calendar d2 = Calendar.getInstance();
        d2.add(Calendar.DAY_OF_YEAR, -5);
        Food expected = new Meat("beef", d2, now, 1200, 0.8);
        List<Food> list = new ArrayList<>();
        list.add(new Meat("beef", d2, now, 1200, 0.8));
        list.add(new Fish("carp", d2, now, 1200, 0.8));
        Store warehouse = new Warehouse();
        warehouse.getStorage().addAll(list);
        Store shop = new Shop();
        Store trash = new Trash();
        ControllQuality distribution = new ControllQuality();
        distribution.addStore("Warehouse", warehouse);
        distribution.addStore("Shop", warehouse);
        distribution.addStore("Trash", trash);
        distribution.redistribution();
        assertTrue(trash.getStorage().contains(expected));
    }

}