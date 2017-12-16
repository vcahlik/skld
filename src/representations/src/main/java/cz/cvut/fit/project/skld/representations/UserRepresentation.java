package cz.cvut.fit.project.skld.representations;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Objects;

/***
 * A response with full details of a User
 */
public class UserRepresentation {

    private long id;
    private String name;
    private Instant createdAt;
    private boolean isAdmin;

    public UserRepresentation(long id, String name, Instant createdAt, boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.isAdmin = isAdmin;
    }

    public UserRepresentation() {}

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("created_at")
    public Instant getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("is_admin")
    public boolean isAdmin() {
        return isAdmin;
    }

    @JsonProperty("is_admin")
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRepresentation)) return false;
        UserRepresentation that = (UserRepresentation) o;
        return id == that.id &&
                isAdmin == that.isAdmin &&
                Objects.equals(name, that.name) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createdAt, isAdmin);
    }
}