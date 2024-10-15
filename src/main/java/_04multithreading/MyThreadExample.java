package _04multithreading;

public class MyThreadExample {

    public static void main(String[] args) {
        // Thread mit Methodenreferenz erzeugen
        Thread thread = new Thread(MyThreadExample::myTask);
        
        // Thread starten
        thread.start();
    }

    // Aufgabe, die der Thread ausführt
    public static void myTask() {
        System.out.println("Thread is running: " + Thread.currentThread().getName());
    }

}
