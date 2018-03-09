package fib;

public class Fibonacci {

    /**
     * Calculates the nth Fibonacci number using the simple recursive solution.
     * @param n - which Fibonacci number to generate
     */
    static long fib(long n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fib(n-1) + fib(n-2);
        }
    }

    /**
     * Calculates the nth Fibonacci number using memoization.
     * @param n - which Fibonacci number to generate
     */
    static long memoizedFib(int n) {
        long[] memo = new long[(n+1)];
        memo[0] = 0;
        memo[1] = 1;
        for (int i = 2; i<=n; i++)
        	memo[i] = -1;
        return memoizedFibAux(n, memo);
    }

    static long memoizedFibAux(int n, long memo[]) {
    	long q;
    	if (n == 0 || n == 1 || memo[n] >= 0)
    		return memo[n];
    	q = (memoizedFibAux(n-1, memo)+memoizedFibAux(n-2, memo));
    	memo[n] = q;
    	return q;
    }
    /**
     * Calculates the nth Fibonacci number using an iterative bottom-up approach.
     * @param n - which Fibonacci number to generate
     */
    static long bottomUpFib(int n) {
        long[] memo = new long[n+1];
        memo[0]= 0;
        memo[1] = 1;
        for (int i = 2; i<=n; i++) {
        	memo[i] = memo[i-1] + memo[i-2];
        }
        return memo[n];
    }
}
