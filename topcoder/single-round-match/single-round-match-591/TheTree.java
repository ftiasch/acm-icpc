import java.math.*;
import java.util.*;

public class TheTree {
    public int maximumDiameter(int[] count) {
        int m = count.length;
        int answer = 0;
        for (int i = 0; i < m; ++ i) {
            int j = 0;
            while (i + j < m && count[i + j] >= 2) {
                j ++;
            }
            answer = Math.max(answer, m - i + j);
        }
        return answer;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			TheTreeHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				TheTreeHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class TheTreeHarness {
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
			int[] cnt                 = {3};
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new TheTree().maximumDiameter(cnt));
		}
		case 1: {
			int[] cnt                 = {2, 2};
			int expected__            = 4;

			return verifyCase(casenum__, expected__, new TheTree().maximumDiameter(cnt));
		}
		case 2: {
			int[] cnt                 = {4, 1, 2, 4};
			int expected__            = 5;

			return verifyCase(casenum__, expected__, new TheTree().maximumDiameter(cnt));
		}
		case 3: {
			int[] cnt                 = {4, 2, 1, 3, 2, 5, 7, 2, 4, 5, 2, 3, 1, 13, 6};
			int expected__            = 21;

			return verifyCase(casenum__, expected__, new TheTree().maximumDiameter(cnt));
		}

		// custom cases

/*      case 4: {
			int[] cnt                 = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TheTree().maximumDiameter(cnt));
		}*/
/*      case 5: {
			int[] cnt                 = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TheTree().maximumDiameter(cnt));
		}*/
/*      case 6: {
			int[] cnt                 = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TheTree().maximumDiameter(cnt));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
