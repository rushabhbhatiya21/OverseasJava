package ExpressionsIOEx.Q8;

import java.util.Scanner;

public class PrimeRecursion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number to check for primality: ");
        int number = scanner.nextInt();

        if (isPrime(number)) {
            System.out.println(number + " is a prime number.");
        } else {
            System.out.println(number + " is not a prime number.");
        }

        scanner.close();
    }

    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        } else {
            return isPrimeHelper(number, 2);
        }
    }

    private static boolean isPrimeHelper(int number, int divisor) {
        if (divisor > Math.sqrt(number)) {
            return true;
        } else {
            if (number % divisor == 0) {
                return false;
            } else {
                return isPrimeHelper(number, divisor + 1);
            }
        }
    }
}
