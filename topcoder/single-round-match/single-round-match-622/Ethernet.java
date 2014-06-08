import java.math.*;
import java.util.*;

public class Ethernet {
    public int connect(int[] parent, int[] length, int limit) {
        int n = parent.length + 1;
        int result = 1;
        int[] distance = new int[n];
        for (int i = n - 1; i >= 0; -- i) {
            List <Integer> children = new ArrayList <Integer>();
            for (int j = 1; j < n; ++ j) {
                if (parent[j - 1] == i) {
                    children.add(distance[j] + length[j - 1]);
                }
            }
            Collections.sort(children);
            for (int child : children) {
                if (distance[i] + child > limit) {
                    result ++;
                } else {
                    distance[i] = child;
                }
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
            EthernetHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                EthernetHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class EthernetHarness {
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
            int[] parent              = {0,0,0};
            int[] dist                = {1,1,1};
            int maxDist               = 2;
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new Ethernet().connect(parent, dist, maxDist));
        }
        case 1: {
            int[] parent              = {0,0,0,0,0,0,0};
            int[] dist                = {1,2,3,4,5,6,7};
            int maxDist               = 8;
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new Ethernet().connect(parent, dist, maxDist));
        }
        case 2: {
            int[] parent              = {0,1,2,3,4,5};
            int[] dist                = {1,2,3,4,5,6};
            int maxDist               = 6;
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new Ethernet().connect(parent, dist, maxDist));
        }
        case 3: {
            int[] parent              = {0,0,0,1,1};
            int[] dist                = {1,1,1,1,1};
            int maxDist               = 2;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new Ethernet().connect(parent, dist, maxDist));
        }
        case 4: {
            int[] parent              = {0,1,0,3,0,5,0,6,0,6,0,6,4,6,9,4,5,5,2,5,2};
            int[] dist                = {93,42,104,105,59,73,161,130,30,81,62,93,131,133,139,5,13,34,25,111,4};
            int maxDist               = 162;
            int expected__            = 11;

            return verifyCase(casenum__, expected__, new Ethernet().connect(parent, dist, maxDist));
        }

        // custom cases

/*      case 5: {
            int[] parent              = ;
            int[] dist                = ;
            int maxDist               = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new Ethernet().connect(parent, dist, maxDist));
        }*/
/*      case 6: {
            int[] parent              = ;
            int[] dist                = ;
            int maxDist               = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new Ethernet().connect(parent, dist, maxDist));
        }*/
/*      case 7: {
            int[] parent              = ;
            int[] dist                = ;
            int maxDist               = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new Ethernet().connect(parent, dist, maxDist));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
