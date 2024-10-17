package _99microservices;

public abstract class SagaEvent {
    protected Order order;

    public SagaEvent(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}

class OrderCreatedEvent extends SagaEvent {
    public OrderCreatedEvent(Order order) {
        super(order);
    }
}

class InventoryReservedEvent extends SagaEvent {
    public InventoryReservedEvent(Order order) {
        super(order);
    }
}

class PaymentProcessedEvent extends SagaEvent {
    public PaymentProcessedEvent(Order order) {
        super(order);
    }
}

class PaymentFailedEvent extends SagaEvent {
    public PaymentFailedEvent(Order order) {
        super(order);
    }
}

class InventoryFailedEvent extends SagaEvent {
    public InventoryFailedEvent(Order order) {
        super(order);
    }
}
