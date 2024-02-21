package RecursionEx.Q3;

public class SumOfDigits {
    static int sumOfDigitsOfNumber(int n) {
        if (n == 0)
            return 0;
        int temp = n % 10;
        return temp + sumOfDigitsOfNumber(n / 10);
    }

    public static void main(String[] args) {
        System.out.println(sumOfDigitsOfNumber(123));
    }
}
