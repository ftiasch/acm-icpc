import java.math.*;
import java.util.*;

public class Nine {
    static long MOD = (long)1e9 + 7;

    long add(long a, long b) {
        a += b;
        if (a >= MOD) {
            a -= MOD;
        }
        return a;
    }

    public int count(int n, int[] a) {
        int m = a.length;
        int[] count = new int[1 << n];
        for (int i = 0; i < m; ++ i) {
            count[a[i]] ++;
        }
        long[][] sumWays = new long[m + 1][9];
        sumWays[0][0] = 1;
        for (int i = 0; i < m; ++ i) {
            for (int r = 0; r < 9; ++ r) {
                for (int d = 0; d < 10; ++ d) {
                    int rr = (r + d) % 9;
                    sumWays[i + 1][rr] = add(sumWays[i + 1][rr], sumWays[i][r]);
                }
            }
        }
        int[] power = new int[n + 1];
        power[0] = 1;
        for (int i = 1; i <= n; ++ i) {
            power[i] = power[i - 1] * 9;
        }
        long[] ways = new long[power[n]];
        ways[0] = 1;
        for (int i = 0; i < 1 << n; ++ i) {
            long[] newWays = new long[power[n]];
            for (int mask = 0; mask < power[n]; ++ mask) {
                for (int r = 0; r < 9; ++ r) {
                    int newMask = 0;
                    for (int j = 0; j < n; ++ j) {
                        int number = mask / power[j] % 9;
                        if ((i >> j & 1) == 1) {
                            number = (number + r) % 9;
                        }
                        newMask += power[j] * number;
                    }
                    newWays[newMask] = add(newWays[newMask], (long)ways[mask] * sumWays[count[i]][r] % MOD);
                }
            }
            ways = newWays;
        }
        return (int)ways[0];
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            NineHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                NineHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class NineHarness {
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
            int[] d                   = {1,2};
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new Nine().count(N, d));
        }
        case 1: {
            int N                     = 2;
            int[] d                   = {1,2,3};
            int expected__            = 16;

            return verifyCase(casenum__, expected__, new Nine().count(N, d));
        }
        case 2: {
            int N                     = 1;
            int[] d                   = {0,0,1};
            int expected__            = 200;

            return verifyCase(casenum__, expected__, new Nine().count(N, d));
        }
        case 3: {
            int N                     = 5;
            int[] d                   = {1,3,5,8,24,22,25,21,30,2,4,0,6,7,9,11,14,13,12,15,18,17,16,19,26,29,31,28,27,10,20,23};
            int expected__            = 450877328;

            return verifyCase(casenum__, expected__, new Nine().count(N, d));
        }
        case 4: {
            int N                     = 5;
            int[] d                   = {31,31,31,31,31};
            int expected__            = 11112;

            return verifyCase(casenum__, expected__, new Nine().count(N, d));
        }

        // custom cases

/*      case 5: {
            int N                     = ;
            int[] d                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new Nine().count(N, d));
        }*/
/*      case 6: {
            int N                     = ;
            int[] d                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new Nine().count(N, d));
        }*/
/*      case 7: {
            int N                     = ;
            int[] d                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new Nine().count(N, d));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
