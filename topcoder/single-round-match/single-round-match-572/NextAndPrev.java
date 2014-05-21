import java.math.*;
import java.util.*;

public class NextAndPrev {
    public int getMinimum(int nextCost, int prevCost, String start, String goal) {
        if (start.equals(goal)) {
            return 0;
        }

        int[] destination = new int[26];
        Arrays.fill(destination, -1);

        for (int i = 0; i < start.length(); ++ i) {
            int x = start.charAt(i) - 'a';
            int y = goal.charAt(i) - 'a';
            if (destination[x] == -1) {
                destination[x] = y;
            }
            if (destination[x] != y) {
                return -1;
            }
        }

        if (true) {
            boolean full = true;
            for (int i = 0; i < 26; ++ i) {
                int j = 0;
                while (j < 26 && destination[j] != i) {
                    j ++;
                }
                full &= j < 26;
            }
            if (full) {
                return -1;
            }
        }

        int result = Integer.MAX_VALUE;
        for (int k = 0; k < 26; ++ k) {
            int m = 0;
            int[] a = new int[26];
            int[] b = new int[26];
            for (int t = 0; t < 2; ++ t) {
                for (int i = (t == 0 ? k : 0); i < (t == 0 ? 26 : k); ++ i) {
                    if (destination[i] != -1) {
                        a[m] = i + (t == 0 ? 0 : 26);
                        b[m ++] = destination[i];
                    }
                }
            }
            boolean valid = true;
            for (int i = 0; i + 1 < m; ++ i) {
                valid &= b[i] <= b[i + 1];
            }
            if (!valid) {
                continue;
            }
            for (int shift = -1; shift <= 2; ++ shift) {
                int[] nextNeed = new int[26];
                int[] prevNeed = new int[26];
                for (int i = 0; i < m; ++ i) {
                    int t = b[i] + shift * 26;
                    if (a[i] < t) {
                        nextNeed[b[i]] = Math.max(nextNeed[b[i]], t - a[i]);
                    } else {
                        prevNeed[b[i]] = Math.max(prevNeed[b[i]], a[i] - t);
                    }
                }

                int cost = 0;
                for (int i = 0; i < 26; ++ i) {
                    cost += nextNeed[i] * nextCost + prevNeed[i] * prevCost;
                }
                result = Math.min(result, cost);
            }
        }

        return result < Integer.MAX_VALUE ? result : -1;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            NextAndPrevHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                NextAndPrevHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class NextAndPrevHarness {
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
            int nextCost              = 5;
            int prevCost              = 8;
            String start              = "aeaae";
            String goal               = "bcbbc";
            int expected__            = 21;

            return verifyCase(casenum__, expected__, new NextAndPrev().getMinimum(nextCost, prevCost, start, goal));
        }
        case 1: {
            int nextCost              = 5;
            int prevCost              = 8;
            String start              = "aeaae";
            String goal               = "bccbc";
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new NextAndPrev().getMinimum(nextCost, prevCost, start, goal));
        }
        case 2: {
            int nextCost              = 1;
            int prevCost              = 1;
            String start              = "srm";
            String goal               = "srm";
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new NextAndPrev().getMinimum(nextCost, prevCost, start, goal));
        }
        case 3: {
            int nextCost              = 1000;
            int prevCost              = 39;
            String start              = "a";
            String goal               = "b";
            int expected__            = 975;

            return verifyCase(casenum__, expected__, new NextAndPrev().getMinimum(nextCost, prevCost, start, goal));
        }
        case 4: {
            int nextCost              = 123;
            int prevCost              = 456;
            String start              = "pqrs";
            String goal               = "abab";
            int expected__            = -1;

            return verifyCase(casenum__, expected__, new NextAndPrev().getMinimum(nextCost, prevCost, start, goal));
        }
        case 5: {
            int nextCost              = 100;
            int prevCost              = 19;
            String start              = "topcoder";
            String goal               = "ssszsffs";
            int expected__            = 676;

            return verifyCase(casenum__, expected__, new NextAndPrev().getMinimum(nextCost, prevCost, start, goal));
        }
        case 6: {
            int nextCost              = 1;
            int prevCost              = 1000;
            String start              = "csk";
            String goal               = "wog";
            int expected__            = 64;

            return verifyCase(casenum__, expected__, new NextAndPrev().getMinimum(nextCost, prevCost, start, goal));
        }
        case 7: {
            int nextCost              = 7;
            int prevCost              = 6;
            String start              = "qwerty";
            String goal               = "jjjjjj";
            int expected__            = 125;

            return verifyCase(casenum__, expected__, new NextAndPrev().getMinimum(nextCost, prevCost, start, goal));
        }
        case 8: {
            int nextCost              = 306;
            int prevCost              = 26;
            String start              = "me";
            String goal               = "ii";
            int expected__            = 572;

            return verifyCase(casenum__, expected__, new NextAndPrev().getMinimum(nextCost, prevCost, start, goal));
        }

        // custom cases

        case 9: {
            int nextCost              = 639;
            int prevCost              = 225;
            String start              = "abcdefgfijklmnopqrstuvwxyz";
            String goal               = "tuvwxyzybcdefghijklmnopqrs";
            int expected__            = 39375;

            return verifyCase(casenum__, expected__, new NextAndPrev().getMinimum(nextCost, prevCost, start, goal));
        }
/*      case 10: {
            int nextCost              = ;
            int prevCost              = ;
            String start              = ;
            String goal               = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new NextAndPrev().getMinimum(nextCost, prevCost, start, goal));
        }*/
/*      case 11: {
            int nextCost              = ;
            int prevCost              = ;
            String start              = ;
            String goal               = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new NextAndPrev().getMinimum(nextCost, prevCost, start, goal));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
