import java.math.*;
import java.util.*;

public class KnightCircuit2 {
    public int maxSize(int w, int h) {
        if (w > h) {
            return maxSize(h, w);
        }
        if (w == 1) {
            return 1;
        }
        if (w == 2) {
            return (h + 1) / 2;
        }
        if (w == 3) {
            assert h >= 3;
            if (h == 3) {
                return 8;
            }
        }
        return w * h;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            KnightCircuit2Harness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                KnightCircuit2Harness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class KnightCircuit2Harness {
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
            int w                     = 1;
            int h                     = 1;
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new KnightCircuit2().maxSize(w, h));
        }
        case 1: {
            int w                     = 15;
            int h                     = 2;
            int expected__            = 8;

            return verifyCase(casenum__, expected__, new KnightCircuit2().maxSize(w, h));
        }
        case 2: {
            int w                     = 100;
            int h                     = 100;
            int expected__            = 10000;

            return verifyCase(casenum__, expected__, new KnightCircuit2().maxSize(w, h));
        }

        // custom cases

/*      case 3: {
            int w                     = ;
            int h                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new KnightCircuit2().maxSize(w, h));
        }*/
/*      case 4: {
            int w                     = ;
            int h                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new KnightCircuit2().maxSize(w, h));
        }*/
/*      case 5: {
            int w                     = ;
            int h                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new KnightCircuit2().maxSize(w, h));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
