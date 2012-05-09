import java.math.*;
import java.util.*;

public class PatrolRoute {
    final static int MOD = 1000000007;

    public int countRoutes(int n, int m, int min, int max) {
        long result = 0;
        for (int i = 2; i < n; ++ i) {
            // (i + j) >= (min + 1) / 2
            for (int j = Math.max(2, ((min + 1) >> 1) - i); j < m && ((i + j) << 1) <= max; ++ j) {
                long ways = (long)(n - i) * (i - 1) * (m - j) * (j - 1);
                result = (result + ways) % MOD;
            }
        }
        return (int)((6 * result) % MOD);
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			PatrolRouteHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				PatrolRouteHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class PatrolRouteHarness {
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
			int X                     = 3;
			int Y                     = 3;
			int minT                  = 1;
			int maxT                  = 20000;
			int expected__            = 6;

			return verifyCase(casenum__, expected__, new PatrolRoute().countRoutes(X, Y, minT, maxT));
		}
		case 1: {
			int X                     = 3;
			int Y                     = 3;
			int minT                  = 4;
			int maxT                  = 7;
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new PatrolRoute().countRoutes(X, Y, minT, maxT));
		}
		case 2: {
			int X                     = 4;
			int Y                     = 6;
			int minT                  = 9;
			int maxT                  = 12;
			int expected__            = 264;

			return verifyCase(casenum__, expected__, new PatrolRoute().countRoutes(X, Y, minT, maxT));
		}
		case 3: {
			int X                     = 7;
			int Y                     = 5;
			int minT                  = 13;
			int maxT                  = 18;
			int expected__            = 1212;

			return verifyCase(casenum__, expected__, new PatrolRoute().countRoutes(X, Y, minT, maxT));
		}
		case 4: {
			int X                     = 4000;
			int Y                     = 4000;
			int minT                  = 4000;
			int maxT                  = 14000;
			int expected__            = 859690013;

			return verifyCase(casenum__, expected__, new PatrolRoute().countRoutes(X, Y, minT, maxT));
		}

		// custom cases

/*      case 5: {
			int X                     = ;
			int Y                     = ;
			int minT                  = ;
			int maxT                  = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new PatrolRoute().countRoutes(X, Y, minT, maxT));
		}*/
/*      case 6: {
			int X                     = ;
			int Y                     = ;
			int minT                  = ;
			int maxT                  = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new PatrolRoute().countRoutes(X, Y, minT, maxT));
		}*/
/*      case 7: {
			int X                     = ;
			int Y                     = ;
			int minT                  = ;
			int maxT                  = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new PatrolRoute().countRoutes(X, Y, minT, maxT));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
