import java.math.*;
import java.util.*;

public class LeftRightDigitsGame2 {
    String concatenate(String a, String b) {
        return a == null || b == null ? null : a + b;
    }

    public String minNumber(String digits, String lowerBound) {
        int n = digits.length();
        String[][][][] memory = new String[n + 1][n + 1][2][3];
        for (int i = n; i >= 0; -- i) {
            for (int prefix = 0; prefix < 2; ++ prefix) {
                for (int suffix = 0; suffix < 3; ++ suffix) {
                    memory[i][i][prefix][suffix] = prefix == 0 && suffix == 0 ? null : "";
                }
            }
            for (int j = i + 1; j <= n; ++ j) {
                for (int prefix = 0; prefix < 2; ++ prefix) {
                    for (int suffix = 0; suffix < 3; ++ suffix) {
                        String best = null;
                        for (int k = i; k < j; ++ k) {
                            if (prefix == 0 && digits.charAt(k - i) < lowerBound.charAt(i)) {
                                continue;
                            }
                            int newPrefix = prefix;
                            if (digits.charAt(k - i) > lowerBound.charAt(i)) {
                                newPrefix = 1;
                            }
                            int newSuffix = suffix;
                            int ret = digits.substring(k + 1 - i, j - i).compareTo(lowerBound.substring(k + 1, j));
                            if (ret < 0) {
                                newSuffix = 0;
                            }
                            if (ret > 0) {
                                newSuffix = 2;
                            }
                            String value = concatenate(memory[i + 1][k + 1][newPrefix][newSuffix], digits.substring(k + 1 - i, j - i));
                            value = concatenate("" + digits.charAt(k - i), value);
                            if (value != null) {
                                if (best == null || value.compareTo(best) < 0) {
                                    best = value;
                                }
                            }
                        }
                        memory[i][j][prefix][suffix] = best;
                    }
                }
            }
        }
        String answer = memory[0][n][0][1];
        return answer == null ? "" : answer;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            LeftRightDigitsGame2Harness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                LeftRightDigitsGame2Harness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class LeftRightDigitsGame2Harness {
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
            String digits             = "565";
            String lowerBound         = "556";
            String expected__         = "556";

            return verifyCase(casenum__, expected__, new LeftRightDigitsGame2().minNumber(digits, lowerBound));
        }
        case 1: {
            String digits             = "565";
            String lowerBound         = "566";
            String expected__         = "655";

            return verifyCase(casenum__, expected__, new LeftRightDigitsGame2().minNumber(digits, lowerBound));
        }
        case 2: {
            String digits             = "565";
            String lowerBound         = "656";
            String expected__         = "";

            return verifyCase(casenum__, expected__, new LeftRightDigitsGame2().minNumber(digits, lowerBound));
        }
        case 3: {
            String digits             = "9876543210";
            String lowerBound         = "5565565565";
            String expected__         = "5678943210";

            return verifyCase(casenum__, expected__, new LeftRightDigitsGame2().minNumber(digits, lowerBound));
        }
        case 4: {
            String digits             = "8016352";
            String lowerBound         = "1000000";
            String expected__         = "1086352";

            return verifyCase(casenum__, expected__, new LeftRightDigitsGame2().minNumber(digits, lowerBound));
        }

        // custom cases

/*      case 5: {
            String digits             = ;
            String lowerBound         = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new LeftRightDigitsGame2().minNumber(digits, lowerBound));
        }*/
/*      case 6: {
            String digits             = ;
            String lowerBound         = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new LeftRightDigitsGame2().minNumber(digits, lowerBound));
        }*/
/*      case 7: {
            String digits             = ;
            String lowerBound         = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new LeftRightDigitsGame2().minNumber(digits, lowerBound));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
