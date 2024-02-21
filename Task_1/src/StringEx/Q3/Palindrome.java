package StringEx.Q3;

public class Palindrome {
    public static void main(String[] args){
        String s = "abcba";
        int len = s.length();
        char[] c = s.toCharArray();
        for(int i=0;i<len/2;i++){
            char temp = c[i];
            c[i] = c[len-i-1];
            c[len-i-1] = temp;
        }
        String s1 = new String(c);
        if (s1.equals(s)){
            System.out.println("palindrome");
        }
        else{
            System.out.println("not palindrome");
        }
    }
}
