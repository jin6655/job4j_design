package ru.job4j.io.fnl;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class FileVisitorSearchFileFinalio extends SimpleFileVisitor<Path> {

    private List<Path> list = new ArrayList<>();
    private Predicate<Path> pred;

    public FileVisitorSearchFileFinalio(Predicate<Path> pred) {
        this.pred = pred;
    }

    public List<Path> getList() {
        return list;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (pred.test(file)) {
            list.add(file);
        }
        return CONTINUE;
    }

}
