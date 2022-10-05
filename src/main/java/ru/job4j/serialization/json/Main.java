package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws NoSuchFieldException {
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
        final Bob bobMod = gson.fromJson(bobJson, Bob.class);
        System.out.println("\nПеревод из JSON в объект - gson.fromJson(bobJson(строка в формате JSON), Bob.class)\n" + bobMod + "\n");
        System.out.println("Перевод из объекта в JSON - gson.toJson(bob) \n" + gson.toJson(bob) + "\n");

        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");
        List<String> list = new ArrayList<>();
        list.add("White");
        list.add("Black");
        JSONArray jsonArray = new JSONArray(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", person.isSex());
        jsonObject.put("age", person.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonArray);

        /*2) Используйте свои классы из задания «Формат JSON»,
             преобразуйте объекты в JSONObject и json-строку с помощью
             библиотеки JSON-Java (org.json).
         */
        System.out.println("Преобразование в json строку\n" + new JSONObject(bob).toString());
        JSONObject jsonBob = new JSONObject();
        JSONObject jsonContactBob = new JSONObject("{\"phone\":" + "\"" + bob.getContact().getPhone() + "\"}");
        jsonBob.put("fate", bob.isFate());
        jsonBob.put("growth", bob.getGrowth());
        jsonBob.put("contact", jsonContactBob);
        jsonBob.put("poetry", bob.getPoetry());
        System.out.println("Преобразование в JSONObject\n" + jsonBob);
    }

}
