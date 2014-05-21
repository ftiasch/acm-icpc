import java.util.*;
import java.math.*;

public class TheFrog {
    public double getMinimum(int s, int[] l, int[] r) {
        int n = l.length;
        double answer = 1e100;
        for (int i = 0; i < n; ++ i) {
            for (int x = 1; x <= r[i]; ++ x) {
                double d = (double)r[i] / x;
                boolean valid = true;
                for (int j = 0; j < n && valid; ++ j) {
                    int t = (int)Math.floor(l[j] / d + 1e-10);
                    valid &= (t + 1) * d >= r[j] - 1e-11;
                }
                if (valid) {
                    answer = Math.min(answer, d);
                }
            }
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            TheFrogHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                TheFrogHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class TheFrogHarness {
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
            int D                     = 16;
            int[] L                   = {2};
            int[] R                   = {7};
            double expected__         = 7.0;

            return verifyCase(casenum__, expected__, new TheFrog().getMinimum(D, L, R));
        }
        case 1: {
            int D                     = 25;
            int[] L                   = {11, 3};
            int[] R                   = {21, 7};
            double expected__         = 10.5;

            return verifyCase(casenum__, expected__, new TheFrog().getMinimum(D, L, R));
        }
        case 2: {
            int D                     = 100;
            int[] L                   = {0};
            int[] R                   = {100};
            double expected__         = 100.0;

            return verifyCase(casenum__, expected__, new TheFrog().getMinimum(D, L, R));
        }
        case 3: {
            int D                     = 100;
            int[] L                   = {0, 50};
            int[] R                   = {50, 100};
            double expected__         = 50.0;

            return verifyCase(casenum__, expected__, new TheFrog().getMinimum(D, L, R));
        }
        case 4: {
            int D                     = 30000;
            int[] L                   = {17, 25281, 5775, 2825, 14040};
            int[] R                   = {55, 26000, 5791, 3150, 14092};
            double expected__         = 787.8787878787879;

            return verifyCase(casenum__, expected__, new TheFrog().getMinimum(D, L, R));
        }

        // custom cases

/*      case 5: {
            int D                     = ;
            int[] L                   = ;
            int[] R                   = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new TheFrog().getMinimum(D, L, R));
        }*/
/*      case 6: {
            int D                     = ;
            int[] L                   = ;
            int[] R                   = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new TheFrog().getMinimum(D, L, R));
        }*/
/*      case 7: {
            int D                     = ;
            int[] L                   = ;
            int[] R                   = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new TheFrog().getMinimum(D, L, R));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
