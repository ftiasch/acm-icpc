import java.math.*;
import java.util.*;

public class DropCoins {
    public int getMinimum(String[] board, int need) {
        int n = board.length;
        int m = board[0].length();
        int[][] sum = new int[n + 1][m + 1];
        for (int i = n - 1; i >= 0; -- i) {
            for (int j = m - 1; j >= 0; -- j) {
                sum[i][j] = sum[i + 1][j] + sum[i][j + 1] - sum[i + 1][j + 1] + (board[i].charAt(j) == 'o' ? 1 : 0);
            }
        }
        int answer = Integer.MAX_VALUE;
        for (int up = 0; up < n; ++ up) {
            for (int down = 0; up + down < n; ++ down) {
                for (int left = 0; left < m; ++ left) {
                    for (int right = 0; left + right < m; ++ right) {
                        int count = sum[up][left] - sum[n - down][left] - sum[up][m - right] + sum[n - down][m - right];
                        if (count == need) {
                            answer = Math.min(answer, up + down + Math.min(up, down) + left + right + Math.min(left, right));
                        }
                    }
                }
            }
        }
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            DropCoinsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                DropCoinsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class DropCoinsHarness {
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
            String[] board            = {".o.."
,"oooo"
,"..o."}
;
            int K                     = 3;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new DropCoins().getMinimum(board, K));
        }
        case 1: {
            String[] board            = {".....o"
,"......"
,"oooooo"
,"oooooo"
,"......"
,"o....."}
;
            int K                     = 12;
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new DropCoins().getMinimum(board, K));
        }
        case 2: {
            String[] board            = {"...."
,".oo."
,".oo."
,"...."}
;
            int K                     = 3;
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new DropCoins().getMinimum(board, K));
        }
        case 3: {
            String[] board            = {"......."
,"..ooo.."
,"ooooooo"
,".oo.oo."
,"oo...oo"}
;
            int K                     = 12;
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new DropCoins().getMinimum(board, K));
        }
        case 4: {
            String[] board            = {"................."
,".ooooooo...oooo.."
,".ooooooo..oooooo."
,".oo.......oo..oo."
,".oo.......oo..oo."
,".ooooo.....oooo.."
,".ooooooo...oooo.."
,".....ooo..oo..oo."
,"......oo..oo..oo."
,".ooooooo..oooooo."
,".oooooo....oooo.."
,"................."}
;
            int K                     = 58;
            int expected__            = 6;

            return verifyCase(casenum__, expected__, new DropCoins().getMinimum(board, K));
        }

        // custom cases

/*      case 5: {
            String[] board            = ;
            int K                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new DropCoins().getMinimum(board, K));
        }*/
/*      case 6: {
            String[] board            = ;
            int K                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new DropCoins().getMinimum(board, K));
        }*/
/*      case 7: {
            String[] board            = ;
            int K                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new DropCoins().getMinimum(board, K));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
