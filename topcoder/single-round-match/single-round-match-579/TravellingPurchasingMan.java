import java.math.*;
import java.util.*;

public class TravellingPurchasingMan {
    final static int INF = 1000000000;

    class Store {
        int open, close, duration;

        Store(String string) {
            String[] tokens = string.split(" ");
            this.open = Integer.parseInt(tokens[0]);
            this.close = Integer.parseInt(tokens[1]);
            this.duration = Integer.parseInt(tokens[2]);
        }
    };

    public int maxStores(int n, String[] interestingStores, String[] roads) {
        int m = interestingStores.length;
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                graph[i][j] = i == j ? 0 : INF;
            }
        }
        for (String road : roads) {
            String[] tokens = road.split(" ");
            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[1]);
            int c = Integer.parseInt(tokens[2]);
            graph[a][b] = graph[b][a] = c;
        }
        for (int k = 0; k < n; ++ k) {
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
        Store[] stores = new Store[m];
        for (int i = 0; i < m; ++ i) {
            stores[i] = new Store(interestingStores[i]);
        }
        int[][] minTime = new int[1 << m][m];
        for (int[] row : minTime) {
            Arrays.fill(row, INF);
        }
        for (int i = 0; i < m; ++ i) {
            if (graph[n - 1][i] <= stores[i].close) {
                minTime[1 << i][i] = Math.max(graph[n - 1][i], stores[i].open) + stores[i].duration;
            }
        }
        for (int mask = 0; mask < 1 << m; ++ mask) {
            for (int u = 0; u < m; ++ u) {
                if (minTime[mask][u] == INF) {
                    continue;
                }
                for (int v = 0; v < m; ++ v) {
                    if ((mask >> v & 1) == 0 && minTime[mask][u] + graph[u][v] <= stores[v].close) {
                        minTime[mask | 1 << v][v] = Math.min(minTime[mask | 1 << v][v], Math.max(minTime[mask][u] + graph[u][v], stores[v].open) + stores[v].duration);
                    }
                }
            }
        }
        int answer = 0;
        for (int mask = 0; mask < 1 << m; ++ mask) {
            for (int u = 0; u < m; ++ u) {
                if (minTime[mask][u] < INF) {
                    answer = Math.max(answer, Integer.bitCount(mask));
                }
            }
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            TravellingPurchasingManHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                TravellingPurchasingManHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class TravellingPurchasingManHarness {
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
            int N                     = 3;
            String[] interestingStores = {"1 10 10" , "1 55 31", "10 50 100" };
            String[] roads            = {"1 2 10"};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new TravellingPurchasingMan().maxStores(N, interestingStores, roads));
        }
        case 1: {
            int N                     = 3;
            String[] interestingStores = {"1 10 10" , "1 55 30", "10 50 100" };
            String[] roads            = {"1 2 10"};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new TravellingPurchasingMan().maxStores(N, interestingStores, roads));
        }
        case 2: {
            int N                     = 5;
            String[] interestingStores = {"0 1000 17"};
            String[] roads            = {"2 3 400", "4 1 500", "4 3 300", "1 0 700", "0 2 400"};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new TravellingPurchasingMan().maxStores(N, interestingStores, roads));
        }

        // custom cases

/*      case 3: {
            int N                     = ;
            String[] interestingStores = ;
            String[] roads            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TravellingPurchasingMan().maxStores(N, interestingStores, roads));
        }*/
/*      case 4: {
            int N                     = ;
            String[] interestingStores = ;
            String[] roads            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TravellingPurchasingMan().maxStores(N, interestingStores, roads));
        }*/
/*      case 5: {
            int N                     = ;
            String[] interestingStores = ;
            String[] roads            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new TravellingPurchasingMan().maxStores(N, interestingStores, roads));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
