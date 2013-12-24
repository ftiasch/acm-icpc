import java.math.*;
import java.util.*;

public class WinterAndPresents {
    public long getNumber(int[] apple, int[] orange) {
        int n = apple.length;
        int m = 2000000;
        for (int i = 0; i < n; ++ i) {
            m = Math.min(m, apple[i] + orange[i]);
        }
        long result = 0;
        for (int x = 1; x <= m; ++ x) {
            int minApple = 0;
            int maxApple = 0;
            for (int i = 0; i < n; ++ i) {
                maxApple += Math.min(apple[i], x);
                minApple += x - Math.min(orange[i], x);
            }
            result += maxApple - minApple + 1;
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			WinterAndPresentsHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				WinterAndPresentsHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class WinterAndPresentsHarness {
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
			int[] apple               = {1};
			int[] orange              = {1};
			long expected__           = 3;

			return verifyCase(casenum__, expected__, new WinterAndPresents().getNumber(apple, orange));
		}
		case 1: {
			int[] apple               = {1, 2, 0, 3};
			int[] orange              = {4, 5, 0, 6};
			long expected__           = 0;

			return verifyCase(casenum__, expected__, new WinterAndPresents().getNumber(apple, orange));
		}
		case 2: {
			int[] apple               = {2, 2, 2};
			int[] orange              = {2, 2, 2};
			long expected__           = 16;

			return verifyCase(casenum__, expected__, new WinterAndPresents().getNumber(apple, orange));
		}
		case 3: {
			int[] apple               = {7, 4, 5};
			int[] orange              = {1, 10, 2};
			long expected__           = 46;

			return verifyCase(casenum__, expected__, new WinterAndPresents().getNumber(apple, orange));
		}
		case 4: {
			int[] apple               = {1000000};
			int[] orange              = {1000000};
			long expected__           = 1000002000000L;

			return verifyCase(casenum__, expected__, new WinterAndPresents().getNumber(apple, orange));
		}

		// custom cases

/*      case 5: {
			int[] apple               = ;
			int[] orange              = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new WinterAndPresents().getNumber(apple, orange));
		}*/
/*      case 6: {
			int[] apple               = ;
			int[] orange              = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new WinterAndPresents().getNumber(apple, orange));
		}*/
/*      case 7: {
			int[] apple               = ;
			int[] orange              = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new WinterAndPresents().getNumber(apple, orange));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
