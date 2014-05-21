import java.util.*;
import java.math.*;

public class TheArray {
    public int find(int n, int d, int first, int last) {
        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++ i) {
            answer = Math.max(answer, Math.min(first + i * d, last + (n - 1 - i) * d));
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            TheArrayHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                TheArrayHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class TheArrayHarness {
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
            int n                     = 3;
            int d                     = 5;
            int first                 = 2;
            int last                  = 4;
            int expected__            = 7;

            return verifyCase(casenum__, expected__, new TheArray().find(n, d, first, last));
        }
        case 1: {
            int n                     = 10;
            int d                     = 100;
            int first                 = 999;
            int last                  = 100;
            int expected__            = 999;

            return verifyCase(casenum__, expected__, new TheArray().find(n, d, first, last));
        }
        case 2: {
            int n                     = 1000000;
            int d                     = 0;
            int first                 = 474;
            int last                  = 474;
            int expected__            = 474;

            return verifyCase(casenum__, expected__, new TheArray().find(n, d, first, last));
        }
        case 3: {
            int n                     = 97;
            int d                     = 53;
            int first                 = -92;
            int last                  = 441;
            int expected__            = 2717;

            return verifyCase(casenum__, expected__, new TheArray().find(n, d, first, last));
        }
        case 4: {
            int n                     = 99;
            int d                     = 3;
            int first                 = -743;
            int last                  = -619;
            int expected__            = -535;

            return verifyCase(casenum__, expected__, new TheArray().find(n, d, first, last));
        }

        // custom cases

/*      case 5: {
            int n                     = ;
            int d                     = ;
            int first                 = ;
            int last                  = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TheArray().find(n, d, first, last));
        }*/
/*      case 6: {
            int n                     = ;
            int d                     = ;
            int first                 = ;
            int last                  = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TheArray().find(n, d, first, last));
        }*/
/*      case 7: {
            int n                     = ;
            int d                     = ;
            int first                 = ;
            int last                  = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TheArray().find(n, d, first, last));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
