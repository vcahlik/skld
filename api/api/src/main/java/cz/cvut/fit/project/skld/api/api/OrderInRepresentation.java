package cz.cvut.fit.project.skld.api.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.fit.project.skld.api.core.LineItem;
import cz.cvut.fit.project.skld.api.core.OrderIn;
import cz.cvut.fit.project.skld.api.core.User;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderInRepresentation {
    @NotEmpty
    private long id;

    @NotEmpty
    private String supplierName;

    private Date deliveryDate;

    private Instant handledAt;

    private Instant createdAt;

    private String state;

    private User createdBy;

    private User handledBy;

    @NotEmpty
    private List<ProductRepresentation> products;

    public OrderInRepresentation() {}

    public OrderInRepresentation(OrderIn order) {
        this.id = order.getId();
        this.supplierName = order.getSupplierName();
        this.deliveryDate = order.getExpectedDelivery();
        this.createdAt = order.getCreatedAt();
        this.handledAt = order.getHandledAt();
        this.state = order.getState().toString();
        this.createdBy = order.getCreator();
        this.handledBy = order.getHandler();

        this.products = new ArrayList<ProductRepresentation>();
        for (LineItem p : order.getLineItems()) {
            products.add(new ProductRepresentation(p.getProduct().getId(), p.getProduct().getName(), p.getQuantity()));
        }
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty("supplier_name")
    public String getSupplierName() {
        return supplierName;
    }

    @JsonProperty("supplier_name")
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @JsonProperty
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    @JsonProperty
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @JsonProperty
    public List<ProductRepresentation> getProducts() {
        return products;
    }

    @JsonProperty
    public void setProducts(List<ProductRepresentation> products) {
        this.products = products;
    }

    @JsonProperty("handled_at")
    public Instant getHandledAt() {
        return handledAt;
    }

    @JsonProperty("handled_at")
    public void setHandledAt(Instant handledAt) {
        this.handledAt = handledAt;
    }

    @JsonProperty("created_at")
    public Instant getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty()
    public String getState() {
        return state;
    }

    @JsonProperty()
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("created_by")
    public User getCreatedBy() {
        return createdBy;
    }

    @JsonProperty("created_by")
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    @JsonProperty("handled_by")
    public User getHandledBy() {
        return handledBy;
    }

    @JsonProperty("handled_by")
    public void setHandledBy(User handledBy) {
        this.handledBy = handledBy;
    }
}
