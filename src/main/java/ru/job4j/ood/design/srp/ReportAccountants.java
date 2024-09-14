package ru.job4j.ood.design.srp;

import java.util.function.Predicate;

public class ReportAccountants implements Report {

    private final double nalog = 0.13;

    @Override
    public String generate(Predicate<Employee> filter, Store store) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;").append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary() * (1 - nalog)).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

}
