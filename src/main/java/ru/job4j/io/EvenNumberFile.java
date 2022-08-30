package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {

    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                int i = Integer.parseInt(line);
                if (i % 2 == 0) {
                    System.out.println(i + " - чётное число");
                } else {
                    System.out.println(i + " - не чётное число");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("----------------------");
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text01 = new StringBuilder();
            StringBuilder text02 = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                int i = Character.getNumericValue((char) read);
                if (i % 2 == 0) {
                    text01.append((char) read);
                } else {
                    text02.append((char) read);
                }
            }
            System.out.println("Это числa чётные");
            System.out.println(text01);
            System.out.println("Это числа не чётные");
            System.out.println(text02);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
