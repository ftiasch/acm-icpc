import java.math.*;
import java.util.*;

public class MuddyRoad {
    public double getExpectedValue(int[] road) {
        int n = road.length;
        double value[] = new double[n];
        for (int i = n - 3; i >= 0; -- i) {
            double pOne = road[i + 1] / 100.0;
            double pTwo = road[i + 2] / 100.0;
            value[i] = (1 - pOne) * value[i + 1]
                + pOne * (value[i + 2] + pTwo);
        }
        return value[0];
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            MuddyRoadHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                MuddyRoadHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class MuddyRoadHarness {
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
            int[] road                = {0, 60, 60, 0};
            double expected__         = 0.36;

            return verifyCase(casenum__, expected__, new MuddyRoad().getExpectedValue(road));
        }
        case 1: {
            int[] road                = {0, 50, 50, 50, 50, 0};
            double expected__         = 0.5625;

            return verifyCase(casenum__, expected__, new MuddyRoad().getExpectedValue(road));
        }
        case 2: {
            int[] road                = {0, 0, 100, 100, 100, 100, 100, 100, 0, 0, 100, 0};
            double expected__         = 3.0;

            return verifyCase(casenum__, expected__, new MuddyRoad().getExpectedValue(road));
        }
        case 3: {
            int[] road                = {0, 12, 34, 56, 78, 91, 23, 45, 67, 89, 0};
            double expected__         = 1.7352539420031923;

            return verifyCase(casenum__, expected__, new MuddyRoad().getExpectedValue(road));
        }
        case 4: {
            int[] road                = {0, 50, 50, 100, 50, 100, 50, 50, 100, 66, 0};
            double expected__         = 2.288125;

            return verifyCase(casenum__, expected__, new MuddyRoad().getExpectedValue(road));
        }

        // custom cases

/*      case 5: {
            int[] road                = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new MuddyRoad().getExpectedValue(road));
        }*/
/*      case 6: {
            int[] road                = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new MuddyRoad().getExpectedValue(road));
        }*/
/*      case 7: {
            int[] road                = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new MuddyRoad().getExpectedValue(road));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
