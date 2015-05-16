import java.math.*;
import java.util.*;

public class OddEvenTree {
    public int[] getTree(String[] s) {
        int n = s.length;
        int[][] parity = new int[n][n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                parity[i][j] = s[i].charAt(j) == 'E' ? 0 : 1;
            }
        }
        int[] depth = parity[0];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                if ((depth[i] + depth[j]) % 2 != parity[i][j]) {
                    return new int[]{-1};
                }
            }
        }
        int odd = 0;
        while (odd < n && depth[odd] == 0) {
            odd ++;
        }
        int[] result = new int[n - 1 << 1];
        int count = 0;
        for (int i = 1; i < n; ++ i) {
            if (depth[i] == 0) {
                if (odd == n) {
                    return new int[]{-1};
                }
                result[count ++] = odd;
                result[count ++] = i;
            } else {
                result[count ++] = 0;
                result[count ++] = i;
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
            OddEvenTreeHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                OddEvenTreeHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class OddEvenTreeHarness {
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
            String[] x                = {"EOE",
 "OEO",
 "EOE"};
            int[] expected__          = {0, 1, 2, 1 };

            return verifyCase(casenum__, expected__, new OddEvenTree().getTree(x));
        }
        case 1: {
            String[] x                = {"EO",
 "OE"};
            int[] expected__          = {0, 1 };

            return verifyCase(casenum__, expected__, new OddEvenTree().getTree(x));
        }
        case 2: {
            String[] x                = {"OO",
 "OE"};
            int[] expected__          = {-1 };

            return verifyCase(casenum__, expected__, new OddEvenTree().getTree(x));
        }
        case 3: {
            String[] x                = {"EO",
 "EE"};
            int[] expected__          = {-1 };

            return verifyCase(casenum__, expected__, new OddEvenTree().getTree(x));
        }
        case 4: {
            String[] x                = {"EOEO",
 "OEOE",
 "EOEO",
 "OEOE"};
            int[] expected__          = {0, 1, 0, 3, 2, 1 };

            return verifyCase(casenum__, expected__, new OddEvenTree().getTree(x));
        }

        // custom cases

/*      case 5: {
            String[] x                = ;
            int[] expected__          = ;

            return verifyCase(casenum__, expected__, new OddEvenTree().getTree(x));
        }*/
/*      case 6: {
            String[] x                = ;
            int[] expected__          = ;

            return verifyCase(casenum__, expected__, new OddEvenTree().getTree(x));
        }*/
/*      case 7: {
            String[] x                = ;
            int[] expected__          = ;

            return verifyCase(casenum__, expected__, new OddEvenTree().getTree(x));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
