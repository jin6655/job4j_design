package ru.job4j;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Flow;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.List.*;

public class Caty {

    private int x;
    private String str;
    private Date date;

    public Caty(int x, String str, Date date) {
        this.x = x;
        this.str = str;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Caty{"
                + "x=" + x
                + ", str='" + str
                + '\'' + ", date: " + new SimpleDateFormat("dd.MM.YYYY").format(date)
                + '}';
    }

    public static void main(String[] args) {
        Date date = new Date();
        Caty cat  = new Caty(10, "Natsu", new Date());
        System.out.println(cat);
    }

}
