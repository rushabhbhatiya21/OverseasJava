package LoopEx.Q8;

import java.util.Scanner;

public class ReverseIntArray {
    public static void main(String[] args) {
        int arr[]={1,2,3,4,5,6,7,8,9,10};

        reverseArray(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }
    private static void reverseArray(int[] array) {
        int start = 0;
        int end = array.length - 1;

        while (start < end) {
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;


            start++;
            end--;
        }
    }
}
