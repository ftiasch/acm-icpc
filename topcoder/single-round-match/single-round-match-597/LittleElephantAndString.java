import java.math.*;
import java.util.*;

public class LittleElephantAndString {
    boolean equivalent(String u, String v) {
        char[] a = u.toCharArray();
        char[] b = v.toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);
        return Arrays.equals(a, b);
    }

    boolean isSubsequence(String a, String b) {
        int i = 0;
        for (char c : a.toCharArray()) {
            while (i < b.length() && c != b.charAt(i)) {
                i ++;
            }
            if (i >= b.length()) {
                return false;
            }
            i ++;
        }
        return true;
    }

    public int getNumber(String a, String b) {
        if (!equivalent(a, b)) {
            return -1;
        }
        int n = a.length();
        for (int prefix = 0; prefix <= n; ++ prefix) {
            if (isSubsequence(b.substring(prefix), a)) {
                return prefix;
            }
        }
        return -1;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            LittleElephantAndStringHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                LittleElephantAndStringHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class LittleElephantAndStringHarness {
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
            String A                  = "ABC";
            String B                  = "CBA";
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new LittleElephantAndString().getNumber(A, B));
        }
        case 1: {
            String A                  = "A";
            String B                  = "B";
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new LittleElephantAndString().getNumber(A, B));
        }
        case 2: {
            String A                  = "AAABBB";
            String B                  = "BBBAAA";
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new LittleElephantAndString().getNumber(A, B));
        }
        case 3: {
            String A                  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String B                  = "ZYXWVUTSRQPONMLKJIHGFEDCBA";
            int expected__            = 25;

            return verifyCase(casenum__, expected__, new LittleElephantAndString().getNumber(A, B));
        }
        case 4: {
            String A                  = "A";
            String B                  = "A";
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new LittleElephantAndString().getNumber(A, B));
        }
        case 5: {
            String A                  = "DCABA";
            String B                  = "DACBA";
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new LittleElephantAndString().getNumber(A, B));
        }

        // custom cases

/*      case 6: {
            String A                  = ;
            String B                  = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new LittleElephantAndString().getNumber(A, B));
        }*/
/*      case 7: {
            String A                  = ;
            String B                  = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new LittleElephantAndString().getNumber(A, B));
        }*/
/*      case 8: {
            String A                  = ;
            String B                  = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new LittleElephantAndString().getNumber(A, B));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
