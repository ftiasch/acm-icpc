import java.math.*;
import java.util.*;

public class TripleStrings {
    public int getMinimumOperations(String init, String goal) {
        int n = init.length();
        int result = Integer.MAX_VALUE;
        for (int i = -1; i < n; ++ i) {
            if (goal.startsWith(init.substring(i + 1, n))) {
                result = Math.min(result, (i + 1) * 2);
            }
        }
        return result;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			TripleStringsHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				TripleStringsHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class TripleStringsHarness {
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
			String init               = "ooxxox";
			String goal               = "xoxoxo";
			int expected__            = 6;

			return verifyCase(casenum__, expected__, new TripleStrings().getMinimumOperations(init, goal));
		}
		case 1: {
			String init               = "oooxxoo";
			String goal               = "oooxxoo";
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new TripleStrings().getMinimumOperations(init, goal));
		}
		case 2: {
			String init               = "ox";
			String goal               = "xo";
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new TripleStrings().getMinimumOperations(init, goal));
		}
		case 3: {
			String init               = "ooxxooxx";
			String goal               = "xxoxoxoo";
			int expected__            = 12;

			return verifyCase(casenum__, expected__, new TripleStrings().getMinimumOperations(init, goal));
		}
		case 4: {
			String init               = "oxxoxxoooxooooxxxoo";
			String goal               = "oxooooxxxooooxoxxxo";
			int expected__            = 16;

			return verifyCase(casenum__, expected__, new TripleStrings().getMinimumOperations(init, goal));
		}
		case 5: {
			String init               = "xxxoxoxxooxooxoxooo";
			String goal               = "oxxooxxooxxoxoxooxo";
			int expected__            = 36;

			return verifyCase(casenum__, expected__, new TripleStrings().getMinimumOperations(init, goal));
		}

		// custom cases

/*      case 6: {
			String init               = ;
			String goal               = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TripleStrings().getMinimumOperations(init, goal));
		}*/
/*      case 7: {
			String init               = ;
			String goal               = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TripleStrings().getMinimumOperations(init, goal));
		}*/
/*      case 8: {
			String init               = ;
			String goal               = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TripleStrings().getMinimumOperations(init, goal));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
