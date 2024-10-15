package _02lambdas;

import java.util.*;
import java.util.stream.*;

public class StreamExample {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        
        // Original-Liste
        System.out.println("Original: " + list);

        // map: jedes Element mit 2 multiplizieren
        List<Integer> doubled = list.stream()
                                    .map(it -> 2 * it)
                                    .toList();
        System.out.println("Verdoppelt: " + doubled);

        // where: nur ungerade Zahlen behalten
        List<Integer> oddNumbers = list.stream()
                                       .filter(it -> it % 2 != 0)
                                       .toList();
        System.out.println("Ungerade Zahlen: " + oddNumbers);

        // reduce: Summe der Liste
        int sum = list.stream()
                      .reduce(Integer::sum).get();
        System.out.println("Summe: " + sum);

        // fold: ähnliche Funktion wie reduce, um die Ziffern zu einer Zahl zu kombinieren
        int folded = list.stream()
                         .reduce(0, (acc, digit) -> acc * 10 + digit);
        System.out.println("Zahlen zu einer Zahl gefaltet: " + folded);

        // every: Überprüfung, ob jedes Element größer als 1 ist
        boolean allGreaterThanOne = list.stream()
                                        .allMatch(it -> it > 1);
        System.out.println("Alle größer als 1: " + allGreaterThanOne);

        // Kombination von where und map: gerade Zahlen quadrieren
        List<Integer> squaredEvens = list.stream()
                                         .filter(it -> it % 2 == 0)
                                         .map(it -> it * it)
                                         .toList();
        System.out.println("Quadrate der geraden Zahlen: " + squaredEvens);
    }
}