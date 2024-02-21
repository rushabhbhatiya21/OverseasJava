package ExpressionsIOEx.Q3;

import java.util.Scanner;

public class CountVowelsRecursion {

    static int isVowel(char ch) 
    { 
        ch = Character.toUpperCase(ch); 
        if(ch=='A' || ch=='E' || ch=='I' || ch=='O' || ch=='U')  
            return 1; 
        else 
            return 0; 
    } 
    
    static int countVowels(String str, int n) 
    { 
        if (n == 1) 
            return isVowel(str.charAt(n));
       
        return countVowels(str, n) + isVowel(str.charAt(n));
    } 
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String str = sc.nextLine();
       
        System.out.println("Number of vowels in given string: " + countVowels(str,str.length() - 1));
    }
}
