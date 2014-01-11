import java.math.*;
import java.util.*;

public class FoxAndMountainEasy {
    public String possible(int n, int h0, int hn, String history) {
        if ((h0 - hn + n) % 2 != 0) {
            return "NO";
        }
        int delta = 0;
        int minDelta = 0;
        for (int i = 0; i < history.length(); ++ i) {
            if (history.charAt(i) == 'U') {
                delta ++;
            } else {
                delta --;
            }
            minDelta = Math.min(minDelta, delta);
        }
        for (int i = 0; i + history.length() <= n; ++ i) {
            int max = h0 + i;
            int min = h0 - i;
            if (max + minDelta >= 0) {
                min = Math.max(min, -minDelta);
                int left = n - i - history.length();
                if (Math.max(min + delta, hn - left) <= Math.min(max + delta, hn + left)) {
                    return "YES";
                }
            }
        }
        return "NO";
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			FoxAndMountainEasyHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				FoxAndMountainEasyHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class FoxAndMountainEasyHarness {
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
	
	static boolean compareOutput(String expected, String result) { return expected.equals(result); }
	static String formatResult(String res) {
		return String.format("\"%s\"", res);
	}
	
	static int verifyCase(int casenum, String expected, String received) { 
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
			int n                     = 4;
			int h0                    = 0;
			int hn                    = 4;
			String history            = "UU";
			String expected__         = "YES";

			return verifyCase(casenum__, expected__, new FoxAndMountainEasy().possible(n, h0, hn, history));
		}
		case 1: {
			int n                     = 4;
			int h0                    = 0;
			int hn                    = 4;
			String history            = "D";
			String expected__         = "NO";

			return verifyCase(casenum__, expected__, new FoxAndMountainEasy().possible(n, h0, hn, history));
		}
		case 2: {
			int n                     = 4;
			int h0                    = 100000;
			int hn                    = 100000;
			String history            = "DDU";
			String expected__         = "YES";

			return verifyCase(casenum__, expected__, new FoxAndMountainEasy().possible(n, h0, hn, history));
		}
		case 3: {
			int n                     = 4;
			int h0                    = 0;
			int hn                    = 0;
			String history            = "DDU";
			String expected__         = "NO";

			return verifyCase(casenum__, expected__, new FoxAndMountainEasy().possible(n, h0, hn, history));
		}
		case 4: {
			int n                     = 20;
			int h0                    = 20;
			int hn                    = 20;
			String history            = "UDUDUDUDUD";
			String expected__         = "YES";

			return verifyCase(casenum__, expected__, new FoxAndMountainEasy().possible(n, h0, hn, history));
		}
		case 5: {
			int n                     = 20;
			int h0                    = 0;
			int hn                    = 0;
			String history            = "UUUUUUUUUU";
			String expected__         = "YES";

			return verifyCase(casenum__, expected__, new FoxAndMountainEasy().possible(n, h0, hn, history));
		}
		case 6: {
			int n                     = 20;
			int h0                    = 0;
			int hn                    = 0;
			String history            = "UUUUUUUUUUU";
			String expected__         = "NO";

			return verifyCase(casenum__, expected__, new FoxAndMountainEasy().possible(n, h0, hn, history));
		}

		// custom cases

      case 7: {
			int n                     = 6;
			int h0                    = 0;
			int hn                    = 0;
			String history            = "DDUU";
			String expected__         = "NO";

			return verifyCase(casenum__, expected__, new FoxAndMountainEasy().possible(n, h0, hn, history));
		}
/*      case 8: {
			int n                     = ;
			int h0                    = ;
			int hn                    = ;
			String history            = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new FoxAndMountainEasy().possible(n, h0, hn, history));
		}*/
/*      case 9: {
			int n                     = ;
			int h0                    = ;
			int hn                    = ;
			String history            = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new FoxAndMountainEasy().possible(n, h0, hn, history));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
