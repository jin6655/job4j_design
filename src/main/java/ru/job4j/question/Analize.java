package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int changed = 0;
        int deleted = 0;
        Map<Integer, String> map = new HashMap<>();
        for (User i : current) {
            map.put(i.getId(), i.getName());
        }
        for (User j : previous) {
            if (map.get(j.getId()) == null) {
                deleted++;
            } else if (!map.get(j.getId()).equals(j.getName())) {
                changed++;
            }
        }
        int added = current.size() - previous.size() + deleted;
        return new Info(added, changed, deleted);
    }

}
