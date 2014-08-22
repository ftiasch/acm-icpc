import java.math.*;
import java.util.*;

public class Egalitarianism3 {
    Map <Integer, Integer> search(int[][] tree, Map <Integer, Integer> distances, Set <Integer> used, int p, int u, int d) {
        if (!used.contains(d)) {
            used.add(d);
            if (distances.containsKey(d)) {
                distances.put(d, distances.get(d) + 1);
            } else {
                distances.put(d, 1);
            }
        }
        int n = tree.length;
        for (int v = 0; v < n; ++ v) {
            if (tree[u][v] > 0 && v != p) {
                search(tree, distances, used, u, v, d + tree[u][v]);
            }
        }
        return distances;
    }

    public int maxCities(int n, int[] a, int[] b, int[] c) {
        if (n == 1) {
            return 1;
        }
        int result = 2;
        int[][] tree = new int[n][n];
        for (int i = 0; i < n - 1; ++ i) {
            a[i] --;
            b[i] --;
            tree[a[i]][b[i]] = c[i];
            tree[b[i]][a[i]] = c[i];
        }
        for (int root = 0; root < n; ++ root) {
            Map <Integer, Integer> distances = new HashMap <Integer, Integer>();
            for (int v = 0; v < n; ++ v) {
                if (tree[root][v] > 0) {
                    distances = search(tree, distances, new HashSet <Integer>(), root, v, tree[root][v]);
                }
            }
            for (Map.Entry <Integer, Integer> iterator : distances.entrySet()) {
                result = Math.max(result, iterator.getValue());
            }
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            Egalitarianism3Harness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                Egalitarianism3Harness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class Egalitarianism3Harness {
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
            int n                     = 4;
            int[] a                   = {1,1,1};
            int[] b                   = {2,3,4};
            int[] len                 = {1,1,1};
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new Egalitarianism3().maxCities(n, a, b, len));
        }
        case 1: {
            int n                     = 6;
            int[] a                   = {1,2,3,2,3};
            int[] b                   = {2,3,4,5,6};
            int[] len                 = {2,1,3,2,3};
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new Egalitarianism3().maxCities(n, a, b, len));
        }
        case 2: {
            int n                     = 10;
            int[] a                   = {1,1,1,1,1,1,1,1,1};
            int[] b                   = {2,3,4,5,6,7,8,9,10};
            int[] len                 = {1000,1000,1000,1000,1000,1000,1000,1000,1000};
            int expected__            = 9;

            return verifyCase(casenum__, expected__, new Egalitarianism3().maxCities(n, a, b, len));
        }
        case 3: {
            int n                     = 1;
            int[] a                   = {};
            int[] b                   = {};
            int[] len                 = {};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new Egalitarianism3().maxCities(n, a, b, len));
        }

        // custom cases

/*      case 4: {
            int n                     = ;
            int[] a                   = ;
            int[] b                   = ;
            int[] len                 = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new Egalitarianism3().maxCities(n, a, b, len));
        }*/
/*      case 5: {
            int n                     = ;
            int[] a                   = ;
            int[] b                   = ;
            int[] len                 = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new Egalitarianism3().maxCities(n, a, b, len));
        }*/
/*      case 6: {
            int n                     = ;
            int[] a                   = ;
            int[] b                   = ;
            int[] len                 = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new Egalitarianism3().maxCities(n, a, b, len));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
