import java.math.*;
import java.util.*;

public class MovingRooksDiv1 {
    public int[] move(int[] y1, int[] y2) {
        int n = y1.length;
        List <Integer> result = new ArrayList <Integer>();
        for (int i = 0; i < n; ++ i) {
            if (y1[i] < y2[i]) {
                return new int[]{-1};
            }
            for (int j = i + 1; j < n && y1[i] > y2[i]; ++ j) {
                if (y1[i] > y1[j] && y1[j] >= y2[i]) {
                    result.add(i);
                    result.add(j);
                    y1[i] ^= y1[j];
                    y1[j] ^= y1[i];
                    y1[i] ^= y1[j];
                }
            }
        }
        int m = Math.min(result.size(), 2500);
        int[] returned = new int[m];
        for (int i = 0; i < m; ++ i) {
            returned[i] = result.get(i);
        }
        return returned;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            MovingRooksDiv1Harness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                MovingRooksDiv1Harness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class MovingRooksDiv1Harness {
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

    static boolean compareOutput(int[] expected, int[] result) { if (expected.length != result.length) return false; for (int i=0; i<expected.length; ++i) if (expected[i] != result[i]) return false; return true; }

    static String formatResult(int[] res) {
        String ret = "";
        ret += "{";
        for (int i=0; i<res.length; ++i) {
            if (i > 0) ret += ",";
            ret += String.format(" %d", res[i]);
        }
        ret += " }";
        return ret;
    }

    static int verifyCase(int casenum, int[] expected, int[] received) {
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
            int[] Y1                  = {0};
            int[] Y2                  = {0};
            int[] expected__          = { };

            return verifyCase(casenum__, expected__, new MovingRooksDiv1().move(Y1, Y2));
        }
        case 1: {
            int[] Y1                  = {1,0};
            int[] Y2                  = {0,1};
            int[] expected__          = {0, 1 };

            return verifyCase(casenum__, expected__, new MovingRooksDiv1().move(Y1, Y2));
        }
        case 2: {
            int[] Y1                  = {1,2,0};
            int[] Y2                  = {2,0,1};
            int[] expected__          = {-1 };

            return verifyCase(casenum__, expected__, new MovingRooksDiv1().move(Y1, Y2));
        }
        case 3: {
            int[] Y1                  = {2,1,0,3,5,4};
            int[] Y2                  = {0,1,2,3,4,5};
            int[] expected__          = {0, 1, 0, 2, 1, 2, 4, 5 };

            return verifyCase(casenum__, expected__, new MovingRooksDiv1().move(Y1, Y2));
        }
        case 4: {
            int[] Y1                  = {10,9,8,7,6,5,4,3,2,1,0};
            int[] Y2                  = {0,1,2,3,4,5,6,7,8,9,10};
            int[] expected__          = {0, 1, 0, 2, 0, 3, 0, 4, 0, 5, 0, 6, 0, 7, 0, 8, 0, 9, 0, 10, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7, 1, 8, 1, 9, 1, 10, 2, 3, 2, 4, 2, 5, 2, 6, 2, 7, 2, 8, 2, 9, 2, 10, 3, 4, 3, 5, 3, 6, 3, 7, 3, 8, 3, 9, 3, 10, 4, 5, 4, 6, 4, 7, 4, 8, 4, 9, 4, 10, 5, 6, 5, 7, 5, 8, 5, 9, 5, 10, 6, 7, 6, 8, 6, 9, 6, 10, 7, 8, 7, 9, 7, 10, 8, 9, 8, 10, 9, 10 };

            return verifyCase(casenum__, expected__, new MovingRooksDiv1().move(Y1, Y2));
        }

        // custom cases

/*      case 5: {
            int[] Y1                  = ;
            int[] Y2                  = ;
            int[] expected__          = ;

            return verifyCase(casenum__, expected__, new MovingRooksDiv1().move(Y1, Y2));
        }*/
/*      case 6: {
            int[] Y1                  = ;
            int[] Y2                  = ;
            int[] expected__          = ;

            return verifyCase(casenum__, expected__, new MovingRooksDiv1().move(Y1, Y2));
        }*/
/*      case 7: {
            int[] Y1                  = ;
            int[] Y2                  = ;
            int[] expected__          = ;

            return verifyCase(casenum__, expected__, new MovingRooksDiv1().move(Y1, Y2));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
