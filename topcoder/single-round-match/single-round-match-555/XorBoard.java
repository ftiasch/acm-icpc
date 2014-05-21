import java.math.*;
import java.util.*;

public class XorBoard {
    final static int N = 3000;
    final static int MOD = 555555555;

    public int count(int h, int w, int r, int c, int s) {
        int[][] binom = new int[N + 1][N + 1];
        for (int i = 0; i <= N; ++ i) {
            binom[i][0] = 1;
            for (int j = 1; j <= i; ++ j) {
                binom[i][j] = (binom[i - 1][j - 1] + binom[i - 1][j]) % MOD;
            }
        }
        int result = 0;
        for (int i = 0; i <= r; ++ i) {
            for (int j = 0; j <= c; ++ j) {
                if ((r - i) % 2 == 0 && (c - j) % 2 == 0 && i * w + j * h - 2 * i * j == s) {
                    result = (int)((result + (long)binom[h][i] * binom[w][j] % MOD
                            * binom[h + (r - i) / 2 - 1][h - 1] % MOD
                            * binom[w + (c - j) / 2 - 1][w - 1] % MOD) % MOD);
                }
            }
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            XorBoardHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                XorBoardHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class XorBoardHarness {
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
            int H                     = 2;
            int W                     = 2;
            int Rcount                = 2;
            int Ccount                = 2;
            int S                     = 4;
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new XorBoard().count(H, W, Rcount, Ccount, S));
        }
        case 1: {
            int H                     = 2;
            int W                     = 2;
            int Rcount                = 0;
            int Ccount                = 0;
            int S                     = 1;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new XorBoard().count(H, W, Rcount, Ccount, S));
        }
        case 2: {
            int H                     = 10;
            int W                     = 20;
            int Rcount                = 50;
            int Ccount                = 40;
            int S                     = 200;
            int expected__            = 333759825;

            return verifyCase(casenum__, expected__, new XorBoard().count(H, W, Rcount, Ccount, S));
        }
        case 3: {
            int H                     = 1200;
            int W                     = 1000;
            int Rcount                = 800;
            int Ccount                = 600;
            int S                     = 4000;
            int expected__            = 96859710;

            return verifyCase(casenum__, expected__, new XorBoard().count(H, W, Rcount, Ccount, S));
        }
        case 4: {
            int H                     = 555;
            int W                     = 555;
            int Rcount                = 555;
            int Ccount                = 555;
            int S                     = 5550;
            int expected__            = 549361755;

            return verifyCase(casenum__, expected__, new XorBoard().count(H, W, Rcount, Ccount, S));
        }

        // custom cases

/*      case 5: {
            int H                     = ;
            int W                     = ;
            int Rcount                = ;
            int Ccount                = ;
            int S                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new XorBoard().count(H, W, Rcount, Ccount, S));
        }*/
/*      case 6: {
            int H                     = ;
            int W                     = ;
            int Rcount                = ;
            int Ccount                = ;
            int S                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new XorBoard().count(H, W, Rcount, Ccount, S));
        }*/
/*      case 7: {
            int H                     = ;
            int W                     = ;
            int Rcount                = ;
            int Ccount                = ;
            int S                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new XorBoard().count(H, W, Rcount, Ccount, S));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
