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

public class SkldHttpClient implements SkldClient {
    final LogInDetails login;
    final Executor executor;
    final ObjectMapper mapper;
    final String baseURL;
    private String authHeader;

    public SkldHttpClient(LogInDetails login, String baseURL) {
        this.login = login;
        this.executor = Executor.newInstance();
        this.baseURL = baseURL;
        this.authHeader = String.format("Bearer %s", login.getToken());
        this.mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    public UserRepresentation getLoggedInUser() {
        return login.getUser();
    }

    public List<OrderInRepresentation> getOrderIns() throws IOException, APIException {
        return (List<OrderInRepresentation>)
                performRequest(HTTPMethod.GET, "/orders/in", null, new TypeReference<List<OrderInRepresentation>>() {});
    }

    public OrderInRepresentation createOrderIn(OrderInRepresentation order) throws IOException, APIException {
        return (OrderInRepresentation)
                performRequest(HTTPMethod.POST, "/orders/in", order, new TypeReference<OrderInRepresentation>() {});
    }

    public OrderInRepresentation getOrderIn(long id) throws IOException, APIException {
        return (OrderInRepresentation)
                performRequest(HTTPMethod.GET, String.format("/orders/in/%d", id), null, new TypeReference<OrderInRepresentation>() {});
    }

    public OrderInRepresentation updateOrderIn(OrderInRepresentation order) throws IOException, APIException {
        return (OrderInRepresentation)
                performRequest(HTTPMethod.PUT, String.format("/orders/in/%d", order.getId()), order, new TypeReference<OrderInRepresentation>() {});
    }

    public OrderInRepresentation closeOrder(OrderInRepresentation order) throws IOException, APIException {
        return (OrderInRepresentation)
                performRequest(HTTPMethod.POST, String.format("/orders/in/%d/close", order.getId()), order, new TypeReference<OrderInRepresentation>() {});
    }

    public OrderInRepresentation refuseOrder(long id) throws IOException, APIException {
        return (OrderInRepresentation)
                performRequest(HTTPMethod.POST, String.format("/orders/in/%d/refuse", id), null, new TypeReference<OrderInRepresentation>() {});
    }

    public List<ProductRepresentation> getProducts() throws IOException, APIException {
        return (List<ProductRepresentation>)
                performRequest(HTTPMethod.GET, "/products", null, new TypeReference<List<ProductRepresentation>>() {});
    }

    public ProductRepresentation createProduct(ProductRepresentation product) throws IOException, APIException {
        return (ProductRepresentation)
                performRequest(HTTPMethod.POST, "/products", product, new TypeReference<ProductRepresentation>() {});
    }

    public ProductRepresentation getProduct(long id) throws IOException, APIException {
        return (ProductRepresentation)
                performRequest(HTTPMethod.GET, String.format("/products/%d", id), null, new TypeReference<ProductRepresentation>() {});
    }

    public ProductRepresentation changeProduct(long id, ProductEdit edit) throws IOException, APIException {
        return (ProductRepresentation)
                performRequest(HTTPMethod.PUT, String.format("/products/%d", id), edit, new TypeReference<ProductRepresentation>() {});
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
     * Authenticate with SKLD API service using the given PIN and, if successful, return a client that can be used to
     * perform other actions on the API.
     *
     * @param baseURL is the URL at which the API is located
     * @param pin is the PIN code that should be used to authenticate with the service.
     * @return A new SkldHttpClient which will communicate with the given API.
     * @throws IOException
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
