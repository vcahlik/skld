package cz.cvut.fit.project.skld.application.core;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/***
 * Represents an Order In from the domain model.
 */
@NamedQueries({
        @NamedQuery(
                name = "OrderIn.findAll",
                query = "SELECT oi FROM OrderIn oi"
        )
})
@Entity(name = "OrderIn")
@Table(name = "order_ins")
public class OrderIn extends Order {
    @Column(name = "supplier_name", nullable = false)
    private String supplierName;

    @Column(name = "expected_delivery")
    private Date expectedDelivery;

    public OrderIn() {}

    public OrderIn(long id, User creator, String supplierName) {
        super(id, creator);
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Date getExpectedDelivery() {
        return expectedDelivery;
    }

    public void setExpectedDelivery(Date expectedDelivery) {
        this.expectedDelivery = expectedDelivery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderIn)) return false;
        if (!super.equals(o)) return false;
        OrderIn orderIn = (OrderIn) o;
        return Objects.equals(supplierName, orderIn.supplierName) &&
                Objects.equals(expectedDelivery, orderIn.expectedDelivery);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), supplierName, expectedDelivery);
    }
}
