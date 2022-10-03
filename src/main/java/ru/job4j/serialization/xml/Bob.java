package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "Bob")
@XmlAccessorType(XmlAccessType.FIELD)
public class Bob {

    @XmlAttribute
    private boolean fate;

    @XmlAttribute
    private int growth;

    private Contact contact;

    @XmlElementWrapper(name = "poetry")
    @XmlElement(name = "poet")
    private String[] poetry;

    public Bob() {

    }

    public Bob(boolean fate, int growth, Contact contact, String[] poetry) {
        this.fate = fate;
        this.growth = growth;
        this.contact = contact;
        this.poetry = poetry;
    }

    @Override
    public String toString() {
        return "Bob{"
                + "fate=" + fate
                + ", growth=" + growth
                + ", contact=" + contact
                + ", poetry=" + Arrays.toString(poetry)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {
        final Bob bob = new Bob(true, 170, new Contact("8 904 786-44-55"), new String[]{"la la la", "lo lo lo"});

        JAXBContext context = JAXBContext.newInstance(Bob.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(bob, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (StringReader read = new StringReader(xml)) {
            Bob rsl = (Bob) unmarshaller.unmarshal(read);
            System.out.println(rsl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
