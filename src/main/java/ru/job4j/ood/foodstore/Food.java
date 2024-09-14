package ru.job4j.ood.foodstore;

import java.util.Calendar;
import java.util.Objects;

public class Food {

    private String name;

    private Calendar expiryDate;

    private Calendar createDate;

    private double price;

    private double discount;

    public Food(String name, Calendar expiryDate, Calendar createDate, double price, double discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        validateDiscount(discount);
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Calendar expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        validateDiscount(discount);
        this.discount = discount;
    }

    protected static void validateDiscount(double discount) {
        if (discount < 0 || discount > 1) {
            throw new IllegalArgumentException("The percentage discount value must be within 100%, enter a value from 0 to 1.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return Objects.equals(name, food.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='" + name + '\''
                + ", expiryDate=" + expiryDate.getTime()
                + ", createDate=" + createDate.getTime()
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }

}
