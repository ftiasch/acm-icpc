import java.math.*;
import java.util.*;

public class HexagonalBoard {
    public int minColors(String[] board) {
        int n = board.length;
        int[][] color = new int[n][n];
        for (int i = 0; i < n; ++ i) {
            Arrays.fill(color[i], -1);
        }
        int result = -1;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                if (board[i].charAt(j) == 'X' && color[i][j] == -1) {
                    if (!dfs(board, color, i, j, 0)) {
                        return 3;
                    }
                }
                result = Math.max(result, color[i][j]);
            }
        }
        return result + 1;
    }

    static int[] DELTA_X = {-1, -1,  0, 0,  1, 1};
    static int[] DELTA_Y = { 0,  1, -1, 1, -1, 0};

    boolean dfs(String[] board, int[][] color, int x, int y, int c) {
        int n = board.length;
        if (x < 0 || x >= n || y < 0 || y >= n || board[x].charAt(y) != 'X') {
            return true;
        }
        if (color[x][y] != -1) {
            return color[x][y] == c;
        }
        boolean valid = true;
        color[x][y] = c;
        for (int i = 0; i < 6 && valid; ++ i) {
            valid &= dfs(board, color, x + DELTA_X[i], y + DELTA_Y[i], c ^ 1);
        }
        return valid;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			HexagonalBoardHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				HexagonalBoardHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class HexagonalBoardHarness {
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
			String[] board            = {"---",
 "---",
 "---"}
 ;
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new HexagonalBoard().minColors(board));
		}
		case 1: {
			String[] board            = {"-X--",
 "---X",
 "----",
 "-X--"};
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new HexagonalBoard().minColors(board));
		}
		case 2: {
			String[] board            = {"XXXX",
 "---X",
 "---X",
 "---X"};
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new HexagonalBoard().minColors(board));
		}
		case 3: {
			String[] board            = {"XX-XX--"
,"-XX-XXX"
,"X-XX--X"
,"X--X-X-"
,"XX-X-XX"
,"-X-XX-X"
,"-XX-XX-"};
			int expected__            = 3;

			return verifyCase(casenum__, expected__, new HexagonalBoard().minColors(board));
		}

		// custom cases

/*      case 4: {
			String[] board            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new HexagonalBoard().minColors(board));
		}*/
/*      case 5: {
			String[] board            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new HexagonalBoard().minColors(board));
		}*/
/*      case 6: {
			String[] board            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new HexagonalBoard().minColors(board));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
