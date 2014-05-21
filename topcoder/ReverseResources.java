import java.math.*;
import java.util.*;

public class ReverseResources {
    final static int MOD = 1000000007;

    int count(String string, String resource, int start, int mainWays[][]) {
        int n = string.length();
        int m = resource.length();
        int exactLength = m;
        for (int i = 0; i < m; ++ i) {
            if (resource.charAt(i) == '%') {
                exactLength --;
            }
        }
        if (exactLength > n) {
            return 0;
        }
        int ways[][] = new int[n + 1][m + 1];
        for (int i = 0; i <= n; ++ i) {
            for (int j = 0; j <= m; ++ j) {
                ways[i][j] = 0;
            }
        }
        ways[n][m] = 1;
        for (int i = n - 1; i >= 0; -- i) {
            for (int j = m - 1; j >= 0; -- j) {
                if (resource.charAt(j) == '%') {
                    for (int k = i; k < n; ++ k) {
                        ways[i][j] += (int)((long)ways[k + 1][j + 2] * mainWays[start + i][start + k] % MOD);
                        ways[i][j] %= MOD;
                    }
                } else if (string.charAt(i) == resource.charAt(j)) {
                    ways[i][j] += ways[i + 1][j + 1];
                    ways[i][j] %= MOD;
                }
            }
        }
        return ways[0][0];
    }

    public int findDecompositions(String string, String[] resources) {
        int n = string.length();
        int m = resources.length;
        int ways[][] = new int[n][n];
        for (int i = n - 1; i >= 0; -- i) {
            for (int j = i; j < n; ++ j) {
                ways[i][j] = 0;
                for (int k = 0; k < m; ++ k) {
                    ways[i][j] += count(string.substring(i, j + 1), resources[k], i, ways);
                    ways[i][j] %= MOD;
                }
            }
        }
        return ways[0][n - 1];
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            ReverseResourcesHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                ReverseResourcesHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class ReverseResourcesHarness {
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
            String str                = "Error in module foo, code 123.";
            String[] resources        = {"foo", "123", "Error in module %s.", "%s, code %s"};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new ReverseResources().findDecompositions(str, resources));
        }
        case 1: {
            String str                = "The fox jumped over the dog.";
            String[] resources        = {"The fox %s over the dog.",
 "lazy",
 "brown",
 "jump%s",
 "s",
 "ed",
 "The %s",
 "fox %s",
 "%s over %s",
 "the dog."};
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new ReverseResources().findDecompositions(str, resources));
        }
        case 2: {
            String str                = "abcde";
            String[] resources        = {"%sc%s", "b", "de", "a%s"};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new ReverseResources().findDecompositions(str, resources));
        }
        case 3: {
            String str                = "abcde";
            String[] resources        = {"%se", "a%s", "%sb%s", "%sc%s", "%sd%s"};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new ReverseResources().findDecompositions(str, resources));
        }
        case 4: {
            String str                = "aaaaa";
            String[] resources        = {"a","%s%s"};
            int expected__            = 14;

            return verifyCase(casenum__, expected__, new ReverseResources().findDecompositions(str, resources));
        }
        case 5: {
            String str                = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
            String[] resources        = {"a","aa","%s%s","%sa","a%s","aaa","aa%s","a%sa",
"a%s%s","%saa","%sa%s","%s%sa","%s%s%s","aaaa",
"aaa%s","aa%sa","aa%s%s","a%saa","a%sa%s","a%s%sa",
"a%s%s%s","%saaa","%saa%s","%sa%sa","%sa%s%s",
"%s%saa","%s%sa%s","%s%s%sa","%s%s%s%s","aaaaa",
"aaaa%s","aaa%sa","aaa%s%s","aa%saa","aa%sa%s",
"aa%s%sa","aa%s%s%s","a%saaa","a%saa%s","a%sa%sa",
"a%sa%s%s","a%s%saa","a%s%sa%s","a%s%s%sa",
"a%s%s%s%s","%saaaa","%saaa%s","%saa%sa","%saa%s%s"};
            int expected__            = 879704799;

            return verifyCase(casenum__, expected__, new ReverseResources().findDecompositions(str, resources));
        }
        case 6: {
            String str                = "aa";
            String[] resources        = {"a", "a", "%s%s", "%s%s"};
            int expected__            = 8;

            return verifyCase(casenum__, expected__, new ReverseResources().findDecompositions(str, resources));
        }

        // custom cases

/*      case 7: {
            String str                = ;
            String[] resources        = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ReverseResources().findDecompositions(str, resources));
        }*/
/*      case 8: {
            String str                = ;
            String[] resources        = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ReverseResources().findDecompositions(str, resources));
        }*/
/*      case 9: {
            String str                = ;
            String[] resources        = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ReverseResources().findDecompositions(str, resources));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
