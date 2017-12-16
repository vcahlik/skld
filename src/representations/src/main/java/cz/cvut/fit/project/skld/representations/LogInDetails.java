package cz.cvut.fit.project.skld.representations;

import com.fasterxml.jackson.annotation.JsonProperty;

/***
 * A response to login request containing information about the logged-in user and a token which can be used
 * in Authorization HTTP header.
 */
public class LogInDetails {
    private UserRepresentation user;
    private String token;

    public LogInDetails() {}

    public LogInDetails(UserRepresentation user, String token) {
        this.setUser(user);
        this.setToken(token);
    }

    @JsonProperty
    public UserRepresentation getUser() {
        return user;
    }

    @JsonProperty
    public void setUser(UserRepresentation user) {
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
