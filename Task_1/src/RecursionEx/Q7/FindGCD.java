package RecursionEx.Q7;
public class FindGCD {
    static int gcdUsingEclidAlgo(int a, int b) {
        if (a == 0)
            return b;
        return gcdUsingEclidAlgo(b % a, a);
    }

    public static void main(String[] args) {
        System.out.println(gcdUsingEclidAlgo(15, 60));
    }
}
