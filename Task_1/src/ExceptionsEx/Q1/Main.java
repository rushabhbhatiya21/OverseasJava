package ExceptionsEx.Q1;

public class Main {
    public static void main(String[] args) {
        try {
            int a = 10, b = 0;
            int c = a / b;
        }
        catch (ArithmeticException e) {
            System.out.println(e.getMessage() + "...\n Please check your code from line 5 to 8.");
        }
    }
}
