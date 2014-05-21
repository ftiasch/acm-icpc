import java.math.*;
import java.util.*;

public class TypoCoderDiv1 {
    public int getmax(int[] deltas, int init) {
        int n = deltas.length;
        int[][] maxChanges = new int[n + 1][2200];
        for (int i = 0; i <= n; ++ i) {
            for (int rating = 0; rating < 2200; ++ rating) {
                maxChanges[i][rating] = Integer.MIN_VALUE;
            }
        }
        maxChanges[0][init] = 0;
        int result = 0;
        for (int i = 0; i < n; ++ i) {
            for (int rating = 0; rating < 2200; ++ rating) {
                if (maxChanges[i][rating] >= 0) {
                    maxChanges[i + 1][Math.max(rating - deltas[i], 0)] = Math.max(maxChanges[i + 1][Math.max(rating - deltas[i], 0)], maxChanges[i][rating]);
                    if (deltas[i] > 0) {
                        int colorChange = 0;
                        if (rating + deltas[i] >= 2200) {
                            colorChange ++;
                            if (i + 1 < n) {
                                if (rating + deltas[i] - deltas[i + 1] < 2200) {
                                    colorChange ++;
                                    int newRating = Math.max(rating + deltas[i] - deltas[i + 1], 0);
                                    maxChanges[i + 2][newRating] = Math.max(maxChanges[i + 2][newRating], maxChanges[i][rating] + colorChange);
                                }
                            } else {
                                result = Math.max(result, maxChanges[i][rating] + colorChange);
                            }
                        } else {
                            maxChanges[i + 1][rating + deltas[i]] = Math.max(maxChanges[i + 1][rating + deltas[i]], maxChanges[i][rating]);
                        }
                    }
                }
            }
        }
        for (int rating = 0; rating < 2200; ++ rating) {
            result = Math.max(result, maxChanges[n][rating]);
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            TypoCoderDiv1Harness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                TypoCoderDiv1Harness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class TypoCoderDiv1Harness {
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
            int[] D                   = {100,200,100,1,1};
            int X                     = 2000;
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new TypoCoderDiv1().getmax(D, X));
        }
        case 1: {
            int[] D                   = {0,0,0,0,0};
            int X                     = 2199;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new TypoCoderDiv1().getmax(D, X));
        }
        case 2: {
            int[] D                   = {90000,80000,70000,60000,50000,40000,30000,20000,10000};
            int X                     = 0;
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new TypoCoderDiv1().getmax(D, X));
        }
        case 3: {
            int[] D                   = {1000000000,1000000000,10000,100000,2202,1};
            int X                     = 1000;
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new TypoCoderDiv1().getmax(D, X));
        }
        case 4: {
            int[] D                   = {2048,1024,5012,256,128,64,32,16,8,4,2,1,0};
            int X                     = 2199;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new TypoCoderDiv1().getmax(D, X));
        }
        case 5: {
            int[] D                   = {61,666,512,229,618,419,757,217,458,883,23,932,547,679,565,767,513,798,870,31,379,294,929,892,173,127,796,353,913,115,802,803,948,592,959,127,501,319,140,694,851,189,924,590,790,3,669,541,342,272};
            int X                     = 1223;
            int expected__            = 29;

            return verifyCase(casenum__, expected__, new TypoCoderDiv1().getmax(D, X));
        }
        case 6: {
            int[] D                   = {34,64,43,14,58,30,2,16,90,58,35,55,46,24,14,73,96,13,9,42,64,36,89,42,42,64,52,68,53,76,52,54,23,88,32,52,28,96,70,32,26,3,23,78,47,23,54,30,86,32};
            int X                     = 1328;
            int expected__            = 20;

            return verifyCase(casenum__, expected__, new TypoCoderDiv1().getmax(D, X));
        }

        // custom cases

/*      case 7: {
            int[] D                   = ;
            int X                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TypoCoderDiv1().getmax(D, X));
        }*/
/*      case 8: {
            int[] D                   = ;
            int X                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TypoCoderDiv1().getmax(D, X));
        }*/
/*      case 9: {
            int[] D                   = ;
            int X                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TypoCoderDiv1().getmax(D, X));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
