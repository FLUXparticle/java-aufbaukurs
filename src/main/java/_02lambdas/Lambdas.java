package _02lambdas;

import java.util.*;
import java.util.function.*;

public class Lambdas {

    public static void foo(MyFunctionalInterface mfi) {
        mfi.execute();
    }

    public static void main(String[] args) {
        // Instanzmethode eines beliebigen Objekts eines bestimmten Typs
        Predicate<String> p = String::isEmpty;
        // Statische Methode mit einem Parameter
        Function<String, Integer> f = Integer::parseInt;
        // Instanzmethode eines bestimmten Objekts
        Consumer<String> c = System.out::println;
        // Konstruktoren
        Supplier<List<String>> s = ArrayList::new;
        // Statische Methode ohne Parameter
        Runnable r = System::gc;
        
        foo(() -> {
            System.out.println("Hallo!");
        });
    }
    
}
