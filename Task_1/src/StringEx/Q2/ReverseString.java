package StringEx.Q2;

public class ReverseString {
    public static void main(String[] args){
        String s = "this is simple string.";
        int len = s.length();
        char[] c = s.toCharArray();
        for(int i = 0; i < len / 2; i++){
            char temp = c[i];
            c[i] = c[len-i-1];
            c[len-i-1] = temp;
        }
        System.out.println(new String(c));
    }
}
