package cz.cvut.fit.project.skld.api;

import cz.cvut.fit.project.skld.api.core.Product;
import cz.cvut.fit.project.skld.api.core.ProductMovement;
import cz.cvut.fit.project.skld.api.core.User;
import cz.cvut.fit.project.skld.api.db.ProductDAO;
import cz.cvut.fit.project.skld.api.db.UserDAO;
import cz.cvut.fit.project.skld.api.resources.ProductsResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.hibernate.HibernateBundle;

public class SKLDAPIApplication extends Application<SKLDAPIConfiguration> {

    public static void main(final String[] args) throws Exception {
        new SKLDAPIApplication().run(args);
    }

    private final HibernateBundle<SKLDAPIConfiguration> hibernateBundle =
            new HibernateBundle<SKLDAPIConfiguration>(User.class, Product.class, ProductMovement.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(SKLDAPIConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    @Override
    public String getName() {
        return "SKLDAPI";
    }

    @Override
    public void initialize(final Bootstrap<SKLDAPIConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(final SKLDAPIConfiguration configuration,
                    final Environment environment) {
        final ProductDAO productDAO = new ProductDAO(hibernateBundle.getSessionFactory());
        final UserDAO userDAO = new UserDAO(hibernateBundle.getSessionFactory());
        environment.jersey().register(new ProductsResource(productDAO, userDAO));
    }

}
