import java.math.*;
import java.util.*;

public class Nisoku {
    public double theMax(double[] cards) {
        Arrays.sort(cards);
        int n = cards.length;
        double answer = 0;
        for (int i = 0; i <= n; ++ i) {
            double result = 1;
            for (int j = 0; j < i; ++ j) {
                int k = i - 1 - j;
                if (j < k) {
                    result *= cards[j] + cards[k];
                } else if (j == k) {
                    result *= cards[j];
                }
            }
            for (int j = i; j < n; ++ j) {
                result *= cards[j];
            }
            answer = Math.max(answer, result);
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            NisokuHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                NisokuHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class NisokuHarness {
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
            double[] cards            = {5, 8};
            double expected__         = 40.0;

            return verifyCase(casenum__, expected__, new Nisoku().theMax(cards));
        }
        case 1: {
            double[] cards            = {1.5, 1.8};
            double expected__         = 3.3;

            return verifyCase(casenum__, expected__, new Nisoku().theMax(cards));
        }
        case 2: {
            double[] cards            = {8.26, 7.54, 3.2567};
            double expected__         = 202.82857868;

            return verifyCase(casenum__, expected__, new Nisoku().theMax(cards));
        }
        case 3: {
            double[] cards            = {1.5, 1.7, 1.6, 1.5};
            double expected__         = 9.920000000000002;

            return verifyCase(casenum__, expected__, new Nisoku().theMax(cards));
        }
        case 4: {
            double[] cards            = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
            double expected__         = 1.0E50;

            return verifyCase(casenum__, expected__, new Nisoku().theMax(cards));
        }

        // custom cases

/*      case 5: {
            double[] cards            = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new Nisoku().theMax(cards));
        }*/
/*      case 6: {
            double[] cards            = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new Nisoku().theMax(cards));
        }*/
/*      case 7: {
            double[] cards            = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new Nisoku().theMax(cards));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
