import java.io.*;
import java.util.*;

public class FoxAndGemstone {
    static int m = 16;

    public String isPossible(String[] bags) {
        int n = bags.length;
        int[][] count = new int[n][m];
        for (int i = 0; i < n; ++ i) {
            String bag = bags[i];
            for (int j = 0; j < bag.length(); ++ j) {
                count[i][bag.charAt(j) - 'A'] ++;
            }
        }
        n = 0;
        for (int i = 0; i < count.length; ++ i) {
            int j = 0;
            while (j < n && !Arrays.equals(count[i], count[j])) {
                j ++;
            }
            if (j == n) {
                count[n ++] = count[i];
            }
        }
        count = Arrays.copyOf(count, n);
        long result = 1;
        for (int i = 1; i <= m; ++ i) {
            result *= i;
        }
        int[][] sum = new int[n][1 << m];
        for (int i = 0; i < n; ++ i) {
            for (int mask = 0; mask < 1 << m; ++ mask) {
                for (int j = 0; j < m; ++ j) {
                    if ((mask >> j & 1) > 0) {
                        sum[i][mask] += count[i][j];
                    }
                }
            }
        }
        int[] maximum = new int[1 << m];
        for (int mask = 0; mask < 1 << m; ++ mask) {
            for (int i = 0; i < n; ++ i) {
                maximum[mask] = Math.max(maximum[mask], sum[i][mask]);
            }
        }
        for (int best = 0; best < n; ++ best) {
            long[] ways = new long[1 << m];
            ways[0] = 1;
            for (int mask = 1; mask < 1 << m; ++ mask) {
                if (sum[best][mask] == maximum[mask]) {
                    for (int i = 0; i < m; ++ i) {
                        if ((mask >> i & 1) > 0) {
                            ways[mask] += ways[mask ^ 1 << i];
                        }
                    }
                }
            }
            result -= ways[(1 << m) - 1];
        }
        return result > 0 ? "Impossible" : "Possible";
    }

    protected static void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            FoxAndGemstoneHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                FoxAndGemstoneHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class FoxAndGemstoneHarness {
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

    static boolean compareOutput(String expected, String result) { return expected.equals(result); }
    static String formatResult(String res) {
        return String.format("\"%s\"", res);
    }

    static int verifyCase(int casenum, String expected, String received) {
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
            String[] bags             = {"AB", "AC"};
            String expected__         = "Possible";

            return verifyCase(casenum__, expected__, new FoxAndGemstone().isPossible(bags));
        }
        case 1: {
            String[] bags             = {"A", "BC"};
            String expected__         = "Impossible";

            return verifyCase(casenum__, expected__, new FoxAndGemstone().isPossible(bags));
        }
        case 2: {
            String[] bags             = {"A", "B", "C", "AB", "AC", "BC", "ABC"};
            String expected__         = "Possible";

            return verifyCase(casenum__, expected__, new FoxAndGemstone().isPossible(bags));
        }
        case 3: {
            String[] bags             = {"AB","AC","AD","BC","BD","CD"};
            String expected__         = "Possible";

            return verifyCase(casenum__, expected__, new FoxAndGemstone().isPossible(bags));
        }
        case 4: {
            String[] bags             = {"AB", "CD"};
            String expected__         = "Impossible";

            return verifyCase(casenum__, expected__, new FoxAndGemstone().isPossible(bags));
        }
        case 5: {
            String[] bags             = {"A", "A", "A"};
            String expected__         = "Possible";

            return verifyCase(casenum__, expected__, new FoxAndGemstone().isPossible(bags));
        }

        // custom cases

/*      case 6: {
            String[] bags             = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new FoxAndGemstone().isPossible(bags));
        }*/
/*      case 7: {
            String[] bags             = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new FoxAndGemstone().isPossible(bags));
        }*/
/*      case 8: {
            String[] bags             = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new FoxAndGemstone().isPossible(bags));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
