import java.math.*;
import java.util.*;

public class GearsDiv1 {
    int n;
    boolean[][] graph;
    int[] match;
    boolean[] visit;

    boolean find(int u) {
        if (visit[u]) {
            return false;
        }
        visit[u] = true;
        for (int v = 0; v < n; ++ v) {
            if (graph[u][v] && (match[v] == -1 || find(match[v]))) {
                match[u] = v;
                match[v] = u;
                return true;
            }
        }
        return false;
    }

    int solve() {
        int ret = n;
        match = new int[n];
        Arrays.fill(match, -1);
        for (int i = 0; i < n; ++ i) {
            visit = new boolean[n];
            if (match[i] == -1 && find(i)) {
                ret --;
            }
        }
        return ret;
    }

    public int getmin(String color, String[] graph) {
        n = color.length();
        int[] kind = new int[n];
        for (int i = 0; i < n; ++ i) {
            if (color.charAt(i) == 'R') {
                kind[i] = 0;
            } else if (color.charAt(i) == 'G') {
                kind[i] = 1;
            } else {
                kind[i] = 2;
            }
        }
        int answer = 0;
        for (int k = 0; k < 3; ++ k) {
            this.graph = new boolean[n][n];
            for (int i = 0; i < n; ++ i) {
                for (int j = 0; j < n; ++ j) {
                    if (graph[i].charAt(j) == 'Y' && kind[i] != k && kind[j] != k) {
                        this.graph[i][j] = true;
                    }
                }
            }
            answer = Math.max(answer, solve());
        }
        return n - answer;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            GearsDiv1Harness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                GearsDiv1Harness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class GearsDiv1Harness {
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
            String color              = "RGB";
            String[] graph            = {"NYY","YNY","YYN"};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new GearsDiv1().getmin(color, graph));
        }
        case 1: {
            String color              = "RGBR";
            String[] graph            = {"NNNN","NNNN","NNNN","NNNN"};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new GearsDiv1().getmin(color, graph));
        }
        case 2: {
            String color              = "RGBR";
            String[] graph            = {"NYNN","YNYN","NYNY","NNYN"};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new GearsDiv1().getmin(color, graph));
        }
        case 3: {
            String color              = "RRRRRGRRBGRRGBBGGGBRRRGBRGRRGG";
            String[] graph            = {"NNNNNYNNNYNNYNNNYNNNNNNNNYNNYY",
 "NNNNNNNNYNNNYNYNNYNNNNYNNYNNYY",
 "NNNNNYNNNNNNNNNNNNYNNNNNNYNNNY",
 "NNNNNNNNNYNNYNNYYYNNNNYNNYNNNN",
 "NNNNNNNNNYNNYNNYYYNNNNYNNNNNNN",
 "YNYNNNYYYNNYNYYNNNNNYYNYNNYYNN",
 "NNNNNYNNNNNNNNNYYYNNNNYNNYNNYY",
 "NNNNNYNNNNNNNNNYNNNNNNNNNNNNYN",
 "NYNNNYNNNYNNYNNYYYNNNNYNNYNNYY",
 "YNNYYNNNYNNNNYYNNNYNYYNYNNNNNN",
 "NNNNNNNNNNNNYNNYNYNNNNYNNNNNNY",
 "NNNNNYNNNNNNYNNYYYNNNNNNNNNNYN",
 "YYNYYNNNYNYYNYYNNNYNYNNYNNNNNN",
 "NNNNNYNNNYNNYNNYYYNNNNYNNYNYYY",
 "NYNNNYNNNYNNYNNYYYNNNNYNNYNNYY",
 "NNNYYNYYYNYYNYYNNNYNYNNYYNYYNN",
 "YNNYYNYNYNNYNYYNNNYNNNNYYNNYNN",
 "NYNYYNYNYNYYNYYNNNNYYNNYYNYNNN",
 "NNYNNNNNNYNNYNNYYNNNNNYNNYNNNY",
 "NNNNNNNNNNNNNNNNNYNNNNYNNYNNNY",
 "NNNNNYNNNYNNYNNYNYNNNNYNNNNNYY",
 "NNNNNYNNNYNNNNNNNNNNNNYNNNNNNN",
 "NYNYYNYNYNYNNYYNNNYYYYNYYNYNNN",
 "NNNNNYNNNYNNYNNYYYNNNNYNNNNNNY",
 "NNNNNNNNNNNNNNNYYYNNNNYNNYNNYY",
 "YYYYNNYNYNNNNYYNNNYYNNNNYNYYNN",
 "NNNNNYNNNNNNNNNYNYNNNNYNNYNNYN",
 "NNNNNYNNNNNNNYNYYNNNNNNNNYNNYY",
 "YYNNNNYYYNNYNYYNNNNNYNNNYNYYNN",
 "YYYNNNYNYNYNNYYNNNYYYNNYYNNYNN"};
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new GearsDiv1().getmin(color, graph));
        }

        // custom cases

/*      case 4: {
            String color              = ;
            String[] graph            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new GearsDiv1().getmin(color, graph));
        }*/
/*      case 5: {
            String color              = ;
            String[] graph            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new GearsDiv1().getmin(color, graph));
        }*/
/*      case 6: {
            String color              = ;
            String[] graph            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new GearsDiv1().getmin(color, graph));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
