package ru.job4j.ood.design.srp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ReportProgrammers implements Report {

    @Override
    public String generate(Predicate<Employee> filter, Store store) {
        StringBuilder text = new StringBuilder();
        text.append("<!DOCTYPE html>").append(System.lineSeparator()).append("</html")
                .append(System.lineSeparator())
                .append("<title>").append("<Employees>").append("</title")
                .append(System.lineSeparator());
        for (Employee em : store.findBy(filter)) {
            text.append(System.lineSeparator())
                    .append("<worker>").append(System.lineSeparator())
                    .append("<name>")
                    .append(em.getName())
                    .append("</name>").append(System.lineSeparator())
                    .append("<hired>")
                    .append(em.getHired())
                    .append("</hired>").append(System.lineSeparator())
                    .append("<fired>")
                    .append(em.getFired())
                    .append("</fired>").append(System.lineSeparator())
                    .append("<salary>")
                    .append(em.getSalary())
                    .append("</salary>").append(System.lineSeparator())
                    .append("</worker>").append(System.lineSeparator());
        }
        text.append("</html>");
        return text.toString();
    }

}
