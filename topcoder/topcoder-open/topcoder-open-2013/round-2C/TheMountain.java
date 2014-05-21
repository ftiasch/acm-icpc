import java.math.*;
import java.util.*;

public class TheMountain {
    final static int INF = (int)1e9;

    public int minSum(int n, int m, int[] rows, int[] columns, int[] values) {
        int[][] matrix = new int[n + 2][m + 2];
        for (int i = 0; i < rows.length; ++ i) {
            matrix[rows[i] + 1][columns[i] + 1] = values[i];
        }

        int[][] minTL = new int[n + 2][m + 2];
        long[][] sumTL = new long[n + 2][m + 2];
        for (int i = 1; i <= n; ++ i) {
            for (int j = 1; j <= m; ++ j) {
                minTL[i][j] = Math.max(Math.max(minTL[i - 1][j], minTL[i][j - 1]) + 1, matrix[i][j]);
                if (matrix[i][j] != 0 && minTL[i][j] > matrix[i][j]) {
                    minTL[i][j] = INF;
                }
                sumTL[i][j] = sumTL[i - 1][j] + sumTL[i][j - 1] - sumTL[i - 1][j - 1] + minTL[i][j];
            }
        }

        int[][] minTR = new int[n + 2][m + 2];
        long[][] sumTR = new long[n + 2][m + 2];
        for (int i = 1; i <= n; ++ i) {
            for (int j = m; j >= 1; -- j) {
                minTR[i][j] = Math.max(Math.max(minTR[i - 1][j], minTR[i][j + 1]) + 1, matrix[i][j]);
                if (matrix[i][j] != 0 && minTR[i][j] > matrix[i][j]) {
                    minTR[i][j] = INF;
                }
                sumTR[i][j] = sumTR[i - 1][j] + sumTR[i][j + 1] - sumTR[i - 1][j + 1] + minTR[i][j];
            }
        }

        int[][] minBL = new int[n + 2][m + 2];
        long[][] sumBL = new long[n + 2][m + 2];
        for (int i = n; i >= 1; -- i) {
            for (int j = 1; j <= m; ++ j) {
                minBL[i][j] = Math.max(Math.max(minBL[i + 1][j], minBL[i][j - 1]) + 1, matrix[i][j]);
                if (matrix[i][j] != 0 && minBL[i][j] > matrix[i][j]) {
                    minBL[i][j] = INF;
                }
                sumBL[i][j] = sumBL[i + 1][j] + sumBL[i][j - 1] - sumBL[i + 1][j - 1] + minBL[i][j];
            }
        }

        int[][] minBR = new int[n + 2][m + 2];
        long[][] sumBR = new long[n + 2][m + 2];
        for (int i = n; i >= 1; -- i) {
            for (int j = m; j >= 1; -- j) {
                minBR[i][j] = Math.max(Math.max(minBR[i + 1][j], minBR[i][j + 1]) + 1, matrix[i][j]);
                if (matrix[i][j] != 0 && minBR[i][j] > matrix[i][j]) {
                    minBR[i][j] = INF;
                }
                sumBR[i][j] = sumBR[i + 1][j] + sumBR[i][j + 1] - sumBR[i + 1][j + 1] + minBR[i][j];
            }
        }

        long answer = INF;
        int[][] min = new int[n + 2][m + 2];
        for (int a = 1; a <= n; ++ a) {
            for (int b = 1; b <= m; ++ b) {
                long sum = sumTL[a - 1][b - 1] + sumTR[a - 1][b + 1] + sumBL[a + 1][b - 1] + sumBR[a + 1][b + 1];
                for (int j = 1; j < b; ++ j) {
                    min[a][j] = Math.max(Math.max(min[a][j - 1], Math.max(minTL[a - 1][j], minBL[a + 1][j])) + 1, matrix[a][j]);
                    if (matrix[a][j] != 0 && min[a][j] > matrix[a][j]) {
                        min[a][j] = INF;
                    }
                    sum += min[a][j];
                }
                for (int j = m; j > b; -- j) {
                    min[a][j] = Math.max(Math.max(min[a][j + 1], Math.max(minTR[a - 1][j], minBR[a + 1][j])) + 1, matrix[a][j]);
                    if (matrix[a][j] != 0 && min[a][j] > matrix[a][j]) {
                        min[a][j] = INF;
                    }
                    sum += min[a][j];
                }
                for (int i = 1; i < a; ++ i) {
                    min[i][b] = Math.max(Math.max(min[i - 1][b], Math.max(minTL[i][b - 1], minTR[i][b + 1])) + 1, matrix[i][b]);
                    if (matrix[i][b] != 0 && min[i][b] > matrix[i][b]) {
                        min[i][b] = INF;
                    }
                    sum += min[i][b];
                }
                for (int i = n; i > a; -- i) {
                    min[i][b] = Math.max(Math.max(min[i + 1][b], Math.max(minBL[i][b - 1], minBR[i][b + 1])) + 1, matrix[i][b]);
                    if (matrix[i][b] != 0 && min[i][b] > matrix[i][b]) {
                        min[i][b] = INF;
                    }
                    sum += min[i][b];
                }
                min[a][b] = Math.max(Math.max(Math.max(min[a - 1][b], min[a + 1][b]), Math.max(min[a][b - 1], min[a][b + 1])) + 1, matrix[a][b]);
                if (matrix[a][b] != 0 && min[a][b] > matrix[a][b]) {
                    min[a][b] = INF;
                }
                sum += min[a][b];
                answer = Math.min(answer, sum);
            }
        }
        return answer < INF ? (int)answer : -1;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            TheMountainHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                TheMountainHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class TheMountainHarness {
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
            int n                     = 2;
            int m                     = 3;
            int[] rowIndex            = {0, 0, 0, 1, 1, 1};
            int[] columnIndex         = {0, 1, 2, 0, 1, 2};
            int[] element             = {4, 6, 9, 1, 3, 6};
            int expected__            = 29;

            return verifyCase(casenum__, expected__, new TheMountain().minSum(n, m, rowIndex, columnIndex, element));
        }
        case 1: {
            int n                     = 2;
            int m                     = 3;
            int[] rowIndex            = {1, 0, 1};
            int[] columnIndex         = {2, 2, 0};
            int[] element             = {5, 7, 6};
            int expected__            = 40;

            return verifyCase(casenum__, expected__, new TheMountain().minSum(n, m, rowIndex, columnIndex, element));
        }
        case 2: {
            int n                     = 3;
            int m                     = 3;
            int[] rowIndex            = {0, 0, 2, 2};
            int[] columnIndex         = {0, 2, 2, 0};
            int[] element             = {1, 1, 1, 1};
            int expected__            = 15;

            return verifyCase(casenum__, expected__, new TheMountain().minSum(n, m, rowIndex, columnIndex, element));
        }
        case 3: {
            int n                     = 2;
            int m                     = 2;
            int[] rowIndex            = {0, 0, 1, 1};
            int[] columnIndex         = {0, 1, 1, 0};
            int[] element             = {5, 8, 5, 8};
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new TheMountain().minSum(n, m, rowIndex, columnIndex, element));
        }
        case 4: {
            int n                     = 1;
            int m                     = 3;
            int[] rowIndex            = {0};
            int[] columnIndex         = {1};
            int[] element             = {1};
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new TheMountain().minSum(n, m, rowIndex, columnIndex, element));
        }
        case 5: {
            int n                     = 123;
            int m                     = 45;
            int[] rowIndex            = {2, 3, 5, 7, 11};
            int[] columnIndex         = {13, 17, 19, 23, 29};
            int[] element             = {100, 200, 300, 400, 500};
            int expected__            = 367047;

            return verifyCase(casenum__, expected__, new TheMountain().minSum(n, m, rowIndex, columnIndex, element));
        }
        case 6: {
            int n                     = 200;
            int m                     = 200;
            int[] rowIndex            = {5};
            int[] columnIndex         = {8};
            int[] element             = {666};
            int expected__            = 5737554;

            return verifyCase(casenum__, expected__, new TheMountain().minSum(n, m, rowIndex, columnIndex, element));
        }
        case 7: {
            int n                     = 10;
            int m                     = 10;
            int[] rowIndex            = {0, 8, 7};
            int[] columnIndex         = {3, 1, 9};
            int[] element             = {5, 4, 7};
            int expected__            = 593;

            return verifyCase(casenum__, expected__, new TheMountain().minSum(n, m, rowIndex, columnIndex, element));
        }

        // custom cases

/*      case 8: {
            int n                     = ;
            int m                     = ;
            int[] rowIndex            = ;
            int[] columnIndex         = ;
            int[] element             = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TheMountain().minSum(n, m, rowIndex, columnIndex, element));
        }*/
/*      case 9: {
            int n                     = ;
            int m                     = ;
            int[] rowIndex            = ;
            int[] columnIndex         = ;
            int[] element             = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TheMountain().minSum(n, m, rowIndex, columnIndex, element));
        }*/
/*      case 10: {
            int n                     = ;
            int m                     = ;
            int[] rowIndex            = ;
            int[] columnIndex         = ;
            int[] element             = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TheMountain().minSum(n, m, rowIndex, columnIndex, element));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
