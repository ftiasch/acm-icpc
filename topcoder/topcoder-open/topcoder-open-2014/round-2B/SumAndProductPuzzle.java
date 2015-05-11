import java.math.*;
import java.util.*;

public class SumAndProductPuzzle {
    long solve(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[1] = false;
        for (int p = 2; p * p < n + 1; ++ p) {
            if (isPrime[p]) {
                for (int d = p * p; d < n + 1; d += p) {
                    isPrime[d] = false;
                }
            }
        }
        int[] count = new int[n];
        for (int a = 1; a * a < n; ++ a) {
            for (int b = a; a * b < n; ++ b) {
                if (!isPrime[a + b - 1]) {
                    count[a * b] ++;
                }
            }
        }
        long result = 0;
        for (int s = 3; s < n; ++ s) {
            if (!isPrime[s - 1] && count[s - 1] == 1) {
                result += s;
            }
        }
        return result;
    }

    public long getSum(int a, int b) {
        return solve(b + 1) - solve(a);
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            SumAndProductPuzzleHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                SumAndProductPuzzleHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class SumAndProductPuzzleHarness {
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

    static int runTestCase(int casenum__) {
        switch(casenum__) {
        case 0: {
            int A                     = 30;
            int B                     = 33;
            long expected__           = 33;

            return verifyCase(casenum__, expected__, new SumAndProductPuzzle().getSum(A, B));
        }
        case 1: {
            int A                     = 8;
            int B                     = 11;
            long expected__           = 19;

            return verifyCase(casenum__, expected__, new SumAndProductPuzzle().getSum(A, B));
        }
        case 2: {
            int A                     = 40;
            int B                     = 43;
            long expected__           = 0;

            return verifyCase(casenum__, expected__, new SumAndProductPuzzle().getSum(A, B));
        }
        case 3: {
            int A                     = 47;
            int B                     = 74;
            long expected__           = 168;

            return verifyCase(casenum__, expected__, new SumAndProductPuzzle().getSum(A, B));
        }
        case 4: {
            int A                     = 1;
            int B                     = 2;
            long expected__           = 0;

            return verifyCase(casenum__, expected__, new SumAndProductPuzzle().getSum(A, B));
        }

        // custom cases

/*      case 5: {
            int A                     = ;
            int B                     = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new SumAndProductPuzzle().getSum(A, B));
        }*/
/*      case 6: {
            int A                     = ;
            int B                     = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new SumAndProductPuzzle().getSum(A, B));
        }*/
/*      case 7: {
            int A                     = ;
            int B                     = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new SumAndProductPuzzle().getSum(A, B));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
