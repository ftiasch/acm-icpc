import java.math.*;
import java.util.*;

public class DisjointSemicircles {
    boolean isIntersect(Pair a, Pair b) {
        return (a.x < b.x && b.x < a.y) != (a.x < b.y && b.y < a.y);
    }

    boolean colorit(ArrayList <ArrayList <Edge> > graph, int[] color, int u) {
        for (Object object : graph.get(u)) {
            Edge e = (Edge) object;
            if (color[e.v] == -1) {
                color[e.v] = color[u] ^ e.w;
                if (!colorit(graph, color, e.v)) {
                    return false;
                }
            } else if ((color[u] ^ e.w) != color[e.v]) {
                return false;
            }
        }
        return true;
    }

    ArrayList <ArrayList <Edge> > newGraph(int n) {
        ArrayList <ArrayList <Edge> > graph = new ArrayList <ArrayList <Edge> >();
        for (int i = 0; i < n; ++ i) {
            graph.add(new ArrayList <Edge>());
        }
        return graph;
    }

    boolean isBipartite(ArrayList <ArrayList <Edge> > graph) {
        int n = graph.size();
        int[] color = new int[n];
        Arrays.fill(color, -1);
        for (int i = 0; i < n; ++ i) {
            if (color[i] == -1) {
                color[i] = 0;
                if (!colorit(graph, color, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean generate(ArrayList <Integer> indexes, ArrayList <Pair> pairs, boolean[] used, int i) {
        if (i < used.length) {
            if (used[i]) {
                return generate(indexes, pairs, used, i + 1);
            } 
            for (int j = i + 1; j < used.length; ++ j) {
                if (!used[j]) {
                    used[j] = true;
                    pairs.add(new Pair(indexes.get(i), indexes.get(j)));
                    if (generate(indexes, pairs, used, i + 1)) {
                        return true;
                    }
                    pairs.remove(pairs.size() - 1);
                    used[j] = false;
                }
            }
            return false;
        }
        ArrayList <ArrayList <Edge> > graph = newGraph(pairs.size());
        for (int j = 0; j < pairs.size(); ++ j) {
            for (int k = 0; k < pairs.size(); ++ k) {
                if (j != k && isIntersect(pairs.get(j), pairs.get(k))) {
                    graph.get(j).add(new Edge(j, k, 1));
                }
            }
        }
        return isBipartite(graph);
    }

    void addEdge(ArrayList <ArrayList <Edge> > graph, int a, int b, int c) {
        graph.get(a).add(new Edge(a, b, c));
        graph.get(b).add(new Edge(b, a, c));
    }

    boolean solve(int[] labels) {
        int n = labels.length;
        ArrayList <Integer> indexes = new ArrayList <Integer>();
        for (int i = 0; i < n; ++ i) {
            if (labels[i] == -1) {
                indexes.add(i);
            }
        }
        ArrayList <Pair> pairs = new ArrayList <Pair>();
        for (int j = 0; j < n; ++ j) {
            for (int i = 0; i < j; ++ i) {
                if (labels[i] != -1 && labels[j] != -1 && labels[i] == labels[j]) {
                    pairs.add(new Pair(i, j));
                }
            }
        }
        if (indexes.size() <= 12) {
            return generate(indexes, pairs, new boolean[indexes.size()], 0);
        }
        int[] sum = new int[n + 1];
        for (int i = n - 1; i >= 0; -- i) {
            sum[i] = sum[i + 1] + (labels[i] == -1 ? 1 : 0);
        }
        for (int mask = 0; mask < 1 << pairs.size(); ++ mask) {
            boolean valid = true;
            for (int j = 0; j < pairs.size(); ++ j) {
                for (int i = 0; i < j; ++ i) {
                    valid &= !isIntersect(pairs.get(i), pairs.get(j)) || (mask >> i & 1) != (mask >> j & 1);
                }
            }
            if (valid) {
                ArrayList <ArrayList <Edge> > graph = newGraph(n + 1);
                addEdge(graph, 0, n, 0);
                for (int i = 0; i < n; ++ i) {
                    if (labels[i] != -1) {
                        addEdge(graph, i, i + 1, 0);
                    }
                }
                for (int i = 0; i < pairs.size(); ++ i) {
                    int l = pairs.get(i).x;
                    int r = pairs.get(i).y + 1;
                    if ((mask >> i & 1) == 0) {
                        addEdge(graph, l, r, sum[l] - sum[r] & 1);
                    } else {
                        addEdge(graph, l, r, 0);
                    }
                }
                if (isBipartite(graph)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getPossibility(int[] labels) {
        return solve(labels) ? "POSSIBLE" : "IMPOSSIBLE";
    }

    class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return String.format("{%d, %d}", x, y);
        }
    }

    class Edge {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			DisjointSemicirclesHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				DisjointSemicirclesHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class DisjointSemicirclesHarness {
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
			int[] labels              = { -1, 0, -1, -1, 0, -1 };
			String expected__         = "POSSIBLE";

			return verifyCase(casenum__, expected__, new DisjointSemicircles().getPossibility(labels));
		}
		case 1: {
			int[] labels              = { 1, -1, 2, 1, -1, 2 };
			String expected__         = "IMPOSSIBLE";

			return verifyCase(casenum__, expected__, new DisjointSemicircles().getPossibility(labels));
		}
		case 2: {
			int[] labels              = { 2, -1, -1, 0, -1, -1, 2, 0 };
			String expected__         = "POSSIBLE";

			return verifyCase(casenum__, expected__, new DisjointSemicircles().getPossibility(labels));
		}
		case 3: {
			int[] labels              = { -1, 1, 3, -1, 1, 3, -1, -1 };
			String expected__         = "IMPOSSIBLE";

			return verifyCase(casenum__, expected__, new DisjointSemicircles().getPossibility(labels));
		}
		case 4: {
			int[] labels              = { -1, 5, -1, -1, 3, 6, 8, -1, 10, 7, -1, 7, 8, 0, 11, -1, -1, 11, 0, 10, 4, -1, 6, 5, -1, -1, 9, 9, 4, 3 } ;
			String expected__         = "POSSIBLE";

			return verifyCase(casenum__, expected__, new DisjointSemicircles().getPossibility(labels));
		}
		case 5: {
			int[] labels              = { 4, -1, 2, 4, -1, 3, 3, 12, 2, 5, -1, 0, 9, 9, 8, -1, 12, 8, -1, 6, 0, -1, -1, -1, 5, 6, 10, -1, -1, 10 } ;
			String expected__         = "IMPOSSIBLE";

			return verifyCase(casenum__, expected__, new DisjointSemicircles().getPossibility(labels));
		}
		case 6: {
			int[] labels              = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 } ;
			String expected__         = "POSSIBLE";

			return verifyCase(casenum__, expected__, new DisjointSemicircles().getPossibility(labels));
		}

		// custom cases

        case 7: {
			int[] labels              = {-1, 7, -1, 11, 15, 10, 4, 9, 2, 5, -1, 11, 12, -1, 2, 13, 15, 9, 14, 8, 10, -1, 0, -1, -1, -1, -1, 1, 12, 14, 7, 13, -1, 6, 3, -1, -1, -1, -1, -1, 1, 6, 0, 8, 3, 4, -1, 5, -1, -1};
			String expected__         = "IMPOSSIBLE";

			return verifyCase(casenum__, expected__, new DisjointSemicircles().getPossibility(labels));
		}
/*      case 8: {
			int[] labels              = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new DisjointSemicircles().getPossibility(labels));
		}*/
/*      case 9: {
			int[] labels              = ;
			String expected__         = ;

			return verifyCase(casenum__, expected__, new DisjointSemicircles().getPossibility(labels));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
