package cz.cvut.fit.si1.skld.client.components.navigator;

import cz.cvut.fit.si1.skld.client.Handler;
import cz.cvut.fit.si1.skld.client.Screen;
import cz.cvut.fit.si1.skld.client.components.navigator.navigator_bar.NavigatorBarFragment;
import cz.cvut.fit.si1.skld.client.components.session_bar.SessionBarFragment;
import cz.cvut.fit.si1.skld.client.FXMLFragment;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class NavigatorFrameHandler extends Handler {
    private NavigatorFrame owner;

    @FXML
    private HBox navigatorBar;

    @FXML
    private HBox content;

    @FXML
    private HBox sessionBar;

    public NavigatorFrameHandler() {
        super(FXMLFragment.NAVIGATOR_FRAME);
    }

    public void setOwner(NavigatorFrame owner) {
        this.owner = owner;
    }

    public void setContent(Screen screen) {
        content.getChildren().setAll((HBox)screen.getRoot());
    }

    public void setNavigatorBarFragment(NavigatorBarFragment navigatorBarFragment) {
        navigatorBar.getChildren().setAll((HBox)navigatorBarFragment.getRoot());
    }

    public void setSessionBarFragment(SessionBarFragment sessionBarFragment) {
        sessionBar.getChildren().setAll((HBox)sessionBarFragment.getRoot());
    }
}
