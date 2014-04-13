import java.math.*;
import java.util.*;

public class UndoHistory {
    public int minPresses(String[] lines) {
        int n = lines.length;
        int result = n;
        for (int i = 0; i < n; ++ i) {
            int need = Integer.MAX_VALUE;
            if (i == 0) {
                need = Math.min(need, lines[i].length());
            }
            if (i > 0 && lines[i].startsWith(lines[i - 1])) {
                need = Math.min(need, lines[i].length() - lines[i - 1].length());
            }
            for (int j = 0; j < i; ++ j) {
                int k = 0;
                while (k < lines[j].length() && k < lines[i].length() && lines[j].charAt(k) == lines[i].charAt(k)) {
                    k ++;
                }
                need = Math.min(need, 2 + lines[i].length() - k);
            }
            result += need;
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            UndoHistoryHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                UndoHistoryHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class UndoHistoryHarness {
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
            String[] lines            = {"tomorrow", "topcoder"};
            int expected__            = 18;

            return verifyCase(casenum__, expected__, new UndoHistory().minPresses(lines));
        }
        case 1: {
            String[] lines            = {"a","b"};
            int expected__            = 6;

            return verifyCase(casenum__, expected__, new UndoHistory().minPresses(lines));
        }
        case 2: {
            String[] lines            = {"a", "ab", "abac", "abacus" };
            int expected__            = 10;

            return verifyCase(casenum__, expected__, new UndoHistory().minPresses(lines));
        }
        case 3: {
            String[] lines            = {"pyramid", "sphinx", "sphere", "python", "serpent"};
            int expected__            = 39;

            return verifyCase(casenum__, expected__, new UndoHistory().minPresses(lines));
        }
        case 4: {
            String[] lines            = {"ba","a","a","b","ba"}
;
            int expected__            = 13;

            return verifyCase(casenum__, expected__, new UndoHistory().minPresses(lines));
        }

        // custom cases

/*      case 5: {
            String[] lines            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new UndoHistory().minPresses(lines));
        }*/
/*      case 6: {
            String[] lines            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new UndoHistory().minPresses(lines));
        }*/
/*      case 7: {
            String[] lines            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new UndoHistory().minPresses(lines));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
