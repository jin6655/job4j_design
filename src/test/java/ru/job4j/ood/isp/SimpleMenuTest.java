package ru.job4j.ood.isp;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.Assert.assertEquals;

public class SimpleMenuTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenAddThenReturnSame() throws Exception {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        PrintConsole printConsole = new PrintConsole();
        assertEquals(
                new Menu.MenuItemInfo(
                        "Сходить в магазин", List.of("Купить продукты"), STUB_ACTION, "1."
                ),
                menu.select("Сходить в магазин").get()
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Купить продукты", List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."
                ),
                menu.select("Купить продукты").get()
        );
        assertEquals(
                new Menu.MenuItemInfo("Купить хлеб", List.of(), STUB_ACTION, "1.1.1."
                ),
                menu.select("Купить хлеб").get()
        );
        assertEquals(
                new Menu.MenuItemInfo("Купить молоко", List.of(), STUB_ACTION, "1.1.2."
                ),
                menu.select("Купить молоко").get()
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Покормить собаку", List.of(), STUB_ACTION, "2."
                ),
                menu.select("Покормить собаку").get()
        );
        StringBuilder expected01 = new StringBuilder();
        expected01.append("1. Сходить в магазин").append(System.lineSeparator());
        expected01.append("    1.1. Купить продукты").append(System.lineSeparator());
        expected01.append("        1.1.1. Купить хлеб").append(System.lineSeparator());
        expected01.append("        1.1.2. Купить молоко").append(System.lineSeparator());
        expected01.append("2. Покормить собаку").append(System.lineSeparator());
        StringBuilder expected02 = new StringBuilder();
        expected02.append("1. Сходить в магазин").append(System.lineSeparator());
        expected02.append("    1.1. Купить продукты").append(System.lineSeparator());
        expected02.append("        1.1.1. Купить хлеб").append(System.lineSeparator());
        expected02.append("        1.1.2. Купить молоко").append(System.lineSeparator());
        expected02.append("2. Покормить собаку");
        String text01 = tapSystemOut(() -> {
            printConsole.print(menu);
        });
        assertEquals(expected01.toString(), text01);
        printConsole.print(menu);
        assertEquals(expected02.toString(), outputStreamCaptor.toString().trim());
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

}