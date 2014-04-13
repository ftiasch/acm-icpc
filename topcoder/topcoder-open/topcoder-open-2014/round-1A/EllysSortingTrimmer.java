import java.math.*;
import java.util.*;

public class EllysSortingTrimmer {
    public String getMin(String s, int l) {
        while (s.length() > l) {
            int n = s.length();
            s = (s.substring(0, n - l) + sort(s.substring(n - l))).substring(0, n - 1);
        }
        return sort(s);
    }

    String sort(String s) {
        char[] buffer = s.toCharArray();
        Arrays.sort(buffer);
        return new String(buffer);
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            EllysSortingTrimmerHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                EllysSortingTrimmerHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class EllysSortingTrimmerHarness {
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
            String S                  = "ABRACADABRA";
            int L                     = 5;
            String expected__         = "AAAAA";

            return verifyCase(casenum__, expected__, new EllysSortingTrimmer().getMin(S, L));
        }
        case 1: {
            String S                  = "ESPRIT";
            int L                     = 3;
            String expected__         = "EIP";

            return verifyCase(casenum__, expected__, new EllysSortingTrimmer().getMin(S, L));
        }
        case 2: {
            String S                  = "BAZINGA";
            int L                     = 7;
            String expected__         = "AABGINZ";

            return verifyCase(casenum__, expected__, new EllysSortingTrimmer().getMin(S, L));
        }
        case 3: {
            String S                  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            int L                     = 13;
            String expected__         = "ABCDEFGHIJKLM";

            return verifyCase(casenum__, expected__, new EllysSortingTrimmer().getMin(S, L));
        }
        case 4: {
            String S                  = "GOODLUCKANDHAVEFUN";
            int L                     = 10;
            String expected__         = "AACDDEFGHK";

            return verifyCase(casenum__, expected__, new EllysSortingTrimmer().getMin(S, L));
        }
        case 5: {
            String S                  = "AAAWDIUAOIWDESBEAIWODJAWDBPOAWDUISAWDOOPAWD";
            int L                     = 21;
            String expected__         = "AAAAAAAAABBDDDDDDDEEI";

            return verifyCase(casenum__, expected__, new EllysSortingTrimmer().getMin(S, L));
        }
        case 6: {
            String S                  = "TOPCODER";
            int L                     = 3;
            String expected__         = "CDT";

            return verifyCase(casenum__, expected__, new EllysSortingTrimmer().getMin(S, L));
        }

        // custom cases

/*      case 7: {
            String S                  = ;
            int L                     = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new EllysSortingTrimmer().getMin(S, L));
        }*/
/*      case 8: {
            String S                  = ;
            int L                     = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new EllysSortingTrimmer().getMin(S, L));
        }*/
/*      case 9: {
            String S                  = ;
            int L                     = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new EllysSortingTrimmer().getMin(S, L));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
