package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {

    public static int[][] multiple(int size) {
        int[][] array = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int cell = 0; cell < size; cell++) {
                array[row][cell] = (row + 1) * (cell + 1);
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[][] rsl = ResultFile.multiple(5);
        try (FileOutputStream out = new FileOutputStream("result.xls")) {
            for (int[] i : rsl) {
                out.write(System.lineSeparator().getBytes());
                for (int j : i) {
                    out.write((String.valueOf(j) + " ").getBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
