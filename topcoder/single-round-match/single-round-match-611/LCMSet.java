import java.math.*;
import java.util.*;

public class LCMSet {
    public String equal(int[] a, int[] b) {
        return check(a, b) && check(b, a) ? "Equal" : "Not equal";
    }

    long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    boolean check(int[] as, int[] bs) {
        for (int a : as) {
            long l = 1;
            for (int b : bs) {
                if (a % b == 0) {
                    l = lcm(l, b);
                    if (l > a) {
                        break;
                    }
                }
            }
            if (a != l) {
                return false;
            }
        }
        return true;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            LCMSetHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                LCMSetHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class LCMSetHarness {
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

    static boolean compareOutput(String expected, String result) { return expected.equals(result); }
    static String formatResult(String res) {
        return String.format("\"%s\"", res);
    }

    static int verifyCase(int casenum, String expected, String received) {
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
            int[] A                   = {2,3,4,12};
            int[] B                   = {2,3,4,6};
            String expected__         = "Equal";

            return verifyCase(casenum__, expected__, new LCMSet().equal(A, B));
        }
        case 1: {
            int[] A                   = {4,9};
            int[] B                   = {6,36};
            String expected__         = "Not equal";

            return verifyCase(casenum__, expected__, new LCMSet().equal(A, B));
        }
        case 2: {
            int[] A                   = {2,3,5,7,14,105};
            int[] B                   = {2,3,5,6,7,30,35};
            String expected__         = "Equal";

            return verifyCase(casenum__, expected__, new LCMSet().equal(A, B));
        }
        case 3: {
            int[] A                   = {2,3,5,7,14,105};
            int[] B                   = {2,3,5,6,7,30,36};
            String expected__         = "Not equal";

            return verifyCase(casenum__, expected__, new LCMSet().equal(A, B));
        }
        case 4: {
            int[] A                   = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97};
            int[] B                   = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97};
            String expected__         = "Equal";

            return verifyCase(casenum__, expected__, new LCMSet().equal(A, B));
        }
        case 5: {
            int[] A                   = {999999999,1953125,512,1000000000};
            int[] B                   = {999999999,1953125,512};
            String expected__         = "Equal";

            return verifyCase(casenum__, expected__, new LCMSet().equal(A, B));
        }
        case 6: {
            int[] A                   = {999999998,999999999,1000000000};
            int[] B                   = {999999999,1000000000};
            String expected__         = "Not equal";

            return verifyCase(casenum__, expected__, new LCMSet().equal(A, B));
        }

        // custom cases

/*      case 7: {
            int[] A                   = ;
            int[] B                   = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new LCMSet().equal(A, B));
        }*/
/*      case 8: {
            int[] A                   = ;
            int[] B                   = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new LCMSet().equal(A, B));
        }*/
/*      case 9: {
            int[] A                   = ;
            int[] B                   = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new LCMSet().equal(A, B));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
