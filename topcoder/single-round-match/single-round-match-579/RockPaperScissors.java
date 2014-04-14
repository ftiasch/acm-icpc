import java.math.*;
import java.util.*;

public class RockPaperScissors {
    public double bestScore(int[] rocks, int[] papers, int[] scissors) {
        int n = rocks.length;
        double[][][] taken = new double[][][]{{{1.}}};
        double[][][] nextRocks = new double[n + 1][n + 1][n + 1];
        double[][][] nextPapers = new double[n + 1][n + 1][n + 1];
        double[][][] nextScissors = new double[n + 1][n + 1][n + 1];
        for (int k = 0; k < n; ++ k) {
            int m = 0;
            for (int i = 0; i < n; ++ i) {
                if (i != k) {
                    double[][][] newTaken = new double[m + 2][m + 2][m + 2];
                    for (int r = 0; r <= m; ++ r) {
                        for (int p = 0; r + p <= m; ++ p) {
                            for (int s = 0; r + p + s <= m; ++ s) {
                                double t = taken[r][p][s];
                                newTaken[r][p][s] += t;
                                t *= (r + p + s + 1.) / (n - r - p - s);
                                newTaken[r + 1][p][s] += t * rocks[i] / 300.;
                                newTaken[r][p + 1][s] += t * papers[i] / 300.;
                                newTaken[r][p][s + 1] += t * scissors[i] / 300.;
                            }
                        }
                    }
                    taken = newTaken;
                    m ++;
                }
            }
            for (int r = 0; r < n; ++ r) {
                for (int p = 0; r + p < n; ++ p) {
                    for (int s = 0; r + p + s < n; ++ s) {
                        double t = taken[r][p][s] / (n - r - p - s);
                        nextRocks[r][p][s] += t * rocks[k] / 300.;
                        nextPapers[r][p][s] += t * papers[k] / 300.;
                        nextScissors[r][p][s] += t * scissors[k] / 300.;
                    }
                }
            }
        }
        double result = 0.;
        for (int r = 0; r < n; ++ r) {
            for (int p = 0; r + p < n; ++ p) {
                for (int s = 0; r + p + s < n; ++ s) {
                    double best = 0.;
                    double[] probabilities = new double[]{nextRocks[r][p][s], nextPapers[r][p][s], nextScissors[r][p][s]};
                    for (int i = 0; i < 3; ++ i) {
                        best = Math.max(best, probabilities[i] * 3 + probabilities[(i + 1) % 3]);
                    }
                    result += best;
                }
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
            RockPaperScissorsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                RockPaperScissorsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class RockPaperScissorsHarness {
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
            int[] rockProb            = {100, 100, 100};
            int[] paperProb           = {100, 100, 100};
            int[] scissorsProb        = {100, 100, 100};
            double expected__         = 3.999999999999999;

            return verifyCase(casenum__, expected__, new RockPaperScissors().bestScore(rockProb, paperProb, scissorsProb));
        }
        case 1: {
            int[] rockProb            = {300};
            int[] paperProb           = {0};
            int[] scissorsProb        = {0};
            double expected__         = 3.0;

            return verifyCase(casenum__, expected__, new RockPaperScissors().bestScore(rockProb, paperProb, scissorsProb));
        }
        case 2: {
            int[] rockProb            = {300, 0, 0};
            int[] paperProb           = {0, 300, 0};
            int[] scissorsProb        = {0, 0, 300};
            double expected__         = 6.333333333333332;

            return verifyCase(casenum__, expected__, new RockPaperScissors().bestScore(rockProb, paperProb, scissorsProb));
        }
        case 3: {
            int[] rockProb            = {100, 0};
            int[] paperProb           = {200, 100};
            int[] scissorsProb        = {0, 200};
            double expected__         = 3.7222222222222223;

            return verifyCase(casenum__, expected__, new RockPaperScissors().bestScore(rockProb, paperProb, scissorsProb));
        }
        case 4: {
            int[] rockProb            = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 ,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 ,0,0,0,0,0,0,0,0,0};
            int[] paperProb           = {150,300,300,300,300,300,300,300,300,300,300,300,300,300 ,300,300,300,300,300,300,300,300,300,300,300,300,300,300 ,300,300,300,300,300,300,300,300,300,300,300,300,300,300 ,300,300,300,300,300,300,300,300};
            int[] scissorsProb        = {150,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 ,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 ,0,0,0,0,0,0,0,0,0};
            double expected__         = 149.00000000000003;

            return verifyCase(casenum__, expected__, new RockPaperScissors().bestScore(rockProb, paperProb, scissorsProb));
        }

        // custom cases

/*      case 5: {
            int[] rockProb            = ;
            int[] paperProb           = ;
            int[] scissorsProb        = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new RockPaperScissors().bestScore(rockProb, paperProb, scissorsProb));
        }*/
/*      case 6: {
            int[] rockProb            = ;
            int[] paperProb           = ;
            int[] scissorsProb        = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new RockPaperScissors().bestScore(rockProb, paperProb, scissorsProb));
        }*/
/*      case 7: {
            int[] rockProb            = ;
            int[] paperProb           = ;
            int[] scissorsProb        = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new RockPaperScissors().bestScore(rockProb, paperProb, scissorsProb));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
