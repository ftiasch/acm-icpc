import java.util.*;
import java.math.*;

public class TheOlympiadInInformatics {
    long solve(int[] sums, int limit) {
        long ret = 0;
        for (int sum : sums) {
            ret += sum / (limit + 1);
        }
        return ret;
    }

    public int find(int[] sums, int k) {		
        int low = 0;
        int high = 1000000000;
        while (low < high) {
            int middle = low + high >> 1;
            if (solve(sums, middle) <= k) {
                high = middle;
            } else {
                low = middle + 1;
            }
        }
        return high;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			TheOlympiadInInformaticsHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				TheOlympiadInInformaticsHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class TheOlympiadInInformaticsHarness {
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
			int[] sums                = {4, 7, 0, 5};
			int k                     = 0;
			int expected__            = 7;

			return verifyCase(casenum__, expected__, new TheOlympiadInInformatics().find(sums, k));
		}
		case 1: {
			int[] sums                = {4, 7};
			int k                     = 2;
			int expected__            = 3;

			return verifyCase(casenum__, expected__, new TheOlympiadInInformatics().find(sums, k));
		}
		case 2: {
			int[] sums                = {999999999};
			int k                     = 1000000000;
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new TheOlympiadInInformatics().find(sums, k));
		}
		case 3: {
			int[] sums                = {95, 23, 87, 23, 82, 78, 59, 44, 12};
			int k                     = 70;
			int expected__            = 6;

			return verifyCase(casenum__, expected__, new TheOlympiadInInformatics().find(sums, k));
		}

		// custom cases

/*      case 4: {
			int[] sums                = ;
			int k                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TheOlympiadInInformatics().find(sums, k));
		}*/
/*      case 5: {
			int[] sums                = ;
			int k                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TheOlympiadInInformatics().find(sums, k));
		}*/
/*      case 6: {
			int[] sums                = ;
			int k                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TheOlympiadInInformatics().find(sums, k));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
