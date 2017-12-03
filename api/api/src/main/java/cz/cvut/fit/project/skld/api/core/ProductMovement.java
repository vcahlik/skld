package cz.cvut.fit.project.skld.api.core;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "product_movements")
public class ProductMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Instant at;

    @Column(nullable = false)
    private long quantity;

    @Column(nullable = false)
    private boolean missing;

    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "FK_Product_movement_product"))
    private Product product;

    @Column(nullable = false)
    private String location;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_Product_User"), nullable = false)
    private User user;

    public ProductMovement() {}

    public ProductMovement(Product product, long qty, String destination, User creator) {
        this.product = product;
        quantity = qty;
        location = destination;
        user = creator;
        at = Instant.now();
        missing = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Instant getAt() {
        return at;
    }

    public void setAt(Instant at) {
        this.at = at;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public boolean isMissing() {
        return missing;
    }

    public void setMissing(boolean missing) {
        this.missing = missing;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductMovement)) return false;
        ProductMovement that = (ProductMovement) o;
        return id == that.id &&
                quantity == that.quantity &&
                missing == that.missing &&
                Objects.equals(at, that.at) &&
                Objects.equals(product, that.product) &&
                Objects.equals(location, that.location) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, at, quantity, missing, product, location, user);
    }
}
