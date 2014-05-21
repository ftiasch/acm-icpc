import java.math.*;
import java.util.*;

public class BallsSeparating {
    public int minOperations(int[] red, int[] green, int[] blue) {
        int n = red.length;
        int[][] count = new int[][]{red, green, blue};
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                for (int k = 0; k < n; ++ k) {
                    if (i != j && j != k && k != i) {
                        int total = 0;
                        for (int x = 0; x < n; ++ x) {
                            total += count[0][x] + count[1][x] + count[2][x];
                            if (x == i) {
                                total -= count[0][x];
                            } else if (x == j) {
                                total -= count[1][x];
                            } else if (x == k) {
                                total -= count[2][x];
                            } else {
                                total -= Math.max(count[0][x], Math.max(count[1][x], count[2][x]));
                            }
                        }
                        answer = Math.min(answer, total);
                    }
                }
            }
        }
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            BallsSeparatingHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                BallsSeparatingHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class BallsSeparatingHarness {
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
            int[] red                 = {1, 1, 1};
            int[] green               = {1, 1, 1};
            int[] blue                = {1, 1, 1};
            int expected__            = 6;

            return verifyCase(casenum__, expected__, new BallsSeparating().minOperations(red, green, blue));
        }
        case 1: {
            int[] red                 = {5};
            int[] green               = {6};
            int[] blue                = {8};
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new BallsSeparating().minOperations(red, green, blue));
        }
        case 2: {
            int[] red                 = {4, 6, 5, 7};
            int[] green               = {7, 4, 6, 3};
            int[] blue                = {6, 5, 3, 8};
            int expected__            = 37;

            return verifyCase(casenum__, expected__, new BallsSeparating().minOperations(red, green, blue));
        }
        case 3: {
            int[] red                 = {7, 12, 9, 9, 7};
            int[] green               = {7, 10, 8, 8, 9};
            int[] blue                = {8, 9, 5, 6, 13};
            int expected__            = 77;

            return verifyCase(casenum__, expected__, new BallsSeparating().minOperations(red, green, blue));
        }
        case 4: {
            int[] red                 = {842398, 491273, 958925, 849859, 771363, 67803, 184892, 391907, 256150, 75799};
            int[] green               = {268944, 342402, 894352, 228640, 903885, 908656, 414271, 292588, 852057, 889141};
            int[] blue                = {662939, 340220, 600081, 390298, 376707, 372199, 435097, 40266, 145590, 505103};
            int expected__            = 7230607;

            return verifyCase(casenum__, expected__, new BallsSeparating().minOperations(red, green, blue));
        }

        // custom cases

/*      case 5: {
            int[] red                 = ;
            int[] green               = ;
            int[] blue                = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new BallsSeparating().minOperations(red, green, blue));
        }*/
/*      case 6: {
            int[] red                 = ;
            int[] green               = ;
            int[] blue                = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new BallsSeparating().minOperations(red, green, blue));
        }*/
/*      case 7: {
            int[] red                 = ;
            int[] green               = ;
            int[] blue                = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new BallsSeparating().minOperations(red, green, blue));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
