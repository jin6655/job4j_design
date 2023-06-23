package ru.job4j.ood.design.srp;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.function.Predicate;

public class ReportEngine {

    //1. Добавить поддержку формата XML в генераторе отчетов. Для сериализации использовать классы, описанные в разделе IO
    //2. Добавить поддержку формата JSON в генераторе отчетов. Для сериализации использовать классы, описанные в разделе IO
    //3. Написать тесты

    private Report report;
    private Store store;

    public ReportEngine(Report report, Store store) {
        this.report = report;
        this.store = store;
    }

    public String generate(Predicate<Employee> filter) {
        try {
            return report.generate(filter, store);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
