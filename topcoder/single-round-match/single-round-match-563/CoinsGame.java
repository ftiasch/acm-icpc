import java.math.*;
import java.util.*;

public class CoinsGame {
    static int MOD = (int)1e9 + 9;

    static int[] DELTA_X = {-1,  0, 0, 1};
    static int[] DELTA_Y = { 0, -1, 1, 0};

    int n, m;
    boolean[] block;
    boolean[][] good;

    int move(int p, int d) {
        int x = p / m + DELTA_X[d];
        int y = p % m + DELTA_Y[d];
        return 0 <= x && x < n && 0 <= y && y < m ? x * m + y : -1;
    }

    public int ways(String[] board) {
        n = board.length;
        m = board[0].length();
        block = new boolean[n * m];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                block[i * m + j] = board[i].charAt(j) == '#';
            }
        }
        good = new boolean[n * m][n * m];
        int[] queue = new int[n * m * n * m];
        int head = 0;
        int tail = 0;
        for (int i = 0; i < n * m; ++ i) {
            for (int j = 0; j < n * m; ++ j) {
                if (!block[i] && !block[j]) {
                    for (int k = 0; k < 4; ++ k) {
                        if (move(i, k) == -1 ^ move(j, k) == -1 && !good[i][j]) {
                            good[i][j] = true;
                            queue[tail ++] = i * n * m + j;
                        }
                    }
                }
            }
        }
        while (head < tail) {
            int p = queue[head] / (n * m);
            int q = queue[head ++] % (n * m);
            for (int k = 0; k < 4; ++ k) {
                for (int mask = 1; mask < 4; ++ mask) {
                    int pp = p;
                    int qq = q;
                    if ((mask & 1) == 1) {
                        pp = move(p, k ^ 3);
                        if (pp == -1 || block[pp]) {
                            continue;
                        }
                    } else {
                        int t = move(p, k);
                        if (t == -1 || !block[t]) {
                            continue;
                        }
                    }
                    if ((mask >> 1) == 1) {
                        qq = move(q, k ^ 3);
                        if (qq == -1 || block[qq]) {
                            continue;
                        }
                    } else {
                        int t = move(q, k);
                        if (t == -1 || !block[t]) {
                            continue;
                        }
                    }
                    if (!good[pp][qq]) {
                        good[pp][qq] = true;
                        queue[tail ++] = pp * n * m + qq;
                    }
                }
            }
        }
        int[] power = new int[n * m + 1];
        power[0] = 1;
        for (int i = 1; i <= n * m; ++ i) {
            power[i] = power[i - 1] * 2 % MOD;
        }
        int total = 0;
        for (int i = 0; i < n * m; ++ i) {
            if (!block[i]) {
                total ++;
            }
        }
        int result = power[total] - 1;
        boolean[] visit = new boolean[n * m];
        for (int i = 0; i < n * m; ++ i) {
            if (!block[i] && !visit[i]) {
                int size = 0;
                for (int j = 0; j < n * m; ++ j) {
                    if (!block[j] && !good[i][j]) {
                        visit[j] = true;
                        size ++;
                    }
                }
                result += MOD - power[size] + 1;
                if (result >= MOD) {
                    result -= MOD;
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
			CoinsGameHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				CoinsGameHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class CoinsGameHarness {
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
			String[] board            = {".."};
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new CoinsGame().ways(board));
		}
		case 1: {
			String[] board            = {"##.#",
 ".###",
 "###.",
 "#.##"};
			int expected__            = 11;

			return verifyCase(casenum__, expected__, new CoinsGame().ways(board));
		}
		case 2: {
			String[] board            = {"####",
 "#..#",
 "#..#",
 "####"};
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new CoinsGame().ways(board));
		}
		case 3: {
			String[] board            = {"#.#.#"};
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new CoinsGame().ways(board));
		}
		case 4: {
			String[] board            = {"........",
 "........",
 "........",
 "........",
 "........",
 "........",
 "........",
 "........"};
			int expected__            = 688856388;

			return verifyCase(casenum__, expected__, new CoinsGame().ways(board));
		}

		// custom cases

/*      case 5: {
			String[] board            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new CoinsGame().ways(board));
		}*/
/*      case 6: {
			String[] board            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new CoinsGame().ways(board));
		}*/
/*      case 7: {
			String[] board            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new CoinsGame().ways(board));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
