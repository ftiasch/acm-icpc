import java.math.*;
import java.util.*;

public class StringPath {
    final static int MOD = (int)1e9 + 9;

    public int countBoards(int n, int m, String a, String b) {
        int[] ways = new int[1 << m + m];
        if (a.charAt(0) == b.charAt(0)) {
            ways[3] = 1;
            ways[0] = 25;
        } else {
            ways[1] = ways[2] = 1;
            ways[0] = 24;
        }
        int all = (1 << m + m) - 1;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                if (i + j > 0) {
                    int[] newWays = new int[1 << m + m];
                    for (int mask = 0; mask < 1 << m + m; ++ mask) {
                        if (ways[mask] > 0) {
                            int newMask = ((mask << 2) | (mask >> m + m - 2)) & all;
                            if (j > 0) {
                                newMask |= mask & 3;
                            }
                            if (a.charAt(i + j) == b.charAt(i + j)) {
                                newWays[newMask] += ways[mask];
                                newWays[newMask] %= MOD;

                                newWays[newMask & ~3] += 25L * ways[mask] % MOD;
                                newWays[newMask & ~3] %= MOD;
                            } else {
                                newWays[newMask & ~1] += ways[mask];
                                newWays[newMask & ~1] %= MOD;

                                newWays[newMask & ~2] += ways[mask];
                                newWays[newMask & ~2] %= MOD;

                                newWays[newMask & ~3] += 24L * ways[mask] % MOD;
                                newWays[newMask & ~3] %= MOD;
                            }
                        }
                    }
                    ways = newWays;
                }
            }
        }
        int answer = 0;
        for (int mask = 0; mask < 1 << m + m; ++ mask) {
            if ((mask & 3) == 3) {
                answer += ways[mask];
                answer %= MOD;
            }
        }
        return answer;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            StringPathHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                StringPathHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class StringPathHarness {
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
            int n                     = 2;
            int m                     = 2;
            String A                  = "ABC";
            String B                  = "ADC";
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new StringPath().countBoards(n, m, A, B));
        }
        case 1: {
            int n                     = 2;
            int m                     = 2;
            String A                  = "ABC";
            String B                  = "ABD";
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new StringPath().countBoards(n, m, A, B));
        }
        case 2: {
            int n                     = 3;
            int m                     = 4;
            String A                  = "ABCDDE";
            String B                  = "ACCBDE";
            int expected__            = 1899302;

            return verifyCase(casenum__, expected__, new StringPath().countBoards(n, m, A, B));
        }
        case 3: {
            int n                     = 8;
            int m                     = 8;
            String A                  = "ZZZZZZZZZZZZZZZ";
            String B                  = "ZABCDEFGHIJKLMZ";
            int expected__            = 120390576;

            return verifyCase(casenum__, expected__, new StringPath().countBoards(n, m, A, B));
        }

        // custom cases

/*      case 4: {
            int n                     = ;
            int m                     = ;
            String A                  = ;
            String B                  = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new StringPath().countBoards(n, m, A, B));
        }*/
/*      case 5: {
            int n                     = ;
            int m                     = ;
            String A                  = ;
            String B                  = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new StringPath().countBoards(n, m, A, B));
        }*/
/*      case 6: {
            int n                     = ;
            int m                     = ;
            String A                  = ;
            String B                  = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new StringPath().countBoards(n, m, A, B));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
