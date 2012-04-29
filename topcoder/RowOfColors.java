import java.math.*;
import java.util.*;

public class RowOfColors {
    final static int MOD = 1000000007;

    void memset(int a[][]) {
        for (int[] b: a) {
            Arrays.fill(b, 0);
        }
    }

    public int countWays(int w, int h, int k) {
        // [length][stackSize][componentCount]
        int ways[][] = new int[h + 1][k + 1];
        memset(ways);
        ways[0][0] = 1;
        for (int length = 0; length < w; ++ length) {
            int newWays[][] = new int[h + 1][k + 1];
            memset(newWays);
            for (int stackSize = 0; stackSize <= h; ++ stackSize) {
                for (int colorCount = 0; colorCount <= k; ++ colorCount) {
                    int value = ways[stackSize][colorCount];
                    if (stackSize > 0) {
                        newWays[stackSize - 1][colorCount] += value;
                        newWays[stackSize - 1][colorCount] %= MOD;
                        newWays[stackSize][colorCount] += value;
                        newWays[stackSize][colorCount] %= MOD;
                    }
                    if (colorCount + 1 <= k && stackSize + 1 <= h) {
                        newWays[stackSize][colorCount + 1] += value;
                        newWays[stackSize][colorCount + 1] %= MOD;
                        newWays[stackSize + 1][colorCount + 1] += value;
                        newWays[stackSize + 1][colorCount + 1] %= MOD;
                    }
                }
            }
            ways = newWays;
        }
        int result = ways[0][k];
        for (int i = 1; i <= k; ++ i) {
            result = (int)((long)result * i % MOD);
        }
        return result;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			RowOfColorsHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				RowOfColorsHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class RowOfColorsHarness {
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
	
	static boolean compareOutput(int expected, int result) { return expected == result; }
	static String formatResult(int res) {
		return String.format("%d", res);
	}
	
	static int verifyCase(int casenum, int expected, int received) { 
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
			int w                     = 4;
			int h                     = 1;
			int k                     = 2;
			int expected__            = 6;

			return verifyCase(casenum__, expected__, new RowOfColors().countWays(w, h, k));
		}
		case 1: {
			int w                     = 4;
			int h                     = 3;
			int k                     = 2;
			int expected__            = 12;

			return verifyCase(casenum__, expected__, new RowOfColors().countWays(w, h, k));
		}
		case 2: {
			int w                     = 4;
			int h                     = 4;
			int k                     = 10;
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new RowOfColors().countWays(w, h, k));
		}
		case 3: {
			int w                     = 14;
			int h                     = 28;
			int k                     = 14;
			int expected__            = 178290591;

			return verifyCase(casenum__, expected__, new RowOfColors().countWays(w, h, k));
		}

		// custom cases

/*      case 4: {
			int w                     = ;
			int h                     = ;
			int k                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new RowOfColors().countWays(w, h, k));
		}*/
/*      case 5: {
			int w                     = ;
			int h                     = ;
			int k                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new RowOfColors().countWays(w, h, k));
		}*/
/*      case 6: {
			int w                     = ;
			int h                     = ;
			int k                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new RowOfColors().countWays(w, h, k));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
