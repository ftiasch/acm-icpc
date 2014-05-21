import java.math.*;
import java.util.*;

public class TheNumberGame {
    String reverse(String s) {
        return new StringBuffer(s).reverse().toString();
    }

    boolean determineOutcome(String a, String b) {
        return a.contains(b) || a.contains(reverse(b));
    }

    public String determineOutcome(int a, int b) {
        return "Manao " + (determineOutcome("" + a, "" + b) ? "wins" : "loses");
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            TheNumberGameHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                TheNumberGameHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class TheNumberGameHarness {
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

    static boolean compareOutput(String expected, String result) { return expected.equals(result); }
    static String formatResult(String res) {
        return String.format("\"%s\"", res);
    }

    static int verifyCase(int casenum, String expected, String received) {
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
            int A                     = 45;
            int B                     = 4;
            String expected__         = "Manao wins";

            return verifyCase(casenum__, expected__, new TheNumberGame().determineOutcome(A, B));
        }
        case 1: {
            int A                     = 45;
            int B                     = 5;
            String expected__         = "Manao wins";

            return verifyCase(casenum__, expected__, new TheNumberGame().determineOutcome(A, B));
        }
        case 2: {
            int A                     = 99;
            int B                     = 123;
            String expected__         = "Manao loses";

            return verifyCase(casenum__, expected__, new TheNumberGame().determineOutcome(A, B));
        }
        case 3: {
            int A                     = 2356236;
            int B                     = 5666;
            String expected__         = "Manao loses";

            return verifyCase(casenum__, expected__, new TheNumberGame().determineOutcome(A, B));
        }

        // custom cases

/*      case 4: {
            int A                     = ;
            int B                     = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new TheNumberGame().determineOutcome(A, B));
        }*/
/*      case 5: {
            int A                     = ;
            int B                     = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new TheNumberGame().determineOutcome(A, B));
        }*/
/*      case 6: {
            int A                     = ;
            int B                     = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new TheNumberGame().determineOutcome(A, B));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
