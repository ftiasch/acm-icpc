import java.math.*;
import java.util.*;

public class CurvyonRails {
    @SuppressWarnings("unchecked")
    class Network {
        int n;
        ArrayList[] graph;

        class Edge {
            int u, v, capacity, cost;
            Edge back;

            Edge(int u, int v, int capacity, int cost) {
                this.u = u;
                this.v = v;
                this.capacity = capacity;
                this.cost = cost;
            }

            public String toString() {
                return String.format("%d->%d %d %d", u, v, capacity, cost);
            }
        }

        Network(int n) {
            this.n = n;
            graph = new ArrayList[n];
            for (int i = 0; i < n; ++ i) {
                graph[i] = new ArrayList <Edge>();
            }
        }

        void addEdge(int u, int v, int capacity, int cost) {
//System.err.println(String.format("%d->%d", u, v));
            Edge e = new Edge(u, v, capacity, cost);
            Edge f = new Edge(v, u, 0, -cost);
            e.back = f;
            graph[u].add(e);
            f.back = e;
            graph[v].add(f);
        }

        int[] maxCostMaxFlow(int source, int target) {
            int flow = 0;
            int cost = 0;
            Edge[] back = new Edge[n];
            int[] distance = new int[n];
            boolean[] visit = new boolean[n];
            while (true) {
                Arrays.fill(distance, Integer.MIN_VALUE);
                distance[source] = 0;
                Arrays.fill(visit, false);
                Queue <Integer> queue = new LinkedList <Integer>();
                queue.offer(source);
                while (!queue.isEmpty()) {
                    int u = queue.poll();
                    visit[u] = false;
                    for (Object obj : graph[u]) {
                        Edge e = (Edge)obj;
                        if (e.capacity > 0 && distance[u] + e.cost > distance[e.v]) {
                            back[e.v] = e;
                            distance[e.v] = distance[u] + e.cost;
                            if (!visit[e.v]) {
                                visit[e.v] = true;
                                queue.offer(e.v);
                            }
                        }
                    }
                }
                if (distance[target] == Integer.MIN_VALUE) {
                    break;
                }
                flow ++;
                for (int i = target; i != source; i = back[i].back.v) {
                    Edge e = back[i];
                    cost += e.cost;
                    e.capacity --;
                    e.back.capacity ++;
                }
            }
            return new int[]{flow, cost};
        }
    }

    int n, m;

    int getID(int level, int x, int y) {
        return (x * m + y) * 4 + level;
    }

    final static int[] DELTA_X = { 0, 0, -1, 1};
    final static int[] DELTA_Y = {-1, 1,  0, 0};

    public int getmin(String[] field) {
        n = field.length;
        m = field[0].length();
        int count = 0;
        int cCount = 0;
        Network network = new Network(n * m * 4 + 2);
        int source = n * m * 4;
        int target = source + 1;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                if (field[i].charAt(j) == 'w') {
                    continue;
                }
                count ++;
                boolean ok = field[i].charAt(j) == 'C';
                if (ok) {
                    cCount ++;
                }
                if ((i + j) % 2 == 0) {
                    int x = getID(2, i, j);
                    network.addEdge(source, x, 2, 0);
                    for (int t = 0; t < 2; ++ t) {
                        network.addEdge(x, getID(t, i, j), 1, 0);
                        network.addEdge(x, getID(t, i, j), 1, ok ? 1 : 0);
                    }
                    for (int k = 0; k < 4; ++ k) {
                        int ii = i + DELTA_X[k];
                        int jj = j + DELTA_Y[k];
                        if (0 <= ii && ii < n && 0 <= jj && jj < m) {
                            int t = k / 2;
                            network.addEdge(getID(t, i, j), getID(t, ii, jj), 1, 0);
                        }
                    }
                } else {
                    int x = getID(3, i, j);
                    for (int t = 0; t < 2; ++ t) {
                        network.addEdge(getID(t, i, j), x, 1, 0);
                        network.addEdge(getID(t, i, j), x, 1, ok ? 1 : 0);
                    }
                    network.addEdge(x, target, 2, 0);
                }
            }
        }
        int[] ret = network.maxCostMaxFlow(source, target);
        if (ret[0] != count) {
            return -1;
        }
        return cCount * 2 - ret[1];
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            CurvyonRailsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                CurvyonRailsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class CurvyonRailsHarness {
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
            String[] field            = {".."
,".."};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new CurvyonRails().getmin(field));
        }
        case 1: {
            String[] field            = {"wCCww"
,"wCC.."
,"..w.."
,"....w"
,"ww..w"};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new CurvyonRails().getmin(field));
        }
        case 2: {
            String[] field            = {"C.w"
,"..."
,".C."};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new CurvyonRails().getmin(field));
        }
        case 3: {
            String[] field            = {"."};
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new CurvyonRails().getmin(field));
        }
        case 4: {
            String[] field            = {"w"};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new CurvyonRails().getmin(field));
        }
        case 5: {
            String[] field            = {"CC..CCCw.CwC..CC.w.C",
 "C.CCCwCCC.w.w..C.w..",
 "wwww...CC.wC.Cw.CC..",
 "CC..CC.w..w.C..CCCC.",
 "CC.CCC..CwwCCC.wCC..",
 "w.C..wwCC.CC.wwwCC..",
 ".CC.CC..CCC..CC.CC.C",
 "Cw....C.C.CCC...CC..",
 "CC.C..Cww.C.CwwwC..w",
 "wCCww..C...CCCCCCC.w",
 "C.CCw.CC.ww...C.CCww",
 "C.C.C.CCwCC..wCCw.Cw",
 "CCC.C...w..C.wC.wCCw",
 "CC.C..C..CCC.CC.C...",
 "C.ww...CCC..CC...CCC",
 "...CCC.CwwwC..www.C.",
 "wwCCCCC.w.C.C...wCwC",
 "CCwC.CwCCC.C.w.Cw...",
 "C.w.wC.CC.CCC.C.w.Cw",
 "CCw.CCC..C..CC.CwCCw",
 "C.wwwww.CwwCCwwwwwww"};
            int expected__            = 9;

            return verifyCase(casenum__, expected__, new CurvyonRails().getmin(field));
        }

        // custom cases

      case 6: {
            String[] field            = {
"CCC",
"CCC",
"CCC"
};
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new CurvyonRails().getmin(field));
        }
/*      case 7: {
            String[] field            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new CurvyonRails().getmin(field));
        }*/
/*      case 8: {
            String[] field            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new CurvyonRails().getmin(field));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
