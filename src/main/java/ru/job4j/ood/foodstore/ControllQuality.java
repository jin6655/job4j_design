package ru.job4j.ood.foodstore;

import java.util.*;
import java.util.function.Predicate;

public class ControllQuality {

    private List<Store> storage = new ArrayList<>();
    private final Predicate<Double> DISCOUNT_CONDITIONS = s -> s >= 0.75 && s <= 1;

    public ControllQuality(List<Store> storage) {
        this.storage = storage;
    }

    public List<Store> getStorage() {
        return storage;
    }

    private static double daysBetweenTwoDates(Calendar firstDate, Calendar secondDate) {
        return Math.abs((double) (secondDate.getTimeInMillis() - firstDate.getTimeInMillis()) / (1000 * 60 * 60 * 24));
    }

    private static double expirationDateCents(Food food) {
        Calendar now = Calendar.getInstance();
        Calendar d1 = food.getCreateDate();
        Calendar d2 = food.getExpiryDate();
        double rsl = 0;
        if (now.getTimeInMillis() < d2.getTimeInMillis()) {
            rsl = 1 - (daysBetweenTwoDates(d2, now)) / (daysBetweenTwoDates(d2, d1));
        } else {
            rsl = daysBetweenTwoDates(now, d1) / daysBetweenTwoDates(d1, d2);
        }
        return rsl;
    }

    public void addStore(Store store) {
        storage.add(store);
    }

    public boolean addFood(Food food) {
        Boolean rsl = false;
        for (Store store : storage) {
            Double expirationDateCents = expirationDateCents(food);
            if (store.getCONDITIONS_CENTS().test(expirationDateCents)) {
                store.add(food);
                if (DISCOUNT_CONDITIONS.test(expirationDateCents)) {
                    food.setPrice(food.getPrice() * food.getDiscount());
                }
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    public void redistribution() {
        List<Food> foodDistribution = new ArrayList<>();
        for (Store store : storage) {
            List<Food> list = store.getSTORAGE();
            for (Food food : list) {
                Double expirationDateCents = expirationDateCents(food);
                Predicate<Double> pred = store.getCONDITIONS_CENTS();
                if (!pred.test(expirationDateCents)) {
                    foodDistribution.add(food);
                }
            }
            store.getSTORAGE().removeAll(foodDistribution);
        }
        for (Food food : foodDistribution) {
            addFood(food);
        }
    }

}
