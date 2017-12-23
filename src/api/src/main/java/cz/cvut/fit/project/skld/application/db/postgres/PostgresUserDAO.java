package cz.cvut.fit.project.skld.application.db.postgres;

import cz.cvut.fit.project.skld.application.core.User;
import cz.cvut.fit.project.skld.application.db.UserDAO;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

/**
 * Implementuje zakladni databazove operace nad User objekty.
 */
public class PostgresUserDAO extends AbstractDAO<User>implements UserDAO {
    /**
     * Konstruktor.
     * @param factory Factory specifikovana v Dropwizard frameworku
     */
    public PostgresUserDAO(SessionFactory factory) {
        super(factory);
    }

    /**
     * Vraci uzivatele se zadanym ID.
     * @param id ID uzivatele
     * @return Uzivatel
     */
    @Override public Optional<User> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    /**
     * Vraci uzivatele, ktery se prihlasuje zadanym PINem.
     * @param pin PIN kod
     * @return Uzivatel
     */
    @Override public Optional<User> findByPin(String pin) {
        Query<User> q = namedQuery("User.findByPIN");
        q.setParameter("pin", pin);
        List<User> found = list(q);
        if (found.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(found.get(0));
        }
    }

    /**
     * Vlozi do databaze noveho uzivatele.
     * @param user Uzivatel
     * @return Uzivatel (vcetne pripadnych automaticky generovanych hodnot)
     */
    @Override public User create(User user) {
        return persist(user);
    }

}
