import java.math.*;
import java.util.*;

public class FoxConnection {
    public int minimalDistance(int[] a, int[] b, String haveFox) {
        int n = haveFox.length();
        ArrayList <ArrayList <Integer>> edges = new ArrayList <ArrayList <Integer>>();
        for (int i = 0; i < n; ++ i) {
            edges.add(new ArrayList <Integer>());
        }
        for (int i = 0; i < n - 1; ++ i) {
            edges.get(a[i] - 1).add(b[i] - 1);
            edges.get(b[i] - 1).add(a[i] - 1);
        }
        int result = Integer.MAX_VALUE;
        for (int root = 0; root < n; ++ root) {
            int[] size = new int[n];
            int[] minimum = go(edges, haveFox, size, -1, root);
            result = Math.min(result, minimum[size[root]]);
        }
        return result;
    }

    int[] go(ArrayList <ArrayList <Integer>> edges, String haveFox, int[] size, int p, int u) {
        size[u] = haveFox.charAt(u) == 'Y' ? 1 : 0;
        int[] subtrees = new int[]{0};
        for (int v : edges.get(u)) {
            if (v != p) {
                int[] minimum = go(edges, haveFox, size, u, v);
                size[u] += size[v];
                int[] newSubtrees = new int[subtrees.length + minimum.length - 1];
                Arrays.fill(newSubtrees, Integer.MAX_VALUE);
                for (int i = 0; i < subtrees.length; ++ i) {
                    for (int j = 0; j < minimum.length; ++ j) {
                        newSubtrees[i + j] = Math.min(newSubtrees[i + j], subtrees[i] + minimum[j] + Math.abs(size[v] - j));
                    }
                }
                subtrees = newSubtrees;
            }
        }
        int[] minimum = new int[subtrees.length + 1];
        minimum[0] = subtrees[0];
        for (int i = 1; i < minimum.length; ++ i) {
            minimum[i] = subtrees[i - 1];
        }
        return minimum;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            FoxConnectionHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                FoxConnectionHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class FoxConnectionHarness {
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
            int[] A                   = {1,2,3};
            int[] B                   = {2,3,4};
            String haveFox            = "YNNY";
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new FoxConnection().minimalDistance(A, B, haveFox));
        }
        case 1: {
            int[] A                   = {1,1,1,1};
            int[] B                   = {2,3,4,5};
            String haveFox            = "NYYYY";
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new FoxConnection().minimalDistance(A, B, haveFox));
        }
        case 2: {
            int[] A                   = {1,3,4,5,4};
            int[] B                   = {2,2,2,4,6};
            String haveFox            = "YNYNYY";
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new FoxConnection().minimalDistance(A, B, haveFox));
        }
        case 3: {
            int[] A                   = {1,2,3,4,5,6,7,8,9};
            int[] B                   = {2,3,4,5,6,7,8,9,10};
            String haveFox            = "YNNNYNYNNY";
            int expected__            = 7;

            return verifyCase(casenum__, expected__, new FoxConnection().minimalDistance(A, B, haveFox));
        }
        case 4: {
            int[] A                   = {1,2,3,4,3,6,8,7};
            int[] B                   = {2,3,4,5,6,8,9,6};
            String haveFox            = "YNNYYNYYY";
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new FoxConnection().minimalDistance(A, B, haveFox));
        }
        case 5: {
            int[] A                   = {1};
            int[] B                   = {2};
            String haveFox            = "NY";
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new FoxConnection().minimalDistance(A, B, haveFox));
        }
        case 6: {
            int[] A                   = {1};
            int[] B                   = {2};
            String haveFox            = "NN";
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new FoxConnection().minimalDistance(A, B, haveFox));
        }

        // custom cases

/*      case 7: {
            int[] A                   = ;
            int[] B                   = ;
            String haveFox            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new FoxConnection().minimalDistance(A, B, haveFox));
        }*/
/*      case 8: {
            int[] A                   = ;
            int[] B                   = ;
            String haveFox            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new FoxConnection().minimalDistance(A, B, haveFox));
        }*/
/*      case 9: {
            int[] A                   = ;
            int[] B                   = ;
            String haveFox            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new FoxConnection().minimalDistance(A, B, haveFox));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
