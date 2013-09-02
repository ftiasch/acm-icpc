import java.math.*;
import java.util.*;

@SuppressWarnings("unchecked")
class FlowSolver {
    class Edge {
        int v, c;
        Edge back;

        Edge(int v, int c) {
            this.v = v;
            this.c = c;
        }
    }

    int n;
    ArrayList[] edges;

    FlowSolver(int n) {
        this.n = n;
        edges = new ArrayList[n];
        for (int i = 0; i < n; ++ i) {
            edges[i] = new ArrayList <Edge>();
        }
    }

    void addEdge(int a, int b, int c) {
        Edge e = new Edge(b, c);
        Edge f = new Edge(a, 0);
        e.back = f;
        f.back = e;
        edges[a].add(e);
        edges[b].add(f);
    }

    int[] level;

    int maxFlow(int source, int target) {
        int ret = 0;
        while (bfs(source, target)) {
            ret += dfs(source, target, Integer.MAX_VALUE);
        }
        return ret;
    }

    private boolean bfs(int source, int target) {
        level = new int[n];
        Arrays.fill(level, -1);
        level[source] = 0;
        Queue <Integer> queue = new LinkedList <Integer>();
        queue.offer(source);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (Object obj : edges[u]) {
                Edge e = (Edge)obj;
                if (e.c > 0 && level[e.v] == -1) {
                    level[e.v] = level[u] + 1;
                    queue.offer(e.v);
                }
            }
        }
        return level[target] != -1;
    }

    private int dfs(int u, int target, int limit) {
        if (u == target) {
            return limit;
        }
        int delta = 0;
        for (Object obj : edges[u]) {
            Edge e = (Edge)obj;
            if (e.c > 0 && level[e.v] == level[u] + 1) {
                int ret = dfs(e.v, target, Math.min(limit - delta, e.c));
                e.c -= ret;
                e.back.c += ret;
                delta += ret;
                if (delta == limit) {
                    break;
                }
            }
        }
        return delta;
    }
}
public class OldBridges {
    public String isPossible(String[] bridges, int a1, int a2, int an, int b1, int b2, int bn) {
        int n = bridges.length;
        FlowSolver solver = new FlowSolver(n + 2);
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                char type = bridges[i].charAt(j);
                if (type == 'X') {
                    continue;
                }
                solver.addEdge(i, j, type == 'O' ? 2 : 200);
            }
        }
        solver.addEdge(n, a1, 200);
        solver.addEdge(n, b1, 200);
        solver.addEdge(a2, n + 1, 200);
        solver.addEdge(b2, n + 1, 200);
        if (solver.maxFlow(n, n + 1) < 2 * (an + bn)) {
            return "No";
        }
        solver = new FlowSolver(n + 2);
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                char type = bridges[i].charAt(j);
                if (type == 'X') {
                    continue;
                }
                solver.addEdge(i, j, type == 'O' ? 2 : 200);
            }
        }
        solver.addEdge(n, a1, 200);
        solver.addEdge(n, b2, 200);
        solver.addEdge(a2, n + 1, 200);
        solver.addEdge(b1, n + 1, 200);
        if (solver.maxFlow(n, n + 1) < 2 * (an + bn)) {
            return "No";
        }
        return "Yes";
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			OldBridgesHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				OldBridgesHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class OldBridgesHarness {
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
			String[] bridges          = {"XOXX","OXOX","XOXO","XXOX"};
			int a1                    = 0;
			int a2                    = 1;
			int an                    = 1;
			int b1                    = 2;
			int b2                    = 3;
			int bn                    = 1;
			String expected__         = "Yes";

			return verifyCase(casenum__, expected__, new OldBridges().isPossible(bridges, a1, a2, an, b1, b2, bn));
		}
		case 1: {
			String[] bridges          = {"XOXX","OXOX","XOXO","XXOX"};
			int a1                    = 0;
			int a2                    = 2;
			int an                    = 1;
			int b1                    = 1;
			int b2                    = 3;
			int bn                    = 1;
			String expected__         = "No";

			return verifyCase(casenum__, expected__, new OldBridges().isPossible(bridges, a1, a2, an, b1, b2, bn));
		}
		case 2: {
			String[] bridges          = {"XOXO","OXOX","XOXO","OXOX"};
			int a1                    = 0;
			int a2                    = 2;
			int an                    = 1;
			int b1                    = 1;
			int b2                    = 3;
			int bn                    = 1;
			String expected__         = "Yes";

			return verifyCase(casenum__, expected__, new OldBridges().isPossible(bridges, a1, a2, an, b1, b2, bn));
		}
		case 3: {
			String[] bridges          = {"XNXO","NXOX","XOXO","OXOX"};
			int a1                    = 0;
			int a2                    = 2;
			int an                    = 1;
			int b1                    = 1;
			int b2                    = 3;
			int bn                    = 2;
			String expected__         = "No";

			return verifyCase(casenum__, expected__, new OldBridges().isPossible(bridges, a1, a2, an, b1, b2, bn));
		}
		case 4: {
			String[] bridges          = {"XOXOO","OXOXO","XOXOO","OXOXO","OOOOX"};
			int a1                    = 0;
			int a2                    = 2;
			int an                    = 2;
			int b1                    = 1;
			int b2                    = 3;
			int bn                    = 2;
			String expected__         = "Yes";

			return verifyCase(casenum__, expected__, new OldBridges().isPossible(bridges, a1, a2, an, b1, b2, bn));
		}
		case 5: {
			String[] bridges          = {"XOOOX","OXOOX","OOXOX","OOOXN","XXXNX"};
			int a1                    = 0;
			int a2                    = 4;
			int an                    = 3;
			int b1                    = 1;
			int b2                    = 2;
			int bn                    = 2;
			String expected__         = "No";

			return verifyCase(casenum__, expected__, new OldBridges().isPossible(bridges, a1, a2, an, b1, b2, bn));
		}

		// custom cases

/*      case 6: {
			String[] bridges          = ;
			int a1                    = ;
			int a2                    = ;
			int an                    = ;
			int b1                    = ;
			int b2                    = ;
			int bn                    = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new OldBridges().isPossible(bridges, a1, a2, an, b1, b2, bn));
		}*/
/*      case 7: {
			String[] bridges          = ;
			int a1                    = ;
			int a2                    = ;
			int an                    = ;
			int b1                    = ;
			int b2                    = ;
			int bn                    = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new OldBridges().isPossible(bridges, a1, a2, an, b1, b2, bn));
		}*/
/*      case 8: {
			String[] bridges          = ;
			int a1                    = ;
			int a2                    = ;
			int an                    = ;
			int b1                    = ;
			int b2                    = ;
			int bn                    = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new OldBridges().isPossible(bridges, a1, a2, an, b1, b2, bn));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
