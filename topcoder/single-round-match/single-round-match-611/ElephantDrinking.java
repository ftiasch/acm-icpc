import java.math.*;
import java.util.*;

public class ElephantDrinking {
    static int[] DELTA_X = {-1, 0, 1, 0};
    static int[] DELTA_Y = {0, -1, 0, 1};

    public int maxSum(String[] field) {
        return Math.max(solve(field), solve(flip(field)));
    }

    int n;
    String[] field;
    int[][][] lineMemory, blockMemory;

    int solve(String[] field) {
        n = field.length;
        this.field = field;
        lineMemory = new int[4][n][n];
        blockMemory = new int[4][n][n];
        for (int k = 0; k < 4; ++ k) {
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    lineMemory[k][i][j] = blockMemory[k][i][j] = -1;
                }
            }
        }
        int[] row = new int[n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 1; j < n; ++ j) {
                row[i] = Math.max(row[i], line(1, i, j - 1) + line(3, i, j));
            }
        }
        int[] upMax = new int[n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                upMax[i] = Math.max(upMax[i], line(0, i - 1, j) + block(0, i - 1, j - 1) + block(3, i - 1, j + 1));
            }
        }
        int[] downMax = new int[n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                downMax[i] = Math.max(downMax[i], line(2, i + 1, j) + block(1, i + 1, j - 1) + block(2, i + 1, j + 1));
            }
        }
        int result = 0;
        for (int i = 0; i < n; ++ i) {
            int sum = 0;
            for (int j = i; j < n; ++ j) {
                sum += row[j];
                result = Math.max(result, sum + upMax[i] + downMax[j]);
            }
        }
        for (int i = 0; i < n; ++ i) {
            for (int ii = i + 1; ii < n; ++ ii) {
                for (int j = 0; j < n; ++ j) {
                    for (int jj = j + 1; jj < n; ++ jj) {
                        result = Math.max(result, block(0, i, jj - 1) + block(1, i + 1, j) + block(2, ii, j + 1) + block(3, ii - 1, jj));
                        result = Math.max(result, block(0, ii - 1, j) + block(1, ii, jj - 1) + block(2, i + 1, jj) + block(3, i, j + 1));
                    }
                }
            }
        }
        return result;
    }

    int line(int k, int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= n) {
            return 0;
        }
        if (lineMemory[k][i][j] == -1) {
            lineMemory[k][i][j] = Math.max(field[i].charAt(j) - '0', line(k, i + DELTA_X[k], j + DELTA_Y[k]));
        }
        return lineMemory[k][i][j];
    }

    int block(int k, int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= n) {
            return 0;
        }
        if (blockMemory[k][i][j] == -1) {
            int kk = k + 1 & 3;
            blockMemory[k][i][j] = Math.max(
                    line(k, i, j) + block(k, i + DELTA_X[kk], j + DELTA_Y[kk]),
                    line(kk, i, j) + block(k, i + DELTA_X[k], j + DELTA_Y[k]));
        }
        return blockMemory[k][i][j];
    }


    String[] flip(String[] field) {
        int n = field.length;
        String[] result = new String[n];
        for (int j = 0; j < n; ++ j) {
            result[j] = "";
            for (int i = 0; i < n; ++ i) {
                result[j] += field[i].charAt(j);
            }
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            ElephantDrinkingHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                ElephantDrinkingHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class ElephantDrinkingHarness {
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
            String[] field            = {"00000",
 "00110",
 "01000",
 "00100",
 "00000"};
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new ElephantDrinking().maxSum(field));
        }
        case 1: {
            String[] field            = {"111",
 "191",
 "111"};
            int expected__            = 16;

            return verifyCase(casenum__, expected__, new ElephantDrinking().maxSum(field));
        }
        case 2: {
            String[] field            = {"1010",
 "0011",
 "1100",
 "1111"};
            int expected__            = 10;

            return verifyCase(casenum__, expected__, new ElephantDrinking().maxSum(field));
        }
        case 3: {
            String[] field            = {"0011",
 "1110",
 "0111",
 "0101"}
;
            int expected__            = 10;

            return verifyCase(casenum__, expected__, new ElephantDrinking().maxSum(field));
        }
        case 4: {
            String[] field            = {"11100",
 "00100",
 "11111",
 "00100",
 "10111"}
;
            int expected__            = 13;

            return verifyCase(casenum__, expected__, new ElephantDrinking().maxSum(field));
        }
        case 5: {
            String[] field            = {"023771",
 "509514",
 "675579",
 "367472",
 "575198",
 "115281"};
            int expected__            = 112;

            return verifyCase(casenum__, expected__, new ElephantDrinking().maxSum(field));
        }
        case 6: {
            String[] field            = {"11111111111111111111111111111111111111",
 "11111111111111111111111111111111111111",
 "11111111111111111111111111111111111111",
 "11111111111111111111111111111111111111",
 "11111111111111111111111111111111111111",
 "11111111111111111111111111111111111111",
 "11111111111111111111111111111111111111",
 "11111111111111111111111111111111111111",
 "11111111111111111111111111111111111111",
 "11111111111111111111111111111111111111",
 "11111111111111111111111111111111111111",
 "11111111111111111111111111111111111111",
 "11111111111111111111111111111111111111",
 "11111111111011111111111111101111111111",
 "11111111111000111111111100001111111111",
 "11111111111000001111111000011111111111",
 "11111111111100000000000000011111111111",
 "11111111111100000000000000011111111111",
 "11111111111100000000000000111111111111",
 "11111111111110000000000000111111111111",
 "11111111111100000000000000011111111111",
 "11111111111000000000000000001111111111",
 "11111110111000000000000000001110111111",
 "11111110010000000000000000001100111111",
 "11111111000000000000000000001001111111",
 "11111111110000000000000000000011111111",
 "11111111100000110000000011000001111111",
 "11111110000001111000001111000000111111",
 "11111100000000111000001100000000101111",
 "11110000000110111000001101100000000111",
 "11111000000111110000000111111000111111",
 "11111100001111110000000111111111111111",
 "11111111111111100000000011111111111111",
 "11111111111111100000000011111111111111",
 "11111111111111000000000011111111111111",
 "11111111111111000000000011111111111111",
 "11111111111111100000000111111111111111",
 "11111111111111100000000111111111111111"};
            int expected__            = 148;

            return verifyCase(casenum__, expected__, new ElephantDrinking().maxSum(field));
        }

        // custom cases

/*      case 7: {
            String[] field            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ElephantDrinking().maxSum(field));
        }*/
/*      case 8: {
            String[] field            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ElephantDrinking().maxSum(field));
        }*/
/*      case 9: {
            String[] field            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ElephantDrinking().maxSum(field));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
