import java.math.*;
import java.util.*;

public class WellTimedSearch {
    final static int INF = (int)1e9;

    int need(int depth, int node) {
        if (node == 1) {
            return depth - 1;
        }
        if (depth == 1) {
            return INF;
        }
        int newNode = node + 1 >> 1;
        return Math.min(need(depth - 1, newNode) + newNode, INF);
    }

    int take(int level, int node, int total) {
        if (level == 0 || total <= 0) {
            return 0;
        }
        return take(level - 1, node * 2, total - node) + Math.min(node, total);
    }

    public double getProbability(int n, int a, int b) {
        int answer = 0;
        for (int i = 1; i <= n; ++ i) {
            int ret = need(a, i);
            if (ret + i <= n) {
                answer = Math.max(answer, take(b - a + 1, i, n - ret));
            }
        }
        return (double)answer / n;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            WellTimedSearchHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                WellTimedSearchHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class WellTimedSearchHarness {
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

    static final double MAX_DOUBLE_ERROR = 1E-9;
    static boolean compareOutput(double expected, double result){ if(Double.isNaN(expected)){ return Double.isNaN(result); }else if(Double.isInfinite(expected)){ if(expected > 0){ return result > 0 && Double.isInfinite(result); }else{ return result < 0 && Double.isInfinite(result); } }else if(Double.isNaN(result) || Double.isInfinite(result)){ return false; }else if(Math.abs(result - expected) < MAX_DOUBLE_ERROR){ return true; }else{ double min = Math.min(expected * (1.0 - MAX_DOUBLE_ERROR), expected * (1.0 + MAX_DOUBLE_ERROR)); double max = Math.max(expected * (1.0 - MAX_DOUBLE_ERROR), expected * (1.0 + MAX_DOUBLE_ERROR)); return result > min && result < max; } }
    static double relativeError(double expected, double result) { if (Double.isNaN(expected) || Double.isInfinite(expected) || Double.isNaN(result) || Double.isInfinite(result) || expected == 0) return 0; return Math.abs(result-expected) / Math.abs(expected); }

    static String formatResult(double res) {
        return String.format("%.10g", res);
    }

    static int verifyCase(int casenum, double expected, double received) {
        System.err.print("Example " + casenum + "... ");
        if (compareOutput(expected, received)) {
            System.err.print("PASSED");
            double rerr = relativeError(expected, received);
            if (rerr > 0) System.err.printf(" (relative error %g)", rerr);
            System.err.println();
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
            int N                     = 3;
            int A                     = 2;
            int B                     = 2;
            double expected__         = 0.6666666666666666;

            return verifyCase(casenum__, expected__, new WellTimedSearch().getProbability(N, A, B));
        }
        case 1: {
            int N                     = 3;
            int A                     = 3;
            int B                     = 3;
            double expected__         = 0.3333333333333333;

            return verifyCase(casenum__, expected__, new WellTimedSearch().getProbability(N, A, B));
        }
        case 2: {
            int N                     = 123456;
            int A                     = 1;
            int B                     = 20;
            double expected__         = 1.0;

            return verifyCase(casenum__, expected__, new WellTimedSearch().getProbability(N, A, B));
        }
        case 3: {
            int N                     = 5;
            int A                     = 3;
            int B                     = 4;
            double expected__         = 0.6;

            return verifyCase(casenum__, expected__, new WellTimedSearch().getProbability(N, A, B));
        }

        // custom cases

/*      case 4: {
            int N                     = ;
            int A                     = ;
            int B                     = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new WellTimedSearch().getProbability(N, A, B));
        }*/
/*      case 5: {
            int N                     = ;
            int A                     = ;
            int B                     = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new WellTimedSearch().getProbability(N, A, B));
        }*/
/*      case 6: {
            int N                     = ;
            int A                     = ;
            int B                     = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new WellTimedSearch().getProbability(N, A, B));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
