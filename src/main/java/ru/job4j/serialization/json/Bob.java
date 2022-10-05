package ru.job4j.serialization.json;

import java.util.Arrays;

public class Bob {

    private final boolean fate;
    private final int growth;
    private final Contact contact;
    private final String[] poetry;

    public Bob(boolean fate, int growth, Contact contact, String[] poetry) {
        this.fate = fate;
        this.growth = growth;
        this.contact = contact;
        this.poetry = poetry;
    }

    public boolean isFate() {
        return fate;
    }

    public int getGrowth() {
        return growth;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getPoetry() {
        return poetry;
    }

    @Override
    public String toString() {
        return "Bob{"
                + "fate=" + fate
                + ", growth=" + growth
                + ", contact=" + contact
                + ", poetry=" + Arrays.toString(poetry)
                + '}';
    }

}
