import java.math.*;
import java.util.*;

public class SubstringReversal {
    public int[] solve(String s) {
        int n = s.length();
        for (int i = 0; i < n; ++ i) {
            int j = i + 1;
            while (j < n && s.charAt(i) <= s.charAt(j)) {
                j ++;
            }
            if (j < n) {
                int[] result = new int[]{i, i};
                String best = s;
                for (int k = i + 1; k < n; ++ k) {
                    String newS = s.substring(0, i) + reverse(s.substring(i, k + 1)) + s.substring(k + 1);
                    if (newS.compareTo(best) < 0) {
                        best = newS;
                        result = new int[]{i, k};
                    }
                }
                return result;
            }
        }
        return new int[]{0, 0};
    }

    String reverse(String s) {
        return new StringBuffer(s).reverse().toString();
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            SubstringReversalHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                SubstringReversalHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class SubstringReversalHarness {
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

    static boolean compareOutput(int[] expected, int[] result) { if (expected.length != result.length) return false; for (int i=0; i<expected.length; ++i) if (expected[i] != result[i]) return false; return true; }

    static String formatResult(int[] res) {
        String ret = "";
        ret += "{";
        for (int i=0; i<res.length; ++i) {
            if (i > 0) ret += ",";
            ret += String.format(" %d", res[i]);
        }
        ret += " }";
        return ret;
    }

    static int verifyCase(int casenum, int[] expected, int[] received) {
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
            String S                  = "abdc";
            int[] expected__          = {2, 3 };

            return verifyCase(casenum__, expected__, new SubstringReversal().solve(S));
        }
        case 1: {
            String S                  = "aabbcc";
            int[] expected__          = {0, 0 };

            return verifyCase(casenum__, expected__, new SubstringReversal().solve(S));
        }
        case 2: {
            String S                  = "misof";
            int[] expected__          = {0, 4 };

            return verifyCase(casenum__, expected__, new SubstringReversal().solve(S));
        }
        case 3: {
            String S                  = "ivan";
            int[] expected__          = {0, 2 };

            return verifyCase(casenum__, expected__, new SubstringReversal().solve(S));
        }
        case 4: {
            String S                  = "thisseemstobeaneasyproblem";
            int[] expected__          = {0, 13 };

            return verifyCase(casenum__, expected__, new SubstringReversal().solve(S));
        }

        // custom cases

        case 5: {
            String S                  = "aa";
            int[] expected__          = {0, 0};

            return verifyCase(casenum__, expected__, new SubstringReversal().solve(S));
        }
/*      case 6: {
            String S                  = ;
            int[] expected__          = ;

            return verifyCase(casenum__, expected__, new SubstringReversal().solve(S));
        }*/
/*      case 7: {
            String S                  = ;
            int[] expected__          = ;

            return verifyCase(casenum__, expected__, new SubstringReversal().solve(S));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
