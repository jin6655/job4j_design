package ru.job4j.ood.foodstore;

import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ControllQualityTest {

    @Test
    public void whenAddFoodInStore() {
        ControllQuality controllQuality = new ControllQuality(List.of(
                new Warehouse("Warehouse"), new Shop("Shop"), new Trash("Trash")
        ));
        Calendar created  = Calendar.getInstance();
        created.add(Calendar.DAY_OF_YEAR, -5);
        Calendar expirW = Calendar.getInstance();
        expirW.add(Calendar.DAY_OF_YEAR, 100);
        Calendar expirShop = Calendar.getInstance();
        expirShop.add(Calendar.DAY_OF_YEAR, 5);
        Calendar expirShopDiscount = Calendar.getInstance();
        expirShopDiscount.add(Calendar.DAY_OF_YEAR, 5);
        Calendar expirTrash = Calendar.getInstance();
        expirTrash.add(Calendar.DAY_OF_YEAR, -1);
        Food expectedWarehouse = new Meat("beef", expirW, created, 1200, 0.8);
        Food expectedShop = new Cabbage("broccoli", expirShop, created, 1200, 0.8);
        Food expectedShopWithDiscount = new Fish("salmon", expirShopDiscount, created, 1200, 0.8);
        Food expectedTrash = new Fish("carp", expirTrash, created, 1200, 0.8);
        controllQuality.addFood(expectedWarehouse);
        controllQuality.addFood(expectedShop);
        controllQuality.addFood(expectedShopWithDiscount);
        expectedShopWithDiscount.setPrice(expectedShopWithDiscount.getPrice() * expectedShopWithDiscount.getDiscount());
        controllQuality.addFood(expectedTrash);
        assertTrue(controllQuality.getStorage().get(0).getStorage().contains(expectedWarehouse));
        assertTrue(controllQuality.getStorage().get(1).getStorage().contains(expectedShop));
        assertTrue(controllQuality.getStorage().get(1).getStorage().get(1).equals(expectedShopWithDiscount));
        assertTrue(controllQuality.getStorage().get(2).getStorage().contains(expectedTrash));
    }

    @Test
    public void whenRedistribution() {
        ControllQuality controllQuality = new ControllQuality(List.of(
                new Warehouse("Warehouse"), new Shop("Shop"), new Trash("Trash")
        ));
        Calendar created  = Calendar.getInstance();
        created.add(Calendar.DAY_OF_YEAR, -5);
        Calendar expirW = Calendar.getInstance();
        expirW.add(Calendar.DAY_OF_YEAR, 100);
        Calendar expirShop = Calendar.getInstance();
        expirShop.add(Calendar.DAY_OF_YEAR, 5);
        Calendar expirShopDiscount = Calendar.getInstance();
        expirShopDiscount.add(Calendar.DAY_OF_YEAR, 5);
        Calendar expirTrash = Calendar.getInstance();
        expirTrash.add(Calendar.DAY_OF_YEAR, -1);
        Food expectedWarehouse = new Meat("beef", expirW, created, 1200, 0.8);
        Food expectedShop = new Cabbage("broccoli", expirShop, created, 1200, 0.8);
        Food expectedShopWithDiscount = new Fish("salmon", expirShopDiscount, created, 1200, 0.8);
        Food expectedTrash = new Fish("carp", expirTrash, created, 1200, 0.8);
        Store warehouse = new Warehouse("Warehouse");
        warehouse.getStorage().add(expectedWarehouse);
        Store shop = new Shop("Shop");
        shop.getStorage().add(expectedShop);
        shop.getStorage().add(expectedShopWithDiscount);
        Store trash = new Trash("Trash");
        trash.getStorage().add(expectedTrash);
        List<Store> expected = List.of(warehouse, shop, trash);
        controllQuality.getStorage().get(0).getStorage().add(expectedShop);
        controllQuality.getStorage().get(0).getStorage().add(expectedShopWithDiscount);
        controllQuality.getStorage().get(1).getStorage().add(expectedTrash);
        controllQuality.getStorage().get(2).getStorage().add(expectedWarehouse);
        controllQuality.redistribution();
        assertEquals(expected, controllQuality.getStorage());
    }

}