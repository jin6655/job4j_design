package ru.job4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Caty {

    private int x;
    private String str;

    public Caty(int x, String str) {
        this.x = x;
        this.str = str;
    }

    public int getX() {
        return x;
    }

    public String getStr() {
        return str;
    }

    @Override
    public String toString() {
        return "Caty{"
                + "x=" + x
                + ", str='"
                + str + '\''
                + '}';
    }

    public static void main(String[] args) {
        String reg = "*.?xt";
        String reqStr = reg.replace(".", "\\.")
                .replace("?", ".")
                .replace("*", "");
        Pattern pattern = Pattern.compile("\\..xt");
        String[] str = {
                "Abs for mtAbf \n dfg.txt sad Abb kfksd.nxt",
                "Abs for mtAbf \n dfg sad Abb ktfkd.txt",
                "Abs for mtAbf \n dfg sad Abb ktfkstxt"
        };
        for (String i: str) {
            Matcher matcher = pattern.matcher(i);
            while (matcher.find()) {
                System.out.print(i.substring(matcher.start(), matcher.end()));
                System.out.println();
            }
        }
        System.out.println(reqStr);
    }

}
