package ExpressionsIOEx.Q5;

import java.util.Scanner;

public class AvgTwoNumbersRecursion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the two integers: ");
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();

        double average = calculateAverage(num1, num2);
        System.out.println("The average is: " + average);

        scanner.close();
    }

    public static double calculateAverage(int num1, int num2) {
        if (num1 == num2) {
            return num1;
        } else {
            return (num1 + num2) / 2.0;
        }
    }
}
