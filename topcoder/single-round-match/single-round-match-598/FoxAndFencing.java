import java.math.*;
import java.util.*;

public class FoxAndFencing {
    static String[] PLAYER = {"Ciel", "Liss"};

    public String WhoCanWin(int m0, int m1, int r0, int r1, int d) {
        if (d <= m0 + r0) {
            return PLAYER[0];
        } else if (d + m0 <= m1 + r1) {
            return PLAYER[1];
        } else if (m0 > m1) {
            return m0 + r0 > m1 + m1 + r1 ? PLAYER[0] : "Draw";
        } else if (m0 < m1) {
            return m1 + r1 > m0 + m0 + r0 ? PLAYER[1] : "Draw";
        } else {
            return "Draw";
        }
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            FoxAndFencingHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                FoxAndFencingHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class FoxAndFencingHarness {
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
            int mov1                  = 1;
            int mov2                  = 58;
            int rng1                  = 1;
            int rng2                  = 58;
            int d                     = 2;
            String expected__         = "Ciel";

            return verifyCase(casenum__, expected__, new FoxAndFencing().WhoCanWin(mov1, mov2, rng1, rng2, d));
        }
        case 1: {
            int mov1                  = 2;
            int mov2                  = 1;
            int rng1                  = 1;
            int rng2                  = 100;
            int d                     = 50;
            String expected__         = "Liss";

            return verifyCase(casenum__, expected__, new FoxAndFencing().WhoCanWin(mov1, mov2, rng1, rng2, d));
        }
        case 2: {
            int mov1                  = 2;
            int mov2                  = 1;
            int rng1                  = 1;
            int rng2                  = 100;
            int d                     = 150;
            String expected__         = "Draw";

            return verifyCase(casenum__, expected__, new FoxAndFencing().WhoCanWin(mov1, mov2, rng1, rng2, d));
        }
        case 3: {
            int mov1                  = 100;
            int mov2                  = 100;
            int rng1                  = 100;
            int rng2                  = 100;
            int d                     = 100000000;
            String expected__         = "Draw";

            return verifyCase(casenum__, expected__, new FoxAndFencing().WhoCanWin(mov1, mov2, rng1, rng2, d));
        }
        case 4: {
            int mov1                  = 100;
            int mov2                  = 1;
            int rng1                  = 100;
            int rng2                  = 1;
            int d                     = 100000000;
            String expected__         = "Ciel";

            return verifyCase(casenum__, expected__, new FoxAndFencing().WhoCanWin(mov1, mov2, rng1, rng2, d));
        }
        case 5: {
            int mov1                  = 100;
            int mov2                  = 1;
            int rng1                  = 100;
            int rng2                  = 250;
            int d                     = 100000000;
            String expected__         = "Draw";

            return verifyCase(casenum__, expected__, new FoxAndFencing().WhoCanWin(mov1, mov2, rng1, rng2, d));
        }
        case 6: {
            int mov1                  = 100;
            int mov2                  = 1;
            int rng1                  = 100;
            int rng2                  = 150;
            int d                     = 100000000;
            String expected__         = "Ciel";

            return verifyCase(casenum__, expected__, new FoxAndFencing().WhoCanWin(mov1, mov2, rng1, rng2, d));
        }
        case 7: {
            int mov1                  = 100;
            int mov2                  = 50;
            int rng1                  = 100;
            int rng2                  = 1;
            int d                     = 100000000;
            String expected__         = "Ciel";

            return verifyCase(casenum__, expected__, new FoxAndFencing().WhoCanWin(mov1, mov2, rng1, rng2, d));
        }
        case 8: {
            int mov1                  = 100;
            int mov2                  = 150;
            int rng1                  = 100;
            int rng2                  = 1;
            int d                     = 100000000;
            String expected__         = "Draw";

            return verifyCase(casenum__, expected__, new FoxAndFencing().WhoCanWin(mov1, mov2, rng1, rng2, d));
        }

        // custom cases

/*      case 9: {
            int mov1                  = ;
            int mov2                  = ;
            int rng1                  = ;
            int rng2                  = ;
            int d                     = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new FoxAndFencing().WhoCanWin(mov1, mov2, rng1, rng2, d));
        }*/
/*      case 10: {
            int mov1                  = ;
            int mov2                  = ;
            int rng1                  = ;
            int rng2                  = ;
            int d                     = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new FoxAndFencing().WhoCanWin(mov1, mov2, rng1, rng2, d));
        }*/
/*      case 11: {
            int mov1                  = ;
            int mov2                  = ;
            int rng1                  = ;
            int rng2                  = ;
            int d                     = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new FoxAndFencing().WhoCanWin(mov1, mov2, rng1, rng2, d));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
