import java.math.*;
import java.util.*;

public class FoxAndDoraemon {
    int n;
    int[][][] memory;

    final static int INF = 1000000000;

    int solve(int[] workCost, int splitCost, int split, int fox, int work) {
        if (work < n && fox == 0) {
            return INF;
        }
        if (work + fox >= n) {
            return split * splitCost + workCost[n - work - 1];
        }
        if (memory[split][fox][work] == -1) {
            int ret = INF;
            for (int i = 0; i <= fox && work + i <= n; ++ i) {
                ret = Math.min(ret, Math.max(solve(workCost, splitCost, split + 1, Math.min((fox - i) * 2, n), work + i), split * splitCost + workCost[n - work - 1]));
            }
            memory[split][fox][work] = ret;
        }
        return memory[split][fox][work];
    }

    public int minTime(int[] workCost, int splitCost) {
        Arrays.sort(workCost);
        n = workCost.length;
        memory = new int[n + 1][n + 1][n + 1];
        for (int i = 0; i <= n; ++ i) {
            for (int j = 0; j <= n; ++ j) {
                for (int k = 0; k <= n; ++ k) {
                    memory[i][j][k] = -1;
                }
            }
        }
        return solve(workCost, splitCost, 0, 1, 0);
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			FoxAndDoraemonHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				FoxAndDoraemonHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class FoxAndDoraemonHarness {
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
			int[] workCost            = {1,2};
			int splitCost             = 1000;
			int expected__            = 1002;

			return verifyCase(casenum__, expected__, new FoxAndDoraemon().minTime(workCost, splitCost));
		}
		case 1: {
			int[] workCost            = {3,6,9,12};
			int splitCost             = 1000;
			int expected__            = 2012;

			return verifyCase(casenum__, expected__, new FoxAndDoraemon().minTime(workCost, splitCost));
		}
		case 2: {
			int[] workCost            = {1000,100,10,1};
			int splitCost             = 1;
			int expected__            = 1001;

			return verifyCase(casenum__, expected__, new FoxAndDoraemon().minTime(workCost, splitCost));
		}
		case 3: {
			int[] workCost            = {1712,1911,1703,1610,1697,1612};
			int splitCost             = 100;
			int expected__            = 2012;

			return verifyCase(casenum__, expected__, new FoxAndDoraemon().minTime(workCost, splitCost));
		}
		case 4: {
			int[] workCost            = {3000,3000,3000,3000,3000,3000,3000,3000,3000,3000};
			int splitCost             = 3000;
			int expected__            = 15000;

			return verifyCase(casenum__, expected__, new FoxAndDoraemon().minTime(workCost, splitCost));
		}
		case 5: {
			int[] workCost            = {58};
			int splitCost             = 3600;
			int expected__            = 58;

			return verifyCase(casenum__, expected__, new FoxAndDoraemon().minTime(workCost, splitCost));
		}

		// custom cases

/*      case 6: {
			int[] workCost            = ;
			int splitCost             = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new FoxAndDoraemon().minTime(workCost, splitCost));
		}*/
/*      case 7: {
			int[] workCost            = ;
			int splitCost             = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new FoxAndDoraemon().minTime(workCost, splitCost));
		}*/
/*      case 8: {
			int[] workCost            = ;
			int splitCost             = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new FoxAndDoraemon().minTime(workCost, splitCost));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
