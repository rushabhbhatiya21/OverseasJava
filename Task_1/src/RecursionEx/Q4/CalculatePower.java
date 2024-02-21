package RecursionEx.Q4;

public class CalculatePower {
    static int calculatePowerOfNumber(int base, int power) {
        if (power == 0)
            return  1;
        return base * calculatePowerOfNumber(base, power - 1);
    }

    public static void main(String[] args) {
        System.out.println(calculatePowerOfNumber(3, 5));
    }
}
