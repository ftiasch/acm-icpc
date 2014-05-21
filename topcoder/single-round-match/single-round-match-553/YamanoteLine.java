import java.math.*;
import java.util.*;

public class YamanoteLine {
    final static long INFINITY = 100000000000L;

    ArrayList <ArrayList <Edge> > graph;

    void addEdge(int u, int v, int k, int l) {
        graph.get(u).add(new Edge(u, v, k, l));
    }

    Integer check(int n, long x) {
        int[] count = new int[n];
        int[] type = new int[n];
        long[] distance = new long[n];
        boolean[] visit = new boolean[n];
        Queue <Integer> queue = new LinkedList <Integer>();
        for (int i = 0; i < n; ++ i) {
            visit[i] = true;
            queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int u = queue.poll();
            visit[u] = false;
            for (Edge e : graph.get(u)) {
                int v = e.to;
                if (distance[u] + e.length(x) < distance[v]) {
                    type[v] = type[u] + e.coefficient;
                    distance[v] = distance[u] + e.length(x);
                    if (!visit[v]) {
                        visit[v] = true;
                        count[v] ++;
                        if (count[v] > n) {
                            assert distance[v] < 0;
                            return type[v];
                        }
                        queue.offer(v);
                    }
                }
            }
        }
        return null;
    }

    public long howMany(int n, int[] s1, int[] t1, int[] l1, int[] s2, int[] t2, int[] l2) {
        graph = new ArrayList <ArrayList <Edge> >();
        for (int i = 0; i <= n; ++ i) {
            graph.add(new ArrayList <Edge>());
        }
        // d[i + 1] - 1 >= d[i]
        for (int i = 0; i < n; ++ i) {
            addEdge(i + 1, i, 0, -1);
        }
        // d[n] - X >= d[0]
        // d[0] + X >= d[n]
        addEdge(n, 0, -1, 0);
        addEdge(0, n, 1, 0);
        for (int i = 0; i < s1.length; ++ i) {
            if (s1[i] < t1[i]) {
                // d[t1[i]] - l1[i] >= d[s1[i]]
                addEdge(t1[i], s1[i], 0, -l1[i]);
            } else {
                // d[t1[i]] + X - l1[i] >= d[s1[i]]
                addEdge(t1[i], s1[i], 1, -l1[i]);
            }
        }
        for (int i = 0; i < s2.length; ++ i) {
            if (s2[i] < t2[i]) {
                // d[s2[i]] + l2[i] >= d[t2[i]]
                addEdge(s2[i], t2[i], 0, l2[i]);
            } else {
                // d[s2[i]] + l2[i] - X >= d[t2[i]]
                addEdge(s2[i], t2[i], -1, l2[i]);
            }
        }
        long low = n;
        long high = INFINITY;
        while (low < high) {
            long middle = low + high >> 1;
            Integer ret = check(n + 1, middle);
            if (ret != null && (int)ret >= 0) {
                low = middle + 1;
            } else {
                high = middle;
            }
        }
        long down = high;
        low = n;
        high = INFINITY;
        while (low < high) {
            long middle = low + high + 1 >> 1;
            Integer ret = check(n + 1, middle);
            if (ret != null && (int)ret <= 0) {
                high = middle - 1;
            } else {
                low = middle;
            }
        }
        long up = low;
        if (up < INFINITY) {
            return Math.max(up - down + 1, 0);
        }
        return -1;
    }

    class Edge {
        int from, to, coefficient, length;

        Edge(int from, int to, int coefficient, int length) {
            this.from = from;
            this.to = to;
            this.coefficient = coefficient;
            this.length = length;
        }

        long length(long x) {
            return coefficient * x + length;
        }

        public String toString() {
            return String.format("%d->%d: %d", from, to, length);
        }
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            YamanoteLineHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                YamanoteLineHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class YamanoteLineHarness {
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

    static boolean compareOutput(long expected, long result) { return expected == result; }
    static String formatResult(long res) {
        return String.format("%d", res);
    }

    static int verifyCase(int casenum, long expected, long received) {
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
            int n                     = 3;
            int[] s1                  = {};
            int[] t1                  = {};
            int[] l1                  = {};
            int[] s2                  = {0,1,2};
            int[] t2                  = {1,2,0};
            int[] l2                  = {1,1,1};
            long expected__           = 1;

            return verifyCase(casenum__, expected__, new YamanoteLine().howMany(n, s1, t1, l1, s2, t2, l2));
        }
        case 1: {
            int n                     = 3;
            int[] s1                  = {};
            int[] t1                  = {};
            int[] l1                  = {};
            int[] s2                  = {0,1,2};
            int[] t2                  = {1,2,0};
            int[] l2                  = {2,2,2};
            long expected__           = 4;

            return verifyCase(casenum__, expected__, new YamanoteLine().howMany(n, s1, t1, l1, s2, t2, l2));
        }
        case 2: {
            int n                     = 3;
            int[] s1                  = {};
            int[] t1                  = {};
            int[] l1                  = {};
            int[] s2                  = {0,1,2};
            int[] t2                  = {2,0,1};
            int[] l2                  = {3,3,3};
            long expected__           = 2;

            return verifyCase(casenum__, expected__, new YamanoteLine().howMany(n, s1, t1, l1, s2, t2, l2));
        }
        case 3: {
            int n                     = 4;
            int[] s1                  = {0,1,2,3};
            int[] t1                  = {2,3,0,1};
            int[] l1                  = {3,4,4,3};
            int[] s2                  = {1,3};
            int[] t2                  = {3,1};
            int[] l2                  = {5,5};
            long expected__           = 4;

            return verifyCase(casenum__, expected__, new YamanoteLine().howMany(n, s1, t1, l1, s2, t2, l2));
        }
        case 4: {
            int n                     = 4;
            int[] s1                  = {0,2};
            int[] t1                  = {2,0};
            int[] l1                  = {5,5};
            int[] s2                  = {1,3};
            int[] t2                  = {3,1};
            int[] l2                  = {4,4};
            long expected__           = 0;

            return verifyCase(casenum__, expected__, new YamanoteLine().howMany(n, s1, t1, l1, s2, t2, l2));
        }
        case 5: {
            int n                     = 5;
            int[] s1                  = {};
            int[] t1                  = {};
            int[] l1                  = {};
            int[] s2                  = {0,2};
            int[] t2                  = {2,4};
            int[] l2                  = {2,2};
            long expected__           = -1;

            return verifyCase(casenum__, expected__, new YamanoteLine().howMany(n, s1, t1, l1, s2, t2, l2));
        }
        case 6: {
            int n                     = 10;
            int[] s1                  = {5,7,2,3,9,4,6,0,4,2};
            int[] t1                  = {0,8,3,9,8,0,8,7,1,7};
            int[] l1                  = {61,54,20,64,25,73,83,79,86,56};
            int[] s2                  = {4,5,4,0,8,3,8,5,5,9};
            int[] t2                  = {5,2,0,1,1,4,7,6,8,3};
            int[] l2                  = {1951,6102,3625,5737,1590,1228,9234,1342,9060,1008};
            long expected__           = 5726;

            return verifyCase(casenum__, expected__, new YamanoteLine().howMany(n, s1, t1, l1, s2, t2, l2));
        }

        // custom cases

      case 7: {
            int n = 7;
            int[] s1 = {1, 3, 6, 4, 2, 4, 5, 1, 5, 2, 5, 5, 0, 0, 5, 1, 6, 1, 2, 4, 5, 6, 3};
            int[] t1 = {4, 2, 1, 2, 0, 5, 4, 5, 6, 5, 2, 1, 2, 3, 6, 2, 3, 0, 6, 5, 4, 3, 5};
            int[] l1 = {536477, 947899, 178928, 724586, 963817, 239114, 939275, 789295, 211532, 713004, 471517, 393454, 223264, 455674, 217586, 81481, 476463, 1045502, 926258, 244861, 941222, 491485, 475836};
            int[] s2 = {5, 1, 4, 3, 2, 0, 2, 1, 3, 4, 6, 1, 4, 6, 2, 0, 1};
            int[] t2 = {4, 2, 6, 0, 4, 4, 3, 0, 1, 3, 1, 0, 3, 2, 0, 6, 0};
            int[] l2 = {937903, 78534, 455914, 732223, 466737, 678325, 223494, 1045758, 875109, 948016, 171545, 1048632, 941740, 256309, 956402, 1146000, 1046372};
            long expected__           = 0;

            return verifyCase(casenum__, expected__, new YamanoteLine().howMany(n, s1, t1, l1, s2, t2, l2));
        }
/*      case 8: {
            int n                     = ;
            int[] s1                  = ;
            int[] t1                  = ;
            int[] l1                  = ;
            int[] s2                  = ;
            int[] t2                  = ;
            int[] l2                  = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new YamanoteLine().howMany(n, s1, t1, l1, s2, t2, l2));
        }*/
/*      case 9: {
            int n                     = ;
            int[] s1                  = ;
            int[] t1                  = ;
            int[] l1                  = ;
            int[] s2                  = ;
            int[] t2                  = ;
            int[] l2                  = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new YamanoteLine().howMany(n, s1, t1, l1, s2, t2, l2));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
