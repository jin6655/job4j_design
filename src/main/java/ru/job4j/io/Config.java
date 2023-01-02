package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            /*read.lines()
                    .filter(s -> s.indexOf("=") != -1)
                    .forEach(s -> values.put(s.substring(0, s.indexOf("=")), s.substring(s.indexOf("=") + 1)));
             */
            for (String i = read.readLine(); i != null; i = read.readLine()) {
                int eq = i.indexOf("=");
                if (eq != -1 && (i.substring(0, eq).length() == 0 || i.substring(eq + 1).length() == 0)) {
                    throw new IllegalArgumentException();
                }
                if (eq != -1) {
                    String[] key = i.substring(0, eq).split("\\.");
                    values.put(key[key.length - 1], i.substring(eq + 1));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        //System.out.println(new Config("app.properties"));
        Config x = new Config("app.properties");
        x.load();
        for (Map.Entry<String, String> entry : x.values.entrySet() ) {
            System.out.print("KEY " + entry.getKey());
            System.out.println(" VALUE " + entry.getValue());
        }
    }

}
