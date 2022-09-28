package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    //private static final Logger LOG = LogManager.getLogger(UsageLog4j.class.getName());
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 3;
        short sh = 45;
        int number = 333;
        long l = 666L;
        float f = 3.45f;
        double g = 6.33;
        char ch = 'F';
        String str = "DD";
        LOG.debug("byte: {} short: {} int: {} long: {} float: {} double: {} char {} String: {}",
                b, sh, number, l, f, g, ch, str);
    }

}
