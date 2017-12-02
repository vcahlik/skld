package cz.cvut.fit.project.skld.api.core;

import javax.persistence.*;
import java.security.Principal;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(
        name="users",
        indexes = @Index(
                name = "idx_user_pin",
                columnList = "PIN",
                unique = true
        )
)
@NamedQueries({
        @NamedQuery(
                name = "User.findByPIN",
                query = "SELECT u FROM User u WHERE u.pin = :pin AND u.blockedAt IS NULL"
        )
})
public class User implements Principal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(name="created_at", nullable = false)
    private Instant createdAt;

    @Column(name="blocked_at")
    private Instant blockedAt;

    @Column(name="PIN")
    private String pin;

    @Column(name="is_admin", nullable = false)
    private boolean isAdmin;


    public User() {}

    public User(String name, String PIN) {
        setName(name);
        setPin(PIN);
    }

    public boolean comparePIN(String comparedPIN) {
        return pin.equals(comparedPIN);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        final User that = (User) o;

        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.isAdmin, that.isAdmin) &&
                Objects.equals(this.createdAt, that.createdAt) &&
                Objects.equals(this.blockedAt, that.blockedAt) &&
                Objects.equals(this.pin, that.pin);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getBlockedAt() {
        return blockedAt;
    }

    public void setBlockedAt(Instant blockedAt) {
        this.blockedAt = blockedAt;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
