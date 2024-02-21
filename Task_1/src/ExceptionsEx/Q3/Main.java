package ExceptionsEx.Q3;

import java.util.Scanner;

public class Main {
    public static void validate() {
        try {
            int a = 10, b = 0;
            int c = a / b;
        }
        catch (ArithmeticException e) {
            System.out.println(e.getMessage() + "...\n Please check your validate function.");
        }
        finally {
            System.out.println("We all know division by 0 is not possible, why? Interesting...");
        }
    }

    public static void main(String[] args) throws Exception {
        validate();
    }
}
