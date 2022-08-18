package ru.job4j.collection;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();
    public int size;

    // Метод pop() - должен возвращать значение и удалять его из коллекции.
    public  T pop() {
        size--;
        return linked.deleteFirst();
    }

    //Метод push(T value) - помещает значение в коллекцию.
    public void push(T value) {
        size++;
        linked.addFirst(value);
    }

}
