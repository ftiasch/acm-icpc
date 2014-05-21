import java.util.*;
import java.math.*;

public class YetAnotherBoardGame {
    int n, m, all;
    int[] board;

    int solve(int type, int first) {
        int answer = 0;
        int pre = 0;
        int now = first;
        for (int i = 0; i < n; ++ i) {
            if ((now & type) == now || (now & (all ^ type)) == now) {
                answer += Integer.bitCount(now);
                int row = board[i] ^ pre ^ (now >> 1) ^ (now << 1 & all) ^ (now & type);
                pre = now;
                now = row;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        if (now != 0) {
            return Integer.MAX_VALUE;
        }
        return answer;
    }

    public int minimumMoves(String[] boardString) {
        n = boardString.length;
        m = boardString[0].length();
        board = new int[n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                if (boardString[i].charAt(j) == 'W') {
                    board[i] |= 1 << j;
                }
            }
        }
        int answer = Integer.MAX_VALUE;
        all = (1 << m) - 1;
        for (int type = 0; type < 1 << m; ++ type) {
            answer = Math.min(answer, solve(type, 0));
            for (int first = type; first > 0; first = (first - 1) & type) {
                answer = Math.min(answer, solve(type, first));
            }
            answer = Math.min(answer, solve(type, all ^ type));
            for (int first = all ^ type; first > 0; first = (first - 1) & (all ^ type)) {
                answer = Math.min(answer, solve(type, first));
            }
        }
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            YetAnotherBoardGameHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                YetAnotherBoardGameHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class YetAnotherBoardGameHarness {
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
            String[] board            = {"BBBBBBBBB",
 "BBWBBBBBB",
 "BWWWBBBBB",
 "BBWBBBWBB",
 "BBBBBWBWB",
 "BBBBBBWBB"};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new YetAnotherBoardGame().minimumMoves(board));
        }
        case 1: {
            String[] board            = {"BBW",
 "WWW",
 "BWW"};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new YetAnotherBoardGame().minimumMoves(board));
        }
        case 2: {
            String[] board            = {"WBW",
 "BBW",
 "WBW"};
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new YetAnotherBoardGame().minimumMoves(board));
        }
        case 3: {
            String[] board            = {"BBBB",
 "WBWB",
 "BBBB",
 "BBBB"};
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new YetAnotherBoardGame().minimumMoves(board));
        }
        case 4: {
            String[] board            = {"WWWWWBW",
 "WBWBWBW",
 "BBWBBWW"};
            int expected__            = 7;

            return verifyCase(casenum__, expected__, new YetAnotherBoardGame().minimumMoves(board));
        }
        case 5: {
            String[] board            = {"WWWWWWWWWW",
 "WWWWWWWWWW",
 "WWWWWWWWWW",
 "WWWWWWWWWW",
 "WWWWWWWWWW",
 "WWWWWWWWWW",
 "WWWWWWWWWW",
 "WWWWWWWWWW",
 "WWWWWWWWWW",
 "WWWWWWWWWW"}
;
            int expected__            = 30;

            return verifyCase(casenum__, expected__, new YetAnotherBoardGame().minimumMoves(board));
        }

        // custom cases

/*      case 6: {
            String[] board            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new YetAnotherBoardGame().minimumMoves(board));
        }*/
/*      case 7: {
            String[] board            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new YetAnotherBoardGame().minimumMoves(board));
        }*/
/*      case 8: {
            String[] board            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new YetAnotherBoardGame().minimumMoves(board));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
