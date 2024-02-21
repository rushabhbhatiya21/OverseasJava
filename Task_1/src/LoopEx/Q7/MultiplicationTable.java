package LoopEx.Q7;

import java.util.Scanner;

public class MultiplicationTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number: ");
        int number = scanner.nextInt();
        printMultiplicationTable(number, 10);

    }
    public static void printMultiplicationTable(int num, int rows) {
        System.out.println("Multiplication Table for " + num + ":");
        for (int i = 1; i <= rows; i++) {
            System.out.println(num + " x " + i + " = " + (num * i));
        }
    }
}
