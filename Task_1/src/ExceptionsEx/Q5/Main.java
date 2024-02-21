package ExceptionsEx.Q5;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
//            int[] a = new int[5];
//            System.out.println(a[10]);
            int a = 10 / 0;
        }
        catch (ArrayStoreException | ArrayIndexOutOfBoundsException | ArithmeticException e) {
            System.out.println("Exception handled!");
        }

        System.out.println("Continue...");
    }
}
