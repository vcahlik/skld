package cz.cvut.fit.project.skld.client.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import cz.cvut.fit.project.skld.client.SkldClient;
import cz.cvut.fit.project.skld.client.exceptions.APIException;
import cz.cvut.fit.project.skld.representations.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * Objekt reprezentujici datovou vrstvu aplikace. Zalozen na komunikaci se serverem pres REST API pomoci HTTP.
 */
public class SkldHttpClient implements SkldClient {
    final LogInDetails login;
    final Executor executor;
    final ObjectMapper mapper;
    final String baseURL;
    private String authHeader;

    /**
     * Vytvori instanci s prihlasenym uzivatelem. Pri neplatnych prihlasovacich udajich vyvola UnauthorizedException
     * @param login Prihlasovaci udaje uzivatele
     * @param baseURL URL serveru
     */
    public SkldHttpClient(LogInDetails login, String baseURL) {
        this.login = login;
        this.executor = Executor.newInstance();
        this.baseURL = baseURL;
        this.authHeader = String.format("Bearer %s", login.getToken());
        this.mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    /**
     * Vraci prave prihlaseneho uzivatele, tedy toho, ktery provadi veskere operace volane pres tuto instanci. Uzivatel zustava stejny po celou dobu zivota instance.
     * @return Prihlaseny uzivatel
     */
    @Override
    public UserRepresentation getLoggedInUser() {
        return login.getUser();
    }

    /**
     * Vraci seznam vsech logistickych objednavek v systemu.
     * @return Seznam logistickych objednavek
     */
    @Override
    public List<OrderInRepresentation> getOrderIns() throws IOException, APIException {
        return (List<OrderInRepresentation>)
                performRequest(HTTPMethod.GET, "/orders/in", null, new TypeReference<List<OrderInRepresentation>>() {});
    }

    /**
     * Vytvori v systemu novou objednavku podle vyplnenych poli v OrderInChange.
     * Muze byt volana jen uzivatelem s administratorskymi pravy (vedouci smeny).
     * @param order Objekt reprezentujici parametry nove objednavky
     * @return Vytvorena logisticka objednavka
     */
    @Override
    public OrderInRepresentation createOrderIn(OrderInChange order) throws IOException, APIException {
        return (OrderInRepresentation)
                performRequest(HTTPMethod.POST, "/orders/in", order, new TypeReference<OrderInRepresentation>() {});
    }

    /**
     * Vraci reprezentaci logisticke objednavky s pozadovanym ID.
     * @param id ID logisticke objednavky
     * @return Logisticka objednavka
     */
    @Override
    public OrderInRepresentation getOrderIn(long id) throws IOException, APIException {
        return (OrderInRepresentation)
                performRequest(HTTPMethod.GET, String.format("/orders/in/%d", id), null, new TypeReference<OrderInRepresentation>() {});
    }

    /**
     * Zmeni logistickou objednavku podle vyplnenych poli v OrderInChange.
     * @param order Objekt reprezentujici parametry zmenene objednavky (zmenena budou veskera pole, nejen ta vyplnena)
     * @return Zmenena logisticka objednavka
     */
    @Override
    public OrderInRepresentation updateOrderIn(OrderInChange order) throws IOException, APIException {
        return (OrderInRepresentation)
                performRequest(HTTPMethod.PUT, String.format("/orders/in/%d", order.getId()), order, new TypeReference<OrderInRepresentation>() {});
    }

    /**
     * Nastavi status dane logisticke objednavky jako uzavreny. V budoucnu rovnou zada do systemu pozice predanych produktu.
     * @param order Uzavirana logisticka objednavka
     * @return Uzavrena logisticka objednavka
     */
    @Override
    public OrderInRepresentation closeOrder(OrderInRepresentation order) throws IOException, APIException {
        return (OrderInRepresentation)
                performRequest(HTTPMethod.POST, String.format("/orders/in/%d/close", order.getId()), order, new TypeReference<OrderInRepresentation>() {});
    }

    /**
     * Nastavi status dane logisticke objednavky jako odmitnuty.
     * @param id ID odmitane logisticke objednavky
     * @return Odmitnuta logisticka objednavka
     */
    @Override
    public OrderInRepresentation refuseOrder(long id) throws IOException, APIException {
        return (OrderInRepresentation)
                performRequest(HTTPMethod.POST, String.format("/orders/in/%d/refuse", id), null, new TypeReference<OrderInRepresentation>() {});
    }

    /**
     * Vraci seznam vsech produktu v systemu.
     * @return Seznam produktu
     */
    @Override
    public List<ProductRepresentation> getProducts() throws IOException, APIException {
        return (List<ProductRepresentation>)
                performRequest(HTTPMethod.GET, "/products", null, new TypeReference<List<ProductRepresentation>>() {});
    }

    /**
     * Prida do systemu novy produkt.
     * @param product Objekt obsahujici parametry noveho produktu
     * @return Vytvoreny produkt
     */
    @Override
    public ProductRepresentation createProduct(ProductChange product) throws IOException, APIException {
        return (ProductRepresentation)
                performRequest(HTTPMethod.POST, "/products", product, new TypeReference<ProductRepresentation>() {});
    }

    /**
     * Vraci produkt s pozadovanym ID.
     * @param id ID produktu
     * @return Produkt
     */
    @Override
    public ProductRepresentation getProduct(long id) throws IOException, APIException {
        return (ProductRepresentation)
                performRequest(HTTPMethod.GET, String.format("/products/%d", id), null, new TypeReference<ProductRepresentation>() {});
    }

    /**
     * Zmeni produkt podle vyplnenych v ProductChange. V soucasnosti umoznuje pouze zmenu nazvu produktu.
     * @param edit Objekt reprezentujici parametry zmeneneho produktu (zmenena budou veskera pole, nejen ta vyplnena)
     * @return Zmeneny produkt
     */
    @Override
    public ProductRepresentation changeProduct(ProductChange edit) throws IOException, APIException {
        return (ProductRepresentation)
                performRequest(HTTPMethod.PUT, String.format("/products/%d", edit.getId()), edit, new TypeReference<ProductRepresentation>() {});
    }

    private <T> Object performRequest(HTTPMethod method, String path, T requestBody, TypeReference responseClass) throws IOException, APIException {
        Request r = method.requestForMethod(baseURL+path);
        r.addHeader("Authorization", authHeader);
        if (requestBody != null) {
            r.body(new ByteArrayEntity(mapper.writeValueAsBytes(requestBody), ContentType.APPLICATION_JSON));
        }
        HttpResponse resp = executor.execute(r).returnResponse();
        int statusCode = resp.getStatusLine().getStatusCode();
        if (statusCode >= 400) {
            throw APIException.forCode(statusCode);
        }
        return mapper.readValue(EntityUtils.toByteArray(resp.getEntity()), responseClass);
    }

    /**
     * Autentikuje se (se serverem) pomoci predaneho PINu a pri uspechu vraci vytvorenou instanci datove vsrtvy klienta.
     * @param baseURL URL serveru
     * @param pin PIN kod prihalsovaneho uzivatele
     * @return Instance SkldHttpClienta
     * @throws IOException Neznamy PIN
     */
    public static SkldHttpClient getClientForPIN(String baseURL, String pin) throws IOException, APIException {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        HttpResponse res = Request
                .Post(baseURL + "/log/in")
                .body(new ByteArrayEntity(mapper.writeValueAsBytes(new PIN(pin)), ContentType.APPLICATION_JSON))
                .execute()
                .returnResponse();

        int statusCode = res.getStatusLine().getStatusCode();
        if (statusCode >= 400) {
            throw APIException.forCode(statusCode);
        }
        return new SkldHttpClient(mapper.readValue(EntityUtils.toByteArray(res.getEntity()), LogInDetails.class), baseURL);
    }
}
