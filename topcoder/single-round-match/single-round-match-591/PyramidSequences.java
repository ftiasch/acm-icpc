import java.math.*;
import java.util.*;

public class PyramidSequences {
    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public long distinctPairs(int n, int m) {
        n --;
        m --;
        int g = gcd(n, m);
        n /= g;
        m /= g;
        return (long)n * m * (g - 1) + (long)(n + 1) * (m + 1) / 2;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			PyramidSequencesHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				PyramidSequencesHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class PyramidSequencesHarness {
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
			int N                     = 3;
			int M                     = 4;
			long expected__           = 6;

			return verifyCase(casenum__, expected__, new PyramidSequences().distinctPairs(N, M));
		}
		case 1: {
			int N                     = 3;
			int M                     = 5;
			long expected__           = 5;

			return verifyCase(casenum__, expected__, new PyramidSequences().distinctPairs(N, M));
		}
		case 2: {
			int N                     = 43;
			int M                     = 76;
			long expected__           = 895;

			return verifyCase(casenum__, expected__, new PyramidSequences().distinctPairs(N, M));
		}
		case 3: {
			int N                     = 2;
			int M                     = 1000000000;
			long expected__           = 1000000000;

			return verifyCase(casenum__, expected__, new PyramidSequences().distinctPairs(N, M));
		}
		case 4: {
			int N                     = 100000;
			int M                     = 95555;
			long expected__           = 4777750000L;

			return verifyCase(casenum__, expected__, new PyramidSequences().distinctPairs(N, M));
		}

		// custom cases

/*      case 5: {
			int N                     = ;
			int M                     = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new PyramidSequences().distinctPairs(N, M));
		}*/
/*      case 6: {
			int N                     = ;
			int M                     = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new PyramidSequences().distinctPairs(N, M));
		}*/
/*      case 7: {
			int N                     = ;
			int M                     = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new PyramidSequences().distinctPairs(N, M));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
