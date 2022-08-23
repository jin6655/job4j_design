package ru.job4j.map;

import java.util.*;

public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "name='" + name + '\''
                + ", children=" + children
                + ", birthday="
                + birthday.getTime();
    }

    public static void main(String[] args) {
        User userOne = new User("Olli", 10, new GregorianCalendar(2022, 8, 23));
        User userTwo = new User("Olli", 3, new GregorianCalendar(2022, 8, 23));
        User userThree = new User("Olli", 20, new GregorianCalendar(2022, 8, 23));
        Map<User, Object> map = new HashMap<>();
        map.put(userOne, new Object());
        map.put(userTwo, new Object());
        map.put(userThree, new Object());
        for (Map.Entry<User, Object> i : map.entrySet()) {
            System.out.println(i.getKey() + "\n" + i.getKey().hashCode());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
