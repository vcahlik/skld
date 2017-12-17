package cz.cvut.fit.project.skld.gui.components.navigator;

import cz.cvut.fit.project.skld.gui.FXMLFragmentType;
import cz.cvut.fit.project.skld.gui.Handler;
import cz.cvut.fit.project.skld.gui.Screen;
import cz.cvut.fit.project.skld.gui.components.navigator.navigator_bar.NavigatorBarFragment;
import cz.cvut.fit.project.skld.gui.components.session_bar.SessionBarFragment;
import cz.cvut.fit.project.skld.gui.util.FXUtil;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * Handler pro NavigatorFrame.
 */
public class NavigatorFrameHandler extends Handler {
    private NavigatorFrame owner;

    @FXML
    private HBox navigatorBar;

    @FXML
    private AnchorPane content;

    @FXML
    private HBox sessionBar;

    /**
     * Konstruktor.
     */
    public NavigatorFrameHandler() {
        super(FXMLFragmentType.NAVIGATOR_FRAME);
    }

    /**
     * Pripoji k handleru prislusny frame.
     * @param owner Fragment
     */
    public void setOwner(NavigatorFrame owner) {
        this.owner = owner;
    }

    /**
     * Zmeni aktualne zobrazeny screen.
     * @param screen Novy screen
     */
    public void setContent(Screen screen) {
        AnchorPane pane = (AnchorPane) screen.getRoot();
        FXUtil.setAnchor(pane, 0.0, 0.0, 0.0, 0.0);
        content.getChildren().setAll(pane);
    }

    /**
     * Nastavi fragment s navigacni listou.
     * @param navigatorBarFragment Fragment s navigacni listou
     */
    public void setNavigatorBarFragment(NavigatorBarFragment navigatorBarFragment) {
        navigatorBar.getChildren().setAll((HBox)navigatorBarFragment.getRoot());
    }

    /**
     * Nastavi SessionBarFragment s informacemi o uzivateli.
     * @param sessionBarFragment SessionBarFragment
     */
    public void setSessionBarFragment(SessionBarFragment sessionBarFragment) {
        sessionBar.getChildren().setAll((HBox)sessionBarFragment.getRoot());
    }
}
