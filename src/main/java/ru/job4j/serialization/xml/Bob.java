package ru.job4j.serialization.xml;

import ru.job4j.serialization.json.Contact;

import java.util.Arrays;

public class Bob {

    private final boolean fate;
    private final int growth;
    private final ru.job4j.serialization.json.Contact contact;
    private final String[] poetry;

    public Bob(boolean fate, int growth, Contact contact, String[] poetry) {
        this.fate = fate;
        this.growth = growth;
        this.contact = contact;
        this.poetry = poetry;
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

    public static void main(String[] args) {
        final Bob bob = new Bob(true, 170, new Contact("8 904 786-44-55"), new String[] {"la la la", "lo lo lo"});
    }

}
