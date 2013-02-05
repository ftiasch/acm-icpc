import java.math.*;
import java.util.*;

public class SPartition {
    public long getCount(String string) {
        int n = string.length();
        int[] symbols = new int[n];
        for (int i = 0; i < n; ++ i) {
            symbols[i] = string.charAt(i) == 'o' ? 0 : 1;
        }
        int m = n >> 1;
        long[][] ways = new long[m + 1][];
        long[][] newWays = new long[m + 1][];
        for (int i = 0; i <= m; ++ i) {
            ways[i] = new long[1 << i];
            newWays[i] = new long[1 << i];
        }
        for (int i = 0; i <= m; ++ i) {
            Arrays.fill(ways[i], 0);
        }
        ways[0][0] = 1;
        for (int symbol : symbols) {
            for (int i = 0; i <= m; ++ i) {
                Arrays.fill(newWays[i], 0);
            }
            for (int i = 0; i <= m; ++ i) {
                for (int mask = 0; mask < 1 << i; ++ mask) {
                    long value = ways[i][mask];
                    if (value != 0) {
                        if (i == 0) {
                            newWays[1][symbol] += value * 2;
                        } else {
                            if (i + 1 <= m) {
                                newWays[i + 1][mask | (symbol << i)] += value;
                            }
                            if ((mask & 1) == symbol) {
                                newWays[i - 1][mask >> 1] += value;
                            }
                        }
                    }
                }
            }
            long[][] t = ways;
            ways = newWays;
            newWays = t;
        }
        return ways[0][0];
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			SPartitionHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				SPartitionHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class SPartitionHarness {
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
			String s                  = "oxox";
			long expected__           = 2;

			return verifyCase(casenum__, expected__, new SPartition().getCount(s));
		}
		case 1: {
			String s                  = "oooxxx";
			long expected__           = 0;

			return verifyCase(casenum__, expected__, new SPartition().getCount(s));
		}
		case 2: {
			String s                  = "xoxxox";
			long expected__           = 4;

			return verifyCase(casenum__, expected__, new SPartition().getCount(s));
		}
		case 3: {
			String s                  = "xo";
			long expected__           = 0;

			return verifyCase(casenum__, expected__, new SPartition().getCount(s));
		}
		case 4: {
			String s                  = "ooooxoox";
			long expected__           = 8;

			return verifyCase(casenum__, expected__, new SPartition().getCount(s));
		}
		case 5: {
			String s                  = "ooxxoxox";
			long expected__           = 8;

			return verifyCase(casenum__, expected__, new SPartition().getCount(s));
		}

		// custom cases

/*      case 6: {
			String s                  = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new SPartition().getCount(s));
		}*/
/*      case 7: {
			String s                  = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new SPartition().getCount(s));
		}*/
/*      case 8: {
			String s                  = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new SPartition().getCount(s));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
