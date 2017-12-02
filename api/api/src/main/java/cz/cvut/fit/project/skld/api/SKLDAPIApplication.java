package cz.cvut.fit.project.skld.api;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class SKLDAPIApplication extends Application<SKLDAPIConfiguration> {

    public static void main(final String[] args) throws Exception {
        new SKLDAPIApplication().run(args);
    }

    @Override
    public String getName() {
        return "SKLDAPI";
    }

    @Override
    public void initialize(final Bootstrap<SKLDAPIConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final SKLDAPIConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
