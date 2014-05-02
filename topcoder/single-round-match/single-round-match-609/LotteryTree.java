import java.math.*;
import java.util.*;

public class LotteryTree {
    public String isFairTree(int[] tree, int m) {
        int n = tree.length + 1;
        int[] parent = new int[n];
        parent[0] = -1;
        for (int i = 1; i < n; ++ i) {
            parent[i] = tree[i - 1];
        }
        return solve(parent, m) ? "YES" : "NO";
    }

    int m;
    long[] weight;
    List <List <Integer> > children;
    Boolean[][] memory;

    boolean go(int u, int delta) {
        if (memory[u][delta] == null) {
            int branch = children.get(u).size();
            if (branch == 0) {
                return delta == 0 && m <= weight[u];
            } else {
                int left = delta * branch;
                List <Integer> deltas = new ArrayList <Integer>();
                for (int i = 0; i < branch; ++ i) {
                    if (left >= m) {
                        deltas.add(0);
                    } else {
                        deltas.add(left);
                        while (left < m) {
                            left += weight[u] * branch;
                        }
                    }
                    left -= m;
                }
                Collections.sort(deltas);
                memory[u][delta] = hasMatch(children.get(u), deltas);
            }
        }
        return memory[u][delta];
    }

    boolean hasMatch(List <Integer> children, List <Integer> deltas) {
        boolean[] visited = new boolean[children.size()];
        int[] match = new int[deltas.size()];
        Arrays.fill(match, -1);
        for (int i = 0; i < children.size(); ++ i) {
            if (find(children, deltas, visited, match, i)) {
                Arrays.fill(visited, false);
            } else {
                return false;
            }
        }
        return true;
    }

    boolean find(List <Integer> children, List <Integer> deltas, boolean[] visited, int[] match, int u) {
        if (!visited[u]) {
            visited[u] = true;
            for (int v = 0; v < deltas.size(); ++ v) {
                if (go(children.get(u), deltas.get(v)) && (match[v] == -1 || find(children, deltas, visited, match, match[v]))) {
                    match[v] = u;
                    return true;
                }
            }
        }
        return false;
    }

    boolean solve(int[] parent, int m) {
        int n = parent.length;
        this.m = m;
        children = new ArrayList <List <Integer>>();
        for (int i = 0; i < n; ++ i) {
            children.add(new ArrayList <Integer>());
        }
        for (int i = 1; i < n; ++ i) {
            children.get(parent[i]).add(i);
        }
        weight = new long[n];
        weight[0] = 1;
        for (int i = 1; i < n; ++ i) {
            weight[i] = weight[parent[i]] * children.get(parent[i]).size();
        }
        memory = new Boolean[n][];
        for (int i = 0; i < n; ++ i) {
            memory[i] = new Boolean[m];
        }
        return go(0, 0);
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            LotteryTreeHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                LotteryTreeHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class LotteryTreeHarness {
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
            int[] tree                = {0, 0, 0};
            int P                     = 3;
            String expected__         = "YES";

            return verifyCase(casenum__, expected__, new LotteryTree().isFairTree(tree, P));
        }
        case 1: {
            int[] tree                = {0, 0, 0, 1, 1, 2, 2, 3, 3};
            int P                     = 2;
            String expected__         = "YES";

            return verifyCase(casenum__, expected__, new LotteryTree().isFairTree(tree, P));
        }
        case 2: {
            int[] tree                = {0, 0, 1, 1, 2, 2, 4, 4, 4};
            int P                     = 3;
            String expected__         = "NO";

            return verifyCase(casenum__, expected__, new LotteryTree().isFairTree(tree, P));
        }
        case 3: {
            int[] tree                = {0, 0, 1, 1, 1, 3, 3, 3};
            int P                     = 3;
            String expected__         = "NO";

            return verifyCase(casenum__, expected__, new LotteryTree().isFairTree(tree, P));
        }
        case 4: {
            int[] tree                = {0, 0, 0, 3, 0, 0, 3, 6, 3, 1, 0, 2, 0, 4, 6, 15, 1, 15, 11, 11, 1, 4, 11, 2, 11, 2, 6} ;
            int P                     = 6;
            String expected__         = "YES";

            return verifyCase(casenum__, expected__, new LotteryTree().isFairTree(tree, P));
        }
        case 5: {
            int[] tree                = {0, 1, 2, 3, 1, 1, 4, 4, 0, 1, 6, 9, 1, 12, 9, 2, 4, 8, 6, 13, 8, 5, 11, 12, 17, 19, 13, 9, 3, 24, 30, 29, 28, 28, 11, 27, 2, 26, 6, 14, 8, 26, 15, 25, 33, 38, 1, 24, 15, 43, 3, 39, 26, 8, 13, 50, 28, 8, 6, 27, 8, 38, 27, 50, 17, 50, 25, 40, 7, 29, 22, 40, 2, 24, 22, 30, 33, 40, 19, 14, 26, 39, 5, 43, 7, 4};
            int P                     = 9;
            String expected__         = "NO";

            return verifyCase(casenum__, expected__, new LotteryTree().isFairTree(tree, P));
        }
        case 6: {
            int[] tree                = {0, 1, 0, 0, 4, 0, 2, 2, 0, 2, 6, 1, 3, 6, 5, 9, 11, 13, 1, 10, 14, 4, 7, 21, 16, 8, 25, 4, 5, 22, 25, 14, 12, 11, 12, 26, 21, 8, 2, 38, 3, 5, 4, 38, 27, 35, 38, 30, 38, 9, 16, 36, 6, 10, 7, 27, 30, 33, 17, 26, 17, 10, 35, 10, 38, 41, 15, 9, 3, 27, 8, 15, 38, 22, 41, 33, 33, 36, 30, 13, 18, 22, 18};
            int P                     = 12;
            String expected__         = "YES";

            return verifyCase(casenum__, expected__, new LotteryTree().isFairTree(tree, P));
        }
        case 7: {
            int[] tree                = {0, 0, 2, 3, 4, 3, 2, 1, 8, 6, 8, 8, 2, 7, 14, 2, 8, 1, 11, 11, 12, 16, 12, 19, 20, 13, 7, 12, 26, 11, 18, 19, 18, 20, 4, 9, 1, 1, 6, 16, 1, 35, 27, 24, 37, 30, 36, 41, 32, 36, 8, 2, 6, 14, 41, 1, 35, 6, 30, 16, 12, 2, 35, 25, 50, 13, 1, 24, 8, 32, 26, 50, 9, 19, 9, 20, 26, 27, 6, 12, 25, 9, 37, 37, 9} ;
            int P                     = 7;
            String expected__         = "NO";

            return verifyCase(casenum__, expected__, new LotteryTree().isFairTree(tree, P));
        }
        case 8: {
            int[] tree                = {0, 0, 1, 0, 2, 3, 0, 0, 8, 5, 7, 5, 2, 12, 12, 14, 14, 13, 8, 2, 1, 7, 18, 16, 8, 24, 18, 2, 24, 3, 11, 5, 24, 4, 34, 6, 31, 13, 38, 19, 4, 3, 22, 3, 11, 12, 21, 34, 41, 8, 19, 4, 13, 29, 33, 8, 14, 50, 18, 45, 16, 50, 44, 50, 38, 5, 43, 31, 29, 7, 6, 45, 38, 4, 19, 41, 50, 21, 44, 41, 43, 22, 33, 6, 8} ;
            int P                     = 12;
            String expected__         = "YES";

            return verifyCase(casenum__, expected__, new LotteryTree().isFairTree(tree, P));
        }

        // custom cases

/*      case 9: {
            int[] tree                = ;
            int P                     = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new LotteryTree().isFairTree(tree, P));
        }*/
/*      case 10: {
            int[] tree                = ;
            int P                     = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new LotteryTree().isFairTree(tree, P));
        }*/
/*      case 11: {
            int[] tree                = ;
            int P                     = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new LotteryTree().isFairTree(tree, P));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
