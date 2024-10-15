package _01generics;

public class Box<T> {
    
    private T item;

    public void set(T item) {
        this.item = item;
    }

    public T get() {
        if (item instanceof Number) {
            Number num = (Number) item;
            num.intValue();
        }
        
        return item;
    }

}
