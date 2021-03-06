package cz.cvut.fit.project.skld.application.core;


import java.util.Objects;

/**
 * Pomocna trida, ktera popisuje urcite mnozstvi nejakeho (zde nespecifikovaneho) produktu na specifikovanem miste.
 */
public class ProductPosition {
    private String position;
    private long quantity;

    /**
     * Konstruktor.
     * @param position Pozice
     * @param quantity Mnozstvi produktu
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductPosition)) return false;
        ProductPosition that = (ProductPosition) o;
        return quantity == that.quantity &&
                Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {

        return Objects.hash(position, quantity);
    }
}
