package ru.job4j.iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.hamcrest.core.Is;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(list, 1, 2);
        assertThat(list, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLastOne() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenAddAfterLastTwo() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 4, 5));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3, 4, 5)));
    }

    @Test
    public void whenRemoveIfEqualsTen() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 10, 2, 10, 3, 10));
        Predicate<Integer> pred = (s) -> s == 10;
        ListUtils.removeIf(list, pred);
        assertThat(list, is(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void whenReplaceIfTen() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 10, 2, 10, 3, 10));
        Predicate<Integer> pred = (s) -> s == 10;
        ListUtils.replaceIf(list, pred, 22);
        assertThat(list, is(Arrays.asList(1, 22, 2, 22, 3, 22)));
    }

    @Test
    public void whenRemoveAllElementsAnotherList() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        List<Integer> elements = new ArrayList<>(Arrays.asList(2, 4, 6, 8, 10));
        ListUtils.removeAll(list, elements);
        assertThat(list, is(Arrays.asList(1, 3, 5, 7, 9)));
    }

}