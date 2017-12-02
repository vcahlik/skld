package cz.cvut.fit.project.skld.api.auth;

import cz.cvut.fit.project.skld.api.core.User;
import io.dropwizard.auth.Authorizer;

public class UserAuthorizer implements Authorizer<User> {
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
