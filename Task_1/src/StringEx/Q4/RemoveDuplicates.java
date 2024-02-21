package StringEx.Q4;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicates {
    public static void main(String[] args) {
        String input = "programming with java";
        char[] charArray = input.toCharArray();
        int length = charArray.length;

        Set<Character> uniqueChars = new HashSet<>();

        StringBuilder resultBuilder = new StringBuilder();

        for (char currentChar : charArray) {
            if (currentChar != ' ' && uniqueChars.add(currentChar)) {
                resultBuilder.append(currentChar);
            } else if (currentChar == ' ') resultBuilder.append(currentChar);
        }
        System.out.println(resultBuilder.toString());
    }
}
