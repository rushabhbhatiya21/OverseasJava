package LoopEx.Q9;

import java.util.Scanner;

public class StarPattern {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter rows : ");
        int numRows = scanner.nextInt();
        generateStarPattern(numRows);

    }
    private static void generateStarPattern(int rows) {
        for (int i = 1; i <= rows; i++) {

            for (int j = 1; j <= rows - i; j++) {
                System.out.print(" ");
            }

            for (int k = 1; k <= 2 * i - 1; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
