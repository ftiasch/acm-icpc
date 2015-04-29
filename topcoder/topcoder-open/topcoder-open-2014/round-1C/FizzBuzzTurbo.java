import java.math.*;
import java.util.*;

public class FizzBuzzTurbo {
    long[] solve(long n) {
        long c15 = n / 15;
        return new long[]{n / 3 - c15, n / 5 - c15, c15};
    }

    public long[] counts(long a, long b) {
        long[] result = new long[3];
        for (int i = 0; i < 3; ++ i) {
            result[i] = solve(b)[i] - solve(a - 1)[i];
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            FizzBuzzTurboHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                FizzBuzzTurboHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class FizzBuzzTurboHarness {
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

    static boolean compareOutput(long[] expected, long[] result) { if (expected.length != result.length) return false; for (int i=0; i<expected.length; ++i) if (expected[i] != result[i]) return false; return true; }

    static String formatResult(long[] res) {
        String ret = "";
        ret += "{";
        for (int i=0; i<res.length; ++i) {
            if (i > 0) ret += ",";
            ret += String.format(" %d", res[i]);
        }
        ret += " }";
        return ret;
    }

    static int verifyCase(int casenum, long[] expected, long[] received) {
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
            long A                    = 1;
            long B                    = 4;
            long[] expected__         = {1, 0, 0};

            return verifyCase(casenum__, expected__, new FizzBuzzTurbo().counts(A, B));
        }
        case 1: {
            long A                    = 2;
            long B                    = 6;
            long[] expected__         = {2, 1, 0};

            return verifyCase(casenum__, expected__, new FizzBuzzTurbo().counts(A, B));
        }
        case 2: {
            long A                    = 150;
            long B                    = 165;
            long[] expected__         = {4, 2, 2};

            return verifyCase(casenum__, expected__, new FizzBuzzTurbo().counts(A, B));
        }
        case 3: {
            long A                    = 474747;
            long B                    = 747474;
            long[] expected__         = {72728, 36363, 18182};

            return verifyCase(casenum__, expected__, new FizzBuzzTurbo().counts(A, B));
        }

        // custom cases

/*      case 4: {
            long A                    = ;
            long B                    = ;
            long[] expected__         = {};

            return verifyCase(casenum__, expected__, new FizzBuzzTurbo().counts(A, B));
        }*/
/*      case 5: {
            long A                    = ;
            long B                    = ;
            long[] expected__         = {};

            return verifyCase(casenum__, expected__, new FizzBuzzTurbo().counts(A, B));
        }*/
/*      case 6: {
            long A                    = ;
            long B                    = ;
            long[] expected__         = {};

            return verifyCase(casenum__, expected__, new FizzBuzzTurbo().counts(A, B));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
