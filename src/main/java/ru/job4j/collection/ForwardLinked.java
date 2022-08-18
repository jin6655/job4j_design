package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {

    private Node<T> head;
    public int size;

    private static class Node<T> {

        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }

    }

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        size++;
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);

    }

    public T deleteFirst() {
        T rsl = null;
        if (head == null) {
            throw new NoSuchElementException();
        }
            rsl = head.value;
            head = head.next;
        size--;
        return rsl;
    }

    public boolean revert() {
        boolean rsl = false;
        if (size > 1) {
            Node<T> prev = null;
            Node<T> node = head;
            while (node != null) {
                Node<T> next = node.next;
                node.next = prev;
                prev = node;
                node = next;
            }
            head = prev;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }
}
