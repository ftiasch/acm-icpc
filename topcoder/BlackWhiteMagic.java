import java.math.*;
import java.util.*;

public class BlackWhiteMagic {
    public int count(String creatures) {
        int n = creatures.length();
        int whiteCount = 0;
        for (int i = 0; i < n; ++ i) {
            if (creatures.charAt(i) == 'W') {
                whiteCount ++;
            }
        }
        int result = 0;
        for (int i = 0; i < whiteCount; ++ i) {
            if (creatures.charAt(i) == 'B') {
                result ++;
            }
        }
        return result;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            BlackWhiteMagicHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                BlackWhiteMagicHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class BlackWhiteMagicHarness {
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
            String creatures          = "WBBW";
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new BlackWhiteMagic().count(creatures));
        }
        case 1: {
            String creatures          = "WWWWBBBB";
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new BlackWhiteMagic().count(creatures));
        }
        case 2: {
            String creatures          = "BBWW";
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new BlackWhiteMagic().count(creatures));
        }
        case 3: {
            String creatures          = "BWWWWWWWBBBBBBBW";
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new BlackWhiteMagic().count(creatures));
        }
        case 4: {
            String creatures          = "WBWBWBWBWWBWBWBWBBBWBW";
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new BlackWhiteMagic().count(creatures));
        }

        // custom cases

/*      case 5: {
            String creatures          = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new BlackWhiteMagic().count(creatures));
        }*/
/*      case 6: {
            String creatures          = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new BlackWhiteMagic().count(creatures));
        }*/
/*      case 7: {
            String creatures          = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new BlackWhiteMagic().count(creatures));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
