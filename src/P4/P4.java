package P4;

import java.util.Random;
import java.util.Arrays;

/*
 * Problem 4: Portfolio Return Sorting
 */

class Asset {
    String name;
    double returnRate;
    double volatility;

    public Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return name + ":" + returnRate + "% (Vol:" + volatility + ")";
    }
}

public class P4 {

    // -------- MERGE SORT (ASC, STABLE) --------
    public static void mergeSort(Asset[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void merge(Asset[] arr, int left, int mid, int right) {
        Asset[] temp = new Asset[right - left + 1];

        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i].returnRate <= arr[j].returnRate) {
                temp[k++] = arr[i++]; // stable
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (int x = 0; x < temp.length; x++) {
            arr[left + x] = temp[x];
        }
    }

    // -------- QUICK SORT (DESC return + ASC volatility) --------
    public static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionMedianOf3(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // -------- MEDIAN OF 3 PIVOT --------
    public static int partitionMedianOf3(Asset[] arr, int low, int high) {
        int mid = (low + high) / 2;

        // Find median of low, mid, high
        if (arr[low].returnRate < arr[mid].returnRate) swap(arr, low, mid);
        if (arr[low].returnRate < arr[high].returnRate) swap(arr, low, high);
        if (arr[mid].returnRate < arr[high].returnRate) swap(arr, mid, high);

        Asset pivot = arr[mid];
        swap(arr, mid, high); // move pivot to end

        return partition(arr, low, high);
    }

    // -------- PARTITION --------
    public static int partition(Asset[] arr, int low, int high) {
        Asset pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {

            // DESC return, ASC volatility
            if (arr[j].returnRate > pivot.returnRate ||
                    (arr[j].returnRate == pivot.returnRate &&
                            arr[j].volatility < pivot.volatility)) {

                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void swap(Asset[] arr, int i, int j) {
        Asset temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // -------- PRINT --------
    public static void print(Asset[] arr) {
        for (Asset a : arr) {
            System.out.println(a);
        }
    }

    // -------- MAIN --------
    public static void main(String[] args) {

        Asset[] assets = {
                new Asset("AAPL", 12, 5),
                new Asset("TSLA", 8, 9),
                new Asset("GOOG", 15, 4)
        };

        System.out.println("Original Assets:");
        print(assets);

        // Merge Sort (ASC)
        Asset[] mergeArr = Arrays.copyOf(assets, assets.length);
        mergeSort(mergeArr, 0, mergeArr.length - 1);

        System.out.println("\nMerge Sort (Ascending Return):");
        print(mergeArr);

        // Quick Sort (DESC + volatility)
        Asset[] quickArr = Arrays.copyOf(assets, assets.length);
        quickSort(quickArr, 0, quickArr.length - 1);

        System.out.println("\nQuick Sort (DESC Return + ASC Volatility):");
        print(quickArr);
    }
}