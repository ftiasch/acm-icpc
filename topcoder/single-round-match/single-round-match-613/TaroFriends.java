import java.math.*;
import java.util.*;

public class TaroFriends {
    public int getNumber(int[] coordinates, int x) {
        int n = coordinates.length;
        if (n == 1) {
            return 0;
        }
        Arrays.sort(coordinates);
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++ i) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < n; ++ j) {
                int y = j < i ? coordinates[j] + x : coordinates[j] - x;
                min = Math.min(min, y);
                max = Math.max(max, y);
            }
            result = Math.min(result, max - min);
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            TaroFriendsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                TaroFriendsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class TaroFriendsHarness {
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
            int[] coordinates         = {-3, 0, 1};
            int X                     = 3;
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new TaroFriends().getNumber(coordinates, X));
        }
        case 1: {
            int[] coordinates         = {4, 7, -7};
            int X                     = 5;
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new TaroFriends().getNumber(coordinates, X));
        }
        case 2: {
            int[] coordinates         = {-100000000, 100000000};
            int X                     = 100000000;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new TaroFriends().getNumber(coordinates, X));
        }
        case 3: {
            int[] coordinates         = {3, 7, 4, 6, -10, 7, 10, 9, -5};
            int X                     = 7;
            int expected__            = 7;

            return verifyCase(casenum__, expected__, new TaroFriends().getNumber(coordinates, X));
        }
        case 4: {
            int[] coordinates         = {-4, 0, 4, 0};
            int X                     = 4;
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new TaroFriends().getNumber(coordinates, X));
        }
        case 5: {
            int[] coordinates         = {7};
            int X                     = 0;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new TaroFriends().getNumber(coordinates, X));
        }

        // custom cases

/*      case 6: {
            int[] coordinates         = ;
            int X                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TaroFriends().getNumber(coordinates, X));
        }*/
/*      case 7: {
            int[] coordinates         = ;
            int X                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TaroFriends().getNumber(coordinates, X));
        }*/
/*      case 8: {
            int[] coordinates         = ;
            int X                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TaroFriends().getNumber(coordinates, X));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
