import java.util.*;
import java.math.*;

public class PiecewiseLinearFunction {
    int signum(long x) {
        return x < 0 ? -1 : (x > 0 ? 1 : 0);
    }

    public int maximumSolutions(int[] ys) {
        int n = ys.length;
        for (int i = 1; i < n; ++ i) {
            if (ys[i - 1] == ys[i]) {
                return -1;
            }
        }
        for (int i = 0; i < n; ++ i) {
            ys[i] *= 2;
        }
        int answer = 0;
        for (int i = 0; i < n; ++ i) {
            for (int d = -1; d <= 1; ++ d) {
                long y = ys[i] + d;
                int count = 0;
                for (int j = 0; j < n; ++ j) {
                    if (y == ys[j]) {
                        count ++;
                    }
                }
                for (int j = 1; j < n; ++ j) {
                    if (signum(ys[j - 1] - y) * signum(ys[j] - y) < 0) {
                        count ++;
                    }
                }
                answer = Math.max(answer, count);
            }
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            PiecewiseLinearFunctionHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                PiecewiseLinearFunctionHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class PiecewiseLinearFunctionHarness {
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
            int[] Y                   = {3, 2};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new PiecewiseLinearFunction().maximumSolutions(Y));
        }
        case 1: {
            int[] Y                   = {4, 4};
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new PiecewiseLinearFunction().maximumSolutions(Y));
        }
        case 2: {
            int[] Y                   = {1, 4, -1, 2};
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new PiecewiseLinearFunction().maximumSolutions(Y));
        }
        case 3: {
            int[] Y                   = {2, 1, 2, 1, 3, 2, 3, 2};
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new PiecewiseLinearFunction().maximumSolutions(Y));
        }
        case 4: {
            int[] Y                   = {125612666, -991004227, 0, 6, 88023, -1000000000, 1000000000, -1000000000, 1000000000};
            int expected__            = 6;

            return verifyCase(casenum__, expected__, new PiecewiseLinearFunction().maximumSolutions(Y));
        }

        // custom cases

/*      case 5: {
            int[] Y                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new PiecewiseLinearFunction().maximumSolutions(Y));
        }*/
/*      case 6: {
            int[] Y                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new PiecewiseLinearFunction().maximumSolutions(Y));
        }*/
/*      case 7: {
            int[] Y                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new PiecewiseLinearFunction().maximumSolutions(Y));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
