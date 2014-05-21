import java.math.*;
import java.util.*;

public class TriangleXor {
    public int theArea(int w) {
        double answer = 0.5 * w * (w + 1);
        for (int i = 0; i < w; ++ i) {
            answer += ((i & 1) == 0 ? -1 : 1) * (double)w * w * (w - i) / (w + i + 1);
        }
        return (int)answer;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            TriangleXorHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                TriangleXorHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class TriangleXorHarness {
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
            int W                     = 1;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new TriangleXor().theArea(W));
        }
        case 1: {
            int W                     = 2;
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new TriangleXor().theArea(W));
        }
        case 2: {
            int W                     = 3;
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new TriangleXor().theArea(W));
        }
        case 3: {
            int W                     = 4;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new TriangleXor().theArea(W));
        }
        case 4: {
            int W                     = 5;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new TriangleXor().theArea(W));
        }
        case 5: {
            int W                     = 12345;
            int expected__            = 4629;

            return verifyCase(casenum__, expected__, new TriangleXor().theArea(W));
        }

        // custom cases

/*      case 6: {
            int W                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TriangleXor().theArea(W));
        }*/
/*      case 7: {
            int W                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TriangleXor().theArea(W));
        }*/
/*      case 8: {
            int W                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TriangleXor().theArea(W));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
