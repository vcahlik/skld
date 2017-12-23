package cz.cvut.fit.project.skld.application.db;

import cz.cvut.fit.project.skld.application.core.User;

import java.util.Optional;

/**
 * Implementuje zakladni databazove operace nad User objekty.
 */
public interface UserDAO {
    /**
     * Vraci uzivatele se zadanym ID.
     * @param id ID uzivatele
     * @return Uzivatel
     */
    Optional<User> findById(Long id);

    /**
     * Vraci uzivatele, ktery se prihlasuje zadanym PINem.
     * @param pin PIN kod
     * @return Uzivatel
     */
    Optional<User> findByPin(String pin);

    /**
     * Vlozi do databaze noveho uzivatele.
     * @param user Uzivatel
     * @return Uzivatel (vcetne pripadnych automaticky generovanych hodnot)
     */
    User create(User user);
}
