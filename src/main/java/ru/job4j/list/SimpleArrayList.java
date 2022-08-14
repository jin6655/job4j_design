package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {
    /*
    Увеличивай массив при заполнении в 2 раза

    modCount  этот счётчик увеличивается при каждой модификации коллекции - методы add, set, remove

    В методах, где используется индекс нужно делать валидацию. Индекс должен находиться в рамках добавленных элементов.
    Используйте метод Objects.checkIndex() - методы remove, get

    Для удаления нужно использовать метод System.arraycopy() - метод remove, set

    Итератор кидает два исключения
    NoSuchElementException - элементов больше нет
    ConcurrentModificationException -  произошла модификация массива

     */

    private T[] container;

    private  int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    /*
     массив полный - увеличить в два раза
     добавить в конец
     */
    @Override
    public void add(T value) {
        if (size >= container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, container.length);
        T rsl = container[index];
        container[index] = newValue;
        modCount++;
        return rsl;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, container.length);
        T rsl = container[index];
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[container.length - 1] = null;
        size--;
        modCount++;
        return rsl;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, container.length);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    /*
    Итератор должен реализовывать fail-fast поведение, выкидывать исключение, если коллекция изменяется

     */
    @Override
    public Iterator<T> iterator() {
        int exceptedModCount = modCount;
        return new Iterator<T>() {
            private int count = 0;
            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public T next() {
                if (exceptedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[count++];
            }
        };
    }

}
