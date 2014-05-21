import java.math.*;
import java.util.*;

public class ConvexSequence {
    public long getMinimum(int[] a) {
        int n = a.length;
        long answer = 0;
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 1; i + 1 < n; ++ i) {
                int upperBound = (a[i - 1] + a[i + 1]) / 2;
                if (upperBound < a[i]) {
                    changed = true;
                    answer += a[i] - upperBound;
                    a[i] = upperBound;
                }
            }
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            ConvexSequenceHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                ConvexSequenceHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class ConvexSequenceHarness {
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
            int[] a                   = {6,5,1,5,3,3};
            long expected__           = 7;

            return verifyCase(casenum__, expected__, new ConvexSequence().getMinimum(a));
        }
        case 1: {
            int[] a                   = {3,0,1,4};
            long expected__           = 0;

            return verifyCase(casenum__, expected__, new ConvexSequence().getMinimum(a));
        }
        case 2: {
            int[] a                   = {1,1,1,0,2,2,2};
            long expected__           = 5;

            return verifyCase(casenum__, expected__, new ConvexSequence().getMinimum(a));
        }
        case 3: {
            int[] a                   = {854159326, 317144183, 781399725, 287076509, 894967145, 882577367, 174517516, 134415519, 274494874, 709819883, 59717133, 732212854, 40551288, 232526958, 811785438, 930853743, 946882902, 321325300, 397702677, 376192501, 599310562, 889156198, 135776890, 882710939, 823196361, 681959076, 318666702, 94469186, 536320456, 116468376, 530320850, 436708006, 903344748, 659080120, 774722507, 967315412, 566063635, 43970906, 497687103, 781266213, 876086123, 366960001, 587364849, 191948103, 172568553, 539762057, 83507466, 71569625, 686305822, 663789601};
            long expected__           = 20178337330L;

            return verifyCase(casenum__, expected__, new ConvexSequence().getMinimum(a));
        }
        case 4: {
            int[] a                   = {5};
            long expected__           = 0;

            return verifyCase(casenum__, expected__, new ConvexSequence().getMinimum(a));
        }

        // custom cases

/*      case 5: {
            int[] a                   = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new ConvexSequence().getMinimum(a));
        }*/
/*      case 6: {
            int[] a                   = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new ConvexSequence().getMinimum(a));
        }*/
/*      case 7: {
            int[] a                   = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new ConvexSequence().getMinimum(a));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
