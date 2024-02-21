package ArrayListEx;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

public class InitializeArrayList {
    int size = 0;

    public InitializeArrayList(int size) {
        this.size = size;
    }
    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    public String getTimeTaken(String algo, LocalTime end, LocalTime start) {
        try {
            return "Time taken to sort using " + algo  + ": " + Duration.between(end, start).toString().split("-")[1].split("S")[0] + " seconds";
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return "Time taken to sort using " + algo  + ": " + Duration.between(end, start).toString().split("T")[1].split("S")[0] + " seconds";
        }

    }
    public ArrayList<Integer> populateArrayWithRandomElements() {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            arr.add((int) (Math.random() * (size)));
        }
        return arr;
    }
}
