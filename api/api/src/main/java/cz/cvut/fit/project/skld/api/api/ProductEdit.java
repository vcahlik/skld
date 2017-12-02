package cz.cvut.fit.project.skld.api.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class ProductEdit {
    @NotEmpty
    private String name;

    public ProductEdit() {}

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }

}
