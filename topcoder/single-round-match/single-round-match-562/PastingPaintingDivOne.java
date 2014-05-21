import java.math.*;
import java.util.*;

public class PastingPaintingDivOne {
    final static String COLORS = "RGB";

    public long[] countColors(String[] clipboard, int t) {
        int n = clipboard.length;
        int m = clipboard[0].length();
        long[] answer = new long[3];
        // x - y
        for (int d = -(m - 1); d <= n - 1; ++ d) {
            ArrayList <Integer> pattern = new ArrayList <Integer>();
            for (int i = 0; i < n; ++ i) {
                int j = i - d;
                if (0 <= j && j < m) {
                    if (clipboard[i].charAt(j) == '.') {
                        pattern.add(-1);
                    } else {
                        pattern.add(COLORS.indexOf(clipboard[i].charAt(j)));
                    }
                }
            }
            int s = pattern.size();
            int last = 0;
            for (int i = 0; i < s; ++ i) {
                if (pattern.get(i) != -1) {
                    int next = i + t;
                    answer[pattern.get(i)] += Math.max(next - Math.max(i, last), 0);
                    last = next;
                }
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
            PastingPaintingDivOneHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                PastingPaintingDivOneHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class PastingPaintingDivOneHarness {
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

    static boolean compareOutput(long[] expected, long[] result) { if (expected.length != result.length) return false; for (int i=0; i<expected.length; ++i) if (expected[i] != result[i]) return false; return true; }

    static String formatResult(long[] res) {
        String ret = "";
        ret += "{";
        for (int i=0; i<res.length; ++i) {
            if (i > 0) ret += ",";
            ret += String.format(" %d", res[i]);
        }
        ret += " }";
        return ret;
    }

    static int verifyCase(int casenum, long[] expected, long[] received) {
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
            String[] clipboard        = {
"..G",
"R..",
"BG."
};
            int T                     = 3;
            long[] expected__         = {3, 4, 3};

            return verifyCase(casenum__, expected__, new PastingPaintingDivOne().countColors(clipboard, T));
        }
        case 1: {
            String[] clipboard        = {
"R...",
"....",
"....",
"...R"
};
            int T                     = 2;
            long[] expected__         = {4, 0, 0};

            return verifyCase(casenum__, expected__, new PastingPaintingDivOne().countColors(clipboard, T));
        }
        case 2: {
            String[] clipboard        = {"RGB"};
            int T                     = 100000;
            long[] expected__         = {100000, 100000, 100000};

            return verifyCase(casenum__, expected__, new PastingPaintingDivOne().countColors(clipboard, T));
        }
        case 3: {
            String[] clipboard        = {"."};
            int T                     = 1000000000;
            long[] expected__         = {0, 0, 0};

            return verifyCase(casenum__, expected__, new PastingPaintingDivOne().countColors(clipboard, T));
        }
        case 4: {
            String[] clipboard        = {
"RB.",
".G."
}
;
            int T                     = 100;
            long[] expected__         = {100, 1, 100};

            return verifyCase(casenum__, expected__, new PastingPaintingDivOne().countColors(clipboard, T));
        }
        case 5: {
            String[] clipboard        = {
"..........G..........",
".........G.G.........",
"........G...G........",
".......G.....G.......",
"......G..B.B..G......",
".....G...B.B...G.....",
"....G...........G....",
"...G...R.....R...G...",
"..G.....RRRRRR....G..",
".G..........RR.....G.",
"GGGGGGGGGGGGGGGGGGGGG"
};
            int T                     = 1000000000;
            long[] expected__         = {2000000018, 17000000048L, 2000000005};

            return verifyCase(casenum__, expected__, new PastingPaintingDivOne().countColors(clipboard, T));
        }

        // custom cases

/*      case 6: {
            String[] clipboard        = ;
            int T                     = ;
            long[] expected__         = {};

            return verifyCase(casenum__, expected__, new PastingPaintingDivOne().countColors(clipboard, T));
        }*/
/*      case 7: {
            String[] clipboard        = ;
            int T                     = ;
            long[] expected__         = {};

            return verifyCase(casenum__, expected__, new PastingPaintingDivOne().countColors(clipboard, T));
        }*/
/*      case 8: {
            String[] clipboard        = ;
            int T                     = ;
            long[] expected__         = {};

            return verifyCase(casenum__, expected__, new PastingPaintingDivOne().countColors(clipboard, T));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
