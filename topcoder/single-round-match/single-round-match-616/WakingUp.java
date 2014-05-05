import java.math.*;
import java.util.*;

public class WakingUp {
    public int maxSleepiness(int[] period, int[] start, int[] volume, int delta) {
        int n = period.length;
        int result = 0;
        int sleepiness = 0;
        for (int t = 1; t <= 2520; ++ t) {
            sleepiness -= delta;
            for (int i = 0; i < n; ++ i) {
                if (t % period[i] == start[i] % period[i]) {
                    sleepiness += volume[i];
                }
            }
            result = Math.max(result, sleepiness);
        }
        return sleepiness > 0 ? -1 : result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            WakingUpHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                WakingUpHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class WakingUpHarness {
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
            int[] period              = {2, 3};
            int[] start               = {1, 2};
            int[] volume              = {3, 4};
            int D                     = 3;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new WakingUp().maxSleepiness(period, start, volume, D));
        }
        case 1: {
            int[] period              = {1};
            int[] start               = {1};
            int[] volume              = {17};
            int D                     = 17;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new WakingUp().maxSleepiness(period, start, volume, D));
        }
        case 2: {
            int[] period              = {1};
            int[] start               = {1};
            int[] volume              = {23};
            int D                     = 17;
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new WakingUp().maxSleepiness(period, start, volume, D));
        }
        case 3: {
            int[] period              = {9, 2, 5, 5, 7};
            int[] start               = {6, 1, 4, 1, 6};
            int[] volume              = {71, 66, 7, 34, 6};
            int D                     = 50;
            int expected__            = 78;

            return verifyCase(casenum__, expected__, new WakingUp().maxSleepiness(period, start, volume, D));
        }
        case 4: {
            int[] period              = {5, 6, 5, 3, 8, 3, 4};
            int[] start               = {1, 1, 3, 2, 6, 3, 3};
            int[] volume              = {42, 85, 10, 86, 21, 78, 38};
            int D                     = 88;
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new WakingUp().maxSleepiness(period, start, volume, D));
        }

        // custom cases

/*      case 5: {
            int[] period              = ;
            int[] start               = ;
            int[] volume              = ;
            int D                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new WakingUp().maxSleepiness(period, start, volume, D));
        }*/
/*      case 6: {
            int[] period              = ;
            int[] start               = ;
            int[] volume              = ;
            int D                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new WakingUp().maxSleepiness(period, start, volume, D));
        }*/
/*      case 7: {
            int[] period              = ;
            int[] start               = ;
            int[] volume              = ;
            int D                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new WakingUp().maxSleepiness(period, start, volume, D));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
