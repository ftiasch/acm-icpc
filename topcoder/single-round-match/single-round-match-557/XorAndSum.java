import java.math.*;
import java.util.*;

public class XorAndSum {
    public long maxSum(long[] number) {
        int n = number.length;
        int rank = 0;
        Arrays.sort(number);
        for (int k = 50; k >= 0; -- k) {
            int pivot = rank;
            while (pivot < n && (number[pivot] >> k & 1) == 0) {
                pivot ++;
            }
            if (pivot < n) {
                long tmp = number[rank];
                number[rank] = number[pivot];
                number[pivot] = tmp;
                for (int i = 0; i < n; ++ i) {
                    if (rank != i && (number[i] >> k & 1) == 1) {
                        number[i] ^= number[rank];
                    }
                }
                rank ++;
            }
        }
        for (int i = 1; i < rank; ++ i) {
            number[0] ^= number[i];
        }
        for (int i = 1; i < rank; ++ i) {
            number[i] ^= number[0];
        }
        long result = number[0] * (n - rank);
        for (int i = 0; i < n; ++ i) {
            result += number[i];
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			XorAndSumHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				XorAndSumHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class XorAndSumHarness {
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
			long[] number             = {1, 0};
			long expected__           = 2;

			return verifyCase(casenum__, expected__, new XorAndSum().maxSum(number));
		}
		case 1: {
			long[] number             = {1, 2, 3};
			long expected__           = 8;

			return verifyCase(casenum__, expected__, new XorAndSum().maxSum(number));
		}
		case 2: {
			long[] number             = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			long expected__           = 0;

			return verifyCase(casenum__, expected__, new XorAndSum().maxSum(number));
		}
		case 3: {
			long[] number             = {2, 3, 5, 7, 11, 13, 17, 19};
			long expected__           = 233;

			return verifyCase(casenum__, expected__, new XorAndSum().maxSum(number));
		}
		case 4: {
			long[] number             = {123456789012345L, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			long expected__           = 1234567890123450L;

			return verifyCase(casenum__, expected__, new XorAndSum().maxSum(number));
		}
		case 5: {
			long[] number             = {627, 674, 281, 272, 289, 877, 62, 122, 603, 189, 615};
			long expected__           = 10742;

			return verifyCase(casenum__, expected__, new XorAndSum().maxSum(number));
		}
		case 6: {
			long[] number             = {557};
			long expected__           = 557;

			return verifyCase(casenum__, expected__, new XorAndSum().maxSum(number));
		}

		// custom cases

/*      case 7: {
			long[] number             = {};
			long expected__           = ;

			return verifyCase(casenum__, expected__, new XorAndSum().maxSum(number));
		}*/
/*      case 8: {
			long[] number             = {};
			long expected__           = ;

			return verifyCase(casenum__, expected__, new XorAndSum().maxSum(number));
		}*/
/*      case 9: {
			long[] number             = {};
			long expected__           = ;

			return verifyCase(casenum__, expected__, new XorAndSum().maxSum(number));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
