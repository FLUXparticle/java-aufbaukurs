package _99microservices;

public class SagaOrchestrator {
    
    private static final InventoryService inventoryService = new InventoryService();
    private static final PaymentService paymentService = new PaymentService();
    private static final OrderService orderService = new OrderService();

    public static void processEvent(SagaEvent event) {
        Order order = event.getOrder();

        if (event instanceof OrderCreatedEvent) {
            inventoryService.reserveInventory(order);

        } else if (event instanceof InventoryReservedEvent) {
            paymentService.processPayment(order);

        } else if (event instanceof PaymentProcessedEvent) {
            // Bestellung erfolgreich

        } else if (event instanceof PaymentFailedEvent) {
            // Kompensationsaktionen ausführen
            inventoryService.compensateInventory(order);
            orderService.compensateOrder(order);

        } else if (event instanceof InventoryFailedEvent) {
            orderService.compensateOrder(order);
        }
    }

}