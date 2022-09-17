package ru.job4j;

import com.sun.source.tree.Tree;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.text.Text;
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
        Path fileRead = Paths.get("C:\\Users\\VVV\\Desktop\\text01.txt");
        Path fileWrite = Paths.get("C:\\Users\\VVV\\Desktop\\text02.txt");
        try (BufferedReader in = new BufferedReader(new FileReader(fileRead.toFile()));
             BufferedWriter out = new BufferedWriter(new FileWriter(fileWrite.toFile()))) {
            for (String i = in.readLine(); i != null; i = in.readLine()) {
                System.out.println(i);
                out.write(i + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
