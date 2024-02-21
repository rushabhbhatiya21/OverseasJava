package ExpressionsIOEx.Q10;

import java.util.Scanner;

public class StringManipulation {
    public static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in);
        System.out.print("\n1. concat\n2. substring\n3. length\nEnter your choice: ");
        choice = sc.nextInt();
        switch (choice)
        {
            case 1:
                System.out.println("Enter two strings: ");
                String str1 = sc.next();
                String str2 = sc.next();
                System.out.println("Concatenated string: " + concatenateStrings(str1,str2));
                break;
            case 2:
                System.out.println("Enter string, start index, end index: ");
                String str = sc.next();
                int si = sc.nextInt();
                int ei = sc.nextInt();
                System.out.println("Substring is: " + getSubstring(str,si,ei));
                break;
            case 3:
                System.out.println("Enter string: ");
                String string = sc.next();
                System.out.println("Substring is: " + getLength(string));
                break;
        
            default:
                break;
        }
    }

    public static String concatenateStrings(String str1, String str2) {
        return str1 + str2;
    }

    public static String getSubstring(String str, int startIndex, int endIndex) {
        String revString = "";
        for(int i = startIndex; i<endIndex; ++i){
            revString += str.charAt(i);
        }
        return revString;
    }

    public static int getLength(String str) {
        int count = 0;
        for (char ch : str.toCharArray()) {
            count++;
        }
        return count;
    }
}
