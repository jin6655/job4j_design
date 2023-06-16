package ru.job4j.ood.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ReportEngineTest {

    /*@Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
     */

    @Test
    public void whenAccountantsGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        double salary = worker.getSalary() * (1 - 0.13);
        store.add(worker);
        Report report = new ReportAccountants();
        ReportEngine engine = new ReportEngine(report, store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(salary).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHRGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        store.add(new Employee("Ivan", now, now, 100));
        store.add(new Employee("Semen", now, now, 50));
        store.add(new Employee("Oleg", now, now, 200));
        Report report = new ReportHR();
        ReportEngine engine = new ReportEngine(report, store);
        List<Employee> list = store.getEmployees().stream()
                .sorted((o1, o2) -> Double.compare(o2.getSalary(), o1.getSalary()))
                .collect(Collectors.toList());
        StringBuilder expect = new StringBuilder().append("Name; Salary;");
        for (Employee em : list) {
            expect.append(System.lineSeparator())
                    .append(em.getName()).append(";")
                    .append(em.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenProgrammersGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        store.add(new Employee("Ivan", now, now, 100));
        store.add(new Employee("Semen", now, now, 50));
        store.add(new Employee("Oleg", now, now, 200));
        Report report = new ReportProgrammers();
        ReportEngine engine = new ReportEngine(report, store);
        StringBuilder expect = new StringBuilder();
        expect.append("</report>");
        for (Employee em : store.getEmployees()) {
            expect.append(System.lineSeparator())
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
        expect.append("</report>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

}