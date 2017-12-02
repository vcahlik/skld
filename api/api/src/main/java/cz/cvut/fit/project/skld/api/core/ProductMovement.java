package cz.cvut.fit.project.skld.api.core;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

public class ProductMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Instant at;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private boolean missing;

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private String location;

    @ManyToOne
    private User user;

    public ProductMovement() {}

    public ProductMovement(Product product, int qty, String destination) {
        this.product = product;
        quantity = qty;
        location = destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductMovement)) {
            return false;
        }

        final ProductMovement that = (ProductMovement) o;

        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.at, that.at) &&
                Objects.equals(this.quantity, that.quantity) &&
                Objects.equals(this.missing, that.missing) &&
                Objects.equals(this.product, that.product) &&
                Objects.equals(this.user, that.user);
    }
}
