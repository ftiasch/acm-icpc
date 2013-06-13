import java.math.*;
import java.util.*;

public class TheNumberGameDivOne {
    boolean check(long n) {
        for (int i = 1; i <= 60; i += 2) {
            if ((1L << i) == n) {
                return false;
            }
        }
        return n % 2 == 0;
    }

    public String find(long n) {
        return check(n) ? "John" : "Brus";
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			TheNumberGameDivOneHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				TheNumberGameDivOneHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class TheNumberGameDivOneHarness {
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
			long n                    = 6;
			String expected__         = "John";

			return verifyCase(casenum__, expected__, new TheNumberGameDivOne().find(n));
		}
		case 1: {
			long n                    = 2;
			String expected__         = "Brus";

			return verifyCase(casenum__, expected__, new TheNumberGameDivOne().find(n));
		}
		case 2: {
			long n                    = 747;
			String expected__         = "Brus";

			return verifyCase(casenum__, expected__, new TheNumberGameDivOne().find(n));
		}
		case 3: {
			long n                    = 128;
			String expected__         = "Brus";

			return verifyCase(casenum__, expected__, new TheNumberGameDivOne().find(n));
		}

		// custom cases

/*      case 4: {
			long n                    = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new TheNumberGameDivOne().find(n));
		}*/
/*      case 5: {
			long n                    = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new TheNumberGameDivOne().find(n));
		}*/
/*      case 6: {
			long n                    = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new TheNumberGameDivOne().find(n));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
