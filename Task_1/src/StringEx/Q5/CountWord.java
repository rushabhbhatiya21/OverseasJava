package StringEx.Q5;

public class CountWord {
    public static void main(String[] args) {
        String s = "hello hello my name is Rushabh.";
        String[] words = s.split(" ");
        String target = "hello";
        int count = 0;
        for(String x:words){
            if (x.equals(target))count++;
        }
        System.out.println(count);
    }
}
