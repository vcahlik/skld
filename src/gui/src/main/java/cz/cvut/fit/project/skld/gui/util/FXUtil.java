package cz.cvut.fit.project.skld.gui.util;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

/**
 * Trida obsahujici pomocne metody pro praci s knihovnou JavaFX.
 */
public class FXUtil {
    /**
     * Ukotvi node na rozmery sveho rodice.
     * @param node Ukotvovany objekt (obvykle AnchorPane)
     * @param top Margin odshora
     * @param right Margin zprava
     * @param bottom Margin odspodu
     * @param left Margin zleva
     */
    public static void setAnchor(Node node, double top, double right, double bottom, double left) {
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
    }

    /**
     * Zobrazi uzivateli okno se zpravou/chybou.
     * @param title Text v zahlavi okna
     * @param text Zprava uzivateli
     */
    public static void displayAlert(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(text);
        alert.show();
    }
}
