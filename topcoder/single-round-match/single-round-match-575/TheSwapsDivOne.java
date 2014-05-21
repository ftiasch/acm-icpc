import java.math.*;
import java.util.*;

public class TheSwapsDivOne {
    String concate(String[] strings) {
        StringBuffer buffer = new StringBuffer("");
        for (String string : strings) {
            buffer.append(string);
        }
        return buffer.toString();
    }

    public double find(String[] strings, int k) {
        String sequence = concate(strings);
        int n = sequence.length();
        int sum = 0;
        for (int i = 0; i < n; ++ i) {
            sum += sequence.charAt(i) - '0';
        }
        double probability = 1.0;
        for (int _ = 0; _ < k; ++ _) {
            probability = probability * (1.0 - 2.0 / n) + (1 - probability) * 2.0 / n / (n - 1);
        }
        double answer = 0.0;
        for (int i = 0; i < n; ++ i) {
            double coefficient = (i + 1) * (n - i);
            answer += coefficient * (probability * (sequence.charAt(i) - '0') + (1 - probability) * (sum - (sequence.charAt(i) - '0')) / (n - 1));
        }
        return answer / (n * (n + 1) / 2);
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            TheSwapsDivOneHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                TheSwapsDivOneHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class TheSwapsDivOneHarness {
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
            String[] sequence         = {"4", "77"};
            int k                     = 1;
            double expected__         = 10.0;

            return verifyCase(casenum__, expected__, new TheSwapsDivOne().find(sequence, k));
        }
        case 1: {
            String[] sequence         = {"4", "77"};
            int k                     = 47;
            double expected__         = 10.0;

            return verifyCase(casenum__, expected__, new TheSwapsDivOne().find(sequence, k));
        }
        case 2: {
            String[] sequence         = {"1", "1", "1", "1", "1", "1", "1"};
            int k                     = 1000000;
            double expected__         = 3.0;

            return verifyCase(casenum__, expected__, new TheSwapsDivOne().find(sequence, k));
        }
        case 3: {
            String[] sequence         = {"572685085149095989026478064633266980348504469", "19720257361", "9", "69"};
            int k                     = 7;
            double expected__         = 98.3238536775161;

            return verifyCase(casenum__, expected__, new TheSwapsDivOne().find(sequence, k));
        }

        // custom cases

/*      case 4: {
            String[] sequence         = ;
            int k                     = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new TheSwapsDivOne().find(sequence, k));
        }*/
/*      case 5: {
            String[] sequence         = ;
            int k                     = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new TheSwapsDivOne().find(sequence, k));
        }*/
/*      case 6: {
            String[] sequence         = ;
            int k                     = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new TheSwapsDivOne().find(sequence, k));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
