import java.math.*;
import java.util.*;

public class ImportantSequence {
    final static long INF = 1000000000000000000L;

    public int getCount(int[] numbers, String operators) {
        int n = numbers.length;
        long min = 1;
        long max = INF;
        int sign = 1;
        long delta = 0;
        for (int i = 0; i < n; ++ i) {
            if (operators.charAt(i) == '+') {
                sign *= -1;
                delta *= -1;
                delta += numbers[i];
            } else {
                delta -= numbers[i];
            }
            if (sign > 0) {
                min = Math.max(min, -delta + 1);
            } else {
                max = Math.min(max, delta - 1);
            }
        }
        if (max == INF) {
            return -1;
        }
        return (int)Math.max(max - min + 1, 0);
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			ImportantSequenceHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				ImportantSequenceHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class ImportantSequenceHarness {
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
			int[] B                   = {3, -1, 7};
			String operators          = "+-+";
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new ImportantSequence().getCount(B, operators));
		}
		case 1: {
			int[] B                   = {1};
			String operators          = "-";
			int expected__            = -1;

			return verifyCase(casenum__, expected__, new ImportantSequence().getCount(B, operators));
		}
		case 2: {
			int[] B                   = {1};
			String operators          = "+";
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new ImportantSequence().getCount(B, operators));
		}
		case 3: {
			int[] B                   = {10};
			String operators          = "+";
			int expected__            = 9;

			return verifyCase(casenum__, expected__, new ImportantSequence().getCount(B, operators));
		}
		case 4: {
			int[] B                   = {540, 2012, 540, 2012, 540, 2012, 540};
			String operators          = "-+-+-+-";
			int expected__            = 1471;

			return verifyCase(casenum__, expected__, new ImportantSequence().getCount(B, operators));
		}

		// custom cases

/*      case 5: {
			int[] B                   = ;
			String operators          = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new ImportantSequence().getCount(B, operators));
		}*/
/*      case 6: {
			int[] B                   = ;
			String operators          = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new ImportantSequence().getCount(B, operators));
		}*/
/*      case 7: {
			int[] B                   = ;
			String operators          = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new ImportantSequence().getCount(B, operators));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
