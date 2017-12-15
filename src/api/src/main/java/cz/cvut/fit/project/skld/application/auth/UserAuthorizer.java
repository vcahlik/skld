package cz.cvut.fit.project.skld.application.auth;

import cz.cvut.fit.project.skld.application.core.User;
import io.dropwizard.auth.Authorizer;

/***
 * Implements checking whether user has the role necessary to perform an action.
 */
public class UserAuthorizer implements Authorizer<User> {
    /***
     * Checks whether user has the requested role.
     * @param user the user that should be checked
     * @param role the role that the user must have to be authorized.
     *             Currently only "admin" and "user" are supported, controlled by the isAdmin field on User.
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
