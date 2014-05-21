import java.util.*;
import java.math.*;

public class MegaFactorial {
    class Context {
        int m, mod;

        Context(int m, int mod) {
            this.m = m;
            this.mod = mod;
        }

        long[][] multiply(long[][] a, long[][] b) {
            long[][] c = new long[m + 1][m + 1];
            for (int i = 0; i <= m; ++ i) {
                for (int j = 0; j <= m; ++ j) {
                    for (int k = 0; k <= m; ++ k) {
                        c[i][j] += a[i][k] * b[k][j] % mod;
                        c[i][j] %= mod;
                    }
                }
            }
            return c;
        }

        final static int MAX = 31;

        long run(int n, int p) {
            int total = 0;
            long[] lengths = new long[MAX];
            lengths[total ++] = 0;
            while (true) {
                lengths[total] = (lengths[total - 1] + 1) * p - 1;
                if (lengths[total] > n) {
                    break;
                }
                total ++;
            }
            long[][][] trans = new long[MAX][m + 1][m + 1];
            for (int i = 0; i < total; ++ i) {
                trans[i][0][0] = 1;
                for (int j = 1; j <= m; ++ j) {
                    trans[i][0][j] = i;
                    for (int k = j; k <= m; ++ k) {
                        trans[i][j][k] = 1;
                    }
                }
            }
            long[][][] powers = new long[MAX][m + 1][m + 1];
            for (int j = 0; j <= m; ++ j) {
                powers[0][j][j] = 1;
            }
            for (int i = 1; i < total; ++ i) {
                powers[i] = powers[i - 1];
                for (int j = 1; j < p; ++ j) {
                    powers[i] = multiply(powers[i], trans[i - 1]);
                    powers[i] = multiply(powers[i], powers[i - 1]);
                }
            }
            long[][] value = new long[m + 1][m + 1];
            value[0][0] = 1;
            for (int i = total - 1; i >= 0; -- i) {
                while (lengths[i] + 1 <= n) {
                    value = multiply(value, powers[i]);
                    value = multiply(value, trans[i]);
                    n -= (int)lengths[i] + 1;
                }
            }
            return value[0][m];
        }
    }

    long solve(int n, int m, int p, int mod) {
        return new Context(m, mod).run(n, p);
    }

    final static int MOD = (int)1e9 + 9;

    long inverse(long a) {
        return a == 1 ? 1 : (MOD - MOD / a) * inverse(MOD % a) % MOD;
    }

    public int countTrailingZeros(int n, int k, int b) {
        int[] primes = {7, 5, 3, 2};
        int prime = -1;
        for (int i = 0; i < 4 && prime == -1; ++ i) {
            if (b % primes[i] == 0) {
                prime = primes[i];
            }
        }
        int exponent = 0;
        while (b % prime == 0) {
            exponent ++;
            b /= prime;
        }
        long answer = solve(n, k, prime, MOD);
        long remainder = solve(n, k, prime, exponent);
        return (int)((answer + MOD - remainder) * inverse(exponent) % MOD);
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            MegaFactorialHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                MegaFactorialHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class MegaFactorialHarness {
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
            int N                     = 6;
            int K                     = 1;
            int B                     = 4;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new MegaFactorial().countTrailingZeros(N, K, B));
        }
        case 1: {
            int N                     = 4;
            int K                     = 2;
            int B                     = 6;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new MegaFactorial().countTrailingZeros(N, K, B));
        }
        case 2: {
            int N                     = 10;
            int K                     = 3;
            int B                     = 10;
            int expected__            = 22;

            return verifyCase(casenum__, expected__, new MegaFactorial().countTrailingZeros(N, K, B));
        }
        case 3: {
            int N                     = 50;
            int K                     = 10;
            int B                     = 8;
            int expected__            = 806813906;

            return verifyCase(casenum__, expected__, new MegaFactorial().countTrailingZeros(N, K, B));
        }
        case 4: {
            int N                     = 1000000000;
            int K                     = 16;
            int B                     = 2;
            int expected__            = 633700413;

            return verifyCase(casenum__, expected__, new MegaFactorial().countTrailingZeros(N, K, B));
        }

        // custom cases

/*      case 5: {
            int N                     = ;
            int K                     = ;
            int B                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new MegaFactorial().countTrailingZeros(N, K, B));
        }*/
/*      case 6: {
            int N                     = ;
            int K                     = ;
            int B                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new MegaFactorial().countTrailingZeros(N, K, B));
        }*/
/*      case 7: {
            int N                     = ;
            int K                     = ;
            int B                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new MegaFactorial().countTrailingZeros(N, K, B));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
