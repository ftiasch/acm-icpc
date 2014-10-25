import java.math.*;
import java.util.*;

public class SplittingFoxes {
    static long MOD = (long)1e9 + 7;

    long[][] multiply(long[][] a, long[][] b) {
        int n = a.length;
        long[][] c = new long[n][n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                for (int k = 0; k < n; ++ k) {
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return c;
    }

    long[][] power(long[][] a, long n) {
        int m = a.length;
        long[][] b = new long[m][m];
        for (int i = 0; i < m; ++ i) {
            b[i][i] = 1;
        }
        while (n > 0) {
            if (n % 2 == 1) {
                b = multiply(b, a);
            }
            a = multiply(a, a);
            n >>= 1;
        }
        return b;
    }

    static int[][] DELTA = {{1, 0, -1,  0}, {0, 1, 0, -1}};

    long normalize(long n) {
        n %= MOD;
        if (n < 0) {
            n += MOD;
        }
        return n;
    }

    public int sum(long n, int s, int l, int r) {
        long[][] t = new long[16][16];
        for (int mask = 0; mask < 16; ++ mask) {
            int dir = mask >> 2;
            t[mask][mask] = s;
            for (int i = 0; i < 2; ++ i) {
                if ((mask >> i & 1) == 1) {
                    t[mask ^ 1 << i][mask] = normalize(s * DELTA[i][dir]);
                }
            }
            t[mask + 12 & 15][mask] = l;
            t[mask + 4  & 15][mask] = r;
        }
        long[][] x0 = new long[16][16];
        x0[0][0] = 1;
        long[][] xn = multiply(x0, power(t, n));
        return (int)((xn[0][3] + xn[0][7] + xn[0][11] + xn[0][15]) % MOD);
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            SplittingFoxesHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                SplittingFoxesHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class SplittingFoxesHarness {
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
            long n                    = 58;
            int S                     = 2;
            int L                     = 0;
            int R                     = 0;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new SplittingFoxes().sum(n, S, L, R));
        }
        case 1: {
            long n                    = 3;
            int S                     = 1;
            int L                     = 1;
            int R                     = 0;
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new SplittingFoxes().sum(n, S, L, R));
        }
        case 2: {
            long n                    = 5;
            int S                     = 1;
            int L                     = 3;
            int R                     = 2;
            int expected__            = 34;

            return verifyCase(casenum__, expected__, new SplittingFoxes().sum(n, S, L, R));
        }
        case 3: {
            long n                    = 5;
            int S                     = 1;
            int L                     = 2;
            int R                     = 3;
            int expected__            = 999999973;

            return verifyCase(casenum__, expected__, new SplittingFoxes().sum(n, S, L, R));
        }
        case 4: {
            long n                    = 123456789;
            int S                     = 987654321;
            int L                     = 544;
            int R                     = 544;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new SplittingFoxes().sum(n, S, L, R));
        }
        case 5: {
            long n                    = 65536;
            int S                     = 1024;
            int L                     = 512;
            int R                     = 4096;
            int expected__            = 371473914;

            return verifyCase(casenum__, expected__, new SplittingFoxes().sum(n, S, L, R));
        }

        // custom cases

/*      case 6: {
            long n                    = ;
            int S                     = ;
            int L                     = ;
            int R                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new SplittingFoxes().sum(n, S, L, R));
        }*/
/*      case 7: {
            long n                    = ;
            int S                     = ;
            int L                     = ;
            int R                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new SplittingFoxes().sum(n, S, L, R));
        }*/
/*      case 8: {
            long n                    = ;
            int S                     = ;
            int L                     = ;
            int R                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new SplittingFoxes().sum(n, S, L, R));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
