package cz.cvut.fit.project.skld.gui;

/**
 * Rozhrani objektu, kteremu muze byt predano rizeni. Ma vyznam zejmena u obrazovek, ktere se predanim rizeni zobrazi misto puvodni.
 * Takovym objektem je obvykle kontejner primitivnich prvku GUI (tlacitek, formularu), ktery se stara o komunikaci mezi nimi.
 */
public interface Passable extends Notifyable {
    /**
     * Predava rizeni dcerinnemu objektu.
     * Predani rizeni znamena, ze objekt je plne aktivovan a pripraven zpracovat uzivatelska data. Obvykle to neznamena, ze by rodicovsky objekt prisel o rizeni.
     */
    void follow();

    /**
     * Metoda, kterou dcerinny objekt vraci rizeni rodicovskemu objektu.
     * Vola ji dcerinny objekt nad rodicovskym objektem, obvykle pote, co dokonci pozadavek, pro ktery byl vytvoren. Obvykle to znamena, ze rodic muze dcerinny objekt zahodit.
     * @param source Dcerinny objekt
     * @param result Vysledek provadeni operaci dcerinneho objektu
     */
    void pass(UI source, PassResult result);

    /**
     * Pozaduje po rodicovskem objektu zmenu obrazovky.
     * @param screen Nova obrazovka
     */
    void changeContent(Screen screen);

}
