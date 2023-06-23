package ru.job4j.ood.design.srp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
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
        expect.append("<!DOCTYPE html>").append(System.lineSeparator()).append("</html")
                .append(System.lineSeparator())
                .append("<title>").append("<Employees>").append("</title")
                .append(System.lineSeparator());
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
        expect.append("</html>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenXMLGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        store.add(new Employee("Ivan", now, now, 100));
        store.add(new Employee("Semen", now, now, 50));
        store.add(new Employee("Oleg", now, now, 200));
        StringBuilder expected = new StringBuilder();
        Report report = new ReportXML();
        ReportEngine engine = new ReportEngine(report, store);
        expected.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n")
                .append("<employees>\n");
        for (Employee em : store.getEmployees()) {
            expected.append(String.format("    <employee name=\"%s\" salary=\"%s\">\n", em.getName(), Double.toString(em.getSalary())))
                    .append("        <hired>").append(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SXXX")
                            .format(em.getHired().getTime())).append("</hired>\n")
                    .append("        <fired>").append(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SXXX")
                            .format(em.getFired().getTime())).append("</fired>\n")
                    .append("    </employee>\n");
        }
        expected.append("</employees>\n");
        assertThat(engine.generate(em -> true), is(expected.toString()));
    }

    @Test
    public void whenJsonGeneratedFirst() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Date date = new Date(123, 5, 23, 15, 6, 13);
        now.setTime(date);
        store.add(new Employee("Ivan", now, now, 100));
        Report report = new ReportJSON();
        ReportEngine engine = new ReportEngine(report, store);
        StringBuilder expected = new StringBuilder();
        expected.append("[\n  {\n    \"name\": \"Ivan\",\n")
                .append("    \"hired\": {\n      \"year\": 2023,\n      \"month\": 5,\n      \"dayOfMonth\": 23,\n")
                .append("      \"hourOfDay\": 15,\n      \"minute\": 6,\n      \"second\": 13\n    },\n")
                .append("    \"fired\": {\n      \"year\": 2023,\n      \"month\": 5,\n      \"dayOfMonth\": 23,\n")
                .append("      \"hourOfDay\": 15,\n      \"minute\": 6,\n      \"second\": 13\n    },\n")
                .append("    \"salary\": 100.0\n  }\n]");
        assertEquals(expected.toString(), engine.generate(em -> true));
    }

    @Test
    public void whenJsonGeneratedSecond() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Date date = new Date(123, 5, 23, 15, 6, 13);
        now.setTime(date);
        store.add(new Employee("Ivan", now, now, 100));
        Report report = new ReportJSON();
        ReportEngine engine = new ReportEngine(report, store);
        StringBuilder expected = new StringBuilder();
        Gson lib = new GsonBuilder().setPrettyPrinting().create();
        expected.append(lib.toJson(store.findBy(em -> true)));
        assertEquals(expected.toString(), engine.generate(em -> true));
    }

}