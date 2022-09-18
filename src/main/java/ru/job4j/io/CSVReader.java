package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {

    public static List<Integer> indexColumn(ArgsName arg) {
        List<Integer> list = new ArrayList<>();
        String delimiter = arg.get("delimiter");
        String[] massiveArg = arg.get("filter").split(",");
        try (FileReader out = new FileReader(arg.get("path"))) {
            Scanner scan = new Scanner(out).useDelimiter(delimiter);
            String[] str = scan.nextLine().split(delimiter);
            for (int i = 0; i < str.length; i++) {
                for (String j : massiveArg) {
                    if (str[i].equals(j)) {
                        list.add(i);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void scanCSV(Scanner scan, String out, String delimiter, List<Integer> list) {
        while (scan.hasNext()) {
            StringJoiner join = new StringJoiner(delimiter);
            String[] str = scan.nextLine().split(delimiter);
            for (Integer i : list) {
                join.add(str[i]);
            }
            if (out.equals("stdout")) {
                System.out.println(join);
            } else {
                try (BufferedWriter write = new BufferedWriter(new FileWriter(out, true))) {
                    write.write(join + System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void handle(ArgsName argsName) throws Exception {
        Path file = Paths.get(argsName.get("path"));
        List<Integer> list = indexColumn(argsName);
        String delimiter = argsName.get("delimiter");
        try (BufferedReader out = new BufferedReader(new FileReader(file.toFile()))) {
            for (String line = out.readLine(); line != null; line = out.readLine()) {
                Scanner scan = new Scanner(line).useDelimiter(delimiter);
                scanCSV(scan, argsName.get("out"), delimiter, list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName arg = ArgsName.of(args);
        handle(arg);
    }

}
