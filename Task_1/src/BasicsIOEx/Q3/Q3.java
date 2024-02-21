package BasicsIOEx.Q3;
import java.util.Scanner;

public class Q3 {
      public static void main(String[] args) {
    	  String s1,s2;
    	  Scanner input = new Scanner(System.in);
    	  System.out.println("Enter first string");
    	  s1=input.next();
    	  System.out.println("Enter Second string");
    	  s2=input.next();
    	  System.out.println(s1.concat(s2));
      }
}
