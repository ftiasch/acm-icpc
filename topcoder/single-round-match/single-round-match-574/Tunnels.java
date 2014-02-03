import java.math.*;
import java.util.*;

public class Tunnels {
    public int minimumTunnels(String[] frame) {
        int n = frame.length;
        int m = frame[0].length();
        boolean[][] visited = new boolean[n][m];
        int component = 0;
        int[][] type = new int[2][n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                if (frame[i].charAt(j) == 'X' && !visited[i][j]) {
                    component ++;
                    if (i + 1 < n && frame[i + 1].charAt(j) == 'X') {
                        while (j + 1 < m && frame[i].charAt(j + 1) == 'X') {
                            j ++;
                        }
                    }
                    int[] result = floodfill(frame, visited, i, j);
                    int x = result[0];
                    int y = result[1];
                    if (i == x) {
                        if (j == 0 && y == m - 1) {
                            type[0][i] = type[1][i] = 3;
                        } else {
                            if (j == 0) {
                                type[0][i] = i == 0 ? 2 : 1;
                            }
                            if (y == m - 1) {
                                type[1][i] = i == 0 ? 2 : 1;
                            }
                        }
                    } else {
                        if (i > 0) {
                            assert j == 0 || j == m - 1;
                            type[j == 0 ? 0 : 1][i] = 1;
                        }
                        if (!(i + 1 == x && j == y) || i == 0) {
                            if (y == 0) {
                                type[0][x] = 2;
                            }
                            if (y == m - 1) {
                                type[1][x] = 2;
                            }
                        } 
                    }
                }
            }
        }
        int[][][] maximum = new int[n + 1][n + 1][n + 1];
        for (int i = 0; i <= n; ++ i) {
            for (int j = 0; j <= n; ++ j) {
                for (int k = 0; k <= n; ++ k) {
                    maximum[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }
        maximum[0][0][0] = 0;
        for (int i = 0; i < n; ++ i) {
            assert (type[0][i] == 3) == (type[1][i] == 3);
            for (int left = 0; left <= i; ++ left) {
                for (int right = 0; right <= i; ++ right) {
                    if (maximum[i][left][right] >= 0) {
                        if (i == 0 && type[0][i] == 3 && type[1][i] == 3) {
                            maximum[1][1][0] = maximum[1][0][1] = 0;
                        } else {
                            for (int t = 0; t < 2; ++ t) {
                                int add = 0;
                                int newLeft = left;
                                int newRight = right;
                                if (type[0][i] == 1 || type[0][i] == 3 && t == 0) {
                                    if (newLeft >= 1) {
                                        add ++;
                                        newLeft --;
                                    }
                                }
                                if (type[0][i] == 2 || type[0][i] == 3 && t == 1) {
                                    newLeft ++;
                                }
                                if (type[1][i] == 1 || type[1][i] == 3 && t == 1) {
                                    if (newRight >= 1) {
                                        add ++;
                                        newRight --;
                                    }
                                } 
                                if (type[1][i] == 2 || type[1][i] == 3 && t == 0) {
                                    newRight ++;
                                }
                                maximum[i + 1][newLeft][newRight] = Math.max(maximum[i + 1][newLeft][newRight], maximum[i][left][right] + add);
                            }
                        }
                    }
                }
            }
        }
        int result = Integer.MAX_VALUE;
        for (int left = 0; left <= n; ++ left) {
            for (int right = 0; right <= n; ++ right) {
                if (maximum[n][left][right] >= 0) {
                    result = Math.min(result, component - maximum[n][left][right]);
                }
            }
        }
        if (m == 1) {
            result = Math.min(result, 1);
        }
        return result;
    }

    int[] floodfill(String[] frame, boolean[][] visited, int x, int y) {
        int n = visited.length;
        int m = visited[0].length;
        if (x < 0 || x >= n || y < 0 || y >= m || frame[x].charAt(y) != 'X' || visited[x][y]) {
            return null;
        }
        visited[x][y] = true;
        int[] result = floodfill(frame, visited, x + 1, y);
        if (result != null) {
            return result;
        }
        result = floodfill(frame, visited, x, y - 1);
        if (result != null) {
            return result;
        }
        result = floodfill(frame, visited, x, y + 1);
        if (result != null) {
            return result;
        }
        return new int[]{x, y};
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			TunnelsHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				TunnelsHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class TunnelsHarness {
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
			String[] frame            = {"XXX.XXXX.....X",
                                         "..X....XXX...X",
                                         "XXX......X...."};
			int expected__            = 3;

			return verifyCase(casenum__, expected__, new Tunnels().minimumTunnels(frame));
		}
		case 1: {
			String[] frame            = {".......X.....",
 ".............",
 "XXX.XXXXXXXXX"};
			int expected__            = 3;

			return verifyCase(casenum__, expected__, new Tunnels().minimumTunnels(frame));
		}
		case 2: {
			String[] frame            = {".............",
 "XXXXXXXXXXXXX",
 ".............",
 "XXX.......XXX",
 "..........X..",
 "..........XXX"};
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new Tunnels().minimumTunnels(frame));
		}
		case 3: {
			String[] frame            = {"XXXX...X..",
                                         "....XXXX.X",
                                         "XX.......X",
                                         "..........",
                                         "....XXXXXX"};
			int expected__            = 4;

			return verifyCase(casenum__, expected__, new Tunnels().minimumTunnels(frame));
		}
		case 4: {
			String[] frame            = {"X........X..",
 ".........XXX",
 "............",
 "XXXXXXXXXXXX",
 "............",
 "XXXXXXXXXXXX",
 "............",
 ".........XXX",
 "..XXXXXXXX.."};
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new Tunnels().minimumTunnels(frame));
		}
		case 5: {
			String[] frame            = {"...............X.X....X",
 "XXXXX..........X.......",
 "....X..................",
 "XXX.X.........XXXXXXXXX",
 "..X.X.........X........",
 "XXX.X.........XXXXXXXXX",
 "....X..................",
 "XXXXX......XXXXXXXXXXXX"};
			int expected__            = 5;

			return verifyCase(casenum__, expected__, new Tunnels().minimumTunnels(frame));
		}
		case 6: {
			String[] frame            = {".",
 "X",
 "X",
 ".",
 "X"};
			int expected__            = 1;

			return verifyCase(casenum__, expected__, new Tunnels().minimumTunnels(frame));
		}
		case 7: {
			String[] frame            = {"X.XX",
 "X...",
 "...X",
 "X...",
 "X..X"};
			int expected__            = 3;

			return verifyCase(casenum__, expected__, new Tunnels().minimumTunnels(frame));
		}
		case 8: {
			String[] frame            = {"...",
 "..."};
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new Tunnels().minimumTunnels(frame));
		}

		// custom cases

/*      case 9: {
			String[] frame            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new Tunnels().minimumTunnels(frame));
		}*/
/*      case 10: {
			String[] frame            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new Tunnels().minimumTunnels(frame));
		}*/
/*      case 11: {
			String[] frame            = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new Tunnels().minimumTunnels(frame));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
