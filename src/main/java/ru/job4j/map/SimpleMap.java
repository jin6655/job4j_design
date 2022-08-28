package ru.job4j.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    /* private int hash(int hashCode) {
        return hashCode % table.length;
    }

    private int indexFor(int hash) {
        return hash;
    }
     */

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] buffer = new MapEntry[capacity];
        for (int i = 0; i < table.length; i++) {
            buffer[i] = table[i];
        }
        table = buffer;
    }

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        int index = indexFor(hash(key.hashCode()));
        if (get(key) == null && table[index] == null) {
            if ((float) count / capacity > LOAD_FACTOR) {
                expand();
            }
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public V get(K key) {
        V rsl = null;
        for (MapEntry<K, V> entry : table) {
            if (entry != null && entry.key.equals(key)) {
                int index = indexFor(hash(key.hashCode()));
                rsl = table[index].value;
            }
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        for (MapEntry<K, V> entry : table) {
            if (entry != null && entry.key.equals(key)) {
                int index = indexFor(hash(key.hashCode()));
                table[index] = null;
                rsl = true;
                count--;
                modCount++;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int currentModCount = modCount;
            int current = 0;

            @Override
            public boolean hasNext() {
                boolean rsl = false;
                if (count != 0 && current < table.length - 1) {
                    while (table[current] == null && current < table.length - 1) {
                        current++;
                    }
                }
                return current < table.length - 1;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (currentModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                int i = current++;
                return table[i].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}
