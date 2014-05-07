import java.math.*;
import java.util.*;

public class AlienAndSetDiv1 {
    static int MOD = (int)1e9 + 7;

    int add(int x, int a) {
        x += a;
        if (x >= MOD) {
            x -= MOD;
        }
        return x;
    }

    public int getNumber(int n, int m) {
        m --;
        int[] popcount = new int[1 << m];
        for (int mask = 1; mask < 1 << m; ++ mask) {
            popcount[mask] = popcount[mask >> 1] + (mask & 1);
        }
        int[][][] ways = new int[n + 1][n + 1][1 << m];
        ways[0][0][0] = 1;
        for (int a = 0; a <= n; ++ a) {
            for (int b = 0; b <= n; ++ b) {
                for (int mask = 0; mask < 1 << m; ++ mask) {
                    if (ways[a][b][mask] > 0) {
                        if (a < n) {
                            if (a >= b || a < b - popcount[mask]) {
                                ways[a + 1][b][mask >> 1] = add(ways[a + 1][b][mask >> 1], ways[a][b][mask]);
                            }
                        }
                        if (b < n) {
                            if (b >= a || b < a - m + popcount[mask]) {
                                ways[a][b + 1][(1 << m | mask) >> 1] = add(ways[a][b + 1][(1 << m | mask) >> 1], ways[a][b][mask]);
                            }
                        }
                    }
                }
            }
        }
        int result = 0;
        for (int mask = 0; mask < 1 << m; ++ mask) {
            result = add(result, ways[n][n][mask]);
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            AlienAndSetDiv1Harness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                AlienAndSetDiv1Harness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class AlienAndSetDiv1Harness {
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
            int K                     = 2;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new AlienAndSetDiv1().getNumber(N, K));
        }
        case 1: {
            int N                     = 3;
            int K                     = 1;
            int expected__            = 20;

            return verifyCase(casenum__, expected__, new AlienAndSetDiv1().getNumber(N, K));
        }
        case 2: {
            int N                     = 4;
            int K                     = 2;
            int expected__            = 14;

            return verifyCase(casenum__, expected__, new AlienAndSetDiv1().getNumber(N, K));
        }
        case 3: {
            int N                     = 10;
            int K                     = 7;
            int expected__            = 40;

            return verifyCase(casenum__, expected__, new AlienAndSetDiv1().getNumber(N, K));
        }

        // custom cases

/*      case 4: {
            int N                     = ;
            int K                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new AlienAndSetDiv1().getNumber(N, K));
        }*/
/*      case 5: {
            int N                     = ;
            int K                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new AlienAndSetDiv1().getNumber(N, K));
        }*/
/*      case 6: {
            int N                     = ;
            int K                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new AlienAndSetDiv1().getNumber(N, K));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
