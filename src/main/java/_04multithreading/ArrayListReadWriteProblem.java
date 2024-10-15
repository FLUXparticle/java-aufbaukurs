package _04multithreading;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class ArrayListReadWriteProblem {

    private static final List<Integer> list = new ArrayList<>();

    private static final int READER_THREADS = 100;
    private static final int READS_PER_THREAD = 1000;

    private static final int WRITER_THREADS = 10;
    private static final int WRITES_PER_THREAD = 100;

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(READER_THREADS + WRITER_THREADS);
        
        // Initialize list with some data
        for (int i = 0; i < 1000; i++) {
            list.add(i * WRITER_THREADS * WRITES_PER_THREAD);
        }
        
        long startTime = System.currentTimeMillis();

        AtomicInteger okay = new AtomicInteger(0);
        AtomicInteger failed = new AtomicInteger(0);

        // Create reader threads
        for (int i = 0; i < READER_THREADS; i++) {
            new Thread(() -> {
                for (int j = 0; j < READS_PER_THREAD; j++) {
                    try {
                        Integer sum = list.stream().reduce(Integer::sum).orElse(null);
                        okay.incrementAndGet();
                    } catch (ConcurrentModificationException e) {
                        failed.incrementAndGet();
                    }
                    try {
                        // Simulate delay in writing
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                latch.countDown();
            }).start();
        }
        
        // Create writer threads
        for (int i = 0; i < WRITER_THREADS; i++) {
            int base = i * WRITER_THREADS;
            new Thread(() -> {
                for (int j = 0; j < WRITES_PER_THREAD; j++) {
                    int number = base + j;
                    System.out.println("number = " + number);
                    list.add(number);
                    try {
                        // Simulate delay in writing
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                latch.countDown();
            }).start();
        }
        
        latch.await();
        
        long endTime = System.currentTimeMillis();
        
        System.out.println("Final list size: " + list.size());
        System.out.println("okay = " + okay);
        System.out.println("failed = " + failed);
        System.out.println("Time taken with unsynchronized ArrayList: " + (endTime - startTime) + " ms");
    }

}
