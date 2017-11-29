package cz.cvut.fit.si1.skld.client.controller;

import cz.cvut.fit.si1.skld.client.model.Model;
import cz.cvut.fit.si1.skld.client.view.AppScreen;
import cz.cvut.fit.si1.skld.client.view.View;

public class AppController implements Controller {
    private Model model;
    private View view;

    public AppController(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public Model getModel() {
        return model;
    }

    public void changeScreen(AppScreen screen) {
        view.changeScreen(screen);
    }
}
