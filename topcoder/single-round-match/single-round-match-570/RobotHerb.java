import java.math.*;
import java.util.*;

public class RobotHerb {
    final static int[] DELTA_X = {-1,  0, 1, 0};
    final static int[] DELTA_Y = { 0, -1, 0, 1};

    public long getdist(int times, int[] a) {
        int n = a.length;
        long x = 0;
        long y = 0;
        int d = 0;
        if (times >= 4) {
            for (int _ = 0; _ < 4; ++ _) {
                for (int i = 0; i < n; ++ i) {
                    x += DELTA_X[d] * a[i];
                    y += DELTA_Y[d] * a[i];
                    d = (d + a[i]) % 4;
                }
            }
            x *= times / 4;
            y *= times / 4;
            times %= 4;
        }
        for (int _ = 0; _ < times; ++ _) {
            for (int i = 0; i < n; ++ i) {
                x += DELTA_X[d] * a[i];
                y += DELTA_Y[d] * a[i];
                d = (d + a[i]) % 4;
            }
        }
        return Math.abs(x) + Math.abs(y);
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			RobotHerbHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				RobotHerbHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class RobotHerbHarness {
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
			int T                     = 1;
			int[] a                   = {1,2,3};
			long expected__           = 2;

			return verifyCase(casenum__, expected__, new RobotHerb().getdist(T, a));
		}
		case 1: {
			int T                     = 100;
			int[] a                   = {1};
			long expected__           = 0;

			return verifyCase(casenum__, expected__, new RobotHerb().getdist(T, a));
		}
		case 2: {
			int T                     = 5;
			int[] a                   = {1,1,2};
			long expected__           = 10;

			return verifyCase(casenum__, expected__, new RobotHerb().getdist(T, a));
		}
		case 3: {
			int T                     = 1000000000;
			int[] a                   = {100};
			long expected__           = 100000000000L;

			return verifyCase(casenum__, expected__, new RobotHerb().getdist(T, a));
		}
		case 4: {
			int T                     = 570;
			int[] a                   = {2013,2,13,314,271,1414,1732};
			long expected__           = 4112;

			return verifyCase(casenum__, expected__, new RobotHerb().getdist(T, a));
		}

		// custom cases

/*      case 5: {
			int T                     = ;
			int[] a                   = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new RobotHerb().getdist(T, a));
		}*/
/*      case 6: {
			int T                     = ;
			int[] a                   = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new RobotHerb().getdist(T, a));
		}*/
/*      case 7: {
			int T                     = ;
			int[] a                   = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new RobotHerb().getdist(T, a));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
