package cz.cvut.fit.project.skld.application.resources;

import cz.cvut.fit.project.skld.api.OrderInRepresentation;
import cz.cvut.fit.project.skld.api.ProductRepresentation;
import cz.cvut.fit.project.skld.api.UserRepresentation;
import cz.cvut.fit.project.skld.application.core.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RepresentationConverter {
    public static UserRepresentation representUser(User u) {
        return new UserRepresentation(u.getId(), u.getName(), u.getCreatedAt(), u.isAdmin());
    }

    public static OrderInRepresentation representOrderIn(OrderIn order) {
        OrderInRepresentation rep = new OrderInRepresentation();
        rep.setId(order.getId());
        rep.setSupplierName(order.getSupplierName());
        rep.setDeliveryDate(order.getExpectedDelivery());
        rep.setCreatedAt(order.getCreatedAt());
        rep.setHandledAt(order.getHandledAt());
        rep.setState(order.getState().toString());
        rep.setCreatedBy(representUser(order.getCreator()));
        rep.setHandledBy(representUser(order.getHandler()));

        ArrayList<ProductRepresentation> products = new ArrayList<ProductRepresentation>();
        for (LineItem li : order.getLineItems()) {
            ProductRepresentation pRep = representProduct(li);
            products.add(pRep);
        }
        rep.setProducts(products);
        return rep;
    }

    public static ProductRepresentation representProduct(Product p) {
        ProductRepresentation rep = new ProductRepresentation();
        rep.setId(p.getId());
        rep.setName(p.getName());
        rep.setPositions(null);
        rep.setQuantity(null);
        return rep;
    }

    public static ProductRepresentation representProduct(Product p, List<ProductPosition> positions) {
        ProductRepresentation rep = representProduct(p);
        if (positions != null && positions.size() > 0) {
            long quantity = 0;
            Map<String, Long> positionMap = new HashMap<>();
            for (ProductPosition pos : positions) {
                positionMap.put(pos.getPosition(), pos.getQuantity());
                quantity += pos.getQuantity();
            }
            rep.setPositions(positionMap);
            rep.setQuantity(quantity);
        } else {
            rep.setPositions(null);
            rep.setQuantity(0L);
        }
        return rep;
    }

    public static ProductRepresentation representProduct(LineItem li) {
        ProductRepresentation rep = new ProductRepresentation();
        rep.setId(li.getProduct().getId());
        rep.setName(li.getProduct().getName());
        if (li.getProductAllocations() != null && !li.getProductAllocations().isEmpty()) {
            long quantity = 0;
            Map<String, Long> positionMap = new HashMap<>();
            for (ProductMovement pm : li.getProductAllocations()) {
                positionMap.put(pm.getLocation(), pm.getQuantity());
                quantity += pm.getQuantity();
            }
            rep.setPositions(positionMap);
            rep.setQuantity(quantity);
        } else {
            rep.setQuantity(li.getQuantity());
        }
        return rep;
    }
}
