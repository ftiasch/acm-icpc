import java.math.*;
import java.util.*;

public class SparseFactorial {
    public long getCount(long low, long high, long d) {
        int divisor = (int)d;
        int[][] factors = factorize(divisor);
        int m = factors.length;
        long[][] minimums = new long[m][];
        for (int i = 0; i < m; ++ i) {
            minimums[i] = find(factors[i][0], factors[i][1], high);
        }
        long result = 0;
        for (int remainder = 0; remainder < divisor; ++ remainder) {
            long minimum = low;
            for (int i = 0; i < m; ++ i) {
                minimum = Math.max(minimum, minimums[i][remainder % minimums[i].length]);
            }
            if (minimum < Long.MAX_VALUE) {
                minimum = remainder + getMinimum(minimum - remainder, divisor);
                if (minimum <= high) {
                    result += 1 + (high - minimum) / divisor;
                }
            }
        }
        return result;
    }

    int[][] factorize(int n) {
        int[][] factors = new int[10][];
        int m = 0;
        for (int p = 2; p * p <= n; ++ p) {
            if (n % p == 0) {
                int e = 0;
                for (; n % p == 0; n /= p) {
                    e ++;
                }
                factors[m ++] = new int[]{p, e};
            }
        }
        if (n > 1) {
            factors[m ++] = new int[]{n, 1};
        }
        return Arrays.copyOf(factors, m);
    }

    long[] find(int p, int e, long high) {
        int[] powers = new int[e + 1];
        powers[0] = 1;
        for (int i = 1; i <= e; ++ i) {
            powers[i] = powers[i - 1] * p;
        }
        int[] count = new int[powers[e]];
        long[] minimum = new long[powers[e]];
        Arrays.fill(minimum, Long.MAX_VALUE);
        for (int i = 0; (long)i * i <= high; ++ i) {
            for (int j = 1; j <= e; ++ j) {
                if (i < powers[j] * e) {
                    int remainder = (int)((long)i * i % powers[j]);
                    while (remainder < powers[e]) {
                        count[remainder] ++;
                        if (count[remainder] == e) {
                            minimum[remainder] = (long)i * i + 1;
                        }
                        remainder += powers[j];
                    }
                }
            }
        }
        return minimum;
    }

    long getMinimum(long a, int d) {
        return ((Math.max(a, 0) + d - 1) / d) * d;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            SparseFactorialHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                SparseFactorialHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class SparseFactorialHarness {
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
            long lo                   = 4;
            long hi                   = 8;
            long divisor              = 6;
            long expected__           = 3;

            return verifyCase(casenum__, expected__, new SparseFactorial().getCount(lo, hi, divisor));
        }
        case 1: {
            long lo                   = 9;
            long hi                   = 11;
            long divisor              = 7;
            long expected__           = 1;

            return verifyCase(casenum__, expected__, new SparseFactorial().getCount(lo, hi, divisor));
        }
        case 2: {
            long lo                   = 1;
            long hi                   = 1000000000000L;
            long divisor              = 4;
            long expected__           = 999999999996L;

            return verifyCase(casenum__, expected__, new SparseFactorial().getCount(lo, hi, divisor));
        }
        case 3: {
            long lo                   = 55;
            long hi                   = 66;
            long divisor              = 98;
            long expected__           = 7;

            return verifyCase(casenum__, expected__, new SparseFactorial().getCount(lo, hi, divisor));
        }
        case 4: {
            long lo                   = 999990;
            long hi                   = 999999;
            long divisor              = 589824;
            long expected__           = 7;

            return verifyCase(casenum__, expected__, new SparseFactorial().getCount(lo, hi, divisor));
        }
        case 5: {
            long lo                   = 100000000;
            long hi                   = 100000050;
            long divisor              = 749910;
            long expected__           = 8;

            return verifyCase(casenum__, expected__, new SparseFactorial().getCount(lo, hi, divisor));
        }
        case 6: {
            long lo                   = 7777777776L;
            long hi                   = 7777777777L;
            long divisor              = 994009;
            long expected__           = 1;

            return verifyCase(casenum__, expected__, new SparseFactorial().getCount(lo, hi, divisor));
        }

        // custom cases

/*      case 7: {
            long lo                   = ;
            long hi                   = ;
            long divisor              = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new SparseFactorial().getCount(lo, hi, divisor));
        }*/
/*      case 8: {
            long lo                   = ;
            long hi                   = ;
            long divisor              = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new SparseFactorial().getCount(lo, hi, divisor));
        }*/
/*      case 9: {
            long lo                   = ;
            long hi                   = ;
            long divisor              = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new SparseFactorial().getCount(lo, hi, divisor));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
