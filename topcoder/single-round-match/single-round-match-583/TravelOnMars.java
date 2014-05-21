import java.math.*;
import java.util.*;

public class TravelOnMars {
    final static int INF = 1000000000;

    public int minTimes(int[] range, int startCity, int endCity) {
        int n = range.length;
        int[][] distance = new int[n][n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                int d = Math.abs(i - j);
                distance[i][j] = Math.min(d, n - d) <= range[i] ? 1 : INF;
            }
        }
        for (int k = 0; k < n; ++ k) {
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }
        return distance[startCity][endCity];
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            TravelOnMarsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                TravelOnMarsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class TravelOnMarsHarness {
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
            int[] range               = {2,1,1,1,1,1};
            int startCity             = 1;
            int endCity               = 4;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new TravelOnMars().minTimes(range, startCity, endCity));
        }
        case 1: {
            int[] range               = {2,1,1,1,1,1};
            int startCity             = 4;
            int endCity               = 1;
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new TravelOnMars().minTimes(range, startCity, endCity));
        }
        case 2: {
            int[] range               = {2,1,1,2,1,2,1,1};
            int startCity             = 2;
            int endCity               = 6;
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new TravelOnMars().minTimes(range, startCity, endCity));
        }
        case 3: {
            int[] range               = {3,2,1,1,3,1,2,2,1,1,2,2,2,2,3};
            int startCity             = 6;
            int endCity               = 13;
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new TravelOnMars().minTimes(range, startCity, endCity));
        }

        // custom cases

/*      case 4: {
            int[] range               = ;
            int startCity             = ;
            int endCity               = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TravelOnMars().minTimes(range, startCity, endCity));
        }*/
/*      case 5: {
            int[] range               = ;
            int startCity             = ;
            int endCity               = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TravelOnMars().minTimes(range, startCity, endCity));
        }*/
/*      case 6: {
            int[] range               = ;
            int startCity             = ;
            int endCity               = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TravelOnMars().minTimes(range, startCity, endCity));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
