package cz.cvut.fit.project.skld.api;

import cz.cvut.fit.project.skld.api.core.User;
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
            new HibernateBundle<SKLDAPIConfiguration>(User.class) {
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
        // TODO: implement application
    }

}
