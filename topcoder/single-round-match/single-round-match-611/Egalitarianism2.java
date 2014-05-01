import java.math.*;
import java.util.*;

public class Egalitarianism2 {
    public double minStdev(int[] x, int[] y) {
        int n = x.length;
        double[][] distances = new double[n][n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                distances[i][j] = Math.sqrt(square(x[i] - x[j]) + square(y[i] - y[j]));
            }
        }
        List <Double> events = new ArrayList <Double>();
        for (int j = 0; j < n; ++ j) {
            for (int i = 0; i < j; ++ i) {
                for (int jj = j; jj < n; ++ jj) {
                    for (int ii = j == jj ? i + 1 : 0; ii < jj; ++ ii) {
                        events.add((distances[i][j] + distances[ii][jj]) * 0.5);
                    }
                }
            }
        }
        Collections.sort(events);
        double result = 1e100;
        for (int i = 1; i < events.size(); ++ i) {
            if (events.get(i) - events.get(i - 1) > 1e-8) {
                double miu = (events.get(i) + events.get(i - 1)) * 0.5;
                result = Math.min(result, solve(distances, miu));
            }
        }
        return result;
    }

    double solve(double[][] distances, double miu) {
        int n = distances.length;
        double[] to = new double[n];
        int[] from = new int[n];
        for (int i = 1; i < n; ++ i) {
            to[i] = square(distances[0][i] - miu);
        }
        List <Double> edges = new ArrayList <Double>();
        boolean[] visited = new boolean[n];
        visited[0] = true;
        for (int _ = 0; _ < n - 1; ++ _) {
            int i = 0;
            while (visited[i]) {
                i ++;
            }
            for (int j = i; j < n; ++ j) {
                if (!visited[j] && to[j] < to[i]) {
                    i = j;
                }
            }
            edges.add(distances[from[i]][i]);
            visited[i] = true;
            for (int j = 0; j < n; ++ j) {
                if (!visited[j]) {
                    double w = square(distances[i][j] - miu);
                    if (w < to[j]) {
                        to[j] = w;
                        from[j] = i;
                    }
                }
            }
        }
        miu = 0.;
        for (int i = 0; i < n - 1; ++ i) {
            miu += edges.get(i);
        }
        miu /= n - 1;
        double var = 0.;
        for (int i = 0; i < n - 1; ++ i) {
            var += square(edges.get(i) - miu);
        }
        return Math.sqrt(var / (n - 1));
    }

    double square(double x) {
        return x * x;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            Egalitarianism2Harness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                Egalitarianism2Harness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class Egalitarianism2Harness {
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
            int[] x                   = {0,0,1,1};
            int[] y                   = {0,1,0,1};
            double expected__         = 0.0;

            return verifyCase(casenum__, expected__, new Egalitarianism2().minStdev(x, y));
        }
        case 1: {
            int[] x                   = {0,0,0};
            int[] y                   = {0,9,10};
            double expected__         = 0.5;

            return verifyCase(casenum__, expected__, new Egalitarianism2().minStdev(x, y));
        }
        case 2: {
            int[] x                   = {12,46,81,56};
            int[] y                   = {0,45,2,67};
            double expected__         = 6.102799971320928;

            return verifyCase(casenum__, expected__, new Egalitarianism2().minStdev(x, y));
        }
        case 3: {
            int[] x                   = {0,0,0,0,0,0,0};
            int[] y                   = {0,2,3,9,10,15,16};
            double expected__         = 0.9428090415820632;

            return verifyCase(casenum__, expected__, new Egalitarianism2().minStdev(x, y));
        }
        case 4: {
            int[] x                   = {167053, 536770, -590401, 507047, 350178, -274523, -584679, -766795, -664177, 267757, -291856, -765547, 604801, -682922, -404590, 468001, 607925, 503849, -499699, -798637};
            int[] y                   = {-12396, -66098, -56843, 20270, 81510, -23294, 10423, 24007, -24343, -21587, -6318, -7396, -68622, 56304, -85680, -14890, -38373, -25477, -38240, 11736};
            double expected__         = 40056.95946451828;

            return verifyCase(casenum__, expected__, new Egalitarianism2().minStdev(x, y));
        }
        case 5: {
            int[] x                   = {-306880, 169480, -558404, -193925, 654444, -300247, -456420, -119436, -620920, -470018, -914272, -691256, -49418, -21054, 603373, -23656, 891691, 258986, -453793, -782940};
            int[] y                   = {-77318, -632629, -344942, -361706, 191982, 349424, 676536, 166124, 291342, -268968, 188262, -537953, -70432, 156803, 166174, 345128, 58614, -671747, 508265, 92324};
            double expected__         = 36879.15127634308;

            return verifyCase(casenum__, expected__, new Egalitarianism2().minStdev(x, y));
        }

        // custom cases

/*      case 6: {
            int[] x                   = ;
            int[] y                   = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new Egalitarianism2().minStdev(x, y));
        }*/
/*      case 7: {
            int[] x                   = ;
            int[] y                   = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new Egalitarianism2().minStdev(x, y));
        }*/
/*      case 8: {
            int[] x                   = ;
            int[] y                   = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new Egalitarianism2().minStdev(x, y));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
