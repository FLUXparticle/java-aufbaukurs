package _01generics;

public class NumberBox<T extends Number> {

    private T item;

    public void set(T item) {
        this.item = item;
    }

    public T get() {
        return item;
    }

    // Methode, die Elemente von einer NumberBox<T> in eine andere NumberBox<? super T> kopiert
    public void copyFrom(NumberBox<? extends T> src) {
        item = src.get();
    }

    public void copyTo(NumberBox<? super T> dest) {
        dest.set(item);
    }

}
