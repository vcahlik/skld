package cz.cvut.fit.project.skld.representations;

import com.fasterxml.jackson.annotation.JsonProperty;

/***
 * Odpoved na pozadavek k prihlaseni. Obsahuje informace o prihlasenem uzivateli a bezpecnostni token (muze byt pouzit v Authorization HTTP headeru).
 */
public class LogInDetails {
    private UserRepresentation user;
    private String token;

    /**
     * Konstruktor.
     */
    public LogInDetails() {}

    /**
     * Konstruktor.
     * @param user Prihlasovany uzivatel
     * @param token Bezpecnostni token
     */
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
