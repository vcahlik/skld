package cz.cvut.fit.project.skld.representations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/***
 * Obsahuje veskere detaily nejakeho produktu.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductRepresentation {
    private long id;

    private String name;

    private Map<String, Long> positions;

    private Long quantity;

    /**
     * Konstruktor.
     */
    public ProductRepresentation() {}

    /**
     * Konstruktor.
     * @param id ID
     * @param name Nazev
     * @param positions Mapa obsahujici nazvy pozic a mnozstvi produktu ulozenych v techto pozicich
     */
    public ProductRepresentation(long id, String name, Map<String, Long> positions) {
        this.id = id;
        this.name = name;
        this.positions = positions;
        //this.quantity = 0L;
    }

    /**
     * Konstruktor.
     * @param id ID
     * @param name Nazev
     * @param quantity Mnozstvi produktu
     */
    public ProductRepresentation(long id, String name, long quantity) {
        this.id = id;
        this.name = name;
        this.positions = null;
        this.quantity = quantity;
    }

    /**
     * Konstruktor.
     * @param id ID
     * @param quantity Mnozstvi produktu
     */
    public ProductRepresentation(long id, long quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductRepresentation that = (ProductRepresentation) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
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
    public Long getQuantity() {
        return quantity;
    }

    @JsonProperty
    public void setQuantity(Long quantity) {
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
