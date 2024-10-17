package _99microservices;

public class InventoryService {
    
    public void reserveInventory(Order order) {
        System.out.println("Reserving inventory for order: " + order.getId());
        if (isInventoryAvailable(order)) {
            SagaOrchestrator.processEvent(new InventoryReservedEvent(order));
        } else {
            System.out.println("Inventory not available for order: " + order.getId());
            SagaOrchestrator.processEvent(new InventoryFailedEvent(order));
        }
    }

    public void compensateInventory(Order order) {
        System.out.println("Compensating inventory for order: " + order.getId());
    }

    private boolean isInventoryAvailable(Order order) {
        // Dummy logic to check if inventory is available
        return true; // Simulate success
    }
}