import java.math.*;
import java.util.*;

public class IncrementAndDoubling {
    public int getMin(int[] array) {
        int n = array.length;
        int result = 0;
        while (true) {
            boolean found = false;
            for (int i = 0; i < n; ++ i) {
                if (array[i] % 2 == 1) {
                    result ++;
                    array[i] --;
                }
                found |= array[i] > 0;
            }
            if (!found) {
                break;
            }
            result ++;
            for (int i = 0; i < n; ++ i) {
                array[i] /= 2;
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
            IncrementAndDoublingHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                IncrementAndDoublingHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class IncrementAndDoublingHarness {
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
            int[] desiredArray        = {2, 1};
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new IncrementAndDoubling().getMin(desiredArray));
        }
        case 1: {
            int[] desiredArray        = {16, 16, 16};
            int expected__            = 7;

            return verifyCase(casenum__, expected__, new IncrementAndDoubling().getMin(desiredArray));
        }
        case 2: {
            int[] desiredArray        = {100};
            int expected__            = 9;

            return verifyCase(casenum__, expected__, new IncrementAndDoubling().getMin(desiredArray));
        }
        case 3: {
            int[] desiredArray        = {0, 0, 1, 0, 1};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new IncrementAndDoubling().getMin(desiredArray));
        }
        case 4: {
            int[] desiredArray        = {123, 234, 345, 456, 567, 789};
            int expected__            = 40;

            return verifyCase(casenum__, expected__, new IncrementAndDoubling().getMin(desiredArray));
        }
        case 5: {
            int[] desiredArray        = {7,5,8,1,8,6,6,5,3,5,5,2,8,9,9,4,6,9,4,4,1,9,9,2,8,4,7,4,8,8,6,3,9,4,3,4,5,1,9,8,3,8,3,7,9,3,8,4,4,7};
            int expected__            = 84;

            return verifyCase(casenum__, expected__, new IncrementAndDoubling().getMin(desiredArray));
        }

        // custom cases

/*      case 6: {
            int[] desiredArray        = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new IncrementAndDoubling().getMin(desiredArray));
        }*/
/*      case 7: {
            int[] desiredArray        = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new IncrementAndDoubling().getMin(desiredArray));
        }*/
/*      case 8: {
            int[] desiredArray        = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new IncrementAndDoubling().getMin(desiredArray));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
