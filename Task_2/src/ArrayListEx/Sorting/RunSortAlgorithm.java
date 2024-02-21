package ArrayListEx.Sorting;

import java.util.ArrayList;

//Run particular algorithm bases on passed name using switch case
public class RunSortAlgorithm {
    ArrayList<Integer> arr;
    public String algoName;

    public RunSortAlgorithm(ArrayList<Integer> arr, String algorithmName) {
        this.algoName = algorithmName;
        this.arr = arr;
    }

    public void runSortingAlgorithm() {
        switch (algoName) {
            case "collection.sort()":
                SortAlgorithms.sortUsingCollectionsSort(this.arr);
                break;
            case "bubble":
                SortAlgorithms.bubbleSort(this.arr);
                break;
            case "selection":
                SortAlgorithms.selectionSort(this.arr);
                break;
            case "insertion":
                SortAlgorithms.insertionSort(this.arr);
                break;
            case "collection.sort(reverse)":
                SortAlgorithms.sortUsingCollectionsSortReverse(this.arr);
                break;
            case "merge":
                SortAlgorithms.mergeSort(this.arr);
                break;
            case "quick":
                SortAlgorithms.quickSort(this.arr);
                break;
            case "heap":
                SortAlgorithms.heapSort(this.arr);
                break;
            default:
                System.out.println("Please select proper algorithm!");
        }
    }
}
