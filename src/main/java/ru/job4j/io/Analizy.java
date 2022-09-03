package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Analizy {

        public static void unavailable(String source, String target) {
            try (BufferedReader in = new BufferedReader(new FileReader(source)); PrintWriter out  = new PrintWriter(new FileWriter(target))) {
                boolean cursor = true;
                List<String> list = new ArrayList<>();
                for (String i = in.readLine(); i != null; i = in.readLine()) {
                    String log = i.split(" ")[0];
                    String date = i.split(" ")[1];
                    if (cursor && (log.equals("400") || log.equals("500"))) {
                        list.add(date);
                        cursor = false;
                    } else if (!cursor && !(log.equals("400") || log.equals("500"))) {
                        list.add(date);
                        cursor = true;
                        out.println(list.get(list.size() - 2) + ";" + list.get(list.size() - 1));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            unavailable("./data/log.log", "./data/analize.csv");
        }

}
