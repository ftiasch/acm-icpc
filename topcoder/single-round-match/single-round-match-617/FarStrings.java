import java.math.*;
import java.util.*;

public class FarStrings {
    public String[] find(String target) {
        int n = target.length();
        String[] result = new String[n];
        for (int i = 0; i < n; ++ i) {
            result[i] = solve(target, i + 1);
        }
        return result;
    }

    String solve(String target, int distance) {
        int n = target.length();
        String result = "";
        for (int i = 0; i < n; ++ i) {
            int c = 0;
            while (c < 26 && !check(result + String.format("%c", 'a' + c), target, distance)) {
                c ++;
            }
            assert c < 26;
            result += String.format("%c", 'a' + c);
        }
        return result;
    }

    boolean check(String source, String target, int distance) {
        return getDistance(source, target, false) <= distance && distance <= getDistance(source, target, true);
    }

    int getDistance(String source, String target, boolean flag) {
        int n = target.length();
        int[][] minimum = new int[n + 1][n + 1];
        for (int i = 0; i <= n; ++ i) {
            for (int j = 0; j <= n; ++ j) {
                if (i > 0 || j > 0) {
                    minimum[i][j] = Integer.MAX_VALUE;
                    if (i > 0) {
                        minimum[i][j] = Math.min(minimum[i][j], minimum[i - 1][j] + 1);
                    }
                    if (j > 0) {
                        minimum[i][j] = Math.min(minimum[i][j], minimum[i][j - 1] + 1);
                    }
                    if (i > 0 && j > 0) {
                        minimum[i][j] = Math.min(minimum[i][j], minimum[i - 1][j - 1] + 1);
                        if (i - 1 < source.length() && source.charAt(i - 1) == target.charAt(j - 1) || i - 1 >= source.length() && !flag) {
                            minimum[i][j] = Math.min(minimum[i][j], minimum[i - 1][j - 1]);
                        }
                    }
                }
            }
        }
        return minimum[n][n];
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            FarStringsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                FarStringsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class FarStringsHarness {
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

    static boolean compareOutput(String[] expected, String[] result) { if (expected.length != result.length) return false; for (int i=0; i<expected.length; ++i) if (!expected[i].equals(result[i])) return false; return true; }

    static String formatResult(String[] res) {
        String ret = "";
        ret += "{";
        for (int i=0; i<res.length; ++i) {
            if (i > 0) ret += ",";
            ret += String.format(" \"%s\"", res[i]);
        }
        ret += " }";
        return ret;
    }

    static int verifyCase(int casenum, String[] expected, String[] received) {
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
            String t                  = "atan";
            String[] expected__       = {"aaan", "aaaa", "aaba", "babb" };

            return verifyCase(casenum__, expected__, new FarStrings().find(t));
        }
        case 1: {
            String t                  = "ir";
            String[] expected__       = {"ar", "aa" };

            return verifyCase(casenum__, expected__, new FarStrings().find(t));
        }
        case 2: {
            String t                  = "aaa";
            String[] expected__       = {"aab", "abb", "bbb" };

            return verifyCase(casenum__, expected__, new FarStrings().find(t));
        }
        case 3: {
            String t                  = "bazinga";
            String[] expected__       = {"aazinga", "aaainga", "aaaanga", "aaaaaga", "aaaaaaa", "aaaaaab", "abbaabb" };

            return verifyCase(casenum__, expected__, new FarStrings().find(t));
        }
        case 4: {
            String t                  = "bcdab";
            String[] expected__       = {"acdab", "aadab", "aaaab", "aaaaa", "aaaca" };

            return verifyCase(casenum__, expected__, new FarStrings().find(t));
        }

        // custom cases

/*      case 5: {
            String t                  = ;
            String[] expected__       = ;

            return verifyCase(casenum__, expected__, new FarStrings().find(t));
        }*/
/*      case 6: {
            String t                  = ;
            String[] expected__       = ;

            return verifyCase(casenum__, expected__, new FarStrings().find(t));
        }*/
/*      case 7: {
            String t                  = ;
            String[] expected__       = ;

            return verifyCase(casenum__, expected__, new FarStrings().find(t));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
