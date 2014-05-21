import java.math.*;
import java.util.*;

public class Rumor {
    public int getMinimum(String knowledge, String[] trust) {
        int n = knowledge.length();
        boolean[][] graph = new boolean[n][n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                graph[i][j] = trust[i].charAt(j) == 'Y';
            }
        }
        int answer = Integer.MAX_VALUE;
        for (int mask = 0; mask < 1 << n; ++ mask) {
            int[][] time = new int[2][n];
            for (int i = 0; i < n; ++ i) {
                time[0][i] = time[1][i] = knowledge.charAt(i) == 'Y' ? 0 : Integer.MAX_VALUE;
            }
            int now = 0;
            while (now <= 2 * n) {
                boolean valid = true;
                for (int i = 0; i < n; ++ i) {
                    valid &= time[0][i] != Integer.MAX_VALUE;
                    valid &= time[1][i] != Integer.MAX_VALUE;
                }
                if (valid) {
                    break;
                }
                for (int i = 0; i < n; ++ i) {
                    int type = mask >> i & 1;
                    if (time[type][i] == -1 || time[type][i] > now) {
                        type ^= 1;
                    }
                    if (time[type][i] == -1 || time[type][i] > now) {
                        continue;
                    }
                    for (int j = 0; j < n; ++ j) {
                        if (graph[i][j]) {
                            time[type][j] = Math.min(time[type][j], now + 1);
                        }
                    }
                    time[type][i] = -1;
                }
//for (int i = 0; i < n; ++ i) {
//    System.err.print(String.format("(%d, %d), ", time[0][i], time[1][i]));
//}
//System.err.println();
                now ++;
            }
            if (now <= 2 * n) {
                answer = Math.min(answer, now);
            }
        }
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            RumorHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                RumorHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class RumorHarness {
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
            String knowledge          = "YNN";
            String[] graph            = {"NYN"
,"NNY"
,"NNN"};
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new Rumor().getMinimum(knowledge, graph));
        }
        case 1: {
            String knowledge          = "YNNY";
            String[] graph            = {"NYYN"
,"YNNY"
,"YNNY"
,"NYYN"};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new Rumor().getMinimum(knowledge, graph));
        }
        case 2: {
            String knowledge          = "YYYY";
            String[] graph            = {"NYNN"
,"YNYN"
,"NYNY"
,"NNYN"};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new Rumor().getMinimum(knowledge, graph));
        }
        case 3: {
            String knowledge          = "YYYYYN";
            String[] graph            = {"NYYYYN"
,"YNYYYN"
,"YYNYYN"
,"YYYNYN"
,"YYYYNN"
,"NNNNNN"};
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new Rumor().getMinimum(knowledge, graph));
        }
        case 4: {
            String knowledge          = "NNNY";
            String[] graph            = {"NNNN"
,"YNNN"
,"YNNN"
,"NYYN"};
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new Rumor().getMinimum(knowledge, graph));
        }
        case 5: {
            String knowledge          =  "NNNNNNNYYY";
            String[] graph            = {"NYNNYNNYNN"
,"NNYNYNNNNY"
,"YYNNNYNNNN"
,"YNNNYNYNNN"
,"NNYNNYNNYN"
,"NNNNYNNNYY"
,"NYNYNYNNNN"
,"NNNNNNYNYY"
,"NNNYNNNYNY"
,"NYYNNNNYNN"}
;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new Rumor().getMinimum(knowledge, graph));
        }

        // custom cases

/*      case 6: {
            String knowledge          = ;
            String[] graph            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new Rumor().getMinimum(knowledge, graph));
        }*/
/*      case 7: {
            String knowledge          = ;
            String[] graph            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new Rumor().getMinimum(knowledge, graph));
        }*/
/*      case 8: {
            String knowledge          = ;
            String[] graph            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new Rumor().getMinimum(knowledge, graph));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
