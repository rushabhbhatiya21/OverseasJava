package RecursionEx.Q8;

public class SumOfNNumbers {
    static int sumOfN(int n) {
        if (n == 0)
            return 0;
        return n + sumOfN(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(sumOfN(10));
    }
}
