package maximumSubarray;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    // Choice of whether to display debug messages
    static final boolean DEBUG = false;
    
    // Choices of available maximum-subarray functions
    static final int BRUTE_FORCE = 1;
    static final int RECURSIVE = 2;

    // Function to use
    static int functionChoice = BRUTE_FORCE;
//    static int functionChoice = RECURSIVE;

    /**
     * Main function, which runs either the brute-force or recursive solution
     * to find the maximum subarray of the chosen array.
     * @param args
     */
    public static void main(String[] args) {
        // Build an array of positive and negative numbers
        int numElements = 10000;
        ArrayList<Integer> data = generateRandomDataset(numElements);

        // Get the start time
        long startNanos = System.nanoTime();

        // Find the maximum subarray
        MaximumSubarraySolution solution;
        switch (functionChoice) {
            case BRUTE_FORCE:
                solution = Solver.findMaximumSubarrayBruteForce(data);
                break;
            case RECURSIVE:
                solution = Solver.findMaximumSubarrayRecursive(data);
                break;
            default:
                System.out.println("Invalid function specified.");
                solution = new MaximumSubarraySolution(-1, -1, 0);
                break;
        }

        // Get the end time and print out the duration
        long endNanos = System.nanoTime();
        long durationNanos = endNanos - startNanos;

        // Print the results
        if (DEBUG) {
            System.out.println("Input array: " + data.toString() + "\n");
        }

        System.out.println("The function took " + String.valueOf(durationNanos / 1000000.0) + " milliseconds.");
        if (DEBUG) {
            System.out.println("Results (sum=" + solution.value + "): " +
                               data.subList(solution.start, solution.end + 1) + "\n");
        }
    }

    private static ArrayList<Integer> generateRandomDataset(int size) {
        ArrayList<Integer> data = new ArrayList<Integer>(size);

        for (int i = 0; i < size; i++) {
            int element = i - size / 2;
            data.add(i, element);
        }
        
        Collections.shuffle(data);
        
        return data;
    }
}
