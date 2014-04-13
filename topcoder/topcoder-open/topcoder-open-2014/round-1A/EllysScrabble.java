import java.math.*;
import java.util.*;

public class EllysScrabble {
    public String getMin(String letters, int maxDistance) {
        int n = letters.length();
        String result = "";
        for (int i = 0; i < n; ++ i) {
            for (int c = 0; c < 26; ++ c) {
                String newResult = result + String.format("%c", 'A' + c);
                if (check(letters, maxDistance, newResult)) {
                    result = newResult;
                    break;
                }
            }
        }
        return result;
    }

    boolean check(String letters, int maxDistance, String prefix) {
        int n = letters.length();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; ++ i) {
            boolean found = false;
            for (int j = 0; j < n && !found; ++ j) {
                if (Math.abs(i - j) <= maxDistance && !visited[j]) {
                    if (i < prefix.length() && prefix.charAt(i) != letters.charAt(j)) {
                        continue;
                    }
                    visited[j] = found = true;
                }
            }
            if (!found) {
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
            EllysScrabbleHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                EllysScrabbleHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class EllysScrabbleHarness {
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
            String letters            = "TOPCODER";
            int maxDistance           = 3;
            String expected__         = "CODTEPOR";

            return verifyCase(casenum__, expected__, new EllysScrabble().getMin(letters, maxDistance));
        }
        case 1: {
            String letters            = "ESPRIT";
            int maxDistance           = 3;
            String expected__         = "EIPRST";

            return verifyCase(casenum__, expected__, new EllysScrabble().getMin(letters, maxDistance));
        }
        case 2: {
            String letters            = "BAZINGA";
            int maxDistance           = 8;
            String expected__         = "AABGINZ";

            return verifyCase(casenum__, expected__, new EllysScrabble().getMin(letters, maxDistance));
        }
        case 3: {
            String letters            = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            int maxDistance           = 9;
            String expected__         = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

            return verifyCase(casenum__, expected__, new EllysScrabble().getMin(letters, maxDistance));
        }
        case 4: {
            String letters            = "GOODLUCKANDHAVEFUN";
            int maxDistance           = 7;
            String expected__         = "CADDGAHEOOFLUKNNUV";

            return verifyCase(casenum__, expected__, new EllysScrabble().getMin(letters, maxDistance));
        }
        case 5: {
            String letters            = "AAAWDIUAOIWDESBEAIWODJAWDBPOAWDUISAWDOOPAWD";
            int maxDistance           = 6;
            String expected__         = "AAAADDEIBWAEUIODWADSBIAJWODIAWDOPOAWDUOSPWW";

            return verifyCase(casenum__, expected__, new EllysScrabble().getMin(letters, maxDistance));
        }
        case 6: {
            String letters            = "ABRACADABRA";
            int maxDistance           = 2;
            String expected__         = "AABARACBDAR";

            return verifyCase(casenum__, expected__, new EllysScrabble().getMin(letters, maxDistance));
        }

        // custom cases

/*      case 7: {
            String letters            = ;
            int maxDistance           = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new EllysScrabble().getMin(letters, maxDistance));
        }*/
/*      case 8: {
            String letters            = ;
            int maxDistance           = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new EllysScrabble().getMin(letters, maxDistance));
        }*/
/*      case 9: {
            String letters            = ;
            int maxDistance           = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new EllysScrabble().getMin(letters, maxDistance));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
