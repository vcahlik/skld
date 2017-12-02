package cz.cvut.fit.project.skld.api.core;


import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

public class ProductPosition {
    private String position;
    private long quantity;

    public ProductPosition(String position, long quantity) {
        this.setPosition(position);
        this.setQuantity(quantity);
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
