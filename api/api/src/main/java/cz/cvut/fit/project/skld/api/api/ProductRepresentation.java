package cz.cvut.fit.project.skld.api.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.fit.project.skld.api.core.LineItem;
import cz.cvut.fit.project.skld.api.core.ProductMovement;
import cz.cvut.fit.project.skld.api.core.ProductPosition;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepresentation {
    private long id;

    private String name;

    private Map<String, Long> positions;

    private long quantity;

    public ProductRepresentation() {}

    public ProductRepresentation(long id, String name, Map<String, Long> positions) {
        this.id = id;
        this.name = name;
        this.positions = positions;
    }


    public ProductRepresentation(long id, String name, @NotNull List<ProductPosition> positions) {
        this.id = id;
        this.name = name;
        if (positions.size() > 0) {
            this.positions = new HashMap<>();
            for (ProductPosition pos : positions) {
                this.positions.put(pos.getPosition(), pos.getQuantity());
                this.quantity += pos.getQuantity();
            }
        } else {
            this.positions = null;
            this.quantity = 0;
        }
    }

    public ProductRepresentation(LineItem li) {
        this.id = li.getProduct().getId();
        this.name = li.getProduct().getName();
        this.quantity = 0;
        if (!li.getProductAllocations().isEmpty()) {
            this.positions = new HashMap<>();
        }
        for (ProductMovement pm : li.getProductAllocations()) {
            this.positions.put(pm.getLocation(), pm.getQuantity());
            this.quantity += pm.getQuantity();
        }
    }

    public ProductRepresentation(long id, String name, long quantity) {
        this.id = id;
        this.name = name;
        this.positions = null;
        this.quantity = quantity;
    }


    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public long getQuantity() {
        return quantity;
    }

    @JsonProperty
    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @JsonProperty
    public Map<String, Long> getPositions() {
        return this.positions;
    }

    @JsonProperty
    public void setPositions(Map<String, Long> positions) {
        this.positions = positions;
    }
}
