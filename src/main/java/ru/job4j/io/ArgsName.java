package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Key not found!");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String str = args[i];
            int equals = str.indexOf("=");
            String key = str.substring(1, equals);
            String value = str.substring(equals + 1);
            if (value.equals("")) {
                throw new IllegalArgumentException("Empty value is not allowed!");
            }
            values.put(key, value);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));
        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }

}
