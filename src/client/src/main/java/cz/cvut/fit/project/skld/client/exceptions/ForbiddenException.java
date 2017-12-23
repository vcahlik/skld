package cz.cvut.fit.project.skld.client.exceptions;

/**
 * Vyjimka reprezentujici chybu zpusobenou nedostatecnymi pravy pro provedeni operace.
 */
public class ForbiddenException extends APIException {
    public ForbiddenException() {
        super("You do not have sufficient privileges to perform this operation.", 403);
    }
}
