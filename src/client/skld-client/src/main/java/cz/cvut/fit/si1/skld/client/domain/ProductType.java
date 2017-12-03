package cz.cvut.fit.si1.skld.client.domain;

import javafx.beans.property.StringProperty;

public class ProductType {
    private String id;
    private String name;

    public ProductType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductType() {
        this("", "");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
