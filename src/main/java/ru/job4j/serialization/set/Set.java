package ru.job4j.serialization.set;

public interface Set<T> extends Iterable<T> {

    boolean add(T value);

    boolean contains(T value);

}
