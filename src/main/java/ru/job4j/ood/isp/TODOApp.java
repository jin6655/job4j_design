package ru.job4j.ood.isp;

import java.util.Scanner;

public class TODOApp {

    private void userSurvey (Menu menu, ActionDelegate action, MenuPrinter printConsole) {
        String exit = "";
        Scanner scanner = new Scanner(System.in);
        while (!exit.equals("0")) {
            System.out.println("Введите задачу. (1 - вывод списка задач, 0 - выход) ");
            String in = scanner.nextLine();
            if (in.equals("1")) {
                printConsole.print(menu);
            } else if (in.equals("0")) {
                break;
            } else {
                System.out.println("Эта задача закреплена за другой задачей? Y/N");
                String str = scanner.nextLine();
                if (str.equals("N") || str.equals("n")) {
                    menu.add(Menu.ROOT, in, action);
                } else if (str.equals("Y") || str.equals("y")) {
                    System.out.println("Введите задачу к которой её прикрепить");
                    String s = scanner.nextLine();
                    menu.add(s, in, action);
                } else {
                    menu.add(Menu.ROOT, in, action);
                }
            }
        }
    }


    public static void main(String[] args) {
        TODOApp app = new TODOApp();
        MenuPrinter printConsole = new PrintConsole();
        Menu menu = new SimpleMenu();
        ActionDelegate action = System.out::println;
        app.userSurvey(menu, action, printConsole);
    }

}
