import java.math.*;
import java.util.*;

public class PasswordXGrid {
    public int minSum(String[] horizontal, String[] vertical) {
        int n = horizontal.length;
        int m = vertical[0].length();
        int[][] maximum = new int[n][m];
        int answer = 0;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                maximum[i][j] = 0;
                if (i > 0) {
                    maximum[i][j] = Math.max(maximum[i][j], maximum[i - 1][j] + vertical[i - 1].charAt(j) - '0');
                }
                if (j > 0) {
                    maximum[i][j] = Math.max(maximum[i][j], maximum[i][j - 1] + horizontal[i].charAt(j - 1) - '0');
                }
            }
        }
        return maximum[n - 1][m - 1];
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			PasswordXGridHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				PasswordXGridHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class PasswordXGridHarness {
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
			String[] horizontal       = {"1","4"};
			String[] vertical         = {"32"};
			int expected__            = 7;

			return verifyCase(casenum__, expected__, new PasswordXGrid().minSum(horizontal, vertical));
		}
		case 1: {
			String[] horizontal       = {"47", "59"};
			String[] vertical         = {"361"};
			int expected__            = 19;

			return verifyCase(casenum__, expected__, new PasswordXGrid().minSum(horizontal, vertical));
		}
		case 2: {
			String[] horizontal       = {"36", "23", "49"};
			String[] vertical         = {"890", "176"};
			int expected__            = 28;

			return verifyCase(casenum__, expected__, new PasswordXGrid().minSum(horizontal, vertical));
		}
		case 3: {
			String[] horizontal       = {"8888585","5888585"};
			String[] vertical         = {"58585858"};
			int expected__            = 58;

			return verifyCase(casenum__, expected__, new PasswordXGrid().minSum(horizontal, vertical));
		}
		case 4: {
			String[] horizontal       = {"7777777","7777777","7777777","7777777"};
			String[] vertical         = {"44444444","44444444","44444444"};
			int expected__            = 61;

			return verifyCase(casenum__, expected__, new PasswordXGrid().minSum(horizontal, vertical));
		}

		// custom cases

/*      case 5: {
			String[] horizontal       = ;
			String[] vertical         = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new PasswordXGrid().minSum(horizontal, vertical));
		}*/
/*      case 6: {
			String[] horizontal       = ;
			String[] vertical         = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new PasswordXGrid().minSum(horizontal, vertical));
		}*/
/*      case 7: {
			String[] horizontal       = ;
			String[] vertical         = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new PasswordXGrid().minSum(horizontal, vertical));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
