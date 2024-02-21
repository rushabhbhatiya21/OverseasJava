package ArrayListEx.Searching;

import java.util.ArrayList;

public class SearchAlgorithms {
    public static int linearSearch(ArrayList<Integer> arr, int searchElement) {
        for (Integer ele : arr) {
            if (ele == searchElement)
                return 1;
        }
        return -1;
    }

    public static int binarySearch(ArrayList<Integer> arr, int searchElement) {
        int left = 0, right = arr.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr.get(mid) == searchElement) {
                return 1;
            } else if (arr.get(mid) < searchElement) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static int binarySearchRecursion(ArrayList<Integer> arr, int searchElement) {
        return _bsrHelper(arr, 0, arr.size()-1, searchElement);
    }

    private static int _bsrHelper(ArrayList<Integer> arr, int left, int right, int searchElement) {
        if(left > right)
            return -1;

        int mid = left + (right - left) / 2;

        if (arr.get(mid) == searchElement)
            return 1;

        else if (arr.get(mid) < searchElement)
            return _bsrHelper(arr, mid+1, right, searchElement);

        else
            return _bsrHelper(arr, left, mid-1, searchElement);
    }
}
