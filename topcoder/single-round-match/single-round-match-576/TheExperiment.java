import java.math.*;
import java.util.*;

public class TheExperiment {
    final static int MOD = (int)1e9 + 9;

    String concate(String[] strings) {
        StringBuffer buffer = new StringBuffer("");
        for (String string : strings) {
            buffer.append(string);
        }
        return buffer.toString();
    }

    int connect(int t, boolean b) {
        if (t == 2 || b) {
            return 2;
        }
        return 1;
    }

    public int countPlacements(String[] intensities, int m, int l, int a, int b) {
        String intensity = concate(intensities);
        int n = intensity.length();
        int[] sum = new int[n + 1];
        for (int i = n - 1; i >= 0; -- i) {
            sum[i] = sum[i + 1] + (intensity.charAt(i) - '0');
        }
        int[][][] ways = new int[n + 1][m + 1][3];
        ways[0][0][0] = 1;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j <= m; ++ j) {
                for (int t = 0; t < 3; ++ t) {
                    if (t != 1) {
                        ways[i + 1][j][0] += ways[i][j][t];
                        ways[i + 1][j][0] %= MOD;
                    }
                    for (int x = 1; x <= l && i + x <= n; ++ x) {
                        int s = sum[i] - sum[i + x];
                        if (a <= s && s <= b && j + 1 <= m) {
                            ways[i + x][j + 1][connect(t, x == l)] += ways[i][j][t];
                            ways[i + x][j + 1][connect(t, x == l)] %= MOD;
                        }
                    }
                }
            }
        }
        return (ways[n][m][0] + ways[n][m][2]) % MOD;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            TheExperimentHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                TheExperimentHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class TheExperimentHarness {
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
            String[] intensity        = {"341156"};
            int M                     = 3;
            int L                     = 3;
            int A                     = 6;
            int B                     = 10;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new TheExperiment().countPlacements(intensity, M, L, A, B));
        }
        case 1: {
            String[] intensity        = {"999112999"};
            int M                     = 2;
            int L                     = 4;
            int A                     = 21;
            int B                     = 30;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new TheExperiment().countPlacements(intensity, M, L, A, B));
        }
        case 2: {
            String[] intensity        = {"111"};
            int M                     = 2;
            int L                     = 2;
            int A                     = 2;
            int B                     = 3;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new TheExperiment().countPlacements(intensity, M, L, A, B));
        }
        case 3: {
            String[] intensity        = {"59059", "110", "1132230231"};
            int M                     = 1;
            int L                     = 5;
            int A                     = 10;
            int B                     = 20;
            int expected__            = 6;

            return verifyCase(casenum__, expected__, new TheExperiment().countPlacements(intensity, M, L, A, B));
        }
        case 4: {
            String[] intensity        = {"111111111111111111111111", "111111111111111111111111111", "11111111111111111111111"};
            int M                     = 12;
            int L                     = 8;
            int A                     = 4;
            int B                     = 2700;
            int expected__            = 418629948;

            return verifyCase(casenum__, expected__, new TheExperiment().countPlacements(intensity, M, L, A, B));
        }

        // custom cases

/*      case 5: {
            String[] intensity        = ;
            int M                     = ;
            int L                     = ;
            int A                     = ;
            int B                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TheExperiment().countPlacements(intensity, M, L, A, B));
        }*/
/*      case 6: {
            String[] intensity        = ;
            int M                     = ;
            int L                     = ;
            int A                     = ;
            int B                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TheExperiment().countPlacements(intensity, M, L, A, B));
        }*/
/*      case 7: {
            String[] intensity        = ;
            int M                     = ;
            int L                     = ;
            int A                     = ;
            int B                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TheExperiment().countPlacements(intensity, M, L, A, B));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
