import java.math.*;
import java.util.*;

public class EqualSums {
    final static int MOD = (int)1e9 + 7;

    int n, m;
    int[] numbers;
    int[][] graph;
    boolean valid;
    boolean[] visit;

    void dfs(int u) {
        visit[u] = true;
        for (int v = 0; v < n + m; ++ v) {
            if (graph[u][v] != -1) {
                int newNumber = graph[u][v] - numbers[u];
                valid &= newNumber >= 0;
                if (numbers[v] == -1) {
                    numbers[v] = newNumber;
                    dfs(v);
                } else {
                    valid &= numbers[v] == newNumber;
                }
            }
        }
    }

    public int count(String[] board) {
        n = board.length;
        m = board[0].length();
        graph = new int[n + m][n + m];
        for (int i = 0; i < n + m; ++ i) {
            for (int j = 0; j < n + m; ++ j) {
                graph[i][j] = -1;
            }
        }
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                if (board[i].charAt(j) != '-') {
                    graph[i][n + j] = graph[n + j][i] = board[i].charAt(j) - '0';
                }
            }
        }
        long[] ways = new long[2];
        ways[0] = 1;
        numbers = new int[n + m];
        visit = new boolean[n + m];
        for (int u = 0; u < n + m; ++ u) {
            if (!visit[u]) {
                int[] count = new int[2];
                for (int seed = 0; seed < 10; ++ seed) {
                    Arrays.fill(numbers, -1);
                    numbers[u] = seed;
                    valid = true;
                    dfs(u);
                    if (valid) {
                        boolean hasZero = false;
                        for (int v = 0; v < n; ++ v) {
                            hasZero |= numbers[v] == 0;
                        }
                        count[hasZero ? 1 : 0] ++;
                    }
                }
                ways = new long[]{ways[0] * count[0] % MOD,
                                ((ways[0] + ways[1]) * (count[0] + count[1]) % MOD + MOD - ways[0] * count[0] % MOD) % MOD};
            }
        }
        return (int)ways[1];
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            EqualSumsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                EqualSumsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class EqualSumsHarness {
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
            String[] board            = {"1-",
 "-2"};
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new EqualSums().count(board));
        }
        case 1: {
            String[] board            = {"123",
 "4--",
 "--9"};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new EqualSums().count(board));
        }
        case 2: {
            String[] board            = {"9--",
 "-9-",
 "--9"};
            int expected__            = 271;

            return verifyCase(casenum__, expected__, new EqualSums().count(board));
        }
        case 3: {
            String[] board            = {"11",
 "12"};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new EqualSums().count(board));
        }
        case 4: {
            String[] board            = {"-4--",
 "-0-2",
 "--1-",
 "4---"}
;
            int expected__            = 10;

            return verifyCase(casenum__, expected__, new EqualSums().count(board));
        }
        case 5: {
            String[] board            = {"--2",
 "02-",
 "-10"}
;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new EqualSums().count(board));
        }
        case 6: {
            String[] board            = {"----------1---------------0-----7-----------------",
 "-----4--------------------------------------------",
 "--------5-------------5-3---5---------------6-----",
 "-------2----------1-------------------------------",
 "-----4--------------------------------------------",
 "-----3--------------------------------------------",
 "-6----------5-------------------------------------",
 "-------------------------------7---5----------6---",
 "--------6-------------6-4---6---------------7-----",
 "-------------4----------------5-------------------",
 "3------------------------------------------6------",
 "3------------------------------------------6------",
 "-------------4----------------5-------------------",
 "---------------2-------------------------3--------",
 "--2------------------------------------------2----",
 "---8---------------1-------------------3----------",
 "---------------3----------------------------------",
 "----7----------------5---0-----------------------5",
 "----------------5-------------------------3-----1-",
 "----------1---------------0-----7-----------------",
 "-------------5----------------6-------------------",
 "-7----------6-------------------------------------",
 "---8---------------1-------------------3----------",
 "-----------------------3--------------------------",
 "----8----------------6---1-----------------------6",
 "------------------------------------------4-----2-",
 "-----------5---------------5----------------------",
 "-----------------------------6--------------------",
 "----8----------------6---1-----------------------6",
 "----------------5-------------------------3-----1-",
 "-------------------------------6---4--2-------5---",
 "-6----------5-------------------------------------",
 "--------5-------------5-3---5---------------6-----",
 "-------------5----------------6-------------------",
 "-----3--------------------------------------------",
 "---------------2-------------------------3--------",
 "---------4---------------------------6------------",
 "-------------------------------6---4--2-------5---",
 "------2-------------1------------22---------------",
 "--------5-------------5-3---5---------------6-----",
 "-----------5--3------------5----------------------",
 "--2------------------------------------------2----",
 "---------5---------------------------7------------",
 "-------------4----------------5-------------------",
 "-----------------5------------------4---6------2--",
 "-------------------------------6---4--2-------5---",
 "-----------------------2--------------------------",
 "----------------6-------------------------4-----2-",
 "-------------------------------6---4--2-------5---",
 "--------5-------------5-3---5---------------6-----"};
            int expected__            = 45094393;

            return verifyCase(casenum__, expected__, new EqualSums().count(board));
        }

        // custom cases

/*      case 7: {
            String[] board            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new EqualSums().count(board));
        }*/
/*      case 8: {
            String[] board            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new EqualSums().count(board));
        }*/
/*      case 9: {
            String[] board            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new EqualSums().count(board));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
