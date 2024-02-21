package ExceptionsEx.Q6;

public class Main {
    public static void main(String[] args) {
        try {
            Class<?> example = Class.forName("Example");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not found!");
        }
    }
}
