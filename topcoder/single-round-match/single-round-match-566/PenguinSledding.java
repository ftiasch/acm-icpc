import java.math.*;
import java.util.*;

public class PenguinSledding {
    public long countDesigns(int n, int[] checkpoint1, int[] checkpoint2) {
        int[] degree = new int[n];
        boolean[][] graph = new boolean[n][n];
        int m = checkpoint1.length;
        for (int i = 0; i < m; ++ i) {
            int a = checkpoint1[i] - 1;
            int b = checkpoint2[i] - 1;
            degree[a] ++;
            degree[b] ++;
            graph[a][b] = graph[b][a] = true;
        }
        long result = 1 + m;
        for (int i = 0; i < n; ++ i) {
            result += (1L << degree[i]) - 1 - degree[i];
        }
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < i; ++ j) {
                for (int k = 0; k < j; ++ k) {
                    if (graph[i][j] && graph[j][k] && graph[k][i]) {
                        result ++;
                    }
                }
            }
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			PenguinSleddingHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				PenguinSleddingHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class PenguinSleddingHarness {
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
			int numCheckpoints        = 2;
			int[] checkpoint1         = {1};
			int[] checkpoint2         = {2};
			long expected__           = 2;

			return verifyCase(casenum__, expected__, new PenguinSledding().countDesigns(numCheckpoints, checkpoint1, checkpoint2));
		}
		case 1: {
			int numCheckpoints        = 4;
			int[] checkpoint1         = {1,2,3};
			int[] checkpoint2         = {2,3,4};
			long expected__           = 6;

			return verifyCase(casenum__, expected__, new PenguinSledding().countDesigns(numCheckpoints, checkpoint1, checkpoint2));
		}
		case 2: {
			int numCheckpoints        = 6;
			int[] checkpoint1         = {1,3,6};
			int[] checkpoint2         = {2,4,4};
			long expected__           = 5;

			return verifyCase(casenum__, expected__, new PenguinSledding().countDesigns(numCheckpoints, checkpoint1, checkpoint2));
		}
		case 3: {
			int numCheckpoints        = 50;
			int[] checkpoint1         = {40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 50, 40, 40, 40, 23, 4, 24, 40, 22, 40, 8, 40, 40, 40, 34, 21, 40, 40, 38, 40, 40, 40, 40, 40, 28, 40, 40, 40, 40, 46, 13, 40, 40, 40, 47, 40, 40};
			int[] checkpoint2         = {45, 42, 20, 48, 12, 32, 25, 10, 26, 39, 16, 2, 19, 43, 37, 17, 19, 19, 19, 18, 19, 27, 19, 29, 11, 36, 19, 19, 1, 41, 19, 35, 14, 33, 49, 3, 19, 7, 5, 19, 31, 19, 19, 6, 9, 15, 19, 44, 30};
			long expected__           = 68719493118L;

			return verifyCase(casenum__, expected__, new PenguinSledding().countDesigns(numCheckpoints, checkpoint1, checkpoint2));
		}
		case 4: {
			int numCheckpoints        = 36;
			int[] checkpoint1         = {13, 24, 24, 20, 31, 16, 10, 36, 34, 32, 28, 5, 20, 29, 23, 2, 14, 4, 9, 5, 19, 21, 8, 1, 26, 27, 25, 15, 22, 30, 30, 6, 11, 7, 2, 35, 13, 29, 4, 12, 33, 18, 13, 14, 17, 35, 3};
			int[] checkpoint2         = {10, 15, 27, 1, 29, 11, 5, 18, 33, 1, 9, 2, 31, 6, 19, 10, 33, 18, 6, 27, 3, 22, 4, 12, 31, 30, 34, 16, 7, 24, 3, 28, 15, 21, 22, 8, 26, 20, 14, 32, 25, 17, 35, 8, 36, 26, 23};
			long expected__           = 162;

			return verifyCase(casenum__, expected__, new PenguinSledding().countDesigns(numCheckpoints, checkpoint1, checkpoint2));
		}

		// custom cases

/*      case 5: {
			int numCheckpoints        = ;
			int[] checkpoint1         = ;
			int[] checkpoint2         = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new PenguinSledding().countDesigns(numCheckpoints, checkpoint1, checkpoint2));
		}*/
/*      case 6: {
			int numCheckpoints        = ;
			int[] checkpoint1         = ;
			int[] checkpoint2         = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new PenguinSledding().countDesigns(numCheckpoints, checkpoint1, checkpoint2));
		}*/
/*      case 7: {
			int numCheckpoints        = ;
			int[] checkpoint1         = ;
			int[] checkpoint2         = ;
			long expected__           = ;

			return verifyCase(casenum__, expected__, new PenguinSledding().countDesigns(numCheckpoints, checkpoint1, checkpoint2));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
