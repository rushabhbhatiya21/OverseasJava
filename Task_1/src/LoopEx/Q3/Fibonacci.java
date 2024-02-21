package LoopEx.Q3;

import java.util.Scanner;

public class Fibonacci {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number : ");
        int numTerms = scanner.nextInt();


        printFibonacciSeries(numTerms);

    }

    public static void printFibonacciSeries(int numTerms) {
        if (numTerms <= 0) {
            System.out.println("Enter Valid Number.");
            return;
        }

        int a = 0, b = 1;
        int i = 2;
        System.out.print(a + " " + b + " ");

        do {
            int c = a + b;
            System.out.print(c + " ");

            a = b;
            b = c;

            i++;
        } while (i < numTerms);
    }
}
