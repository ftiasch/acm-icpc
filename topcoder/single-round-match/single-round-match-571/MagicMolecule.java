import java.math.*;
import java.util.*;

public class MagicMolecule {
    int search(int[] magicPower, int[] a, int[] b, int[] state, int i, int left, int sum) {
        if (left < 0) {
            return Integer.MIN_VALUE;
        }
        if (i == a.length) {
            return sum;
        }
        if (state[a[i]] == 1 || state[b[i]] == 1) {
            return search(magicPower, a, b, state, i + 1, left, sum);
        }
        int result = Integer.MIN_VALUE;
        if (state[a[i]] == -1) {
            state[a[i]] = 1;
            result = Math.max(result, search(magicPower, a, b, state, i + 1, left - 1, sum - magicPower[a[i]]));
            state[a[i]] = -1;
        }
        if (state[b[i]] == -1) {
            state[b[i]] = 1;
            result = Math.max(result, search(magicPower, a, b, state, i + 1, left - 1, sum - magicPower[b[i]]));
            state[b[i]] = -1;
        }
        return result;
    }

    public int maxMagicPower(int[] magicPower, String[] magicBond) {
        int n = magicPower.length;
        boolean[][] graph = new boolean[n][n];
        int m = 0;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                graph[i][j] = i != j && magicBond[i].charAt(j) == 'N';
                if (i < j && graph[i][j]) {
                    m ++;
                }
            }
        }
        int[] a = new int[m];
        int[] b = new int[m];
        m = 0;
        for (int j = 0; j < n; ++ j) {
            for (int i = 0; i < j; ++ i) {
                if (graph[i][j]) {
                    a[m] = i;
                    b[m ++] = j;
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < n; ++ i) {
            sum += magicPower[i];
        }
        int[] state = new int[n];
        Arrays.fill(state, -1);
        int result = search(magicPower, a, b, state, 0, n / 3, sum);
        return result == Integer.MIN_VALUE ? -1 : result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            MagicMoleculeHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                MagicMoleculeHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class MagicMoleculeHarness {
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
            int[] magicPower          = {1,2,3};
            String[] magicBond        = {"NYY","YNN","YNN"};
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new MagicMolecule().maxMagicPower(magicPower, magicBond));
        }
        case 1: {
            int[] magicPower          = {1,1,1,1};
            String[] magicBond        = {"NNYY","NNYY","YYNN","YYNN"};
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new MagicMolecule().maxMagicPower(magicPower, magicBond));
        }
        case 2: {
            int[] magicPower          = {86,15,100,93,53,50};
            String[] magicBond        = {"NYYYYN","YNNNNY","YNNYYN","YNYNYN","YNYYNY","NYNNYN"};
            int expected__            = 332;

            return verifyCase(casenum__, expected__, new MagicMolecule().maxMagicPower(magicPower, magicBond));
        }
        case 3: {
            int[] magicPower          = {3969,9430,7242,8549,8190,8368,3704,9740,1691};
            String[] magicBond        = {"NYYYYYYYY","YNYYYYYYY","YYNYYYYYY","YYYNYYYYY","YYYYNYYYY","YYYYYNYYY","YYYYYYNNY","YYYYYYNNY","YYYYYYYYN"};
            int expected__            = 57179;

            return verifyCase(casenum__, expected__, new MagicMolecule().maxMagicPower(magicPower, magicBond));
        }

        // custom cases

/*      case 4: {
            int[] magicPower          = ;
            String[] magicBond        = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new MagicMolecule().maxMagicPower(magicPower, magicBond));
        }*/
/*      case 5: {
            int[] magicPower          = ;
            String[] magicBond        = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new MagicMolecule().maxMagicPower(magicPower, magicBond));
        }*/
/*      case 6: {
            int[] magicPower          = ;
            String[] magicBond        = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new MagicMolecule().maxMagicPower(magicPower, magicBond));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
