package ru.job4j.ood.isp;

import java.util.Iterator;

public class PrintConsole implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        Iterator<Menu.MenuItemInfo> iterator = menu.iterator();
        while (iterator.hasNext()) {
            Menu.MenuItemInfo item = iterator.next();
            int indent = ((item.getNumber().length() - 2) / 2) * 4;
            String str = String.format("%s %s", item.getNumber(), item.getName());
            if (indent != 0) {
                String strIndent = String.format("%1$" + indent + "s", "");
                System.out.println(strIndent + str);
            } else {
                System.out.println(str);
            }
        }
    }

}
