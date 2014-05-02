import java.math.*;
import java.util.*;

public class PalindromicSubstringsDiv1 {
    public double expectedPalindromes(String[] s1, String[] s2) {
        String s = concat(s1) + concat(s2);
        int n = s.length();
        double result = 0;
        for (int i = 0; i < n; ++ i) {
            double probability = 1.0;
            for (int j = 0; i - j >= 0 && i + j < n; ++ j) {
                if (s.charAt(i - j) != '?' && s.charAt(i + j) != '?' && s.charAt(i - j) != s.charAt(i + j)) {
                    probability *= 0.;
                }
                if (j > 0 && (s.charAt(i - j) == '?' || s.charAt(i + j) == '?')) {
                    probability *= 1. / 26.;
                }
                result += probability;
            }
            probability = 1.0;
            for (int j = 0; i - j >= 0 && i + 1 + j < n; ++ j) {
                if (s.charAt(i - j) != '?' && s.charAt(i + 1 + j) != '?' && s.charAt(i - j) != s.charAt(i + 1 + j)) {
                    probability *= 0.;
                }
                if (s.charAt(i - j) == '?' || s.charAt(i + 1 + j) == '?') {
                    probability *= 1. / 26.;
                }
                result += probability;
            }
        }
        return result;
    }

    String concat(String[] parts) {
        StringBuffer buffer = new StringBuffer();
        for (String part : parts) {
            buffer.append(part);
        }
        return buffer.toString();
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            PalindromicSubstringsDiv1Harness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                PalindromicSubstringsDiv1Harness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class PalindromicSubstringsDiv1Harness {
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
            String[] S1               = {"a","a",""};
            String[] S2               = {"a"};
            double expected__         = 6.0;

            return verifyCase(casenum__, expected__, new PalindromicSubstringsDiv1().expectedPalindromes(S1, S2));
        }
        case 1: {
            String[] S1               = {"z??"};
            String[] S2               = {};
            double expected__         = 3.115384615384615;

            return verifyCase(casenum__, expected__, new PalindromicSubstringsDiv1().expectedPalindromes(S1, S2));
        }
        case 2: {
            String[] S1               = {"ab","c"};
            String[] S2               = {"??","a?"};
            double expected__         = 7.315088757396449;

            return verifyCase(casenum__, expected__, new PalindromicSubstringsDiv1().expectedPalindromes(S1, S2));
        }
        case 3: {
            String[] S1               = {};
            String[] S2               = {"?"};
            double expected__         = 1.0;

            return verifyCase(casenum__, expected__, new PalindromicSubstringsDiv1().expectedPalindromes(S1, S2));
        }
        case 4: {
            String[] S1               = {"ab?def","?"};
            String[] S2               = {"f??a"};
            double expected__         = 12.545971779699588;

            return verifyCase(casenum__, expected__, new PalindromicSubstringsDiv1().expectedPalindromes(S1, S2));
        }

        // custom cases

/*      case 5: {
            String[] S1               = ;
            String[] S2               = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new PalindromicSubstringsDiv1().expectedPalindromes(S1, S2));
        }*/
/*      case 6: {
            String[] S1               = ;
            String[] S2               = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new PalindromicSubstringsDiv1().expectedPalindromes(S1, S2));
        }*/
/*      case 7: {
            String[] S1               = ;
            String[] S2               = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new PalindromicSubstringsDiv1().expectedPalindromes(S1, S2));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
