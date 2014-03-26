import java.math.*;
import java.util.*;

public class FoxAndGo3 {
    static int[] DELTA_X = {-1, 0, 0, 1};
    static int[] DELTA_Y = {0, -1, 1, 0};

    public int maxEmptyCells(String[] board) {
        int n = board.length;
        int m = board[0].length();
        boolean[][] graph = new boolean[n * m][n * m];
        int result = 0;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                if (board[i].charAt(j) != 'x') {
                    result ++;
                    if ((i + j & 1) == 0) {
                        for (int k = 0; k < 4; ++ k) {
                            int ii = i + DELTA_X[k];
                            int jj = j + DELTA_Y[k];
                            if (0 <= ii && ii < n && 0 <= jj && jj < m && board[ii].charAt(jj) != 'x' && board[i].charAt(j) != board[ii].charAt(jj)) {
                                graph[i * m + j][ii * m + jj] = true;
                            }
                        }
                    }
                }
            }
        }
        boolean[] visited = new boolean[n * m];
        int[] match = new int[n * m];
        Arrays.fill(match, -1);
        for (int i = 0; i < n * m; ++ i) {
            if (find(graph, visited, match, i)) {
                result --;
                Arrays.fill(visited, false);
            }
        }
        return result;
    }

    boolean find(boolean[][] graph, boolean[] visited, int[] match, int u) {
        int n = graph.length;
        if (visited[u]) {
            return false;
        }
        visited[u] = true;
        for (int v = 0; v < n; ++ v) {
            if (graph[u][v] && (match[v] == -1 || find(graph, visited, match, match[v]))) {
                match[v] = u;
                return true;
            }
        }
        return false;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            FoxAndGo3Harness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                FoxAndGo3Harness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class FoxAndGo3Harness {
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
            String[] board            = {"o.o",
 ".o.",
 "o.o"};
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new FoxAndGo3().maxEmptyCells(board));
        }
        case 1: {
            String[] board            = {"...",
 ".o.",
 "..."}
;
            int expected__            = 8;

            return verifyCase(casenum__, expected__, new FoxAndGo3().maxEmptyCells(board));
        }
        case 2: {
            String[] board            = {"xxxxx",
 "xxoxx",
 "xo.ox",
 "xxoxx",
 "xxxxx"}
;
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new FoxAndGo3().maxEmptyCells(board));
        }
        case 3: {
            String[] board            = {".xox.",
 ".o.ox",
 "x.o.o",
 "ox.ox",
 ".ox.."}
 ;
            int expected__            = 12;

            return verifyCase(casenum__, expected__, new FoxAndGo3().maxEmptyCells(board));
        }
        case 4: {
            String[] board            = {"o.o.o",
 ".ox..",
 "oxxxo",
 "..x..",
 "o.o.o"}
;
            int expected__            = 12;

            return verifyCase(casenum__, expected__, new FoxAndGo3().maxEmptyCells(board));
        }
        case 5: {
            String[] board            = {"...",
 "...",
 "..."};
            int expected__            = 9;

            return verifyCase(casenum__, expected__, new FoxAndGo3().maxEmptyCells(board));
        }

        // custom cases

/*      case 6: {
            String[] board            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new FoxAndGo3().maxEmptyCells(board));
        }*/
/*      case 7: {
            String[] board            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new FoxAndGo3().maxEmptyCells(board));
        }*/
/*      case 8: {
            String[] board            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new FoxAndGo3().maxEmptyCells(board));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
