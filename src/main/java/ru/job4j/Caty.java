package ru.job4j;

import com.sun.source.tree.Tree;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.text.Text;
import ru.job4j.list.SimpleArrayList;
import ru.job4j.set.Set;
import ru.job4j.set.SimpleSet;

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
        Set<Integer> set = new SimpleSet<>();
        set.add(null);
        set.contains(null);
        for (Integer i : set) {
            System.out.println(i);
        }
    }

}
