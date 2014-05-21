import java.math.*;
import java.util.*;

public class Egalitarianism {
    final static int INF = (int)1e9;

    public int maxDifference(String[] isFriend, int d) {
        int n = isFriend.length;
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                graph[i][j] = isFriend[i].charAt(j) == 'Y' ? 1 : INF;
            }
        }
        for (int k = 0; k < n; ++ k) {
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < i; ++ j) {
                if (graph[i][j] >= INF) {
                    return -1;
                }
                answer = Math.max(answer, graph[i][j] * d);
            }
        }
        return answer;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            EgalitarianismHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                EgalitarianismHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class EgalitarianismHarness {
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
            String[] isFriend         = {"NYN",
 "YNY",
 "NYN"};
            int d                     = 10;
            int expected__            = 20;

            return verifyCase(casenum__, expected__, new Egalitarianism().maxDifference(isFriend, d));
        }
        case 1: {
            String[] isFriend         = {"NN",
 "NN"};
            int d                     = 1;
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new Egalitarianism().maxDifference(isFriend, d));
        }
        case 2: {
            String[] isFriend         = {"NNYNNN",
 "NNYNNN",
 "YYNYNN",
 "NNYNYY",
 "NNNYNN",
 "NNNYNN"};
            int d                     = 1000;
            int expected__            = 3000;

            return verifyCase(casenum__, expected__, new Egalitarianism().maxDifference(isFriend, d));
        }
        case 3: {
            String[] isFriend         = {"NNYN",
 "NNNY",
 "YNNN",
 "NYNN"};
            int d                     = 584;
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new Egalitarianism().maxDifference(isFriend, d));
        }
        case 4: {
            String[] isFriend         = {"NYNYYYN",
 "YNNYYYN",
 "NNNNYNN",
 "YYNNYYN",
 "YYYYNNN",
 "YYNYNNY",
 "NNNNNYN"};
            int d                     = 5;
            int expected__            = 20;

            return verifyCase(casenum__, expected__, new Egalitarianism().maxDifference(isFriend, d));
        }
        case 5: {
            String[] isFriend         = {"NYYNNNNYYYYNNNN",
 "YNNNYNNNNNNYYNN",
 "YNNYNYNNNNYNNNN",
 "NNYNNYNNNNNNNNN",
 "NYNNNNYNNYNNNNN",
 "NNYYNNYNNYNNNYN",
 "NNNNYYNNYNNNNNN",
 "YNNNNNNNNNYNNNN",
 "YNNNNNYNNNNNYNN",
 "YNNNYYNNNNNNNNY",
 "YNYNNNNYNNNNNNN",
 "NYNNNNNNNNNNNNY",
 "NYNNNNNNYNNNNYN",
 "NNNNNYNNNNNNYNN",
 "NNNNNNNNNYNYNNN"}
;
            int d                     = 747;
            int expected__            = 2988;

            return verifyCase(casenum__, expected__, new Egalitarianism().maxDifference(isFriend, d));
        }
        case 6: {
            String[] isFriend         = {"NY",
 "YN"};
            int d                     = 0;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new Egalitarianism().maxDifference(isFriend, d));
        }

        // custom cases

/*      case 7: {
            String[] isFriend         = ;
            int d                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new Egalitarianism().maxDifference(isFriend, d));
        }*/
/*      case 8: {
            String[] isFriend         = ;
            int d                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new Egalitarianism().maxDifference(isFriend, d));
        }*/
/*      case 9: {
            String[] isFriend         = ;
            int d                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new Egalitarianism().maxDifference(isFriend, d));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
