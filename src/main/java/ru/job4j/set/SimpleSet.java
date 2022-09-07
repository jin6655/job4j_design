package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;
import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(10);


    //???, ???? ??? ?????????? ? ?? ???????????
    @Override
    public boolean add(T value) {
        boolean rsl = false;
        if (!contains(value)) {
            rsl = true;
        }
        set.add(value);
        return rsl;
    }

    //???, ???? ??? ???? ????? ???????
    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            if (Objects.equals(it.next(), value)) {
                rsl = true;
            }
        }
        return rsl;
         /*
        /*Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            if (Objects.equals(it.next(), value)) {
                return true;
            }
        }
        return false;

         */
    }

    @Override
    public Iterator iterator() {
        return set.iterator();
    }

}
