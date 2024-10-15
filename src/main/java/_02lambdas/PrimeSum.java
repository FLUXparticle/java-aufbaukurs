package _02lambdas;

import java.util.*;
import java.util.stream.*;

public class PrimeSum {
    public static void main(String[] args) {
        // Generiere eine Liste von 2 bis 100

        // Filtere nur Primzahlen und berechne die Summe
        int sumPrimes = IntStream.rangeClosed(2, 100).boxed()
                .filter(PrimeSum::isPrime)
                .reduce(Integer::sum)
                .get();

        // Ergebnis ausgeben
        System.out.println("Summe aller Primzahlen bis 100: " + sumPrimes); // Ergebnis: 1060
    }

    // Funktion zur Überprüfung, ob eine Zahl eine Primzahl ist
    public static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}