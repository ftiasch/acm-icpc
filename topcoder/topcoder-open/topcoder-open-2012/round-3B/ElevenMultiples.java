import java.math.*;
import java.util.*;

public class ElevenMultiples {
    final static int MOD = (int)1e9 + 7;

    int factorial(int n) {
        long ret = 1;
        for (int i = 1; i <= n; ++ i) {
            ret *= i;
            ret %= MOD;
        }
        return (int)ret;
    }

    public int countMultiples(String[] pieces) {
        int n = pieces.length;
        ArrayList <Integer> odds = new ArrayList <Integer>();
        ArrayList <Integer> evens = new ArrayList <Integer>();
        for (String piece : pieces) {
            int remainder = 0;
            for (int i = 0; i < piece.length(); ++ i) {
                remainder *= 10;
                remainder += piece.charAt(i) - '0';
                remainder %= 11;
            }
            if (piece.length() % 2 == 1) {
                odds.add(remainder);
            } else {
                evens.add(remainder);
            }
        }
        int[][] ways = new int[n + 2][11];
        ways[0][0] = 1;
        for (int odd : odds) {
            int[][] newWays = new int[n + 2][11];
            for (int count = 0; count <= n + 1; ++ count) {
                for (int remainder = 0; remainder < 11; ++ remainder) {
                    if (ways[count][remainder] == 0) {
                        continue;
                    }
                    newWays[count + 1][(remainder + odd) % 11] += ways[count][remainder];
                    newWays[count + 1][(remainder + odd) % 11] %= MOD;
                    newWays[count][(remainder + odd * 10) % 11] += ways[count][remainder];
                    newWays[count][(remainder + odd * 10) % 11] %= MOD;
                }
            }
            ways = newWays;
        }
        for (int count = 0; count <= n + 1; ++ count) {
            for (int remainder = 0; remainder < 11; ++ remainder) {
                if (count != (odds.size() + 1) / 2) {
                    ways[count][remainder] = 0;
                } else {
                    ways[count][remainder] = (int)((long)ways[count][remainder] * factorial(odds.size() / 2) % MOD * factorial((odds.size() + 1) / 2) % MOD);
                }
            }
        }
        int total = odds.size() + 1;
        for (int even : evens) {
            int[][] newWays = new int[n + 2][11];
            for (int count = 0; count <= n + 1; ++ count) {
                for (int remainder = 0; remainder < 11; ++ remainder) {
                    if (ways[count][remainder] == 0) {
                        continue;
                    }
                    newWays[count + 1][(remainder + even) % 11] += (int)((long)ways[count][remainder] * count % MOD);
                    newWays[count + 1][(remainder + even) % 11] %= MOD;
                    newWays[count][(remainder + even * 10) % 11] += (int)((long)ways[count][remainder] * (total - count) % MOD);
                    newWays[count][(remainder + even * 10) % 11] %= MOD;
                }
            }
            total ++;
            ways = newWays;
        }
        int answer = 0;
        for (int i = 0; i <= n + 1; ++ i) {
            answer += ways[i][0];
            answer %= MOD;
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            ElevenMultiplesHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                ElevenMultiplesHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class ElevenMultiplesHarness {
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
            String[] pieces           = {"58", "2012", "123"};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new ElevenMultiples().countMultiples(pieces));
        }
        case 1: {
            String[] pieces           = {"1", "1111", "1", "11"};
            int expected__            = 24;

            return verifyCase(casenum__, expected__, new ElevenMultiples().countMultiples(pieces));
        }
        case 2: {
            String[] pieces           = {"43925486943738659795389387498953274"};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new ElevenMultiples().countMultiples(pieces));
        }
        case 3: {
            String[] pieces           = {"983", "4654", "98", "3269", "861", "30981"};
            int expected__            = 96;

            return verifyCase(casenum__, expected__, new ElevenMultiples().countMultiples(pieces));
        }
        case 4: {
            String[] pieces           = {"193", "8819", "40676", "97625892", "5719", "45515667", "32598836", "70559374", "38756", "724",
"93391", "942068", "506", "901150", "874", "895567", "7560480", "7427691", "799450", "85127"};
            int expected__            = 537147821;

            return verifyCase(casenum__, expected__, new ElevenMultiples().countMultiples(pieces));
        }
        case 5: {
            String[] pieces           = {"687045939630", "997856158148599044", "2014910234712225061", "9658113323175370226", "1584118137",
"67925153345598920", "6960366756", "863413844386808834", "799302243562410012", "44481835751",
"8004606814733183", "19623906615609", "23859998326058162", "461385591582", "9261878982390119",
"1569373294276", "318106951168934", "65389049931", "12791173342", "507877942026",
"3947173045690", "472425746178910", "524552931853595", "40771812249667850232", "563988469071932",
"28147819070", "797007158858587", "5716177008624223", "387565700495309324", "4716621063133318"}
;
            int expected__            = 814880650;

            return verifyCase(casenum__, expected__, new ElevenMultiples().countMultiples(pieces));
        }

        // custom cases

/*      case 6: {
            String[] pieces           = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ElevenMultiples().countMultiples(pieces));
        }*/
/*      case 7: {
            String[] pieces           = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ElevenMultiples().countMultiples(pieces));
        }*/
/*      case 8: {
            String[] pieces           = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new ElevenMultiples().countMultiples(pieces));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
