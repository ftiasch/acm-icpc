import java.math.*;
import java.util.*;

public class ColorfulChocolates {
    public int maximumSpread(String chocolates, int maxSwaps) {
        int n = chocolates.length();
        int[] count = new int[26];
        for (int i = 0; i < n; ++ i) {
            count[chocolates.charAt(i) - 'A'] ++;
        }
        int result = 0;
        for (int c = 0; c < 26; ++ c) {
            for (int i = 0; i < n; ++ i) {
                for (int j = i; j < n; ++ j) {
                    int minSwaps = Integer.MAX_VALUE;
                    for (int s = 0; s < n; ++ s) {
                        int total = 0;
                        int swaps = 0;
                        for (int k = s; k < n && total < j - i + 1; ++ k) {
                            if (chocolates.charAt(k) - 'A' == c) {
                                swaps += Math.abs(k - (i + total));
                                total ++;
                            }
                        }
                        if (total == j - i + 1) {
                            minSwaps = Math.min(minSwaps, swaps);
                        }
                    }
                    if (minSwaps <= maxSwaps) {
                        result = Math.max(result, j - i + 1);
                    }
                }
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
            ColorfulChocolatesHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                ColorfulChocolatesHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class ColorfulChocolatesHarness {
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
            String chocolates         = "ABCDCBC";
            int maxSwaps              = 1;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new ColorfulChocolates().maximumSpread(chocolates, maxSwaps));
        }
        case 1: {
            String chocolates         = "ABCDCBC";
            int maxSwaps              = 2;
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new ColorfulChocolates().maximumSpread(chocolates, maxSwaps));
        }
        case 2: {
            String chocolates         = "ABBABABBA";
            int maxSwaps              = 3;
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new ColorfulChocolates().maximumSpread(chocolates, maxSwaps));
        }
        case 3: {
            String chocolates         = "ABBABABBA";
            int maxSwaps              = 4;
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new ColorfulChocolates().maximumSpread(chocolates, maxSwaps));
        }
        case 4: {
            String chocolates         = "QASOKZNHWNFODOQNHGQKGLIHTPJUVGKLHFZTGPDCEKSJYIWFOO";
            int maxSwaps              = 77;
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new ColorfulChocolates().maximumSpread(chocolates, maxSwaps));
        }

        // custom cases

/*      case 5: {
            String chocolates         = ;
            int maxSwaps              = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ColorfulChocolates().maximumSpread(chocolates, maxSwaps));
        }*/
/*      case 6: {
            String chocolates         = ;
            int maxSwaps              = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ColorfulChocolates().maximumSpread(chocolates, maxSwaps));
        }*/
/*      case 7: {
            String chocolates         = ;
            int maxSwaps              = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ColorfulChocolates().maximumSpread(chocolates, maxSwaps));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
