import java.math.*;
import java.util.*;

public class InducedSubgraphs {
    static int MOD = (int)1e9 + 9;

    int n;
    ArrayList <ArrayList <Integer> > tree;

    int[][] binom;

    class Data {
        int size, ways;

        Data(int size, int ways) {
            this.size = size;
            this.ways = ways;
        }

        Data add(Data other) {
            return new Data(size + other.size, (int)((long)ways * other.ways % MOD * binom[size + other.size][size] % MOD));
        }

        public String toString() {
            return String.format("<@size=%d, @ways=%d>", size, ways);
        }
    }

    Data count(int p, int u) {
        Data result = new Data(0, 1);
        for (Object object : tree.get(u)) {
            int v = (Integer)object;
            if (v != p) {
                result = result.add(count(u, v));
            }
        }
        result.size ++;
        return result;
    }

    class Pair implements Comparable <Pair> {
        int distance, id;

        Pair(int distance, int id) {
            this.distance = distance;
            this.id = id;
        }

        public int compareTo(Pair other) {
            return distance - other.distance;
        }
    }

    int[] size, parent;
    int[][][] ways;

    void prepare(int p, int u) {
        parent[u] = p;
        ways[u][0][0] = 1;
        for (Object object : tree.get(u)) {
            int v = (Integer)object;
            if (v != p) {
                prepare(u, v);
                size[u] += size[v];
                int subTree = count(u, v).ways;
                for (int i = size[u]; i >= 0; -- i) {
                    for (int j = size[u] - i; j >= 0; -- j) {
                        int result = 0;
                        if (size[v] <= i) {
                            result += (int)((long)ways[u][i - size[v]][j] * binom[i][size[v]] % MOD * subTree % MOD);
                            if (result >= MOD) {
                                result -= MOD;
                            }
                        }
                        if (size[v] <= j) {
                            result += (int)((long)ways[u][i][j - size[v]] * binom[j][size[v]] % MOD * subTree % MOD);
                            if (result >= MOD) {
                                result -= MOD;
                            }
                        }
                        for (int x = 0; x <= size[v] && x <= i; ++ x) {
                            for (int y = 0; x + y <= size[v] && y <= j; ++ y) {
                                result += (int)((long)ways[u][i - x][j - y] * binom[i][x] % MOD * binom[j][y] % MOD * ways[v][x][y] % MOD);
                                if (result >= MOD) {
                                    result -= MOD;
                                }
                            }
                        }
                        ways[u][i][j] = result;
                    }
                }
            }
        }
        size[u] ++;
    }

    public int getCount(int[] edge1, int[] edge2, int m) {
        n = edge1.length + 1;
        binom = new int[n + 1][n + 1];
        for (int i = 0; i <= n; ++ i) {
            binom[i][0] = 1;
            for (int j = 1; j <= i; ++ j) {
                binom[i][j] = (binom[i - 1][j - 1] + binom[i - 1][j]) % MOD;
            }
        }
        tree = new ArrayList <ArrayList <Integer> >();
        for (int i = 0; i < n; ++ i) {
            tree.add(new ArrayList <Integer>());
        }
        int[][] distance = new int[n][n];
        for (int i = 0; i < n; ++ i) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
            distance[i][i] = 0;
        }
        for (int i = 0; i < n - 1; ++ i) {
            int a = edge1[i];
            int b = edge2[i];
            tree.get(a).add(b);
            tree.get(b).add(a);
            distance[a][b] = distance[b][a] = 1;
        }
        for (int k = 0; k < n; ++ k) {
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    if (distance[i][k] < Integer.MAX_VALUE && distance[k][j] < Integer.MAX_VALUE) {
                        distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                    }
                }
            }
        }
        if (m == 1 || m == n) {
            int result = 1;
            for (int i = 1; i <= n; ++ i) {
                result = (int)((long)result * i % MOD);
            }
            return result;
        }
        if (m * 2 <= n) {
            int result = 0;
            for (int u = 0; u < n; ++ u) {
                for (int v = 0; v < n; ++ v) {
                    if (distance[u][v] == n - 2 * m + 1) {
                        ArrayList <Pair> path = new ArrayList <Pair>();
                        for (int k = 0; k < n; ++ k) {
                            if (distance[u][k] + distance[k][v] == distance[u][v]) {
                                path.add(new Pair(distance[u][k], k));
                            }
                        }
                        Collections.sort(path);
                        boolean valid = true;
                        for (int i = 1; i < path.size() - 1; ++ i) {
                            valid &= tree.get(path.get(i).id).size() == 2;
                        }
                        if (valid) {
                            Data uData = count(path.get(1).id, u);
                            Data vData = count(path.get(path.size() - 2).id, v);
                            if (uData.size == m && vData.size == m) {
                                result += (int)((long)uData.ways * vData.ways % MOD);
                                if (result >= MOD) {
                                    result -= MOD;
                                }
                            }
                        }
                    }
                }
            }
            return result;
        } else {
            size = new int[n];
            parent = new int[n];
            ways = new int[n][n + 1][n + 1];
            prepare(-1, 0);
            int result = ways[0][n - m][n - m];
            for (int top = 1; top < n; ++ top) {
                if (n - size[top] <= n - m) {
                    Data subTree = count(top, parent[top]);
                    result += (int)((long)ways[top][n - m - subTree.size][n - m] * binom[n - m][subTree.size] % MOD * subTree.ways % MOD);
                    if (result >= MOD) {
                        result -= MOD;
                    }
                    result += (int)((long)ways[top][n - m][n - m - subTree.size] * binom[n - m][subTree.size] % MOD * subTree.ways % MOD);
                    if (result >= MOD) {
                        result -= MOD;
                    }
                }
            }
            for (int i = 1; i <= m * 2 - n; ++ i) {
                result = (int)((long)result * i % MOD);
            }
            return result;
        }
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            InducedSubgraphsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                InducedSubgraphsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class InducedSubgraphsHarness {
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
            int[] edge1               = {0, 1};
            int[] edge2               = {1, 2};
            int k                     = 2;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new InducedSubgraphs().getCount(edge1, edge2, k));
        }
        case 1: {
            int[] edge1               = {0, 1, 3};
            int[] edge2               = {2, 2, 2};
            int k                     = 3;
            int expected__            = 12;

            return verifyCase(casenum__, expected__, new InducedSubgraphs().getCount(edge1, edge2, k));
        }
        case 2: {
            int[] edge1               = {5, 0, 1, 2, 2};
            int[] edge2               = {0, 1, 2, 4, 3};
            int k                     = 3;
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new InducedSubgraphs().getCount(edge1, edge2, k));
        }
        case 3: {
            int[] edge1               = {0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6};
            int[] edge2               = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
            int k                     = 11;
            int expected__            = 481904640;

            return verifyCase(casenum__, expected__, new InducedSubgraphs().getCount(edge1, edge2, k));
        }
        case 4: {
            int[] edge1               = {5, 9, 4, 10, 10, 0, 7, 6, 2, 1, 11, 8} ;
            int[] edge2               = {0, 0, 10, 3, 0, 6, 1, 1, 12, 12, 7, 11} ;
            int k                     = 6;
            int expected__            = 800;

            return verifyCase(casenum__, expected__, new InducedSubgraphs().getCount(edge1, edge2, k));
        }
        case 5: {
            int[] edge1               = {0, 5, 1, 0, 2, 3, 5} ;
            int[] edge2               = {4, 7, 0, 6, 7, 5, 0} ;
            int k                     = 3;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new InducedSubgraphs().getCount(edge1, edge2, k));
        }
        case 6: {
            int[] edge1               = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
            int[] edge2               = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
            int k                     = 1;
            int expected__            = 890964601;

            return verifyCase(casenum__, expected__, new InducedSubgraphs().getCount(edge1, edge2, k));
        }

        // custom cases

        case 7: {
            int[] edge1               = new int[40];
            int[] edge2               = new int[40];
            for (int i = 0; i < 40; ++ i) {
                edge1[i] = i;
                edge2[i] = 40;
            }
            int k                     = 31;
            int expected__            = 79888617;

            return verifyCase(casenum__, expected__, new InducedSubgraphs().getCount(edge1, edge2, k));
        }
/*      case 8: {
            int[] edge1               = ;
            int[] edge2               = ;
            int k                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new InducedSubgraphs().getCount(edge1, edge2, k));
        }*/
/*      case 9: {
            int[] edge1               = ;
            int[] edge2               = ;
            int k                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new InducedSubgraphs().getCount(edge1, edge2, k));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
