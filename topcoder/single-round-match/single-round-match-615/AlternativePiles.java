import java.math.*;
import java.util.*;

public class AlternativePiles {
    static int MOD = (int)1e9 + 7;

    public int count(String colors, int m) {
        int n = colors.length();
        int[][] ways = new int[m + 1][m];
        ways[0][0] = 1;
        for (int i = 0; i < n; ++ i) {
            int[][] newWays = new int[m + 1][m];
            char c = colors.charAt(i);
            for (int j = 0; j <= m; ++ j) {
                for (int k = 0; k < m; ++ k) {
                    if (j + 1 <= m && (c == 'R' || c == 'W')) {
                        newWays[j + 1][(k + 1) % m] = (newWays[j + 1][(k + 1) % m] + ways[j][k]) % MOD;
                    }
                    if (j - 1 >= 0 && (c == 'G' || c == 'W')) {
                        newWays[j - 1][k] = (newWays[j - 1][k] + ways[j][k]) % MOD;
                    }
                    if (c == 'B' || c == 'W') {
                        newWays[j][k] = (newWays[j][k] + ways[j][k]) % MOD;
                    }
                }
            }
            ways = newWays;
        }
        return ways[0][0];
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            AlternativePilesHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                AlternativePilesHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class AlternativePilesHarness {
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

    static boolean compareOutput(int expected, int result) { return expected == result; }
    static String formatResult(int res) {
        return String.format("%d", res);
    }

    static int verifyCase(int casenum, int expected, int received) {
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
            String C                  = "WRGWWRGW";
            int M                     = 2;
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new AlternativePiles().count(C, M));
        }
        case 1: {
            String C                  = "RRGG";
            int M                     = 1;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new AlternativePiles().count(C, M));
        }
        case 2: {
            String C                  = "BBBB";
            int M                     = 5;
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new AlternativePiles().count(C, M));
        }
        case 3: {
            String C                  = "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW";
            int M                     = 50;
            int expected__            = 265470435;

            return verifyCase(casenum__, expected__, new AlternativePiles().count(C, M));
        }
        case 4: {
            String C                  = "WRWRGWWGWWWRWBWRWGWWRWBWWRGWBWGRGWWGWGRWGRWBRWBW";
            int M                     = 7;
            int expected__            = 7400348;

            return verifyCase(casenum__, expected__, new AlternativePiles().count(C, M));
        }

        // custom cases

/*      case 5: {
            String C                  = ;
            int M                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new AlternativePiles().count(C, M));
        }*/
/*      case 6: {
            String C                  = ;
            int M                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new AlternativePiles().count(C, M));
        }*/
/*      case 7: {
            String C                  = ;
            int M                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new AlternativePiles().count(C, M));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
