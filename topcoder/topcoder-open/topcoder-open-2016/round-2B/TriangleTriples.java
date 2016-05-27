import java.io.*;
import java.util.*;

public class TriangleTriples {
    static int MOD = (int)1e9 + 7;

    int update(int x, int a) {
        x += a;
        if (x >= MOD) {
            x -= MOD;
        }
        return x;
    }

    int count(int n) {
        if (n <= 1) {
            return 0;
        }
        return (int)((long)(n + 1) * n % MOD * (n - 1) % MOD * 166666668 % MOD);
    }

    int solve(int a, int b, int c) {
        int result = count(c);
        result = update(result, MOD - count(c - a));
        result = update(result, MOD - count(c - b));
        result = update(result, count(c - a - b));
        return result;
    }

    public int count(int a, int b, int c) {
        int result = (int)((long)a * b % MOD * c % MOD);
        result = update(result, MOD - solve(a, b, c));
        result = update(result, MOD - solve(b, c, a));
        result = update(result, MOD - solve(c, a, b));
        return result;
    }

    protected static void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            TriangleTriplesHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                TriangleTriplesHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class TriangleTriplesHarness {
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
            int A                     = 1;
            int B                     = 10;
            int C                     = 20;
            int expected__            = 10;

            return verifyCase(casenum__, expected__, new TriangleTriples().count(A, B, C));
        }
        case 1: {
            int A                     = 2;
            int B                     = 2;
            int C                     = 1000000000;
            int expected__            = 6;

            return verifyCase(casenum__, expected__, new TriangleTriples().count(A, B, C));
        }
        case 2: {
            int A                     = 10;
            int B                     = 10;
            int C                     = 10;
            int expected__            = 505;

            return verifyCase(casenum__, expected__, new TriangleTriples().count(A, B, C));
        }
        case 3: {
            int A                     = 1;
            int B                     = 1;
            int C                     = 1;
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new TriangleTriples().count(A, B, C));
        }
        case 4: {
            int A                     = 123456789;
            int B                     = 987654321;
            int C                     = 555555555;
            int expected__            = 64296241;

            return verifyCase(casenum__, expected__, new TriangleTriples().count(A, B, C));
        }

        // custom cases

/*      case 5: {
            int A                     = ;
            int B                     = ;
            int C                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TriangleTriples().count(A, B, C));
        }*/
/*      case 6: {
            int A                     = ;
            int B                     = ;
            int C                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TriangleTriples().count(A, B, C));
        }*/
/*      case 7: {
            int A                     = ;
            int B                     = ;
            int C                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TriangleTriples().count(A, B, C));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
