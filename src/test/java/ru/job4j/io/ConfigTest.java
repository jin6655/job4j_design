package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import org.hamcrest.Matchers;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPropWithComment() {
        String path = "./data/prop_data_prop.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Vasily"));
        assertThat(config.value("age"), is("35"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenPropException() throws IllegalArgumentException {
        String path = "./data/prop_data_exception.properties";
        Config config = new Config(path);
        config.load();
    }

}