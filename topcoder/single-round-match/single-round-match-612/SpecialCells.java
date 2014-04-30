import java.math.*;
import java.util.*;

public class SpecialCells {
    public int guess(int[] xs, int[] ys) {
        Map <Integer, Integer> ids = new HashMap <Integer, Integer>();
        int m = 0;
        for (int x : xs) {
            if (!ids.containsKey(+x)) {
                ids.put(+x, m ++);
            }
        }
        int m1 = m;
        for (int y : ys) {
            if (!ids.containsKey(-y)) {
                ids.put(-y, m ++);
            }
        }
        boolean[][] visited = new boolean[m][m];
        Network network = new Network(m + 2);
        for (int i = 0; i < xs.length; ++ i) {
            int x = ids.get(+xs[i]);
            int y = ids.get(-ys[i]);
            visited[x][y] = true;
            network.addEdge(x, y, 1, 1);
        }
        for (int i = 0; i < m1; ++ i) {
            for (int j = m1; j < m; ++ j) {
                if (!visited[i][j]) {
                    network.addEdge(i, j, 1, 0);
                }
            }
        }
        for (int x : xs) {
            network.addEdge(m, ids.get(+x), 1, 0);
        }
        for (int y : ys) {
            network.addEdge(ids.get(-y), m + 1, 1, 0);
        }
        return network.minCostMaxFlow(m, m + 1)[1];
    }

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

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            SpecialCellsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                SpecialCellsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class SpecialCellsHarness {
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
            int[] x                   = {1,2};
            int[] y                   = {1,2};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new SpecialCells().guess(x, y));
        }
        case 1: {
            int[] x                   = {1,1,2};
            int[] y                   = {1,2,1};
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new SpecialCells().guess(x, y));
        }
        case 2: {
            int[] x                   = {1,2,1,2,1,2};
            int[] y                   = {1,2,3,1,2,3};
            int expected__            = 6;

            return verifyCase(casenum__, expected__, new SpecialCells().guess(x, y));
        }
        case 3: {
            int[] x                   = {1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9};
            int[] y                   = {1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3};
            int expected__            = 9;

            return verifyCase(casenum__, expected__, new SpecialCells().guess(x, y));
        }
        case 4: {
            int[] x                   = {1,100000};
            int[] y                   = {1,100000};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new SpecialCells().guess(x, y));
        }

        // custom cases

/*      case 5: {
            int[] x                   = ;
            int[] y                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new SpecialCells().guess(x, y));
        }*/
/*      case 6: {
            int[] x                   = ;
            int[] y                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new SpecialCells().guess(x, y));
        }*/
/*      case 7: {
            int[] x                   = ;
            int[] y                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new SpecialCells().guess(x, y));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
