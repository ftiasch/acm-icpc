import java.math.*;
import java.util.*;

public class BlurredDartboard {
    long solve(ArrayList <Integer> sums, int max, int k) {
        int m = sums.size() - 1;
        if (m == 0) {
            return (long)max * k;
        }
        long ret = 0;
        for (int r = 0; r < m; ++ r) {
            if (r <= k) {
                ret = Math.max(ret, sums.get(r) + (long)max * (k - r));
                int p = (k - r) / m;
                ret = Math.max(ret, sums.get(r) + (long)sums.get(m) * p + (long)max * (k - m * p - r));
            }
        }
        return ret;
    }

    public int minThrows(int[] points, int p) {
        int n = points.length;
        boolean[] found = new boolean[n + 1];
        int max = 0;
        for (int point : points) {
            found[point] = true;
            max = Math.max(max, point);
        }
        ArrayList <Integer> sums = new ArrayList <Integer>();
        sums.add(0);
        for (int i = 1; i <= n; ++ i) {
            if (!found[i]) {
                sums.add(sums.get(sums.size() - 1) + i);
            }
        }
        int low = 0;
        int high = 1000000000;
        while (low < high) {
            int middle = low + high >> 1;
            if (solve(sums, max, middle) >= p) {
                high = middle;
            } else {
                low = middle + 1;
            }
        }
        return high;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            BlurredDartboardHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                BlurredDartboardHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class BlurredDartboardHarness {
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
            int[] points              = {0, 3, 4, 0, 0};
            int P                     = 8;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new BlurredDartboard().minThrows(points, P));
        }
        case 1: {
            int[] points              = {0, 0, 0, 0, 0};
            int P                     = 15;
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new BlurredDartboard().minThrows(points, P));
        }
        case 2: {
            int[] points              = {4, 7, 8, 1, 3, 2, 6, 5};
            int P                     = 2012;
            int expected__            = 252;

            return verifyCase(casenum__, expected__, new BlurredDartboard().minThrows(points, P));
        }
        case 3: {
            int[] points              = {0, 0, 5, 0, 0, 0, 1, 3, 0, 0};
            int P                     = 2012;
            int expected__            = 307;

            return verifyCase(casenum__, expected__, new BlurredDartboard().minThrows(points, P));
        }
        case 4: {
            int[] points              = {0, 2, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 6, 0, 0, 0, 4, 0, 0, 0};
            int P                     = 1000000000;
            int expected__            = 84656087;

            return verifyCase(casenum__, expected__, new BlurredDartboard().minThrows(points, P));
        }

        // custom cases

/*      case 5: {
            int[] points              = ;
            int P                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new BlurredDartboard().minThrows(points, P));
        }*/
/*      case 6: {
            int[] points              = ;
            int P                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new BlurredDartboard().minThrows(points, P));
        }*/
/*      case 7: {
            int[] points              = ;
            int P                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new BlurredDartboard().minThrows(points, P));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
