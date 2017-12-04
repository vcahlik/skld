package cz.cvut.fit.project.skld.client.http;
import static org.assertj.core.api.Assertions.*;

import cz.cvut.fit.project.skld.client.exceptions.APIException;
import cz.cvut.fit.project.skld.client.exceptions.UnauthorizedException;
import cz.cvut.fit.project.skld.representations.OrderInRepresentation;
import cz.cvut.fit.project.skld.representations.ProductEdit;
import cz.cvut.fit.project.skld.representations.ProductRepresentation;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SkldHttpClientTest {
    SkldHttpClient client;

    @Before
    public void setup() {
        assertThatCode( () -> {client = SkldHttpClient.getClientForPIN("http://localhost:8080", "1984");}).doesNotThrowAnyException();
    }

    @Test(expected = UnauthorizedException.class)
    public void testUnauthorized() throws IOException, APIException {
        SkldHttpClient.getClientForPIN("http://localhost:8080", "XXXXXXXXX");
    }

    @Test
    public void testCreateProduct() throws IOException, APIException {
        ProductRepresentation prod = new ProductRepresentation(1909, "Unit test product 1", null);
        ProductRepresentation response = client.createProduct(prod);
        assertThat(response.getId()).isEqualTo(1909);
        assertThat(response.getName()).isEqualTo("Unit test product 1");
        assertThat(response.getQuantity()).isEqualTo(null);
        assertThat(response.getPositions()).isEqualTo(null);
    }

    @Test
    public void testGetProduct() throws IOException, APIException {
        ProductRepresentation response = client.getProduct(1);
        assertThat(response.getId()).isEqualTo(1);
        assertThat(response.getName()).isEqualTo("World-Destroyer v2");
        assertThat(response.getQuantity()).isEqualTo(3);
        Map<String, Long> positions = new HashMap<>();
        positions.put("A1", 2L);
        positions.put("A2", 1L);
        assertThat(response.getPositions()).isEqualTo(positions);
    }

    @Test
    public void testEditProduct() throws IOException, APIException {
        ProductRepresentation response = client.changeProduct(2, new ProductEdit("Flesh-Eating Nanobot Cloud"));
        assertThat(response.getId()).isEqualTo(2);
        assertThat(response.getName()).isEqualTo("Flesh-Eating Nanobot Cloud");
        response = client.getProduct(2);
        assertThat(response.getId()).isEqualTo(2);
        assertThat(response.getName()).isEqualTo("Flesh-Eating Nanobot Cloud");
    }

    @Test
    public void testCreateOrderIn() throws IOException, APIException {
        OrderInRepresentation order = new OrderInRepresentation(9000, "ACME Fulfillment Co.");
        ArrayList<ProductRepresentation> products = new ArrayList<>();
        products.add(new ProductRepresentation(1, 13));
        products.add(new ProductRepresentation(3, 8));
        order.setProducts(products);
        order.setDeliveryDate(new Date(117, 11, 20));
        OrderInRepresentation response = client.createOrderIn(order);
        assertThat(response.getId()).isEqualTo(9000);
        assertThat(response.getSupplierName()).isEqualTo("ACME Fulfillment Co.");
        assertThat(response.getDeliveryDate()).isEqualTo("2017-12-20");
    }

    @Test
    public void testGetOrderIn() throws IOException, APIException {
        OrderInRepresentation order = client.getOrderIn(2);
        assertThat(order.getId()).isEqualTo(2);
        assertThat(order.getSupplierName()).isEqualTo("Black Mesa Research Institute");
        assertThat(order.getState()).isEqualToIgnoringCase("CLOSED");
        ProductRepresentation product0 = order.getProducts().get(0);
        assertThat(product0.getId()).isEqualTo(1);
        assertThat(product0.getQuantity()).isEqualTo(3);
        Map<String, Long> positions = new HashMap<>();
        positions.put("A1", 2L);
        positions.put("A2", 1L);
        assertThat(product0.getPositions()).isEqualTo(positions);
    }
}
