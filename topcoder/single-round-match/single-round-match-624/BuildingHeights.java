import java.math.*;
import java.util.*;

public class BuildingHeights {
    public int minimum(int[] heights) {
        int n = heights.length;
        Arrays.sort(heights);
        int[] result = new int[n + 1];
        Arrays.fill(result, Integer.MAX_VALUE);
        for (int i = 0; i < n; ++ i) {
            int need = 0;
            for (int j = i; j >= 0; -- j) {
                need += heights[i] - heights[j];
                result[i - j + 1] = Math.min(result[i - j + 1], need);
            }
        }
        result[0] = 0;
        for (int i = 1; i <= n; ++ i) {
            result[0] ^= result[i];
        }
        return result[0];
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            BuildingHeightsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                BuildingHeightsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class BuildingHeightsHarness {
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
            int[] heights             = {1, 5, 4, 3, 8};
            int expected__            = 22;

            return verifyCase(casenum__, expected__, new BuildingHeights().minimum(heights));
        }
        case 1: {
            int[] heights             = {1, 2, 3};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new BuildingHeights().minimum(heights));
        }
        case 2: {
            int[] heights             = {3, 4, 1, 6, 8, 1};
            int expected__            = 21;

            return verifyCase(casenum__, expected__, new BuildingHeights().minimum(heights));
        }
        case 3: {
            int[] heights             = {990, 20, 2359, 1667, 51, 2455, 1659, 1093, 2015, 205, 656, 752, 1760, 1594, 857, 2033, 1868, 1932, 2408, 1518, 91, 2220, 1696, 1552, 933, 143, 1888, 1261, 2298, 1975, 929, 2139, 1994, 2139, 158, 896, 2093, 1816, 841, 459, 2020, 1496, 63, 131, 589, 919, 1015, 1308, 350, 922, 326, 1792, 641, 2021, 843, 425, 1015, 231, 1685, 2165, 1057, 1465, 655, 550, 1103, 812, 297, 2048, 1479, 1137, 6, 2350, 1484, 1420, 1332, 925, 2338, 1198, 2232, 1539, 2119, 57, 830, 1611, 929, 525, 888};
            int expected__            = 56068;

            return verifyCase(casenum__, expected__, new BuildingHeights().minimum(heights));
        }

        // custom cases

/*      case 4: {
            int[] heights             = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new BuildingHeights().minimum(heights));
        }*/
/*      case 5: {
            int[] heights             = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new BuildingHeights().minimum(heights));
        }*/
/*      case 6: {
            int[] heights             = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new BuildingHeights().minimum(heights));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
