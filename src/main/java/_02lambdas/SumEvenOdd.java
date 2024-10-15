package _02lambdas;

import java.util.*;
import java.util.stream.*;

public class SumEvenOdd {
    public static void main(String[] args) {
        List<Integer> werte = List.of(3, 2, 5, 7, 9, 12);

        // Summe aller Werte
        int sumAll = werte.stream().mapToInt(Integer::intValue).sum();

        // Summe aller geraden Werte (N % 2 == 0)
        int sumEven = werte.stream()
                           .filter(it -> it % 2 == 0)
                           .mapToInt(Integer::intValue)
                           .sum();

        // Summe aller ungeraden Werte (N % 2 != 0)
        int sumOdd = werte.stream()
                          .filter(it -> it % 2 != 0)
                          .mapToInt(Integer::intValue)
                          .sum();

        // Ergebnisse ausgeben
        System.out.println("Summe aller Werte: " + sumAll);
        System.out.println("Summe aller geraden Werte: " + sumEven);
        System.out.println("Summe aller ungeraden Werte: " + sumOdd);
    }
}