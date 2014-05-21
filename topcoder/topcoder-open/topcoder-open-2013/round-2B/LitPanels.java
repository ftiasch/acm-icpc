import java.math.*;
import java.util.*;

public class LitPanels {
    final static int MOD = (int)1e9 + 7;

    int bi(boolean condition) {
        return condition ? 1 : 0;
    }

    long solve(int n, int m, int a, int b) {
        int[] count = new int[1 << 6];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                int mask = 0;
                mask |= bi(i == 0) << 0;
                mask |= bi(j == 0) << 1;
                mask |= bi(i == n - 1) << 2;
                mask |= bi(j == m - 1) << 3;
                mask |= bi((i >= a || j >= b) && (n - i > a || m - j > b)) << 4;
                mask |= bi((i >= a || m - j > b) && (n - i > a || j >= b)) << 5;
                count[mask] ++;
            }
        }
        int[] power = new int[n * m + 1];
        power[0] = 1;
        for (int i = 1; i <= n * m; ++ i) {
            power[i] = power[i - 1] * 2 % MOD;
        }
        int[] ways = new int[1 << 6];
        ways[0] = 1;
        for (int type = 0; type < 1 << 6; ++ type) {
            if (count[type] > 0) {
                int[] newWays = new int[1 << 6];
                for (int mask = 0; mask < 1 << 6; ++ mask) {
                    newWays[mask] += ways[mask];
                    newWays[mask] %= MOD;
                    newWays[mask | type] += (int)((long)ways[mask] * (power[count[type]] - 1) % MOD);
                    newWays[mask | type] %= MOD;
                }
                ways = newWays;
            }
        }
        long ret = 0;
        for (int mask = 0; mask < 3; ++ mask) {
            ret += ways[mask << 4 | 15];
            ret %= MOD;
        }
        return ret % MOD;
    }

    public int countPatterns(int n, int m, int a, int b) {
        long ret = 0;
        for (int i = 1; i <= n; ++ i) {
            for (int j = 1; j <= m; ++ j) {
                ret += solve(i, j, a, b) * (n - i + 1) % MOD * (m - j + 1) % MOD;
                ret %= MOD;
            }
        }
        return (int)((ret + 1) % MOD);
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            LitPanelsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                LitPanelsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class LitPanelsHarness {
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
            int X                     = 2;
            int Y                     = 2;
            int sx                    = 1;
            int sy                    = 1;
            int expected__            = 11;

            return verifyCase(casenum__, expected__, new LitPanels().countPatterns(X, Y, sx, sy));
        }
        case 1: {
            int X                     = 2;
            int Y                     = 3;
            int sx                    = 1;
            int sy                    = 2;
            int expected__            = 40;

            return verifyCase(casenum__, expected__, new LitPanels().countPatterns(X, Y, sx, sy));
        }
        case 2: {
            int X                     = 4;
            int Y                     = 4;
            int sx                    = 3;
            int sy                    = 2;
            int expected__            = 14096;

            return verifyCase(casenum__, expected__, new LitPanels().countPatterns(X, Y, sx, sy));
        }
        case 3: {
            int X                     = 40;
            int Y                     = 39;
            int sx                    = 5;
            int sy                    = 8;
            int expected__            = 877713074;

            return verifyCase(casenum__, expected__, new LitPanels().countPatterns(X, Y, sx, sy));
        }

        // custom cases

/*      case 4: {
            int X                     = ;
            int Y                     = ;
            int sx                    = ;
            int sy                    = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new LitPanels().countPatterns(X, Y, sx, sy));
        }*/
/*      case 5: {
            int X                     = ;
            int Y                     = ;
            int sx                    = ;
            int sy                    = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new LitPanels().countPatterns(X, Y, sx, sy));
        }*/
/*      case 6: {
            int X                     = ;
            int Y                     = ;
            int sx                    = ;
            int sy                    = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new LitPanels().countPatterns(X, Y, sx, sy));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
