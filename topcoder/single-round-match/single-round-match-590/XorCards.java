import java.math.*;
import java.util.*;

public class XorCards {
    final static int M = 50;

    int rank(long[] coef, int n) {
        int m = coef.length;
        int r = 0;
        for (int i = 0; i < n; ++ i) {
            int p = r;
            while (p < m && (coef[p] >> i & 1) == 0) {
                p ++;
            }
            if (p < m) {
                long tmp = coef[p];
                coef[p] = coef[r];
                coef[r] = tmp;
                for (int j = 0; j < m; ++ j) {
                    if (j != r && (coef[j] >> i & 1) == 1) {
                        coef[j] ^= coef[r];
                    }
                }
                r ++;
            }
        }
        for (int i = 0; i < m; ++ i) {
            if (coef[i] == (1L << n)) {
                return -1;
            }
        }
        return r;
    }

    public long numberOfWays(long[] number, long limit) {
        int n = number.length;
        long[] power2 = new long[n + 1];
        power2[0] = 1;
        for (int i = 1; i <= n; ++ i) {
            power2[i] = power2[i - 1] * 2;
        }
        long answer = 0;
        limit ++;
        for (int b = 0; b < M; ++ b) {
            if ((limit >> b & 1) == 1) {
                long[] coef = new long[M - b];
                for (int i = b; i < M; ++ i) {
                    for (int j = 0; j < n; ++ j) {
                        coef[i - b] |= (number[j] >> i & 1) << j;
                    }
                    if (i > b && (limit >> i & 1) == 1) {
                        coef[i - b] |= 1L << n;
                    }
                }
                int r = rank(coef, n);
                if (r != -1) {
                    answer += power2[n - r];
                }
            }
        }
        return answer;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            XorCardsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                XorCardsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class XorCardsHarness {
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

    static boolean compareOutput(long expected, long result) { return expected == result; }
    static String formatResult(long res) {
        return String.format("%d", res);
    }

    static int verifyCase(int casenum, long expected, long received) {
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
            long[] number             = {1, 2};
            long limit                = 2;
            long expected__           = 3;

            return verifyCase(casenum__, expected__, new XorCards().numberOfWays(number, limit));
        }
        case 1: {
            long[] number             = {5, 5};
            long limit                = 3;
            long expected__           = 2;

            return verifyCase(casenum__, expected__, new XorCards().numberOfWays(number, limit));
        }
        case 2: {
            long[] number             = {1, 2, 3, 4, 5, 6, 7};
            long limit                = 5;
            long expected__           = 96;

            return verifyCase(casenum__, expected__, new XorCards().numberOfWays(number, limit));
        }
        case 3: {
            long[] number             = {123, 456, 789, 147, 258, 369, 159, 357};
            long limit                = 500;
            long expected__           = 125;

            return verifyCase(casenum__, expected__, new XorCards().numberOfWays(number, limit));
        }
        case 4: {
            long[] number             = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            long limit                = 1000000000000000L;
            long expected__           = 4294967296L;

            return verifyCase(casenum__, expected__, new XorCards().numberOfWays(number, limit));
        }
        case 5: {
            long[] number             = {1000000000000000L, 999999999999999L};
            long limit                = 65535;
            long expected__           = 2;

            return verifyCase(casenum__, expected__, new XorCards().numberOfWays(number, limit));
        }

        // custom cases

/*      case 6: {
            long[] number             = {};
            long limit                = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new XorCards().numberOfWays(number, limit));
        }*/
/*      case 7: {
            long[] number             = {};
            long limit                = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new XorCards().numberOfWays(number, limit));
        }*/
/*      case 8: {
            long[] number             = {};
            long limit                = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new XorCards().numberOfWays(number, limit));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
