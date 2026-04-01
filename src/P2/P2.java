package P2;

import java.util.Arrays;

/*
 * Problem 2: Client Risk Score Ranking
 */

class Client {
    String name;
    int riskScore;
    double accountBalance;

    public Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return name + " (" + riskScore + ", $" + accountBalance + ")";
    }
}

public class P2 {

    // -------- BUBBLE SORT (ASCENDING RISK SCORE) --------
    public static void bubbleSort(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        System.out.println("\nBubble Sort (Ascending Risk):");
        print(arr);
        System.out.println("Swaps: " + swaps);
    }

    // -------- INSERTION SORT (DESC RISK + BALANCE) --------
    public static void insertionSort(Client[] arr) {

        for (int i = 1; i < arr.length; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 && (
                    arr[j].riskScore < key.riskScore ||   // DESC risk
                            (arr[j].riskScore == key.riskScore &&
                                    arr[j].accountBalance < key.accountBalance) // DESC balance
            )) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }

        System.out.println("\nInsertion Sort (DESC Risk + Balance):");
        print(arr);
    }

    // -------- TOP 10 HIGH RISK --------
    public static void topRisk(Client[] arr) {
        System.out.println("\nTop High Risk Clients:");

        int limit = Math.min(10, arr.length);

        for (int i = 0; i < limit; i++) {
            System.out.println(arr[i].name + " (" + arr[i].riskScore + ")");
        }
    }

    // -------- PRINT --------
    public static void print(Client[] arr) {
        for (Client c : arr) {
            System.out.println(c);
        }
    }

    // -------- MAIN --------
    public static void main(String[] args) {

        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 2000),
                new Client("clientB", 50, 3000)
        };

        System.out.println("Original Clients:");
        print(clients);

        // Copy arrays
        Client[] bubbleArr = Arrays.copyOf(clients, clients.length);
        Client[] insertionArr = Arrays.copyOf(clients, clients.length);

        // Bubble Sort
        bubbleSort(bubbleArr);

        // Insertion Sort
        insertionSort(insertionArr);

        // Top Risk Clients
        topRisk(insertionArr);
    }
}
