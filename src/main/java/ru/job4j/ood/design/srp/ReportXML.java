package ru.job4j.ood.design.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class ReportXML implements Report {

    @Override
    public String generate(Predicate<Employee> filter, Store store) throws JAXBException {
        StringBuilder rsl = new StringBuilder();
        List<Employee> list = store.findBy(filter);
        JAXBContext context = JAXBContext.newInstance(Employees.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(new Employees(list), writer);
                rsl.append(writer.getBuffer().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl.toString();
    }

    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    static class Employees {

        @XmlElement(name = "employee")
        List<Employee> list = new ArrayList<>();

        public Employees(List<Employee> list) {
            this.list = list;
        }

        public Employees () {

        }

        public List<Employee> getList() {
            return list;
        }

        public void setList(List<Employee> list) {
            this.list = list;
        }
    }

}
