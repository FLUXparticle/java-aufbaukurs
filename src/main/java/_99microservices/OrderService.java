package _99microservices;

public class OrderService {
    public void createOrder(Order order) {
        System.out.println("Order created: " + order.getId());
        // Sende Event zur Zahlung
        SagaOrchestrator.processEvent(new OrderCreatedEvent(order));
    }

    public void compensateOrder(Order order) {
        System.out.println("Order compensation: " + order.getId());
        // Event zur Bestellungsstornierung senden
    }
}