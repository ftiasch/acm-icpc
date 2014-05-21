import java.util.*;
import java.math.*;

public class EllysFigurines {
    int count(int mask, int n, int k) {
        int ret = 0;
        int last = 0;
        for (int i = 0; i < n; ++ i) {
            if (i >= last && (mask >> i & 1) == 1) {
                ret ++;
                last = i + k;
            }
        }
        return ret;
    }

    public int getMoves(String[] board, int r, int c) {
        int n = board.length;
        int m = board[0].length();
        int answer = Integer.MAX_VALUE;
        for (int mask = 0; mask < 1 << m; ++ mask) {
            int rowMask = 0;
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < m; ++ j) {
                    if (board[i].charAt(j) == 'X' && (mask >> j & 1) == 0) {
                        rowMask |= 1 << i;
                    }
                }
            }
            answer = Math.min(answer, count(rowMask, n, r) + count(mask, m, c));
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            EllysFigurinesHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                EllysFigurinesHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class EllysFigurinesHarness {
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
            String[] board            = {".X.X.",
 "XX..X",
 ".XXX.",
 "...X.",
 ".X.XX"};
            int R                     = 1;
            int C                     = 2;
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new EllysFigurines().getMoves(board, R, C));
        }
        case 1: {
            String[] board            = {".X.X.",
 "XX..X",
 ".X.X.",
 "...X.",
 ".X.XX"};
            int R                     = 2;
            int C                     = 2;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new EllysFigurines().getMoves(board, R, C));
        }
        case 2: {
            String[] board            = {"XXXXXXX"};
            int R                     = 2;
            int C                     = 3;
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new EllysFigurines().getMoves(board, R, C));
        }
        case 3: {
            String[] board            = {"XXXXX",
 "X....",
 "XXX..",
 "X....",
 "XXXXX"};
            int R                     = 1;
            int C                     = 1;
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new EllysFigurines().getMoves(board, R, C));
        }
        case 4: {
            String[] board            = {"XXX..XXX..XXX.",
 ".X..X....X...X",
 ".X..X....X...X",
 ".X..X....X...X",
 ".X...XXX..XXX.",
 "..............",
 "...XX...XXX...",
 "....X......X..",
 "....X....XXX..",
 "....X......X..",
 "...XXX..XXX..."};
            int R                     = 1;
            int C                     = 2;
            int expected__            = 7;

            return verifyCase(casenum__, expected__, new EllysFigurines().getMoves(board, R, C));
        }

        // custom cases

/*      case 5: {
            String[] board            = ;
            int R                     = ;
            int C                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new EllysFigurines().getMoves(board, R, C));
        }*/
/*      case 6: {
            String[] board            = ;
            int R                     = ;
            int C                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new EllysFigurines().getMoves(board, R, C));
        }*/
/*      case 7: {
            String[] board            = ;
            int R                     = ;
            int C                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new EllysFigurines().getMoves(board, R, C));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
