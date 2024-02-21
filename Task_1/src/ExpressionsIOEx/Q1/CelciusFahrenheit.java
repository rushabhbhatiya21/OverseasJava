package ExpressionsIOEx.Q1;

import java.util.Scanner;

public class CelciusFahrenheit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose:\n 1. Celsius to Fahrenheit\n 2. Fahrenheit to Celsius");
        System.out.print("Choice: ");
        int choice = sc.nextInt();
        if (choice == 1){
            System.out.print("Enter temperature in Celsius: ");
            double celsius = sc.nextDouble();
            System.out.println("Fahrenheit temperature: " + ((celsius*1.8)+32));
        }
        else if(choice == 2){
            System.out.print("Enter temperature in Fahrenheit: ");
            double fahrenheit = sc.nextDouble();
            System.out.println("Celsius temperature: " + ((fahrenheit-32)/1.8));
        }
        else{
            System.out.println("Please select valid option!!!");
        }
    }
}
