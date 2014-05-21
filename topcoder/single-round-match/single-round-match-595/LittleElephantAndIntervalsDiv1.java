import java.math.*;
import java.util.*;

public class LittleElephantAndIntervalsDiv1 {
    public long getNumber(int m, int[] l, int[] r) {
        int[] belongs = new int[m];
        Arrays.fill(belongs, -1);
        int n = l.length;
        for (int i = 0; i < n; ++ i) {
            for (int j = l[i] - 1; j < r[i]; ++ j) {
                belongs[j] = i;
            }
        }
        long result = 1;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < m; ++ i) {
            if (belongs[i] != -1 && !visited[belongs[i]]) {
                result *= 2;
                visited[belongs[i]] = true;
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
            LittleElephantAndIntervalsDiv1Harness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                LittleElephantAndIntervalsDiv1Harness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class LittleElephantAndIntervalsDiv1Harness {
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

    static boolean compareOutput(long expected, long result) { return expected == result; }
    static String formatResult(long res) {
        return String.format("%d", res);
    }

    static int verifyCase(int casenum, long expected, long received) {
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
            int M                     = 4;
            int[] L                   = {1, 2, 3};
            int[] R                   = {1, 2, 3};
            long expected__           = 8;

            return verifyCase(casenum__, expected__, new LittleElephantAndIntervalsDiv1().getNumber(M, L, R));
        }
        case 1: {
            int M                     = 3;
            int[] L                   = {1, 1, 2};
            int[] R                   = {3, 1, 3};
            long expected__           = 4;

            return verifyCase(casenum__, expected__, new LittleElephantAndIntervalsDiv1().getNumber(M, L, R));
        }
        case 2: {
            int M                     = 1000;
            int[] L                   = {47};
            int[] R                   = {747};
            long expected__           = 2;

            return verifyCase(casenum__, expected__, new LittleElephantAndIntervalsDiv1().getNumber(M, L, R));
        }
        case 3: {
            int M                     = 42;
            int[] L                   = {5, 23, 4, 1, 15, 2, 22, 26, 13, 16, 28, 13, 27, 9, 18, 4, 10, 3, 4, 4, 3, 4, 1, 18, 18, 2, 38, 4, 10, 12, 3, 30, 11, 38, 2, 13, 1, 13, 18, 16, 13, 2, 14, 27, 13, 3, 26, 19, 5, 10};
            int[] R                   = {30, 41, 17, 1, 21, 6, 28, 30, 15, 19, 31, 28, 35, 27, 30, 13, 31, 5, 8, 25, 40, 10, 3, 26, 23, 9, 40, 8, 40, 23, 12, 37, 35, 39, 11, 34, 10, 21, 22, 21, 24, 5, 39, 27, 17, 16, 26, 35, 25, 36};
            long expected__           = 256;

            return verifyCase(casenum__, expected__, new LittleElephantAndIntervalsDiv1().getNumber(M, L, R));
        }

        // custom cases

/*      case 4: {
            int M                     = ;
            int[] L                   = ;
            int[] R                   = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new LittleElephantAndIntervalsDiv1().getNumber(M, L, R));
        }*/
/*      case 5: {
            int M                     = ;
            int[] L                   = ;
            int[] R                   = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new LittleElephantAndIntervalsDiv1().getNumber(M, L, R));
        }*/
/*      case 6: {
            int M                     = ;
            int[] L                   = ;
            int[] R                   = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new LittleElephantAndIntervalsDiv1().getNumber(M, L, R));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
