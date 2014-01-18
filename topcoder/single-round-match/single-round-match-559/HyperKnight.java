import java.math.*;
import java.util.*;

public class HyperKnight {
    public long countCells(int a, int b, int n, int m, int k) {
        if (a > b) {
            return countCells(b, a, n, m, k);
        }
        long[] result = new long[9];
        result[8] = (long)(n - b * 2) * (m - b * 2);
        result[6] = (long)((n - b * 2) + (m - b * 2)) * (b - a) * 2;
        result[3] = (long)a * (b - a) * 8;
        result[2] = (long)a * a * 4;
        result[4] = (long)n * m - result[2] - result[3] - result[6] - result[8];
        return result[k];
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			HyperKnightHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				HyperKnightHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class HyperKnightHarness {
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
			int a                     = 2;
			int b                     = 1;
			int numRows               = 8;
			int numColumns            = 8;
			int k                     = 4;
			long expected__           = 20;

			return verifyCase(casenum__, expected__, new HyperKnight().countCells(a, b, numRows, numColumns, k));
		}
		case 1: {
			int a                     = 3;
			int b                     = 2;
			int numRows               = 8;
			int numColumns            = 8;
			int k                     = 2;
			long expected__           = 16;

			return verifyCase(casenum__, expected__, new HyperKnight().countCells(a, b, numRows, numColumns, k));
		}
		case 2: {
			int a                     = 1;
			int b                     = 3;
			int numRows               = 7;
			int numColumns            = 11;
			int k                     = 0;
			long expected__           = 0;

			return verifyCase(casenum__, expected__, new HyperKnight().countCells(a, b, numRows, numColumns, k));
		}
		case 3: {
			int a                     = 3;
			int b                     = 2;
			int numRows               = 10;
			int numColumns            = 20;
			int k                     = 8;
			long expected__           = 56;

			return verifyCase(casenum__, expected__, new HyperKnight().countCells(a, b, numRows, numColumns, k));
		}
		case 4: {
			int a                     = 1;
			int b                     = 4;
			int numRows               = 100;
			int numColumns            = 10;
			int k                     = 6;
			long expected__           = 564;

			return verifyCase(casenum__, expected__, new HyperKnight().countCells(a, b, numRows, numColumns, k));
		}
		case 5: {
			int a                     = 2;
			int b                     = 3;
			int numRows               = 1000000000;
			int numColumns            = 1000000000;
			int k                     = 8;
			long expected__           = 999999988000000036L;

			return verifyCase(casenum__, expected__, new HyperKnight().countCells(a, b, numRows, numColumns, k));
		}

		// custom cases

/*      case 6: {
			int a                     = ;
			int b                     = ;
			int numRows               = ;
			int numColumns            = ;
			int k                     = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new HyperKnight().countCells(a, b, numRows, numColumns, k));
		}*/
/*      case 7: {
			int a                     = ;
			int b                     = ;
			int numRows               = ;
			int numColumns            = ;
			int k                     = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new HyperKnight().countCells(a, b, numRows, numColumns, k));
		}*/
/*      case 8: {
			int a                     = ;
			int b                     = ;
			int numRows               = ;
			int numColumns            = ;
			int k                     = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new HyperKnight().countCells(a, b, numRows, numColumns, k));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
