import java.math.*;
import java.util.*;

public class SplitStoneGame {
    public String winOrLose(int[] number) {
        return check(number) ? "WIN" : "LOSE";
    }

    boolean check(int[] number) {
        int n = number.length;
        if (n == 1) {
            return false;
        }
        boolean allOne = true;
        for (int i = 0; i < n; ++ i) {
            allOne &= number[i] == 1;
        }
        if (allOne) {
            return false;
        }
        return n % 2 == 1;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            SplitStoneGameHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                SplitStoneGameHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class SplitStoneGameHarness {
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

    static boolean compareOutput(String expected, String result) { return expected.equals(result); }
    static String formatResult(String res) {
        return String.format("\"%s\"", res);
    }

    static int verifyCase(int casenum, String expected, String received) {
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
            int[] number              = {1, 1, 1};
            String expected__         = "LOSE";

            return verifyCase(casenum__, expected__, new SplitStoneGame().winOrLose(number));
        }
        case 1: {
            int[] number              = {2, 2};
            String expected__         = "LOSE";

            return verifyCase(casenum__, expected__, new SplitStoneGame().winOrLose(number));
        }
        case 2: {
            int[] number              = {1, 1, 2};
            String expected__         = "WIN";

            return verifyCase(casenum__, expected__, new SplitStoneGame().winOrLose(number));
        }
        case 3: {
            int[] number              = {1, 2, 3, 4, 3, 2, 2, 4, 3, 1, 4, 4, 1, 2, 4, 4, 1, 4, 3, 1, 4, 2, 1};
            String expected__         = "WIN";

            return verifyCase(casenum__, expected__, new SplitStoneGame().winOrLose(number));
        }
        case 4: {
            int[] number              = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 3, 1, 9, 1, 3, 1, 1, 1, 1, 1};
            String expected__         = "LOSE";

            return verifyCase(casenum__, expected__, new SplitStoneGame().winOrLose(number));
        }

        // custom cases

/*      case 5: {
            int[] number              = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new SplitStoneGame().winOrLose(number));
        }*/
/*      case 6: {
            int[] number              = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new SplitStoneGame().winOrLose(number));
        }*/
/*      case 7: {
            int[] number              = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new SplitStoneGame().winOrLose(number));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
