import java.math.*;
import java.util.*;

public class UniformBoard {
    public int getBoard(String[] board, int limit) {
        int n = board.length;
        int m = board[0].length();
        int[][][] sum = new int[3][n + 1][m + 1];
        for (int _ = 0; _ < 3; ++ _) {
            for (int i = n - 1; i >= 0; -- i) {
                for (int j = m - 1; j >= 0; -- j) {
                    sum[_][i][j] = sum[_][i + 1][j] + sum[_][i][j + 1] - sum[_][i + 1][j + 1];
                    if (board[i].charAt(j) == 'A' && _ == 0) {
                        sum[_][i][j] ++;
                    }
                    if (board[i].charAt(j) == 'P' && _ == 1) {
                        sum[_][i][j] ++;
                    }
                    if (board[i].charAt(j) == '.' && _ == 2) {
                        sum[_][i][j] ++;
                    }
                }
            }
        }
        int result = 0;
        for (int x0 = 0; x0 < n; ++ x0) {
            for (int x1 = x0 + 1; x1 <= n; ++ x1) {
                for (int y0 = 0; y0 < m; ++ y0) {
                    for (int y1 = y0 + 1; y1 <= m; ++ y1) {
                        int area = (x1 -x0) * (y1 - y0);
                        if (area <= sum[0][0][0]) {
                            int cost = 0;
                            cost += get(sum[1], x0, x1, y0, y1) * 2;
                            cost += get(sum[2], x0, x1, y0, y1);
                            if ((sum[2][0][0] > 0 && cost <= limit) || cost == 0) {
                                result = Math.max(result, area);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    int get(int[][] sum, int x0, int x1, int y0, int y1) {
        return sum[x0][y0] - sum[x0][y1] - sum[x1][y0] + sum[x1][y1];
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            UniformBoardHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                UniformBoardHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class UniformBoardHarness {
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
            String[] board            = {"AP",
 ".A"};
            int K                     = 0;
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new UniformBoard().getBoard(board, K));
        }
        case 1: {
            String[] board            = {"AP",
 ".A"};
            int K                     = 1;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new UniformBoard().getBoard(board, K));
        }
        case 2: {
            String[] board            = {"PPP",
 "APA",
 "A.P"};
            int K                     = 2;
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new UniformBoard().getBoard(board, K));
        }
        case 3: {
            String[] board            = {"AAA",
 "PPP",
 "AAA"};
            int K                     = 10;
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new UniformBoard().getBoard(board, K));
        }
        case 4: {
            String[] board            = {"."};
            int K                     = 1000;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new UniformBoard().getBoard(board, K));
        }
        case 5: {
            String[] board            = {"PPAAPA..AP",
 "PPA.APAP..",
 "..P.AA.PPP",
 "P.P..APAA.",
 "P.P..P.APA",
 "PPA..AP.AA",
 "APP..AAPAA",
 "P.P.AP...P",
 ".P.A.PAPPA",
 "..PAPAP..P"};
            int K                     = 10;
            int expected__            = 15;

            return verifyCase(casenum__, expected__, new UniformBoard().getBoard(board, K));
        }

        // custom cases

/*      case 6: {
            String[] board            = ;
            int K                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new UniformBoard().getBoard(board, K));
        }*/
/*      case 7: {
            String[] board            = ;
            int K                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new UniformBoard().getBoard(board, K));
        }*/
/*      case 8: {
            String[] board            = ;
            int K                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new UniformBoard().getBoard(board, K));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
