package cz.cvut.fit.si1.skld.client.model;

import cz.cvut.fit.si1.skld.client.model.domain.User;
import cz.cvut.fit.si1.skld.client.view.View;

public class Model {
    private User signedUser;

    public void logIn(User user) {
        this.signedUser = user;
    }

    public void logOut() {
        this.signedUser = null;
    }

}
