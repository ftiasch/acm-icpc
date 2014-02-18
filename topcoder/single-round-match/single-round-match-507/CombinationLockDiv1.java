import java.math.*;
import java.util.*;

public class CombinationLockDiv1 {
    public int minimumMoves(String[] pParts, String[] qParts) {
        int[] a = parse(pParts);
        int[] b = parse(qParts);
        int n = a.length;
        int[] minimum = new int[n * 20 + 1];
        Arrays.fill(minimum, Integer.MAX_VALUE);
        int zero = n * 10;
        minimum[zero] = 0;
        for (int i = 0, min = zero, max = zero; i < n; ++ i) {
            int[] newMinimum = new int[n * 20 + 1];
            Arrays.fill(newMinimum, Integer.MAX_VALUE);
            int d = (b[i] - a[i] + 10) % 10;
            int d2 = (10 - d) % 10;
            for (int j = min; j <= max; ++ j) {
                if (minimum[j] < Integer.MAX_VALUE) {
                    newMinimum[j + d] = Math.min(newMinimum[j + d], minimum[j] + d);
                    newMinimum[j - d2] = Math.min(newMinimum[j - d2], minimum[j] + d2);
                }
            }
            min -= d;
            max += d;
            minimum = newMinimum;
        }
        return minimum[zero] / 2;
    }

    int[] parse(String[] parts) {
        StringBuffer buffer = new StringBuffer();
        for (String part : parts) {
            buffer.append(part);
        }
        String string = buffer.toString();
        int n = string.length();
        int[] delta = new int[n + 1];
        delta[0] = string.charAt(0) - '0';
        for (int i = 1; i < n; ++ i) {
            delta[i] = (10 + string.charAt(i) - string.charAt(i - 1)) % 10;
        }
        delta[n] = (10 - (string.charAt(n - 1) - '0')) % 10;
        return delta;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            CombinationLockDiv1Harness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                CombinationLockDiv1Harness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class CombinationLockDiv1Harness {
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
            String[] P                = {"123"};
            String[] Q                = {"112"};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new CombinationLockDiv1().minimumMoves(P, Q));
        }
        case 1: {
            String[] P                = {"1"};
            String[] Q                = {"7"};
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new CombinationLockDiv1().minimumMoves(P, Q));
        }
        case 2: {
            String[] P                = {"6","07"};
            String[] Q                = {"","60","7"};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new CombinationLockDiv1().minimumMoves(P, Q));
        }
        case 3: {
            String[] P                = {"1234"};
            String[] Q                = {"4567"};
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new CombinationLockDiv1().minimumMoves(P, Q));
        }
        case 4: {
            String[] P                = {"020"};
            String[] Q                = {"909"};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new CombinationLockDiv1().minimumMoves(P, Q));
        }
        case 5: {
            String[] P                = {"4423232218340"};
            String[] Q                = {"6290421476245"};
            int expected__            = 18;

            return verifyCase(casenum__, expected__, new CombinationLockDiv1().minimumMoves(P, Q));
        }

        // custom cases

/*      case 6: {
            String[] P                = ;
            String[] Q                = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new CombinationLockDiv1().minimumMoves(P, Q));
        }*/
/*      case 7: {
            String[] P                = ;
            String[] Q                = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new CombinationLockDiv1().minimumMoves(P, Q));
        }*/
/*      case 8: {
            String[] P                = ;
            String[] Q                = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new CombinationLockDiv1().minimumMoves(P, Q));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
