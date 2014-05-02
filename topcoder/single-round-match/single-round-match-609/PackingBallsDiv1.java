import java.math.*;
import java.util.*;

public class PackingBallsDiv1 {
    public int minPacks(int n, int a, int b, int c, int d) {
        int[] colors = new int[n];
        colors[0] = a;
        for (int i = 1; i < n; ++ i) {
            colors[i] = (int)(((long)colors[i - 1] * b + c) % d + 1);
        }
        return solve(colors);
    }

    int solve(int[] colors) {
        int n = colors.length;
        int sum = 0;
        for (int i = 0; i < n; ++ i) {
            sum += (colors[i] + n - 1) / n;
        }
        int result = sum;
        int[] delta = new int[n];
        for (int i = 0; i < n; ++ i) {
            delta[colors[i] % n] --;
        }
        delta[0] = 0;
        for (int i = 1; i < n; ++ i) {
            delta[i] += delta[i - 1];
        }
        for (int i = 1; i < n; ++ i) {
            result = Math.min(result, sum + i + delta[i]);
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            PackingBallsDiv1Harness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                PackingBallsDiv1Harness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class PackingBallsDiv1Harness {
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
            int K                     = 3;
            int A                     = 4;
            int B                     = 2;
            int C                     = 5;
            int D                     = 6;
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new PackingBallsDiv1().minPacks(K, A, B, C, D));
        }
        case 1: {
            int K                     = 1;
            int A                     = 58;
            int B                     = 23;
            int C                     = 39;
            int D                     = 93;
            int expected__            = 58;

            return verifyCase(casenum__, expected__, new PackingBallsDiv1().minPacks(K, A, B, C, D));
        }
        case 2: {
            int K                     = 23;
            int A                     = 10988;
            int B                     = 5573;
            int C                     = 4384;
            int D                     = 100007;
            int expected__            = 47743;

            return verifyCase(casenum__, expected__, new PackingBallsDiv1().minPacks(K, A, B, C, D));
        }
        case 3: {
            int K                     = 100000;
            int A                     = 123456789;
            int B                     = 234567890;
            int C                     = 345678901;
            int D                     = 1000000000;
            int expected__            = 331988732;

            return verifyCase(casenum__, expected__, new PackingBallsDiv1().minPacks(K, A, B, C, D));
        }

        // custom cases

/*      case 4: {
            int K                     = ;
            int A                     = ;
            int B                     = ;
            int C                     = ;
            int D                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new PackingBallsDiv1().minPacks(K, A, B, C, D));
        }*/
/*      case 5: {
            int K                     = ;
            int A                     = ;
            int B                     = ;
            int C                     = ;
            int D                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new PackingBallsDiv1().minPacks(K, A, B, C, D));
        }*/
/*      case 6: {
            int K                     = ;
            int A                     = ;
            int B                     = ;
            int C                     = ;
            int D                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new PackingBallsDiv1().minPacks(K, A, B, C, D));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
