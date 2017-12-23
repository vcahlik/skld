package cz.cvut.fit.project.skld.representations;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/***
 * Reprezentuje zmenu provadenou v nejakem produktu.
 */
public class ProductChange {
    private String name;

    private Long id;

    /**
     * Konstruktor.
     */
    public ProductChange() {}

    /**
     * Konstruktor.
     * @param id ID
     * @param name Nazev produktu
     */
    public ProductChange(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonProperty
    @NotNull
    public String getName() {
        return name;
    }

    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    @NotNull
    public Long getId() {
        return id;
    }

    @JsonProperty
    public void setId(Long id) {
        this.id = id;
    }
}
