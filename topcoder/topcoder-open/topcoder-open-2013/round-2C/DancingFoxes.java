import java.math.*;
import java.util.*;

public class DancingFoxes {
    final static int INF = (int)1e9;

    int solve(int n) {
        return n <= 1 ? 0 : 1 + solve(n - (n + 1) / 3);
    }

    public int minimalDays(String[] friendship) {
        int n = friendship.length;
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                graph[i][j] = friendship[i].charAt(j) == 'Y' ? 1 : INF;
            }
        }
        for (int k = 0; k < n; ++ k) {
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
        return graph[0][1] < INF ? solve(graph[0][1]) : -1;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			DancingFoxesHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				DancingFoxesHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class DancingFoxesHarness {
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
			String[] friendship       = {"NNY",
 "NNY",
 "YYN"};
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new DancingFoxes().minimalDays(friendship));
		}
		case 1: {
			String[] friendship       = {"NNNNN",
 "NNYYY",
 "NYNYY",
 "NYYNY",
 "NYYYN"};
			int expected__            = -1;

			return verifyCase(casenum__, expected__, new DancingFoxes().minimalDays(friendship));
		}
		case 2: {
			String[] friendship       = {"NNYYNN",
 "NNNNYY",
 "YNNNYN",
 "YNNNNY",
 "NYYNNN",
 "NYNYNN"};
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new DancingFoxes().minimalDays(friendship));
		}
		case 3: {
			String[] friendship       = {"NNYNNNNYN",
 "NNNNYNYNN",
 "YNNYNYNNN",
 "NNYNYNYYN",
 "NYNYNNNNY",
 "NNYNNNYNN",
 "NYNYNYNNN",
 "YNNYNNNNY",
 "NNNNYNNYN"};
			int expected__            = 3;

			return verifyCase(casenum__, expected__, new DancingFoxes().minimalDays(friendship));
		}
		case 4: {
			String[] friendship       = {"NY",
 "YN"};
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new DancingFoxes().minimalDays(friendship));
		}

		// custom cases

/*      case 5: {
			String[] friendship       = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new DancingFoxes().minimalDays(friendship));
		}*/
/*      case 6: {
			String[] friendship       = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new DancingFoxes().minimalDays(friendship));
		}*/
/*      case 7: {
			String[] friendship       = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new DancingFoxes().minimalDays(friendship));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
