import java.math.*;
import java.util.*;

public class TorusSailing {
    public double expectedTime(int n, int m, int goalX, int goalY) {
        double[][][] coefficient = new double[n][m][m + 1];
        for (int j = 1; j < m; ++ j) {
            coefficient[0][j][j] = 1.;
        }
        for (int i = 1; i < n; ++ i) {
            for (int k = 0; k <= m; ++ k) {
                double a = 1.;
                double b = 0.;
                for (int j = 1; j <= m; ++ j) {
                    a *= 0.5;
                    b *= 0.5;
                    b += coefficient[i - 1][j % m][k] * 0.5 + (k == m ? 1. : 0.);
                }
                coefficient[i][0][k] = b / (1. - a);
                for (int j = 1; j < m; ++ j) {
                    coefficient[i][j][k] = (coefficient[i][j - 1][k] + coefficient[i - 1][j][k]) * 0.5 + (k == m ? 1. : 0.);
                }
            }
        }
        double[][] matrix = new double[m][m + 1];
        for (int j = 1; j < m; ++ j) {
            for (int k = 0; k <= m; ++ k) {
                matrix[j][k] = (matrix[j - 1][k] + coefficient[n - 1][j][k]) * 0.5 + (k == m ? 1. : 0.);
            }
        }
        matrix[0][0] = 1.;
        matrix[0][m] = 0.;
        for (int j = 1; j < m; ++ j) {
            matrix[j][j] -= 1.;
        }
        double[] solutions = solve(matrix);
        double[][] expectation = new double[n][m];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                expectation[i][j] = coefficient[i][j][m];
                for (int k = 0; k < m; ++ k) {
                    expectation[i][j] += coefficient[i][j][k] * solutions[k];
                }
            }
        }
        for (int _ = 0; _ < 1000; ++ _) {
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < m; ++ j) {
                    if (i > 0 || j > 0) {
                        expectation[i][j] = (expectation[(i + n - 1) % n][j] + expectation[i][(j + m - 1) % m]) * 0.5 + 1.;
                    }
                }
            }
        }
        return expectation[goalX][goalY];
    }

    static double EPS = 1e-10;

    double[] solve(double[][] coefficient) {
        int n = coefficient.length;
        for (int j = 0; j < n; ++ j) {
            int pivot = j;
            while (pivot < n && Math.abs(coefficient[pivot][j]) < EPS) {
                pivot ++;
            }
            assert pivot < n;
            if (true) {
                double[] row = coefficient[j];
                coefficient[j] = coefficient[pivot];
                coefficient[pivot] = row;
            }
            for (int i = 0; i < n; ++ i) {
                if (i != j && Math.abs(coefficient[i][j]) >= EPS) {
                    double t = coefficient[i][j] / coefficient[j][j];
                    for (int k = 0; k <= n; ++ k) {
                        coefficient[i][k] -= coefficient[j][k] * t;
                    }
                }
            }
        }
        double[] result = new double[n];
        for (int i = 0; i < n; ++ i) {
            result[i] = -coefficient[i][n] / coefficient[i][i];
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            TorusSailingHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                TorusSailingHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class TorusSailingHarness {
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
            int N                     = 2;
            int M                     = 2;
            int goalX                 = 1;
            int goalY                 = 1;
            double expected__         = 4.0;

            return verifyCase(casenum__, expected__, new TorusSailing().expectedTime(N, M, goalX, goalY));
        }
        case 1: {
            int N                     = 3;
            int M                     = 3;
            int goalX                 = 0;
            int goalY                 = 2;
            double expected__         = 8.0;

            return verifyCase(casenum__, expected__, new TorusSailing().expectedTime(N, M, goalX, goalY));
        }
        case 2: {
            int N                     = 7;
            int M                     = 10;
            int goalX                 = 3;
            int goalY                 = 2;
            double expected__         = 51.80060107964039;

            return verifyCase(casenum__, expected__, new TorusSailing().expectedTime(N, M, goalX, goalY));
        }
        case 3: {
            int N                     = 100;
            int M                     = 100;
            int goalX                 = 99;
            int goalY                 = 99;
            double expected__         = 9992.616372325532;

            return verifyCase(casenum__, expected__, new TorusSailing().expectedTime(N, M, goalX, goalY));
        }

        // custom cases

/*      case 4: {
            int N                     = ;
            int M                     = ;
            int goalX                 = ;
            int goalY                 = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new TorusSailing().expectedTime(N, M, goalX, goalY));
        }*/
/*      case 5: {
            int N                     = ;
            int M                     = ;
            int goalX                 = ;
            int goalY                 = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new TorusSailing().expectedTime(N, M, goalX, goalY));
        }*/
/*      case 6: {
            int N                     = ;
            int M                     = ;
            int goalX                 = ;
            int goalY                 = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new TorusSailing().expectedTime(N, M, goalX, goalY));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
