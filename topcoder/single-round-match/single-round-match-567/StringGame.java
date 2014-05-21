import java.math.*;
import java.util.*;

public class StringGame {
    final static int M = 26;

    boolean check(int[][] count, boolean[] active, int i, int c) {
        int n = count.length;
        for (int j = 0; j < n; ++ j) {
            if (active[j] && count[j][c] > count[i][c]) {
                return false;
            }
        }
        return true;
    }

    public int[] getWinningStrings(String[] strings) {
        int n = strings.length;
        int[][] count = new int[n][M];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < strings[i].length(); ++ j) {
                count[i][strings[i].charAt(j) - 'a'] ++;
            }
        }
        boolean[] valid = new boolean[n];
        int answer = 0;
        for (int i = 0; i < n; ++ i) {
            boolean[] used = new boolean[M];
            boolean[] active = new boolean[n];
            for (int j = 0; j < n; ++ j) {
                active[j] = i != j;
            }
            valid[i] = true;
            for (int _ = 0; _ < M && valid[i]; ++ _) {
                int j = 0;
                while (j < M && (used[j] || !check(count, active, i, j))) {
                    j ++;
                }
                if (j == M) {
                    valid[i] = false;
                } else {
                    used[j] = true;
                    for (int k = 0; k < n; ++ k) {
                        active[k] &= count[k][j] >= count[i][j];
                    }
                }
            }
            for (int j = 0; j < n; ++ j) {
                valid[i] &= !active[j];
            }
            if (valid[i]) {
                answer ++;
            }
        }
        int[] ret = new int[answer];
        answer = 0;
        for (int i = 0; i < n; ++ i) {
            if (valid[i]) {
                ret[answer ++] = i;
            }
        }
        return ret;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            StringGameHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                StringGameHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class StringGameHarness {
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

    static boolean compareOutput(int[] expected, int[] result) { if (expected.length != result.length) return false; for (int i=0; i<expected.length; ++i) if (expected[i] != result[i]) return false; return true; }

    static String formatResult(int[] res) {
        String ret = "";
        ret += "{";
        for (int i=0; i<res.length; ++i) {
            if (i > 0) ret += ",";
            ret += String.format(" %d", res[i]);
        }
        ret += " }";
        return ret;
    }

    static int verifyCase(int casenum, int[] expected, int[] received) {
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
            String[] S                = {"a", "b", "c", "d"};
            int[] expected__          = {0, 1, 2, 3 };

            return verifyCase(casenum__, expected__, new StringGame().getWinningStrings(S));
        }
        case 1: {
            String[] S                = {"aabbcc", "aaabbb", "aaaccc"};
            int[] expected__          = {1, 2 };

            return verifyCase(casenum__, expected__, new StringGame().getWinningStrings(S));
        }
        case 2: {
            String[] S                = {"ab", "ba"};
            int[] expected__          = { };

            return verifyCase(casenum__, expected__, new StringGame().getWinningStrings(S));
        }
        case 3: {
            String[] S                = {"xaocxsss", "oooxsoas", "xaooosss", "xaocssss", "coxaosxx"};
            int[] expected__          = {1, 3, 4 };

            return verifyCase(casenum__, expected__, new StringGame().getWinningStrings(S));
        }

        // custom cases

/*      case 4: {
            String[] S                = ;
            int[] expected__          = ;

            return verifyCase(casenum__, expected__, new StringGame().getWinningStrings(S));
        }*/
/*      case 5: {
            String[] S                = ;
            int[] expected__          = ;

            return verifyCase(casenum__, expected__, new StringGame().getWinningStrings(S));
        }*/
/*      case 6: {
            String[] S                = ;
            int[] expected__          = ;

            return verifyCase(casenum__, expected__, new StringGame().getWinningStrings(S));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
