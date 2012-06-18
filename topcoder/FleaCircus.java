import java.math.*;
import java.util.*;

public class FleaCircus {
    final static int MOD = 1000000009;

    long[][] choose;

    long solve(int size, int count) {
        long[] ways = new long[count + 1];
        ways[0] = 1;
        for (int i = 1; i <= count; ++ i) {
            // 1
            if (size % 2 != 0) {
                ways[i] = (ways[i] + ways[i - 1]) % MOD;
            }
            // 2
            if (i >= 2 && size % 2 != 0) {
                ways[i] = (ways[i] + (ways[i - 2] * choose[i - 1][1]) % MOD * size) % MOD;
            }
            // 4
            if (i >= 4) {
                ways[i] = (ways[i] + (ways[i - 4] * choose[i - 1][3]) % MOD * (size * size * size * 6) % MOD) % MOD;
            }
        }
        return ways[count];
    }

    public int countArrangements(String[] afterFourClicks) {
        String bufferString = new String();
        for (String s : afterFourClicks) {
            bufferString += s;
        }
        StringTokenizer tokenizer = new StringTokenizer(bufferString);
        ArrayList <Integer> bufferPermutation = new ArrayList <Integer>();
        while (tokenizer.hasMoreTokens()) {
            bufferPermutation.add(Integer.parseInt(tokenizer.nextToken()));
        }
        int n = bufferPermutation.size();
        int[] permutation = new int[n];
        for (int i = 0; i < n; ++ i) {
            permutation[i] = bufferPermutation.get(i);
        }
        int[] count = new int[n + 1];
        boolean[] visit = new boolean[n];
        for (int i = 0; i < n; ++ i) {
            if (!visit[i]) {
                int size = 0;
                int j = i;
                do {
                    size ++;
                    visit[j] = true;
                    j = permutation[j];
                } while (j != i);
                count[size] ++;
            }
        }
        choose = new long[n + 1][4];
        for (int i = 0; i <= n; ++ i) {
            choose[i][0] = 1;
            for (int j = 1; j <= Math.min(i, 3); ++ j) {
                choose[i][j] = (choose[i - 1][j] + choose[i - 1][j - 1]) % MOD;
            }
        }
        long result = 1;
        for (int i = 1; i <= n; ++ i) {
            result = (result * solve(i, count[i])) % MOD;
        }
        return (int)result;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			FleaCircusHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				FleaCircusHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class FleaCircusHarness {
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
			String[] afterFourClicks  = {"1 2 0 3"};
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new FleaCircus().countArrangements(afterFourClicks));
		}
		case 1: {
			String[] afterFourClicks  = {"1 2 ", "0 3"};
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new FleaCircus().countArrangements(afterFourClicks));
		}
		case 2: {
			String[] afterFourClicks  = {"0 1 2"};
			int expected__            = 4;

			return verifyCase(casenum__, expected__, new FleaCircus().countArrangements(afterFourClicks));
		}
		case 3: {
			String[] afterFourClicks  = {"0 1 2 3 5 4"};
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new FleaCircus().countArrangements(afterFourClicks));
		}
		case 4: {
			String[] afterFourClicks  = {"3 6 7 9 8 2 1", "0 5 1 0 4"};
			int expected__            = 4;

			return verifyCase(casenum__, expected__, new FleaCircus().countArrangements(afterFourClicks));
		}
		case 5: {
			String[] afterFourClicks  = {"1 0 7 5 6 3 4 2"};
			int expected__            = 48;

			return verifyCase(casenum__, expected__, new FleaCircus().countArrangements(afterFourClicks));
		}

		// custom cases

/*      case 6: {
			String[] afterFourClicks  = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new FleaCircus().countArrangements(afterFourClicks));
		}*/
/*      case 7: {
			String[] afterFourClicks  = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new FleaCircus().countArrangements(afterFourClicks));
		}*/
/*      case 8: {
			String[] afterFourClicks  = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new FleaCircus().countArrangements(afterFourClicks));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
