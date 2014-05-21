import java.util.*;
import java.math.*;

public class TreeUnion {
    final static int INF = 1000000000;

    int[] countPath(int[] parent) {
        int n = parent.length;
        int[][] distance = new int[n][n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                distance[i][j] = i == j ? 0 : INF;
            }
        }
        for (int i = 1; i < n; ++ i) {
            distance[parent[i]][i] = distance[i][parent[i]] = 1;
        }
        for (int k = 0; k < n; ++ k) {
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }
        int[] count = new int[n + 1];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < i; ++ j) {
                count[distance[i][j]] ++;
            }
        }
        return count;
    }

    public double expectedCycles(String[] treeString1, String[] treeString2, int cycle) {
        int[] tree1 = parse(treeString1);
        int[] tree2 = parse(treeString2);
        int n = tree1.length;
        int[] paths1 = countPath(tree1);
        int[] paths2 = countPath(tree2);
        double answer = 0;
        for (int i = 1; i <= cycle; ++ i) {
            int j = cycle - 2 - i;
            if (1 <= i && i <= n && 1 <= j && j <= n) {
                answer += (double)paths1[i] * paths2[j] * 2;
            }
        }
        answer /= n * (n - 1);
        return answer;
    }

    int[] parse(String[] fragments) {
        StringBuffer buffer = new StringBuffer("");
        for (String fragment : fragments) {
            buffer.append(fragment);
        }
        StringTokenizer tokenizer = new StringTokenizer(buffer.toString());
        int n = tokenizer.countTokens() + 1;
        int[] ret = new int[n];
        ret[0] = -1;
        for (int i = 1; i < n; ++ i) {
            ret[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return ret;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            TreeUnionHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                TreeUnionHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class TreeUnionHarness {
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
            String[] tree1            = {"0"};
            String[] tree2            = {"0"};
            int K                     = 4;
            double expected__         = 1.0;

            return verifyCase(casenum__, expected__, new TreeUnion().expectedCycles(tree1, tree2, K));
        }
        case 1: {
            String[] tree1            = {"0 1"};
            String[] tree2            = {"0 1"};
            int K                     = 4;
            double expected__         = 1.3333333333333333;

            return verifyCase(casenum__, expected__, new TreeUnion().expectedCycles(tree1, tree2, K));
        }
        case 2: {
            String[] tree1            = {"0 1"};
            String[] tree2            = {"0 1"};
            int K                     = 6;
            double expected__         = 0.3333333333333333;

            return verifyCase(casenum__, expected__, new TreeUnion().expectedCycles(tree1, tree2, K));
        }
        case 3: {
            String[] tree1            = {"0 ", "1 1 1"};
            String[] tree2            = {"0 1 0 ", "1"};
            int K                     = 5;
            double expected__         = 4.0;

            return verifyCase(casenum__, expected__, new TreeUnion().expectedCycles(tree1, tree2, K));
        }
        case 4: {
            String[] tree1            = {"0 1 2 0 1 2 0 1 2 5 6 1", "0 11", " 4"};
            String[] tree2            = {"0 1 1 0 2 3 4 3 4 6 6", " 10 12 12"};
            int K                     = 7;
            double expected__         = 13.314285714285713;

            return verifyCase(casenum__, expected__, new TreeUnion().expectedCycles(tree1, tree2, K));
        }

        // custom cases

/*      case 5: {
            String[] tree1            = ;
            String[] tree2            = ;
            int K                     = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new TreeUnion().expectedCycles(tree1, tree2, K));
        }*/
/*      case 6: {
            String[] tree1            = ;
            String[] tree2            = ;
            int K                     = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new TreeUnion().expectedCycles(tree1, tree2, K));
        }*/
/*      case 7: {
            String[] tree1            = ;
            String[] tree2            = ;
            int K                     = ;
            double expected__         = ;

            return verifyCase(casenum__, expected__, new TreeUnion().expectedCycles(tree1, tree2, K));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
