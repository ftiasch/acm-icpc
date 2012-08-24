import java.math.*;
import java.util.*;

public class TwoLotteryGames {
    public double getHigherChanceGame(int n, int m, int k) {
        int mask_0 = (1 << m) - 1;
        int[] ones = new int[1 << n];
        ones[0] = 0;
        for (int mask = 1; mask < (1 << n); ++ mask) {
            ones[mask] = ones[mask >> 1] + (mask & 1);
        }
        int valid = 0;
        int total = 0;
        for (int mask = 0; mask < (1 << n); ++ mask) {
            if (ones[mask] == m) {
                total ++;
                if (ones[mask & mask_0] >= k) {
                    valid ++;
                }
            }
        }
        return (double)valid / total;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			TwoLotteryGamesHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				TwoLotteryGamesHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class TwoLotteryGamesHarness {
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
	
	static final double MAX_DOUBLE_ERROR = 1E-9;
	static boolean compareOutput(double expected, double result){ if(Double.isNaN(expected)){ return Double.isNaN(result); }else if(Double.isInfinite(expected)){ if(expected > 0){ return result > 0 && Double.isInfinite(result); }else{ return result < 0 && Double.isInfinite(result); } }else if(Double.isNaN(result) || Double.isInfinite(result)){ return false; }else if(Math.abs(result - expected) < MAX_DOUBLE_ERROR){ return true; }else{ double min = Math.min(expected * (1.0 - MAX_DOUBLE_ERROR), expected * (1.0 + MAX_DOUBLE_ERROR)); double max = Math.max(expected * (1.0 - MAX_DOUBLE_ERROR), expected * (1.0 + MAX_DOUBLE_ERROR)); return result > min && result < max; } }
	static double relativeError(double expected, double result) { if (Double.isNaN(expected) || Double.isInfinite(expected) || Double.isNaN(result) || Double.isInfinite(result) || expected == 0) return 0; return Math.abs(result-expected) / Math.abs(expected); }
	
	static String formatResult(double res) {
		return String.format("%.10g", res);
	}
	
	static int verifyCase(int casenum, double expected, double received) { 
		System.err.print("Example " + casenum + "... ");
		if (compareOutput(expected, received)) {
			System.err.print("PASSED");
			double rerr = relativeError(expected, received);
			if (rerr > 0) System.err.printf(" (relative error %g)", rerr);
			System.err.println();
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
			int n                     = 3;
			int m                     = 2;
			int k                     = 1;
			double expected__         = 1.0;

			return verifyCase(casenum__, expected__, new TwoLotteryGames().getHigherChanceGame(n, m, k));
		}
		case 1: {
			int n                     = 3;
			int m                     = 1;
			int k                     = 1;
			double expected__         = 0.3333333333333333;

			return verifyCase(casenum__, expected__, new TwoLotteryGames().getHigherChanceGame(n, m, k));
		}
		case 2: {
			int n                     = 8;
			int m                     = 2;
			int k                     = 1;
			double expected__         = 0.4642857142857143;

			return verifyCase(casenum__, expected__, new TwoLotteryGames().getHigherChanceGame(n, m, k));
		}
		case 3: {
			int n                     = 8;
			int m                     = 4;
			int k                     = 2;
			double expected__         = 0.7571428571428571;

			return verifyCase(casenum__, expected__, new TwoLotteryGames().getHigherChanceGame(n, m, k));
		}

		// custom cases

/*      case 4: {
			int n                     = ;
			int m                     = ;
			int k                     = ;
			double expected__         = ;

			return verifyCase(casenum__, expected__, new TwoLotteryGames().getHigherChanceGame(n, m, k));
		}*/
/*      case 5: {
			int n                     = ;
			int m                     = ;
			int k                     = ;
			double expected__         = ;

			return verifyCase(casenum__, expected__, new TwoLotteryGames().getHigherChanceGame(n, m, k));
		}*/
/*      case 6: {
			int n                     = ;
			int m                     = ;
			int k                     = ;
			double expected__         = ;

			return verifyCase(casenum__, expected__, new TwoLotteryGames().getHigherChanceGame(n, m, k));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
