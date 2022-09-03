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
import ru.job4j.list.SimpleArrayList;
import ru.job4j.map.Map;
import ru.job4j.map.SimpleMap;
import ru.job4j.set.Set;
import ru.job4j.set.SimpleSet;

import java.io.*;
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

    public static void main(String[] args) {
        //C:\Users\VVV\Desktop\text.01
        //try (FileReader in = new FileReader("C:\\Users\\VVV\\Desktop\\text01.txt")) {
        try (BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\VVV\\Desktop\\text01.txt"))) {
            in.mark(1);
            System.out.println(in.readLine());
            System.out.println(in.readLine());
            System.out.println(in.readLine());
            System.out.println(in.readLine());
            in.reset();
            System.out.println();
            System.out.println(in.readLine());
            System.out.println(in.readLine());
            System.out.println(in.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
