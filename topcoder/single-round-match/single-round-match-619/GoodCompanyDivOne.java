import java.math.*;
import java.util.*;

public class GoodCompanyDivOne {
    List <List <Integer> > children;

    public int minimumCost(int[] superior, int[] training) {
        int n = superior.length;
        children = new ArrayList <List <Integer>>();
        for (int i = 0; i < n; ++ i) {
            children.add(new ArrayList <Integer>());
        }
        for (int i = 1; i < n; ++ i) {
            children.get(superior[i]).add(i);
        }
        int m = training.length;
        int[][] minimum = new int[n][m];
        for (int i = n - 1; i >= 0; -- i) {
            Arrays.fill(minimum[i], Integer.MAX_VALUE);
            for (int j = 0; j < m; ++ j) {
                int source = m + children.get(i).size() + 1;
                int target = source + 1;
                Network network = new Network(target + 1);
                for (int x = 0; x < m; ++ x) {
                    network.addEdge(source, x, 1, 0);
                }
                int minTraining = Integer.MAX_VALUE;
                for (int y = 0; y <= children.get(i).size(); ++ y) {
                    network.addEdge(m + y, target, 1, 0);
                    for (int x = 0; x < m; ++ x) {
                        if (y < children.get(i).size()) {
                            int v = children.get(i).get(y);
                            if (minimum[v][x] < Integer.MAX_VALUE) {
                                network.addEdge(x, m + y, 1, minimum[v][x] + training[x]);
                            }
                        } else if (x != j) {
                            minTraining = Math.min(minTraining, training[x]);
                            network.addEdge(x, source - 1, 1, training[x]);
                        }
                    }
                }
                if (minTraining < Integer.MAX_VALUE) {
                    network.addEdge(j, source - 1, 1, minTraining);
                }
                int[] result = network.minCostMaxFlow(source, target);
                if (result[0] == children.get(i).size() + 1) {
                    minimum[i][j] = result[1];
                }
            }
        }
        int result = Integer.MAX_VALUE;
        for (int j = 0; j < m; ++ j) {
            if (minimum[0][j] < Integer.MAX_VALUE) {
                result = Math.min(result, minimum[0][j] + training[j]);
            }
        }
        return result < Integer.MAX_VALUE ? result : -1;
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
            GoodCompanyDivOneHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                GoodCompanyDivOneHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class GoodCompanyDivOneHarness {
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
            int[] superior            = {-1};
            int[] training            = {1, 2};
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new GoodCompanyDivOne().minimumCost(superior, training));
        }
        case 1: {
            int[] superior            = {-1, 0, 0};
            int[] training            = {1, 2, 3};
            int expected__            = 10;

            return verifyCase(casenum__, expected__, new GoodCompanyDivOne().minimumCost(superior, training));
        }
        case 2: {
            int[] superior            = {-1, 0, 0, 0};
            int[] training            = {1, 2, 3};
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new GoodCompanyDivOne().minimumCost(superior, training));
        }
        case 3: {
            int[] superior            = {-1, 0, 0, 2, 2, 2, 1, 1, 6, 0, 5, 4, 11, 10, 3, 6, 11, 7, 0, 2, 13, 14, 2, 10, 9, 11, 22, 10, 3};
            int[] training            = {4, 2, 6, 6, 8, 3, 3, 1, 1, 5, 8, 6, 8, 2, 4};
            int expected__            = 71;

            return verifyCase(casenum__, expected__, new GoodCompanyDivOne().minimumCost(superior, training));
        }

        // custom cases

/*      case 4: {
            int[] superior            = ;
            int[] training            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new GoodCompanyDivOne().minimumCost(superior, training));
        }*/
/*      case 5: {
            int[] superior            = ;
            int[] training            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new GoodCompanyDivOne().minimumCost(superior, training));
        }*/
/*      case 6: {
            int[] superior            = ;
            int[] training            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new GoodCompanyDivOne().minimumCost(superior, training));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
