package ru.job4j.iterator.map;

import java.util.Iterator;

public interface Map<K, V> extends Iterable<K> {

    boolean put(K key, V value);

    V get(K key);

    boolean remove(K key);

}
