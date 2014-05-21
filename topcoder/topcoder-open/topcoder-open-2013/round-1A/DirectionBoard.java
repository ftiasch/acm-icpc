import java.util.*;
import java.math.*;

public class DirectionBoard {
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
//System.err.println(String.format("%d->%d %d", u, v, capacity, cost));
            Edge e = new Edge(u, v, capacity, cost);
            Edge f = new Edge(v, u, 0, -cost);
            e.back = f;
            graph[u].add(e);
            f.back = e;
            graph[v].add(f);
        }

        int[] minCostMaxFlow(int source, int target) {
            int flow = 0;
            int cost = 0;
            Edge[] back = new Edge[n];
            int[] distance = new int[n];
            boolean[] visit = new boolean[n];
            while (true) {
                Arrays.fill(distance, Integer.MAX_VALUE);
                distance[source] = 0;
                Arrays.fill(visit, false);
                Queue <Integer> queue = new LinkedList <Integer>();
                queue.offer(source);
                while (!queue.isEmpty()) {
                    int u = queue.poll();
                    visit[u] = false;
                    for (Object obj : graph[u]) {
                        Edge e = (Edge)obj;
                        if (e.capacity > 0 && distance[u] + e.cost < distance[e.v]) {
                            back[e.v] = e;
                            distance[e.v] = distance[u] + e.cost;
                            if (!visit[e.v]) {
                                visit[e.v] = true;
                                queue.offer(e.v);
                            }
                        }
                    }
                }
                if (distance[target] == Integer.MAX_VALUE) {
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

    final static String DIRECTIONS = "LRUD";
    final static int[] DELTA_X = { 0, 0, -1, 1};
    final static int[] DELTA_Y = {-1, 1,  0, 0};

    int n, m;

    int getID(int t, int x, int y) {
        return (x * m + y) * 2 + t;
    }

    public int getMinimum(String[] board) {
        n = board.length;
        m = board[0].length();
        Network network = new Network(n * m * 2 + 2);
        int source = n * m * 2;
        int target = source + 1;
        int[] degree = new int[n * m];
        for (int x = 0; x < n; ++ x) {
            for (int y = 0; y < m; ++ y) {
                network.addEdge(source, getID(0, x, y), 1, 0);
                network.addEdge(getID(1, x, y), target, 1, 0);
                int t = DIRECTIONS.indexOf(board[x].charAt(y));
                for (int k = 0; k < 4; ++ k) {
                    int xx = (x + n + DELTA_X[k]) % n;
                    int yy = (y + m + DELTA_Y[k]) % m;
                    network.addEdge(getID(0, x, y), getID(1, xx, yy), 1, k == t ? 0 : 1);
                }
            }
        }
        int[] ret = network.minCostMaxFlow(source, target);
        return ret[1];
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            DirectionBoardHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                DirectionBoardHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class DirectionBoardHarness {
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
            String[] board            = {"RRRD",
 "URDD",
 "UULD",
 "ULLL"};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new DirectionBoard().getMinimum(board));
        }
        case 1: {
            String[] board            = {"RRRD",
 "URLL",
 "LRRR"};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new DirectionBoard().getMinimum(board));
        }
        case 2: {
            String[] board            = {"RRD",
 "URD",
 "ULL"};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new DirectionBoard().getMinimum(board));
        }
        case 3: {
            String[] board            = {"ULRLRD",
 "UDDLRR"};
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new DirectionBoard().getMinimum(board));
        }
        case 4: {
            String[] board            = {"UDLRLDLD",
 "DLDLLDLR",
 "LLLLLDLD",
 "UUURRRDD"};
            int expected__            = 9;

            return verifyCase(casenum__, expected__, new DirectionBoard().getMinimum(board));
        }
        case 5: {
            String[] board            = {"UDUDUUDUDUDUDUR",
 "LLLLDUUDRDLUDRU",
 "DLLDLDURDURUDDL",
 "UDUDUUDUDUDUDUR",
 "LLLLDUUDRDLUDRU",
 "DLLDLDURDURUDDL",
 "UDUDUUDUDUDUDUR",
 "LLLLDUUUDDLUDRU",
 "DLLDLDURDURUDDL",
 "UDUDUUDUDUDUDUR",
 "LLLLDUUDRDLUDRU",
 "DLLDLDURDURUDDL",
 "UDUDUUDUDUDUDUR",
 "LLLLDUUDRDLUDRU",
 "RRRDLDURDURUDDR"}
;
            int expected__            = 73;

            return verifyCase(casenum__, expected__, new DirectionBoard().getMinimum(board));
        }

        // custom cases

/*      case 6: {
            String[] board            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new DirectionBoard().getMinimum(board));
        }*/
/*      case 7: {
            String[] board            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new DirectionBoard().getMinimum(board));
        }*/
/*      case 8: {
            String[] board            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new DirectionBoard().getMinimum(board));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
