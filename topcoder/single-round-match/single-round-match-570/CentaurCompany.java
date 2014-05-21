import java.math.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class CentaurCompany {
    final static double EPS = 1e-12;

    int n;
    ArrayList[] trees;

    double[][][] ways;

    void solve(int p, int u) {
        ways[u][0][0 + n] = ways[u][1][1 + n] = 0.5;
        for (Object obj : trees[u]) {
            int v = (Integer)obj;
            if (v != p) {
                solve(u, v);
                double[][] newWays = new double[2][2 * n + 1];
                for (int uType = 0; uType < 2; ++ uType) {
                    for (int uCount = -n; uCount <= n; ++ uCount) {
                        if (Math.abs(ways[u][uType][uCount + n]) < EPS) {
                            continue;
                        }
                        for (int vType = 0; vType < 2; ++ vType) {
                            for (int vCount = -n; vCount <= n; ++ vCount) {
                                if (Math.abs(ways[v][vType][vCount + n]) < EPS) {
                                    continue;
                                }
                                int delta = vCount;
                                if (uType == 1 && vType == 1) {
                                    delta -= 2;
                                }
                                newWays[uType][uCount + delta + n] += ways[u][uType][uCount + n] * ways[v][vType][vCount + n];
                            }
                        }
                    }
                }
                ways[u] = newWays;
            }
        }
    }

    public double getvalue(int[] a, int[] b) {
        n = a.length + 1;
        trees = new ArrayList[n];
        for (int i = 0; i < n; ++ i) {
            trees[i] = new ArrayList <Integer>();
        }
        for (int i = 0; i < n - 1; ++ i) {
            a[i] --;
            b[i] --;
            trees[a[i]].add(b[i]);
            trees[b[i]].add(a[i]);
        }
        ways = new double[n][2][2 * n + 1];
        solve(-1, 0);
        double answer = 0;
        for (int type = 0; type < 2; ++ type) {
            for (int count = -n; count <= n; ++ count) {
                answer += ways[0][type][count + n] * Math.max(count - 2, 0);
            }
        }
        return answer * 2;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            CentaurCompanyHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                CentaurCompanyHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class CentaurCompanyHarness {
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
            int[] a                   = {1};
            int[] b                   = {2};
            double expected__         = 0.0;

            return verifyCase(casenum__, expected__, new CentaurCompany().getvalue(a, b));
        }
        case 1: {
            int[] a                   = {1,1,1};
            int[] b                   = {2,3,4};
            double expected__         = 0.125;

            return verifyCase(casenum__, expected__, new CentaurCompany().getvalue(a, b));
        }
        case 2: {
            int[] a                   = {1,2,3,2,2};
            int[] b                   = {2,3,4,5,6};
            double expected__         = 0.375;

            return verifyCase(casenum__, expected__, new CentaurCompany().getvalue(a, b));
        }
        case 3: {
            int[] a                   = {1,2,3,4,5,6,7,8,9};
            int[] b                   = {2,3,4,5,6,7,8,9,10};
            double expected__         = 0.41796875;

            return verifyCase(casenum__, expected__, new CentaurCompany().getvalue(a, b));
        }
        case 4: {
            int[] a                   = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
            int[] b                   = {2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36};
            double expected__         = 15.500000001076842;

            return verifyCase(casenum__, expected__, new CentaurCompany().getvalue(a, b));
        }
        case 5: {
            int[] a                   = {10, 7, 2, 5, 6, 2, 4, 9, 7};
            int[] b                   = {8, 10, 10, 4, 1, 6, 2, 2, 3};
            double expected__         = 0.646484375;

            return verifyCase(casenum__, expected__, new CentaurCompany().getvalue(a, b));
        }

        // custom cases

/*      case 6: {
            int[] a                   = ;
            int[] b                   = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new CentaurCompany().getvalue(a, b));
        }*/
/*      case 7: {
            int[] a                   = ;
            int[] b                   = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new CentaurCompany().getvalue(a, b));
        }*/
/*      case 8: {
            int[] a                   = ;
            int[] b                   = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new CentaurCompany().getvalue(a, b));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
