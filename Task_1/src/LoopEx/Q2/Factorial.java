package LoopEx.Q2;

import java.util.Scanner;

public class Factorial {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int number = scanner.nextInt();

        long factorial = calculateFactorial(number);
        System.out.println("Factorial of " + number + " is: " + factorial);

    }


    public static long calculateFactorial(int n) {
        if (n < 0) {
            return -1;
        }

        long result = 1;
        int i = 1;

        while (i <= n) {
            result *= i;
            i++;
        }

        return result;
    }
}
