package cz.cvut.fit.project.skld.api.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class PIN {
    @NotEmpty
    private String pin;

    public PIN() {}

    @JsonProperty("PIN")
    public String getPin() {
        return pin;
    }

    @JsonProperty("PIN")
    public void setPin(String pin) {
        this.pin = pin;
    }

}
