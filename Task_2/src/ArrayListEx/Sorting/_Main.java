package ArrayListEx.Sorting;

import ArrayListEx.InitializeArrayList;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class _Main {
    public static void main(String[] args) {
        //initialize object with variable amount of size
        InitializeArrayList init = new InitializeArrayList(50000);

        //HashMap to store algo name and their time taken
        HashMap<String, String> map = new HashMap<>();

        //create a list of algorithms and add their logic in Algorithms class
        String[] algoList = {"collection.sort()", "collection.sort(reverse)", "bubble", "selection", "insertion", "merge", "quick", "heap"};

        //iterate over algorithms list of names which will run algo according to their name using switch case from RunAlgorithm class
        for (String algo : algoList) {
            System.out.println(algo);

            //initialize random array
            ArrayList<Integer> arr = init.populateArrayWithRandomElements();
            //send array and algorithm to run to RunAlgorithm class
            RunSortAlgorithm sortingAlgo = new RunSortAlgorithm(arr, algo);

            //initialize time and run selected algo to measure time
            LocalTime start = init.getCurrentTime();
            sortingAlgo.runSortingAlgorithm();
            LocalTime end = init.getCurrentTime();

            //get time difference and store them in HashMap
            String time = init.getTimeTaken(sortingAlgo.algoName, end, start);
            map.put(sortingAlgo.algoName, time);
        }

        //iterate over hash map to print time taken by respective algorithms
        for (HashMap.Entry<String, String> mapElement: map.entrySet()) {
            System.out.println(mapElement.getValue());
        }
    }
}
