import java.math.*;
import java.util.*;

public class LittleElephantAndBalls {
    static String COLORS = "RGB";

    public int getNumber(String balls) {
        int result = 0;
        int[] count = new int[3];
        for (int i = 0; i < balls.length(); ++ i) {
            for (int c = 0; c < 3; ++ c) {
                result += Math.min(count[c], 2);
            }
            count[COLORS.indexOf(balls.charAt(i))] ++;
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            LittleElephantAndBallsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                LittleElephantAndBallsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class LittleElephantAndBallsHarness {
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
            String S                  = "RGB";
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new LittleElephantAndBalls().getNumber(S));
        }
        case 1: {
            String S                  = "RGGRBBB";
            int expected__            = 21;

            return verifyCase(casenum__, expected__, new LittleElephantAndBalls().getNumber(S));
        }
        case 2: {
            String S                  = "RRRGBRR";
            int expected__            = 16;

            return verifyCase(casenum__, expected__, new LittleElephantAndBalls().getNumber(S));
        }
        case 3: {
            String S                  = "RRRR";
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new LittleElephantAndBalls().getNumber(S));
        }
        case 4: {
            String S                  = "GGRRRGR";
            int expected__            = 18;

            return verifyCase(casenum__, expected__, new LittleElephantAndBalls().getNumber(S));
        }
        case 5: {
            String S                  = "G";
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new LittleElephantAndBalls().getNumber(S));
        }

        // custom cases

/*      case 6: {
            String S                  = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new LittleElephantAndBalls().getNumber(S));
        }*/
/*      case 7: {
            String S                  = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new LittleElephantAndBalls().getNumber(S));
        }*/
/*      case 8: {
            String S                  = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new LittleElephantAndBalls().getNumber(S));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
