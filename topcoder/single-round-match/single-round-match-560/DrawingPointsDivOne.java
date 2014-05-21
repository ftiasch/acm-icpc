import java.math.*;
import java.util.*;

public class DrawingPointsDivOne {
    boolean check(int[] x, int[] y, int step) {
        int n = x.length;
        int[][] sum = new int[300][300];
        for (int k = 0; k < n; ++ k) {
            for (int i = 0; i < step; ++ i) {
                for (int j = 0; j < step; ++ j) {
                    sum[70 + x[k] + i][70 + y[k] + j] = 1;
                }
            }
        }
        for (int i = 298; i >= 0; -- i) {
            for (int j = 298; j >= 0; -- j) {
                sum[i][j] += sum[i + 1][j] + sum[i][j + 1] - sum[i + 1][j + 1];
            }
        }
        for (int i = 0; i + step < 300; ++ i) {
            for (int j = 0; j + step < 300; ++ j) {
                int k = 0;
                while (k < n && (x[k] + 70 != i || y[k] + 70 != j)) {
                    k ++;
                }
                if (k == n && sum[i][j] - sum[i + step][j] - sum[i][j + step] + sum[i + step][j + step] == step * step) {
                    return false;
                }
            }
        }
        return true;
    }

    public int maxSteps(int[] x, int[] y) {
        int low = 0;
        int high = 150;
        while (low < high) {
            int middle = low + high + 1 >> 1;
            if (check(x, y, middle + 1)) {
                low = middle;
            } else {
                high = middle - 1;
            }
        }
        return low < 150 ? low : -1;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            DrawingPointsDivOneHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                DrawingPointsDivOneHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class DrawingPointsDivOneHarness {
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
            int[] x                   = {0, 3};
            int[] y                   = {0, 0};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new DrawingPointsDivOne().maxSteps(x, y));
        }
        case 1: {
            int[] x                   = {0,2};
            int[] y                   = {0,0};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new DrawingPointsDivOne().maxSteps(x, y));
        }
        case 2: {
            int[] x                   = {-70};
            int[] y                   = {3};
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new DrawingPointsDivOne().maxSteps(x, y));
        }
        case 3: {
            int[] x                   = {-41,-40,1,-11,-32,-7,24,-11,49,-15,-22,20,-8,54,54,69,16,-30,36,-6,-30,40,64,20,-66, -37,-33,-18,-35,36,9,61,-43,45,5,60,-8,-58,65,-66,41,12,34,-11,-57,-38,46,63,-55,3};
            int[] y                   = {5,-24,-2,-4,23,14,1,70,-26,45,15,48,32,-41,54,-47,-67,-46,-9,-53,54,28,-61,11,53,68, -33,62,37,-8,-17,-17,48,19,-49,56,-41,16,17,-50,28,59,10,50,23,-16,56,31,-70,-44};
            int expected__            = 9;

            return verifyCase(casenum__, expected__, new DrawingPointsDivOne().maxSteps(x, y));
        }

        // custom cases

/*      case 4: {
            int[] x                   = ;
            int[] y                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new DrawingPointsDivOne().maxSteps(x, y));
        }*/
/*      case 5: {
            int[] x                   = ;
            int[] y                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new DrawingPointsDivOne().maxSteps(x, y));
        }*/
/*      case 6: {
            int[] x                   = ;
            int[] y                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new DrawingPointsDivOne().maxSteps(x, y));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
