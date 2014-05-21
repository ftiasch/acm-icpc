import java.math.*;
import java.util.*;

public class LotsOfLines {
    public long countDivisions(int aMax, int bMax) {
        int[][] gcd = new int[aMax + 1][bMax + 1];
        int[][] sum = new int[aMax + 1][bMax + 1];
        for (int a = 0; a <= aMax; ++ a) {
            for (int b = 0; b <= bMax; ++ b) {
                if (a > 0 && b > 0) {
                    gcd[a][b] = a >= b ? gcd[a - b][b] : gcd[a][b - a];
                    sum[a][b] = sum[a - 1][b] + sum[a][b - 1] - sum[a - 1][b - 1];
                    if (gcd[a][b] == 1) {
                        sum[a][b] ++;
                    }
                } else {
                    gcd[a][b] = a ^ b;
                }
            }
        }
        long result = 1 + aMax * bMax;
        for (int a = 1; a < aMax; ++ a) {
            for (int b = 0; b < bMax; ++ b) {
                result += 1 + sum[a][bMax - 1 - b] + sum[a][b];
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
            LotsOfLinesHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                LotsOfLinesHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class LotsOfLinesHarness {
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
            int A                     = 1;
            int B                     = 1;
            long expected__           = 2;

            return verifyCase(casenum__, expected__, new LotsOfLines().countDivisions(A, B));
        }
        case 1: {
            int A                     = 2;
            int B                     = 2;
            long expected__           = 9;

            return verifyCase(casenum__, expected__, new LotsOfLines().countDivisions(A, B));
        }
        case 2: {
            int A                     = 3;
            int B                     = 2;
            long expected__           = 17;

            return verifyCase(casenum__, expected__, new LotsOfLines().countDivisions(A, B));
        }
        case 3: {
            int A                     = 1;
            int B                     = 1200;
            long expected__           = 1201;

            return verifyCase(casenum__, expected__, new LotsOfLines().countDivisions(A, B));
        }
        case 4: {
            int A                     = 5;
            int B                     = 9;
            long expected__           = 638;

            return verifyCase(casenum__, expected__, new LotsOfLines().countDivisions(A, B));
        }

        // custom cases

/*      case 5: {
            int A                     = ;
            int B                     = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new LotsOfLines().countDivisions(A, B));
        }*/
/*      case 6: {
            int A                     = ;
            int B                     = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new LotsOfLines().countDivisions(A, B));
        }*/
/*      case 7: {
            int A                     = ;
            int B                     = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new LotsOfLines().countDivisions(A, B));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
