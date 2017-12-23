package cz.cvut.fit.project.skld.representations;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/***
 * Pozadavek na prihlaseni, obsahujici uzivateluv PIN.
 */
public class PIN {
    private String pin;

    /**
     * Konstruktor.
     */
    public PIN() {}

    /**
     * Konstruktor.
     * @param pin PIN prihlasovaneho uzivatele
     */
    public PIN(String pin) {
        this.pin = pin;
    }

    /**
     * Vraci PIN.
     * @return PIN
     */
    @JsonProperty("PIN")
    @NotNull
    public String getPin() {
        return pin;
    }

    /**
     * Nastavi PIN.
     * @param pin PIN
     */
    @JsonProperty("PIN")
    public void setPin(String pin) {
        this.pin = pin;
    }

}
