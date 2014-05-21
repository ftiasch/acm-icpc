import java.math.*;
import java.util.*;

public class MayTheBestPetWin {
    static int X = 500000;

    public int calc(int[] a, int[] b) {
        int n = a.length;
        int aSum = 0;
        int bSum = 0;
        for (int i = 0; i < n; ++ i) {
            aSum += a[i];
            bSum += b[i];
        }
        boolean[] valid = new boolean[aSum + bSum + 1];
        valid[0] = true;
        for (int i = 0, sum = 0; i < n; ++ i) {
            int c = a[i] + b[i];
            sum += c;
            for (int j = sum; j >= c; -- j) {
                valid[j] |= valid[j - c];
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i <= aSum + bSum; ++ i) {
            if (valid[i]) {
                result = Math.min(result, Math.max(i - aSum, bSum - i));
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
            MayTheBestPetWinHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                MayTheBestPetWinHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class MayTheBestPetWinHarness {
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
            int[] A                   = {3,4,4,7};
            int[] B                   = {3,4,4,7};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new MayTheBestPetWin().calc(A, B));
        }
        case 1: {
            int[] A                   = {1,3,5,4,5};
            int[] B                   = {2,5,6,8,7};
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new MayTheBestPetWin().calc(A, B));
        }
        case 2: {
            int[] A                   = {2907,949,1674,6092,8608,5186,2630,970,1050,2415,1923,2697,5571,6941,8065,4710,716,756,5185,1341,993,5092,248,1895,4223,1783,3844,3531,2431,1755,2837,4015};
            int[] B                   = {7296,6954,4407,9724,8645,8065,9323,8433,1352,9618,6487,7309,9297,8999,9960,5653,4721,7623,6017,7320,3513,6642,6359,3145,7233,5077,6457,3605,2911,4679,5381,6574};
            int expected__            = 52873;

            return verifyCase(casenum__, expected__, new MayTheBestPetWin().calc(A, B));
        }

        // custom cases

/*      case 3: {
            int[] A                   = ;
            int[] B                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new MayTheBestPetWin().calc(A, B));
        }*/
/*      case 4: {
            int[] A                   = ;
            int[] B                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new MayTheBestPetWin().calc(A, B));
        }*/
/*      case 5: {
            int[] A                   = ;
            int[] B                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new MayTheBestPetWin().calc(A, B));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
