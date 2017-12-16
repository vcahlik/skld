package cz.cvut.fit.project.skld.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * Fragment uzivatelskeho rozhrani - obaluje GUI prvky obrazovky nebo jeji casti. Ve vysledku zahrnuje tlacitka, textove pole, dalsi fragmenty...
 * Schema kazdeho fragmentu je definovano souborem typu FXML (definovano v JavaFX), ktery obsahuje graficke schema zapsane v XML.
 * Kazdy fragment ma vlastni handler (v terminologii JavaFX se jedna o controller), se kterym komunikuje.
 * Handler primo zpracovava JavaFx objekty a vola jejich metody. Fragment nevola GUI logiku JavaFX objektu primo, ale pres handler.
 */
public abstract class Fragment extends Component {
    private Parent root;

    /**
     * Konstruktor
     * @param parent Rodicovska komponenta, ktera obsahuje tento fragment
     */
    public Fragment(Notifyable parent) {
        super(parent);

        Handler handler = makeHandler();
        load(handler);
    }

    /**
     * Factory metoda pro tvoreni handleru.
     * Smi byt zavolana behem zivota fragmentu pouze jednou.
     * Diky tomu muze mit kazdy fragment (implementovany zvlastni tridou dedenou z Fragment) vlastni handler (implementovany zvlastni tridou dedenou z Handler).
     * @return Nove vytvoreny handler
     */
    public abstract Handler makeHandler();

    /**
     * Inicializuje JavaFX GUI a napojuje jej na handler fragmentu.
     * @param handler Handler tohoto fragmentu
     */
    public void load(Handler handler) {
        if (root != null) {
            throw new IllegalStateException("Fragment can only be loaded once");
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(handler.getFXMLFilePath()));
        loader.setController(handler);

        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Vraci JavaFX root, kterym si rodic napoji tento fragment
     * @return JavaFX root
     */
    public Parent getRoot() {
        return root;
    }

}
