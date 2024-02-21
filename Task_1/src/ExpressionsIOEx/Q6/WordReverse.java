package ExpressionsIOEx.Q6;

import java.util.Scanner;

public class WordReverse {

    static String strRev(String str){
        char[] strarr = new char[str.length()];
        for(int i=0, j=strarr.length-1; i<strarr.length; ++i,--j){
            strarr[i] = str.charAt(j);
        }
        return new String(strarr);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String str = sc.nextLine();
        for (String string : str.split(" ")) {
            System.out.print(strRev(string) + " ");
        }
    }
}
