import java.math.*;
import java.util.*;

public class SuffixArrayDiv1 {
    public int minimalCharacters(int[] array) {
        int n = array.length;
        int[] position = new int[n + 1];
        for (int i = 0; i < n; ++ i) {
            position[array[i]] = i;
        }
        position[n] = -1;
        int[] minimum = new int[n + 1];
        for (int i = n - 1; i >= 0; -- i) {
            minimum[i] = Integer.MAX_VALUE;
            for (int j = i + 1; j <= n; ++ j) {
                boolean valid = true;
                for (int x = i; x < j; ++ x) {
                    for (int y = x + 1; y < j; ++ y) {
                        valid &= position[array[x] + 1] < position[array[y] + 1];
                    }
                }
                if (valid && minimum[j] < Integer.MAX_VALUE) {
                    minimum[i] = Math.min(minimum[i], minimum[j] + 1);
                }
            }
        }
        return minimum[0];
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            SuffixArrayDiv1Harness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                SuffixArrayDiv1Harness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class SuffixArrayDiv1Harness {
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
            int[] SA                  = {3,0,1,2};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new SuffixArrayDiv1().minimalCharacters(SA));
        }
        case 1: {
            int[] SA                  = {3,2,1,0};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new SuffixArrayDiv1().minimalCharacters(SA));
        }
        case 2: {
            int[] SA                  = {0,1,2,3};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new SuffixArrayDiv1().minimalCharacters(SA));
        }
        case 3: {
            int[] SA                  = {7,4,8,6,1,5,2,9,3,0};
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new SuffixArrayDiv1().minimalCharacters(SA));
        }
        case 4: {
            int[] SA                  = {0};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new SuffixArrayDiv1().minimalCharacters(SA));
        }

        // custom cases

/*      case 5: {
            int[] SA                  = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new SuffixArrayDiv1().minimalCharacters(SA));
        }*/
/*      case 6: {
            int[] SA                  = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new SuffixArrayDiv1().minimalCharacters(SA));
        }*/
/*      case 7: {
            int[] SA                  = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new SuffixArrayDiv1().minimalCharacters(SA));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
