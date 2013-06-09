import java.math.*;
import java.util.*;

public class YetAnotherANDProblem {
    public String test(int[] xs, int k, int[] queries) {
        int n = xs.length;
        int m = queries.length;
        long atLeast = Math.min(k + 1, 3);
        long atMost = 1L << k;
        boolean[] found = new boolean[m];
        for (int mask = 0; mask < 1 << n; ++ mask) {
            int size = Integer.bitCount(mask);
            if (size < atLeast || size > atMost) {
                continue;
            }
            int sum = ~0;
            for (int i = 0; i < n; ++ i) {
                if ((mask >> i & 1) == 1) {
                    sum &= xs[i];
                }
            }
            for (int i = 0; i < m; ++ i) {
                if (sum == queries[i]) {
                    found[i] = true;
                }
            }
        }
        String answer = "";
        for (int i = 0; i < m; ++ i) {
            answer += found[i] ? '+' : '-';
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			YetAnotherANDProblemHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				YetAnotherANDProblemHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class YetAnotherANDProblemHarness {
	public static void run_test(int casenum) {
		if (casenum != -1) {
			if (runTestCase(casenum) == -1)
				System.err.println("Illegal input! Test case " + casenum + " does not exist.");
			return;
		}
		
		int correct = 0, total = 0;
		for (int i=0;; ++i) {
			int x = runTestCase(i);
			if (x == -1) {
				if (i >= 100) break;
				continue;
			}
			correct += x;
			++total;
		}
		
		if (total == 0) {
			System.err.println("No test cases run.");
		} else if (correct < total) {
			System.err.println("Some cases FAILED (passed " + correct + " of " + total + ").");
		} else {
			System.err.println("All " + total + " tests passed!");
		}
	}
	
	static boolean compareOutput(String expected, String result) { return expected.equals(result); }
	static String formatResult(String res) {
		return String.format("\"%s\"", res);
	}
	
	static int verifyCase(int casenum, String expected, String received) { 
		System.err.print("Example " + casenum + "... ");
		if (compareOutput(expected, received)) {
			System.err.println("PASSED");
			return 1;
		} else {
			System.err.println("FAILED");
			System.err.println("    Expected: " + formatResult(expected)); 
			System.err.println("    Received: " + formatResult(received)); 
			return 0;
		}
	}

	static int runTestCase(int casenum__) {
		switch(casenum__) {
		case 0: {
			int[] X                   = {10, 14, 7};
			int K                     = 1;
			int[] queries             = {10, 14, 2, 4};
			String expected__         = "+-+-";

			return verifyCase(casenum__, expected__, new YetAnotherANDProblem().test(X, K, queries));
		}
		case 1: {
			int[] X                   = {30, 29, 27, 23, 15};
			int K                     = 2;
			int[] queries             = {28, 9, 16, 0, 12};
			String expected__         = "-++-+";

			return verifyCase(casenum__, expected__, new YetAnotherANDProblem().test(X, K, queries));
		}
		case 2: {
			int[] X                   = {5, 5, 5, 5, 5};
			int K                     = 50;
			int[] queries             = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
			String expected__         = "-----+----";

			return verifyCase(casenum__, expected__, new YetAnotherANDProblem().test(X, K, queries));
		}
		case 3: {
			int[] X                   = {71258, 1257, 2592588, 2885851, 999928, 123456, 59881, 99999};
			int K                     = 4;
			int[] queries             = {72, 91, 10, 0, 27, 64, 8};
			String expected__         = "+--+-++";

			return verifyCase(casenum__, expected__, new YetAnotherANDProblem().test(X, K, queries));
		}

		// custom cases

/*      case 4: {
			int[] X                   = ;
			int K                     = ;
			int[] queries             = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new YetAnotherANDProblem().test(X, K, queries));
		}*/
/*      case 5: {
			int[] X                   = ;
			int K                     = ;
			int[] queries             = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new YetAnotherANDProblem().test(X, K, queries));
		}*/
/*      case 6: {
			int[] X                   = ;
			int K                     = ;
			int[] queries             = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new YetAnotherANDProblem().test(X, K, queries));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
