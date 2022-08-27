package ru.job4j.map;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenAddNewElementsBoolean() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(1, "Lev"));
    }

    @Test
    public void whenAddDoubleElementsBoolean() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Lev");
        assertFalse(map.put(1, "Lev"));
    }

    @Test
    public void whenAddNewElements() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Lev");
        map.put(2, "Osel");
        assertThat("Osel", is(map.get(2)));
    }

    @Test
    public void whenIncreasLengthOfArray() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Lev");
        map.put(2, "Osel");
        map.put(3, "Lev");
        map.put(4, "Osel");
        map.put(5, "Lev");
        map.put(6, "Osel");
        map.put(7, "Lev");
        map.put(8, "Osel");
        map.put(9, "Lev");
        map.put(10, "Osel");
        map.put(11, "Slon");
        assertThat("Slon", is(map.get(11)));
    }

    @Test
    public void whenGetValueForIndexInt() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Lev");
        map.put(2, "Osel");
        assertThat("Osel", is(map.get(2)));
    }

    @Test
    public void whenGetValueForIndexString() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("1", "Lev");
        map.put("2", "Osel");
        assertThat("Osel", is(map.get("2")));
    }

    @Test
    public void whenRemoveElement() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("1", "Lev");
        map.put("2", "Osel");
        assertTrue(map.remove("2"));
        assertTrue(map.put("3", "Lev"));
    }

    @Test
    public void whenRemoveNull() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("1", "Lev");
        map.put("kl", "Osel");
        assertFalse(map.remove("nnn"));
    }

    @Test
    public void whenIterator() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Lev");
        map.put(2, "Osel");
        Iterator<Integer> it = map.iterator();
        assertTrue(it.hasNext());
        it.next();
        assertTrue(it.hasNext());
        it.next();
        assertFalse(it.hasNext());
    }

}