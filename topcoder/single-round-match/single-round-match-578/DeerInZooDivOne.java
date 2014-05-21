import java.math.*;
import java.util.*;

class DisjointSet {
    int[] parent;

    DisjointSet(int n) {
        parent = new int[n];
        for (int i = 0; i < n; ++ i) {
            parent[i] = i;
        }
    }

    int find(int u) {
        if (parent[u] != u) {
            parent[u] = find(parent[u]);
        }
        return parent[u];
    }

    boolean merge(int u, int v) {
        if (find(u) == find(v)) {
            return false;
        }
        parent[find(u)] = find(v);
        return true;
    }
}

class Edge {
    static int count;
    static ArrayList <Edge> edges;

    int p, u, id;

    Edge(int u, int v) {
        this.p = u;
        this.u = v;
        this.id = count ++;
        edges.add(this);
    }

    public String toString() {
        return String.format("<%d, %d>", p, u);
    }
}

@SuppressWarnings("unchecked")
class MatchingSolver {
    int n;
    int[][] graph;

    MatchingSolver(int n) {
        this.n = n;
        graph = new int[n][n];
    }

    void add(int u, int v, int w) {
        graph[u][v] = w;
    }

    int[] match, weightX, weightY, slack;
    boolean[] visitX, visitY;

    boolean find(int i) {
        if (visitX[i]) {
            return false;
        }
        visitX[i] = true;
        for (int j = 0; j < n; ++ j) {
            if (weightX[i] + weightY[j] == graph[i][j]) {
                visitY[j] = true;
                if (match[j] == -1 || find(match[j])) {
                    match[j] = i;
                    return true;
                }
            } else {
                slack[j] = Math.min(slack[j], weightX[i] + weightY[j] - graph[i][j]);
            }
        }
        return false;
    }

    int solve() {
        match = new int[n];
        Arrays.fill(match, -1);
        weightX = new int[n];
        weightY = new int[n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                weightX[i] = Math.max(weightX[i], graph[i][j]);
            }
        }
        visitX = new boolean[n];
        visitY = new boolean[n];
        slack = new int[n];
        for (int j = 0; j < n; ++ j) {
            while (true) {
                Arrays.fill(visitX, false);
                Arrays.fill(visitY, false);
                Arrays.fill(slack, Integer.MAX_VALUE);
                if (find(j)) {
                    break;
                }
                int delta = Integer.MAX_VALUE;
                for (int i = 0; i < n; ++ i) {
                    if (!visitY[i]) {
                        delta = Math.min(delta, slack[i]);
                    }
                }
                for (int i = 0; i < n; ++ i) {
                    if (visitX[i]) {
                        weightX[i] -= delta;
                    }
                }
                for (int i = 0; i < n; ++ i) {
                    if (visitY[i]) {
                        weightY[i] += delta;
                    }
                }
            }
        }
        int ret = 0;
        for (int i = 0; i < n; ++ i) {
            ret += weightX[i] + weightY[i];
        }
        return ret;
    }
}

@SuppressWarnings("unchecked")
public class DeerInZooDivOne {
    int n;
    ArrayList[] tree;

    int[][] memory;

    ArrayList <Edge> getChildren(Edge e) {
        ArrayList <Edge> ret = new ArrayList <Edge>();
        for (Object obj : tree[e.u]) {
            Edge f = (Edge)obj;
            if (f.u != e.p) {
                ret.add(f);
            }
        }
        return ret;
    }

    int isomorphim(Edge e, Edge f) {
        if (e.id > f.id) {
            return isomorphim(f, e);
        }
        if (memory[e.id][f.id] == -1) {
            ArrayList <Edge> childrenE = getChildren(e);
            ArrayList <Edge> childrenF = getChildren(f);
            int m = Math.max(childrenE.size(), childrenF.size());
            for (int i = 0; i < childrenE.size(); ++ i) {
                for (int j = 0; j < childrenF.size(); ++ j) {
                    isomorphim(childrenE.get(i), childrenF.get(j));
                }
            }
            MatchingSolver solver = new MatchingSolver(m);
            for (int i = 0; i < childrenE.size(); ++ i) {
                for (int j = 0; j < childrenF.size(); ++ j) {
                    solver.add(i, j, isomorphim(childrenE.get(i), childrenF.get(j)));
                }
            }
            memory[e.id][f.id] = solver.solve() + 1;
        }
        return memory[e.id][f.id];
    }

    public int getmax(int[] a, int[] b) {
        n = a.length + 1;
        int answer = 0;
        for (int k = 0; k < n - 1; ++ k) {
            tree = new ArrayList[n + 1];
            for (int i = 0; i <= n; ++ i) {
                tree[i] = new ArrayList <Edge>();
            }
            Edge.count = 0;
            Edge.edges = new ArrayList <Edge>();
            DisjointSet set = new DisjointSet(n);
            for (int i = 0; i < n; ++ i) {
                tree[n].add(new Edge(n, i));
            }
            for (int i = 0; i < n - 1; ++ i) {
                if (i != k) {
                    set.merge(a[i], b[i]);
                    tree[a[i]].add(new Edge(a[i], b[i]));
                    tree[b[i]].add(new Edge(b[i], a[i]));
                }
            }
            memory = new int[Edge.count][Edge.count];
            for (int[] row : memory) {
                Arrays.fill(row, -1);
            }
            for (Object obj1 : tree[n]) {
                for (Object obj2 : tree[n]) {
                    Edge e = (Edge)obj1;
                    Edge f = (Edge)obj2;
                    if (set.find(e.u) != set.find(f.u)) {
                        answer = Math.max(answer, isomorphim(e, f));
                    }
                }
            }
        }
        return answer;
    }

    static void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            DeerInZooDivOneHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                DeerInZooDivOneHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class DeerInZooDivOneHarness {
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
            int[] a                   = {0, 1, 2};
            int[] b                   = {1, 2, 3};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new DeerInZooDivOne().getmax(a, b));
        }
        case 1: {
            int[] a                   = {1, 8, 1, 7, 4, 2, 5, 2};
            int[] b                   = {5, 3, 6, 8, 2, 6, 8, 0};
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new DeerInZooDivOne().getmax(a, b));
        }
        case 2: {
            int[] a                   = {0};
            int[] b                   = {1};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new DeerInZooDivOne().getmax(a, b));
        }
        case 3: {
            int[] a                   = {0, 11, 10, 10, 19, 17, 6, 17, 19, 10, 10, 11, 9, 9, 14, 2, 13, 11, 6};
            int[] b                   = {7, 5, 2, 12, 8, 9, 16, 8, 4, 18, 8, 13, 15, 13, 17, 16, 3, 1, 7};
            int expected__            = 8;

            return verifyCase(casenum__, expected__, new DeerInZooDivOne().getmax(a, b));
        }
        case 4: {
            int[] a                   = {14, 13, 28, 15, 20, 4, 9, 6, 1, 23, 19, 25, 25, 8, 14, 16, 2, 8, 15, 25, 22, 22, 28, 10, 10, 14, 24, 27, 8};
            int[] b                   = {21, 5, 12, 13, 27, 1, 24, 17, 27, 17, 23, 14, 18, 26, 7, 26, 11, 0, 25, 23, 3, 29, 22, 11, 22, 29, 15, 28, 29};
            int expected__            = 11;

            return verifyCase(casenum__, expected__, new DeerInZooDivOne().getmax(a, b));
        }

        // custom cases

/*      case 5: {
            int[] a                   = ;
            int[] b                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new DeerInZooDivOne().getmax(a, b));
        }*/
/*      case 6: {
            int[] a                   = ;
            int[] b                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new DeerInZooDivOne().getmax(a, b));
        }*/
/*      case 7: {
            int[] a                   = ;
            int[] b                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new DeerInZooDivOne().getmax(a, b));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
