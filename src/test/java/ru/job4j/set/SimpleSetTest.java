package ru.job4j.set;

import org.junit.Test;
import ru.job4j.serialization.set.Set;
import ru.job4j.serialization.set.SimpleSet;

import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenAllOptions() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(2));
        assertTrue(set.add(null));
        assertFalse(set.contains(1));
        assertTrue(set.contains(null));
        assertTrue(set.contains(2));
        assertFalse(set.contains(1));
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(null));
    }

}