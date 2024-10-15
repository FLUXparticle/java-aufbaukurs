package _04multithreading;

public class MyThreadExample {

    public static void main(String[] args) {
        // Thread mit Methodenreferenz erzeugen
        Thread thread = new Thread(MyThreadExample::myTask);
        thread.setDaemon(true);
        
        // Thread starten
        thread.start();

        System.out.println("Thread is running: " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread is running: " + Thread.currentThread().getName());
    }

    // Aufgabe, die der Thread ausführt
    public static void myTask() {
        System.out.println("Thread is running: " + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread is running: " + Thread.currentThread().getName());
    }

}
