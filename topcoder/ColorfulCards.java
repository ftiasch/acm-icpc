import java.math.*;
import java.util.*;

public class ColorfulCards {
    boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i * i <= n; ++ i) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int[] theCards(int n, String colors) {
        int m = colors.length();
        int[] low = new int[n];
        for (int i = 0; i < m; ++ i) {
            low[i] = i > 0 ? low[i - 1] + 1 : 1;
            while (isPrime(low[i]) ^ colors.charAt(i) == 'R') {
                low[i] ++;
            }
        }
        int[] high = new int[m];
        for (int i = m - 1; i >= 0; -- i) {
            high[i] = i + 1 < m ? high[i + 1] - 1 : n;
            while (isPrime(high[i]) ^ colors.charAt(i) == 'R') {
                high[i] --;
            }
        }
        int[] answer = new int[m];
        for (int i = 0; i < m; ++ i) {
            answer[i] = low[i] == high[i] ? low[i] : -1;
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            ColorfulCardsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                ColorfulCardsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class ColorfulCardsHarness {
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

    static boolean compareOutput(int[] expected, int[] result) { if (expected.length != result.length) return false; for (int i=0; i<expected.length; ++i) if (expected[i] != result[i]) return false; return true; }

    static String formatResult(int[] res) {
        String ret = "";
        ret += "{";
        for (int i=0; i<res.length; ++i) {
            if (i > 0) ret += ",";
            ret += String.format(" %d", res[i]);
        }
        ret += " }";
        return ret;
    }

    static int verifyCase(int casenum, int[] expected, int[] received) {
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
            int N                     = 5;
            String colors             = "RRR";
            int[] expected__          = {2, 3, 5 };

            return verifyCase(casenum__, expected__, new ColorfulCards().theCards(N, colors));
        }
        case 1: {
            int N                     = 7;
            String colors             = "BBB";
            int[] expected__          = {1, 4, 6 };

            return verifyCase(casenum__, expected__, new ColorfulCards().theCards(N, colors));
        }
        case 2: {
            int N                     = 6;
            String colors             = "RBR";
            int[] expected__          = {-1, 4, 5 };

            return verifyCase(casenum__, expected__, new ColorfulCards().theCards(N, colors));
        }
        case 3: {
            int N                     = 58;
            String colors             = "RBRRBRBBRBRRBBRRBBBRRBBBRR";
            int[] expected__          = {-1, -1, -1, -1, -1, -1, -1, -1, 17, 18, 19, 23, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 47, 53 };

            return verifyCase(casenum__, expected__, new ColorfulCards().theCards(N, colors));
        }
        case 4: {
            int N                     = 495;
            String colors             = "RBRRBRBBRBRRBBRRBBBRRBBBRR";
            int[] expected__          = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };

            return verifyCase(casenum__, expected__, new ColorfulCards().theCards(N, colors));
        }

        // custom cases

/*      case 5: {
            int N                     = ;
            String colors             = ;
            int[] expected__          = ;

            return verifyCase(casenum__, expected__, new ColorfulCards().theCards(N, colors));
        }*/
/*      case 6: {
            int N                     = ;
            String colors             = ;
            int[] expected__          = ;

            return verifyCase(casenum__, expected__, new ColorfulCards().theCards(N, colors));
        }*/
/*      case 7: {
            int N                     = ;
            String colors             = ;
            int[] expected__          = ;

            return verifyCase(casenum__, expected__, new ColorfulCards().theCards(N, colors));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
