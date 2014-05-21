import java.math.*;
import java.util.*;

public class StringEquations {
    final static int N = 26;

    String[] solve(String[][] graph) {
        int[] degree = new int[N];
        for (int i = 0; i < N; ++ i) {
            for (int j = 0; j < N; ++ j) {
                if (graph[i][j] != null) {
                    degree[j] ++;
                }
            }
        }
        int[] queue = new int[N];
        int tail = 0;
        for (int i = 0; i < N; ++ i) {
            if (degree[i] == 0) {
                queue[tail ++] = i;
            }
        }
        for (int i = 0; i < tail; ++ i) {
            int u = queue[i];
            for (int v = 0; v < N; ++ v) {
                if (graph[u][v] != null) {
                    degree[v] --;
                    if (degree[v] == 0) {
                        queue[tail ++] = v;
                    }
                }
            }
        }
        if (tail != N) {
            return null;
        }
        String[] ends = new String[N];
        Arrays.fill(ends, "");
        for (int i = N - 1; i >= 0; -- i) {
            int u = queue[i];
            ArrayList <String> suffixes = new ArrayList <String>();
            for (int v = 0; v < N; ++ v) {
                if (graph[u][v] != null) {
                    suffixes.add(ends[v] + graph[u][v]);
                }
            }
            Collections.sort(suffixes, new Comparator <String>() {
                public int compare(String a, String b) {
                    return b.length() - a.length();
                }
            });
            for (int j = 1; j < suffixes.size(); ++ j) {
                if (!suffixes.get(0).endsWith(suffixes.get(j))) {
                    return null;
                }
            }
            if (!suffixes.isEmpty()) {
                ends[u] = suffixes.get(0);
            }
        }
        return ends;
    }

    public int getMinimum(String[] equations) {
        String[][] graph = new String[N][N];
        for (String equation : equations) {
            // A = B + s
            // 012345678
            int a = equation.charAt(0) - 'A';
            int b = equation.charAt(4) - 'A';
            String s = equation.substring(8);
            if (graph[a][b] != null && !graph[a][b].equals(s)) {
                return -1;
            }
            graph[a][b] = s;
        }
        int answer = 0;
        for (int _ = 0; _ <= N; ++ _) {
            String[] ends = solve(graph);
            if (ends == null) {
                return -1;
            }
            int delta = 0;
            for (int i = 0; i < N; ++ i) {
                delta += ends[i].length();
            }
            answer += delta;
            if (delta == 0) {
                return answer;
            }
            String[][] newGraph = new String[N][N];
            for (int u = 0; u < N; ++ u) {
                for (int v = 0; v < N; ++ v) {
                    if (graph[u][v] != null) {
                        newGraph[v][u] = ends[u].substring(0, ends[u].length() - ends[v].length() - graph[u][v].length());
                    }
                }
            }
            graph = newGraph;
        }
        return -1;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            StringEquationsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                StringEquationsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class StringEquationsHarness {
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
            String[] equations        = { "B = A + top", "C = B + coder", "C = A + topcoder" };
            int expected__            = 11;

            return verifyCase(casenum__, expected__, new StringEquations().getMinimum(equations));
        }
        case 1: {
            String[] equations        = { "B = A + coder", "C = B + top", "C = A + topcoder" };
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new StringEquations().getMinimum(equations));
        }
        case 2: {
            String[] equations        = { "A = B + p", "C = A + q", "D = F + r", "E = B + x", "G = A + y", "H = F + z" };
            int expected__            = 8;

            return verifyCase(casenum__, expected__, new StringEquations().getMinimum(equations));
        }
        case 3: {
            String[] equations        = { "X = X + a" };
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new StringEquations().getMinimum(equations));
        }
        case 4: {
            String[] equations        = { "Y = X + a", "Y = X + b" };
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new StringEquations().getMinimum(equations));
        }

        // custom cases

      case 5: {
            String[] equations        = {"C = A + a", "B = A + a", "B = C + a", "F = D + a", "E = D + a", "E = F + a", "I = G + a", "H = G + a", "H = I + a", "L = J + a", "K = J + a", "K = L + a", "O = M + a", "N = M + a", "N = O + a", "R = P + a", "Q = P + a", "Q = R + a", "U = S + a", "T = S + a", "T = U + a", "X = V + a", "W = V + a", "W = X + a"};
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new StringEquations().getMinimum(equations));
        }
/*      case 6: {
            String[] equations        = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new StringEquations().getMinimum(equations));
        }*/
/*      case 7: {
            String[] equations        = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new StringEquations().getMinimum(equations));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
