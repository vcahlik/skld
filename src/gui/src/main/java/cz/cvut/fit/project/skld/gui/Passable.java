package cz.cvut.fit.project.skld.gui;

/**
 * Rozhrani objektu, kteremu muze byt predano rizeni.
 * Takovym objektem je obvykle kontejner primitivnich prvku GUI (tlacitek, formularu), ktery se stara o komunikaci mezi nimi.
 */
public interface Passable extends Notifyable {
    /**
     * Predava rizeni dcerinnemu objektu.
     */
    void follow();

    /**
     * Vraci rizeni rodicovskemu objektu.
     * @param source Dcerinny
     * @param result
     */
    void pass(UI source, PassResult result);

    /**
     * Pozaduje po rodicovskem objektu zmenu obrazovky.
     * @param screen
     */
    void changeContent(Screen screen);

}
