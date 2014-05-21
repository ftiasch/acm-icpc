import java.math.*;
import java.util.*;

public class MaxMinTreeGame {
    public int findend(int[] edges, int[] costs) {
        int n = costs.length;
        int[] degree = new int[n];
        for (int i = 0; i < n - 1; ++ i) {
            degree[i + 1] ++;
            degree[edges[i]] ++;
        }
        int result = 0;
        for (int i = 0; i < n; ++ i) {
            if (degree[i] == 1) {
                result = Math.max(result, costs[i]);
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
            MaxMinTreeGameHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                MaxMinTreeGameHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class MaxMinTreeGameHarness {
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
            int[] edges               = {0};
            int[] costs               = {4,6};
            int expected__            = 6;

            return verifyCase(casenum__, expected__, new MaxMinTreeGame().findend(edges, costs));
        }
        case 1: {
            int[] edges               = {0,1};
            int[] costs               = {4,6,5};
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new MaxMinTreeGame().findend(edges, costs));
        }
        case 2: {
            int[] edges               = {0,1,2,3};
            int[] costs               = {0,1,0,1,0};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new MaxMinTreeGame().findend(edges, costs));
        }
        case 3: {
            int[] edges               = {0,0,0};
            int[] costs               = {5,1,2,3};
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new MaxMinTreeGame().findend(edges, costs));
        }
        case 4: {
            int[] edges               = {0,0};
            int[] costs               = {3,2,5};
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new MaxMinTreeGame().findend(edges, costs));
        }

        // custom cases

/*      case 5: {
            int[] edges               = ;
            int[] costs               = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new MaxMinTreeGame().findend(edges, costs));
        }*/
/*      case 6: {
            int[] edges               = ;
            int[] costs               = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new MaxMinTreeGame().findend(edges, costs));
        }*/
/*      case 7: {
            int[] edges               = ;
            int[] costs               = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new MaxMinTreeGame().findend(edges, costs));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
