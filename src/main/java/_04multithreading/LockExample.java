package _04multithreading;

import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    private static final ReentrantLock lock = new ReentrantLock();
    private static int counter = 0;

    public static void increment() {
        lock.lock(); // Lock aufrufen
        try {
            counter++;
        } finally {
            lock.unlock(); // Immer im finally Block entsperren
        }
    }
}