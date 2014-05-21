import java.math.*;
import java.util.*;

public class PasswordXPalindrome {
    int[] parent;

    int find(int e) {
        if (parent[e] == -1) {
            return e;
        }
        return parent[e] = find(parent[e]);
    }

    public int minSwaps(String password) {
        int n = password.length();
        int[] count = new int[26];
        for (int i = 0; i < n; ++ i) {
            count[password.charAt(i) - 'a'] ++;
        }
        int answer = 0;
        if (n % 2 == 1) {
            int i = 0;
            while (i < 26 && count[i] % 2 == 0) {
                i ++;
            }
            if (i == 26) {
                return -1;
            }
            count[i] = 0;
            for (int j = 0; j < n; ++ j) {
                if (password.charAt(j) - 'a' == i) {
                    if (j != (n >> 1)) {
                        answer ++;
                    }
                    password = password.substring(0, j) + password.charAt(n >> 1) + password.substring(j + 1);
                    password = password.substring(0, n >> 1) + password.substring((n >> 1) + 1);
                    n --;
                    break;
                }
            }
        }
        for (int i = 0; i < 26; ++ i) {
            if (count[i] % 2 == 1) {
                return -1;
            }
        }
        parent = new int[n >> 1];
        Arrays.fill(parent, -1);
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < i; ++ j) {
                if (password.charAt(i) == password.charAt(j)) {
                    int x = Math.min(i, n - 1 - i);
                    int y = Math.min(j, n - 1 - j);
                    if (find(x) != find(y)) {
                        parent[find(x)] = find(y);
                        answer ++;
                    }
                }
            }
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            PasswordXPalindromeHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                PasswordXPalindromeHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class PasswordXPalindromeHarness {
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
            String password           = "levle";
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new PasswordXPalindrome().minSwaps(password));
        }
        case 1: {
            String password           = "racecar";
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new PasswordXPalindrome().minSwaps(password));
        }
        case 2: {
            String password           = "abcdadcb";
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new PasswordXPalindrome().minSwaps(password));
        }
        case 3: {
            String password           = "msmscielciel";
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new PasswordXPalindrome().minSwaps(password));
        }
        case 4: {
            String password           = "hicanyouguesstodaywriter";
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new PasswordXPalindrome().minSwaps(password));
        }

        // custom cases

/*      case 5: {
            String password           = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new PasswordXPalindrome().minSwaps(password));
        }*/
/*      case 6: {
            String password           = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new PasswordXPalindrome().minSwaps(password));
        }*/
/*      case 7: {
            String password           = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new PasswordXPalindrome().minSwaps(password));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
