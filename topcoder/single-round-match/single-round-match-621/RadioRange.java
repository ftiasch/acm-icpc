import java.math.*;
import java.util.*;

public class RadioRange {
    public double RadiusProbability(int[] x, int[] y, int[] r, int z) {
        int n = x.length;
        List <Pair> events = new ArrayList <Pair>();
        for (int i = 0; i < n; ++ i) {
            double d = Math.sqrt(square(x[i]) + square(y[i]));
            events.add(new Pair(Math.max(d - r[i], 0.), +1));
            events.add(new Pair(d + r[i], -1));
        }
        events.add(new Pair(z, 0));
        Collections.sort(events);
        double result = events.get(0).x;
        int delta = 0;
        for (int i = 0; events.get(i).d != 0; ++ i) {
            delta += events.get(i).d;
            if (delta == 0) {
                result += events.get(i + 1).x - events.get(i).x;
            }
        }
        return result / z;
    }

    long square(long x) {
        return x * x;
    }

    class Pair implements Comparable {
        Pair(double x, int d) {
            this.x = x;
            this.d = d;
        }

        @Override
        public int compareTo(Object other) {
            Pair o = (Pair)other;
            if (x == o.x) {
                return 0;
            }
            return x < o.x ? -1 : 1;
        }

        @Override
        public String toString() {
            return String.format("(%.2f, %d)", x, d);
        }

        double x;
        int    d;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            RadioRangeHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                RadioRangeHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class RadioRangeHarness {
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
            int[] X                   = {0};
            int[] Y                   = {0};
            int[] R                   = {5};
            int Z                     = 10;
            double expected__         = 0.5;

            return verifyCase(casenum__, expected__, new RadioRange().RadiusProbability(X, Y, R, Z));
        }
        case 1: {
            int[] X                   = {0};
            int[] Y                   = {0};
            int[] R                   = {10};
            int Z                     = 10;
            double expected__         = 0.0;

            return verifyCase(casenum__, expected__, new RadioRange().RadiusProbability(X, Y, R, Z));
        }
        case 2: {
            int[] X                   = {10};
            int[] Y                   = {10};
            int[] R                   = {10};
            int Z                     = 10;
            double expected__         = 0.4142135623730951;

            return verifyCase(casenum__, expected__, new RadioRange().RadiusProbability(X, Y, R, Z));
        }
        case 3: {
            int[] X                   = {11, -11, 0, 0};
            int[] Y                   = {0, 0, 11, -11};
            int[] R                   = {10, 10, 10, 10};
            int Z                     = 31;
            double expected__         = 0.3548387096774194;

            return verifyCase(casenum__, expected__, new RadioRange().RadiusProbability(X, Y, R, Z));
        }
        case 4: {
            int[] X                   = {100};
            int[] Y                   = {100};
            int[] R                   = {1};
            int Z                     = 10;
            double expected__         = 1.0;

            return verifyCase(casenum__, expected__, new RadioRange().RadiusProbability(X, Y, R, Z));
        }
        case 5: {
            int[] X                   = {1000000000};
            int[] Y                   = {1000000000};
            int[] R                   = {1000000000};
            int Z                     = 1000000000;
            double expected__         = 0.41421356237309503;

            return verifyCase(casenum__, expected__, new RadioRange().RadiusProbability(X, Y, R, Z));
        }
        case 6: {
            int[] X                   = {20, -20, 0, 0};
            int[] Y                   = {0, 0, 20, -20};
            int[] R                   = {50, 50, 50, 50};
            int Z                     = 100;
            double expected__         = 0.3;

            return verifyCase(casenum__, expected__, new RadioRange().RadiusProbability(X, Y, R, Z));
        }
        case 7: {
            int[] X                   = {0, -60, -62, -60, 63, -97};
            int[] Y                   = {-72, 67, 61, -8, -32, 89};
            int[] R                   = {6, 7, 8, 7, 5, 6};
            int Z                     = 918;
            double expected__         = 0.9407071068962471;

            return verifyCase(casenum__, expected__, new RadioRange().RadiusProbability(X, Y, R, Z));
        }

        // custom cases

/*      case 8: {
            int[] X                   = ;
            int[] Y                   = ;
            int[] R                   = ;
            int Z                     = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new RadioRange().RadiusProbability(X, Y, R, Z));
        }*/
/*      case 9: {
            int[] X                   = ;
            int[] Y                   = ;
            int[] R                   = ;
            int Z                     = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new RadioRange().RadiusProbability(X, Y, R, Z));
        }*/
/*      case 10: {
            int[] X                   = ;
            int[] Y                   = ;
            int[] R                   = ;
            int Z                     = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new RadioRange().RadiusProbability(X, Y, R, Z));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
