package BasicsIOEx.Q4;
import java.util.Scanner;

public class Q4 {
     public static void main(String[] args) {
    	 char ch;
    	 Scanner input =new Scanner(System.in);
    	 System.out.println("Enter a character:");
    	 ch=input.next().charAt(0);
    	 int n;
    	 n=(int) ch;
    	 System.out.printf("Your entered number is %d",n);
     }
}
