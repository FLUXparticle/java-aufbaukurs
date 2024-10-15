package _03logging;

public class Wrapper {

    private final String message;

    public Wrapper(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        System.out.println("Unwrapped!");
        return message;
    }

}
