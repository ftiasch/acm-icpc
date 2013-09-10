import java.math.*;
import java.util.*;

public class FoxAndChess {
    final static String YES = "Possible";
    final static String NO = "Impossible";

    public String ableToMove(String begin, String target) {
        int n = begin.length();
        int j = 0;
        for (int i = 0; i < n; ++ i) {
            if (begin.charAt(i) == '.') {
                continue;
            }
            while (j < n && target.charAt(j) == '.') {
                j ++;
            }
            if (j == n) {
                return NO;
            }
            if (begin.charAt(i) != target.charAt(j)) {
                return NO;
            }
            if (begin.charAt(i) == 'L' && i < j) {
                return NO;
            }
            if (begin.charAt(i) == 'R' && i > j) {
                return NO;
            }
            j ++;
        }
        while (j < n && target.charAt(j) == '.') {
            j ++;
        }
        if (j < n) {
            return NO;
        }
        return YES;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			FoxAndChessHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				FoxAndChessHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class FoxAndChessHarness {
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
			String begin              = "R...";
			String target             = "..R.";
			String expected__         = "Possible";

			return verifyCase(casenum__, expected__, new FoxAndChess().ableToMove(begin, target));
		}
		case 1: {
			String begin              = "..R.";
			String target             = "R...";
			String expected__         = "Impossible";

			return verifyCase(casenum__, expected__, new FoxAndChess().ableToMove(begin, target));
		}
		case 2: {
			String begin              = ".L.R.R.";
			String target             = "L...R.R";
			String expected__         = "Possible";

			return verifyCase(casenum__, expected__, new FoxAndChess().ableToMove(begin, target));
		}
		case 3: {
			String begin              = ".L.R.";
			String target             = ".R.L.";
			String expected__         = "Impossible";

			return verifyCase(casenum__, expected__, new FoxAndChess().ableToMove(begin, target));
		}
		case 4: {
			String begin              = "LRLLRLRLLRLLRLRLRL";
			String target             = "LRLLRLRLLRLLRLRLRL";
			String expected__         = "Possible";

			return verifyCase(casenum__, expected__, new FoxAndChess().ableToMove(begin, target));
		}
		case 5: {
			String begin              = "L";
			String target             = ".";
			String expected__         = "Impossible";

			return verifyCase(casenum__, expected__, new FoxAndChess().ableToMove(begin, target));
		}

		// custom cases

/*      case 6: {
			String begin              = ;
			String target             = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new FoxAndChess().ableToMove(begin, target));
		}*/
/*      case 7: {
			String begin              = ;
			String target             = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new FoxAndChess().ableToMove(begin, target));
		}*/
/*      case 8: {
			String begin              = ;
			String target             = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new FoxAndChess().ableToMove(begin, target));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
