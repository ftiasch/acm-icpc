import java.math.*;
import java.util.*;

public class ColorfulCoins {
    public int minQueries(long[] coins) {
        int n = coins.length - 1;
        int[] limits = new int[n];
        for (int i = 0; i < n; ++ i) {
            limits[i] = (int)Math.min(coins[i + 1] / coins[i], n + 1);
        }
        Arrays.sort(limits);
        int result = 1;
        int[] count = new int[n + 2];
        Arrays.fill(count, 1);
        while (true) {
            for (int i = 0; i <= n + 1; ++ i) {
                count[i] = Math.min(count[i] * i, n + 1);
            }
            boolean valid = true;
            for (int i = 0; i < n; ++ i) {
                valid &= i + 1 <= count[limits[i]] - 1;
            }
            if (valid) {
                break;
            }
            result ++;
        }
        return result;
    }

    boolean check(int[] limits, int m) {
        int n = limits.length;
        return true;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            ColorfulCoinsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                ColorfulCoinsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class ColorfulCoinsHarness {
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
            long[] values             = {1};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new ColorfulCoins().minQueries(values));
        }
        case 1: {
            long[] values             = {1, 3};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new ColorfulCoins().minQueries(values));
        }
        case 2: {
            long[] values             = {1, 2, 4};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new ColorfulCoins().minQueries(values));
        }
        case 3: {
            long[] values             = {1, 2, 4, 8, 16};
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new ColorfulCoins().minQueries(values));
        }
        case 4: {
            long[] values             = {1, 2, 6, 30, 90, 270, 810, 2430, 7290, 29160, 87480, 262440, 787320, 3149280, 9447840, 28343520, 56687040, 170061120, 510183360, 1530550080, 3061100160L, 9183300480L, 27549901440L, 82649704320L, 247949112960L, 1239745564800L, 3719236694400L, 14876946777600L, 44630840332800L, 223154201664000L, 669462604992000L, 2008387814976000L, 6025163444928000L, 12050326889856000L, 24100653779712000L, 72301961339136000L, 289207845356544000L, 867623536069632000L};
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new ColorfulCoins().minQueries(values));
        }

        // custom cases

/*      case 5: {
            long[] values             = {};
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ColorfulCoins().minQueries(values));
        }*/
/*      case 6: {
            long[] values             = {};
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ColorfulCoins().minQueries(values));
        }*/
/*      case 7: {
            long[] values             = {};
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ColorfulCoins().minQueries(values));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
