import java.math.*;
import java.util.*;

public class BinaryPolynomialDivOne {
    final static int LIMIT = 101;

    public int findCoefficient(int[] a, long m, long k) {
        int n = a.length;
        int ways[] = new int[LIMIT];
        Arrays.fill(ways, 0);
        ways[0] = 1;
        for (int bit = 60; bit >= 0; -- bit) {
            for (int i = LIMIT - 1; i >= 0; -- i) {
                if (i >= (k >> bit & 1) && ((i - (k >> bit & 1)) & 1) == 0) {
                    ways[i] = ways[(int)(i - (k >> bit & 1)) >> 1];
                } else {
                    ways[i] = 0;
                }
            }
            if ((m >> bit & 1) == 1) {
                for (int i = 0; i < LIMIT; ++ i) {
                    ways[i] *= a[0];
                    for (int j = 1; j < n && i + j < LIMIT; ++ j) {
                        ways[i] += ways[i + j] * a[j];
                        ways[i] &= 1;
                    }
                }
            }
        }
        return ways[0];
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            BinaryPolynomialDivOneHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                BinaryPolynomialDivOneHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class BinaryPolynomialDivOneHarness {
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
            int[] a                   = {1, 0, 1};
            long m                    = 3;
            long k                    = 4;
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new BinaryPolynomialDivOne().findCoefficient(a, m, k));
        }
        case 1: {
            int[] a                   = {1, 0, 1};
            long m                    = 3;
            long k                    = 5;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new BinaryPolynomialDivOne().findCoefficient(a, m, k));
        }
        case 2: {
            int[] a                   = {0, 0, 1, 1, 0, 1};
            long m                    = 7;
            long k                    = 15;
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new BinaryPolynomialDivOne().findCoefficient(a, m, k));
        }
        case 3: {
            int[] a                   = {1};
            long m                    = 1;
            long k                    = 0;
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new BinaryPolynomialDivOne().findCoefficient(a, m, k));
        }
        case 4: {
            int[] a                   = {1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1};
            long m                    = 2229508454872453L;
            long k                    = 96130299655104846L;
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new BinaryPolynomialDivOne().findCoefficient(a, m, k));
        }

        // custom cases

/*      case 5: {
            int[] a                   = ;
            long m                    = ;
            long k                    = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new BinaryPolynomialDivOne().findCoefficient(a, m, k));
        }*/
/*      case 6: {
            int[] a                   = ;
            long m                    = ;
            long k                    = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new BinaryPolynomialDivOne().findCoefficient(a, m, k));
        }*/
/*      case 7: {
            int[] a                   = ;
            long m                    = ;
            long k                    = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new BinaryPolynomialDivOne().findCoefficient(a, m, k));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
