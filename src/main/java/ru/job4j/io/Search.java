package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    /*
    1. Доработайте программу ru.job4j.io.Search. Программа должна запускаться с параметрами.
    Первый параметр - начальная папка.
    Второй параметр - расширение файлов, которые нужно искать.
    Необходимо добавить валидацию данных параметров. Валидацию параметров надо вынести в отдельный метод.
     */

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validation(String way, String fileExtension) throws IOException {
        Path dir = Paths.get(way);
        if (!dir.toFile().isDirectory()) {
            throw new IllegalArgumentException("Directory not defined. Set the right path!");
        }
        boolean chekValidation = !fileExtension.equals(".js") && !fileExtension.equals(".txt");
        if (chekValidation) {
            throw new IllegalArgumentException("Incorrect file extension specified.");
        }
    }

    public static void main(String[] args) throws IOException {
        validation(args[0], args[1]);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

}
