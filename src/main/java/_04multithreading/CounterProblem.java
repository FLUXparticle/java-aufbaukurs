package _04multithreading;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;

public class CounterProblem {

    private static final ReentrantLock lock = new ReentrantLock();
    private static int counter1 = 0;
    private static int counter2 = 0;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    private static AtomicReference<Object> atomicReference = new AtomicReference<>();

    private static final int WRITER_THREADS = 100;
    private static final int WRITES_PER_THREAD = 1000;

    public static void increment() {
        lock.lock(); // Lock aufrufen
        try {
            counter2++;
        } finally {
            lock.unlock(); // Immer im finally Block entsperren
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(WRITER_THREADS);
        
        // Create writer threads
        for (int i = 0; i < WRITER_THREADS; i++) {
            new Thread(() -> {
                for (int j = 0; j < WRITES_PER_THREAD; j++) {
                    atomicInteger.incrementAndGet();
                    counter1++;
                    increment();
                }
                latch.countDown();
            }).start();
        }
        
        latch.await();

        System.out.println("counter1 = " + counter1);
        System.out.println("counter2 = " + counter2);
        System.out.println("atomicInteger = " + atomicInteger);
    }

}
