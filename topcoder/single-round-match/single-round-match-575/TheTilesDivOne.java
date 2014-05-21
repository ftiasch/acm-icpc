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
        return delta;
    }
}

public class TheTilesDivOne {
    final static int[] DELTA_X = {-1, 0, 0, 1};
    final static int[] DELTA_Y = {0, -1, 1, 0};

    int n, m;

    int getID(int x, int y, int l) {
        return (x * m + y) << 1 | l;
    }

    public int find(String[] board) {
        n = board.length;
        m = board[0].length();
        FlowSolver solver = new FlowSolver(n * m * 2 + 2);
        int source = n * m * 2;
        int target = source + 1;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                if (board[i].charAt(j) == 'X') {
                    continue;
                }
                if ((i + j) % 2 == 0) {
                    solver.addEdge(getID(i, j, 0), getID(i, j, 1), 1);
                    for (int k = 0; k < 4; ++ k) {
                        int x = i + DELTA_X[k];
                        int y = j + DELTA_Y[k];
                        if (0 <= x && x < n && 0 <= y && y < m) {
                            if (x % 2 == 0) {
                                solver.addEdge(getID(x, y, 0), getID(i, j, 0), 1);
                            } else {
                                solver.addEdge(getID(i, j, 1), getID(x, y, 0), 1);
                            }
                        }
                    }
                } else {
                    if (i % 2 == 0) {
                        solver.addEdge(source, getID(i, j, 0), 1);
                    } else {
                        solver.addEdge(getID(i, j, 0), target, 1);
                    }
                }
            }
        }
        return solver.getMaxFlow(source, target);
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            TheTilesDivOneHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                TheTilesDivOneHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class TheTilesDivOneHarness {
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
            String[] board            = {"X.X",
 "...",
 "X.X"};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new TheTilesDivOne().find(board));
        }
        case 1: {
            String[] board            = {"...",
 "...",
 "..."};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new TheTilesDivOne().find(board));
        }
        case 2: {
            String[] board            = {"......X.X.XXX.X.XX."};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new TheTilesDivOne().find(board));
        }
        case 3: {
            String[] board            = {"X.....XXX.XX..XXXXXXXXX...X.XX.XX....X",
 ".XXXX..X..XXXXXXXX....XX.X.X.X.....XXX",
 "....XX....X.XX..X.X...XX.X..XXXXXXX..X",
 "XX.XXXXX.X.X..X..XX.XXX..XX...XXX.X..."};
            int expected__            = 13;

            return verifyCase(casenum__, expected__, new TheTilesDivOne().find(board));
        }

        // custom cases

/*      case 4: {
            String[] board            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TheTilesDivOne().find(board));
        }*/
/*      case 5: {
            String[] board            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TheTilesDivOne().find(board));
        }*/
/*      case 6: {
            String[] board            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TheTilesDivOne().find(board));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
