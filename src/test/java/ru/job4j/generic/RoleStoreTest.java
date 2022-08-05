package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenUserRoleIsPetr() {
        RoleStore role = new RoleStore();
        role.add(new Role("1", "Vaily"));
        Role rsl = role.findById("1");
        assertThat(rsl.getUserrole(), is("Vaily"));
    }

    @Test
    public void whenAddAndFindThenUserRoleIsNull() {
        RoleStore role = new RoleStore();
        role.add(new Role("1", "Vaily"));
        Role rsl = role.findById("10");
        assertNull(rsl);
    }

    @Test
    public void whenAddDuplicateAndFindUserRoleIsPetr() {
        RoleStore role = new RoleStore();
        role.add(new Role("1", "Petr"));
        role.add(new Role("1", "Maxim"));
        Role result = role.findById("1");
        assertThat(result.getUserrole(), is("Petr"));
    }

}