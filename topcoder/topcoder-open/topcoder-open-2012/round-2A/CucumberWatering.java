import java.math.*;
import java.util.*;

public class CucumberWatering {
    final static long INF = 1000000000000L;

    public long theMin(int[] x, int m) {
        int n = x.length;
        long[] xs = new long[n + 2];
        for (int i = 0; i < n; ++ i) {
            xs[i] = x[i];
        }
        xs[n] = -INF;
        xs[n + 1] = INF;
        Arrays.sort(xs);
        m ++;
        long[] mininmum = new long[n + 2];
        Arrays.fill(mininmum, INF);
        mininmum[0] = 0;
        while (m > 0) {
            m --;
            for (int j = n + 1; j >= 0; -- j) {
                for (int i = 0; i < j; ++ i) {
                    boolean[] in = new boolean[n];
                    for (int k = 0; k < n; ++ k) {
                        in[k] = xs[i] <= x[k] && x[k] <= xs[j];
                    }
                    long extra = 0;
                    long total = xs[j] - xs[i];
                    for (int k = 1; k < n; ++ k) {
                        if (in[k - 1] && in[k]) {
                            int length = Math.abs(x[k] - x[k - 1]);
                            extra += Math.min(length, total - length);
                        } else {
                            if (in[k - 1]) {
                                extra += Math.min(x[k - 1] - xs[i], xs[j] - x[k - 1]);
                            }
                            if (in[k]) {
                                extra += Math.min(x[k] - xs[i], xs[j] - x[k]);
                            }
                        }
                    }
                    mininmum[j] = Math.min(mininmum[j], mininmum[i] + extra);
                }
            }
        }
        return mininmum[n + 1];
    }
    

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			CucumberWateringHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				CucumberWateringHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class CucumberWateringHarness {
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
			int[] x                   = {0, 6, 8, 2};
			int K                     = 2;
			long expected__           = 6;

			return verifyCase(casenum__, expected__, new CucumberWatering().theMin(x, K));
		}
		case 1: {
			int[] x                   = {-1000000000, 1000000000, 0};
			int K                     = 1;
			long expected__           = 3000000000L;

			return verifyCase(casenum__, expected__, new CucumberWatering().theMin(x, K));
		}
		case 2: {
			int[] x                   = {58, 2012};
			int K                     = 50;
			long expected__           = 0;

			return verifyCase(casenum__, expected__, new CucumberWatering().theMin(x, K));
		}
		case 3: {
			int[] x                   = {9, -3, 14, 6, 5, -9, 32, 7, -5, 26, 2, 11};
			int K                     = 3;
			long expected__           = 58;

			return verifyCase(casenum__, expected__, new CucumberWatering().theMin(x, K));
		}

		// custom cases

/*      case 4: {
			int[] x                   = ;
			int K                     = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new CucumberWatering().theMin(x, K));
		}*/
/*      case 5: {
			int[] x                   = ;
			int K                     = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new CucumberWatering().theMin(x, K));
		}*/
/*      case 6: {
			int[] x                   = ;
			int K                     = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new CucumberWatering().theMin(x, K));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
