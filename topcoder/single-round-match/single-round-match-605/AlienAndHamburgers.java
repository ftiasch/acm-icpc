import java.math.*;
import java.util.*;

public class AlienAndHamburgers {
    public int getNumber(int[] type, int[] taste) {
        int[] total = new int[100];
        int[] maximum = new int[100];
        Arrays.fill(maximum, Integer.MIN_VALUE);
        for (int i = 0; i < type.length; ++ i) {
            int t = type[i] - 1;
            if (taste[i] > 0) {
                total[t] += taste[i];
            }
            maximum[t] = Math.max(maximum[t], taste[i]);
        }
        for (int i = 0; i < 100; ++ i) {
            if (total[i] == 0) {
                total[i] = maximum[i];
            }
        }
        Arrays.sort(total);
        int result = 0;
        for (int i = 99, sum = 0; i >= 0; -- i) {
            if (total[i] == Integer.MIN_VALUE) {
                break;
            }
            sum += total[i];
            result = Math.max(result, sum * (100 - i));
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            AlienAndHamburgersHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                AlienAndHamburgersHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class AlienAndHamburgersHarness {
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
            int[] type                = {1, 2};
            int[] taste               = {4, 7};
            int expected__            = 22;

            return verifyCase(casenum__, expected__, new AlienAndHamburgers().getNumber(type, taste));
        }
        case 1: {
            int[] type                = {1, 1};
            int[] taste               = {-1, -1};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new AlienAndHamburgers().getNumber(type, taste));
        }
        case 2: {
            int[] type                = {1, 2, 3};
            int[] taste               = {7, 4, -1};
            int expected__            = 30;

            return verifyCase(casenum__, expected__, new AlienAndHamburgers().getNumber(type, taste));
        }
        case 3: {
            int[] type                = {1, 2, 3, 2, 3, 1, 3, 2, 3, 1, 1, 1};
            int[] taste               = {1, 7, -2, 3, -4, -1, 3, 1, 3, -5, -1, 0};
            int expected__            = 54;

            return verifyCase(casenum__, expected__, new AlienAndHamburgers().getNumber(type, taste));
        }
        case 4: {
            int[] type                = {30, 20, 10};
            int[] taste               = {100000, -100000, 100000};
            int expected__            = 400000;

            return verifyCase(casenum__, expected__, new AlienAndHamburgers().getNumber(type, taste));
        }

        // custom cases

/*      case 5: {
            int[] type                = ;
            int[] taste               = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new AlienAndHamburgers().getNumber(type, taste));
        }*/
/*      case 6: {
            int[] type                = ;
            int[] taste               = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new AlienAndHamburgers().getNumber(type, taste));
        }*/
/*      case 7: {
            int[] type                = ;
            int[] taste               = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new AlienAndHamburgers().getNumber(type, taste));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
