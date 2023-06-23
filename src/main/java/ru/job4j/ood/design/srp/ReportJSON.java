package ru.job4j.ood.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ReportJSON implements Report {

    @Override
    public String generate(Predicate<Employee> filter, Store store) {
        StringBuilder rsl = new StringBuilder();
        List<Employee> list = store.findBy(filter);
        Gson lib = new GsonBuilder().setPrettyPrinting().create();
        rsl.append(lib.toJson(list));
        return rsl.toString();
    }

}
