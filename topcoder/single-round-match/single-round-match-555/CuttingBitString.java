import java.math.*;
import java.util.*;

public class CuttingBitString {
    boolean isPower(long n) {
        if (n == 0) {
            return false;
        }
        while (n % 5 == 0) {
            n /= 5;
        }
        return n == 1;
    }

    public int getmin(String bits) {
        int n = bits.length();
        int[] minimum = new int[n + 1];
        Arrays.fill(minimum, Integer.MAX_VALUE);
        minimum[0] = 0;
        for (int i = 0; i < n; ++ i) {
            if (minimum[i] < Integer.MAX_VALUE && bits.charAt(i) == '1') {
                long number = 0;
                for (int j = i; j < n; ++ j) {
                    number = number * 2 + bits.charAt(j) - '0';
                    if (isPower(number)) {
                        minimum[j + 1] = Math.min(minimum[j + 1], minimum[i] + 1);
                    }
                }
            }
        }
        return minimum[n] < Integer.MAX_VALUE ? minimum[n] : -1;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			CuttingBitStringHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				CuttingBitStringHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class CuttingBitStringHarness {
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
			String S                  = "101101101";
			int expected__            = 3;

			return verifyCase(casenum__, expected__, new CuttingBitString().getmin(S));
		}
		case 1: {
			String S                  = "1111101";
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new CuttingBitString().getmin(S));
		}
		case 2: {
			String S                  = "00000";
			int expected__            = -1;

			return verifyCase(casenum__, expected__, new CuttingBitString().getmin(S));
		}
		case 3: {
			String S                  = "110011011";
			int expected__            = 3;

			return verifyCase(casenum__, expected__, new CuttingBitString().getmin(S));
		}
		case 4: {
			String S                  = "1000101011";
			int expected__            = -1;

			return verifyCase(casenum__, expected__, new CuttingBitString().getmin(S));
		}
		case 5: {
			String S                  = "111011100110101100101110111";
			int expected__            = 5;

			return verifyCase(casenum__, expected__, new CuttingBitString().getmin(S));
		}

		// custom cases

/*      case 6: {
			String S                  = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new CuttingBitString().getmin(S));
		}*/
/*      case 7: {
			String S                  = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new CuttingBitString().getmin(S));
		}*/
/*      case 8: {
			String S                  = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new CuttingBitString().getmin(S));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
