package ExpressionsIOEx.Q9;

import java.util.Scanner;

public class AreaOfShapes {
    public static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Area Calculator!!");
        do{
            System.out.print("\n1. circle\n2. rectangle\n3. triangle\nEnter your choice: ");
            choice = sc.nextInt();
            switch (choice)
            {
                case 1:
                    System.out.println("Enter radius: ");
                    double r = sc.nextDouble();
                    System.out.printf("The area of the circle is %.2f", Math.PI * Math.pow(r, 2));
                    break;
                case 2:
                    System.out.println("Enter length and breadth: ");
                    double l = sc.nextDouble();
                    double b = sc.nextDouble();
                    System.out.printf("The area of the rectangle is %.2f", l*b);
                    break;
                case 3:
                    System.out.println("Enter base and height: ");
                    double base = sc.nextDouble();
                    double height = sc.nextDouble();
                    System.out.printf("The area of the triangle is %.2f", 0.5*base*height);
                    break;
        
                default:
                    break;
            }
        }while(choice>0 && choice<4);
    }
}
