package LoopEx.Q5;

import java.util.Scanner;

public class PrimeNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter a number :- ");
        int number = scanner.nextInt();
        boolean isPrime = checkPrime(number);

        if (isPrime) {
            System.out.println(number + " is a prime number.");
        } else {
            System.out.println(number + " is not a prime number.");
        }

    }

    public static boolean checkPrime(int n) {
        if (n <= 1) {
            return false;
        }

        int i = 2;

        while (i <= n / 2) {
            if (n % i == 0) {
                return false;
            }
            i++;
        }

        return true;
    }
}
