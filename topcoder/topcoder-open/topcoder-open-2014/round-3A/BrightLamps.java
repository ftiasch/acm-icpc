import java.math.*;
import java.util.*;

public class BrightLamps {
    int solve(int[] s, int[] a, int k) {
        int n = s.length;
        int[] parity = new int[k];
        for (int i = 0; i < n; ++ i) {
            parity[i % k] ^= s[i];
        }
        int result = 0;
        for (int i = 0; i < k; ++ i) {
            List <Integer> v = new ArrayList <Integer>();
            for (int j = i; j < n; j += k) {
                v.add(a[j]);
            }
            Collections.sort(v);
            for (int j = parity[i] ^ (v.size() & 1); j < v.size(); ++ j) {
                result += v.get(j);
            }
        }
        return result;
    }

    public int maxBrightness(String s, int[] a, int k) {
        int n = s.length();
        int[] b = new int[n];
        for (int i = 0; i < n; ++ i) {
            b[i] = s.charAt(i) - '0';
        }
        int result = solve(b, a, k);
        for (int i = 0; i < k; ++ i) {
            b[i] ^= 1;
        }
        result = Math.max(result, solve(b, a, k));
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            BrightLampsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                BrightLampsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class BrightLampsHarness {
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
            String init               = "011";
            int[] a                   = {3, 2, 4};
            int K                     = 2;
            int expected__            = 7;

            return verifyCase(casenum__, expected__, new BrightLamps().maxBrightness(init, a, K));
        }
        case 1: {
            String init               = "0010100101";
            int[] a                   = {1, 1, 1, 3, 4, 3, 4, 5, 1, 5};
            int K                     = 1;
            int expected__            = 28;

            return verifyCase(casenum__, expected__, new BrightLamps().maxBrightness(init, a, K));
        }
        case 2: {
            String init               = "1111111111";
            int[] a                   = {5, 5, 3, 3, 4, 3, 5, 1, 1, 3};
            int K                     = 7;
            int expected__            = 33;

            return verifyCase(casenum__, expected__, new BrightLamps().maxBrightness(init, a, K));
        }
        case 3: {
            String init               = "0010000001";
            int[] a                   = {8, 3, 10, 8, 3, 7, 4, 6, 3, 10};
            int K                     = 4;
            int expected__            = 55;

            return verifyCase(casenum__, expected__, new BrightLamps().maxBrightness(init, a, K));
        }
        case 4: {
            String init               = "00001001010101100001100000010000011001000000001011";
            int[] a                   = {99, 29, 22, 50, 17, 49, 50, 54, 43, 29, 30, 33, 38, 53, 71, 48, 82, 25, 62, 93, 90, 64, 43, 95, 68, 35, 79, 11, 13, 47, 51, 44, 35, 55, 4, 34, 7, 10, 25, 38, 29, 58, 36, 34, 77, 90, 37, 58, 20, 20};
            int K                     = 17;
            int expected__            = 2068;

            return verifyCase(casenum__, expected__, new BrightLamps().maxBrightness(init, a, K));
        }

        // custom cases

/*      case 5: {
            String init               = ;
            int[] a                   = ;
            int K                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new BrightLamps().maxBrightness(init, a, K));
        }*/
/*      case 6: {
            String init               = ;
            int[] a                   = ;
            int K                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new BrightLamps().maxBrightness(init, a, K));
        }*/
/*      case 7: {
            String init               = ;
            int[] a                   = ;
            int K                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new BrightLamps().maxBrightness(init, a, K));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
