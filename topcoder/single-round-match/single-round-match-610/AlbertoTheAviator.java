import java.math.*;
import java.util.*;

public class AlbertoTheAviator {
    public int MaximumFlights(int init, int[] duration, int[] refuel) {
        int n = duration.length;
        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; ++ i) {
            pairs[i] = new Pair(duration[i], refuel[i]);
        }
        Arrays.sort(pairs);
        int[] maximum = new int[init + 1];
        for (Pair pair : pairs) {
            for (int i = pair.refuel; i - pair.refuel + pair.duration <= init; ++ i) {
                maximum[i] = Math.max(maximum[i], maximum[i - pair.refuel + pair.duration] + 1);
            }
        }
        int result = 0;
        for (int i = 0; i <= init; ++ i) {
            result = Math.max(result, maximum[i]);
        }
        return result;
    }

    class Pair implements Comparable {
        Pair(int duration, int refuel) {
            this.duration = duration;
            this.refuel = refuel;
        }

        @Override
        public int compareTo(Object other) {
            return ((Pair)other).refuel - refuel;
        }

        int duration, refuel;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            AlbertoTheAviatorHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                AlbertoTheAviatorHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class AlbertoTheAviatorHarness {
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
            int F                     = 10;
            int[] duration            = {10};
            int[] refuel              = {0};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new AlbertoTheAviator().MaximumFlights(F, duration, refuel));
        }
        case 1: {
            int F                     = 10;
            int[] duration            = {8, 4};
            int[] refuel              = {0, 2};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new AlbertoTheAviator().MaximumFlights(F, duration, refuel));
        }
        case 2: {
            int F                     = 12;
            int[] duration            = {4, 8, 2, 1};
            int[] refuel              = {2, 0, 0, 0};
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new AlbertoTheAviator().MaximumFlights(F, duration, refuel));
        }
        case 3: {
            int F                     = 9;
            int[] duration            = {4, 6};
            int[] refuel              = {0, 1};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new AlbertoTheAviator().MaximumFlights(F, duration, refuel));
        }
        case 4: {
            int F                     = 100;
            int[] duration            = {101};
            int[] refuel              = {100};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new AlbertoTheAviator().MaximumFlights(F, duration, refuel));
        }
        case 5: {
            int F                     = 1947;
            int[] duration            = {2407, 2979, 1269, 2401, 3227, 2230, 3991, 2133, 3338, 356, 2535, 3859, 3267, 365};
            int[] refuel              = {2406, 793, 905, 2400, 1789, 2229, 1378, 2132, 1815, 355, 72, 3858, 3266, 364};
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new AlbertoTheAviator().MaximumFlights(F, duration, refuel));
        }

        // custom cases

/*      case 6: {
            int F                     = ;
            int[] duration            = ;
            int[] refuel              = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new AlbertoTheAviator().MaximumFlights(F, duration, refuel));
        }*/
/*      case 7: {
            int F                     = ;
            int[] duration            = ;
            int[] refuel              = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new AlbertoTheAviator().MaximumFlights(F, duration, refuel));
        }*/
/*      case 8: {
            int F                     = ;
            int[] duration            = ;
            int[] refuel              = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new AlbertoTheAviator().MaximumFlights(F, duration, refuel));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
