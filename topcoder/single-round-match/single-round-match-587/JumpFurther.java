import java.math.*;
import java.util.*;

public class JumpFurther {
    public int furthest(int n, int badStep) {
        int now = 0;
        int touch = 0;
        for (int i = 1; i <= n; ++ i) {
            now += i;
            if (now == badStep) {
                touch ++;
            }
        }
        return now - touch;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            JumpFurtherHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                JumpFurtherHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class JumpFurtherHarness {
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
            int N                     = 2;
            int badStep               = 2;
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new JumpFurther().furthest(N, badStep));
        }
        case 1: {
            int N                     = 2;
            int badStep               = 1;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new JumpFurther().furthest(N, badStep));
        }
        case 2: {
            int N                     = 3;
            int badStep               = 3;
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new JumpFurther().furthest(N, badStep));
        }
        case 3: {
            int N                     = 1313;
            int badStep               = 5858;
            int expected__            = 862641;

            return verifyCase(casenum__, expected__, new JumpFurther().furthest(N, badStep));
        }
        case 4: {
            int N                     = 1;
            int badStep               = 757065;
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new JumpFurther().furthest(N, badStep));
        }

        // custom cases

/*      case 5: {
            int N                     = ;
            int badStep               = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new JumpFurther().furthest(N, badStep));
        }*/
/*      case 6: {
            int N                     = ;
            int badStep               = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new JumpFurther().furthest(N, badStep));
        }*/
/*      case 7: {
            int N                     = ;
            int badStep               = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new JumpFurther().furthest(N, badStep));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
