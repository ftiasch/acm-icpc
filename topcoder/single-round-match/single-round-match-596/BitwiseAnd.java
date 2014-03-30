import java.math.*;
import java.util.*;

public class BitwiseAnd {
    public long[] lexSmallest(long[] subset, int n) {
        int m = subset.length;
        for (int i = 0; i < m; ++ i) {
            for (int j = 0; j < i; ++ j) {
                if ((subset[i] & subset[j]) == 0) {
                    return new long[]{};
                }
            }
        }
        for (int i = 0; i < m; ++ i) {
            for (int j = 0; j < i; ++ j) {
                for (int k = 0; k < j; ++ k) {
                    if ((subset[i] & subset[j] & subset[k]) > 0) {
                        return new long[]{};
                    }
                }
            }
        }
        long[] unique = subset.clone();
        for (int i = 0; i < m; ++ i) {
            for (int j = 0; j < m; ++ j) {
                if (i != j) {
                    unique[i] &= ~subset[j];
                }
            }
        }
        long[] result = new long[n];
        for (int i = 0; i < m; ++ i) {
            result[n - m + i] = subset[i];
        }
        for (int i = 0; i < n - m; ++ i) {
            for (int j = 0; j < m; ++ j) {
                if (unique[j] == 0) {
                    return new long[]{};
                }
                long lowbit = unique[j] & -unique[j];
                result[i] |= lowbit;
                unique[j] &= ~lowbit;
            }
        }
        long left = (1L << 60) - 1;
        for (int i = 0; i < n; ++ i) {
            left &= ~result[i];
        }
        for (int i = 0; i < n - m; ++ i) {
            for (int j = i + 1; j < n - m; ++ j) {
                if (left == 0) {
                    return new long[]{};
                }
                long lowbit = left & -left;
                result[i] |= lowbit;
                result[j] |= lowbit;
                left &= ~lowbit;
            }
        }
        Arrays.sort(result);
        return result;
    }

    void printBit(long mask) {
        for (int i = 0; i < 60; ++ i) {
            System.err.print(mask >> i & 1);
        }
        System.err.println();
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            BitwiseAndHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                BitwiseAndHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class BitwiseAndHarness {
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

    static boolean compareOutput(long[] expected, long[] result) { if (expected.length != result.length) return false; for (int i=0; i<expected.length; ++i) if (expected[i] != result[i]) return false; return true; }

    static String formatResult(long[] res) {
        String ret = "";
        ret += "{";
        for (int i=0; i<res.length; ++i) {
            if (i > 0) ret += ",";
            ret += String.format(" %d", res[i]);
        }
        ret += " }";
        return ret;
    }

    static int verifyCase(int casenum, long[] expected, long[] received) {
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
            long[] subset             = {14, 20};
            int N                     = 3;
            long[] expected__         = {14, 18, 20};

            return verifyCase(casenum__, expected__, new BitwiseAnd().lexSmallest(subset, N));
        }
        case 1: {
            long[] subset             = {11, 17, 20};
            int N                     = 4;
            long[] expected__         = {};

            return verifyCase(casenum__, expected__, new BitwiseAnd().lexSmallest(subset, N));
        }
        case 2: {
            long[] subset             = {99, 157};
            int N                     = 4;
            long[] expected__         = {99, 157, 262, 296};

            return verifyCase(casenum__, expected__, new BitwiseAnd().lexSmallest(subset, N));
        }
        case 3: {
            long[] subset             = {1152921504606846975L};
            int N                     = 3;
            long[] expected__         = {};

            return verifyCase(casenum__, expected__, new BitwiseAnd().lexSmallest(subset, N));
        }
        case 4: {
            long[] subset             = {};
            int N                     = 5;
            long[] expected__         = {15, 113, 402, 676, 840};

            return verifyCase(casenum__, expected__, new BitwiseAnd().lexSmallest(subset, N));
        }
        case 5: {
            long[] subset             = {1, 3, 5, 7, 9, 11};
            int N                     = 6;
            long[] expected__         = {};

            return verifyCase(casenum__, expected__, new BitwiseAnd().lexSmallest(subset, N));
        }

        // custom cases

/*      case 6: {
            long[] subset             = {};
            int N                     = ;
            long[] expected__         = {};

            return verifyCase(casenum__, expected__, new BitwiseAnd().lexSmallest(subset, N));
        }*/
/*      case 7: {
            long[] subset             = {};
            int N                     = ;
            long[] expected__         = {};

            return verifyCase(casenum__, expected__, new BitwiseAnd().lexSmallest(subset, N));
        }*/
/*      case 8: {
            long[] subset             = {};
            int N                     = ;
            long[] expected__         = {};

            return verifyCase(casenum__, expected__, new BitwiseAnd().lexSmallest(subset, N));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
