package StringEx.Q6;

public class LongestWordLength {
    public static void main(String[] args) {
        String s = "hello hello my name is Rushabh";
        String[] words = s.split(" ");
        int count = Integer.MIN_VALUE;
        for(String x:words){
            if (x.length() > count) count = x.length();
        }
        System.out.println(count);
    }
}
