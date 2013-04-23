import java.math.*;
import java.util.*;

public class FoxAndKgram {
    public int maxK(int[] len) {
        Arrays.sort(len);
        int n = len.length;
        int k = 50;
        while (k > 0) {
            int count = 0;
            for (int i = 0, j = n - 1; i < j; ++ i) {
                while (i < j && len[i] + len[j] >= k) {
                    j --;
                }
                if (i < j && len[i] + len[j] == k - 1) {
                    j --;
                    count ++;
                }
            }
            for (int i = 0; i < n; ++ i) {
                if (len[i] == k) {
                    count ++;
                }
            }
            if (count >= k) {
                return k;
            }
            k --;
        }
        return 0;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			FoxAndKgramHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				FoxAndKgramHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class FoxAndKgramHarness {
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
			int[] len                 = {1,1,1,1,3};
			int expected__            = 3;

			return verifyCase(casenum__, expected__, new FoxAndKgram().maxK(len));
		}
		case 1: {
			int[] len                 = {2,2,1,3,5,5,5};
			int expected__            = 5;

			return verifyCase(casenum__, expected__, new FoxAndKgram().maxK(len));
		}
		case 2: {
			int[] len                 = {1};
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new FoxAndKgram().maxK(len));
		}
		case 3: {
			int[] len                 = {2,2,2,2,2,2,2,2,2,2};
			int expected__            = 5;

			return verifyCase(casenum__, expected__, new FoxAndKgram().maxK(len));
		}
		case 4: {
			int[] len                 = {1,2,3,1,2,4,2,3,1};
			int expected__            = 4;

			return verifyCase(casenum__, expected__, new FoxAndKgram().maxK(len));
		}
		case 5: {
			int[] len                 = {2,3,5,7,11,13,17,19,23,29};
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new FoxAndKgram().maxK(len));
		}

		// custom cases

/*      case 6: {
			int[] len                 = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new FoxAndKgram().maxK(len));
		}*/
/*      case 7: {
			int[] len                 = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new FoxAndKgram().maxK(len));
		}*/
/*      case 8: {
			int[] len                 = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new FoxAndKgram().maxK(len));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
