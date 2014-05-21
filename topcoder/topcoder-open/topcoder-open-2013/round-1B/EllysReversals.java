import java.util.*;
import java.math.*;

public class EllysReversals {
    void process(int[][] c, String a, int d) {
        int n = a.length();
        for (int i = 0; i + 1 < n; i += 2) {
            int x = a.charAt(i) - 'a';
            int y = a.charAt(i + 1) - 'a';
            if (x <= y) {
                c[x][y] += d;
            } else {
                c[y][x] += d;
            }
        }
    }

    boolean equal(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        int n = a.length();
        if (n % 2 == 1 && a.charAt(n - 1) != b.charAt(n - 1)) {
            return false;
        }
        int[][] count = new int[26][26];
        process(count, a, 1);
        process(count, b, -1);
        for (int i = 0; i < 26; ++ i) {
            for (int j = 0; j < 26; ++ j) {
                if (count[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getMin(String[] words) {
        int n = words.length;
        int answer = 0;
        boolean[] visit = new boolean[n];
        for (int i = 0; i < n; ++ i) {
            if (visit[i]) {
                continue;
            }
            int size = 0;
            for (int j = 0; j < n; ++ j) {
                if (!visit[j] && equal(words[i], words[j])) {
                    size ++;
                    visit[j] = true;
                }
            }
            answer += size % 2;
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            EllysReversalsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                EllysReversalsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class EllysReversalsHarness {
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
            String[] words            = {"esprit", "god", "redotopc", "odcpoter", "dog"};
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new EllysReversals().getMin(words));
        }
        case 1: {
            String[] words            = {"no", "zaphod", "just", "very", "improbable"};
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new EllysReversals().getMin(words));
        }
        case 2: {
            String[] words            = {"rats", "live", "stressed", "to", "act", "as", "star", "desserts", "of", "evil", "cat", "sa", "fo", "ot"};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new EllysReversals().getMin(words));
        }
        case 3: {
            String[] words            = {"topcoder", "redocpot", "doretopc", "cpotdoer", "harlemshake"};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new EllysReversals().getMin(words));
        }
        case 4: {
            String[] words            = {"iprlzgukfggzg", "bmhxvjbrtkbxy", "khapjiabbny", "nqlwgmcyvdikt",
 "nxromtvtpug", "leealcapovm", "ushnxwjczczbmd", "bwhykzupcux",
 "xrlboyuwlnsp", "bbjoketeheezfs", "dxfztrldomjqkv", "dkbktqdtgfujcut",
 "zfybzyuxgpnt", "ffmsldrdftode", "vopuufksxd", "pqhbsiujwda",
 "yhwbkzupcux", "hkbabnapjiy", "zqsqefrrzehtxn", "yovinyguyudmv"};
            int expected__            = 16;

            return verifyCase(casenum__, expected__, new EllysReversals().getMin(words));
        }

        // custom cases

/*      case 5: {
            String[] words            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new EllysReversals().getMin(words));
        }*/
/*      case 6: {
            String[] words            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new EllysReversals().getMin(words));
        }*/
/*      case 7: {
            String[] words            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new EllysReversals().getMin(words));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
