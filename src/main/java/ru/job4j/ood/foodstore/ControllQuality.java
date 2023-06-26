package ru.job4j.ood.foodstore;

import java.util.*;
import java.util.function.Predicate;

public class ControllQuality {

    private static final Map<String, Store> STORAGE = new HashMap<>();

    public void addStore(String nameStore,  Store store) {
        STORAGE.put(nameStore, store);
    }

    public static double daysBetweenTwoDates(Calendar firstDate, Calendar secondDate) {
        return Math.abs((double) (secondDate.getTimeInMillis() - firstDate.getTimeInMillis()) / (1000 * 60 * 60 * 24));
    }

    public static double expirationDateCents(Food food) {
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

    public void redistribution() {
        List<Food> foodDistribution = new ArrayList<>();
        Predicate<Double> predicateDiscount = s -> s >= 0.75 && s <= 1;
        System.out.println("Инвентаризация");
        for (Map.Entry<String, Store> store : STORAGE.entrySet()) {
            List<Food> list = store.getValue().getStorage();
            System.out.println("склад " + store.getKey());
            for (Food food : list) {
                Double expirationDateCents = expirationDateCents(food);
                System.out.println("Food = " + food);
                System.out.println("expDateCents = " + expirationDateCents);
                Predicate<Double> pred = store.getValue().getCONDITIONS_CENTS();
                System.out.println("Проверка товара на срок годности - " + pred.test(expirationDateCents));
                if (!pred.test(expirationDateCents)) {
                    foodDistribution.add(food);
                }
            }
            store.getValue().getStorage().removeAll(foodDistribution);
            System.out.println();
        }
        System.out.println("Список на распределение");
        System.out.println(foodDistribution);
        System.out.println("Распределение");
        for (Food food : foodDistribution) {
            Double expirationDateCents = expirationDateCents(food);
            for (Map.Entry<String, Store> store : STORAGE.entrySet()) {
                Predicate<Double> pred = store.getValue().getCONDITIONS_CENTS();
                if (pred.test(expirationDateCents)) {
                    if (predicateDiscount.test(expirationDateCents)) {
                        food.setPrice(food.getPrice() * food.getDiscount());
                    }
                    store.getValue().getStorage().add(food);
                    break;
                }
            }
        }
    }

}
