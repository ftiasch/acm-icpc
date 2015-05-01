import java.math.*;
import java.util.*;

public class TreePuzzle {
    List <List <Integer>> tree;

    List <Integer> search(int[] token, List <Integer> nodes, int p, int u) {
        if (token[u] == 0) {
            nodes.add(u);
        }
        for (int v : tree.get(u)) {
            if (v != p) {
                search(token, nodes, u, v);
            }
        }
        return nodes;
    }

    boolean find(int p, int u, int quotas) {
        if (tree.get(u).size() >= 3 && quotas >= 3) {
            return true;
        }
        for (int v : tree.get(u)) {
            if (v != p && find(u, v, quotas - 1)) {
                return true;
            }
        }
        return false;
    }

    int[] check(int[] token, int u, int v) {
        List <Integer> vChildren = search(token, new ArrayList <Integer>(), u, v);
        if (!vChildren.isEmpty()) {
            token = token.clone();
            token[u] = 0;
            token[vChildren.get(0)] = 1;
            return token;
        }
        int p = -1;
        int count = 0;
        for (int w : tree.get(u)) {
            if (w != v) {
                List <Integer> children = search(token, new ArrayList <Integer>(), u, w);
                if (!children.isEmpty()) {
                    p = w;
                    count ++;
                }
            }
        }
        boolean valid = count >= 2;
        valid |= p != -1 && find(u, p, search(token, new ArrayList <Integer>(), u, p).size());
        if (valid) {
            token = token.clone();
            token[u] = 0;
            token[search(token, new ArrayList <Integer>(), u, p).get(0)] = 1;
            return token;
        }
        return null;
    }

    int[] solve(int[] result, int[] token, int u) {
        result[u] = 1;
        for (int v : tree.get(u)) {
            if (result[v] == 0) {
                int[] newToken = check(token, u, v);
                if (newToken != null) {
                    solve(result, newToken, v);
                }
            }
        }
        return result;
    }

    public int[] reachable(int[] parent, int[] token) {
        int n = parent.length;
        tree = new ArrayList <List <Integer>>();
        for (int i = 0; i < n; ++ i) {
            tree.add(new ArrayList <Integer>());
        }
        for (int u = 1; u < n; ++ u) {
            int p = parent[u];
            tree.get(p).add(u);
            tree.get(u).add(p);
        }
        int[] result = solve(new int[n], token, 0);
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            TreePuzzleHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                TreePuzzleHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class TreePuzzleHarness {
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

    static boolean compareOutput(int[] expected, int[] result) { if (expected.length != result.length) return false; for (int i=0; i<expected.length; ++i) if (expected[i] != result[i]) return false; return true; }

    static String formatResult(int[] res) {
        String ret = "";
        ret += "{";
        for (int i=0; i<res.length; ++i) {
            if (i > 0) ret += ",";
            ret += String.format(" %d", res[i]);
        }
        ret += " }";
        return ret;
    }

    static int verifyCase(int casenum, int[] expected, int[] received) {
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
            int[] parent              = {-1, 0, 0, 0, 1};
            int[] token               = {1, 1, 0, 0, 1};
            int[] expected__          = {1, 1, 1, 1, 0 };

            return verifyCase(casenum__, expected__, new TreePuzzle().reachable(parent, token));
        }
        case 1: {
            int[] parent              = {-1, 0, 1, 0, 3, 2, 5, 6, 7, 8};
            int[] token               = {1, 1, 1, 1, 1, 1, 0, 0, 1, 0};
            int[] expected__          = {1, 1, 1, 0, 0, 1, 0, 0, 0, 0 };

            return verifyCase(casenum__, expected__, new TreePuzzle().reachable(parent, token));
        }
        case 2: {
            int[] parent              = {-1, 0, 0, 2, 1, 1, 5, 0, 0, 2};
            int[] token               = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
            int[] expected__          = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

            return verifyCase(casenum__, expected__, new TreePuzzle().reachable(parent, token));
        }
        case 3: {
            int[] parent              = {-1, 0, 1, 2, 3, 4, 5, 0, 7, 6, 8, 9, 11, 0, 11, 2, 15, 13, 15, 10};
            int[] token               = {1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1};
            int[] expected__          = {1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 };

            return verifyCase(casenum__, expected__, new TreePuzzle().reachable(parent, token));
        }
        case 4: {
            int[] parent              = {-1, 0, 1, 2, 3, 4, 0, 5, 7, 8, 6, 10, 11, 9, 13, 14, 12, 16, 16, 18, 15, 10, 17, 2, 23, 22, 21, 20, 27, 24, 19, 30, 28, 29, 17, 21, 34, 35, 31, 30, 36, 32, 31, 39, 26, 40, 3, 38, 45, 25, 49, 46, 41, 44, 51, 47, 18, 54, 48, 36, 43, 57, 52, 56, 60, 59, 53, 61, 64, 68, 37, 55, 71, 32, 26, 73, 28, 50, 75, 72, 70, 67, 74, 66, 79, 76, 78, 63, 69, 41, 83, 86, 80, 18, 82, 87, 58, 62, 42, 20};
            int[] token               = {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1};
            int[] expected__          = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0 };

            return verifyCase(casenum__, expected__, new TreePuzzle().reachable(parent, token));
        }

        // custom cases

/*      case 5: {
            int[] parent              = ;
            int[] token               = ;
            int[] expected__          = ;

            return verifyCase(casenum__, expected__, new TreePuzzle().reachable(parent, token));
        }*/
/*      case 6: {
            int[] parent              = ;
            int[] token               = ;
            int[] expected__          = ;

            return verifyCase(casenum__, expected__, new TreePuzzle().reachable(parent, token));
        }*/
/*      case 7: {
            int[] parent              = ;
            int[] token               = ;
            int[] expected__          = ;

            return verifyCase(casenum__, expected__, new TreePuzzle().reachable(parent, token));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
