import java.math.*;
import java.util.*;

public class BichromePainting {
    public String isThatPossible(String[] boardString, int k) {
        int n = boardString.length;
        char[][] board = new char[n][];
        for (int i = 0; i < n; ++ i) {
            board[i] = boardString[i].toCharArray();
        }
        boolean changed;
        do {
            changed = false;
            for (int i = 0; i + k <= n; ++ i) {
                for (int j = 0; j + k <= n; ++ j) {
                    boolean hasW = false;
                    boolean hasB = false;
                    for (int x = i; x < i + k; ++ x) {
                        for (int y = j; y < j + k; ++ y) {
                            hasW |= board[x][y] == 'W';
                            hasB |= board[x][y] == 'B';
                        }
                    }
                    if (hasW ^ hasB) {
                        changed = true;
                        for (int x = i; x < i + k; ++ x) {
                            for (int y = j; y < j + k; ++ y) {
                                board[x][y] = '?';
                            }
                        }
                    }
                }
            }
        } while (changed);
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                if (board[i][j] != '?') {
                    return "Impossible";
                }
            }
        }
        return "Possible";
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            BichromePaintingHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                BichromePaintingHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class BichromePaintingHarness {
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
            String[] board            = {"BBBW",
 "BWWW",
 "BWWW",
 "WWWW"};
            int k                     = 3;
            String expected__         = "Possible";

            return verifyCase(casenum__, expected__, new BichromePainting().isThatPossible(board, k));
        }
        case 1: {
            String[] board            = {"BW",
 "WB"}
;
            int k                     = 2;
            String expected__         = "Impossible";

            return verifyCase(casenum__, expected__, new BichromePainting().isThatPossible(board, k));
        }
        case 2: {
            String[] board            = {"BWBWBB",
 "WBWBBB",
 "BWBWBB",
 "WBWBBB",
 "BBBBBB",
 "BBBBBB"}
;
            int k                     = 2;
            String expected__         = "Possible";

            return verifyCase(casenum__, expected__, new BichromePainting().isThatPossible(board, k));
        }
        case 3: {
            String[] board            = {"BWBWBB",
 "WBWBWB",
 "BWBWBB",
 "WBWBWB",
 "BWBWBB",
 "BBBBBB"}
;
            int k                     = 2;
            String expected__         = "Impossible";

            return verifyCase(casenum__, expected__, new BichromePainting().isThatPossible(board, k));
        }
        case 4: {
            String[] board            = {"BWBWBB",
 "WBWBWB",
 "BWBWBB",
 "WBWBWB",
 "BWBWBB",
 "BBBBBB"}
;
            int k                     = 1;
            String expected__         = "Possible";

            return verifyCase(casenum__, expected__, new BichromePainting().isThatPossible(board, k));
        }
        case 5: {
            String[] board            = {"BB",
 "BB"};
            int k                     = 2;
            String expected__         = "Possible";

            return verifyCase(casenum__, expected__, new BichromePainting().isThatPossible(board, k));
        }

        // custom cases

/*      case 6: {
            String[] board            = ;
            int k                     = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new BichromePainting().isThatPossible(board, k));
        }*/
/*      case 7: {
            String[] board            = ;
            int k                     = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new BichromePainting().isThatPossible(board, k));
        }*/
/*      case 8: {
            String[] board            = ;
            int k                     = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new BichromePainting().isThatPossible(board, k));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
