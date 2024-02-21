package ExpressionsIOEx.Q2;

import java.util.Scanner;

public class CompoundInterest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the principal amount: ");
        double principal = scanner.nextDouble();

        System.out.print("Enter the annual interest rate (in percentage): ");
        double rate = scanner.nextDouble();

        System.out.print("Enter the time in years: ");
        double time = scanner.nextDouble();

        double compoundInterest = calculateCompoundInterest(principal, rate, time);

        System.out.printf("The compound interest is: %.2f%n", compoundInterest);

        scanner.close();
    }

    public static double calculateCompoundInterest(double principal, double rate, double time) {
        int n = 12;
        return principal * Math.pow(1 + (rate / 100.0) / n, n * time);
    }
}
