package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : sources) {
                zip.putNextEntry(new ZipEntry(file.getPath().substring(12)));
                if (file.isFile()) {
                    try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                        zip.write(out.readAllBytes());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getAbsoluteFile().getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            throw new IllegalArgumentException("Not enough arguments!");
        }
        ArgsName arg = ArgsName.of(args);
        if (arg.get("d") == null && arg.get("e") == null && arg.get("o") == null) {
            throw new IllegalArgumentException("Not enough arguments!");
        }
        Path root = Paths.get(arg.get("d"));
        if (!root.toFile().isDirectory()) {
            throw new IllegalArgumentException("Directory does not exist!");
        }
        List<File> list = Search.search(root, s -> !s.toFile().getName().endsWith(arg.get("e")))
                .stream()
                .map(s -> s.toFile())
                .collect(Collectors.toList());
        File target = Paths.get(".\\" + arg.get("o")).toFile();
        packFiles(list, target);
        System.out.println(arg.get("d"));
    }

}
