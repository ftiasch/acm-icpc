import java.math.*;
import java.util.*;

public class ApplesAndOrangesEasy {
    public int maximumApples(int n, int m, int[] as) {
        int m2 = m / 2;
        int[] v = new int[n];
        for (int a : as) {
            v[a - 1] = 1;
        }
        int result = 0;
        for (int i = 0; i < n; ++ i) {
            if (v[i] == 0) {
                v[i] = 1;
                boolean valid = true;
                int count = 0;
                for (int j = 0; j < n; ++ j) {
                    count += v[j];
                    if (j >= m) {
                        count -= v[j - m];
                    }
                    valid &= count <= m2;
                }
                if (!valid) {
                    v[i] = 0;
                }
            }
            result += v[i];
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            ApplesAndOrangesEasyHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                ApplesAndOrangesEasyHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class ApplesAndOrangesEasyHarness {
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
            int N                     = 3;
            int K                     = 2;
            int[] info                = {};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new ApplesAndOrangesEasy().maximumApples(N, K, info));
        }
        case 1: {
            int N                     = 10;
            int K                     = 3;
            int[] info                = {3, 8};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new ApplesAndOrangesEasy().maximumApples(N, K, info));
        }
        case 2: {
            int N                     = 9;
            int K                     = 4;
            int[] info                = {1, 4};
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new ApplesAndOrangesEasy().maximumApples(N, K, info));
        }
        case 3: {
            int N                     = 9;
            int K                     = 4;
            int[] info                = {2, 4};
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new ApplesAndOrangesEasy().maximumApples(N, K, info));
        }
        case 4: {
            int N                     = 23;
            int K                     = 7;
            int[] info                = {3, 2, 9, 1, 15, 23, 20, 19};
            int expected__            = 10;

            return verifyCase(casenum__, expected__, new ApplesAndOrangesEasy().maximumApples(N, K, info));
        }

        // custom cases

/*      case 5: {
            int N                     = ;
            int K                     = ;
            int[] info                = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ApplesAndOrangesEasy().maximumApples(N, K, info));
        }*/
/*      case 6: {
            int N                     = ;
            int K                     = ;
            int[] info                = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ApplesAndOrangesEasy().maximumApples(N, K, info));
        }*/
/*      case 7: {
            int N                     = ;
            int K                     = ;
            int[] info                = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ApplesAndOrangesEasy().maximumApples(N, K, info));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
