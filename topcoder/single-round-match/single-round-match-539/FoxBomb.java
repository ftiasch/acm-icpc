import java.math.*;
import java.util.*;

public class FoxBomb {
	static int[] POWER = {2, 2 * 3, 2 * 3 * 3, 2 * 3 * 3 * 3};

	int n, m;
	char[][] map;
	List <Integer> candidates;
	Integer[][][] memory;

	int[] unpack(int mask) {
		int[] neighbour = new int[4];
		for (int i = 0; i < 4; ++ i) {
			neighbour[i] = mask / POWER[i] % 3 - 1;
		}
		return neighbour;
	}

	static int[] DELTA_X = {-1,  0, 1, 0};
	static int[] DELTA_Y = { 0, -1, 0, 1};

	int solve(int d, int x, int y, int t) {
		if (memory[x][y][t + 1] == null) {
			int result = Integer.MAX_VALUE;
			for (int mask : candidates) {
				int[] neighbour = unpack(mask);
				int value = mask % 2;
				for (int i = 0; i < 4 && value >= 0; ++ i) {
					if (i == d) {
						if (neighbour[i] != t) {
							value = -1;
						}
					} else {
						int x0 = x + DELTA_X[i];
						int y0 = y + DELTA_Y[i];
						if (0 <= x0 && x0 < n && 0 <= y0 && y0 < m && map[x0][y0] == '.') {
							value += solve(i ^ 2, x0, y0, -neighbour[i]);
						} else if (neighbour[i] != 0) {
							value = -1;
						}
					}
				}
				if (value >= 0) {
					result = Math.min(result, value);
				}
			}
			memory[x][y][t + 1] = result;
		}
		return memory[x][y][t + 1];
	}

	public int getMinimumCost(String[] grid) {
		n = grid.length;
		m = grid[0].length();
		map = new char[n][];
		for (int i = 0; i < n; ++ i) {
			map[i] = grid[i].toCharArray();
		}
		candidates = new ArrayList <Integer>();
		for (int mask = 0; mask < 3 * 3 * 3 * 3 * 2; ++ mask) {
			boolean center = (mask % 2) == 1;
			int[] neighbour = unpack(mask);
			boolean valid = center;
			for (int i = 0; i < 4; ++ i) {
				valid |= neighbour[i] == +1;
			}
			for (int i = 0; i < 4; ++ i) {
				if (neighbour[i] == -1) {
					valid &= center || neighbour[i ^ 2] == +1;
				}
			}
			if (valid) {
				candidates.add(mask);
			}
		}
		memory = new Integer[n][m][3];
		for (int i = 0; i < n; ++ i) {
			for (int j = 0; j < m; ++ j) {
				if (map[i][j] == '.') {
					return solve(-1, i, j, 0);
				}
			}
		}
		return 0;
	}

	void debug(Object...os) {
		System.err.println(Arrays.deepToString(os));
	}

// BEGIN CUT HERE
	public static void main(String[] args) {
		if (args.length == 0) {
			FoxBombHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				FoxBombHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class FoxBombHarness {
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
			String[] grid             = {"#..."
,"..##"
,"#.##"};
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new FoxBomb().getMinimumCost(grid));
		}
		case 1: {
			String[] grid             = {".#.#.#."
,"......."
,".#.#.#."};
			int expected__            = 4;

			return verifyCase(casenum__, expected__, new FoxBomb().getMinimumCost(grid));
		}
		case 2: {
			String[] grid             = {"######################################"
,"######################################"
,"###.....................##############"
,"###.###################.###....#...###"
,"###.###################.###.######.###"
,"###.###################.###.######.###"
,"###.###################.###.######.###"
,"###.###################.###.######.###"
,"###.###################.###.######.###"
,"###.........####........###.######.###"
,"###########.###########.###........###"
,"###########.###########.##########.###"
,"###########.###########.##########.###"
,"###########.###########.##########.###"
,"###########.###########.##########.###"
,"##..........##..........##########.###"
,"#######################............###"
,"######################################"};
			int expected__            = 9;

			return verifyCase(casenum__, expected__, new FoxBomb().getMinimumCost(grid));
		}
		case 3: {
			String[] grid             = {".#."
,"..."
,"#.#"
,"..."
,".#."}
;
			int expected__            = 5;

			return verifyCase(casenum__, expected__, new FoxBomb().getMinimumCost(grid));
		}
		case 4: {
			String[] grid             = {"."};
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new FoxBomb().getMinimumCost(grid));
		}

		// custom cases

/*      case 5: {
			String[] grid             = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new FoxBomb().getMinimumCost(grid));
		}*/
/*      case 6: {
			String[] grid             = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new FoxBomb().getMinimumCost(grid));
		}*/
/*      case 7: {
			String[] grid             = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new FoxBomb().getMinimumCost(grid));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
