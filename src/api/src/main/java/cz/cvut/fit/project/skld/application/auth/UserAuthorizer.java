package cz.cvut.fit.project.skld.application.auth;

import cz.cvut.fit.project.skld.application.core.User;
import io.dropwizard.auth.Authorizer;

/**
 * Implementace overeni, zda ma uzivatel prava k provedeni nejake akce.
 */
public class UserAuthorizer implements Authorizer<User> {
    /**
     * Zjisti, zda ma uzivatel specifikovane minimalni opravneni.
     * @param user Overovany uzivatel
     * @param role Role, kterou uzivatel musi mit k provedeni prislusne operace
     * @return
     */
    @Override
    public boolean authorize(User user, String role) {
        if (role.equals("user")) {
            return true;
        } else if (role.equals("admin")) {
            return user.isAdmin();
        } else {
            throw new IllegalArgumentException("Trying to authorize an unknown role");
        }
    }
}
