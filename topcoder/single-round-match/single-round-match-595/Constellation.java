import java.math.*;
import java.util.*;

public class Constellation {
    public double expectation(int[] x, int[] y, int[] prob) {
        int n = x.length;
        Point[] point = new Point[n];
        for (int i = 0; i < n; ++ i) {
            point[i] = new Point(x[i], y[i]);
        }
        double result = 0.;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                if (i != j) {
                    double p = prob[i] * prob[j] / 1000000.;
                    for (int k = 0; k < n; ++ k) {
                        if (k != i && k != j) {
                            int d = point[j].subtract(point[i]).det(point[k].subtract(point[i]));
                            if (d < 0) {
                                p *= 1. - prob[k] / 1000.;
                            }
                            if (d == 0) {
                                if (point[i].subtract(point[k]).dot(point[j].subtract(point[k])) < 0) {
                                    p *= 1. - prob[k] / 1000.;
                                }
                            }
                        }
                    }
                    result += point[i].det(point[j]) * p;
                }
            }
        }
        return result * 0.5;
    }

    class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int det(Point o) {
            return x * o.y - y * o.x;
        }

        int dot(Point o) {
            return x * o.x + y * o.y;
        }

        Point subtract(Point o) {
            return new Point(x - o.x, y - o.y);
        }
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            ConstellationHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                ConstellationHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class ConstellationHarness {
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
            int[] x                   = {0,0,1};
            int[] y                   = {0,1,0};
            int[] prob                = {500,500,500};
            double expected__         = 0.0625;

            return verifyCase(casenum__, expected__, new Constellation().expectation(x, y, prob));
        }
        case 1: {
            int[] x                   = {0,1,0,1};
            int[] y                   = {0,0,1,1};
            int[] prob                = {1000,1000,400,800};
            double expected__         = 0.6000000000000001;

            return verifyCase(casenum__, expected__, new Constellation().expectation(x, y, prob));
        }
        case 2: {
            int[] x                   = {-1,-1,-1,0,0,0,1,1,1};
            int[] y                   = {-1,0,1,-1,0,1,-1,0,1};
            int[] prob                = {500,500,500,500,500,500,500,500,500};
            double expected__         = 1.9375;

            return verifyCase(casenum__, expected__, new Constellation().expectation(x, y, prob));
        }
        case 3: {
            int[] x                   = {0,0,1,2,2};
            int[] y                   = {0,1,2,1,0};
            int[] prob                = {1000,500,200,500,1000};
            double expected__         = 1.3;

            return verifyCase(casenum__, expected__, new Constellation().expectation(x, y, prob));
        }
        case 4: {
            int[] x                   = {1,5,5,8,2,6,9};
            int[] y                   = {3,6,4,2,5,7,9};
            int[] prob                = {100,400,200,1000,400,900,600};
            double expected__         = 12.888936;

            return verifyCase(casenum__, expected__, new Constellation().expectation(x, y, prob));
        }
        case 5: {
            int[] x                   = {-100,100,-100,100,-42,57,-34,76,35,-75,-54,10,43};
            int[] y                   = {-100,-100,100,100,52,-57,-84,63,-43,50,63,10,-44};
            int[] prob                = {1000,1000,1000,1000,342,747,897,325,678,34,53,6,253};
            double expected__         = 40000.0;

            return verifyCase(casenum__, expected__, new Constellation().expectation(x, y, prob));
        }

        // custom cases

/*      case 6: {
            int[] x                   = ;
            int[] y                   = ;
            int[] prob                = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new Constellation().expectation(x, y, prob));
        }*/
/*      case 7: {
            int[] x                   = ;
            int[] y                   = ;
            int[] prob                = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new Constellation().expectation(x, y, prob));
        }*/
/*      case 8: {
            int[] x                   = ;
            int[] y                   = ;
            int[] prob                = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new Constellation().expectation(x, y, prob));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
