import java.math.*;
import java.util.*;

public class SpamChecker {
    public String spamCheck(String judgeLog, int good, int bad) {
        int score = 0;
        for (char c : judgeLog.toCharArray()) {
            if (c == 'o') {
                score += good;
            } else {
                score -= bad;
            }
            if (score < 0) {
                return "SPAM";
            }
        }
        return "NOT SPAM";
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            SpamCheckerHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                SpamCheckerHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class SpamCheckerHarness {
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
            String judgeLog           = "ooooxxxo";
            int good                  = 2;
            int bad                   = 3;
            String expected__         = "SPAM";

            return verifyCase(casenum__, expected__, new SpamChecker().spamCheck(judgeLog, good, bad));
        }
        case 1: {
            String judgeLog           = "ooooxxxo";
            int good                  = 3;
            int bad                   = 4;
            String expected__         = "NOT SPAM";

            return verifyCase(casenum__, expected__, new SpamChecker().spamCheck(judgeLog, good, bad));
        }
        case 2: {
            String judgeLog           = "xooooooooooooooooooooooooooo";
            int good                  = 1000;
            int bad                   = 1;
            String expected__         = "SPAM";

            return verifyCase(casenum__, expected__, new SpamChecker().spamCheck(judgeLog, good, bad));
        }
        case 3: {
            String judgeLog           = "oxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
            int good                  = 1000;
            int bad                   = 1;
            String expected__         = "NOT SPAM";

            return verifyCase(casenum__, expected__, new SpamChecker().spamCheck(judgeLog, good, bad));
        }
        case 4: {
            String judgeLog           = "ooxoxoxooxoxxoxoxooxoxoxoxxoxx";
            int good                  = 15;
            int bad                   = 17;
            String expected__         = "SPAM";

            return verifyCase(casenum__, expected__, new SpamChecker().spamCheck(judgeLog, good, bad));
        }
        case 5: {
            String judgeLog           = "oooxoxoxoxoxoxooxooxoxooxo";
            int good                  = 16;
            int bad                   = 18;
            String expected__         = "NOT SPAM";

            return verifyCase(casenum__, expected__, new SpamChecker().spamCheck(judgeLog, good, bad));
        }

        // custom cases

/*      case 6: {
            String judgeLog           = ;
            int good                  = ;
            int bad                   = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new SpamChecker().spamCheck(judgeLog, good, bad));
        }*/
/*      case 7: {
            String judgeLog           = ;
            int good                  = ;
            int bad                   = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new SpamChecker().spamCheck(judgeLog, good, bad));
        }*/
/*      case 8: {
            String judgeLog           = ;
            int good                  = ;
            int bad                   = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new SpamChecker().spamCheck(judgeLog, good, bad));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
