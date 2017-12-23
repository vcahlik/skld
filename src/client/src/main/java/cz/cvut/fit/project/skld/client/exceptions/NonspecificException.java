package cz.cvut.fit.project.skld.client.exceptions;

/**
 * Vyjimka reprezentujici neznamou chybu.
 */
public class NonspecificException extends APIException {
    public NonspecificException() {
        super("An error has ocurred. That's all we know.", -1);
    }
}
