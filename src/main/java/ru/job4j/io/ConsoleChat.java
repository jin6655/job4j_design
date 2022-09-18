package ru.job4j.io;

import javafx.scene.Scene;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {

    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> list = new ArrayList<>();
        System.out.println("Добро пожаловать в чат! Чё хотел?");
        list.add("Добро пожаловать в чат! Чё хотел?");
        Scanner scan = new Scanner(System.in);
        String question = "";
        boolean botWorks = true;
        do {
            question = scan.nextLine();
            list.add(question);
            if (question.equals(STOP) || question.equals(OUT)) {
                botWorks = false;
                if (question.equals(OUT)) {
                    list.add("Пока");
                    System.out.println("Пока");
                }
            } else if (question.equals(CONTINUE)) {
                System.out.println("Ну задавай свой вопрос.");
                botWorks = true;
            }
            if (botWorks && !question.equals(CONTINUE) && !question.equals(OUT)) {
                String answer = readPhrases().get(new Random().nextInt(readPhrases().size()));
                System.out.println(answer);
                list.add(answer);
            }
            saveLog(list);
        } while (!question.equals(OUT));
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        Path file = Paths.get("./src/data/" + botAnswers);
        try (BufferedReader read = new BufferedReader(new FileReader(file.toFile()))) {
            String str;
            while ((str = read.readLine()) != null) {
                rsl.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter("./src/data/" + path))) {
            for (String i : log) {
                out.write(i);
                out.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("log.txt", "answers.txt");
        cc.run();
    }

}
