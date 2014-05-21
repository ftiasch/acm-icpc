import java.math.*;
import java.util.*;

public class BallRemoval {
    public String canLeave(String label) {
        int n = label.length();
        boolean[][] can = new boolean[n + 1][n + 1];
        for (int i = 0; i <= n; ++ i) {
            can[i][i] = true;
        }
        for (int i = n - 1; i >= 0; -- i) {
            for (int j = i + 1; j <= n; ++ j) {
                for (int x = i; x < j; ++ x) {
                    for (int y = x + 1; y < j; ++ y) {
                        if (x != 0 && x != n - 1 && label.charAt(x) == '>') {
                            can[i][j] |= can[i][x] && can[x + 1][y] && can[y + 1][j];
                        }
                        if (y != 0 && y != n - 1 && label.charAt(y) == '<') {
                            can[i][j] |= can[i][x] && can[x + 1][y] && can[y + 1][j];
                        }
                    }
                }
            }
        }
        String answer = "";
        for (int i = 0; i < n; ++ i) {
            answer += can[0][i] && can[i + 1][n] ? 'o' : '.';
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            BallRemovalHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                BallRemovalHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class BallRemovalHarness {
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
            String label              = "<<>";
            String expected__         = "..o";

            return verifyCase(casenum__, expected__, new BallRemoval().canLeave(label));
        }
        case 1: {
            String label              = ">>><<";
            String expected__         = "o...o";

            return verifyCase(casenum__, expected__, new BallRemoval().canLeave(label));
        }
        case 2: {
            String label              = "<<><<" ;
            String expected__         = "....o";

            return verifyCase(casenum__, expected__, new BallRemoval().canLeave(label));
        }
        case 3: {
            String label              = "<><<><>";
            String expected__         = "o.....o";

            return verifyCase(casenum__, expected__, new BallRemoval().canLeave(label));
        }
        case 4: {
            String label              = ">>><<<>>>>><<<>";
            String expected__         = "o.....o.o.....o";

            return verifyCase(casenum__, expected__, new BallRemoval().canLeave(label));
        }

        // custom cases

/*      case 5: {
            String label              = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new BallRemoval().canLeave(label));
        }*/
/*      case 6: {
            String label              = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new BallRemoval().canLeave(label));
        }*/
/*      case 7: {
            String label              = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new BallRemoval().canLeave(label));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
