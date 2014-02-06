import java.math.*;
import java.util.*;

public class GameInDarknessDiv1 {
    static int[] DELTA_X = {-1,  0, 0, 1};
    static int[] DELTA_Y = { 0, -1, 1, 0};

    public String check(String[] field) {
        int n = field.length;
        int m = field[0].length();
        int nodeCnt = 0;
        int[][] id = new int[n][m];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                if (field[i].charAt(j) != '#') {
                    id[i][j] = nodeCnt ++;
                }
            }
        }
        int a = -1;
        int b = -1;
        ArrayList <ArrayList <Integer> > graph = new ArrayList <ArrayList <Integer> >();
        for (int i = 0; i < nodeCnt; ++ i) {
            graph.add(new ArrayList <Integer>());
        }
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                if (field[i].charAt(j) == 'A') {
                    a = id[i][j];
                }
                if (field[i].charAt(j) == 'B') {
                    b = id[i][j];
                }
                if (field[i].charAt(j) != '#') {
                    for (int k = 0; k < 4; ++ k) {
                        int x = i + DELTA_X[k];
                        int y = j + DELTA_Y[k];
                        if (0 <= x && x < n && 0 <= y && y < m && field[x].charAt(y) != '#') {
                            graph.get(id[i][j]).add(id[x][y]);
                        }
                    }
                }
            }
        }
        assert a != -1 && b != -1;
        int[] distA = dfs(graph, new int[nodeCnt], -1, a, 0);
        int[] distB = dfs(graph, new int[nodeCnt], -1, b, 0);
        for (int p = 0; p < nodeCnt; ++ p) {
            if (distA[p] - distB[p] >= 2) {
                int cnt = 0;
                for (Object o : graph.get(p)) {
                    int v = (Integer)o;
                    int[] ret = dfs(graph, new int[nodeCnt], p, v, 1);
                    for (int u = 0; u < nodeCnt; ++ u) {
                        if (ret[u] >= 3) {
                            cnt ++;
                            break;
                        }
                    }
                }
                if (cnt >= 3) {
                    return "Bob wins";
                }
            }
        }
        return "Alice wins";
    }

    int[] dfs(ArrayList <ArrayList <Integer> > graph, int[] dist, int p, int u, int d) {
        dist[u] = d;
        for (Object o : graph.get(u)) {
            int v = (Integer)o;
            if (v != p) {
                dfs(graph, dist, u, v, d + 1);
            }
        }
        return dist;
    }


    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			GameInDarknessDiv1Harness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				GameInDarknessDiv1Harness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class GameInDarknessDiv1Harness {
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
	
	static boolean compareOutput(String expected, String result) { return expected.equals(result); }
	static String formatResult(String res) {
		return String.format("\"%s\"", res);
	}
	
	static int verifyCase(int casenum, String expected, String received) { 
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
			String[] field            = {"A.B..",
 "##.##",
 "##.##"};
			String expected__         = "Alice wins";

			return verifyCase(casenum__, expected__, new GameInDarknessDiv1().check(field));
		}
		case 1: {
			String[] field            = {"A.B..",
 ".#.#.",
 "#..##"};
			String expected__         = "Bob wins";

			return verifyCase(casenum__, expected__, new GameInDarknessDiv1().check(field));
		}
		case 2: {
			String[] field            = {"#...#",
 ".#A#.",
 "..B..",
 ".#.#.",
 "#...#"};
			String expected__         = "Alice wins";

			return verifyCase(casenum__, expected__, new GameInDarknessDiv1().check(field));
		}
		case 3: {
			String[] field            = {"##..#",
 "A.#..",
 "#B..#",
 "#.##.",
 "....."};
			String expected__         = "Alice wins";

			return verifyCase(casenum__, expected__, new GameInDarknessDiv1().check(field));
		}
		case 4: {
			String[] field            = {"##################################################",
 "###..................#................#........###",
 "###.################.########.#######.########.###",
 "###.################.########.#######.########.###",
 "###.################.########.#######.########.###",
 "###.################.########.#######.########.###",
 "###.################.########.#######.########.###",
 "###.################.########.#######.########.###",
 "###.################.########.#######.########.###",
 "###.################.########.#######.########.###",
 "###..........#######........#.#######........#.###",
 "############.#######.########.#######.########.###",
 "############.#######.########.#######.########.###",
 "############.#######.########.#######.########.###",
 "############.#######.########.#######.########.###",
 "############.#######.########.#######.########.###",
 "############.#######.########.#######.########.###",
 "############.#######.########.#######.########.###",
 "############.#######.########.#######.########.###",
 "###B.........#######..........#######..A.......###",
 "##################################################"}
;
			String expected__         = "Bob wins";

			return verifyCase(casenum__, expected__, new GameInDarknessDiv1().check(field));
		}
		case 5: {
			String[] field            = {"###.#",
 "###..",
 "A..B#",
 "###..",
 "###.#"};
			String expected__         = "Alice wins";

			return verifyCase(casenum__, expected__, new GameInDarknessDiv1().check(field));
		}
		case 6: {
			String[] field            = {".....#.##.##.#.#",
 ".###.#.##.##....",
 "#......B#...#.#.",
 "#.#A#.#.#.#..##.",
 "...#..#....#...."};
			String expected__         = "Alice wins";

			return verifyCase(casenum__, expected__, new GameInDarknessDiv1().check(field));
		}
		case 7: {
			String[] field            = {"...#.....###....#.....#...#.#.",
 ".#..#.##..#..#.#..###...#.....",
 "..#..#..#...#.#..#....##.#.###",
 ".#.#...###.#..#.#..###....#...",
 "...##.###..#.#..#.#...#.##..#.",
 ".#..#..#..#...#.#.#.#.#..#.#..",
 "..#..#.#.#..#..##.#.#..#.##..#",
 ".#.###.#.##..#.....A##......#.",
 "#.........#.#..#.###..###.#...",
 "..###.#.#.#..#.#....#.....#.#.",
 ".#..#.#.####.#..#.#..#.#.###..",
 "..#...#...#..#.#...#.#..#.#..#",
 "#..#.#..#.#.#..###.#.#.#....#.",
 "..#..##.##...#....#..#.#.####.",
 "#..#...#...#..#.#..###.#......",
 "#.#.##...#..#..#.#....#..#.#.#",
 "....#..##.#..#....#.#.#.#...#.",
 ".#.#..#....#.#.##..#..##..#.#.",
 "..##.#..##.#..#..#..#...#.#...",
 "#.#..##..#..#..#..#..##.#.#.#.",
 "..#.#.#.#.#..#...##.#...#..#..",
 ".##.....#..#.#.#.#..#.##.#..#.",
 "...#.#.#..#..#.###.#..#...#.#.",
 ".#..#....#..#.#...###.#.#..#..",
 ".#.#.#####.#....#..#..#.##.#.#",
 ".#...#......#.#..###B#....#...",
 "..###..####.#..#.#...#.#.#..#.",
 "#.#..#.#..#.#.#..#.#..#....#..",
 "..#.##..#.#.#.####..#.#####..#",
 "#.....#...#.#......#.......#.."}
;
			String expected__         = "Bob wins";

			return verifyCase(casenum__, expected__, new GameInDarknessDiv1().check(field));
		}

		// custom cases

/*      case 8: {
			String[] field            = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new GameInDarknessDiv1().check(field));
		}*/
/*      case 9: {
			String[] field            = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new GameInDarknessDiv1().check(field));
		}*/
/*      case 10: {
			String[] field            = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new GameInDarknessDiv1().check(field));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
