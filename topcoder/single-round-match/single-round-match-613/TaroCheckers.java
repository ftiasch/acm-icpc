import java.math.*;
import java.util.*;

public class TaroCheckers {
    static int MOD = (int)1e9 + 7;

    public int getNumber(int[] left, int[] right, int m) {
        int n = left.length;
        int[] begin = new int[m];
        int[] end = new int[m];
        int[] empty = new int[m];
        Arrays.fill(empty, n);
        for (int i = 0; i < n; ++ i) {
            end[left[i] - 1] ++;
            begin[m - right[i]] ++;
            for (int j = 0; j < left[i]; ++ j) {
                empty[j] --;
            }
            for (int j = m - right[i]; j < m; ++ j) {
                empty[j] --;
            }
        }
        int[][] ways = new int[n + 1][n + 1];
        ways[0][0] = 1;
        for (int i = 0; i < m; ++ i) {
            int[][] newWays = new int[n + 1][n + 1];
            for (int b = 0; b <= n; ++ b) {
                for (int e = 0; e <= n; ++ e) {
                    if (ways[b][e] > 0) {
                        int newB = b + begin[i];
                        int newE = e - end[i];
                        if (newE >= 0) {
                            newWays[newB][newE] += (int)((long)ways[b][e] * (empty[i] + 1) % MOD);
                            newWays[newB][newE] %= MOD;
                            if (newB > 0) {
                                newWays[newB - 1][newE] += (int)((long)ways[b][e] * newB % MOD);
                                newWays[newB - 1][newE] %= MOD;
                            }
                        }
                        if (newE + 1 >= 0 && e + 1 <= n) {
                            newWays[newB][newE + 1] += (int)((long)ways[b][e] * (e + 1) % MOD);
                            newWays[newB][newE + 1] %= MOD;
                        }
                    }
                }
            }
            ways = newWays;
        }
        return ways[0][0];
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            TaroCheckersHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                TaroCheckersHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class TaroCheckersHarness {
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
            int[] left                = {1, 2};
            int[] right               = {2, 1};
            int M                     = 4;
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new TaroCheckers().getNumber(left, right, M));
        }
        case 1: {
            int[] left                = {1, 4, 2};
            int[] right               = {2, 3, 1};
            int M                     = 7;
            int expected__            = 6;

            return verifyCase(casenum__, expected__, new TaroCheckers().getNumber(left, right, M));
        }
        case 2: {
            int[] left                = {4, 7, 4};
            int[] right               = {7, 4, 7};
            int M                     = 11;
            int expected__            = 5760;

            return verifyCase(casenum__, expected__, new TaroCheckers().getNumber(left, right, M));
        }
        case 3: {
            int[] left                = {10, 25, 100, 74};
            int[] right               = {100, 47, 27, 100};
            int M                     = 200;
            int expected__            = 796178974;

            return verifyCase(casenum__, expected__, new TaroCheckers().getNumber(left, right, M));
        }
        case 4: {
            int[] left                = {21, 99, 87, 12, 138, 16, 78, 36, 98, 40, 57, 10, 61, 100, 8, 110, 96, 9, 69, 110, 14, 71};
            int[] right               = {83, 8, 25, 169, 1, 89, 109, 89, 19, 112, 39, 112, 87, 66, 116, 16, 41, 97, 52, 70, 111, 23};
            int M                     = 190;
            int expected__            = 235017573;

            return verifyCase(casenum__, expected__, new TaroCheckers().getNumber(left, right, M));
        }
        case 5: {
            int[] left                = {3, 37, 26, 50, 8, 3, 38, 15, 58, 47, 35, 8, 27, 22, 5};
            int[] right               = {19, 26, 62, 15, 84, 13, 6, 46, 22, 53, 23, 8, 29, 55, 6};
            int M                     = 102;
            int expected__            = 528024858;

            return verifyCase(casenum__, expected__, new TaroCheckers().getNumber(left, right, M));
        }

        // custom cases

/*      case 6: {
            int[] left                = ;
            int[] right               = ;
            int M                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TaroCheckers().getNumber(left, right, M));
        }*/
/*      case 7: {
            int[] left                = ;
            int[] right               = ;
            int M                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TaroCheckers().getNumber(left, right, M));
        }*/
/*      case 8: {
            int[] left                = ;
            int[] right               = ;
            int M                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TaroCheckers().getNumber(left, right, M));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
