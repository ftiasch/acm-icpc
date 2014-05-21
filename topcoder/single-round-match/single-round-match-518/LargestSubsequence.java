import java.math.*;
import java.util.*;

public class LargestSubsequence {
    public String getLargest(String s) {
        String answer = "";
        for (int i = s.length() - 1; i >= 0; -- i) {
            String now = s.charAt(i) + answer;
            if (now.compareTo(answer) > 0) {
                answer = now;
            }
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            LargestSubsequenceHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                LargestSubsequenceHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class LargestSubsequenceHarness {
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
            String s                  = "test";
            String expected__         = "tt";

            return verifyCase(casenum__, expected__, new LargestSubsequence().getLargest(s));
        }
        case 1: {
            String s                  = "a";
            String expected__         = "a";

            return verifyCase(casenum__, expected__, new LargestSubsequence().getLargest(s));
        }
        case 2: {
            String s                  = "example";
            String expected__         = "xple";

            return verifyCase(casenum__, expected__, new LargestSubsequence().getLargest(s));
        }
        case 3: {
            String s                  = "aquickbrownfoxjumpsoverthelazydog";
            String expected__         = "zyog";

            return verifyCase(casenum__, expected__, new LargestSubsequence().getLargest(s));
        }

        // custom cases

/*      case 4: {
            String s                  = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new LargestSubsequence().getLargest(s));
        }*/
/*      case 5: {
            String s                  = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new LargestSubsequence().getLargest(s));
        }*/
/*      case 6: {
            String s                  = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new LargestSubsequence().getLargest(s));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
