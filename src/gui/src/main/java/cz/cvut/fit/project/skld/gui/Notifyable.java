package cz.cvut.fit.project.skld.gui;

/**
 * Rozhrani objektu, kteremu muze byt odeslano oznameni o nejake udalosti. Obvykle jedine rozhrani, kterym komunikuji vestavene graficke komponenty s rodici.
 * Vestavene komponenty pomoci nej obvykle odeslou rodici oznameni, ze u nich doslo ke zmene. Ten si dalsi podrobnosti jiz musi sam nacist z dcerinnych komponent.
 */
public interface Notifyable {
    /**
     * Oznami objektu, ze doslo k nejake udalosti. Zavolana na objektu, ktery je notifikovan.
     * @param source Objekt, ktery odeslal notifikaci
     * @param notifyType Typ notifikace
     */
    void notify(UI source, NotifyType notifyType);

    /**
     * Vraci referenci na celou aplikaci.
     * @return Aplikace
     */
    App getApp();

}
