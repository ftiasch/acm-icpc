import java.util.*;
import java.math.*;

public class EllysPairs {
    public int getDifference(int[] knowledge) {
        int n = knowledge.length;
        Arrays.sort(knowledge);
        int maximum = Integer.MIN_VALUE;
        int minimum = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++ i) {
            maximum = Math.max(maximum, knowledge[i] + knowledge[n - 1 - i]);
            minimum = Math.min(minimum, knowledge[i] + knowledge[n - 1 - i]);
        }
        return maximum - minimum;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            EllysPairsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                EllysPairsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class EllysPairsHarness {
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
            int[] knowledge           = {2, 6, 4, 3};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new EllysPairs().getDifference(knowledge));
        }
        case 1: {
            int[] knowledge           = {1, 1, 1, 1, 1, 1};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new EllysPairs().getDifference(knowledge));
        }
        case 2: {
            int[] knowledge           = {4, 2, 4, 2, 1, 3, 3, 7};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new EllysPairs().getDifference(knowledge));
        }
        case 3: {
            int[] knowledge           = {5, 1, 8, 8, 13, 7, 6, 2, 1, 9, 5, 11, 3, 4};
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new EllysPairs().getDifference(knowledge));
        }

        // custom cases

/*      case 4: {
            int[] knowledge           = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new EllysPairs().getDifference(knowledge));
        }*/
/*      case 5: {
            int[] knowledge           = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new EllysPairs().getDifference(knowledge));
        }*/
/*      case 6: {
            int[] knowledge           = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new EllysPairs().getDifference(knowledge));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
