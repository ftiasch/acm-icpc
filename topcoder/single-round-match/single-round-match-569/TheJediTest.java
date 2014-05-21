import java.math.*;
import java.util.*;

public class TheJediTest {
    final static long INF = (long)1e18;

    public int minimumSupervisors(int[] students, int m) {
        int n = students.length;
        long[][] graph = new long[n + 1][n + 1];
        for (int i = 0; i <= n; ++ i) {
            for (int j = 0; j <= n; ++ j) {
                graph[i][j] = INF;
            }
        }
        for (int i = 0; i < n; ++ i) {
            graph[i][i + 1] = 0;
        }
        for (int i = 0; i < n; ++ i) {
            long sum = 0;
            for (int j = i; j < n; ++ j) {
                sum += students[j];
                int l = Math.max(i - 1, 0);
                int r = Math.min(j + 1, n - 1) + 1;
                graph[l][r] = Math.min(graph[l][r], (sum + m - 1) / m * -1);
            }
        }
        for (int k = 0; k <= n; ++ k) {
            for (int i = 0; i <= n; ++ i) {
                for (int j = 0; j <= n; ++ j) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
        return -(int)graph[0][n];
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            TheJediTestHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                TheJediTestHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class TheJediTestHarness {
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
            int[] students            = {3, 6, 3};
            int K                     = 4;
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new TheJediTest().minimumSupervisors(students, K));
        }
        case 1: {
            int[] students            = {1, 1, 1, 1};
            int K                     = 4;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new TheJediTest().minimumSupervisors(students, K));
        }
        case 2: {
            int[] students            = {0, 0, 0, 0};
            int K                     = 12345;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new TheJediTest().minimumSupervisors(students, K));
        }
        case 3: {
            int[] students            = {15, 0, 13, 4, 29, 6, 2};
            int K                     = 7;
            int expected__            = 10;

            return verifyCase(casenum__, expected__, new TheJediTest().minimumSupervisors(students, K));
        }
        case 4: {
            int[] students            = {1284912, 1009228, 9289247, 2157, 2518, 52781, 2, 2818, 68};
            int K                     = 114;
            int expected__            = 102138;

            return verifyCase(casenum__, expected__, new TheJediTest().minimumSupervisors(students, K));
        }

        // custom cases

/*      case 5: {
            int[] students            = ;
            int K                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TheJediTest().minimumSupervisors(students, K));
        }*/
/*      case 6: {
            int[] students            = ;
            int K                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TheJediTest().minimumSupervisors(students, K));
        }*/
/*      case 7: {
            int[] students            = ;
            int K                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TheJediTest().minimumSupervisors(students, K));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
