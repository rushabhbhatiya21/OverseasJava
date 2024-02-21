package ExceptionsEx.Q2;

import java.util.Scanner;

public class Main {
    public static void validate(int age) {
        if(age < 18) {
            throw new ArithmeticException("Person is not eligible to vote");
        }
        else {
            System.out.println("Person is eligible to vote!!");
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int age = sc.nextInt();

        validate(age);
    }
}
