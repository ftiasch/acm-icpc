import java.math.*;
import java.util.*;

public class MonstersValley {
    public int minimumPrice(long[] dread, int[] price) {
        int n = dread.length;
        int m = n << 1;
        long[] maximum = new long[m + 1];
        Arrays.fill(maximum, 0);
        for (int i = 0; i < n; ++ i) {
            long[] new_maximum = new long[m + 1];
            Arrays.fill(new_maximum, -1);
            for (int j = 0; j <= m; ++ j) {
                if (maximum[j] != -1) {
                    if (maximum[j] >= dread[i]) {
                        new_maximum[j] = Math.max(new_maximum[j], maximum[j]);
                    }
                    if (j + price[i] <= m) {
                        new_maximum[j + price[i]] = Math.max(new_maximum[j + price[i]], maximum[j] + dread[i]);
                    }
                }
            }
            maximum = new_maximum;
        }
        int answer = 0;
        while (maximum[answer] == -1) {
            answer ++;
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            MonstersValleyHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                MonstersValleyHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class MonstersValleyHarness {
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
            long[] dread              = {8, 5, 10};
            int[] price               = {1, 1, 2};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new MonstersValley().minimumPrice(dread, price));
        }
        case 1: {
            long[] dread              = {1, 2, 4, 1000000000};
            int[] price               = {1, 1, 1, 2};
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new MonstersValley().minimumPrice(dread, price));
        }
        case 2: {
            long[] dread              = {200, 107, 105, 206, 307, 400};
            int[] price               = {1, 2, 1, 1, 1, 2};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new MonstersValley().minimumPrice(dread, price));
        }
        case 3: {
            long[] dread              = {5216, 12512, 613, 1256, 66, 17202, 30000, 23512, 2125, 33333};
            int[] price               = {2, 2, 1, 1, 1, 1, 2, 1, 2, 1};
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new MonstersValley().minimumPrice(dread, price));
        }

        // custom cases

/*      case 4: {
            long[] dread              = {};
            int[] price               = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new MonstersValley().minimumPrice(dread, price));
        }*/
/*      case 5: {
            long[] dread              = {};
            int[] price               = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new MonstersValley().minimumPrice(dread, price));
        }*/
/*      case 6: {
            long[] dread              = {};
            int[] price               = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new MonstersValley().minimumPrice(dread, price));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
