import java.math.*;
import java.util.*;

public class SixteenBricks {
    static int[] DELTA_X = {-1,  0, 0, 1};
    static int[] DELTA_Y = { 0, -1, 1, 0};

    boolean check(int mask, int x, int y) {
        return (mask >> (x << 2 | y) & 1) > 0;
    }

    public int maximumSurface(int[] height) {
        Arrays.sort(height);
        int[] maximum = new int[1 << 16];
        Arrays.fill(maximum, Integer.MIN_VALUE);
        maximum[0] = 4 * 4;
        for (int mask = 0; mask < 1 << 16; ++ mask) {
            int i = Integer.bitCount(mask);
            if (i < 16) {
                for (int x = 0; x < 4; ++ x) {
                    for (int y = 0; y < 4; ++ y) {
                        if (!check(mask, x, y)) {
                            int sum = maximum[mask];
                            for (int k = 0; k < 4; ++ k) {
                                int xx = x + DELTA_X[k];
                                int yy = y + DELTA_Y[k];
                                if (0 <= xx && xx < 4 && 0 <= yy && yy < 4 && !check(mask, xx, yy)) {
                                    sum -= height[i];
                                } else {
                                    sum += height[i];
                                }
                            }
                            int newMask = mask | 1 << (x << 2 | y);
                            maximum[newMask] = Math.max(maximum[newMask], sum);
                        }
                    }
                }
            }
        }
        return maximum[(1 << 16) - 1];
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            SixteenBricksHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                SixteenBricksHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class SixteenBricksHarness {
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
            int[] height              = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
            int expected__            = 32;

            return verifyCase(casenum__, expected__, new SixteenBricks().maximumSurface(height));
        }
        case 1: {
            int[] height              = {1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2};
            int expected__            = 64;

            return verifyCase(casenum__, expected__, new SixteenBricks().maximumSurface(height));
        }
        case 2: {
            int[] height              = {77, 78, 58, 34, 30, 20, 8, 71, 37, 74, 21, 45, 39, 16, 4, 59} ;
            int expected__            = 1798;

            return verifyCase(casenum__, expected__, new SixteenBricks().maximumSurface(height));
        }

        // custom cases

/*      case 3: {
            int[] height              = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new SixteenBricks().maximumSurface(height));
        }*/
/*      case 4: {
            int[] height              = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new SixteenBricks().maximumSurface(height));
        }*/
/*      case 5: {
            int[] height              = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new SixteenBricks().maximumSurface(height));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
