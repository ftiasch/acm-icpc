import java.math.*;
import java.util.*;

public class WolvesAndSheep {
    public int getmin(String[] field) {
        int n = field.length;
        int m = field[0].length();
        int result = Integer.MAX_VALUE;
        for (int mask = 0; mask < 1 << n - 1; ++ mask) {
            int[] group = new int[n];
            for (int i = 1; i < n; ++ i) {
                group[i] = group[i - 1] + (mask >> i - 1 & 1);
            }
            int count = Integer.bitCount(mask);
            int[] status = new int[2];
            for (int j = 0; j < m && count < Integer.MAX_VALUE; ++ j) {
                int[] newStatus = new int[2];
                for (int i = 0; i < n; ++ i) {
                    char c = field[i].charAt(j);
                    if (c != '.') {
                        newStatus[c == 'S' ? 0 : 1] |= 1 << group[i];
                    }
                }
                if ((newStatus[0] & newStatus[1]) > 0) {
                    count = Integer.MAX_VALUE;
                } else {
                    for (int i = 0; i < 2; ++ i) {
                        status[i] |= newStatus[i];
                    }
                    if ((status[0] & status[1]) > 0) {
                        count ++;
                        status = newStatus;
                    }
                }
            }
            result = Math.min(result, count);
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            WolvesAndSheepHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                WolvesAndSheepHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class WolvesAndSheepHarness {
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
            String[] field            = {"W.WSS",
 "WW.S.",
 ".SSS.",
 "SSS.S",
 ".SS.S"};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new WolvesAndSheep().getmin(field));
        }
        case 1: {
            String[] field            = {".....",
 ".....",
 "....."};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new WolvesAndSheep().getmin(field));
        }
        case 2: {
            String[] field            = {".SS",
 "...",
 "S..",
 "W.W"};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new WolvesAndSheep().getmin(field));
        }
        case 3: {
            String[] field            = {"WWWSWWSSWWW",
 "WWSWW.SSWWW",
 "WS.WSWWWWS."};
            int expected__            = 10;

            return verifyCase(casenum__, expected__, new WolvesAndSheep().getmin(field));
        }
        case 4: {
            String[] field            = {".W.S.W.W",
 "W.W.S.W.",
 ".S.S.W.W",
 "S.S.S.W.",
 ".S.W.W.S",
 "S.S.W.W.",
 ".W.W.W.S",
 "W.W.S.S."};
            int expected__            = 7;

            return verifyCase(casenum__, expected__, new WolvesAndSheep().getmin(field));
        }
        case 5: {
            String[] field            = {"W.SSWWSSSW.SS",
 ".SSSSW.SSWWWW",
 ".WWWWS.WSSWWS",
 "SS.WSS..W.WWS",
 "WSSS.SSWS.W.S",
 "WSS.WS...WWWS",
 "S.WW.S.SWWWSW",
 "WSSSS.SSW...S",
 "S.WWSW.WWSWSW",
 ".WSSS.WWSWWWS",
 "..SSSS.WWWSSS",
 "SSWSWWS.W.SSW",
 "S.WSWS..WSSS.",
 "WS....W..WSS."};
            int expected__            = 24;

            return verifyCase(casenum__, expected__, new WolvesAndSheep().getmin(field));
        }
        case 6: {
            String[] field            = {"WW..SS",
 "WW..SS",
 "......",
 "......",
 "SS..WW",
 "SS..WW"};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new WolvesAndSheep().getmin(field));
        }

        // custom cases

/*      case 7: {
            String[] field            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new WolvesAndSheep().getmin(field));
        }*/
/*      case 8: {
            String[] field            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new WolvesAndSheep().getmin(field));
        }*/
/*      case 9: {
            String[] field            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new WolvesAndSheep().getmin(field));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
