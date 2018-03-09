package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main {

    // Choice of whether to display debug messages
    static final boolean DEBUG = false;

    // Choices of available sorting functions
    static final int INSERTION_SORT = 1;
    static final int MERGE_SORT = 2;
    static final int SELECTION_SORT = 3;
    static final int BUBBLE_SORT = 4;
    
    // Size of datasets
    static final int SMALL_SIZE = 1000;
    static final int LARGE_SIZE = 100000;
    
    // Choices of available data to sort
    static final int SMALL_SORTED = 1;
    static final int SMALL_ALMOST = 2;
    static final int SMALL_BACKWARDS = 3;
    static final int SMALL_RANDOM = 4;
    static final int LARGE_SORTED = 5;
    static final int LARGE_ALMOST = 6;
    static final int LARGE_BACKWARDS = 7;
    static final int LARGE_RANDOM = 8;

    // Sort function to apply
//    static int sortFunctionChoice = INSERTION_SORT;
//    static int sortFunctionChoice = MERGE_SORT;
    static int sortFunctionChoice = SELECTION_SORT;
//    static int sortFunctionChoice = BUBBLE_SORT;

    // Dataset to sort
//    static int datasetChoice = SMALL_SORTED;
//    static int datasetChoice = SMALL_ALMOST;
//    static int datasetChoice = SMALL_BACKWARDS;
//    static int datasetChoice = SMALL_RANDOM;
//    static int datasetChoice = LARGE_SORTED;
//    static int datasetChoice = LARGE_ALMOST;
//    static int datasetChoice = LARGE_BACKWARDS;
    static int datasetChoice = LARGE_RANDOM;

    /**
     * Main function, which runs a single sort-dataset combination.
     * @param args
     */
    public static void main(String[] args) {
        // Choose the data to sort
        ArrayList<Integer> dataset;
        switch (datasetChoice) {
            case SMALL_SORTED:
                dataset = buildSmallSortedList();
                break;
            case SMALL_ALMOST:
                dataset = buildSmallAlmostSortedList();
                break;
            case SMALL_BACKWARDS:
                dataset = buildSmallBackwardsSortedList();
                break;
            case SMALL_RANDOM:
                dataset = buildSmallRandomList();
                break;
            case LARGE_SORTED:
                dataset = buildLargeSortedList();
                break;
            case LARGE_ALMOST:
                dataset = buildLargeAlmostSortedList();
                break;
            case LARGE_BACKWARDS:
                dataset = buildLargeBackwardsSortedList();
                break;
            case LARGE_RANDOM:
                dataset = buildLargeRandomList();
                break;
            default:
                System.out.println("Invalid dataset specified.");
                dataset = new ArrayList<Integer>();
                break;
        }

        if (DEBUG) {
            System.out.println("About to sort the list: " + dataset.toString());
        }

        // Get the start time
        long startNanos = System.nanoTime();

        // Sort the data
        switch (sortFunctionChoice) {
            case INSERTION_SORT:
                Sorter.insertionSort(dataset);
                break;
            case MERGE_SORT:
                Sorter.mergeSort(dataset);
                break;
            case SELECTION_SORT:
                Sorter.selectionSort(dataset);
                break;
            case BUBBLE_SORT:
                Sorter.bubbleSort(dataset);
                break;
            default:
                System.out.println("Invalid sorting function specified.");
        }
        
        // Get the end time and print out the duration
        long endNanos = System.nanoTime();
        long durationNanos = endNanos - startNanos;
        System.out.println("The sort took " + String.valueOf(durationNanos / 1000000.0) + " milliseconds.");

        if (DEBUG) {
            System.out.println("The sorted list is: " + dataset.toString());
        }
    }

    // Private functions to build datasets

    private static ArrayList<Integer> buildSmallSortedList() {
        return buildSortedList(SMALL_SIZE);
    }

    private static ArrayList<Integer> buildSmallAlmostSortedList() {
        return buildAlmostSortedList(SMALL_SIZE);
    }

    private static ArrayList<Integer> buildSmallBackwardsSortedList() {
        return buildBackwardsSortedList(SMALL_SIZE);
    }
    
    private static ArrayList<Integer> buildSmallRandomList() {
        return buildRandomList(SMALL_SIZE);
    }
    
    private static ArrayList<Integer> buildLargeSortedList() {
        return buildSortedList(LARGE_SIZE);
    }
    
    private static ArrayList<Integer> buildLargeAlmostSortedList() {
        return buildAlmostSortedList(LARGE_SIZE);
    }
    
    private static ArrayList<Integer> buildLargeBackwardsSortedList() {
        return buildBackwardsSortedList(LARGE_SIZE);
    }
    
    private static ArrayList<Integer> buildLargeRandomList() {
        return buildRandomList(LARGE_SIZE);
    }
    
    private static ArrayList<Integer> buildSortedList(int size) {
        ArrayList<Integer> data = new ArrayList<Integer>(size);

        for (int i = 0; i < size; i++) {
            data.add(i, i);
        }

        return data;
    }
    
    private static ArrayList<Integer> buildAlmostSortedList(int size) {
        ArrayList<Integer> data = buildSortedList(size);

        Random r = new Random();

        // Swap a few pairs of elements
        for (int k = 0; k < 3; k++) {
            int i = r.nextInt(size);
            int j = r.nextInt(size);
            while (i == j) {
                j = r.nextInt() % size;
            }
            
            int tmp = data.get(i);
            data.set(i, data.get(j));
            data.set(j, tmp);
        }

        return data;
    }
    
    private static ArrayList<Integer> buildBackwardsSortedList(int size) {
        ArrayList<Integer> data = buildSortedList(size);
        
        Collections.reverse(data);
        
        return data;
    }
    
    private static ArrayList<Integer> buildRandomList(int size) {
        ArrayList<Integer> data = buildSortedList(size);
        
        Collections.shuffle(data);
        
        return data;
    }
}
