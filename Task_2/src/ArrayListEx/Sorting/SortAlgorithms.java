package ArrayListEx.Sorting;

import java.util.ArrayList;
import java.util.Collections;

import static java.util.Collections.swap;

//Contains implementation of all algorithms
public class SortAlgorithms {
    public static void sortUsingCollectionsSort(ArrayList<Integer> arr) {
        Collections.sort(arr);
    }

    public static void sortUsingCollectionsSortReverse(ArrayList<Integer> arr) {
        arr.sort(Collections.reverseOrder());
    }
    public static void bubbleSort(ArrayList<Integer> arr)
    {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    // swap temp and arr[i]
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
        }
    }

    public static void selectionSort(ArrayList<Integer> arr){
        int n = arr.size();
        // Iterate from 0 to n - 1
        for(int i = 0; i < n - 1; i++ ){

            int minIndex = i;
            // Iterating from i + 1 to n
            for( int j = i + 1; j < n; j++){

                // If a[j] is smaller than a[minIndex]
                // then updating the minIndex.
                if (arr.get(j) < arr.get(minIndex))
                    minIndex = j;
            }

            // Swapping a[i] with a[minIndex].
            swap(arr, i, minIndex);
        }
    }

    public static void insertionSort(ArrayList<Integer> arr)
    {
        int n = arr.size();
        for (int i = 1; i < n; ++i) {
            int key = arr.get(i);
            int j = i - 1;

            while (j >= 0 && arr.get(j) > key) {
                arr.set(j + 1, arr.get(j));
                j = j - 1;
            }
            arr.set(j + 1, key);
        }
    }

    public static void mergeSort(ArrayList<Integer> arr) {
        divide(arr,0, arr.size()-1);
    }

    private static void divide(ArrayList<Integer> arr, int startIndex, int endIndex) {
        if(startIndex < endIndex && (endIndex - startIndex) >= 1){
            int mid = (endIndex + startIndex) / 2;
            divide(arr, startIndex, mid);
            divide(arr, mid + 1, endIndex);
            
            merger(arr, startIndex,mid,endIndex);
        }
    }

    private static void merger(ArrayList<Integer> arr, int startIndex, int midIndex, int endIndex) {
        ArrayList<Integer> mergedSortedArray = new ArrayList<Integer>();

        int leftIndex = startIndex;
        int rightIndex = midIndex + 1;

        while(leftIndex <= midIndex && rightIndex <= endIndex){
            if(arr.get(leftIndex) <= arr.get(rightIndex)){
                mergedSortedArray.add(arr.get(leftIndex));
                leftIndex++;
            }else{
                mergedSortedArray.add(arr.get(rightIndex));
                rightIndex++;
            }
        }

        while(leftIndex <= midIndex){
            mergedSortedArray.add(arr.get(leftIndex));
            leftIndex++;
        }

        while(rightIndex <= endIndex){
            mergedSortedArray.add(arr.get(rightIndex));
            rightIndex++;
        }

        int i = 0;
        int j = startIndex;
        //Setting sorted array to original one
        while(i<mergedSortedArray.size()) {
            arr.set(j, mergedSortedArray.get(i++));
            j++;
        }
    }

    public static void quickSort(ArrayList<Integer> arr) {
        quickSortHelper(arr, 0, arr.size()-1);
    }

    private static int partition(ArrayList<Integer> arr, int low, int high)
    {
        int pivot = arr.get(high);
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than or
            // equal to pivot
            if (arr.get(j) <= pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                swap(arr, i, j);
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr.get(i + 1);
        arr.set(i + 1, arr.get(high));
        arr.set(high, temp);

        return i+1;
    }

    private static void quickSortHelper(ArrayList<Integer> arr, int low, int high)
    {
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            quickSortHelper(arr, low, pi-1);
            quickSortHelper(arr, pi+1, high);
        }
    }

    public static void heapSort(ArrayList<Integer> arr)
    {
        int n = arr.size();

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i = n - 1; i >= 0; i--) {
            swap(arr, 0, i);

            heapify(arr, i, 0);
        }
    }

    private static void heapify(ArrayList<Integer> arr, int n, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        if (l < n && arr.get(l) > arr.get(largest))
            largest = l;

        if (r < n && arr.get(r) > arr.get(largest))
            largest = r;

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }
}
