import java.io.*;
import java.util.*;

public class ParenthesesTree {
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
        List<List<Edge>> graph;

        Graph(int n) {
            this.n = n;
            graph = new ArrayList<List<Edge>>();
            for (int i = 0; i < n; ++ i) {
                graph.add(new ArrayList<Edge>());
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
            Queue<Integer> queue = new LinkedList<Integer>();
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
// if (level[t] == 0) {
//     for (int i = 0; i < n; ++ i) {
//         if (level[i] == 0) {
//             System.err.println(String.format("%d [color=red]", i));
//         }
//     }
// }
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

    int n;
    int[] begin, depth, parent;

    int getValue(int i, int j) {
        return -depth[i] + j * 2;
    }

    int getNode(int i, int j) {
        assert 0 <= j && j <= depth[i] + 1;
        if (j == 0) {
            return 0;
        }
        if (j == depth[i] + 1) {
            return 1;
        }
        return begin[i] + (j - 1);
    }

    int getLCA(int u, int v) {
        if (u == v) {
            return u;
        }
        return depth[u] > depth[v] ? getLCA(parent[u - 1], v) : getLCA(u, parent[v - 1]);
    }

    static int VERY_BIG = 1000000000;

    void addLessThan(Graph graph, int from, int p) {
        for (int i = 0, j = -1; i <= depth[p]; ++ i) {
            while (j < depth[from] && getValue(from, j + 1) <= getValue(p, i)) {
                j ++;
            }
            graph.addEdge(getNode(from, j + 1), getNode(p, i + 1), VERY_BIG);
        }
    }

    void addEdges(Graph graph, int from, int to) {
        int p = from;
        while (p != to) {
            p = parent[p - 1];
            addLessThan(graph, from, p);
        }
    }

    public int minCost(int[] parent_, int[] leftCost, int[] rightCost, int[] from, int[] to) {
        parent = parent_;
        n = parent.length + 1;
        int v = 2;
        begin = new int[n];
        depth = new int[n];
        for (int i = 1; i < n; ++ i) {
            depth[i] = depth[parent[i - 1]] + 1;
            begin[i] = v;
            v += depth[i];
        }
        Graph graph = new Graph(v);
        for (int i = 1; i < n; ++ i) {
            int p = parent[i - 1];
            for (int j = 0; j <= depth[p]; ++ j) {
                graph.addEdge(getNode(p, j), getNode(i, j), VERY_BIG);
                graph.addEdge(getNode(p, j), getNode(i, j + 1), rightCost[i - 1]);
                graph.addEdge(getNode(i, j + 1), getNode(p, j + 1), leftCost[i - 1]);
                graph.addEdge(getNode(i, j + 2), getNode(p, j + 1), VERY_BIG);
            }
        }
        for (int i = 0; i < from.length; ++ i) {
            if ((depth[from[i]] - depth[to[i]] & 1) != 0) {
                return -1;
            }
            int w = getLCA(from[i], to[i]);
            addLessThan(graph, from[i], to[i]);
            addLessThan(graph, to[i], from[i]);
            addEdges(graph, from[i], w);
            addEdges(graph, to[i], w);
        }
// System.err.println("digraph {");
// for (List<Graph.Edge> edges : graph.graph) {
//     for (Graph.Edge edge : edges) {
//         if (0 < edge.capacity && edge.capacity < Integer.MAX_VALUE) {
//             System.err.println(String.format("%d->%d [label=\"%d\"]", edge.from, edge.to, edge.capacity));
//         }
//     }
// }
        int result = graph.maxFlow(0, 1);
// System.err.println("}");
        return result >= VERY_BIG ? -1 : result;
    }

    protected static void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            ParenthesesTreeHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                ParenthesesTreeHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class ParenthesesTreeHarness {
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
            int[] p                   = {0,1,2,3};
            int[] L                   = {1,2,3,4};
            int[] R                   = {10,20,30,40};
            int[] a                   = {0};
            int[] b                   = {4};
            int expected__            = 64;

            return verifyCase(casenum__, expected__, new ParenthesesTree().minCost(p, L, R, a, b));
        }
        case 1: {
            int[] p                   = {0,1,2,3};
            int[] L                   = {1,2,3,4};
            int[] R                   = {10,20,30,40};
            int[] a                   = {0};
            int[] b                   = {3};
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new ParenthesesTree().minCost(p, L, R, a, b));
        }
        case 2: {
            int[] p                   = {0,1,2,3};
            int[] L                   = {1,2,3,4};
            int[] R                   = {10,20,30,40};
            int[] a                   = {2};
            int[] b                   = {2};
            int expected__            = 10;

            return verifyCase(casenum__, expected__, new ParenthesesTree().minCost(p, L, R, a, b));
        }
        case 3: {
            int[] p                   = {0,1,1,3,4,4};
            int[] L                   = {1,2,3,4,5,6};
            int[] R                   = {100,200,300,400,500,600};
            int[] a                   = {0,0,2,2,1};
            int[] b                   = {5,6,5,6,4};
            int expected__            = 1704;

            return verifyCase(casenum__, expected__, new ParenthesesTree().minCost(p, L, R, a, b));
        }
        case 4: {
            int[] p                   = {0,1,1,3,4,4};
            int[] L                   = {1,2,3,4,5,6};
            int[] R                   = {100,200,300,400,500,600};
            int[] a                   = {0,0,2,2};
            int[] b                   = {5,6,5,6};
            int expected__            = 1605;

            return verifyCase(casenum__, expected__, new ParenthesesTree().minCost(p, L, R, a, b));
        }
        case 5: {
            int[] p                   = {0, 0, 2, 0, 2, 3, 1, 5, 8, 2, 1, 6, 5, 5, 10, 11, 8, 12, 1};
            int[] L                   = {959, 475, 349, 884, 853, 73, 883, 737, 910, 435, 207, 1, 484, 490, 485, 968, 19, 428, 648};
            int[] R                   = {899, 770, 514, 327, 626, 84, 244, 695, 669, 138, 480, 778, 368, 892, 440, 976, 957, 959, 107};
            int[] a                   = {7, 13, 13, 5, 6, 1, 7};
            int[] b                   = {10, 13, 6, 17, 4, 2, 12};
            int expected__            = 9813;

            return verifyCase(casenum__, expected__, new ParenthesesTree().minCost(p, L, R, a, b));
        }

        // custom cases

/*      case 6: {
            int[] p                   = ;
            int[] L                   = ;
            int[] R                   = ;
            int[] a                   = ;
            int[] b                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ParenthesesTree().minCost(p, L, R, a, b));
        }*/
/*      case 7: {
            int[] p                   = ;
            int[] L                   = ;
            int[] R                   = ;
            int[] a                   = ;
            int[] b                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ParenthesesTree().minCost(p, L, R, a, b));
        }*/
/*      case 8: {
            int[] p                   = ;
            int[] L                   = ;
            int[] R                   = ;
            int[] a                   = ;
            int[] b                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ParenthesesTree().minCost(p, L, R, a, b));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
