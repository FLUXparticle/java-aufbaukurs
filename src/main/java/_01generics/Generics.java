package _01generics;

public class Generics {

    public static void printNumBox(NumberBox<Number> numBox) {
        Number value = numBox.get();
        System.out.println("NumberBox<Number>: " + value);
    }

    public static void printIntBox(NumberBox<Integer> intBox) {
        Integer value = intBox.get();
        System.out.println("NumberBox<Integer>: " + value);
    }

    public static void printExtendsNumberBox(NumberBox<? extends Number> extNumBox) {
        Number value = extNumBox.get();
        System.out.println("NumberBox<? extends Number>: " + value);
    }

    public static <T> void transferBox(Box<? super T> dest, Box<? extends T> src) {
        dest.set(src.get());
    }

    public static <T extends Number> void transferNumberBox(NumberBox<? super T> dest, NumberBox<? extends T> src) {
        dest.set(src.get());
    }

    public static void main(String[] args) {
        // 1. Generische Klasse

        Box<String> strBox = new Box<>();

        strBox.set("test"); // Funktioniert
        // strBox.set(42);     // Fehler

        // 2. Bounded Types
        {
            NumberBox<Number> numBox = new NumberBox<>();

            numBox.set(10);          // Integer
            Number num = numBox.get(); // Gibt ein Number-Objekt zurück

            numBox.set(15.5);        // Double
            Double dub = (Double) numBox.get(); // Ich muss sicher sein, dass sich wirklich ein Double-Objekt in der Box befindet

            NumberBox<Double> dubBox = new NumberBox<>();
            dubBox.set(25.5);

            // dubBox.set(10); // Integer
            num = dubBox.get();
            dub = dubBox.get();
        }

        // 3. Probleme bei der Vererbung mit Generics
        {
            NumberBox<Number> numBox = new NumberBox<>();
            numBox.set(10);          // Integer
            NumberBox<Integer> intBox = new NumberBox<>();
            intBox.set(20);

            printIntBox(intBox);  // Funktioniert
            // printIntBox(numBox);  // Fehler

            // printNumBox(intBox);  // Fehler
            printNumBox(numBox);  // Funktioniert

            printExtendsNumberBox(intBox);  // Funktioniert
            printExtendsNumberBox(numBox);  // Funktioniert
        }

        // 4. Generic-Klasse als Ausgabe-Parameter
        {
            NumberBox<Number> numBox = new NumberBox<>();

            NumberBox<Integer> intBox = new NumberBox<>();
            intBox.set(20);

            NumberBox<Double> dubBox = new NumberBox<>();
            dubBox.set(20.5);

            dubBox.copyTo(numBox); // Funktioniert
            // dubBox.copyTo(intBox); // Fehler

            transferNumberBox(numBox, intBox); // Funktioniert
            transferNumberBox(numBox, dubBox); // Funktioniert
            // transferNumberBox(intBox, dubBox); // Fehler
        }
    }

}
