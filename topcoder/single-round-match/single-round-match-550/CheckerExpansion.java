import java.math.*;
import java.util.*;

public class CheckerExpansion {
    private char getChar(long t, long x, long y) {
        if ((x + y) % 2 == 1 || (x + y) / 2 >= t || x < y) {
            return '.';
        }
        long a = (x + y) / 2;
        long b = (x - y) / 2;
        if ((a & b) != b) {
            return '.';
        }
        return (a & 1) == 0 ? 'A' : 'B';
    }

    public String[] resultAfter(long t, long x0, long y0, int w, int h) {
        String[] result = new String[h];
        for (int i = 0; i < h; ++ i) {
            result[i] = new String();
            for (int j = 0; j < w; ++ j) {
                result[i] += getChar(t, x0 + j, y0 + h - 1 - i);
            }
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			CheckerExpansionHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				CheckerExpansionHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class CheckerExpansionHarness {
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
	
	static boolean compareOutput(String[] expected, String[] result) { if (expected.length != result.length) return false; for (int i=0; i<expected.length; ++i) if (!expected[i].equals(result[i])) return false; return true; }

	static String formatResult(String[] res) {
		String ret = "";
		ret += "{";
		for (int i=0; i<res.length; ++i) {
			if (i > 0) ret += ",";
			ret += String.format(" \"%s\"", res[i]);
		}
		ret += " }";
		return ret;
	}
	
	static int verifyCase(int casenum, String[] expected, String[] received) { 
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
			long t                    = 1;
			long x0                   = 0;
			long y0                   = 0;
			int w                     = 4;
			int h                     = 4;
			String[] expected__       = {"....", "....", "....", "A..." };

			return verifyCase(casenum__, expected__, new CheckerExpansion().resultAfter(t, x0, y0, w, h));
		}
		case 1: {
			long t                    = 5;
			long x0                   = 4;
			long y0                   = 1;
			int w                     = 3;
			int h                     = 4;
			String[] expected__       = {"A..", "...", "B..", ".B." };

			return verifyCase(casenum__, expected__, new CheckerExpansion().resultAfter(t, x0, y0, w, h));
		}
		case 2: {
			long t                    = 1024;
			long x0                   = 1525;
			long y0                   = 512;
			int w                     = 20;
			int h                     = 2;
			String[] expected__       = {"B...B...B...........", ".B.A.B.A.B.........." };

			return verifyCase(casenum__, expected__, new CheckerExpansion().resultAfter(t, x0, y0, w, h));
		}
		case 3: {
			long t                    = 53;
			long x0                   = 85;
			long y0                   = 6;
			int w                     = 5;
			int h                     = 14;
			String[] expected__       = {".....", ".....", "B....", ".B.A.", ".....", ".....", ".....", ".....", ".....", ".....", "B....", ".B...", "..B..", ".A.B." };

			return verifyCase(casenum__, expected__, new CheckerExpansion().resultAfter(t, x0, y0, w, h));
		}

		// custom cases

      /*case 4: {
			long t                    = ;
			long x0                   = ;
			long y0                   = ;
			int w                     = ;
			int h                     = ;
			String[] expected__       = ;

			return verifyCase(casenum__, expected__, new CheckerExpansion().resultAfter(t, x0, y0, w, h));
		}*/
/*      case 5: {
			long t                    = ;
			long x0                   = ;
			long y0                   = ;
			int w                     = ;
			int h                     = ;
			String[] expected__       = ;

			return verifyCase(casenum__, expected__, new CheckerExpansion().resultAfter(t, x0, y0, w, h));
		}*/
/*      case 6: {
			long t                    = ;
			long x0                   = ;
			long y0                   = ;
			int w                     = ;
			int h                     = ;
			String[] expected__       = ;

			return verifyCase(casenum__, expected__, new CheckerExpansion().resultAfter(t, x0, y0, w, h));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
