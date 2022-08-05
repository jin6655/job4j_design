package ru.job4j.generic;

public class Role extends Base {

    private final String userrole;

    public Role(String id, String userrole) {
        super(id);
        this.userrole = userrole;
    }

    public String getUserrole() {
        return userrole;
    }
}
