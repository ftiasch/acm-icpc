import java.math.*;
import java.util.*;

public class ORSolitaire {
    public int getMinimum(int[] numbers, int goal) {
        int n = numbers.length;
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < 30; ++ i) {
            if ((goal >> i & 1) == 1) {
                int count = 0;
                for (int number : numbers) {
                    if ((goal & number) == number && (number >> i & 1) == 1) {
                        count ++;
                    }
                }
                result = Math.min(result, count);
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
            ORSolitaireHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                ORSolitaireHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class ORSolitaireHarness {
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
            int[] numbers             = {1, 2, 4};
            int goal                  = 7;
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new ORSolitaire().getMinimum(numbers, goal));
        }
        case 1: {
            int[] numbers             = {1, 2, 4, 7, 8};
            int goal                  = 7;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new ORSolitaire().getMinimum(numbers, goal));
        }
        case 2: {
            int[] numbers             = {12571295, 2174218, 2015120};
            int goal                  = 1;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new ORSolitaire().getMinimum(numbers, goal));
        }
        case 3: {
            int[] numbers             = {5,2,4,52,62,9,8,3,1,11,6};
            int goal                  = 11;
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new ORSolitaire().getMinimum(numbers, goal));
        }
        case 4: {
            int[] numbers             = {503, 505, 152, 435, 491, 512, 1023, 355, 510, 500, 502, 255, 63, 508, 509, 511, 60, 250, 254, 346};
            int goal                  = 510;
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new ORSolitaire().getMinimum(numbers, goal));
        }

        // custom cases

/*      case 5: {
            int[] numbers             = ;
            int goal                  = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ORSolitaire().getMinimum(numbers, goal));
        }*/
/*      case 6: {
            int[] numbers             = ;
            int goal                  = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ORSolitaire().getMinimum(numbers, goal));
        }*/
/*      case 7: {
            int[] numbers             = ;
            int goal                  = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ORSolitaire().getMinimum(numbers, goal));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
