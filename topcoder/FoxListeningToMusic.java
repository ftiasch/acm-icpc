import java.math.*;
import java.util.*;

public class FoxListeningToMusic {
    public double[] getProbabilities(int[] length, int m) {
        int n = length.length;
        double dp[] = new double[m + 1];
        dp[0] = 1;
        double result[] = new double[n];
        for (int i = 0; i <= m; ++ i) {
            for (int j = 0; j < n; ++ j) {
                if (i + length[j] <= m) {
                    dp[i + length[j]] += dp[i] / n;
                } else {
                    result[j] += dp[i] / n;
                }
            }
        }
        return result;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            FoxListeningToMusicHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                FoxListeningToMusicHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class FoxListeningToMusicHarness {
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
    static boolean compareOutput(double[] expected, double[] result) { if (expected.length != result.length) return false; for (int i=0; i<expected.length; ++i) if (!compareOutput(expected[i], result[i])) return false; return true; }
    static double relativeError(double[] expected, double[] result) { double ret = 0.0; for (int i=0; i<expected.length; ++i) { ret = Math.max(ret, relativeError(expected[i], result[i])); } return ret; }

    static String formatResult(double[] res) {
        String ret = "";
        ret += "{";
        for (int i=0; i<res.length; ++i) {
            if (i > 0) ret += ",";
            ret += String.format(" %.10g", res[i]);
        }
        ret += " }";
        return ret;
    }

    static int verifyCase(int casenum, double[] expected, double[] received) {
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
            int[] length              = {1, 2};
            int T                     = 1;
            double[] expected__       = {0.25, 0.75 };

            return verifyCase(casenum__, expected__, new FoxListeningToMusic().getProbabilities(length, T));
        }
        case 1: {
            int[] length              = {1, 10, 100, 1000, 10000};
            int T                     = 0;
            double[] expected__       = {0.2, 0.2, 0.2, 0.2, 0.2 };

            return verifyCase(casenum__, expected__, new FoxListeningToMusic().getProbabilities(length, T));
        }
        case 2: {
            int[] length              = {5, 8, 4, 7};
            int T                     = 10;
            double[] expected__       = {0.1875, 0.3125, 0.1875, 0.3125 };

            return verifyCase(casenum__, expected__, new FoxListeningToMusic().getProbabilities(length, T));
        }
        case 3: {
            int[] length              = {10, 1};
            int T                     = 9;
            double[] expected__       = {0.9990234375, 9.765625E-4 };

            return verifyCase(casenum__, expected__, new FoxListeningToMusic().getProbabilities(length, T));
        }
        case 4: {
            int[] length              = {58, 47, 36, 25, 14, 3};
            int T                     = 100;
            double[] expected__       = {0.32895835374381194, 0.26291497538241776, 0.18463894970453887, 0.1312301113062895, 0.07518634032025856, 0.017071269542683242 };

            return verifyCase(casenum__, expected__, new FoxListeningToMusic().getProbabilities(length, T));
        }

        // custom cases

/*      case 5: {
            int[] length              = ;
            int T                     = ;
            double[] expected__       = ;

            return verifyCase(casenum__, expected__, new FoxListeningToMusic().getProbabilities(length, T));
        }*/
/*      case 6: {
            int[] length              = ;
            int T                     = ;
            double[] expected__       = ;

            return verifyCase(casenum__, expected__, new FoxListeningToMusic().getProbabilities(length, T));
        }*/
/*      case 7: {
            int[] length              = ;
            int T                     = ;
            double[] expected__       = ;

            return verifyCase(casenum__, expected__, new FoxListeningToMusic().getProbabilities(length, T));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
