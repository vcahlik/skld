package cz.cvut.fit.project.skld.application.core;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="line_items")
public class LineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long quantity;

    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "FK_Line_item_product"))
    private Product product;

    @OneToMany(orphanRemoval = true)
    @JoinTable(name = "line_item_allocations", joinColumns = {@JoinColumn(name = "line_item_id")}, inverseJoinColumns = {@JoinColumn(name = "product_movement_id")})
    private List<ProductMovement> productAllocations;

    @ManyToOne
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "FK_Line_item_order"))
    private Order order;

    public LineItem() {}

    public LineItem(long quantity, Product product, Order order) {
        this.quantity = quantity;
        this.product = product;
        this.order = order;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<ProductMovement> getProductAllocations() {
        return productAllocations;
    }

    public void setProductAllocations(List<ProductMovement> productAllocations) {
        this.productAllocations = productAllocations;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LineItem)) return false;
        LineItem lineItem = (LineItem) o;
        return id == lineItem.id &&
                quantity == lineItem.quantity &&
                Objects.equals(product, lineItem.product) &&
                Objects.equals(productAllocations, lineItem.productAllocations) &&
                Objects.equals(order, lineItem.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, product, productAllocations, order);
    }
}
