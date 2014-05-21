import java.math.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class TurnOnLamps {
    int edgeCount;
    int[] firstEdge, to, type, nextEdge;

    void addEdge(int u, int v, int t) {
        to[edgeCount] = v;
        type[edgeCount] = t;
        nextEdge[edgeCount] = firstEdge[u];
        firstEdge[u] = edgeCount ++;
    }

    int answer;

    int solve(int p, int u) {
        int count = 0;
        for (int iter = firstEdge[u]; iter != -1; iter = nextEdge[iter]) {
            int v = to[iter];
            if (v != p) {
                int ret = solve(u, v);
                if (type[iter] != -1) {
                    if (type[iter] == 1 && ret == 0) {
                        answer ++;
                    }
                    ret = type[iter];
                }
                if (count == 1 && ret == 1) {
                    answer --;
                }
                count ^= ret;
            }
        }
        return count;
    }

    public int minimize(int[] roads, String initState, String isImportant) {
        int n = roads.length + 1;
        edgeCount = 0;
        firstEdge = new int[n];
        Arrays.fill(firstEdge, -1);
        to = new int[n - 1 << 1];
        type = new int[n - 1 << 1];
        nextEdge = new int[n - 1 << 1];
        for (int i = 0; i < n - 1; ++ i) {
            int t = isImportant.charAt(i) == '1' ? (initState.charAt(i) == '0' ? 1 : 0) : -1;
            addEdge(i + 1, roads[i], t);
            addEdge(roads[i], i + 1, t);
        }
        answer = 0;
        solve(-1, 0);
        return answer;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            TurnOnLampsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                TurnOnLampsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class TurnOnLampsHarness {
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
            int[] roads               = {0,0,1,1};
            String initState          = "0001";
            String isImportant        = "0111";
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new TurnOnLamps().minimize(roads, initState, isImportant));
        }
        case 1: {
            int[] roads               = {0,0,1,1};
            String initState          = "0000";
            String isImportant        = "0111";
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new TurnOnLamps().minimize(roads, initState, isImportant));
        }
        case 2: {
            int[] roads               = {0,0,1,1,4,4};
            String initState          = "000100";
            String isImportant        = "111111";
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new TurnOnLamps().minimize(roads, initState, isImportant));
        }
        case 3: {
            int[] roads               = {0,0,1,1,4,4};
            String initState          = "100100";
            String isImportant        = "011101";
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new TurnOnLamps().minimize(roads, initState, isImportant));
        }
        case 4: {
            int[] roads               = {0,0,2,2,3,1,6,3,1};
            String initState          = "010001110";
            String isImportant        = "000110100";
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new TurnOnLamps().minimize(roads, initState, isImportant));
        }
        case 5: {
            int[] roads               = {0,0,1,2,4,4,6,1,2,5,2,8,8,3,6,4,14,7,18,14,11,7,1,12,7,5,18,23,0,14,11,10,2,2,6,1,30,11,9,12,5,35,25,11,23,17,14,45,15};
            String initState          = "0000000000010000000000000010000010100000000000000";
            String isImportant        = "1010111111111011011111000110111111111111111110111";
            int expected__            = 14;

            return verifyCase(casenum__, expected__, new TurnOnLamps().minimize(roads, initState, isImportant));
        }

        // custom cases

/*      case 6: {
            int[] roads               = ;
            String initState          = ;
            String isImportant        = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TurnOnLamps().minimize(roads, initState, isImportant));
        }*/
/*      case 7: {
            int[] roads               = ;
            String initState          = ;
            String isImportant        = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TurnOnLamps().minimize(roads, initState, isImportant));
        }*/
/*      case 8: {
            int[] roads               = ;
            String initState          = ;
            String isImportant        = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TurnOnLamps().minimize(roads, initState, isImportant));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
