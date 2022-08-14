package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements List<E> {

    private class Node<E> {

        E item;
        Node<E> next;
        Node<E> prev;

    }

    private Node<E>[] conteiner = new Node[10];

    private int size;

    private int modCount;

    @Override
    public void add(E value) {
        Node<E> el = new Node();
        el.item = value;
        if (size != 0) {
            el.prev = conteiner[size - 1];
            conteiner[size - 1].next = el;
        }
        conteiner[size] = el;
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return conteiner[index].item;
    }

    @Override
    public Iterator<E> iterator() {
        int exceptedModCount = modCount;
        return new Iterator<E>() {
            private int count;
            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public E next() {
                if (exceptedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return conteiner[count++].item;
            }
        };
    }
}
