package ExpressionsIOEx.Q7;

import java.util.Scanner;

public class PowerofNumRecursion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the base: ");
        double base = scanner.nextDouble();

        System.out.print("Enter the exponent: ");
        int exponent = scanner.nextInt();

        double result = calculatePower(base, exponent);
        System.out.println(base + " raised to the power of " + exponent + " is: " + result);

        scanner.close();
    }

    public static double calculatePower(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        } else {
            return base * calculatePower(base, exponent - 1);
        }
    }
}
