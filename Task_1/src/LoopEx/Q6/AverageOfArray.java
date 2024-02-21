package LoopEx.Q6;

public class AverageOfArray {

    public static void main(String[] args) {
        int[] num ={45,69,36,22,45,78,96,1,2,3};
        int average = calculateAverage(num);


        System.out.println("Average of the array elements is: " + average);
    }

    public static int calculateAverage(int[] array) {
        if (array.length == 0) {
            return 0;
        }

        int sum = 0;


        for (int num : array) {
            sum += num;
        }

        return sum / array.length;
    }
}
