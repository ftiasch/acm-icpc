import java.math.*;
import java.util.*;

public class EmoticonsDiv1 {
    public int printSmiles(int n) {
        int[][] minimum = new int[n << 1][n << 1];
        for (int i = 0; i < n << 1; ++ i) {
            Arrays.fill(minimum[i], -1);
        }
        Queue <Integer> queue = new LinkedList <Integer>();
        minimum[1][0] = 0;
        queue.offer(1 << 16);
        while (!queue.isEmpty()) {
            int state = queue.poll();
            int message = state >> 16;
            int clipboard = state & 65535;
            if (minimum[message][message] == -1) {
                minimum[message][message] = minimum[message][clipboard] + 1;
                queue.offer(message << 16 | message);
            }
            if (message + clipboard < n << 1 && minimum[message + clipboard][clipboard] == -1) {
                minimum[message + clipboard][clipboard] = minimum[message][clipboard] + 1;
                queue.offer(message + clipboard << 16 | clipboard);
            }
            if (message >= 1 && minimum[message - 1][clipboard] == -1) {
                minimum[message - 1][clipboard] = minimum[message][clipboard] + 1;
                queue.offer(message - 1 << 16 | clipboard);
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n << 1; ++ i) {
            if (minimum[n][i] >= 0) {
                result = Math.min(result, minimum[n][i]);
            }
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            EmoticonsDiv1Harness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                EmoticonsDiv1Harness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class EmoticonsDiv1Harness {
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
            int smiles                = 2;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new EmoticonsDiv1().printSmiles(smiles));
        }
        case 1: {
            int smiles                = 4;
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new EmoticonsDiv1().printSmiles(smiles));
        }
        case 2: {
            int smiles                = 6;
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new EmoticonsDiv1().printSmiles(smiles));
        }
        case 3: {
            int smiles                = 18;
            int expected__            = 8;

            return verifyCase(casenum__, expected__, new EmoticonsDiv1().printSmiles(smiles));
        }
        case 4: {
            int smiles                = 11;
            int expected__            = 8;

            return verifyCase(casenum__, expected__, new EmoticonsDiv1().printSmiles(smiles));
        }

        // custom cases

/*      case 5: {
            int smiles                = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new EmoticonsDiv1().printSmiles(smiles));
        }*/
/*      case 6: {
            int smiles                = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new EmoticonsDiv1().printSmiles(smiles));
        }*/
/*      case 7: {
            int smiles                = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new EmoticonsDiv1().printSmiles(smiles));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
