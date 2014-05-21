import java.math.*;
import java.util.*;

public class WolfPack {
    final static int MOD = (int)1e9 + 7;

    long[] invFact;

    long inverse(long a) {
        return a == 1 ? 1 : (MOD - MOD / a) * inverse(MOD % a) % MOD;
    }

    long solve(int[] xs, int m) {
        int n = xs.length;
        Arrays.sort(xs);
        int xMin = xs[n - 1] - m;
        int xMax = xs[0] + m;
        if (xMin > xMax) {
            return 0;
        }
        long ret = 0;
        long factM = inverse(invFact[m]);
        for (int x = xMin; x <= xMax; x += 2) {
            long ways = 1;
            for (int i = 0; i < n && ways > 0; ++ i) {
                int d = m + Math.abs(x - xs[i]);
                if (d % 2 == 0) {
                    ways = ways * factM % MOD * invFact[d / 2] % MOD * invFact[m - d / 2] % MOD;
                } else {
                    ways = 0;
                }
            }
            ret += ways;
            if (ret >= MOD) {
                ret -= MOD;
            }
        }
        return ret;
    }

    public int calc(int[] x, int[] y, int m) {
        invFact = new long[m + 1];
        invFact[1] = 1;
        for (int i = 2; i <= m; ++ i) {
            invFact[i] = (MOD - MOD / i) * invFact[MOD % i] % MOD;
        }
        invFact[0] = 1;
        for (int i = 1; i <= m; ++ i) {
            invFact[i] = (invFact[i - 1] * invFact[i]) % MOD;
        }
        int n = x.length;
        for (int i = 0; i < n; ++ i) {
            x[i] = x[i] + y[i];
            y[i] = x[i] - 2 * y[i];
        }
        return (int)(solve(x, m) * solve(y, m) % MOD);
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            WolfPackHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                WolfPackHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class WolfPackHarness {
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
            int[] x                   = {3,5};
            int[] y                   = {0,0};
            int m                     = 1;
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new WolfPack().calc(x, y, m));
        }
        case 1: {
            int[] x                   = {0,0};
            int[] y                   = {0,1};
            int m                     = 1;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new WolfPack().calc(x, y, m));
        }
        case 2: {
            int[] x                   = {0,2,4};
            int[] y                   = {0,0,0};
            int m                     = 2;
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new WolfPack().calc(x, y, m));
        }
        case 3: {
            int[] x                   = {7,8};
            int[] y                   = {8,7};
            int m                     = 1;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new WolfPack().calc(x, y, m));
        }
        case 4: {
            int[] x                   = {-2,-2,-2,0,0,0,2,2,2};
            int[] y                   = {-2,0,2,-2,0,2,-2,0,2};
            int m                     = 1000;
            int expected__            = 387540818;

            return verifyCase(casenum__, expected__, new WolfPack().calc(x, y, m));
        }

        // custom cases

/*      case 5: {
            int[] x                   = ;
            int[] y                   = ;
            int m                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new WolfPack().calc(x, y, m));
        }*/
/*      case 6: {
            int[] x                   = ;
            int[] y                   = ;
            int m                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new WolfPack().calc(x, y, m));
        }*/
/*      case 7: {
            int[] x                   = ;
            int[] y                   = ;
            int m                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new WolfPack().calc(x, y, m));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
