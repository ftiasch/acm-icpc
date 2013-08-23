import java.math.*;
import java.util.*;

public class KnightOfIntegerland {
    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public String able(int d, int x, int y) {
        if (x == 0 && y == 0) {
            return "YES";
        }
        int g = 0;
        for (int i = 0; i * i <= d; ++ i) {
            int j = (int)Math.sqrt(d - i * i);
            if (i * i + j * j == d) {
                g = gcd(g, i);
            }
        }
        if (g == 0 || x % g != 0 || y % g != 0) {
            return "NO";
        }
        boolean found = false;
        for (int i = 0; i * i <= d; ++ i) {
            int j = (int)Math.sqrt(d - i * i);
            if (i * i + j * j == d) {
                found |= (i / g + j / g) % 2 == 1;
            }
        }
        return found || (x / g + y / g) % 2 == 0 ? "YES" : "NO";
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			KnightOfIntegerlandHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				KnightOfIntegerlandHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class KnightOfIntegerlandHarness {
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
			int d                     = 25;
			int x                     = 1;
			int y                     = 0;
			String expected__         = "YES";

			return verifyCase(casenum__, expected__, new KnightOfIntegerland().able(d, x, y));
		}
		case 1: {
			int d                     = 25;
			int x                     = 2276;
			int y                     = -9059;
			String expected__         = "YES";

			return verifyCase(casenum__, expected__, new KnightOfIntegerland().able(d, x, y));
		}
		case 2: {
			int d                     = 5;
			int x                     = 58585858;
			int y                     = 85858585;
			String expected__         = "YES";

			return verifyCase(casenum__, expected__, new KnightOfIntegerland().able(d, x, y));
		}
		case 3: {
			int d                     = 4;
			int x                     = 47474747;
			int y                     = 74747474;
			String expected__         = "NO";

			return verifyCase(casenum__, expected__, new KnightOfIntegerland().able(d, x, y));
		}
		case 4: {
			int d                     = 169;
			int x                     = 2;
			int y                     = 0;
			String expected__         = "YES";

			return verifyCase(casenum__, expected__, new KnightOfIntegerland().able(d, x, y));
		}
		case 5: {
			int d                     = 3;
			int x                     = 1;
			int y                     = 1;
			String expected__         = "NO";

			return verifyCase(casenum__, expected__, new KnightOfIntegerland().able(d, x, y));
		}
		case 6: {
			int d                     = 3;
			int x                     = 0;
			int y                     = 0;
			String expected__         = "YES";

			return verifyCase(casenum__, expected__, new KnightOfIntegerland().able(d, x, y));
		}

		// custom cases

/*      case 7: {
			int d                     = ;
			int x                     = ;
			int y                     = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new KnightOfIntegerland().able(d, x, y));
		}*/
/*      case 8: {
			int d                     = ;
			int x                     = ;
			int y                     = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new KnightOfIntegerland().able(d, x, y));
		}*/
/*      case 9: {
			int d                     = ;
			int x                     = ;
			int y                     = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new KnightOfIntegerland().able(d, x, y));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
