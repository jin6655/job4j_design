package ru.job4j;

import com.sun.source.tree.Tree;
import ru.job4j.io.PrintFiles;
import ru.job4j.io.duplicates.FileProperty;
import ru.job4j.list.SimpleArrayList;
import ru.job4j.map.SimpleMap;
import ru.job4j.set.Set;
import ru.job4j.set.SimpleSet;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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
        String str = "238145";
        System.out.println(str.indexOf("1"));
    }

}
