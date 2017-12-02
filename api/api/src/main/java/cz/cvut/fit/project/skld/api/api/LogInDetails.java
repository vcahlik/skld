package cz.cvut.fit.project.skld.api.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.fit.project.skld.api.core.User;

public class LogInDetails {
    private User user;
    private String token;

    public LogInDetails() {}

    public LogInDetails(User user, String token) {
        this.setUser(user);
        this.setToken(token);
    }

    @JsonProperty
    public User getUser() {
        return user;
    }

    @JsonProperty
    public void setUser(User user) {
        this.user = user;
    }

    @JsonProperty
    public String getToken() {
        return token;
    }

    @JsonProperty
    public void setToken(String token) {
        this.token = token;
    }
}
