package cz.cvut.fit.si1.skld.client.model.domain;

public class User {
    private String userName;
    private Type type;

    public User(String userName, Type type) {
        this.userName = userName;
        this.type = type;
    }

    public enum Type {
        WORKER, MANAGER
    }
}
