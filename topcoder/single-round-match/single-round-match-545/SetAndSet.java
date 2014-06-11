import java.math.*;
import java.util.*;

public class SetAndSet {
    public long countandset(int[] a) {
        int n = a.length;
        int m = 0;
        boolean[][] visited = new boolean[20][2];
        for (int k = 0; k < 20; ++ k) {
            for (int i = 0; i < n; ++ i) {
                visited[k][a[i] >> k & 1] = true;
            }
            if (visited[k][0] && visited[k][1]) {
                m ++;
            }
        }
        int[][] bits = new int[m][n];
        m = 0;
        for (int k = 0; k < 20; ++ k) {
            if (visited[k][0] && visited[k][1]) {
                for (int i = 0; i < n; ++ i) {
                    bits[m][i] = a[i] >> k & 1;
                }
                m ++;
            }
        }
//debug(bits);
        int[] parent = new int[n];
        for (int i = 0; i < n; ++ i) {
            parent[i] = i;
        }
        return search(bits, parent, 0);
    }

    long search(int[][] bits, int[] parent, int i) {
        int m = bits.length;
        int n = parent.length;
        if (i < m) {
            long result = search(bits, parent, i + 1);
            int[] newParent = parent.clone();
            int j0 = 0;
            while (bits[i][j0] == 1) {
                j0 ++;
                assert j0 < n;
            }
            for (int j = 0; j < n; ++ j) {
                if (bits[i][j] == 0) {
                    merge(newParent, j0, j);
                }
            }
            result -= search(bits, newParent, i + 1);
            return result;
        } else {
            int component = 0;
            for (int j = 0; j < n; ++ j) {
                if (find(parent, j) == j) {
                    component ++;
                }
            }
            return (1L << component) - 2;
        }
    }

    void merge(int[] parent, int i, int j) {
        if (find(parent, i) != find(parent, j)) {
            parent[find(parent, i)] = find(parent, j);
        }
    }

    int find(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = find(parent, parent[i]);
        }
        return parent[i];
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            SetAndSetHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                SetAndSetHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class SetAndSetHarness {
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
            int[] A                   = {1,2};
            long expected__           = 0;

            return verifyCase(casenum__, expected__, new SetAndSet().countandset(A));
        }
        case 1: {
            int[] A                   = {1,2,3,4};
            long expected__           = 2;

            return verifyCase(casenum__, expected__, new SetAndSet().countandset(A));
        }
        case 2: {
            int[] A                   = {1,2,3,4,5};
            long expected__           = 8;

            return verifyCase(casenum__, expected__, new SetAndSet().countandset(A));
        }
        case 3: {
            int[] A                   = {6,6,6};
            long expected__           = 6;

            return verifyCase(casenum__, expected__, new SetAndSet().countandset(A));
        }
        case 4: {
            int[] A                   = {13,10,4,15,4,8,4,2,4,14,0};
            long expected__           = 1728;

            return verifyCase(casenum__, expected__, new SetAndSet().countandset(A));
        }

        // custom cases

/*      case 5: {
            int[] A                   = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new SetAndSet().countandset(A));
        }*/
/*      case 6: {
            int[] A                   = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new SetAndSet().countandset(A));
        }*/
/*      case 7: {
            int[] A                   = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new SetAndSet().countandset(A));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
