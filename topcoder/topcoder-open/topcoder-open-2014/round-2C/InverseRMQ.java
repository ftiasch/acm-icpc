import java.math.*;
import java.util.*;

public class InverseRMQ {
    static String NO  = "Impossible";
    static String YES = "Possible";

    public String possible(int n, int[] l, int[] r, int[] rmq) {
        int m = l.length;
        for (int i = 0; i < m; ++ i) {
            for (int j = 0; j < m; ++ j) {
                if (rmq[i] == rmq[j]) {
                    l[i] = Math.max(l[i], l[j]);
                    r[i] = Math.min(r[i], r[j]);
                    if (l[i] > r[i]) {
                        return NO;
                    }
                }
            }
        }
        List <Integer> ends = new ArrayList <Integer>();
        ends.add(0);
        ends.add(n);
        for (int i = 0; i < m; ++ i) {
            l[i] --;
            r[i] --;
            ends.add(l[i]);
            ends.add(r[i] + 1);
        }
        ends = unique(ends);
        List <Integer> values = new ArrayList <Integer>();
        values.add(n);
        for (int i = 0; i < m; ++ i) {
            values.add(rmq[i]);
            if (rmq[i] > 1) {
                values.add(rmq[i] - 1);
            }
        }
        values = unique(values);
        List <Integer> maximums = new ArrayList <Integer>();
        for (int i = 0; i + 1 < (int)ends.size(); ++ i) {
            int max = Integer.MAX_VALUE;
            for (int j = 0; j < m; ++ j) {
                if (l[j] <= ends.get(i) && ends.get(i + 1) <= r[j] + 1) {
                    max = Math.min(max, rmq[j]);
                }
            }
            maximums.add(max);
        }
        int s = maximums.size() + values.size();
        int t = s + 1;
        flow = new int[t + 1][t + 1];
        for (int i = 0; i < maximums.size(); ++ i) {
            flow[s][i] = ends.get(i + 1) - ends.get(i);
        }
        for (int i = 0; i < values.size(); ++ i) {
            int count = values.get(i);
            if (i > 0) {
                count -= values.get(i - 1);
            }
            flow[maximums.size() + i][t] = count;
        }
        for (int j = 0; j < values.size(); ++ j) {
            int k = 0;
            while (k < m && rmq[k] != values.get(j)) {
                k ++;
            }
            if (k < m) {
                for (int i = 0; i < maximums.size(); ++ i) {
                    if (l[k] <= ends.get(i) && ends.get(i + 1) <= r[k] + 1 && maximums.get(i) >= values.get(j)) {
                        flow[i][maximums.size() + j] = INF;
                    }
                }
            } else {
                for (int i = 0; i < maximums.size(); ++ i) {
                    if (maximums.get(i) >= values.get(j)) {
                        flow[i][maximums.size() + j] = INF;
                    }
                }
            }
        }
        int result = 0;
        while (bfs(s, t)) {
            result += dfs(s, t, INF);
        }
        return result == n ? YES : NO;
    }

    List <Integer> unique(List <Integer> list) {
        Collections.sort(list);
        List <Integer> newList = new ArrayList <Integer>();
        for (int i = 0; i < list.size(); ++ i) {
            if (i == 0 || list.get(i - 1) < list.get(i)) {
                newList.add(list.get(i));
            }
        }
        return newList;
    }

    final static int INF = 1000000000;

    int[] level;
    int[][] flow;

    boolean bfs(int s, int t) {
        level = new int[t + 1];
        Arrays.fill(level, -1);
        level[s] = 0;
        Queue <Integer> queue = new LinkedList <Integer>();
        queue.offer(s);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v = 0; v <= t; ++ v) {
                if (flow[u][v] > 0 && level[v] == -1) {
                    level[v] = level[u] + 1;
                    queue.offer(v);
                }
            }
        }
        return level[t] != -1;
    }

    int dfs(int u, int t, int d) {
        if (u == t) {
            return d;
        }
        int result = 0;
        for (int v = 0; v <= t; ++ v) {
            if (flow[u][v] > 0 && level[u] + 1 == level[v]) {
                int ret = dfs(v, t, Math.min(d - result, flow[u][v]));
                flow[u][v] -= ret;
                flow[v][u] += ret;
                result += ret;
                if (result == d) {
                    break;
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
            InverseRMQHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                InverseRMQHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class InverseRMQHarness {
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

    static boolean compareOutput(String expected, String result) { return expected.equals(result); }
    static String formatResult(String res) {
        return String.format("\"%s\"", res);
    }

    static int verifyCase(int casenum, String expected, String received) {
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
            int n                     = 5;
            int[] A                   = {1,2,4};
            int[] B                   = {2,4,5};
            int[] ans                 = {3,4,5};
            String expected__         = "Possible";

            return verifyCase(casenum__, expected__, new InverseRMQ().possible(n, A, B, ans));
        }
        case 1: {
            int n                     = 3;
            int[] A                   = {1,2,3};
            int[] B                   = {1,2,3};
            int[] ans                 = {3,3,3};
            String expected__         = "Impossible";

            return verifyCase(casenum__, expected__, new InverseRMQ().possible(n, A, B, ans));
        }
        case 2: {
            int n                     = 600;
            int[] A                   = {1,101,201,301,401,501};
            int[] B                   = {100,200,300,400,500,600};
            int[] ans                 = {100,200,300,400,500,600};
            String expected__         = "Possible";

            return verifyCase(casenum__, expected__, new InverseRMQ().possible(n, A, B, ans));
        }
        case 3: {
            int n                     = 1000000000;
            int[] A                   = {1234,1234};
            int[] B                   = {5678,5678};
            int[] ans                 = {10000,20000};
            String expected__         = "Impossible";

            return verifyCase(casenum__, expected__, new InverseRMQ().possible(n, A, B, ans));
        }
        case 4: {
            int n                     = 8;
            int[] A                   = {1,2,3,4,5,6,7,8};
            int[] B                   = {1,2,3,4,5,6,7,8};
            int[] ans                 = {4,8,2,5,6,3,7,1};
            String expected__         = "Possible";

            return verifyCase(casenum__, expected__, new InverseRMQ().possible(n, A, B, ans));
        }
        case 5: {
            int n                     = 1000000000;
            int[] A                   = {1};
            int[] B                   = {1000000000};
            int[] ans                 = {19911120};
            String expected__         = "Impossible";

            return verifyCase(casenum__, expected__, new InverseRMQ().possible(n, A, B, ans));
        }

        // custom cases

/*      case 6: {
            int n                     = ;
            int[] A                   = ;
            int[] B                   = ;
            int[] ans                 = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new InverseRMQ().possible(n, A, B, ans));
        }*/
/*      case 7: {
            int n                     = ;
            int[] A                   = ;
            int[] B                   = ;
            int[] ans                 = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new InverseRMQ().possible(n, A, B, ans));
        }*/
/*      case 8: {
            int n                     = ;
            int[] A                   = ;
            int[] B                   = ;
            int[] ans                 = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new InverseRMQ().possible(n, A, B, ans));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
