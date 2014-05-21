import java.math.*;
import java.util.*;

public class DefectiveAddition {
    final static int M = 30;
    final static int MOD = (int)1e9 + 7;

    public int count(int[] cards, int sum) {
        int n = cards.length;
        int[][] suffix = new int[n][M + 1];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < M; ++ j) {
                suffix[i][j] = cards[i] & (1 << j) - 1;
            }
        }
        int[] power = new int[M + 1];
        power[0] = 1;
        for (int i = 0; i < M; ++ i) {
            power[i + 1] = power[i] * 2 % MOD;
        }
        int result = 0;
        for (int b = 0; b < M; ++ b) {
            int mask = ~((1 << b + 1) - 1);
            int prefix = sum & mask;
            for (int i = 0; i < n; ++ i) {
                prefix ^= cards[i] & mask;
            }
            if (prefix == 0) {
                int[][] ways = new int[2][2];
                ways[sum >> b & 1][0] = 1;
                for (int i = 0; i < n; ++ i) {
                    int[][] newWays = new int[2][2];
                    for (int s = 0; s < 2; ++ s) {
                        for (int f = 0; f < 2; ++ f) {
                            if ((cards[i] >> b & 1) == 0) {
                                newWays[s][f] += (int)((long)ways[s][f] * (suffix[i][b] + 1) % MOD);
                                newWays[s][f] %= MOD;
                            } else {
                                newWays[s ^ 1][f] += (int)((long)ways[s][f] * (suffix[i][b] + 1) % MOD);
                                newWays[s ^ 1][f] %= MOD;
                                if (f == 0) {
                                    newWays[s][1] += ways[s][f];
                                } else {
                                    newWays[s][1] += (int)((long)ways[s][f] * power[b] % MOD);
                                }
                                newWays[s][1] %= MOD;
                            }
                        }
                    }
                    ways = newWays;
                }
                result += ways[0][1];
                result %= MOD;
            }
        }
        if (true) {
            int s = 0;
            for (int i = 0; i < n; ++ i) {
                s ^= cards[i];
            }
            if (s == sum) {
                result += 1;
                result %= MOD;
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
            DefectiveAdditionHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                DefectiveAdditionHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class DefectiveAdditionHarness {
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
            int[] cards               = {2,3};
            int n                     = 2;
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new DefectiveAddition().count(cards, n));
        }
        case 1: {
            int[] cards               = {1,2,3};
            int n                     = 1;
            int expected__            = 6;

            return verifyCase(casenum__, expected__, new DefectiveAddition().count(cards, n));
        }
        case 2: {
            int[] cards               = {4, 5, 7, 11};
            int n                     = 6;
            int expected__            = 240;

            return verifyCase(casenum__, expected__, new DefectiveAddition().count(cards, n));
        }
        case 3: {
            int[] cards               = {1,2,3,4,5,6,7,8,9,10};
            int n                     = 15;
            int expected__            = 1965600;

            return verifyCase(casenum__, expected__, new DefectiveAddition().count(cards, n));
        }
        case 4: {
            int[] cards               = {1,2,3,4,5,6,7,8,9,10};
            int n                     = 16;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new DefectiveAddition().count(cards, n));
        }
        case 5: {
            int[] cards               = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
            int n                     = 1;
            int expected__            = 949480669;

            return verifyCase(casenum__, expected__, new DefectiveAddition().count(cards, n));
        }

        // custom cases

/*      case 6: {
            int[] cards               = ;
            int n                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new DefectiveAddition().count(cards, n));
        }*/
/*      case 7: {
            int[] cards               = ;
            int n                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new DefectiveAddition().count(cards, n));
        }*/
/*      case 8: {
            int[] cards               = ;
            int n                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new DefectiveAddition().count(cards, n));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
