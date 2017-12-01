package cz.cvut.fit.si1.skld.client;

import cz.cvut.fit.si1.skld.client.domain.User;

public class Session {
    private User signedUser;

    public void logIn(User user) {
        this.signedUser = user;
    }

    public void logOut() {
        this.signedUser = null;
    }

}
