import java.math.*;
import java.util.*;

public class EllysFractions {
    boolean isPrime(int n) {
        for (int i = 2; i * i <= n; ++ i) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public long getCount(int n) {
        long answer = 0;
        long power = 1;
        for (int i = 2; i <= n; ++ i) {
            if (isPrime(i)) {
                if (i > 2) {
                    power *= 2;
                }
            }
            answer += power;
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			EllysFractionsHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				EllysFractionsHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class EllysFractionsHarness {
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
			int N                     = 1;
			long expected__           = 0;

			return verifyCase(casenum__, expected__, new EllysFractions().getCount(N));
		}
		case 1: {
			int N                     = 2;
			long expected__           = 1;

			return verifyCase(casenum__, expected__, new EllysFractions().getCount(N));
		}
		case 2: {
			int N                     = 3;
			long expected__           = 3;

			return verifyCase(casenum__, expected__, new EllysFractions().getCount(N));
		}
		case 3: {
			int N                     = 5;
			long expected__           = 9;

			return verifyCase(casenum__, expected__, new EllysFractions().getCount(N));
		}
		case 4: {
			int N                     = 100;
			long expected__           = 177431885;

			return verifyCase(casenum__, expected__, new EllysFractions().getCount(N));
		}

		// custom cases

/*      case 5: {
			int N                     = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new EllysFractions().getCount(N));
		}*/
/*      case 6: {
			int N                     = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new EllysFractions().getCount(N));
		}*/
/*      case 7: {
			int N                     = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new EllysFractions().getCount(N));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
