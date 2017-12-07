package cz.cvut.fit.project.skld.representations;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderInChange {
    private long id;

    private String supplierName;

    private Date deliveryDate;

    private List<ProductRepresentation> products;

    public OrderInChange() {}

    public OrderInChange(long id, String supplierName) {
        this.id = id;
        this.supplierName = supplierName;
        this.products = new ArrayList<>();
    }

    @JsonProperty
    @NotNull
    public long getId() {
        return id;
    }

    @JsonProperty
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty("supplier_name")
    @NotNull
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
    @NotNull
    public List<ProductRepresentation> getProducts() {
        return products;
    }

    @JsonProperty
    public void setProducts(List<ProductRepresentation> products) {
        this.products = products;
    }
}
