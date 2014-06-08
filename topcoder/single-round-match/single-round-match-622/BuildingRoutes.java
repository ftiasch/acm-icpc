import java.math.*;
import java.util.*;

public class BuildingRoutes {
    public int build(String[] raw, int limit) {
        int n = raw.length;
        int[][] graph = new int[n][n];
        int[][] distance = new int[n][n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                graph[i][j] = distance[i][j] = raw[i].charAt(j) - '0';
            }
        }
        for (int k = 0; k < n; ++ k) {
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    if (distance[i][j] < Integer.MAX_VALUE && distance[j][k] < Integer.MAX_VALUE) {
                        distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                    }
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                if (i != j) {
                    int count = 0;
                    for (int x = 0; x < n; ++ x) {
                        for (int y = 0; y < n; ++ y) {
                            if (distance[x][i] + graph[i][j] + distance[j][y] == distance[x][y]) {
                                count ++;
                            }
                        }
                    }
                    if (count >= limit) {
                        sum += graph[i][j];
                    }
                }
            }
        }
        return sum;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            BuildingRoutesHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                BuildingRoutesHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class BuildingRoutesHarness {
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
            String[] dist             = {"011",
 "101",
 "110"};
            int T                     = 1;
            int expected__            = 6;

            return verifyCase(casenum__, expected__, new BuildingRoutes().build(dist, T));
        }
        case 1: {
            String[] dist             = {"033",
 "309",
 "390"};
            int T                     = 1;
            int expected__            = 12;

            return verifyCase(casenum__, expected__, new BuildingRoutes().build(dist, T));
        }
        case 2: {
            String[] dist             = {"0123",
 "1023",
 "1203",
 "1230"};
            int T                     = 2;
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new BuildingRoutes().build(dist, T));
        }
        case 3: {
            String[] dist             = {"05789654",
 "10347583",
 "65085479",
 "55602398",
 "76590934",
 "57939045",
 "12345608",
 "68647640"};
            int T                     = 3;
            int expected__            = 40;

            return verifyCase(casenum__, expected__, new BuildingRoutes().build(dist, T));
        }

        // custom cases

/*      case 4: {
            String[] dist             = ;
            int T                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new BuildingRoutes().build(dist, T));
        }*/
/*      case 5: {
            String[] dist             = ;
            int T                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new BuildingRoutes().build(dist, T));
        }*/
/*      case 6: {
            String[] dist             = ;
            int T                     = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new BuildingRoutes().build(dist, T));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
