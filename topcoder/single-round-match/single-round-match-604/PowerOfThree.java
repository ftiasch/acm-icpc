import java.math.*;
import java.util.*;

public class PowerOfThree {
    public String ableToGet(int x, int y) {
        while (x != 0 || y != 0) {
            if ((x % 3 == 0) ^ (y % 3 == 0)) {
                if (x % 3 != 0) {
                    x += (x % 3 + 3) % 3 == 1 ? -1 : 1;
                } else {
                    y += (y % 3 + 3) % 3 == 1 ? -1 : 1;
                }
            } else {
                return "Impossible";
            }
            x /= 3;
            y /= 3;
        }
        return "Possible";
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            PowerOfThreeHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                PowerOfThreeHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class PowerOfThreeHarness {
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
            int x                     = 1;
            int y                     = 3;
            String expected__         = "Possible";

            return verifyCase(casenum__, expected__, new PowerOfThree().ableToGet(x, y));
        }
        case 1: {
            int x                     = 0;
            int y                     = 2;
            String expected__         = "Possible";

            return verifyCase(casenum__, expected__, new PowerOfThree().ableToGet(x, y));
        }
        case 2: {
            int x                     = 1;
            int y                     = 9;
            String expected__         = "Impossible";

            return verifyCase(casenum__, expected__, new PowerOfThree().ableToGet(x, y));
        }
        case 3: {
            int x                     = 3;
            int y                     = 0;
            String expected__         = "Impossible";

            return verifyCase(casenum__, expected__, new PowerOfThree().ableToGet(x, y));
        }
        case 4: {
            int x                     = 1;
            int y                     = 1;
            String expected__         = "Impossible";

            return verifyCase(casenum__, expected__, new PowerOfThree().ableToGet(x, y));
        }
        case 5: {
            int x                     = -6890;
            int y                     = 18252;
            String expected__         = "Possible";

            return verifyCase(casenum__, expected__, new PowerOfThree().ableToGet(x, y));
        }
        case 6: {
            int x                     = 1000000000;
            int y                     = -1000000000;
            String expected__         = "Impossible";

            return verifyCase(casenum__, expected__, new PowerOfThree().ableToGet(x, y));
        }
        case 7: {
            int x                     = 0;
            int y                     = 0;
            String expected__         = "Possible";

            return verifyCase(casenum__, expected__, new PowerOfThree().ableToGet(x, y));
        }

        // custom cases

/*      case 8: {
            int x                     = ;
            int y                     = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new PowerOfThree().ableToGet(x, y));
        }*/
/*      case 9: {
            int x                     = ;
            int y                     = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new PowerOfThree().ableToGet(x, y));
        }*/
/*      case 10: {
            int x                     = ;
            int y                     = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new PowerOfThree().ableToGet(x, y));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
