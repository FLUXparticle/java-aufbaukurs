package _99microservices;

public class PaymentService {
    
    public void processPayment(Order order) {
        System.out.println("Processing payment for order: " + order.getId());
        if (isPaymentSuccessful(order)) {
            SagaOrchestrator.processEvent(new PaymentProcessedEvent(order));
        } else {
            System.out.println("Payment failed for order: " + order.getId());
            SagaOrchestrator.processEvent(new PaymentFailedEvent(order));
        }
    }

    public void compensatePayment(Order order) {
        System.out.println("Compensating payment for order: " + order.getId());
    }

    private boolean isPaymentSuccessful(Order order) {
        // Dummy logic to check if payment is successful
        return false; // Simulate failure
    }
}