package ru.job4j;

import java.util.List;

public class CatActionPrintLN implements CatyInterface {

    @Override
    public void printLn(List<Caty> catyln) {
        catyln.stream()
                .map(s -> s.getStr())
                .forEach(System.out::println);
    }

}
