import java.math.*;
import java.util.*;

public class SurroundingGame {
    int m;

    int value(char c) {
        if ('0' <= c && c <= '9') {
            return c - '0';
        }
        if ('a' <= c && c <= 'z') {
            return c - 'a' + 10;
        }
        if ('A' <= c && c <= 'Z') {
            return c - 'A' + 36;
        }
        return -1;
    }

    int id(int x, int y, int z) {
        return (x * m + y) * 2 + z;
    }

    final static int[] DELTA_X = {-1, 0, 0, 1};
    final static int[] DELTA_Y = {0, -1, 1, 0};

    public int maxScore(String[] cost, String[] benefit) {
        int n = cost.length;
        int m = this.m = cost[0].length();
        int result = 0;
        Graph graph = new Graph(n * m * 2 + 2);
        int s = n * m * 2;
        int t = s + 1;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                int b = value(benefit[i].charAt(j));
                result += b;
                graph.addEdge(id(i, j, 0), id(i, j, 1), b);
                int c = value(cost[i].charAt(j));
                if ((i + j & 1) == 0) {
                    graph.addEdge(s, id(i, j, 0), c);
                    for (int k = 0; k < 4; ++ k) {
                        int x = i + DELTA_X[k];
                        int y = j + DELTA_Y[k];
                        if (0 <= x && x < n && 0 <= y && y < m) {
                            graph.addEdge(id(i, j, 0), id(x, y, 0), Integer.MAX_VALUE);
                            graph.addEdge(id(i, j, 1), id(x, y, 1), Integer.MAX_VALUE);
                        }
                    }
                } else {
                    graph.addEdge(id(i, j, 1), t, c);
                }
            }
        }
        return result - graph.maxFlow(s, t);
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			SurroundingGameHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				SurroundingGameHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

class Graph {
    class Edge {
        int from, to, capacity;
        Edge back;

        Edge(int from, int to, int capacity) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
        }
    }

    int n;
    ArrayList <ArrayList <Edge> > graph;

    Graph(int n) {
        this.n = n;
        graph = new ArrayList <ArrayList <Edge> >();
        for (int i = 0; i < n; ++ i) {
            graph.add(new ArrayList <Edge>());
        }
    }

    public void addEdge(int u, int v, int c) {
        Edge a = new Edge(u, v, c);
        Edge b = new Edge(v, u, 0);
        a.back = b;
        b.back = a;
        graph.get(u).add(a);
        graph.get(v).add(b);
    }

    public int maxFlow(int s, int t) {
        int result = 0;
        while (true) {
            int[] level = bfs(s, t);
            if (level == null) {
                break;
            }
            result += dfs(level, s, t, Integer.MAX_VALUE);
        }
        return result;
    }

    int[] bfs(int s, int t) {
        int[] level = new int[n];
        level[s] = 1;
        Queue <Integer> queue = new LinkedList <Integer>();
        queue.offer(s);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (Edge e : graph.get(u)) {
                if (e.capacity > 0 && level[e.to] == 0) {
                    level[e.to] = level[u] + 1;
                    queue.offer(e.to);
                }
            }
        }
        return level[t] == 0 ? null : level;
    }

    int dfs(int[] level, int u, int t, int d) {
        if (u == t) {
            return d;
        }
        int result = 0;
        for (Edge e : graph.get(u)) {
            if (level[u] + 1 == level[e.to] && e.capacity > 0) {
                int ret = dfs(level, e.to, t, Math.min(d - result, e.capacity));
                e.capacity -= ret;
                e.back.capacity += ret;
                result += ret;
                if (result == d) {
                    return d;
                }
            }
        }
        level[u] = 0;
        return result;
    }
}

// BEGIN CUT HERE
class SurroundingGameHarness {
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
			String[] cost             = {"21","12"};
			String[] benefit          = {"21","12"};
			int expected__            = 4;

			return verifyCase(casenum__, expected__, new SurroundingGame().maxScore(cost, benefit));
		}
		case 1: {
			String[] cost             = {"ZZ","ZZ"};
			String[] benefit          = {"11","11"};
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new SurroundingGame().maxScore(cost, benefit));
		}
		case 2: {
			String[] cost             = {"XXX","XXX","XXX"};
			String[] benefit          = {"aaa","aZa","aaa"};
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new SurroundingGame().maxScore(cost, benefit));
		}
		case 3: {
			String[] cost             = {"asam","atik"};
			String[] benefit          = {"123A","45BC"};
			int expected__            = 71;

			return verifyCase(casenum__, expected__, new SurroundingGame().maxScore(cost, benefit));
		}
		case 4: {
			String[] cost             = {"IIIIIIII",
 "IIWWWWII",
 "IIWIIIII",
 "IIWIIIII",
 "IIWWWWII",
 "IIIIIWII",
 "IIIIIWII",
 "IIWWWWII",
 "IIIIIIII"}
;
			String[] benefit          = {"IIIIIIII",
 "II0000II",
 "II0II0II",
 "II0II0II",
 "II0000II",
 "II0II0II",
 "II0II0II",
 "II0000II",
 "IIIIIIII"}
;
			int expected__            = 606;

			return verifyCase(casenum__, expected__, new SurroundingGame().maxScore(cost, benefit));
		}

		// custom cases

/*      case 5: {
			String[] cost             = ;
			String[] benefit          = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new SurroundingGame().maxScore(cost, benefit));
		}*/
/*      case 6: {
			String[] cost             = ;
			String[] benefit          = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new SurroundingGame().maxScore(cost, benefit));
		}*/
/*      case 7: {
			String[] cost             = ;
			String[] benefit          = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new SurroundingGame().maxScore(cost, benefit));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
