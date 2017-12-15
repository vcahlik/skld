package cz.cvut.fit.project.skld.application.core;

import javax.persistence.*;
import java.time.Instant;
import java.util.*;

/***
 * Represents an Order from the domain model.
 */
@Entity
@Table(name="orders")
@Inheritance(strategy = InheritanceType.JOINED)
public class Order {

    @Id
    private long id;

    @Column(name="created_at", nullable = false)
    private Instant createdAt;

    @Column(name="handled_at", nullable = false)
    private Instant handledAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderState state;

    @ManyToOne
    @JoinColumn(name = "creator_id", foreignKey = @ForeignKey(name = "FK_Order_Creator"), nullable = false)
    private User creator;

    @ManyToOne
    @JoinColumn(name = "handler_id", foreignKey = @ForeignKey(name = "FK_Order_Handler"))
    private User handler;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<LineItem> lineItems;

    public Order() {}

    public Order(long id, User creator) {
        this.id = id;
        this.createdAt = Instant.now();
        this.state = OrderState.OPEN;
        this.creator = creator;
        this.lineItems = new ArrayList<LineItem>();
    }

    public Map<Long, LineItem> lineItemMap() {
        HashMap<Long, LineItem> m = new HashMap<>();
        for (LineItem li : getLineItems()) {
            m.put(li.getProduct().getId(), li);
        }
        return m;
    }

    public void setHandlingDetails(User handler) {
        this.handler = handler;
        this.handledAt = Instant.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getHandledAt() {
        return handledAt;
    }

    public void setHandledAt(Instant handledAt) {
        this.handledAt = handledAt;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getHandler() {
        return handler;
    }

    public void setHandler(User handler) {
        this.handler = handler;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id == order.id &&
                Objects.equals(createdAt, order.createdAt) &&
                Objects.equals(handledAt, order.handledAt) &&
                state == order.state &&
                Objects.equals(creator, order.creator) &&
                Objects.equals(handler, order.handler);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, handledAt, state, creator, handler);
    }
}
