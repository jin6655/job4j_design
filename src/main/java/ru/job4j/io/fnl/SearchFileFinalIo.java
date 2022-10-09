package ru.job4j.io.fnl;

import ru.job4j.io.fnl.FileVisitorSearchFileFinalio;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchFileFinalIo {

    private static Map<String, String> mapArgs(String[] arg) {
        Map<String, String> map = new HashMap<>();
        for (String i : arg) {
            map.put(i.split("=")[0], i.split("=")[1]);
        }
        return map;
    }

    private static List<Path> search(Path root, Predicate<Path> predicate) throws IOException {
        FileVisitorSearchFileFinalio fileVisit = new FileVisitorSearchFileFinalio(predicate);
        Files.walkFileTree(root, fileVisit);
        return fileVisit.getList();
    }

    private static Predicate<Path> predicate(String type, String name) {
        Predicate<Path> rsl = null;
        Matcher matcher;
        if (type.equals("name")) {
            rsl = (s) -> s.toFile().getName().equals(name);
        } else if (type.equals("mask")) {
            String reqStr = name.replace(".", "\\.")
                    .replace("?", ".")
                    .replace("*", "");
            Pattern pattern = Pattern.compile(reqStr);
            rsl = (s) -> pattern.matcher(s.toFile().getName()).find();
        } else if (type.equals("regex")) {
            Pattern pattern = Pattern.compile(name);
            rsl = (s) -> pattern.matcher(s.toFile().getName()).find();
        }
        return rsl;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("\nNo arguments to run. Usage java -jar -d=DIRECTORY -n=(file name, mask(example - *.?xt) or regular expressions) -t=(name, mask or regex) -o=log.txt\");");
        }
        Path root = Paths.get(mapArgs(args).get("d"));
        Predicate<Path> pred = predicate(mapArgs(args).get("t"), mapArgs(args).get("n"));
        List<Path> list = search(root, pred);
        System.out.println("Идёт поиск по " + mapArgs(args).get("t") + " : " + mapArgs(args).get("n"));
        if (!list.isEmpty()) {
            for (Path i : list) {
                System.out.println("Файл найден, находится по адресу: " + i.toString());
                try (BufferedWriter write = new BufferedWriter(new FileWriter(root + "\\" + mapArgs(args).get("o"), true))) {
                    write.write(i.toString());
                    write.write(System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Файл не найден.");
        }
    }
}
