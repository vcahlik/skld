package cz.cvut.fit.project.skld.api.core;

public enum OrderState {
    OPEN, CLOSED, REFUSED;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
