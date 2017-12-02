package cz.cvut.fit.project.skld.api.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.fit.project.skld.api.core.ProductPosition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepresentation {
    private long id;

    private String name;

    private List<ProductPosition> positions;

    public ProductRepresentation(long id, String name, List<ProductPosition> positions) {
        this.id = id;
        this.name = name;
        this.positions = positions;
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
        return positions.stream().mapToLong(ProductPosition::getQuantity).sum();
    }

    @JsonProperty
    public Map<String, Long> getPositions() {
        Map<String, Long> p = new HashMap<>();
        for (ProductPosition pos : positions) {
            p.put(pos.getPosition(), pos.getQuantity());
        }
        return p;
    }
}
