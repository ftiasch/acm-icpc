import java.math.*;
import java.util.*;

public class LeftAndRightHandedDiv1 {
    public long countSwaps(String pattern, int a, int b, int c, int d, int n) {
        char[] hands = new char[n];
        int y = a;
        for (int i = 0; i < n; ++ i) {
            hands[i] = pattern.charAt(y % pattern.length());
            y = (int)(((long)y * b + c) % d);
        }
        return solve(hands);
    }

    int getDistance(int n, int x, int y) {
        if (x > y) {
            y += n;
        }
        return y - x;
    }

    long solve(char[] hands) {
        int n = hands.length;
        int m = 0;
        for (int i = 0; i < n; ++ i) {
            if (hands[i] == 'L') {
                m ++;
            }
        }
        if (m == 0) {
            return 0;
        }
        int[] indices = new int[m << 1];
        m = 0;
        for (int i = 0; i < n; ++ i) {
            if (hands[i] == 'L') {
                indices[m ++] = i;
            }
        }
        for (int i = 0; i < n; ++ i) {
            if (hands[i] == 'L') {
                indices[m ++] = i + n;
            }
        }
        m >>= 1;
        long[] sum = new long[(m << 1) + 1];
        for (int i = (m << 1) - 1; i >= 0; -- i) {
            sum[i] = sum[i + 1] + indices[i];
        }
        long result = Long.MAX_VALUE;
        for (int i = 0; i < m; ++ i) {
            int middle = m >> 1;
            int best = indices[i + middle] - middle;
            long now = (sum[i + middle] - sum[i + m]) - (sum[i] - sum[i + middle]);
            now += (long)middle * (best + best + middle - 1) / 2;
            now -= (long)(m - middle) * (best + middle + best + m - 1) / 2;
            result = Math.min(result, now);
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            LeftAndRightHandedDiv1Harness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                LeftAndRightHandedDiv1Harness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class LeftAndRightHandedDiv1Harness {
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
            String Y                  = "LR";
            int A                     = 1;
            int B                     = 0;
            int C                     = 1;
            int D                     = 2;
            int N                     = 1;
            long expected__           = 0;

            return verifyCase(casenum__, expected__, new LeftAndRightHandedDiv1().countSwaps(Y, A, B, C, D, N));
        }
        case 1: {
            String Y                  = "LRLR";
            int A                     = 1;
            int B                     = 1;
            int C                     = 2;
            int D                     = 3;
            int N                     = 4;
            long expected__           = 0;

            return verifyCase(casenum__, expected__, new LeftAndRightHandedDiv1().countSwaps(Y, A, B, C, D, N));
        }
        case 2: {
            String Y                  = "LRRLLR";
            int A                     = 2;
            int B                     = 3;
            int C                     = 4;
            int D                     = 5;
            int N                     = 6;
            long expected__           = 1;

            return verifyCase(casenum__, expected__, new LeftAndRightHandedDiv1().countSwaps(Y, A, B, C, D, N));
        }
        case 3: {
            String Y                  = "LRRLRLLRLRLRLLRLR";
            int A                     = 12;
            int B                     = 15;
            int C                     = 3;
            int D                     = 22;
            int N                     = 10;
            long expected__           = 2;

            return verifyCase(casenum__, expected__, new LeftAndRightHandedDiv1().countSwaps(Y, A, B, C, D, N));
        }
        case 4: {
            String Y                  = "LLLLLLLLL";
            int A                     = 0;
            int B                     = 1;
            int C                     = 2;
            int D                     = 3;
            int N                     = 1000000;
            long expected__           = 0;

            return verifyCase(casenum__, expected__, new LeftAndRightHandedDiv1().countSwaps(Y, A, B, C, D, N));
        }
        case 5: {
            String Y                  = "RLRRRLLLRLRLLRLRLLRLRLLLLLRRLRLRLRLRLLRRRLRRLLLLLRRRLRRLRLRRLLLLRLRRLRLRRLRRLRLLRRLRLRRRLRLLRLLLLRLLLLRLLRRLLRRRRLLRLLRLRLRRLL";
            int A                     = 48658960;
            int B                     = 221863772;
            int C                     = 385355602;
            int D                     = 393787970;
            int N                     = 980265;
            long expected__           = 59619692663L;

            return verifyCase(casenum__, expected__, new LeftAndRightHandedDiv1().countSwaps(Y, A, B, C, D, N));
        }

        // custom cases

/*      case 6: {
            String Y                  = ;
            int A                     = ;
            int B                     = ;
            int C                     = ;
            int D                     = ;
            int N                     = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new LeftAndRightHandedDiv1().countSwaps(Y, A, B, C, D, N));
        }*/
/*      case 7: {
            String Y                  = ;
            int A                     = ;
            int B                     = ;
            int C                     = ;
            int D                     = ;
            int N                     = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new LeftAndRightHandedDiv1().countSwaps(Y, A, B, C, D, N));
        }*/
/*      case 8: {
            String Y                  = ;
            int A                     = ;
            int B                     = ;
            int C                     = ;
            int D                     = ;
            int N                     = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new LeftAndRightHandedDiv1().countSwaps(Y, A, B, C, D, N));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
