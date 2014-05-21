import java.math.*;
import java.util.*;

public class TheBrickTowerEasyDivOne {
    public int find(int redCount, int redHeight, int blueCount, int blueHeight) {
        int count = Math.min(redCount, blueCount);
        int result = count * 2;
        if (redHeight != blueHeight) {
            result += count;
        }
        if (redCount != blueCount) {
            result ++;
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            TheBrickTowerEasyDivOneHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                TheBrickTowerEasyDivOneHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class TheBrickTowerEasyDivOneHarness {
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
            int redCount              = 1;
            int redHeight             = 2;
            int blueCount             = 3;
            int blueHeight            = 4;
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new TheBrickTowerEasyDivOne().find(redCount, redHeight, blueCount, blueHeight));
        }
        case 1: {
            int redCount              = 4;
            int redHeight             = 4;
            int blueCount             = 4;
            int blueHeight            = 7;
            int expected__            = 12;

            return verifyCase(casenum__, expected__, new TheBrickTowerEasyDivOne().find(redCount, redHeight, blueCount, blueHeight));
        }
        case 2: {
            int redCount              = 7;
            int redHeight             = 7;
            int blueCount             = 4;
            int blueHeight            = 4;
            int expected__            = 13;

            return verifyCase(casenum__, expected__, new TheBrickTowerEasyDivOne().find(redCount, redHeight, blueCount, blueHeight));
        }
        case 3: {
            int redCount              = 47;
            int redHeight             = 47;
            int blueCount             = 47;
            int blueHeight            = 47;
            int expected__            = 94;

            return verifyCase(casenum__, expected__, new TheBrickTowerEasyDivOne().find(redCount, redHeight, blueCount, blueHeight));
        }

        // custom cases

/*      case 4: {
            int redCount              = ;
            int redHeight             = ;
            int blueCount             = ;
            int blueHeight            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TheBrickTowerEasyDivOne().find(redCount, redHeight, blueCount, blueHeight));
        }*/
/*      case 5: {
            int redCount              = ;
            int redHeight             = ;
            int blueCount             = ;
            int blueHeight            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TheBrickTowerEasyDivOne().find(redCount, redHeight, blueCount, blueHeight));
        }*/
/*      case 6: {
            int redCount              = ;
            int redHeight             = ;
            int blueCount             = ;
            int blueHeight            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TheBrickTowerEasyDivOne().find(redCount, redHeight, blueCount, blueHeight));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
