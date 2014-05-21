import java.util.*;
import java.math.*;

public class EllysXors {
    long getXor(long N) {
        if (N == 1) {
            return 0;
        }
        long result = 0;
        if ((N >> 1 & 1) == 1) {
            result |= 1;
        }
        if ((N & 1) == 1) {
            for (int i = 1; (1L << i) < N; ++ i) {
                if (((N - 1) >> i & 1) == 1) {
                    result |= 1L << i;
                }
            }
        }
        return result;
    }

    public long getXor(long L, long R) {
        return getXor(R + 1) ^ getXor(L);
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            EllysXorsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                EllysXorsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class EllysXorsHarness {
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

    static boolean compareOutput(long expected, long result) { return expected == result; }
    static String formatResult(long res) {
        return String.format("%d", res);
    }

    static int verifyCase(int casenum, long expected, long received) {
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

    static int runTestCase(int casenum) {
        switch(casenum) {
        case 0: {
            long L                    = 3L;
            long R                    = 10L;
            long expected__           = 8L;

            return verifyCase(casenum, expected__, new EllysXors().getXor(L, R));
        }
        case 1: {
            long L                    = 5L;
            long R                    = 5L;
            long expected__           = 5L;

            return verifyCase(casenum, expected__, new EllysXors().getXor(L, R));
        }
        case 2: {
            long L                    = 13L;
            long R                    = 42L;
            long expected__           = 39L;

            return verifyCase(casenum, expected__, new EllysXors().getXor(L, R));
        }
        case 3: {
            long L                    = 666L;
            long R                    = 1337L;
            long expected__           = 0L;

            return verifyCase(casenum, expected__, new EllysXors().getXor(L, R));
        }
        case 4: {
            long L                    = 1234567L;
            long R                    = 89101112L;
            long expected__           = 89998783L;

            return verifyCase(casenum, expected__, new EllysXors().getXor(L, R));
        }

        // custom cases

/*      case 5: {
            long L                    = L;
            long R                    = L;
            long expected__           = L;

            return verifyCase(casenum, expected__, new EllysXors().getXor(L, R));
        }*/
/*      case 6: {
            long L                    = L;
            long R                    = L;
            long expected__           = L;

            return verifyCase(casenum, expected__, new EllysXors().getXor(L, R));
        }*/
/*      case 7: {
            long L                    = L;
            long R                    = L;
            long expected__           = L;

            return verifyCase(casenum, expected__, new EllysXors().getXor(L, R));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
