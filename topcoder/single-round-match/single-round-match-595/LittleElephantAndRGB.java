import java.math.*;
import java.util.*;

public class LittleElephantAndRGB {
    public long getNumber(String[] parts, int minGreen) {
        String string = join(parts);
        int n = string.length();
        long result = (long)(n - 1) * n * (n + 1) * (n + 2) / 24;
        int[] count = new int[n + 1];
        for (int i = 0; i < n; ++ i) {
            int[] sum = new int[n + 1];
            sum[0] = count[0];
            for (int j = 1; j <= n; ++ j) {
                sum[j] = sum[j - 1] + count[j];
            }
            int prefix = 0;
            while (i + prefix < n && string.charAt(i + prefix) == 'G') {
                prefix ++;
            }
            for (int j = i, g = 0; j < n; ++ j) {
                if (string.charAt(j) == 'G') {
                    g ++;
                } else {
                    g = 0;
                }
                if (g == minGreen) {
                    break;
                }
                result -= sum[Math.min(minGreen - Math.min(prefix, j - i + 1) - 1, n)];
            }
            int suffix = 0;
            while (i - suffix >= 0 && string.charAt(i - suffix) == 'G') {
                suffix ++;
            }
            for (int j = i, g = 0; j >= 0; -- j) {
                if (string.charAt(j) == 'G') {
                    g ++;
                } else {
                    g = 0;
                }
                if (g == minGreen) {
                    break;
                }
                count[Math.min(suffix, i - j + 1)] ++;
            }
        }
        return result;
    }

    String join(String[] parts) {
        StringBuffer buffer = new StringBuffer();
        for (String part : parts) {
            buffer.append(part);
        }
        return buffer.toString();
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            LittleElephantAndRGBHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                LittleElephantAndRGBHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class LittleElephantAndRGBHarness {
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
            String[] list             = {"GRG"};
            int minGreen              = 2;
            long expected__           = 1;

            return verifyCase(casenum__, expected__, new LittleElephantAndRGB().getNumber(list, minGreen));
        }
        case 1: {
            String[] list             = {"GG", "GG"};
            int minGreen              = 3;
            long expected__           = 9;

            return verifyCase(casenum__, expected__, new LittleElephantAndRGB().getNumber(list, minGreen));
        }
        case 2: {
            String[] list             = {"GRBGRBBRG"};
            int minGreen              = 2;
            long expected__           = 11;

            return verifyCase(casenum__, expected__, new LittleElephantAndRGB().getNumber(list, minGreen));
        }
        case 3: {
            String[] list             = {"RRBRBBRRR", "R", "B"};
            int minGreen              = 1;
            long expected__           = 0;

            return verifyCase(casenum__, expected__, new LittleElephantAndRGB().getNumber(list, minGreen));
        }
        case 4: {
            String[] list             = {"GRGGGRBRGG", "GGGGGGGG", "BRGRBRB"};
            int minGreen              = 4;
            long expected__           = 12430;

            return verifyCase(casenum__, expected__, new LittleElephantAndRGB().getNumber(list, minGreen));
        }

        // custom cases

/*      case 5: {
            String[] list             = ;
            int minGreen              = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new LittleElephantAndRGB().getNumber(list, minGreen));
        }*/
/*      case 6: {
            String[] list             = ;
            int minGreen              = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new LittleElephantAndRGB().getNumber(list, minGreen));
        }*/
/*      case 7: {
            String[] list             = ;
            int minGreen              = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new LittleElephantAndRGB().getNumber(list, minGreen));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
