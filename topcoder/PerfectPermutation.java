import java.math.*;
import java.util.*;

public class PerfectPermutation {
    public int reorder(int[] permutation) {
        int result = 0;
        int n = permutation.length;
        boolean visit[] = new boolean[n];
        for (int i = 0; i < n; ++ i) {
            if (!visit[i]) {
                int j = i;
                do {
                    j = permutation[j];
                    visit[j] = true;
                } while (j != i);
                result ++;
            }
        }
        return result == 1? 0: result;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			PerfectPermutationHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				PerfectPermutationHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class PerfectPermutationHarness {
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
			int[] P                   = {2, 0, 1};
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new PerfectPermutation().reorder(P));
		}
		case 1: {
			int[] P                   = {2, 0, 1, 4, 3};
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new PerfectPermutation().reorder(P));
		}
		case 2: {
			int[] P                   = {2, 3, 0, 1};
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new PerfectPermutation().reorder(P));
		}
		case 3: {
			int[] P                   = {0, 5, 3, 2, 1, 4};
			int expected__            = 3;

			return verifyCase(casenum__, expected__, new PerfectPermutation().reorder(P));
		}
		case 4: {
			int[] P                   = {4, 2, 6, 0, 3, 5, 9, 7, 8, 1};
			int expected__            = 5;

			return verifyCase(casenum__, expected__, new PerfectPermutation().reorder(P));
		}

		// custom cases

/*      case 5: {
			int[] P                   = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new PerfectPermutation().reorder(P));
		}*/
/*      case 6: {
			int[] P                   = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new PerfectPermutation().reorder(P));
		}*/
/*      case 7: {
			int[] P                   = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new PerfectPermutation().reorder(P));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
