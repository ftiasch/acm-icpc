import java.math.*;
import java.util.*;

public class OneDimensionalRobot {
    public long theSum(String[] commands1, String[] commands2, int minA, int maxA, int minB, int maxB) {
        String command = concat(commands1) + concat(commands2);
        int n = command.length();
        long result = 0;
        for (int length = minA + minB; length <= maxA + maxB; ++ length) {
            int xLow = 0;
            int yLow = 0;
            int xHigh = length;
            int yHigh = length;
            for (int i = 0; i < n; ++ i) {
                if (command.charAt(i) == 'L') {
                    yLow --;
                    yHigh --;
                    if (yHigh <= 0) {
                        yLow = yHigh = 0;
                    } else {
                        while (yLow < 0) {
                            xLow ++;
                            yLow ++;
                        }
                    }
                } else {
                    yLow ++;
                    yHigh ++;
                    if (yLow >= length) {
                        yLow = yHigh = length;
                    } else {
                        while (yHigh > length) {
                            xHigh --;
                            yHigh --;
                        }
                    }
                }
            }
            for (int a = minA; a <= maxA; ++ a) {
                int b = length - a;
                if (minB <= b && b <= maxB) {
                    if (a <= xLow) {
                        result += yLow - a;
                    } else if (a >= xHigh) {
                        result += yHigh - a;
                    } else {
                        result += yLow - xLow;
                    }
                }
            }
        }
        return result;
    }

    String concat(String[] parts) {
        StringBuffer buffer = new StringBuffer("");
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
            OneDimensionalRobotHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                OneDimensionalRobotHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class OneDimensionalRobotHarness {
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
            String[] commands1        = {"RRLRLLRRLL"};
            String[] commands2        = {};
            int minA                  = 2;
            int maxA                  = 2;
            int minB                  = 1;
            int maxB                  = 1;
            long expected__           = -1;

            return verifyCase(casenum__, expected__, new OneDimensionalRobot().theSum(commands1, commands2, minA, maxA, minB, maxB));
        }
        case 1: {
            String[] commands1        = {"RLRRLRLLRRLLLRLRLLRL"};
            String[] commands2        = {};
            int minA                  = 2;
            int maxA                  = 3;
            int minB                  = 1;
            int maxB                  = 2;
            long expected__           = -9;

            return verifyCase(casenum__, expected__, new OneDimensionalRobot().theSum(commands1, commands2, minA, maxA, minB, maxB));
        }
        case 2: {
            String[] commands1        = {"RLRRLRRRLLLLLRLRRLLLLRRRRLLRLLRLRRRLLRRLRLLRLLRRRL", "LRLRLRLLRLLLRRLLRLRRLLLRLLRLLRLLLLRRRLLRLRRRLLRRRR"};
            String[] commands2        = {};
            int minA                  = 3;
            int maxA                  = 5;
            int minB                  = 2;
            int maxB                  = 4;
            long expected__           = 17;

            return verifyCase(casenum__, expected__, new OneDimensionalRobot().theSum(commands1, commands2, minA, maxA, minB, maxB));
        }
        case 3: {
            String[] commands1        = {"LRRRRLLLRLRLLLRRLLLRRRLLLLLLRLLRLRRLLRLLLLLRRLLLLR",
 "RLLLLRRRLRLLRLRRLRLRRLLRRLRRRRLLLRRLLRRLRRRLLRLRLL",
 "RLRRRLLLRLRRLRLLLRLLLLRRRLLRLRRLRRRRRLRLLLLLRLLRLR",
 "LLRLRRRLRLLLRLLRRLRLRLRLRRRLLRRRLRRRLRLRLRRLLRLRLR",
 "LRRRRRLLLRLLRRLLRRRRLLLLRRRRLRRRLLLRRLLRRRRRRLLRLR",
 "LRLLRLRLLLLRLLLRLLRRRRLRRLLLLRRRLRRLRLRRLLLRLRLRLR"};
            String[] commands2        = {};
            int minA                  = 1;
            int maxA                  = 608;
            int minB                  = 1;
            int maxB                  = 608;
            long expected__           = -1417089;

            return verifyCase(casenum__, expected__, new OneDimensionalRobot().theSum(commands1, commands2, minA, maxA, minB, maxB));
        }
        case 4: {
            String[] commands1        = {"LRRRRRLRRRLLLRLLLLRLRLLLRLLRLLRRLRRLRRLLRLLLRLLLLL",
 "RRRLRLRRLLLRLLRRRRLLLRLRLRRLLLRLRRLLRLRLRLLLRLRLLR",
 "RLLLLLRLRRLLLRRRLLRLRLLRRLLLLRRLRLRLRRRLRRLRRLLRLL",
 "RRLRLRLRLLLLRRRRLLLRLLRLLLLRLLLRLLLRLRLRLRRRLRRRRR",
 "LRRLRLLRLLRRLRRLLRLRLRRLRRLLLLLLLRRRLRLLRRRRRRLRRR",
 "RLRLLLRLRLLRRLRLLLLLRLRRLRLLLRRRLRLRLRLLLRLRLLLRRL",
 "LRLRRLRRLLLRRLLLRRLRRRLLLLLLLLLLLLLRLLLRLLLRLLLRLL",
 "LLLRLRRRRLRRLLRLRLLLRLLLRRLRRLRLRRLLLLRRLRRRLLLRLR",
 "RLRLLRRRRRLLLRLRRLRLLRLRLLLRLLLRRLRLRLRRRLRRLLRRRL",
 "RLLLLRRRLRRLRRRLRRRLRLLRRRLLLRRLRLLLLLLLLRLLRLLRRL",
 "RRLLLRRLRLLRLLLRRRLRRLLLRLLRLRRRLRRRRRLRRRRRLRLLLR",
 "LLLLRLRLRLLLLRLLRRLLLRLLLRLLLLRLRRRLRRRLRLRLRLRLRR",
 "LLLLRLRLRLRLRLLLRLLRLLRLLLLLLLRRLRRRLRRLLLLLRLLLLL",
 "RRLLRLLLRRLRLLLLRRRRLLLLRLLRLLLLLRLLLLRRLRLLRLRLLR",
 "LRRLLLLRLLRRLRRRRRLLRLLLRLRLLLLLRLLRRLLLLLLRLRLLLR",
 "LRLLRRLLLRLRLLLLRLLRRLLLRLLLRLRLRRLRRRLLLLRRRLRLLL",
 "LLLRRLRLLLLRLRRLRLRLRRRRLLLLLLLRLLLRRLRRLLLLRLLLLR",
 "RRLRLLLRLRRLRRRLRLLRRLLLLLRLRLRRLRLLLLLLLLLRRLRRLL",
 "LRRLLLLRLLLRRRLLLRLLRRLLLRRLLLRRLLLRRLLRLRRRLLRLLL",
 "LRRRRLLRRLLLLLRLLLRLRRLLRLLLLLLLRRRLLLRLLLLLLLRLRR",
 "LLRRRLRLRLLRLRRLRLRRLLRLLLLLLRRLLRLRLRLLLLRLRLRRRR",
 "RRLRLLLLLRLRRLLLLLRLLLRLLLRLLRLLLLLRLLLRRLRRLLLLRR",
 "RLLLRLLLLLLLLLRRRLLLRRRLRRLRRRLLLRLRLLRLLLRLLRRRRL",
 "LRLLLRRRLRLRRLLRLLRLRRLRLRLLLLRLLLRLLLLRRRRLRLRLRR",
 "RLRRLLLLLRRLRLRLRRRLLLRLRRRLRLRLRLLRRLLLLRRLLRRRLL",
 "RLLRRLRRLRLRLRLLLLLLRLLLLRRLLRLRLRRLLRLRRLLRLRLLLR",
 "LRLLLLRLRLLLRLRLLRLLRRLLRLLLLLRRLRLRRLLLRLLLLLLRLL",
 "LRLRLRLLLLLLRRLLLRLLRLRLRRRRLLLLLRLLLLRLLRRLRLLLLL",
 "LLRLRRLLLRLLLRRLRLLRLRLLLLLLRLLRLRLRRLRLRLLLRRLLLR",
 "LLLLLLLLRRLLLLRLRRRLRRLLRRLRLLLLRLLRLRRLLLLLRRLLLL",
 "RRLLLLRRRRLLLLLRRLLLRLRLRRLLLLLLLRLRRRLRLLRLLLLLRL",
 "RLLRRLRRLRRLRLLRLLLRLRRRLRRLLLLLRRRLLRRLRLRLLRLLRL",
 "LRLRLLLRLLLRLRRRRRLRLRRLRLRLLLRRLRLLRLRLLLLRLLLLLR",
 "LLLLRRRRLLLRLLRRLLLLLRRLLLRLRRRLRLRLRRLLRLRRLLRLLL",
 "RLLRLLLRRLRRRRLLRLRRRRRLRLLRLLLLRRLLRLLLLRRLRLLLRL",
 "LRRLLLLRRLLRLLLLLRRLRLRRLRLLRRRRRLRLLLRLLRLRRLLLRR",
 "LLRRLLRLLRRLRRLRLRLLLRRRRLLLRLLRRLLLLRRLRLLRLLLLRR",
 "LRLLRRRRLLRLRLLLLLLLLLRLLRLLLRLRLRRRLLLLRRRRLRLLLR",
 "RRLLLLLLLLRRLLRRRLLRLLLLRLLLLLRLRLLLLRLRRRRRLRLLRL",
 "RRRLLRRLLLLLRRRLLLLLLLRLLLRRLLRLRRLLLRLRLLLLRLRLLL",
 "LLRRRRLRLRRRRRLRRRRRRRLLLLLLLLLLRRRRLLLRLRLLLLRRLL",
 "RLLLLRLLLRRLRRLRRLLRRLLLLLRLLRRRRRRLLLLRLLLLLRRLLL",
 "RLRLLRRRLLLLLRRRLLLRRLLLLLRRLLRRLRRRRLRRLLRRRLLLRL",
 "LLLRLLRLRRLLRRRLRRRLLLRLLLLLLRRLLRRRLLRRRRLRLLLRRL",
 "RLLLRLLRLLRLRLRLRLRRLLLLLLLRLRRLLRLRLRRRLLLRLLRRLR",
 "LLRLRLRLRLLLRRLLLRRLRRLLLLRRLLRLLLRLLRRLLRRRLRRLRL",
 "RRLRLRRLRRRLLLRRRLRRRLLLLLLLLLRRRRLRRLRRRLRLRRLRRL",
 "LLLRRLRLLRLRLLLRRLRRLLRLLLLRRLRRLRRLLLLRLLRLRLRRLL",
 "RLRLLLLLLRLRRLLRRRLLLRRLLRRLRLRRLLLRLLLLLRLRRLRLRR",
 "LLLLRRRRRLLLRLLRLRRLLRRRLRLLRLLLRLLLLRLRRRRLRRLLLL"};
            String[] commands2        = {"LLLLRRRLRLLLRRLRLRRLRLLLLLLLRRLRLRRLLRRLLRLRRRRLRL",
 "LLLRRLLRLRLLRLLLRLLLLRLLLLLRLLLLRRLRLRLRRLLLRRLLLL",
 "RRLLLRRRLRLLRLRLLRLLLRLLLRLLLLLLLRLLLLLLLLLRLLRLLR",
 "LLRLLRRRLLRLLRLRLRRLRLLLLLLLRLLLRRLRLLLLLLLLLLRRRL",
 "LLRRRRLLRLLLRRLLLLLLRLLLRRLLLRLRRRRLLLRRLLRLRLRLLL",
 "LLLRLLLRLLLLRLLLLLRRLLLRRLLLRLRRRRLLLRRLLLLLLLRLRL",
 "RLRRLRRRRRLRLRLLLRLRRRRLLRLRLLLLLLLLLLRRLLLLRRRLLL",
 "RRRLRLLRRRLLRRLLRLLLRLRLRRRLLRLLLLRLLRRRRLRRLLLRLL",
 "RLLRLLRLLLLLLRLLLRLLLLLRRRLRLRRRLLRRLRRRLLRRRRLLLR",
 "RRLRRRLLRLLLLRLRRLRLLLLLRLRRRLLLLLLRLLRLLRLLLRLLLL",
 "LLLRLLLRRLRLLLLLLLLRLLLLLLLLLRRLLLRLLRLLRLLLLLRLRR",
 "LRRRLLRRRRLRRLRLLRRRLLLLRRLRRLRRLRLLLLLLRLRRLLLRLL",
 "RLLLRRRRLLRLRLLRLLLRLLLRLLRRRLLRLLRRLLLLLLLRRRRRRL",
 "LLRRRLRLLRRRLRLLLRLRLLLLLRRLLRLLRLLLRLRLRLLRLLRLRR",
 "LLRLRRRRLLRRLLLRRLLRLLLLLLLRRLRLRLLRRLLLLRRRLLRRLR",
 "LRLLRLLRLLRLLLLLRLLRLLRLLRLRLLRLLLRLRLRLLLLLRLLLRR",
 "LRRRLRLLRLLRLLRRRLRRLRLLLRLRLLLLLLLLLRLRLRRRRRLRLL",
 "LLLLLLRLRLLLLRRRLRLLRRRRLRLRLLLLLRRLRRLLRRLLLRLLRL",
 "LRLRLLLLLLRLLLLLLLLLRLLLRLLRLLRLRRRRLRRRRLRLRLLLLL",
 "RLLLLLLRLRLLRRLRLLRLLRLLRLRLRRRLLRRLRLRLRRLLRRLRLR",
 "LRRRLRLLRLLLLLRLRLRLLLRLLLLLLRRLRLLLRLRLLRLLLLLRRL",
 "LRLLLRRLRLLLRLRRLLRLRLLLLLLRLLLLRRRLLLLRRRRLLRRRRL",
 "LRRLRLRLRRRRRRLRLLLRRRLLLRLLRRLLRLLRRLLRLLLRRRRLRL",
 "LLRLLRLRLRLLLRRLLLRLRLLLRLRRRLRLRLLLRRLLLRLRRLRLLR",
 "RRLLLLLLLLLLRRRLLLRRLLLLLRRLLLLRLRRLRLRLLLLLRRLLRL",
 "LRRRLLRLLLRRRLLLLRLRLLRRLLLRRRRRLLLLRRRLLLRRRLLLRL",
 "LRRLLLRLLRRLLLRLLRLRLRRLLLLRRRLLRLLLRLRLLRLLRLLLLL",
 "LRLLRRLLLLLLRRRLLRLLLLLLLLRRRLLLRLLRRRRLLLLLLLRLLL",
 "LLLRLLRRLLRLLRLRLLRRRLLLLLLRRLLLLLLLLLLLRLLLLLLRLR",
 "LLLLLRRRRLLLLLRRRRLLRLRRLLLLLLLLRRLLLRRRLRRLLRLRRL",
 "RLRLRLRRLLRLLRRRRLLRLLLLLRLRLLRLLLLLRLRRRRRLRRRLRR",
 "RLRRLRRRLLRLLRLRLRLLLLRLRLLLRLRRRLLLRRLLRRLLRLRRLL",
 "RLLLRRRRRLLLLLLLLLRRLRRLLLRLLLRLLLLRRLRLLLLLRLLLRR",
 "LLLRRRLLLLLLLLLRRLRLRLLLLRLLRLRRLLRRRLRRRRLRRRLLLL",
 "LRRLLLLLRLRLRLLRRRRLLLLRLRRLLRRRRLRLRLRRRRRRLRLLRL",
 "RRRLLRLLRLLRLRRLRRLLLLLRRLLLRLLRRLLLLRRRLRLRLRRLRL",
 "LRLRLLRLLRRRRLLRRRLLLLRRLRLLLLLLRRRLLRRLLLLLLRRRLL",
 "RRRRRLRLRLLRLLLRLRLRLLLLRLLLLRLRLLRLLRLLRLLRRRLLRL",
 "RLRRRLLLRLRLRRLLRLLLLLLLLLLRRLRRLRLLRRLRRLLRLRLLLL",
 "RRLRLRLRRLLLLLLRRLLLRRLRLLLLLLLLLLRLRLLLRLLRRLLLLR",
 "RLLLRLRRLLRLLRRRRLLRLLRLRLLLRLRLRRLLLLRLRLLRRRLLLL",
 "RLRLLRLLRRRLLLLRLLLRLLRLRRLLLLRRLRRRLRRRLLRLRRRRLR",
 "LLRLRLRLLRLRRRRRRLLLLLLLRLLLRLRLRLLRRRLRRLLLRLLRRL",
 "RLLRLLRRLRRLLLRLLRLLLLRRLLRLRRRLRRLRRLLRRRRRLLRLRL",
 "LRRLLLLLLLLLLRLLLLLRRLLLRRRRLRLRRLLLRRRRRRLRRRLRLL",
 "RRRLRLLLLLLLLRRLLRLRLRLRLLLLLRRLLRLLLLLRLLRRLLLRLL",
 "RLLLLRLRLLLLLRRLLLLRRLLRRLLLLLLRLLRLRLRLLRLLLLRLRR",
 "LLLRLRLRRRLRLLLRRLLLRLRLLRLRLLLLLLLLLRRLRLLLLRRRLR",
 "LRLRLLLLLRRLLRLRLLRLLLLRRLRRRLRRLRRRRLLLRRRLLRRLLL",
 "LRLLRRLRRLLLLRLLLLLLLLLLRLLLLLRRRLRLLLLLLLRLLRLLRR"};
            int minA                  = 1;
            int maxA                  = 5000;
            int minB                  = 1;
            int maxB                  = 5000;
            long expected__           = -19478907170L;

            return verifyCase(casenum__, expected__, new OneDimensionalRobot().theSum(commands1, commands2, minA, maxA, minB, maxB));
        }

        // custom cases

/*      case 5: {
            String[] commands1        = ;
            String[] commands2        = ;
            int minA                  = ;
            int maxA                  = ;
            int minB                  = ;
            int maxB                  = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new OneDimensionalRobot().theSum(commands1, commands2, minA, maxA, minB, maxB));
        }*/
/*      case 6: {
            String[] commands1        = ;
            String[] commands2        = ;
            int minA                  = ;
            int maxA                  = ;
            int minB                  = ;
            int maxB                  = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new OneDimensionalRobot().theSum(commands1, commands2, minA, maxA, minB, maxB));
        }*/
/*      case 7: {
            String[] commands1        = ;
            String[] commands2        = ;
            int minA                  = ;
            int maxA                  = ;
            int minB                  = ;
            int maxB                  = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new OneDimensionalRobot().theSum(commands1, commands2, minA, maxA, minB, maxB));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
