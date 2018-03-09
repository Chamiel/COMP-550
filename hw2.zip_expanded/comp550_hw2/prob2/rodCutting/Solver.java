package rodCutting;

import java.util.ArrayList;

public class Solver {

    /**
     * Cuts the rod using the simple recursive solution.
     * @param valueByLength - a list of values for each rod length
     * @param rodLength - the total length of the rod to be cut
     */
    static RodCuttingSolution cutRod(ArrayList<Integer> valueByLength, int rodLength) {
        // TODO - implement!
        // Note: for problem 2a, you only need to return the correct value,
        // not the list of lengths, so you can still return new ArrayList<Integer>()
        // until you start 2c
    	ArrayList<Integer> lengths = new ArrayList<Integer>();
    	if (rodLength == 0)
    		return new RodCuttingSolution(new ArrayList<Integer>(), 0);
    	int bestVal = (int)Integer.MIN_VALUE;
    	for (int i = 1; i<=rodLength; i++) {
    		RodCuttingSolution solution = cutRod(valueByLength, rodLength - i);
    		int a = (int)valueByLength.get(i) + solution.value;
    		if (a>bestVal) {
    			bestVal = a;
    			lengths = solution.lengths;
    			lengths.add(i);
    		}
    	}
        return new RodCuttingSolution(lengths, bestVal);
    }

    /**
     * Cuts the rod using the simple recursive solution.
     * @param valueByLength - a list of values for each rod length
     * @param rodLength - the total length of the rod to be cut
     */
    static RodCuttingSolution cutRodMemoized(ArrayList<Integer> valueByLength, int rodLength) {
        // TODO - implement!
        // Note: for problem 2b, you only need to return the correct value,
        // not the list of lengths, so you can still return new ArrayList<Integer>()
        // until you start 2c\
    	int[] memo = new int[rodLength+1];		// memo for best price for each length
    	int[] lengths = new int[rodLength+1];	// memo for best cut at each length
    	memo[0] = 0;
    	lengths[0] = 0;
    	for (int i = 1; i<=rodLength; i++) {	//initialize memo values
    		memo[i] = (int)Integer.MIN_VALUE;
    		lengths[i] = -1;
    	}
    	
        return RodCutMemAux(valueByLength, rodLength, memo, lengths);
    }
    
    static RodCuttingSolution RodCutMemAux(ArrayList<Integer> valueByLength, int rodLength, int[] memo, int[] lengths) {
    	if (memo[rodLength]>=0) {	// if length 0 return 0
    		return new RodCuttingSolution(getRodLengths(lengths, rodLength), memo[rodLength]);
    	}
    	int q = (int)Integer.MIN_VALUE;
    	int p = (int)Integer.MIN_VALUE;
    	
		for (int i=1; i<=rodLength; i++) {
			RodCuttingSolution solution = RodCutMemAux(valueByLength, rodLength-i, memo, lengths);
			int a = (int)valueByLength.get(i) + solution.value;	// best value
			
			if (a>q) {	// if best value, update best price and best cut in memos
				q = a;
				p = i;
			}
			memo[rodLength] = q;
			lengths[rodLength] = p;
		}
		
    	return new RodCuttingSolution(getRodLengths(lengths, rodLength), q);
    }
    
    // to get the lengths of the rod for the solution
    static ArrayList<Integer> getRodLengths(int[] lengths, int rodLength) {
    	ArrayList<Integer> length = new ArrayList<Integer>();
    	int i = rodLength;
    	while (true) {		// for the length, look into array with length as index to find next cut and subtract and loop
    		if (lengths[i] < 0)
    			return length;
    		if (lengths[i] == 0)
    			length.add(i);
    		else
    			length.add(lengths[i]);
    		i = i-lengths[i];
    		if (i == 0)
    			return length;
    	}
    }
}
