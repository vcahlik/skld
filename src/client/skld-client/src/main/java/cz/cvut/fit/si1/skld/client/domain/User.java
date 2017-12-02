package cz.cvut.fit.si1.skld.client.domain;

public class User {
    private String userName;
    private Type type;

    public enum Type {
        WORKER, MANAGER
    }

    public User(String userName, Type type) {
        this.userName = userName;
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }
}