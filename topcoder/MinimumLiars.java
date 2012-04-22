import java.math.*;
import java.util.*;

public class MinimumLiars {
    public int getMinimum(int[] claim) {
        int n = claim.length;
        int result = 0;
        while (true) {
            int count = 0;
            for (int i = 0; i < n; ++ i) {
                if (result < claim[i]) {
                    count ++;
                }
            }
            if (count == result) {
                break;
            }
            result ++;
            if (result > n) {
                break;
            }
        }
        if (result <= n) {
            return result;
        }
        return -1;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			MinimumLiarsHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				MinimumLiarsHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class MinimumLiarsHarness {
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
			int[] claim               = {1,1,1,2};
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new MinimumLiars().getMinimum(claim));
		}
		case 1: {
			int[] claim               = {7,8,1};
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new MinimumLiars().getMinimum(claim));
		}
		case 2: {
			int[] claim               = {5,5,5,5,5};
			int expected__            = -1;

			return verifyCase(casenum__, expected__, new MinimumLiars().getMinimum(claim));
		}
		case 3: {
			int[] claim               = {0,0,0,4,3,0};
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new MinimumLiars().getMinimum(claim));
		}
		case 4: {
			int[] claim               = {4,7,5};
			int expected__            = 3;

			return verifyCase(casenum__, expected__, new MinimumLiars().getMinimum(claim));
		}

		// custom cases

/*      case 5: {
			int[] claim               = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new MinimumLiars().getMinimum(claim));
		}*/
/*      case 6: {
			int[] claim               = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new MinimumLiars().getMinimum(claim));
		}*/
/*      case 7: {
			int[] claim               = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new MinimumLiars().getMinimum(claim));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
