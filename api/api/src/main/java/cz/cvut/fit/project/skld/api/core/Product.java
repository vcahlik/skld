package cz.cvut.fit.project.skld.api.core;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="products")
@NamedQueries(
        {
                @NamedQuery(
                        name = "cz.cvut.fit.project.skld.api.core.Product.getAll",
                        query = "SELECT p FROM Product p"
                )
        }
)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "created_by", foreignKey = @ForeignKey(name = "FK_Product_Creator"))
    private User creator;

    public Product() {}

    public Product(String name, User creator) {}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }

        final Product that = (Product) o;

        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.creator, that.creator);
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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
