import java.math.*;
import java.util.*;

public class MonochromePuzzle {
    int n;

    public int getMinimum(String[] board) {
        n = board.length;
        boolean[][] graph = new boolean[n][n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                graph[i][j] = board[i].charAt(j) == '#';
            }
        }
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                for (int k = 0; k < n; ++ k) {
                    answer = Math.min(answer, solve(graph, i, j, k));
                }
            }
        }
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    int solve(boolean[][] graph, int start, int next, int opposite) {
        if (start == next || next == opposite || opposite == start
                || !graph[start][next] || !graph[start][opposite]) {
            return Integer.MAX_VALUE;
        }
        int[] order = new int[n];
        for (int i = 0; i < n >> 1; ++ i) {
            order[i << 1] = i;
            order[i << 1 | 1] = n - 1 - i;
        }
        boolean[] visit = new boolean[n];
        Arrays.fill(visit, false);
        visit[start] = visit[opposite] = visit[next] = true;
        int[] mapping = new int[n];
        mapping[0] = start;
        mapping[n - 1] = opposite;
        mapping[1] = next;
        for (int i = 3; i < n; ++ i) {
            int v = 0;
            while (v < n && (visit[v]
                        || !graph[mapping[order[i - 2]]][v]
                        || ((i & 1) == 1 && !graph[mapping[order[i - 1]]][v]))) {
                v ++;
            }
            if (v == n) {
                return Integer.MAX_VALUE;
            }
            visit[v] = true;
            mapping[order[i]] = v;
        }
        boolean[][] newGraph = new boolean[n][n];
        for (int i = 0; i < n; ++ i) {
            Arrays.fill(newGraph[i], false);
        }
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                if (Math.abs(i - j) == 1) {
                    newGraph[mapping[i]][mapping[j]] = true;
                }
                if (i + j == n - 1) {
                    newGraph[mapping[i]][mapping[j]] = true;
                }
            }
        }
        newGraph[mapping[0]][mapping[(n >> 1) - 1]] =
        newGraph[mapping[(n >> 1) - 1]][mapping[0]] =
        newGraph[mapping[n >> 1]][mapping[n - 1]] =
        newGraph[mapping[n - 1]][mapping[n >> 1]] = true;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                if (graph[i][j] != newGraph[i][j]) {
                    return Integer.MAX_VALUE;
                }
            }
        }
        Arrays.fill(visit, false);
        int count = n;
        for (int i = 0; i < n; ++ i) {
            if (!visit[i]) {
                count --;
                int j = i;
                do {
                    visit[j] = true;
                    j = mapping[j];
                } while (j != i);
            }
        }
        return count;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            MonochromePuzzleHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                MonochromePuzzleHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class MonochromePuzzleHarness {
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
            String[] board            = {".##.#."
,"#.##.."
,"##...#"
,".#..##"
,"#..#.#"
,"..###."};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new MonochromePuzzle().getMinimum(board));
        }
        case 1: {
            String[] board            = {"###..."
,".##..."
,"..#..."
,"#..###"
,"##..##"
,"###..#"};
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new MonochromePuzzle().getMinimum(board));
        }
        case 2: {
            String[] board            = {".#.#...#"
,"#.#...#."
,".#.#.#.."
,"#.#.#..."
,"...#.#.#"
,"..#.#.#."
,".#...#.#"
,"#...#.#."};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new MonochromePuzzle().getMinimum(board));
        }
        case 3: {
            String[] board            = {".#..#....#"
,"#....#..#."
,"....###..."
,"....#.#..#"
,"#.##......"
,".##....#.."
,"..##...#.."
,".....##.#."
,".#.....#.#"
,"#..#....#."}
;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new MonochromePuzzle().getMinimum(board));
        }
        case 4: {
            String[] board            = {".##.....#."
,"#..#.....#"
,"#..##....."
,".##..#...."
,"..#..##..."
,"...##..#.."
,"....#..##."
,".....##..#"
,"#.....#..#"
,".#.....##."};
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new MonochromePuzzle().getMinimum(board));
        }
        case 5: {
            String[] board            = {".......##.#."
,"......#..#.#"
,"......#..##."
,".......##..#"
,"......#.#..#"
,".......#.##."
,".##.#......."
,"#..#.#......"
,"#..##......."
,".##..#......"
,"#.#..#......"
,".#.##......."};
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new MonochromePuzzle().getMinimum(board));
        }

        // custom cases

/*      case 6: {
            String[] board            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new MonochromePuzzle().getMinimum(board));
        }*/
/*      case 7: {
            String[] board            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new MonochromePuzzle().getMinimum(board));
        }*/
/*      case 8: {
            String[] board            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new MonochromePuzzle().getMinimum(board));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
