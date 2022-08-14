package ru.job4j;

import java.util.*;

public class CatAction implements CatyInterface {

    @Override
    public void printLn(List<Caty> catyln) {
        catyln.stream()
                .map(s -> s.getStr())
                .forEach(System.out::println);
    }

    @Override
    public void printStr(List<Caty> catystr) {
        catystr.stream()
                .map(s -> s.getStr() + " ")
                .forEach(System.out::print);
    }

}
