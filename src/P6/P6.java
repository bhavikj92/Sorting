package P6;

import java.util.Arrays;

/*
 * Problem 6: Risk Threshold Binary Lookup
 */

public class P6 {

    // -------- LINEAR SEARCH --------
    public static int linearSearch(int[] arr, int target) {
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                System.out.println("Linear Found at index: " + i);
                System.out.println("Comparisons: " + comparisons);
                return i;
            }
        }

        System.out.println("Linear: Not Found");
        System.out.println("Comparisons: " + comparisons);
        return -1;
    }

    // -------- FLOOR --------
    public static int floor(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int floor = -1;
        int comparisons = 0;

        while (low <= high) {
            comparisons++;
            int mid = (low + high) / 2;

            if (arr[mid] == target) {
                floor = arr[mid];
                break;
            }

            if (arr[mid] < target) {
                floor = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Floor(" + target + ") = " + floor + " | Comparisons: " + comparisons);
        return floor;
    }

    // -------- CEILING --------
    public static int ceiling(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int ceil = -1;
        int comparisons = 0;

        while (low <= high) {
            comparisons++;
            int mid = (low + high) / 2;

            if (arr[mid] == target) {
                ceil = arr[mid];
                break;
            }

            if (arr[mid] > target) {
                ceil = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        System.out.println("Ceiling(" + target + ") = " + ceil + " | Comparisons: " + comparisons);
        return ceil;
    }

    // -------- INSERTION POINT (LOWER BOUND) --------
    public static int insertionPoint(int[] arr, int target) {
        int low = 0, high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;

            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        System.out.println("Insertion Index for " + target + ": " + low);
        return low;
    }

    // -------- MAIN --------
    public static void main(String[] args) {

        int[] risks = {10, 25, 50, 100};

        System.out.println("Sorted Risk Bands:");
        System.out.println(Arrays.toString(risks));

        int target = 30;

        System.out.println("\n--- Linear Search ---");
        linearSearch(risks, target);

        System.out.println("\n--- Binary Variants ---");
        floor(risks, target);
        ceiling(risks, target);
        insertionPoint(risks, target);
    }
}