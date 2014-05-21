import java.math.*;
import java.util.*;

public class LISNumber {
    final static int MOD = (int)1e9 + 7;

    public int count(int[] cards, int part) {
        long[][] binom = new long[2000][2000];
        for (int i = 0; i < 2000; ++ i) {
            binom[i][0] = 1;
            for (int j = 1; j <= i; ++ j) {
                binom[i][j] = (binom[i - 1][j - 1] + binom[i - 1][j]) % MOD;
            }
        }
        int n = cards.length;
        long[] ways = new long[part + 1];
        boolean first = true;
        int length = 0;
        for (int number : cards) {
            if (number == 0) {
                continue;
            }
            if (first) {
                first = false;
                if (number <= part) {
                    ways[number] = 1;
                }
            } else {
                long[] newWays = new long[part + 1];
                for (int p = 0; p <= part; ++ p) {
                    if (ways[p] == 0) {
                        continue;
                    }
                    // +0 : p
                    // +1 : lenght + 1 - p
                    for (int x = 0; x <= number && x <= p; ++ x) {
                        if (p + number - x <= part) {
                            newWays[p + number - x] += ways[p] * binom[p][x] % MOD * binom[length + 1 - p + x + number - x - 1][number - x] % MOD;
                            newWays[p + number - x] %= MOD;
                        }
                    }
                }
                ways = newWays;
            }
            length += number;
        }
        return (int)ways[part];
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            LISNumberHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                LISNumberHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class LISNumberHarness {
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
            int[] cardsnum            = {1, 1, 1};
            int K                     = 2;
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new LISNumber().count(cardsnum, K));
        }
        case 1: {
            int[] cardsnum            = {2};
            int K                     = 1;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new LISNumber().count(cardsnum, K));
        }
        case 2: {
            int[] cardsnum            = {36, 36, 36, 36, 36};
            int K                     = 36;
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new LISNumber().count(cardsnum, K));
        }
        case 3: {
            int[] cardsnum            = {3, 2, 11, 5, 7};
            int K                     = 20;
            int expected__            = 474640725;

            return verifyCase(casenum__, expected__, new LISNumber().count(cardsnum, K));
        }
        case 4: {
            int[] cardsnum            = {31, 4, 15, 9, 26, 5, 35, 8, 9, 7, 9, 32, 3, 8, 4, 6, 26};
            int K                     = 58;
            int expected__            = 12133719;

            return verifyCase(casenum__, expected__, new LISNumber().count(cardsnum, K));
        }
        case 5: {
            int[] cardsnum            = {27, 18, 28, 18, 28, 4, 5, 9, 4, 5, 23, 5, 36, 28, 7, 4, 7, 13, 5, 26, 6, 24, 9, 7, 7, 5, 7, 24, 7, 9, 36, 9, 9, 9, 5, 9};
            int K                     = 116;
            int expected__            = 516440918;

            return verifyCase(casenum__, expected__, new LISNumber().count(cardsnum, K));
        }

        // custom cases

/*      case 6: {
            int[] cardsnum            = ;
            int K                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new LISNumber().count(cardsnum, K));
        }*/
/*      case 7: {
            int[] cardsnum            = ;
            int K                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new LISNumber().count(cardsnum, K));
        }*/
/*      case 8: {
            int[] cardsnum            = ;
            int K                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new LISNumber().count(cardsnum, K));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
