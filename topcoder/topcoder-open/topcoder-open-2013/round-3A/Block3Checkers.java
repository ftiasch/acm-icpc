import java.math.*;
import java.util.*;

public class Block3Checkers {
    int n, m;
    int[] cost;
    ArrayList[] graph;

    int getid(int x, int y) {
        return x * m + y + 3;
    }

    final static int[] DELTA_X = {-1, -1, -1,  0, 0,  1, 1, 1};
    final static int[] DELTA_Y = {-1,  0,  1, -1, 1, -1, 0, 1};

    @SuppressWarnings("unchecked")
    public int blockThem(String[] board) {
        n = board.length;
        m = board[0].length();
        cost = new int[n * m + 3];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                if (board[i].charAt(j) == 'A') {
                    cost[getid(i, j)] = 100;
                }
                if (board[i].charAt(j) == '.') {
                    cost[getid(i, j)] = 1;
                }
            }
        }
        graph = new ArrayList[n * m + 3];
        for (int i = 0; i < n * m + 3; ++ i) {
            graph[i] = new ArrayList <Integer>();
        }
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                for (int k = 0; k < 8; ++ k) {
                    int x = i + DELTA_X[k];
                    int y = j + DELTA_Y[k];
                    if (0 <= x && x < n && 0 <= y && y < m) {
                        graph[getid(i, j)].add(getid(x, y));
                    }
                }
            }
        }
        if (true) {
            int i = 0;
            int j = 0;
            int dx = 1;
            int dy = 0;
            int side = 0;
            for (int _ = 0; _ < (n + m - 2) * 2; ++ _) {
                if (board[i].charAt(j) == 'A') {
                    side = (side + 1) % 3;
                }
                graph[side].add(getid(i, j));
                graph[getid(i, j)].add(side);
                if (i + dx < 0 || i + dx >= n || j + dy < 0 || j + dy >= m) {
                    int tmp = dx;
                    dx = -dy;
                    dy = tmp;
                }
                i += dx;
                j += dy;
            }
        }
        int[][] minCost = new int[1 << 3][n * m + 3];
        for (int[] row : minCost) {
            Arrays.fill(row, 100);
        }
        for (int i = 0; i < 3; ++ i) {
            minCost[1 << i][i] = 0;
        }
        for (int mask = 0; mask < 1 << 3; ++ mask) {
            boolean[] visit = new boolean[n * m + 3];
            Arrays.fill(visit, true);
            Queue <Integer> queue = new LinkedList <Integer>();
            for (int i = 0; i < n * m + 3; ++ i) {
                for (int a = mask; a > 0; a = (a - 1) & mask) {
                    minCost[mask][i] = Math.min(minCost[mask][i], minCost[a][i] + minCost[mask ^ a][i] - cost[i]);
                }
                queue.offer(i);
            }
            int[] distance = minCost[mask];
            while (!queue.isEmpty()) {
                int u = queue.poll();
                visit[u] = false;
                for (Object obj : graph[u]) {
                    int v = (Integer)obj;
                    if (distance[u] + cost[v] < distance[v]) {
                        distance[v] = distance[u] + cost[v];
                        if (!visit[v]) {
                            visit[v] = true;
                            queue.offer(v);
                        }
                    }
                }
            }
        }
        int answer = 100;
        for (int i = 0; i < n * m + 3; ++ i) {
            answer = Math.min(answer, minCost[7][i]);
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            Block3CheckersHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                Block3CheckersHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class Block3CheckersHarness {
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
            String[] board            = {"A......A",
 "...N.N..",
 ".NNN.NN.",
 "NNNN.N.N",
 "N.NN.NNN",
 ".NNN.NNN",
 "NNN...NN",
 ".NN.A..N"};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new Block3Checkers().blockThem(board));
        }
        case 1: {
            String[] board            = {".....A",
 "......",
 "......",
 "NNNNNN",
 "A.....",
 "A....."};
            int expected__            = 100;

            return verifyCase(casenum__, expected__, new Block3Checkers().blockThem(board));
        }
        case 2: {
            String[] board            = {"A.N",
 "NNA",
 "AN."};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new Block3Checkers().blockThem(board));
        }
        case 3: {
            String[] board            = {"......NA",
 ".NNNN.N.",
 ".N......",
 "....NNNN",
 "........",
 ".N..NNN.",
 "......N.",
 "A.N....A"}
;
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new Block3Checkers().blockThem(board));
        }

        // custom cases

/*      case 4: {
            String[] board            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new Block3Checkers().blockThem(board));
        }*/
/*      case 5: {
            String[] board            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new Block3Checkers().blockThem(board));
        }*/
/*      case 6: {
            String[] board            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new Block3Checkers().blockThem(board));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
