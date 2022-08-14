package ru.job4j;

import java.io.IOException;
import java.util.*;

public interface CatyInterface {

    void printLn(List<Caty> catyln);

    default void printStr(List<Caty> catystr) {
     throw new UnsupportedOperationException("printStr Not Used");
    }

}
