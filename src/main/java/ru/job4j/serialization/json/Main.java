package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

    public static void main(String[] args) {
        final Bob bob = new Bob(true, 180, new Contact("8 933 946-66-44"), new String[] {"lo lo lo", "la la la"});
        final String bobJson =
                "{"
                        + "\"fate\":true,"
                        + "\"growth\":177,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"8 932 843 256-11-77\""
                        + "},"
                        + "\"poetry\":"
                        + "[\"la la\", \"lo lo\"]"
                + "}";
        final Person person = new Person(false, 32, new Contact("8 955 324-76-43"), new String[] {"White", "Black"});
        final String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";
        final Gson gson = new GsonBuilder().create();
        final Person personMod = gson.fromJson(personJson, Person.class);
        final Bob bobMod = gson.fromJson(bobJson, Bob.class);
        System.out.println(bobMod);
        System.out.println(gson.toJson(bob));
    }

}
