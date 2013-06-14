import java.math.*;
import java.util.*;

public class TeamContest {
    public int worstRank(int[] strength) {
        int n = strength.length;
        Arrays.sort(strength, 0, 3);
        Arrays.sort(strength, 3, n);
        int me = strength[0] + strength[2];
        int answer = 1;
        for (int i = 3, j = n - 1; i < j; ) {
            if (strength[i] + strength[j] > me) {
                answer ++;
                i += 2;
                j -= 1;
            } else {
                i += 3;
            }
        }
        return answer;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			TeamContestHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				TeamContestHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class TeamContestHarness {
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
			int[] strength            = {5, 7, 3, 5, 7, 3, 5, 7, 3};
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new TeamContest().worstRank(strength));
		}
		case 1: {
			int[] strength            = {5, 7, 3} ;
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new TeamContest().worstRank(strength));
		}
		case 2: {
			int[] strength            = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new TeamContest().worstRank(strength));
		}
		case 3: {
			int[] strength            = {3,9,4,6,2,6,1,6,9,1,4,1,3,8,5} ;
			int expected__            = 3;

			return verifyCase(casenum__, expected__, new TeamContest().worstRank(strength));
		}
		case 4: {
			int[] strength            = {53,47,88,79,99,75,28,54,65,14,22,13,11,31,43} ;
			int expected__            = 3;

			return verifyCase(casenum__, expected__, new TeamContest().worstRank(strength));
		}

		// custom cases

/*      case 5: {
			int[] strength            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TeamContest().worstRank(strength));
		}*/
/*      case 6: {
			int[] strength            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TeamContest().worstRank(strength));
		}*/
/*      case 7: {
			int[] strength            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new TeamContest().worstRank(strength));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
