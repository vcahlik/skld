package cz.cvut.fit.project.skld.representations;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/***
 * A login request, containing user's PIN code.
 */
public class PIN {
    private String pin;

    public PIN() {}

    public PIN(String pin) {
        this.pin = pin;
    }

    @JsonProperty("PIN")
    @NotNull
    public String getPin() {
        return pin;
    }

    @JsonProperty("PIN")
    public void setPin(String pin) {
        this.pin = pin;
    }

}
