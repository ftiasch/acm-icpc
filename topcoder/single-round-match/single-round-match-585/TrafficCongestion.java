import java.math.*;
import java.util.*;

public class TrafficCongestion {
    final static int MOD = (int)1e9 + 7;

    public int theMinCars(int treeHeight) {
        treeHeight += 1;
        long answer = treeHeight & 1;
        long pow2 = (treeHeight & 1) == 1 ? 2 : 1;
        for (int i = treeHeight & 1; i < treeHeight; i += 2) {
            answer = (answer + pow2) % MOD;
            pow2 = (pow2 * 4) % MOD;
        }
        return (int)answer;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			TrafficCongestionHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				TrafficCongestionHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class TrafficCongestionHarness {
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
			int treeHeight            = 1;
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new TrafficCongestion().theMinCars(treeHeight));
		}
		case 1: {
			int treeHeight            = 2;
			int expected__            = 3;

			return verifyCase(casenum__, expected__, new TrafficCongestion().theMinCars(treeHeight));
		}
		case 2: {
			int treeHeight            = 3;
			int expected__            = 5;

			return verifyCase(casenum__, expected__, new TrafficCongestion().theMinCars(treeHeight));
		}
		case 3: {
			int treeHeight            = 585858;
			int expected__            = 548973404;

			return verifyCase(casenum__, expected__, new TrafficCongestion().theMinCars(treeHeight));
		}

		// custom cases

/*      case 4: {
			int treeHeight            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TrafficCongestion().theMinCars(treeHeight));
		}*/
/*      case 5: {
			int treeHeight            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TrafficCongestion().theMinCars(treeHeight));
		}*/
/*      case 6: {
			int treeHeight            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TrafficCongestion().theMinCars(treeHeight));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
