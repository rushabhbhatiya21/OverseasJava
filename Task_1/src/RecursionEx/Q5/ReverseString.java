package RecursionEx.Q5;

public class ReverseString {
    static String reverseStringRec (String s, String s2, int i) {
        if (i < 0)
            return s2;
        char c = s.charAt(i);
        return reverseStringRec(s, s2 + c, i-1);
    }

    public static void main(String[] args) {
        String s = "Rushabh";
        int i = s.length();
        System.out.println(reverseStringRec(s, "", i-1));
    }
}
