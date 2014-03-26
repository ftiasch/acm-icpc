import java.math.*;
import java.util.*;

public class AstronomicalRecords {
    public int minimalPlanets(int[] a, int[] b) {
        int n = a.length;
        int m = b.length;
        int[][] maximum = new int[n + 1][m + 1];
        for (int i = n - 1; i >= 0; -- i) {
            for (int j = m - 1; j >= 0; -- j) {
                maximum[i][j] = 1;
                for (int ii = i + 1; ii < n; ++ ii) {
                    for (int jj = j + 1; jj < m; ++ jj) {
                        if ((long)a[i] * b[jj] == (long)b[j] * a[ii]) {
                            maximum[i][j] = Math.max(maximum[i][j], maximum[ii][jj] + 1);
                        }
                    }
                }
            }
        }
        int result = n + m;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                result = Math.min(result, n + m - maximum[i][j]);
            }
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            AstronomicalRecordsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                AstronomicalRecordsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class AstronomicalRecordsHarness {
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
            int[] A                   = {1,2,1,2,1};
            int[] B                   = {2,1,2,1,2};
            int expected__            = 6;

            return verifyCase(casenum__, expected__, new AstronomicalRecords().minimalPlanets(A, B));
        }
        case 1: {
            int[] A                   = {1,2,3,4};
            int[] B                   = {2,4,6,8};
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new AstronomicalRecords().minimalPlanets(A, B));
        }
        case 2: {
            int[] A                   = {2,3,2,3,2,3,2};
            int[] B                   = {600,700,600,700,600,700,600};
            int expected__            = 10;

            return verifyCase(casenum__, expected__, new AstronomicalRecords().minimalPlanets(A, B));
        }
        case 3: {
            int[] A                   = {1,2,3,4,5,6,7,8,9};
            int[] B                   = {6,7,8,9,10,11,12};
            int expected__            = 12;

            return verifyCase(casenum__, expected__, new AstronomicalRecords().minimalPlanets(A, B));
        }
        case 4: {
            int[] A                   = {100000000,200000000};
            int[] B                   = {200000000,100000000};
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new AstronomicalRecords().minimalPlanets(A, B));
        }

        // custom cases

/*      case 5: {
            int[] A                   = ;
            int[] B                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new AstronomicalRecords().minimalPlanets(A, B));
        }*/
/*      case 6: {
            int[] A                   = ;
            int[] B                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new AstronomicalRecords().minimalPlanets(A, B));
        }*/
/*      case 7: {
            int[] A                   = ;
            int[] B                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new AstronomicalRecords().minimalPlanets(A, B));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
