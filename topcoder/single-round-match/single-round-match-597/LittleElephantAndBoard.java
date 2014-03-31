import java.math.*;
import java.util.*;

public class LittleElephantAndBoard {
    public int getNumber(int m, int r, int g, int b) {
        int sum = (r + g + b) / 2;
        return (solve(sum - r, sum - g, sum - b) * 2) % MOD;
    }

    static int MOD = (int)1e9 + 7;

    int inverse(int a) {
        return a == 1 ? 1 : (int)((long)(MOD - MOD / a) * inverse(MOD % a) % MOD);
    }

    int[] factorial, inversedFactorial;

    int solve(int r, int g, int b) {
        int n = r + g + b;
        factorial = new int[n + 1];
        inversedFactorial = new int[n + 1];
        factorial[0] = inversedFactorial[0] = 1;
        for (int i = 1; i <= n; ++ i) {
            factorial[i] = (int)((long)factorial[i - 1] * i % MOD);
            inversedFactorial[i] = inverse(factorial[i]);
        }
        int result = 0;
        for (int change = 0; change < r + g; ++ change) {
            int ways = 0;
            ways = add(ways, (long)partition(r, change / 2 + 1) * partition(g, (change + 1) / 2) % MOD);
            ways = add(ways, (long)partition(g, change / 2 + 1) * partition(r, (change + 1) / 2) % MOD);
            int unchanged = r + g - 1 - change;
            if (ways != 0 && unchanged <= b) {
                result = add(result, (long)ways * binom(change + 2, b - unchanged) % MOD);
            }
        }
        return result;
    }

    int add(int x, long a) {
        x += (int)a;
        if (x >= MOD) {
            x -= MOD;
        }
        return x;
    }

    int binom(int n, int k) {
        return (int)((long)factorial[n] * inversedFactorial[k] % MOD * inversedFactorial[n - k] % MOD);
    }

    int partition(int n, int k) {
        if (n < k) {
            return 0;
        }
        if (k == 0) {
            return n == 0 ? 1 : 0;
        }
        return binom(n - 1, k - 1);
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            LittleElephantAndBoardHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                LittleElephantAndBoardHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class LittleElephantAndBoardHarness {
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
            int M                     = 2;
            int R                     = 2;
            int G                     = 1;
            int B                     = 1;
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new LittleElephantAndBoard().getNumber(M, R, G, B));
        }
        case 1: {
            int M                     = 2;
            int R                     = 2;
            int G                     = 2;
            int B                     = 0;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new LittleElephantAndBoard().getNumber(M, R, G, B));
        }
        case 2: {
            int M                     = 10;
            int R                     = 7;
            int G                     = 7;
            int B                     = 6;
            int expected__            = 496;

            return verifyCase(casenum__, expected__, new LittleElephantAndBoard().getNumber(M, R, G, B));
        }
        case 3: {
            int M                     = 474;
            int R                     = 250;
            int G                     = 300;
            int B                     = 398;
            int expected__            = 969878317;

            return verifyCase(casenum__, expected__, new LittleElephantAndBoard().getNumber(M, R, G, B));
        }

        // custom cases

/*      case 4: {
            int M                     = ;
            int R                     = ;
            int G                     = ;
            int B                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new LittleElephantAndBoard().getNumber(M, R, G, B));
        }*/
/*      case 5: {
            int M                     = ;
            int R                     = ;
            int G                     = ;
            int B                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new LittleElephantAndBoard().getNumber(M, R, G, B));
        }*/
/*      case 6: {
            int M                     = ;
            int R                     = ;
            int G                     = ;
            int B                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new LittleElephantAndBoard().getNumber(M, R, G, B));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
