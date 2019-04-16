package model.entity;

public enum Role {
    STUDENT,
    ADMIN;

    private int id;

    Role() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
