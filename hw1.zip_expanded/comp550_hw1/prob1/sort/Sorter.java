package sort;

import java.util.ArrayList;

public class Sorter {

    /**
     * Sorts the provided list in-place using Insertion Sort.
     * @param list - the list to be sorted (will be modified)
     */
    static void insertionSort(ArrayList<Integer> list) {
        for (int j = 1; j < list.size(); j++) {
            int key = list.get(j);

            // Find the position in the sorted sequence in indices 0..j-1 in
            // which to insert the key
            int i = j - 1;
            while (i >= 0 && list.get(i) > key) {
                list.set(i + 1, list.get(i));
                i--;
            }

            list.set(i+1, key);
        }
    }

    /**
     * Merges the sorted ranges p..q-1 and q..r of the provided list.
     * @param list - the list containing the ranges to merge (will be modified)
     * @param p    - the start of the first range
     * @param q    - the midpoint of the two ranges
     * @param r    - the end of the second range
     */
    private static void merge(ArrayList<Integer> list, int p, int q, int r) {
        int i, j;

        int n1 = q - p + 1;
        int n2 = r - q;

        // Create arrays left and right to hold the two starting ranges
        ArrayList<Integer> left = new ArrayList<Integer>(n1 + 1);
        ArrayList<Integer> right = new ArrayList<Integer>(n2 + 1);

        for (i = 0; i < n1; i++) {
            left.add(i, list.get(p + i));
        }

        for (j = 0; j < n2; j++) {
            right.add(j, list.get(q + j + 1));
        }

        left.add(n1, Integer.MAX_VALUE);
        right.add(n2, Integer.MAX_VALUE);

        // Merge the two sorted ranges in left and right into a single sorted range in list[p..r]
        i = 0;
        j = 0;
        for (int k = p; k <= r; k++) {
            if (left.get(i) <= right.get(j)) {
                list.set(k, left.get(i));
                i++;
            } else {
                list.set(k, right.get(j));
                j++;
            }
        }
    }
    
    /**
     * Sorts the provided list in-place using Merge Sort.
     * @param list - the list to be sorted (will be modified)
     * @param p    - the start of the range to sort in list
     * @param r    - the end of the range to sort in list
     */
    private static void mergeSort(ArrayList<Integer> list, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(list, p, q);
            mergeSort(list, q + 1, r);
            merge(list, p, q, r);
        }
    }

    /**
     * Sorts the provided list in-place using Merge Sort.
     * @param list - the list to be sorted (will be modified)
     */
    static void mergeSort(ArrayList<Integer> list) {
        mergeSort(list, 0, list.size() - 1);
    }

    /**
     * Sorts the provided list in-place using Selection Sort.
     * @param list - the list to be sorted (will be modified)
     */
    static void selectionSort(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            // Find the minimum element in list[i..n]
            int minValIndex = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j) < list.get(minValIndex)) {
                    minValIndex = j;
                }
            }
            
            // Swap list[i] with the minimum element in list[i..n]
            int tmp = list.get(i);
            list.set(i, list.get(minValIndex));
            list.set(minValIndex, tmp);
        }
    }

    /**
     * Sorts the provided list in-place using Bubble Sort.
     * @param list - the list to be sorted (will be modified)
     */
    static void bubbleSort(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j) < list.get(j - 1)) {
                    int tmp = list.get(j);
                    list.set(j, list.get(j - 1));
                    list.set(j - 1, tmp);
                }
            }
        }
    }
}
