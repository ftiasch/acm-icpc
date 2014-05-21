import java.math.*;
import java.util.*;

public class FoxPaintingBalls {
    boolean check(long r, long g, long b, long n, long m) {
        long part = n * (n + 1) / 6;
        if (part > 0 && m > Math.min(r, Math.min(g, b)) / part) {
            return false;
        }
        if (n % 3 == 1 && m > (r + g + b) / (3 * part + 1)) {
            return false;
        }
        return true;
    }

    public long theMax(long r, long g, long b, int n) {
        long low = 0;
        long high = 3000000000000000000L;
        while (low < high) {
            long middle = low + high + 1 >> 1;
            if (check(r, g, b, n, middle)) {
                low = middle;
            } else {
                high = middle - 1;
            }
        }
        return low;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            FoxPaintingBallsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                FoxPaintingBallsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class FoxPaintingBallsHarness {
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
            long R                    = 2;
            long G                    = 2;
            long B                    = 2;
            int N                     = 3;
            long expected__           = 1;

            return verifyCase(casenum__, expected__, new FoxPaintingBalls().theMax(R, G, B, N));
        }
        case 1: {
            long R                    = 1;
            long G                    = 2;
            long B                    = 3;
            int N                     = 3;
            long expected__           = 0;

            return verifyCase(casenum__, expected__, new FoxPaintingBalls().theMax(R, G, B, N));
        }
        case 2: {
            long R                    = 8;
            long G                    = 6;
            long B                    = 6;
            int N                     = 4;
            long expected__           = 2;

            return verifyCase(casenum__, expected__, new FoxPaintingBalls().theMax(R, G, B, N));
        }
        case 3: {
            long R                    = 7;
            long G                    = 6;
            long B                    = 7;
            int N                     = 4;
            long expected__           = 2;

            return verifyCase(casenum__, expected__, new FoxPaintingBalls().theMax(R, G, B, N));
        }
        case 4: {
            long R                    = 100;
            long G                    = 100;
            long B                    = 100;
            int N                     = 4;
            long expected__           = 30;

            return verifyCase(casenum__, expected__, new FoxPaintingBalls().theMax(R, G, B, N));
        }
        case 5: {
            long R                    = 19330428391852493L;
            long G                    = 48815737582834113L;
            long B                    = 11451481019198930L;
            int N                     = 3456;
            long expected__           = 5750952686L;

            return verifyCase(casenum__, expected__, new FoxPaintingBalls().theMax(R, G, B, N));
        }
        case 6: {
            long R                    = 1;
            long G                    = 1;
            long B                    = 1;
            int N                     = 1;
            long expected__           = 3;

            return verifyCase(casenum__, expected__, new FoxPaintingBalls().theMax(R, G, B, N));
        }

        // custom cases

/*      case 7: {
            long R                    = ;
            long G                    = ;
            long B                    = ;
            int N                     = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new FoxPaintingBalls().theMax(R, G, B, N));
        }*/
/*      case 8: {
            long R                    = ;
            long G                    = ;
            long B                    = ;
            int N                     = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new FoxPaintingBalls().theMax(R, G, B, N));
        }*/
/*      case 9: {
            long R                    = ;
            long G                    = ;
            long B                    = ;
            int N                     = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new FoxPaintingBalls().theMax(R, G, B, N));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
