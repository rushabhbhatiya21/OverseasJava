package RecursionEx.Q6;

public class CheckPalindrome {
    static boolean isPalindromeOld (String s1, String s2, int i, int j) {
        if (i > s1.length()-1 && j < 0)
            return true;
        if (s1.charAt(i) != s2.charAt(j))
            return false;
        return isPalindromeOld(s1, s2, i+1, j-1);
    }

    static boolean isPalindrome (String s, int i, int j) {
        if (i >= j)
            return true;
        if (s.charAt(i) != s.charAt(j))
            return false;
        return isPalindrome(s, i+1, j-1);
    }

    public static void main(String[] args) {
        String s = "abcdcba";
        int i = 0, j = s.length() - 1;
        System.out.println(isPalindrome(s, i, j));
    }
}
