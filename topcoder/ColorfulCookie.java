import java.math.*;
import java.util.*;

public class ColorfulCookie {
    public int getMaximum(int[] cookies, int p1, int p2) {
        int low = 0;
        int high = 100000;
        while (low < high) {
            int middle = low + high + 1 >> 1;
            if (check(cookies, p1, p2, middle)) {
                low = middle;
            } else {
                high = middle - 1;
            }
        }
        return low * (p1 + p2);
    }

    boolean check(int[] cookies, int p1, int p2, int need) {
        int n = cookies.length;
        if (n == 1) {
            return false;
        }
        int[] maximum = new int[need + 1];
        Arrays.fill(maximum, Integer.MIN_VALUE);
        maximum[0] = 0;
        for (int cookie : cookies) {
            int[] newMaximum = new int[need + 1];
            Arrays.fill(newMaximum, Integer.MIN_VALUE);
            for (int j = 0; j <= need; ++ j) {
                if (maximum[j] != Integer.MIN_VALUE) {
                    for (int x = 0; p1 * x <= cookie && x <= need && j + x <= need; ++ x) {
                        int y = Math.min((cookie - p1 * x) / p2, need - x);
                        newMaximum[j + x] = Math.max(newMaximum[j + x], maximum[j] + y);
                    }
                }
            }
            maximum = newMaximum;
        }
        return maximum[need] >= need;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            ColorfulCookieHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                ColorfulCookieHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class ColorfulCookieHarness {
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
            int[] cookies             = {100, 100};
            int P1                    = 50;
            int P2                    = 50;
            int expected__            = 200;

            return verifyCase(casenum__, expected__, new ColorfulCookie().getMaximum(cookies, P1, P2));
        }
        case 1: {
            int[] cookies             = {50, 250, 50};
            int P1                    = 50;
            int P2                    = 100;
            int expected__            = 300;

            return verifyCase(casenum__, expected__, new ColorfulCookie().getMaximum(cookies, P1, P2));
        }
        case 2: {
            int[] cookies             = {2000};
            int P1                    = 100;
            int P2                    = 200;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new ColorfulCookie().getMaximum(cookies, P1, P2));
        }
        case 3: {
            int[] cookies             = {123, 456, 789, 555};
            int P1                    = 58;
            int P2                    = 158;
            int expected__            = 1728;

            return verifyCase(casenum__, expected__, new ColorfulCookie().getMaximum(cookies, P1, P2));
        }

        // custom cases

/*      case 4: {
            int[] cookies             = ;
            int P1                    = ;
            int P2                    = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ColorfulCookie().getMaximum(cookies, P1, P2));
        }*/
/*      case 5: {
            int[] cookies             = ;
            int P1                    = ;
            int P2                    = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ColorfulCookie().getMaximum(cookies, P1, P2));
        }*/
/*      case 6: {
            int[] cookies             = ;
            int P1                    = ;
            int P2                    = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ColorfulCookie().getMaximum(cookies, P1, P2));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
