import java.math.*;
import java.util.*;

public class EllysJuice {
    public String[] getWinners(String[] players) {
        int n = players.length;
        Arrays.sort(players);
        if (n == 1) {
            return players;
        }
        ArrayList <String> answer = new ArrayList <String>();
        for (int i = 0; i < n; ) {
            int j = i;
            while (j < n && players[i].equals(players[j])) {
                j ++;
            }
            if (j - i >= 2) {
                answer.add(players[i]);
            }
            i = j;
        }
        return (String[])answer.toArray(new String[answer.size()]);
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            EllysJuiceHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                EllysJuiceHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class EllysJuiceHarness {
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

    static boolean compareOutput(String[] expected, String[] result) { if (expected.length != result.length) return false; for (int i=0; i<expected.length; ++i) if (!expected[i].equals(result[i])) return false; return true; }

    static String formatResult(String[] res) {
        String ret = "";
        ret += "{";
        for (int i=0; i<res.length; ++i) {
            if (i > 0) ret += ",";
            ret += String.format(" \"%s\"", res[i]);
        }
        ret += " }";
        return ret;
    }

    static int verifyCase(int casenum, String[] expected, String[] received) {
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
            String[] players          = { "elly", "kriss", "stancho", "elly", "stancho" };
            String[] expected__       = {"elly", "stancho" };

            return verifyCase(casenum__, expected__, new EllysJuice().getWinners(players));
        }
        case 1: {
            String[] players          = {"torb", "elly", "stancho", "kriss"};
            String[] expected__       = { };

            return verifyCase(casenum__, expected__, new EllysJuice().getWinners(players));
        }
        case 2: {
            String[] players          = {"elly", "elly", "elly", "elly", "elly"};
            String[] expected__       = {"elly" };

            return verifyCase(casenum__, expected__, new EllysJuice().getWinners(players));
        }
        case 3: {
            String[] players          = { "ariadne", "elly", "ariadne", "stancho", "stancho", "kriss", "stancho", "elly" };
            String[] expected__       = {"ariadne", "elly", "stancho" };

            return verifyCase(casenum__, expected__, new EllysJuice().getWinners(players));
        }

        // custom cases

/*      case 4: {
            String[] players          = ;
            String[] expected__       = ;

            return verifyCase(casenum__, expected__, new EllysJuice().getWinners(players));
        }*/
/*      case 5: {
            String[] players          = ;
            String[] expected__       = ;

            return verifyCase(casenum__, expected__, new EllysJuice().getWinners(players));
        }*/
/*      case 6: {
            String[] players          = ;
            String[] expected__       = ;

            return verifyCase(casenum__, expected__, new EllysJuice().getWinners(players));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
