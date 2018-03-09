package maximumSubarray;

import java.util.ArrayList;

public class Solver {

    /**
     * Finds the maximum subarray of the provided list using the brute-force solution.
     * @param list - the list of elements
     */
    static MaximumSubarraySolution findMaximumSubarrayBruteForce(ArrayList<Integer> list) {
        int bestLow = 0;
        int bestHigh = 0;
        int bestVal = list.get(0);
        for (int i=0; i<list.size();i++) {
        	int currentVal = 0;
        	for (int j=i; j<list.size();j++) {
        		currentVal = currentVal + list.get(j);
        		if (currentVal >= bestVal) {
        			bestVal = currentVal;
        			bestLow = i;
        			bestHigh = j;
        		}
        	}
        }
        return new MaximumSubarraySolution(bestLow, bestHigh, bestVal);
    }

    /**
     * Finds the maximum subarray of the provided list using the recursive solution.
     * @param list - the list of elements
     */
    static MaximumSubarraySolution findMaximumSubarrayRecursive(ArrayList<Integer> list) {
    	return findMaximumSubarrayRecursive(list, 0, list.size()-1);
    }

    /**
     * Finds the maximum subarray of the provided list using the recursive solution.
     * @param list - the list of elements
     * @param low  - the start of the range to consider
     * @param high - the end of the range to consider
     */
    private static MaximumSubarraySolution findMaximumSubarrayRecursive(ArrayList<Integer> list,
                                                                        int low, int high) {
        // It might be helpful to implement this function...
        if (high == low)
        	return new MaximumSubarraySolution(low, high, list.get(low));
        else {
        	int mid = (int)Math.floor((double)(low+high)/2);
        	MaximumSubarraySolution left = findMaximumSubarrayRecursive(list,low,mid);
        	MaximumSubarraySolution right = findMaximumSubarrayRecursive(list,mid+1,high);
        	MaximumSubarraySolution cross = findMaxCrossingSubarray(list, low, mid, high);
        	if (left.value >= right.value && left.value >= cross.value)
        		return left;
        	else if (right.value >= cross.value)
        		return right;
        	return cross;
        }
    }

    /**
     * Finds the maximum subarray, including the element at list[mid], of the provided list
     * using the recursive solution.
     * @param list - the list of elements
     * @param low  - the start of the range to consider
     * @param mid  - the mid element, which must be included in the final range
     * @param high - the end of the range to consider
     */
    private static MaximumSubarraySolution findMaxCrossingSubarray(ArrayList<Integer> list,
                                                                   int low, int mid, int high) {
        // It might be helpful to implement this function...
        int leftsum = Integer.MIN_VALUE;
        int sum = 0;
        int maxleft = 0;
        int maxright = 0;
        for (int i = mid; i>=low; i--) {
        	sum = sum + list.get(i);
        	if (sum > leftsum) {
        		leftsum = sum;
        		maxleft = i;
        	}
        }
        int rightsum = Integer.MIN_VALUE;
        sum = 0;
        for (int j = mid; j<high; j++) {
        	sum = sum + list.get(j);
        	if (sum>rightsum) {
        		rightsum = sum;
        		maxright = j;
        	}
        }
        return new MaximumSubarraySolution(maxleft, maxright, leftsum + rightsum);
        
    }
}
