import java.math.*;
import java.util.*;

public class SwitchingGame {
    public int timeToWin(String[] s) {
        int n = s.length;
        int m = s[0].length();
        char[][] games = new char[n][];
        for (int i = 0; i < n; ++ i) {
            games[i] = s[i].toCharArray();
        }
        char[] state = new char[m];
        Arrays.fill(state, '-');
        int result = 0;
        for (int i = 0; i < n; ++ i) {
            char[] game = games[i];
            boolean[] need = new boolean[2];
            for (int j = 0; j < m; ++ j) {
                if (state[j] != game[j] && game[j] != '?') {
                    need["-+".indexOf(game[j])] = true;
                }
            }
            for (int j = 0; j < m; ++ j) {
                int p = i;
                while (p < n && games[p][j] == '?') {
                    p ++;
                }
                if (p < n) {
                    int t = "-+".indexOf(games[p][j]);
                    if (need[t]) {
                        state[j] = games[p][j];
                    }
                }
            }
            for (int j = 0; j < 2; ++ j) {
                if (need[j]) {
                    result ++;
                }
            }
            result ++;
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            SwitchingGameHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                SwitchingGameHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class SwitchingGameHarness {
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
            String[] states           = {"++--",
 "--++"};
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new SwitchingGame().timeToWin(states));
        }
        case 1: {
            String[] states           = {"+-++",
 "+-++"};
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new SwitchingGame().timeToWin(states));
        }
        case 2: {
            String[] states           = {"++",
 "+?",
 "?+",
 "++"};
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new SwitchingGame().timeToWin(states));
        }
        case 3: {
            String[] states           = {"+",
 "?",
 "?",
 "?",
 "-"};
            int expected__            = 7;

            return verifyCase(casenum__, expected__, new SwitchingGame().timeToWin(states));
        }
        case 4: {
            String[] states           = {"+??+++",
 "++??+-",
 "?++??+",
 "?-+-??",
 "??+?++",
 "++-?+?",
 "?++?-+",
 "?--+++",
 "-??-?+"}
;
            int expected__            = 20;

            return verifyCase(casenum__, expected__, new SwitchingGame().timeToWin(states));
        }

        // custom cases

/*      case 5: {
            String[] states           = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new SwitchingGame().timeToWin(states));
        }*/
/*      case 6: {
            String[] states           = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new SwitchingGame().timeToWin(states));
        }*/
/*      case 7: {
            String[] states           = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new SwitchingGame().timeToWin(states));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
