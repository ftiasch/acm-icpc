import java.math.*;
import java.util.*;

public class SkiResorts {
    public long minCost(String[] road, int[] altitude) {
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            SkiResortsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                SkiResortsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class SkiResortsHarness {
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

    static boolean compareOutput(long expected, long result) { return expected == result; }
    static String formatResult(long res) {
        return String.format("%d", res);
    }

    static int verifyCase(int casenum, long expected, long received) {
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
            String[] road             = {"NYN",
 "YNY",
 "NYN"};
            int[] altitude            = {30, 20, 10};
            long expected__           = 0;

            return verifyCase(casenum__, expected__, new SkiResorts().minCost(road, altitude));
        }
        case 1: {
            String[] road             = {"NY",
 "YN"};
            int[] altitude            = {10, 20};
            long expected__           = 10;

            return verifyCase(casenum__, expected__, new SkiResorts().minCost(road, altitude));
        }
        case 2: {
            String[] road             = {"NYN",
 "YNN",
 "NNN"};
            int[] altitude            = {573, 573, 573};
            long expected__           = -1;

            return verifyCase(casenum__, expected__, new SkiResorts().minCost(road, altitude));
        }
        case 3: {
            String[] road             = {"NNYNNYYYNN",
 "NNNNYNYNNN",
 "YNNNNYYNNN",
 "NNNNNNYNYY",
 "NYNNNNNNYY",
 "YNYNNNNYNN",
 "YYYYNNNYNN",
 "YNNNNYYNNN",
 "NNNYYNNNNN",
 "NNNYYNNNNN"};
            int[] altitude            = {7, 4, 13, 2, 8, 1, 8, 15, 5, 15};
            long expected__           = 12;

            return verifyCase(casenum__, expected__, new SkiResorts().minCost(road, altitude));
        }

        // custom cases

/*      case 4: {
            String[] road             = ;
            int[] altitude            = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new SkiResorts().minCost(road, altitude));
        }*/
/*      case 5: {
            String[] road             = ;
            int[] altitude            = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new SkiResorts().minCost(road, altitude));
        }*/
/*      case 6: {
            String[] road             = ;
            int[] altitude            = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new SkiResorts().minCost(road, altitude));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
