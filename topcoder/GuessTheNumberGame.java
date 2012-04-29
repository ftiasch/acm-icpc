import java.math.*;
import java.util.*;

public class GuessTheNumberGame {
    final static int MOD = 1000000007;

    public int possibleClues(int n) {
        boolean isPrime[] = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        int result = 1;
        for (int i = 2; i <= n; ++ i) {
            if (isPrime[i]) {
                int exponent = 1;
                long power = i;
                while (power * i <= n) {
                    exponent ++;
                    power *= i;
                }
                result = (int)((long)result * (exponent + 1) % MOD);
                for (int j = i + i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return result;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			GuessTheNumberGameHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				GuessTheNumberGameHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class GuessTheNumberGameHarness {
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
			int n                     = 5;
			int expected__            = 12;

			return verifyCase(casenum__, expected__, new GuessTheNumberGame().possibleClues(n));
		}
		case 1: {
			int n                     = 16;
			int expected__            = 240;

			return verifyCase(casenum__, expected__, new GuessTheNumberGame().possibleClues(n));
		}
		case 2: {
			int n                     = 1;
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new GuessTheNumberGame().possibleClues(n));
		}
		case 3: {
			int n                     = 1000000;
			int expected__            = 677298706;

			return verifyCase(casenum__, expected__, new GuessTheNumberGame().possibleClues(n));
		}

		// custom cases

/*      case 4: {
			int n                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new GuessTheNumberGame().possibleClues(n));
		}*/
/*      case 5: {
			int n                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new GuessTheNumberGame().possibleClues(n));
		}*/
/*      case 6: {
			int n                     = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new GuessTheNumberGame().possibleClues(n));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
