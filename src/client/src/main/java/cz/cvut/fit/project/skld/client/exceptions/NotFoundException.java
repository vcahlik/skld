package cz.cvut.fit.project.skld.client.exceptions;

/**
 * Vyjimka reprezentujici nenalezeni pozadovaneho objektu.
 */
public class NotFoundException extends APIException {
    public NotFoundException() {
        super("Entity Not Found", 404);
    }
}
