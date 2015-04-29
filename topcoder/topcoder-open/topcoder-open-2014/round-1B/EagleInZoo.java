import java.math.*;
import java.util.*;

public class EagleInZoo {
    public double calc(int[] parent, int eagles) {
        int n = parent.length + 1;
        int[] degree = new int[n];
        for (int i = 1; i < n; ++ i) {
            degree[parent[i - 1]] ++;
        }
        double[] path = new double[n];
        path[0] = 1.;
        for (int i = 1; i < n; ++ i) {
            int p = parent[i - 1];
            path[i] = path[p] / degree[p];
        }
        double result = 0.;
        for (int target = 0; target < n; ++ target) {
            boolean[] ancestor = new boolean[n];
            for (int p = target; p > 0; p = parent[p - 1]) {
                ancestor[p] = true;
            }
            double[] probability = new double[n];
            probability[0] = 1.;
            for (int e = 1; e < eagles; ++ e) {
                double[] newProbability = new double[n];
                for (int u = 1; u < n; ++ u) {
                    if (ancestor[u]) {
                        int p = parent[u - 1];
                        newProbability[p] += probability[p] * (1 - path[u]);
                        newProbability[u] += probability[p] * path[u];
                    }
                }
                probability = newProbability;
            }
            result += probability[target];
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            EagleInZooHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                EagleInZooHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class EagleInZooHarness {
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
            int[] parent              = {0,0};
            int K                     = 2;
            double expected__         = 1.0;

            return verifyCase(casenum__, expected__, new EagleInZoo().calc(parent, K));
        }
        case 1: {
            int[] parent              = {0,0};
            int K                     = 3;
            double expected__         = 0.5;

            return verifyCase(casenum__, expected__, new EagleInZoo().calc(parent, K));
        }
        case 2: {
            int[] parent              = {0,1,0,3};
            int K                     = 4;
            double expected__         = 0.75;

            return verifyCase(casenum__, expected__, new EagleInZoo().calc(parent, K));
        }
        case 3: {
            int[] parent              = {0,0,1,1,2,4,4,4,5,5,6,6};
            int K                     = 20;
            double expected__         = 0.14595168754091617;

            return verifyCase(casenum__, expected__, new EagleInZoo().calc(parent, K));
        }
        case 4: {
            int[] parent              = {0,1,2,3,2,1,1,7,0,9,10,11,12,13,14,15,16,17,18,14,9};
            int K                     = 24;
            double expected__         = 0.3272154791654077;

            return verifyCase(casenum__, expected__, new EagleInZoo().calc(parent, K));
        }
        case 5: {
            int[] parent              = {0,1,2,3,4,5,6,7,8,9,10};
            int K                     = 50;
            double expected__         = 0.0;

            return verifyCase(casenum__, expected__, new EagleInZoo().calc(parent, K));
        }

        // custom cases

/*      case 6: {
            int[] parent              = ;
            int K                     = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new EagleInZoo().calc(parent, K));
        }*/
/*      case 7: {
            int[] parent              = ;
            int K                     = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new EagleInZoo().calc(parent, K));
        }*/
/*      case 8: {
            int[] parent              = ;
            int K                     = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new EagleInZoo().calc(parent, K));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
