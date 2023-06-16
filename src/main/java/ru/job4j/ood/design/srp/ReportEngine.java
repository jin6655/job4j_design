package ru.job4j.ood.design.srp;

import java.util.function.Predicate;

public class ReportEngine {

    private Report report;
    private Store store;

    public ReportEngine(Report report, Store store) {
        this.report = report;
        this.store = store;
    }

    public String generate(Predicate<Employee> filter) {
        return report.generate(filter, store);
    }

}
