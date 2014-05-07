import java.math.*;
import java.util.*;

public class AmebaDiv1 {
    public int count(int[] xs) {
        Set <Integer> set = new TreeSet <Integer>();
        for (int x : xs) {
            if (!set.contains(x)) {
                set.add(x);
                if (set.contains(x * 2)) {
                    set.remove(x * 2);
                }
            }
        }
        return set.size();
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            AmebaDiv1Harness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                AmebaDiv1Harness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class AmebaDiv1Harness {
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
            int[] X                   = {3,2,1};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new AmebaDiv1().count(X));
        }
        case 1: {
            int[] X                   = {2,2,2,2,2,2,4,2,2,2};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new AmebaDiv1().count(X));
        }
        case 2: {
            int[] X                   = {1,2,4,8,16,32,64,128,256,1024,2048};
            int expected__            = 11;

            return verifyCase(casenum__, expected__, new AmebaDiv1().count(X));
        }
        case 3: {
            int[] X                   = {854,250,934,1000,281,250,281,467,854,562,934,1000,854,500,562};
            int expected__            = 7;

            return verifyCase(casenum__, expected__, new AmebaDiv1().count(X));
        }

        // custom cases

/*      case 4: {
            int[] X                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new AmebaDiv1().count(X));
        }*/
/*      case 5: {
            int[] X                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new AmebaDiv1().count(X));
        }*/
/*      case 6: {
            int[] X                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new AmebaDiv1().count(X));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
