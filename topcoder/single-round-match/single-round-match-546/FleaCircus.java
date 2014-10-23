import java.math.*;
import java.util.*;

public class FleaCircus {
    static long MOD = (long)1e9 + 9;

    public int countArrangements(String[] afterFourClicks) {
        int[] p4 = parse(afterFourClicks);
        int n = p4.length;
        int[] count = new int[n + 1];
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; ++ i) {
            if (!visited[i]) {
                int l = 0;
                for (int p = i; !visited[p]; p = p4[p]) {
                    l ++;
                    visited[p] = true;
                }
                count[l] ++;
            }
        }
        long[] evenWays = new long[n + 1];
        evenWays[0] = 1;
        for (int i = 4; i <= n; i += 4) {
            evenWays[i] = evenWays[i - 4] * ((i - 1) * (i - 2) * (i - 3)) % MOD;
        }
        long[][] oddWays = new long[n + 1][n + 1];
        oddWays[0][0] = 1;
        for (int i = 1; i <= n; ++ i) {
            for (int j = 1; j <= i; ++ j) {
                oddWays[i][j] = plus(oddWays[i][j], oddWays[i - 1][j - 1]);
                if (i >= 2) {
                    oddWays[i][j] = plus(oddWays[i][j], oddWays[i - 2][j - 1] * (i - 1) % MOD);
                }
                if (i >= 4) {
                    oddWays[i][j] = plus(oddWays[i][j], oddWays[i - 4][j - 1] * ((i - 1) * (i - 2) * (i - 3)) % MOD);
                }
            }
        }
        long result = 1;
        for (int l = 1; l <= n; ++ l) {
            if (l % 2 == 1) {
                long sum = 0;
                for (int j = 0; j <= count[l]; ++ j) {
                    sum = plus(sum, oddWays[count[l]][j] * power(l, count[l] - j) % MOD);
                }
                result = result * sum % MOD;
            } else {
                result = result * evenWays[count[l]] % MOD;
                if (count[l] % 4 == 0) {
                    result = result * power(l, count[l] - count[l] / 4) % MOD;
                }
            }
        }
        return (int)result;
    }

    long plus(long a, long b) {
        a += b;
        if (a >= MOD) {
            a -= MOD;
        }
        return a;
    }

    long power(long a, long n) {
        long result = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                result = result * a % MOD;
            }
            a = a * a % MOD;
            n >>= 1;
        }
        return result;
    }

    int[] parse(String[] parts) {
        StringBuffer buffer = new StringBuffer("");
        for (String part : parts) {
            buffer.append(part);
        }
        StringTokenizer tokenizer = new StringTokenizer(buffer.toString());
        int n = tokenizer.countTokens();
        int[] result = new int[n];
        for (int i = 0; i < n; ++ i) {
            result[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return result;
    }



    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            FleaCircusHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                FleaCircusHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class FleaCircusHarness {
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
            String[] afterFourClicks  = {"1 2 0 3"};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new FleaCircus().countArrangements(afterFourClicks));
        }
        case 1: {
            String[] afterFourClicks  = {"1 2 ", "0 3"};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new FleaCircus().countArrangements(afterFourClicks));
        }
        case 2: {
            String[] afterFourClicks  = {"0 1 2"};
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new FleaCircus().countArrangements(afterFourClicks));
        }
        case 3: {
            String[] afterFourClicks  = {"0 1 2 3 5 4"};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new FleaCircus().countArrangements(afterFourClicks));
        }
        case 4: {
            String[] afterFourClicks  = {"3 6 7 9 8 2 1", "0 5 1 0 4"};
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new FleaCircus().countArrangements(afterFourClicks));
        }
        case 5: {
            String[] afterFourClicks  = {"1 0 7 5 6 3 4 2"};
            int expected__            = 48;

            return verifyCase(casenum__, expected__, new FleaCircus().countArrangements(afterFourClicks));
        }

        // custom cases

/*      case 6: {
            String[] afterFourClicks  = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new FleaCircus().countArrangements(afterFourClicks));
        }*/
/*      case 7: {
            String[] afterFourClicks  = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new FleaCircus().countArrangements(afterFourClicks));
        }*/
/*      case 8: {
            String[] afterFourClicks  = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new FleaCircus().countArrangements(afterFourClicks));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
