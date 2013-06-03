import java.math.*;
import java.util.*;

public class IndependentOfOR {
    public int maxSum(int[] as) {
        int answer = 0;
        for (int mask = 0; mask < 1 << 20; ++ mask) {
            int[] maximum = new int[20];
            for (int a : as) {
                if (Integer.bitCount(a & mask) == 1) {
                    int i = Integer.numberOfTrailingZeros(a & mask);
                    maximum[i] = Math.max(maximum[i], a);
                }
            }
            int sum = 0;
            for (int i : maximum) {
                sum += i;
            }
            answer = Math.max(answer, sum);
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			IndependentOfORHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				IndependentOfORHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class IndependentOfORHarness {
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
			int[] A                   = {2, 3};
			int expected__            = 3;

			return verifyCase(casenum__, expected__, new IndependentOfOR().maxSum(A));
		}
		case 1: {
			int[] A                   = {1, 2, 3, 4, 5, 6};
			int expected__            = 11;

			return verifyCase(casenum__, expected__, new IndependentOfOR().maxSum(A));
		}
		case 2: {
			int[] A                   = {2, 3, 5, 7, 11, 13, 17, 19};
			int expected__            = 41;

			return verifyCase(casenum__, expected__, new IndependentOfOR().maxSum(A));
		}
		case 3: {
			int[] A                   = {8, 9, 13, 45, 47, 111, 127};
			int expected__            = 127;

			return verifyCase(casenum__, expected__, new IndependentOfOR().maxSum(A));
		}
		case 4: {
			int[] A                   = {5, 8, 55, 58, 85, 88, 555, 558, 585, 588, 855, 858, 885, 888};
			int expected__            = 2919;

			return verifyCase(casenum__, expected__, new IndependentOfOR().maxSum(A));
		}
		case 5: {
			int[] A                   = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288};
			int expected__            = 1048575;

			return verifyCase(casenum__, expected__, new IndependentOfOR().maxSum(A));
		}

		// custom cases

/*      case 6: {
			int[] A                   = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new IndependentOfOR().maxSum(A));
		}*/
/*      case 7: {
			int[] A                   = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new IndependentOfOR().maxSum(A));
		}*/
/*      case 8: {
			int[] A                   = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new IndependentOfOR().maxSum(A));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
