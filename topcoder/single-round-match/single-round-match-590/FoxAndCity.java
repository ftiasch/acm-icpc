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

    int getMaxFlow(int source, int target) {
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
        if (delta < limit) {
            level[u] = -1;
        }
        return delta;
    }
}

public class FoxAndCity {
    int n;

    int getID(int x, int y) {
        return x * (n + 1) + y;
    }

    public int minimalCost(String[] linked, int[] want) {
        n = linked.length;
        FlowSolver solver = new FlowSolver(n * (n + 1) + 2);
        int source = n * (n + 1);
        int target = source + 1;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                solver.addEdge(getID(i, j), getID(i, j + 1), (i == 0) == (j > 0) ? Integer.MAX_VALUE : (j - want[i]) * (j - want[i]));
                for (int k = 0; k < n; ++ k) {
                    if (linked[i].charAt(k) == 'Y') {
                        solver.addEdge(getID(i, j + 1), getID(k, j), Integer.MAX_VALUE);
                    }
                }
            }
        }
        for (int i = 0; i < n; ++ i) {
            solver.addEdge(source, getID(i, 0), Integer.MAX_VALUE);
            solver.addEdge(getID(i, n), target, Integer.MAX_VALUE);
        }
        return solver.getMaxFlow(source, target);
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			FoxAndCityHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				FoxAndCityHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class FoxAndCityHarness {
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
			String[] linked           = {"NYN",
 "YNY",
 "NYN"};
			int[] want                = {0, 1, 1};
			int expected__            = 0;

			return verifyCase(casenum__, expected__, new FoxAndCity().minimalCost(linked, want));
		}
		case 1: {
			String[] linked           = {"NYNN",
 "YNYN",
 "NYNY",
 "NNYN"};
			int[] want                = {0, 3, 3, 3};
			int expected__            = 5;

			return verifyCase(casenum__, expected__, new FoxAndCity().minimalCost(linked, want));
		}
		case 2: {
			String[] linked           = {"NYNNNY",
 "YNYNNN",
 "NYNYNN",
 "NNYNYN",
 "NNNYNY",
 "YNNNYN"};
			int[] want                = {0, 2, 2, 2, 2, 2};
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new FoxAndCity().minimalCost(linked, want));
		}
		case 3: {
			String[] linked           = {"NYY","YNN","YNN"};
			int[] want                = {0,0,0};
			int expected__            = 2;

			return verifyCase(casenum__, expected__, new FoxAndCity().minimalCost(linked, want));
		}
		case 4: {
			String[] linked           = {"NYNNNN",
 "YNYNNN",
 "NYNYYY",
 "NNYNYY",
 "NNYYNY",
 "NNYYYN"}
;
			int[] want                = {0, 1, 2, 3, 0, 3};
			int expected__            = 3;

			return verifyCase(casenum__, expected__, new FoxAndCity().minimalCost(linked, want));
		}
		case 5: {
			String[] linked           = {"NYNNNN",
 "YNYNNN",
 "NYNYYY",
 "NNYNYY",
 "NNYYNY",
 "NNYYYN"};
			int[] want                = {0, 1, 2, 4, 0, 4};
			int expected__            = 6;

			return verifyCase(casenum__, expected__, new FoxAndCity().minimalCost(linked, want));
		}
		case 6: {
			String[] linked           = {"NYNYYYYYYYY","YNYNNYYNYYY","NYNNNYYNYYN","YNNNYYYYYYY","YNNYNYYYNYY","YYYYYNNYYNY","YYYYYNNNYYY","YNNYYYNNNYY","YYYYNYYNNNY","YYYYYNYYNNY","YYNYYYYYYYN"};
			int[] want                = {0,1,2,0,0,5,1,3,0,2,3};
			int expected__            = 28;

			return verifyCase(casenum__, expected__, new FoxAndCity().minimalCost(linked, want));
		}

		// custom cases

/*      case 7: {
			String[] linked           = ;
			int[] want                = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new FoxAndCity().minimalCost(linked, want));
		}*/
/*      case 8: {
			String[] linked           = ;
			int[] want                = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new FoxAndCity().minimalCost(linked, want));
		}*/
/*      case 9: {
			String[] linked           = ;
			int[] want                = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new FoxAndCity().minimalCost(linked, want));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
