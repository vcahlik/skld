package cz.cvut.fit.project.skld.representations;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 * A response with full details of an Order In.
 */
public class OrderInRepresentation {
    private long id;

    private String supplierName;

    private Date deliveryDate;

    private Instant handledAt;

    private Instant createdAt;

    private String state;

    private UserRepresentation createdBy;

    private UserRepresentation handledBy;

    private List<ProductRepresentation> products;

    public OrderInRepresentation() {}

    public OrderInRepresentation(long id, String supplierName) {
        this.id = id;
        this.supplierName = supplierName;
        this.state = "OPEN";
        this.products = new ArrayList<>();
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
    public UserRepresentation getCreatedBy() {
        return createdBy;
    }

    @JsonProperty("created_by")
    public void setCreatedBy(UserRepresentation createdBy) {
        this.createdBy = createdBy;
    }

    @JsonProperty("handled_by")
    public UserRepresentation getHandledBy() {
        return handledBy;
    }

    @JsonProperty("handled_by")
    public void setHandledBy(UserRepresentation handledBy) {
        this.handledBy = handledBy;
    }
}
