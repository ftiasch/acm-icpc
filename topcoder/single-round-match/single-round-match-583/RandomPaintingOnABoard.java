import java.math.*;
import java.util.*;

public class RandomPaintingOnABoard {
    public double expectedSteps(String[] board) {
        int n = board.length;
        int m = board[0].length();
        int[][] weight = new int[n][m];
        int totalWeight = 0;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                weight[i][j] = board[i].charAt(j) - '0';
                totalWeight += weight[i][j];
            }
        }
        if (n > m) {
            int[][] newWeight = new int[m][n];
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < m; ++ j) {
                    newWeight[j][i] = weight[i][j];
                }
            }
            weight = newWeight;
            n ^= m;
            m ^= n;
            n ^= m;
        }
        int[][][] ways = new int[2][totalWeight + 1][2];
        double answer = 0;
        for (int mask = 0; mask < 1 << n; ++ mask) {
            int max = 0;
            ways[0][0][0] = ways[0][0][1] = 0;
            ways[0][0][Integer.bitCount(mask) & 1] = 1;
            for (int j = 0; j < m; ++ j) {
                int notTaken = 0;
                int taken = 0;
                for (int i = 0; i < n; ++ i) {
                    taken += weight[i][j];
                    if ((mask >> i & 1) == 1) {
                        notTaken += weight[i][j];
                    }
                }
                max += taken;
                for (int sum = 0; sum <= max; ++ sum) {
                    ways[j + 1 & 1][sum][0] = ways[j + 1 & 1][sum][1] = 0;
                }
                for (int sum = 0; sum + taken <= max; ++ sum) {
                    for (int parity = 0; parity < 2; ++ parity) {
                        ways[j + 1 & 1][sum + notTaken][parity] += ways[j & 1][sum][parity];
                        ways[j + 1 & 1][sum + taken][parity ^ 1] += ways[j & 1][sum][parity];
                    }
                }
            }
            for (int sum = 1; sum <= max; ++ sum) {
                answer += (double)totalWeight / sum * (ways[m & 1][sum][1] - ways[m & 1][sum][0]);
            }
        }
        return answer;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            RandomPaintingOnABoardHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                RandomPaintingOnABoardHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class RandomPaintingOnABoardHarness {
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

    static final double MAX_DOUBLE_ERROR = 1E-9;
    static boolean compareOutput(double expected, double result){ if(Double.isNaN(expected)){ return Double.isNaN(result); }else if(Double.isInfinite(expected)){ if(expected > 0){ return result > 0 && Double.isInfinite(result); }else{ return result < 0 && Double.isInfinite(result); } }else if(Double.isNaN(result) || Double.isInfinite(result)){ return false; }else if(Math.abs(result - expected) < MAX_DOUBLE_ERROR){ return true; }else{ double min = Math.min(expected * (1.0 - MAX_DOUBLE_ERROR), expected * (1.0 + MAX_DOUBLE_ERROR)); double max = Math.max(expected * (1.0 - MAX_DOUBLE_ERROR), expected * (1.0 + MAX_DOUBLE_ERROR)); return result > min && result < max; } }
    static double relativeError(double expected, double result) { if (Double.isNaN(expected) || Double.isInfinite(expected) || Double.isNaN(result) || Double.isInfinite(result) || expected == 0) return 0; return Math.abs(result-expected) / Math.abs(expected); }

    static String formatResult(double res) {
        return String.format("%.10g", res);
    }

    static int verifyCase(int casenum, double expected, double received) {
        System.err.print("Example " + casenum + "... ");
        if (compareOutput(expected, received)) {
            System.err.print("PASSED");
            double rerr = relativeError(expected, received);
            if (rerr > 0) System.err.printf(" (relative error %g)", rerr);
            System.err.println();
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
            String[] prob             = {"10",
 "01"};
            double expected__         = 3.0;

            return verifyCase(casenum__, expected__, new RandomPaintingOnABoard().expectedSteps(prob));
        }
        case 1: {
            String[] prob             = {"11",
 "11"};
            double expected__         = 3.6666666666666665;

            return verifyCase(casenum__, expected__, new RandomPaintingOnABoard().expectedSteps(prob));
        }
        case 2: {
            String[] prob             = {"11",
 "12"};
            double expected__         = 3.9166666666666665;

            return verifyCase(casenum__, expected__, new RandomPaintingOnABoard().expectedSteps(prob));
        }
        case 3: {
            String[] prob             = {"0976",
 "1701",
 "7119"};
            double expected__         = 11.214334077031307;

            return verifyCase(casenum__, expected__, new RandomPaintingOnABoard().expectedSteps(prob));
        }
        case 4: {
            String[] prob             = {"000000000000001000000",
 "888999988889890999988",
 "988889988899980889999",
 "889898998889980999898",
 "988889999989880899999",
 "998888998988990989998",
 "998988999898990889899"};
            double expected__         = 1028.7662876159634;

            return verifyCase(casenum__, expected__, new RandomPaintingOnABoard().expectedSteps(prob));
        }

        // custom cases

/*      case 5: {
            String[] prob             = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new RandomPaintingOnABoard().expectedSteps(prob));
        }*/
/*      case 6: {
            String[] prob             = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new RandomPaintingOnABoard().expectedSteps(prob));
        }*/
/*      case 7: {
            String[] prob             = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new RandomPaintingOnABoard().expectedSteps(prob));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
