package RecursionEx.Q9;

public class TowerOfHanoi {
    static void helper(int index, char from, char to, char aux) {
        if (index == 0)
            return;
        helper(index-1, from, aux, to);
        System.out.printf("Disk %d from %c to %c.\n", index, from, to);
        helper(index-1, aux, to, from);
    }

    public static void main(String[] args) {
        helper(3, 'S', 'D', 'A');
    }
}
