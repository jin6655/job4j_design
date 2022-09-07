package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private List<FileProperty> list = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String[] str = file.toString().split("\\\\");
        FileProperty fileProp = new FileProperty(attrs.size(), str[str.length - 1]);
        if (list.contains(fileProp)) {
            System.out.println(String.format("name %-20s size %d", fileProp.getName(), fileProp.getSize()));
        }
        list.add(fileProp);
        return super.visitFile(file, attrs);
    }

}
