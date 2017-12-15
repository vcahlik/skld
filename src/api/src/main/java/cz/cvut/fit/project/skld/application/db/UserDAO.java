package cz.cvut.fit.project.skld.application.db;

import cz.cvut.fit.project.skld.application.core.User;

import java.util.Optional;

/***
 * Implements basic database operations over user objects.
 */
public interface UserDAO {
    /***
     * Get the user with the given ID.
     * @param id the ID of the searched-for user.
     * @return optionally the user that's in the database under the given ID
     */
    Optional<User> findById(Long id);

    /***
     * Get the user which can sign in using the given PIN code
     * @param pin PIN code according to which to get the user
     * @return optionally the user that can sign in using pin.
     */
    Optional<User> findByPin(String pin);

    /***
     * Insert a new user into the database.
     * @param user the user to insert
     * @return inserted user, with its generated fields filled-out.
     */
    User create(User user);
}
