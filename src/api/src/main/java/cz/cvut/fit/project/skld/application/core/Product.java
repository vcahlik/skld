package cz.cvut.fit.project.skld.application.core;

import javax.persistence.*;
import java.util.Objects;

/**
 * Reprezentuje produkt z domenoveho modelu.
 */
@Entity
@Table(name="products")
@NamedQueries({
    @NamedQuery(
        name = "Product.findAll",
        query = "SELECT p FROM Product p"
    )
})
public class Product {
    @Id
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "created_by", foreignKey = @ForeignKey(name = "FK_Product_Creator"), nullable = false)
    private User creator;

    /**
     * Konstruktor.
     */
    public Product() {}

    /**
     * Konstruktor.
     * @param id ID
     * @param name Nazv produktu
     * @param creator Uzivatel, ktery produkt zadal do systemu
     */
    public Product(long id, String name, User creator) {
        this.id = id;
        this.name = name;
        this.creator = creator;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id == product.id &&
                Objects.equals(name, product.name) &&
                Objects.equals(creator, product.creator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, creator);
    }
}
