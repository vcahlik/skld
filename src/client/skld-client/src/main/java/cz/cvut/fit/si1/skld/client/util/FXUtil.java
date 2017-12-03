package cz.cvut.fit.si1.skld.client.util;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class FXUtil {
    public static void setAnchor(Node node, double top, double right, double bottom, double left) {
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
    }
}
