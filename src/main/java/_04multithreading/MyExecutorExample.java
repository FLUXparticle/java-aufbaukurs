package _04multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyExecutorExample {

    public static void main(String[] args) {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("availableProcessors = " + availableProcessors);

        // ExecutorService mit festem Thread-Pool erzeugen
        ExecutorService executor = Executors.newFixedThreadPool(availableProcessors);
        
        // Drei Aufgaben an den Executor übergeben
        executor.submit(MyExecutorExample::task);
        executor.submit(MyExecutorExample::task);
        executor.submit(MyExecutorExample::task);
        
        // ExecutorService herunterfahren
        executor.shutdown();
    }

    public static void task() {
        System.out.println("Task executed by: " + Thread.currentThread().getName());
    }

}
