import java.math.*;
import java.util.*;

public class FoxAndAvatar {
    boolean check1(int w, int n, int x) {
        if (x == 0) {
            return w == 1 || n <= w;
        } else if (x == n - 1) {
            return x % w == 0 || n <= w;
        } else {
            return x % w == w - 1 && n < w * 2;
        }
    }

    int search(int w, int max, int n, int x) {
        assert 0 <= x && x < n;
        if (n == 1) {
            return 0;
        }
        if (check1(w, n, x)) {
            return 1;
        }
        int result = 4;
        if (max > 1) {
            for (int i = 1; i <= x / w; ++ i) {
                for (int j = 1; j <= w; ++ j) {
                    result = Math.min(result, search(w, max - 1, n - i * j, x - i * j) + 1);
                }
            }
            if ((x / w + 1) * w < n) {
                result = Math.min(result, search(w, max - 1, (x / w + 1) * w, x) + 1);
            }
            for (int i = 1; i <= x / w + 1; ++ i) {
                for (int j = 1; j <= x % w; ++ j) {
                    result = Math.min(result, search(w, max - 1, n - (x / w < (n - 1) / w ? Math.min(j, n % w) : 0) - (i + Math.max(n / w - x / w - 1, 0)) * j, x - i * j) + 1);
                }
            }
            for (int i = 1; i <= x / w + 1; ++ i) {
                for (int j = 1; j <= w - 1 - x % w; ++ j) {
                    result = Math.min(result, search(w, max - 1, n - Math.min(j, Math.max(n % w - x % w - 1, 0)) - (i - 1 + n / w - x / w) * j, x - (i - 1) * j) + 1);
                }
            }
        }
        return result;
    }

    public int minimalSteps(int n, int width, int x) {
        //return search(width, 2, n, x - 1);
        return Math.min(search(width, 3, n, x - 1), 4);
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            FoxAndAvatarHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                FoxAndAvatarHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class FoxAndAvatarHarness {
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
            int n                     = 3;
            int width                 = 2;
            int x                     = 3;
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new FoxAndAvatar().minimalSteps(n, width, x));
        }
        case 1: {
            int n                     = 5;
            int width                 = 2;
            int x                     = 3;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new FoxAndAvatar().minimalSteps(n, width, x));
        }
        case 2: {
            int n                     = 13;
            int width                 = 3;
            int x                     = 8;
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new FoxAndAvatar().minimalSteps(n, width, x));
        }
        case 3: {
            int n                     = 54;
            int width                 = 6;
            int x                     = 28;
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new FoxAndAvatar().minimalSteps(n, width, x));
        }
        case 4: {
            int n                     = 1;
            int width                 = 1;
            int x                     = 1;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new FoxAndAvatar().minimalSteps(n, width, x));
        }

        // custom cases

        case 5: {
            int n                     = 1164;
            int width                 = 490;
            int x                     = 572;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new FoxAndAvatar().minimalSteps(n, width, x));
        }
        case 6: {
            int n                     = 3000;
            int width                 = 7;
            int x                     = 16;
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new FoxAndAvatar().minimalSteps(n, width, x));
        }
        case 7: {
            int n                     = 2000;
            int width                 = 661;
            int x                     = 1162;
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new FoxAndAvatar().minimalSteps(n, width, x));
        }
        case 8: {
            int n                     = 7;
            int width                 = 4;
            int x                     = 3;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new FoxAndAvatar().minimalSteps(n, width, x));
        }
        default:
            return -1;
        }
    }
}

// END CUT HERE
