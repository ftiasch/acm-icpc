import java.math.*;
import java.util.*;

public class AlternateColors2 {
    public long countWays(int n, int k) {
        k --;
        long[][] ways = new long[n + 3][1 << 3];
        ways[0][(1 << 3) - 1] = 1;
        for (int i = 0; i < n; ++ i) {
            for (int mask = 1; mask < 1 << 3; ++ mask) {
                for (int subMask = mask; subMask > 0; subMask = subMask - 1 & mask) {
                    if (i == k && (subMask & 1) == 0) {
                        continue;
                    }
                    if (i < k && k < i + Integer.bitCount(subMask)) {
                        continue;
                    }
                    ways[i + Integer.bitCount(subMask)][subMask] += ways[i][mask];
                }
            }
        }
        long result = 0;
        for (int mask = 1; mask < 1 << 3; ++ mask) {
            result += ways[n][mask];
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			AlternateColors2Harness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				AlternateColors2Harness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class AlternateColors2Harness {
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
	
	static boolean compareOutput(long expected, long result) { return expected == result; }
	static String formatResult(long res) {
		return String.format("%d", res);
	}
	
	static int verifyCase(int casenum, long expected, long received) { 
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
			int n                     = 1;
			int k                     = 1;
			long expected__           = 1;

			return verifyCase(casenum__, expected__, new AlternateColors2().countWays(n, k));
		}
		case 1: {
			int n                     = 3;
			int k                     = 3;
			long expected__           = 3;

			return verifyCase(casenum__, expected__, new AlternateColors2().countWays(n, k));
		}
		case 2: {
			int n                     = 6;
			int k                     = 4;
			long expected__           = 9;

			return verifyCase(casenum__, expected__, new AlternateColors2().countWays(n, k));
		}
		case 3: {
			int n                     = 6;
			int k                     = 1;
			long expected__           = 21;

			return verifyCase(casenum__, expected__, new AlternateColors2().countWays(n, k));
		}
		case 4: {
			int n                     = 1000;
			int k                     = 2;
			long expected__           = 1;

			return verifyCase(casenum__, expected__, new AlternateColors2().countWays(n, k));
		}
		case 5: {
			int n                     = 100000;
			int k                     = 100000;
			long expected__           = 1666700000;

			return verifyCase(casenum__, expected__, new AlternateColors2().countWays(n, k));
		}

		// custom cases

/*      case 6: {
			int n                     = ;
			int k                     = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new AlternateColors2().countWays(n, k));
		}*/
/*      case 7: {
			int n                     = ;
			int k                     = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new AlternateColors2().countWays(n, k));
		}*/
/*      case 8: {
			int n                     = ;
			int k                     = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new AlternateColors2().countWays(n, k));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
