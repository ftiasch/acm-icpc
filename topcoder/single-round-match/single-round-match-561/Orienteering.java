import java.math.*;
import java.util.*;

public class Orienteering {
    int n, m, k;
    boolean[] color;
    ArrayList <ArrayList <Integer> > tree;
    double exceptation;

    double getProbability(int n, int m) { // binom(n, k) / binom(m, k)
        double result = 1;
        for (int i = 0; i < k; ++ i) {
            result *= n - i;
            result /= m - i;
        }
        return result;
    }

    int traverse(int p, int u) {
        int count = 0;
        for (Object object : tree.get(u)) {
            int v = (Integer)object;
            if (v != p) {
                int ret = traverse(u, v);
                exceptation -= 2 * getProbability(ret, m);
                exceptation -= 2 * getProbability(m - ret, m);
                count += ret;
            }
        }
        if (color[u]) {
            count ++;
        }
        return count;
    }

    class Pair implements Comparable {
        int depth, node, subtree;

        Pair(int depth, int node, int subtree) {
            this.depth = depth;
            this.node = node;
            this.subtree = subtree;
        }

        @Override
        public int compareTo(Object o) {
            Pair other = (Pair)o;
            if (depth != other.depth) {
                return depth - other.depth;
            }
            return node - other.node;
        }
    }

    void search(ArrayList <Pair> pairs, int p, int u, int depth) {
        if (color[u]) {
            pairs.add(new Pair(depth, u, -1));
        }
        for (Object object : tree.get(u)) {
            int v = (Integer)object;
            if (v != p) {
                search(pairs, u, v, depth + 1);
            }
        }
    }

    public double expectedLength(String[] field, int k) {
        do {
            int n = field.length;
            int m = field[0].length();
            int[][] id = new int[n][m];
            int nodeCount = 0;
            for (int i = 0; i < n; ++ i) {
                Arrays.fill(id[i], -1);
                for (int j = 0; j < m; ++ j) {
                    if (field[i].charAt(j) != '#') {
                        id[i][j] = nodeCount ++;
                    }
                }
            }
            tree = new ArrayList <ArrayList <Integer> >();
            for (int i = 0; i < nodeCount; ++ i) {
                tree.add(new ArrayList <Integer>());
            }
            color = new boolean[nodeCount];
            this.m = 0;
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < m; ++ j) {
                    if (id[i][j] != -1) {
                        color[id[i][j]] = field[i].charAt(j) == '*';
                        if (color[id[i][j]]) {
                            this.m ++;
                        }
                        int[] deltaX = {-1,  0, 0, 1};
                        int[] deltaY = { 0, -1, 1, 0};
                        for (int p = 0; p < 4; ++ p) {
                            int x = i + deltaX[p];
                            int y = j + deltaY[p];
                            if (0 <= x && x < n && 0 <= y && y < m && id[x][y] != -1) {
                                tree.get(id[i][j]).add(id[x][y]);
                            }
                        }
                    }
                }
            }
            this.n = nodeCount;
            this.k = k;
        } while (false);
        exceptation = 2 * (n - 1);
        traverse(-1, 0);
        double[] probability = new double[m];
        for (int i = 0; i <= m - 2; ++ i) {
            probability[i] = 1;
            for (int j = 0; j < k - 2; ++ j) {
                probability[i] *= i - j;
                probability[i] /= m - j;
            }
            probability[i] *= k;
            probability[i] /= m - k + 2;
            probability[i] *= k - 1;
            probability[i] /= m - k + 1;
        }
        for (int u = 0; u < n; ++ u) {
            ArrayList <Pair> all = new ArrayList <Pair>();
            if (color[u]) {
                all.add(new Pair(0, u, u));
            }
            for (Object object : tree.get(u)) {
                int v = (Integer)object;
                if (u < v) {
                    ArrayList <Pair> uv = new ArrayList <Pair>();
                    ArrayList <Pair> vu = new ArrayList <Pair>();
                    search(uv, u, v, 0);
                    search(vu, v, u, 0);
                    Collections.sort(uv);
                    Collections.sort(vu);
                    for (int i = 0; i < uv.size(); ++ i) {
                        for (int j = 0; j < vu.size(); ++ j) {
                            if (uv.get(i).depth == vu.get(j).depth) {
                                exceptation -= probability[i + j] * (2 * uv.get(i).depth + 1);
                            }
                        }
                    }
                }
                ArrayList <Pair> pairs = new ArrayList <Pair>();
                search(pairs, u, v, 1);
                for (Pair pair : pairs) {
                    all.add(new Pair(pair.depth, pair.node, v));
                }
            }
            Collections.sort(all);
            for (int i = 0; i < all.size(); ++ i) {
                int same = 0;
                for (int j = i - 1; j >= 0 && all.get(j).depth == all.get(i).depth; -- j) {
                    if (all.get(j).subtree == all.get(i).subtree) {
                        same ++;
                    } else {
                        exceptation -= probability[j + same] * 2 * all.get(i).depth;
                    }
                }
            }
        }
        return exceptation;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			OrienteeringHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				OrienteeringHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class OrienteeringHarness {
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
	
	static final double MAX_DOUBLE_ERROR = 1E-9;
	static boolean compareOutput(double expected, double result){ if(Double.isNaN(expected)){ return Double.isNaN(result); }else if(Double.isInfinite(expected)){ if(expected > 0){ return result > 0 && Double.isInfinite(result); }else{ return result < 0 && Double.isInfinite(result); } }else if(Double.isNaN(result) || Double.isInfinite(result)){ return false; }else if(Math.abs(result - expected) < MAX_DOUBLE_ERROR){ return true; }else{ double min = Math.min(expected * (1.0 - MAX_DOUBLE_ERROR), expected * (1.0 + MAX_DOUBLE_ERROR)); double max = Math.max(expected * (1.0 - MAX_DOUBLE_ERROR), expected * (1.0 + MAX_DOUBLE_ERROR)); return result > min && result < max; } }
	static double relativeError(double expected, double result) { if (Double.isNaN(expected) || Double.isInfinite(expected) || Double.isNaN(result) || Double.isInfinite(result) || expected == 0) return 0; return Math.abs(result-expected) / Math.abs(expected); }
	
	static String formatResult(double res) {
		return String.format("%.10g", res);
	}
	
	static int verifyCase(int casenum, double expected, double received) { 
		System.err.print("Example " + casenum + "... ");
		if (compareOutput(expected, received)) {
			System.err.print("PASSED");
			double rerr = relativeError(expected, received);
			if (rerr > 0) System.err.printf(" (relative error %g)", rerr);
			System.err.println();
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
			String[] field            = {"*#..#",
 ".#*#.",
 "*...*"};
			int K                     = 2;
			double expected__         = 3.8333333333333353;

			return verifyCase(casenum__, expected__, new Orienteering().expectedLength(field, K));
		}
		case 1: {
			String[] field            = {"*#..#",
 ".#*#.",
 "*...*"};
			int K                     = 4;
			double expected__         = 8.0;

			return verifyCase(casenum__, expected__, new Orienteering().expectedLength(field, K));
		}
		case 2: {
			String[] field            = {"#.#**",
 "....#",
 "#*#**",
 "**#*#",
 "#..##",
 "*#..#",
 ".#.#.",
 "....*"};
			int K                     = 3;
			double expected__         = 10.825000000000024;

			return verifyCase(casenum__, expected__, new Orienteering().expectedLength(field, K));
		}
		case 3: {
			String[] field            = {"###################",
 "#*###############*#",
 "#.....#######.....#",
 "#*###*.#.*.#.*###*#",
 "#*####*.*#*.*####*#",
 "#*#####*###*#####*#",
 "###################"};
			int K                     = 9;
			double expected__         = 30.272233648704244;

			return verifyCase(casenum__, expected__, new Orienteering().expectedLength(field, K));
		}
		case 4: {
			String[] field            = {"**##*.**#..#.*...*#...*#..#.##..#..#.#*...#.##*##.",
 ".#..###..#..#.#.##..#.#.*#.*..#..#.#*..##.#*...*..",
 "..#.....###.#*.##..#.#.#*..#.#..#....#..#...#*####",
 ".#.##*#.*#..#*#*.#.#...*.#.*#.#.##.#*.##.#.#..*...",
 "..*.*#*.###.#..#.#..##.##.*#..#.....#.....#..#.#.#",
 ".#.##.#..##..*#..#.#...#*##*#*..#.#.#.#.##.##.#.#*",
 "..##....#..#.#*#...*.##...#.#.####...#.#*.....#...",
 ".#.*#.##.*#*.#*.#.#.#..#.#..#.#*#.###..##.##.#.##*",
 ".*.#*..*.#.#...#.*##.#.**.#.*...**..*#..#.#.#*.#..",
 ".#*.#*##....##.#.#*..*.###.#.##.##.#.#.#....#.#*.#",
 "*.#..#*#.#*#*....#.#.#..*#**...##.#.#.**#*##.*.#..",
 ".#*.##..##..##.#.#..#.#.###.###...#...#*#..##*#.#.",
 "#..#*.#..*.###..#.#...#.###.#.#*#.#.#**##.#...*.#*",
 "..#..#.#.##.#..#.**.##*#.#**.**..#.#..#...#.##*#..",
 ".#*#.#.*..#.*#...#.#...#...#.##.#..*#*.##*....###.",
 ".*.#.#.#.#*#..*##.**.##*##..#.*#.#*###..*.#.##.#..",
 ".#......#...#.#.*#.#.#..#..#.#*#....#*.#*#.*#..*.#",
 "#..####..#*#...#*.#..#.###...#.#.#.###*#..##*##.#.",
 ".#.*..#.#...#.#..#.##...#..#.#.#.#.###..##..*.*.*.",
 ".#.#.#.#..##.*..#.*.#.##.#..##*...#.#..#.#.##.#.##",
 ".#..#*.#.#..#.##..##..#.*..#.*#.#...##....#...###.",
 ".#.#.#.#*.#.#..#.#..#..#.#.*#...#.##...#.##.##.*..",
 ".#...#.#.##.#.#..*#.*#..###..#.#.#*###.##...#*.##.",
 ".#.##.*.......*.#.*#.#.#*###..*...*..#.*.##.#.#..#",
 "...###*####*#.#..##*...#..#..##.#.#.#..##*#*.*.*#.",
 "#.#.#....*#..#.#.#.#.##..#*.#...#..#.#*#...#.##.*.",
 "..*.#*##.#.#*#.###...#..##.#.#.#*###*#.*#.#.*###.#",
 "##*##..##...#.....##.#.#.**#..#*.....##.#..#*.#.*.",
 ".....#.*.##..##.##*.*#...#.#.#.##.#*#.**..#..#.#.#",
 "##.#.#*##.#.#.*.*.#.#*#.#.#....*...#*##*##.#....#.",
 "*.**#**....*..##.#*.*.**..##.###.##.....##...##.**",
 "#.####.##*#*##..#.*#*#.##*...#.##..#.##....#*..##.",
 "....#...##.#...#*.#..##.##.#*..*.#....##.#.*##...#",
 "#.#..*##*..#.#..#..#..#*....#.##..##.#*##.##.*##..",
 "..#.#*.*.##.#.#*#.#*##.###.##...#............#*.#.",
 "#.#.##.#....*....*..##..*#.#.#.###.#.#.#.###..#..#",
 ".#**..#*#.#*#*#.#.#...*##....##.#*..#..#*..*#..#..",
 "...#*#.....#..#.#..#*#.*##.#..#.#.##..#.*#*#.#...#",
 ".#*.###.#.#.#.#.*#*##.##..#.#*..#...#.#.#..#*.*#..",
 "#*.#.#.#..#..#..#....*#.*##..##.#.#..#...##.#.#..#",
 "*.#..#..#...#..##.#*#..#.#*#.#.#.###..#.#*...#.#..",
 "#...#.#...#.#.#..#.*.#*.....**.*..#*##.#*.##....##",
 "#*#....#*#..#.*.###*#..#*##.##.#.#...#.*.##.##.##.",
 "..##*##*..#*#.#..#*.*##*.##.#...#.#.#.#.#..*#.##..",
 "#...#*##.#*#**.##.*#.*.##..*.#*#**....#**##...*.*#",
 "*#.##......*#.##.#.#.##**.#.#.#.#.#.##..#...#*#*#*",
 "*....##.#.#..#.....#..##.#....*....#.#.##.#.#.##**",
 "#.##*#...#..#.#.##..#..##.##.##.##........##.#*#.#",
 "..#...#.#*#*..*#..*#.*#.#......##.#.#.#*#..#..****",
 ".###.#..#...#.#..#..#.#...#.#.#...**.#..*#*.*##*#."};
			int K                     = 150;
			double expected__         = 1309.4951033725558;

			return verifyCase(casenum__, expected__, new Orienteering().expectedLength(field, K));
		}

		// custom cases

/*      case 5: {
			String[] field            = ;
			int K                     = ;
			double expected__         = ;

			return verifyCase(casenum__, expected__, new Orienteering().expectedLength(field, K));
		}*/
/*      case 6: {
			String[] field            = ;
			int K                     = ;
			double expected__         = ;

			return verifyCase(casenum__, expected__, new Orienteering().expectedLength(field, K));
		}*/
/*      case 7: {
			String[] field            = ;
			int K                     = ;
			double expected__         = ;

			return verifyCase(casenum__, expected__, new Orienteering().expectedLength(field, K));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
