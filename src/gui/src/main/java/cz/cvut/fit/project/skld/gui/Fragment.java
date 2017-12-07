package cz.cvut.fit.project.skld.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public abstract class Fragment extends Component {
    Parent root;
    Notifyable parent;

    public Fragment(Notifyable parent) {
        super(parent.getApp());

        this.parent = parent;
        Handler handler = makeHandler();
        load(handler);
    }

    public abstract Handler makeHandler();

    @Override
    public void notify(UI source, NotifyType notifyType) {

    }

    public void load(Handler handler) {
        if (root != null) {
            throw new IllegalStateException("Fragment can only be loaded once");
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(handler.getFXMLFilePath()));
        loader.setController(handler);

        try {
            root = (Parent)loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public Parent getRoot() {
        return root;
    }

    public Notifyable getParent() {
        return parent;
    }
}
