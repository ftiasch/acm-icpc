import java.math.*;
import java.util.*;

public class FoxAndPhotography {
    public int getMinimumSwaps(int[] a, int[] b) {
        int n = a.length;
        int[] ones = new int[1 << n];
        ones[0] = 0;
        for (int mask = 1; mask < 1 << n; ++ mask) {
            ones[mask] = ones[mask >> 1] + (mask & 1);
        }
        int[] minimum = new int[1 << n];
        Arrays.fill(minimum, Integer.MAX_VALUE);
        minimum[(1 << n) - 1] = 0;
        for (int mask = (1 << n) - 1; mask >= 0; -- mask) {
            if (minimum[mask] == Integer.MAX_VALUE) {
                continue;
            }
            int i = n - ones[mask];
            int cost = 0;
            for (int j = 0; j < n; ++ j) {
                if ((mask >> j & 1) == 1) {
                    if (a[i] < b[j]) {
                        minimum[mask ^ (1 << j)] = Math.min(minimum[mask ^ (1 << j)], minimum[mask] + cost);
                    }
                    cost ++;
                }
            }
        }
        return minimum[0] == Integer.MAX_VALUE ? -1 : minimum[0];
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            FoxAndPhotographyHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                FoxAndPhotographyHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class FoxAndPhotographyHarness {
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
            int[] heightsFront        = {140, 150};
            int[] heightsBack         = {160, 150};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new FoxAndPhotography().getMinimumSwaps(heightsFront, heightsBack));
        }
        case 1: {
            int[] heightsFront        = {140, 140, 140, 140};
            int[] heightsBack         = {190, 190, 190, 190};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new FoxAndPhotography().getMinimumSwaps(heightsFront, heightsBack));
        }
        case 2: {
            int[] heightsFront        = {170, 170, 170};
            int[] heightsBack         = {160, 170, 180};
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new FoxAndPhotography().getMinimumSwaps(heightsFront, heightsBack));
        }
        case 3: {
            int[] heightsFront        = {140, 141, 142, 143};
            int[] heightsBack         = {144, 143, 142, 141};
            int expected__            = 6;

            return verifyCase(casenum__, expected__, new FoxAndPhotography().getMinimumSwaps(heightsFront, heightsBack));
        }
        case 4: {
            int[] heightsFront        = {140, 170, 140, 170, 140, 170, 140, 170, 140, 170} ;
            int[] heightsBack         = {180, 180, 180, 180, 180, 150, 150, 150, 150, 150};
            int expected__            = 15;

            return verifyCase(casenum__, expected__, new FoxAndPhotography().getMinimumSwaps(heightsFront, heightsBack));
        }

        // custom cases

/*      case 5: {
            int[] heightsFront        = ;
            int[] heightsBack         = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new FoxAndPhotography().getMinimumSwaps(heightsFront, heightsBack));
        }*/
/*      case 6: {
            int[] heightsFront        = ;
            int[] heightsBack         = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new FoxAndPhotography().getMinimumSwaps(heightsFront, heightsBack));
        }*/
/*      case 7: {
            int[] heightsFront        = ;
            int[] heightsBack         = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new FoxAndPhotography().getMinimumSwaps(heightsFront, heightsBack));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
