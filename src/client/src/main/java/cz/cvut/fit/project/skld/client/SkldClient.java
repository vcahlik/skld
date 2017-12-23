package cz.cvut.fit.project.skld.client;

import cz.cvut.fit.project.skld.client.exceptions.APIException;
import cz.cvut.fit.project.skld.representations.*;

import java.io.IOException;
import java.util.List;

/**
 * Interface pro prezentacni vrstvu klienta, ktery diky nemu nemusi komunikovat se serverem primo.
 * Interface nevystavuje metodu pro prihlaseni. Instance datove vrstvy ma misto toho byt rovnou vytvorena jiz s prihlasenym uzivatelem, diky cemuz si rovnou uchovava prihlasovaci tokeny.
 */
public interface SkldClient {

    /**
     * Vraci prave prihlaseneho uzivatele, tedy toho, ktery provadi veskere operace volane pres tuto instanci. Uzivatel zustava stejny po celou dobu zivota instance.
     * @return Prihlaseny uzivatel
     */
    UserRepresentation getLoggedInUser();

    /**
     * Vraci seznam vsech logistickych objednavek v systemu.
     * @return Seznam logistickych objednavek
     */
    List<OrderInRepresentation> getOrderIns() throws IOException, APIException;

    /**
     * Vytvori v systemu novou objednavku podle vyplnenych poli v OrderInChange.
     * Muze byt volana jen uzivatelem s administratorskymi pravy (vedouci smeny).
     * @param order Objekt reprezentujici parametry nove objednavky
     * @return Vytvorena logisticka objednavka
     */
    OrderInRepresentation createOrderIn(OrderInChange order) throws IOException, APIException;

    /**
     * Vraci reprezentaci logisticke objednavky s pozadovanym ID.
     * @param id ID logisticke objednavky
     * @return Logisticka objednavka
     */
    OrderInRepresentation getOrderIn(long id) throws IOException, APIException;

    /**
     * Zmeni logistickou objednavku podle vyplnenych poli v OrderInChange.
     * @param order Objekt reprezentujici parametry zmenene objednavky (zmenena budou veskera pole, nejen ta vyplnena)
     * @return Zmenena logisticka objednavka
     */
    OrderInRepresentation updateOrderIn(OrderInChange order) throws IOException, APIException;

    /**
     * Nastavi status dane logisticke objednavky jako uzavreny. V budoucnu rovnou zada do systemu pozice predanych produktu.
     * @param order Uzavirana logisticka objednavka
     * @return Uzavrena logisticka objednavka
     */
    OrderInRepresentation closeOrder(OrderInRepresentation order) throws IOException, APIException;

    /**
     * Nastavi status dane logisticke objednavky jako odmitnuty.
     * @param id ID odmitane logisticke objednavky
     * @return Odmitnuta logisticka objednavka
     */
    OrderInRepresentation refuseOrder(long id) throws IOException, APIException;

    /**
     * Vraci seznam vsech produktu v systemu.
     * @return Seznam produktu
     */
    List<ProductRepresentation> getProducts() throws IOException, APIException;

    /**
     * Prida do systemu novy produkt.
     * @param product Objekt obsahujici parametry noveho produktu
     * @return Vytvoreny produkt
     */
    ProductRepresentation createProduct(ProductChange product) throws IOException, APIException;

    /**
     * Vraci produkt s pozadovanym ID.
     * @param id ID produktu
     * @return Produkt
     */
    ProductRepresentation getProduct(long id) throws IOException, APIException;

    /**
     * Zmeni produkt podle vyplnenych v ProductChange. V soucasnosti umoznuje pouze zmenu nazvu produktu.
     * @param edit Objekt reprezentujici parametry zmeneneho produktu (zmenena budou veskera pole, nejen ta vyplnena)
     * @return Zmeneny produkt
     */
    ProductRepresentation changeProduct(ProductChange edit) throws IOException, APIException;
}
