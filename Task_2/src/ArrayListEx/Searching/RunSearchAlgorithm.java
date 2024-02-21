package ArrayListEx.Searching;

import ArrayListEx.Sorting.SortAlgorithms;

import java.util.ArrayList;

public class RunSearchAlgorithm {
    ArrayList<Integer> arr;
    public String algoName;
    int searchElement;

    public RunSearchAlgorithm(String algorithmName, ArrayList<Integer> arr, int searchElement) {
        this.algoName = algorithmName;
        this.searchElement = searchElement;
        this.arr = arr;
    }

    public void runSearchingAlgorithm() {
        switch (algoName) {
            case "linear":
                if (SearchAlgorithms.linearSearch(this.arr, this.searchElement) == 1)
                    System.out.println("key found!");
                else
                    System.out.println("key not found");
                break;
            case "binary":
                if (SearchAlgorithms.binarySearch(this.arr, this.searchElement) == 1)
                    System.out.println("key found!");
                else
                    System.out.println("key not found");
                break;
            case "binaryRec":
                if (SearchAlgorithms.binarySearchRecursion(this.arr, this.searchElement) == 1)
                    System.out.println("key found!");
                else
                    System.out.println("key not found");
                break;
            default:
                System.out.println("Please select proper algorithm!");
        }
    }

}
