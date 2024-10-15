package _04multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MyFutureExample {

    public static void main(String[] args) throws Exception {
        // ExecutorService mit festem Thread-Pool
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        // Aufgabe mit Future übergeben
        Future<Integer> result = executor.submit(MyFutureExample::calculate);

        // Warten, bis das Ergebnis verfügbar ist
        if (result.isDone()) {
            System.out.println("Result: " + result.get());
        } else {
            System.out.println("Task is not yet finished...");
        }

        // Warten auf das Ergebnis (mit Timeout)
        Integer finalResult = result.get(2, TimeUnit.SECONDS);
        System.out.println("Final Result: " + finalResult);
        
        // ExecutorService herunterfahren
        executor.shutdown();
    }

    // Aufgabe, die ein Ergebnis zurückgibt
    public static Integer calculate() {
        try {
            // Simulation einer Berechnung
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return 42; // Beispiel-Ergebnis
    }
}