package ArrayListEx.Searching;

import ArrayListEx.InitializeArrayList;
import ArrayListEx.Sorting.RunSortAlgorithm;

import java.time.LocalTime;
import java.util.*;

public class _Main {
    public static void main(String[] args) {
        int size = 100000;
        //initialize object with variable amount of size
        InitializeArrayList init = new InitializeArrayList(size);
        //initialize random array
        ArrayList<Integer> arr = init.populateArrayWithRandomElements();
        Collections.sort(arr);

        //HashMap to store algo name and their time taken
        Map<String, String> map = new HashMap<>();

        //create a list of algorithms and add their logic in Algorithms class
        String[] algoList = {"linear", "binary", "binaryRec"};

        //iterate over algorithms list of names which will run algo according to their name using switch case from RunAlgorithm class
        for (String algo : algoList) {
            System.out.println(algo);

            //send array and algorithm to run to RunAlgorithm class
            RunSearchAlgorithm searchAlgorithm = new RunSearchAlgorithm(algo, arr, 106055);

            //initialize time and run selected algo to measure time2
            LocalTime start = init.getCurrentTime();
            searchAlgorithm.runSearchingAlgorithm();
            LocalTime end = init.getCurrentTime();

            //get time difference and store them in HashMap
            String time = init.getTimeTaken(searchAlgorithm.algoName, end, start);
            map.put(searchAlgorithm.algoName, time);
        }

        //iterate over hash map to print time taken by respective algorithms
        for (HashMap.Entry<String, String> mapElement: map.entrySet()) {
            System.out.println(mapElement.getValue());
        }
    }
}
