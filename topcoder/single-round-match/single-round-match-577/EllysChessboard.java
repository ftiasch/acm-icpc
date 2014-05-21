import java.math.*;
import java.util.*;

public class EllysChessboard {
    String[] board;
    Integer[][][][] memory;

    int solve(int smin, int smax, int amin, int amax) {
        if (memory[smin + 7][smax + 7][amin][amax] != null) {
            return memory[smin + 7][smax + 7][amin][amax];
        }
        int ret = Integer.MAX_VALUE;
        boolean found = false;
        for (int i = 0; i < 8; ++ i) {
            for (int j = 0; j < 8; ++ j) {
                if (board[i].charAt(j) == '#') {
                    if (smin <= i - j && i - j <= smax && amin <= i + j && i + j <= amax) {
                        continue;
                    }
                    found = true;
                    int new_smin = Math.min(smin, i - j);
                    int new_smax = Math.max(smax, i - j);
                    int new_amin = Math.min(amin, i + j);
                    int new_amax = Math.max(amax, i + j);
                    int cost = 0;
                    for (int x = 0; x < 8; ++ x) {
                        for (int y = 0; y < 8; ++ y) {
                            if (board[x].charAt(y) == '#') {
                                if (smin <= x - y && x - y <= smax && amin <= x + y && x + y <= amax) {
                                    continue;
                                }
                                if (new_smin <= x - y && x - y <= new_smax && new_amin <= x + y && x + y <= new_amax) {
                                    cost += Math.max(Math.max(Math.abs(x - y - new_smin), Math.abs(x - y - new_smax)),
                                            Math.max(Math.abs(x + y - new_amin), Math.abs(x + y - new_amax)));
                                }
                            }
                        }
                    }
                    ret = Math.min(ret, cost + solve(new_smin, new_smax, new_amin, new_amax));
                }
            }
        }
        if (!found) {
            ret = 0;
        }
        memory[smin + 7][smax + 7][amin][amax] = new Integer(ret);
        return ret;
    }

    public int minCost(String[] _board) {
        board = _board;
        memory = new Integer[15][15][15][15];
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 8; ++ i) {
            for (int j = 0; j < 8; ++ j) {
                if (board[i].charAt(j) == '#') {
                    answer = Math.min(answer, solve(i - j, i - j, i + j, i + j));
                }
            }
        }
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            EllysChessboardHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                EllysChessboardHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class EllysChessboardHarness {
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
            String[] board            = {"........",
 "........",
 "...#....",
 ".#......",
 ".......#",
 "........",
 "........",
 "........"};
            int expected__            = 10;

            return verifyCase(casenum__, expected__, new EllysChessboard().minCost(board));
        }
        case 1: {
            String[] board            = {"........",
 "........",
 "........",
 "........",
 "........",
 "........",
 "........",
 "........"};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new EllysChessboard().minCost(board));
        }
        case 2: {
            String[] board            = {".#......",
 "........",
 "..#..#.#",
 "...#..#.",
 "........",
 "...#...#",
 "...#...#",
 "........"};
            int expected__            = 58;

            return verifyCase(casenum__, expected__, new EllysChessboard().minCost(board));
        }
        case 3: {
            String[] board            = {"##..####",
 "#####..#",
 "..#.#...",
 "#..##.##",
 ".#.###.#",
 "####.###",
 "#.#...#.",
 "##....#."};
            int expected__            = 275;

            return verifyCase(casenum__, expected__, new EllysChessboard().minCost(board));
        }
        case 4: {
            String[] board            = {"########",
 "########",
 "########",
 "########",
 "########",
 "########",
 "########",
 "########"};
            int expected__            = 476;

            return verifyCase(casenum__, expected__, new EllysChessboard().minCost(board));
        }

        // custom cases

/*      case 5: {
            String[] board            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new EllysChessboard().minCost(board));
        }*/
/*      case 6: {
            String[] board            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new EllysChessboard().minCost(board));
        }*/
/*      case 7: {
            String[] board            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new EllysChessboard().minCost(board));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
