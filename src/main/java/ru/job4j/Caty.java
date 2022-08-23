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
        Map<Caty, String> map = new HashMap<>();
        Map<Integer, String> map02  = new HashMap<>();
        map.put(new Caty(1, "ff"), "g");
        map.put(new Caty(2, "hhhh"), "b");
        map.put(new Caty(1, "jj"), "c");
        map02.put(1, "ff");
        map02.put(2, "gg");
        map02.put(1, "kk");
        System.out.println(map);
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Caty caty = (Caty) o;
        return x == caty.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x);
    }

}
