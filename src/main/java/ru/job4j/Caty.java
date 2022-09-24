package ru.job4j;

import com.sun.source.tree.Tree;
import ru.job4j.io.PrintFiles;
import ru.job4j.io.duplicates.FileProperty;
import ru.job4j.list.SimpleArrayList;
import ru.job4j.map.SimpleMap;
import ru.job4j.set.Set;
import ru.job4j.set.SimpleSet;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Caty {

    private int x;
    private String str;

    public Caty(int x, String str) {
        this.x = x;
        this.str = str;
    }

    public int getX() {
        return x;
    }

    public String getStr() {
        return str;
    }

    @Override
    public String toString() {
        return "Caty{"
                + "x=" + x
                + ", str='"
                + str + '\''
                + '}';
    }

    public static void main(String[] args) throws IOException {
        String[] msg = {"1111", "333=1", "4444"};
        String i = Arrays.stream(msg).filter(s -> s.contains("=")).map(s -> s.substring(s.indexOf("=") + 1))
                .findFirst().orElse("");
        if (i.length() > 0) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

}
