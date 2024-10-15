package _04multithreading;

import java.util.concurrent.*;

public class MyCompletableExample {

    public static void main(String[] args) {

        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(MyCompletableExample::calculate);

        completableFuture.thenAccept(System.out::println);
        completableFuture.exceptionally(e -> {
            e.printStackTrace();
            return null;
        });

        System.out.println("Fertig!");

        try {
            // Simulation einer Berechnung
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

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
