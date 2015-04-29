import java.math.*;
import java.util.*;

public class RedPaint {
    public double expectedCells(int n) {
        double[][] prob = new double[1][1];
        prob[0][0] = 1.;
        for (int k = 1; k <= n; ++ k) {
            double[][] newProb = new double[k + 1][k + 1];
            for (int i = 0; i < k; ++ i) {
                for (int j = 0; j < k; ++ j) {
                    double p = prob[i][j];
                    if (p > 0) {
                        newProb[Math.max(i - 1, 0)][j + 1] += p / 2;
                        newProb[i + 1][Math.max(j - 1, 0)] += p / 2;
                    }
                }
            }
            prob = newProb;
        }
        double result = 0.;
        for (int i = 0; i <= n; ++ i) {
            for (int j = 0; j <= n; ++ j) {
                result += prob[i][j] * (i + j + 1);
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
            RedPaintHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                RedPaintHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class RedPaintHarness {
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
            int N                     = 0;
            double expected__         = 1.0;

            return verifyCase(casenum__, expected__, new RedPaint().expectedCells(N));
        }
        case 1: {
            int N                     = 1;
            double expected__         = 2.0;

            return verifyCase(casenum__, expected__, new RedPaint().expectedCells(N));
        }
        case 2: {
            int N                     = 2;
            double expected__         = 2.5;

            return verifyCase(casenum__, expected__, new RedPaint().expectedCells(N));
        }
        case 3: {
            int N                     = 4;
            double expected__         = 3.375;

            return verifyCase(casenum__, expected__, new RedPaint().expectedCells(N));
        }

        // custom cases

/*      case 4: {
            int N                     = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new RedPaint().expectedCells(N));
        }*/
/*      case 5: {
            int N                     = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new RedPaint().expectedCells(N));
        }*/
/*      case 6: {
            int N                     = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new RedPaint().expectedCells(N));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
