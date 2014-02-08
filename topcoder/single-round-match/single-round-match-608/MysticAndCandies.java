import java.math.*;
import java.util.*;

public class MysticAndCandies {
    public int minBoxes(int total, int least, int[] low, int[] high) {
        int n = low.length;
        int result = n;
        Arrays.sort(low);
        for (int i = n - 1, sum = 0; i >= 0; -- i) {
            sum += low[i];
            if (sum >= least) {
                result = Math.min(result, n - i);
            }
        }
        Arrays.sort(high);
        for (int i = 0, sum = total; i < n; ++ i) {
            if (sum >= least) {
                result = Math.min(result, n - i);
            }
            sum -= high[i];
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            MysticAndCandiesHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                MysticAndCandiesHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class MysticAndCandiesHarness {
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
            int C                     = 15;
            int X                     = 12;
            int[] low                 = {1, 2, 3, 4, 5};
            int[] high                = {1, 2, 3, 4, 5};
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new MysticAndCandies().minBoxes(C, X, low, high));
        }
        case 1: {
            int C                     = 60;
            int X                     = 8;
            int[] low                 = {5, 2, 3};
            int[] high                = {49, 48, 47};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new MysticAndCandies().minBoxes(C, X, low, high));
        }
        case 2: {
            int C                     = 58;
            int X                     = 30;
            int[] low                 = {3, 9, 12, 6, 15};
            int[] high                = {8, 12, 20, 8, 15};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new MysticAndCandies().minBoxes(C, X, low, high));
        }
        case 3: {
            int C                     = 207581165;
            int X                     = 172146543;
            int[] low                 = {4725448, 2753824, 6019698, 4199708, 4070001, 3589497, 5358499, 3637585, 5393667, 2837466, 2747807, 2918199, 3638042, 5199002, 3072044, 3858909, 3762101, 3657754, 3218704, 3888861, 3195689, 4768935, 3137633, 4124272, 4125056, 6087486, 3632970, 3620489, 2748765, 5917493, 3958996, 3335021, 3517186, 5543440, 2951006, 3403270, 3299481, 3093204, 4092331};
            int[] high                = {5702812, 6805664, 6823687, 5337687, 4286533, 4999849, 6567411, 4563235, 6618139, 6260135, 6249469, 3821449, 5963157, 6385012, 4255959, 5786920, 6112817, 4103918, 6371537, 4231698, 3409172, 6806782, 5623563, 4511221, 6407338, 6491490, 5209517, 6076093, 6530132, 6111464, 5833839, 6253088, 5595160, 6236805, 5772388, 5285713, 5617002, 4650978, 5234740};
            int expected__            = 31;

            return verifyCase(casenum__, expected__, new MysticAndCandies().minBoxes(C, X, low, high));
        }
        case 4: {
            int C                     = 43873566;
            int X                     = 32789748;
            int[] low                 = {2053198, 2175819, 4260803, 1542497, 1418952, 5000015, 1381849, 2462882, 6466891, 1827580, 6943641, 5775477};
            int[] high                = {2827461, 3726335, 5410505, 4781355, 4925909, 5621160, 7325774, 5025476, 7876037, 8072075, 6979462, 6647628};
            int expected__            = 7;

            return verifyCase(casenum__, expected__, new MysticAndCandies().minBoxes(C, X, low, high));
        }

        // custom cases

/*      case 5: {
            int C                     = ;
            int X                     = ;
            int[] low                 = ;
            int[] high                = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new MysticAndCandies().minBoxes(C, X, low, high));
        }*/
/*      case 6: {
            int C                     = ;
            int X                     = ;
            int[] low                 = ;
            int[] high                = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new MysticAndCandies().minBoxes(C, X, low, high));
        }*/
/*      case 7: {
            int C                     = ;
            int X                     = ;
            int[] low                 = ;
            int[] high                = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new MysticAndCandies().minBoxes(C, X, low, high));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
