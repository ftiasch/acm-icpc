import java.math.*;
import java.util.*;

public class Incubator {
    int[] match;
    boolean[] visit;
    boolean[][] graph;

    boolean find(int u) {
        if (visit[u]) {
            return false;
        }
        visit[u] = true;
        for (int v = 0; v < graph.length; ++ v) {
            if (graph[u][v]) {
                if (match[v] == -1 || find(match[v])) {
                    match[v] = u;
                    return true;
                }
            }
        }
        return false;
    }

    public int maxMagicalGirls(String[] love) {
        int n = love.length;
        boolean[][] graph = new boolean[n][n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                graph[i][j] = love[i].charAt(j) == 'Y';
            }
        }
        for (int k = 0; k < n; ++ k) {
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    graph[i][j] |= graph[i][k] && graph[k][j];
                }
            }
        }
        boolean[] inCycle = new boolean[n];
        int result = 0;
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                inCycle[i] |= graph[i][j] && graph[j][i];
            }
            if (!inCycle[i]) {
                result ++;
            }
        }
        this.graph = new boolean[n][n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                this.graph[i][j] = !inCycle[i] && !inCycle[j] && graph[i][j];
            }
        }
        match = new int[n];
        visit = new boolean[n];
        Arrays.fill(match, -1);
        for (int i = 0; i < n; ++ i) {
            if (find(i)) {
                result --;
                Arrays.fill(visit, false);
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
            IncubatorHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                IncubatorHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class IncubatorHarness {
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
            String[] love             = {"NY","NN"};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new Incubator().maxMagicalGirls(love));
        }
        case 1: {
            String[] love             = {"NYN", "NNY", "NNN"};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new Incubator().maxMagicalGirls(love));
        }
        case 2: {
            String[] love             = {"NNYNN","NNYNN","NNNYY","NNNNN","NNNNN"};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new Incubator().maxMagicalGirls(love));
        }
        case 3: {
            String[] love             = {"NNNNN","NYNNN","NYNYN","YNYNN","NNNNN"};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new Incubator().maxMagicalGirls(love));
        }
        case 4: {
            String[] love             = {"NNNNN","NNNNN","NNNNN","NNNNN","NNNNN"};
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new Incubator().maxMagicalGirls(love));
        }
        case 5: {
            String[] love             = {"NNYNNNNN","NNNYNNNN","NNNNYNNN","NNYNNNNN","NNNNNYYN","NNNYNNNY","NNNNNNNN","NNNNNNNN"};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new Incubator().maxMagicalGirls(love));
        }
        case 6: {
            String[] love             = {"Y"};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new Incubator().maxMagicalGirls(love));
        }

        // custom cases

/*      case 7: {
            String[] love             = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new Incubator().maxMagicalGirls(love));
        }*/
/*      case 8: {
            String[] love             = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new Incubator().maxMagicalGirls(love));
        }*/
/*      case 9: {
            String[] love             = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new Incubator().maxMagicalGirls(love));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
