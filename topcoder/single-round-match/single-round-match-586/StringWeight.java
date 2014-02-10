import java.math.*;
import java.util.*;

public class StringWeight {
    static int C = 26;

    public int getMinimum(int[] lengths) {
        int n = lengths.length;
        int[][][] minimum = new int[n + 1][C + 1][C + 1];
        for (int i = 0; i <= n; ++ i) {
            for (int used = 0; used <= C; ++ used) {
                for (int active = 0; active <= C; ++ active) {
                    minimum[i][used][active] = Integer.MAX_VALUE;
                }
            }
        }
        minimum[0][0][0] = 0;
        for (int i = 0; i < n; ++ i) {
            int length = lengths[i];
            int type = Math.min(length, 26);
            // type <= length
            for (int used = 0; used <= C; ++ used) {
                for (int active = 0; active <= used; ++ active) {
                    if (minimum[i][used][active] < Integer.MAX_VALUE) {
                        for (int first = 0; used + first <= C && first <= type; ++ first) {
                            if (active + first < type) {
                                continue;
                            }
                            for (int inactive = 0; inactive <= Math.min(active + first, length); ++ inactive) {
                                int cost = minimum[i][used][active];
                                int maxInactive = Math.min(length - first, inactive);
                                if (active > maxInactive) {
                                    cost += (active + (active - maxInactive + 1)) * maxInactive / 2;
                                    cost += (active - maxInactive) * (length - first - maxInactive);
                                } else {
                                    cost += (1 + active) * active / 2;
                                    cost += (length - first - active);
                                }
                                int nowActive = Math.max(active - Math.min(inactive, length - first), 0);
                                int nowInactive = Math.min(inactive - Math.min(maxInactive, active), first);
                                cost += nowActive * nowInactive;
                                cost += (nowActive + (nowActive + first - 1 - nowInactive)) * (first - nowInactive) / 2;
                                minimum[i + 1][used + first][active + first - inactive] = Math.min(minimum[i + 1][used + first][active + first - inactive] , cost);
                            }
                        }
                    }
                }
            }
        }
        int result = Integer.MAX_VALUE;
        for (int used = 1; used <= C; ++ used) {
            result = Math.min(result, minimum[n][used][0]);
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            StringWeightHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                StringWeightHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class StringWeightHarness {
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
            int[] L                   = {1};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new StringWeight().getMinimum(L));
        }
        case 1: {
            int[] L                   = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new StringWeight().getMinimum(L));
        }
        case 2: {
            int[] L                   = {26, 2, 2};
            int expected__            = 8;

            return verifyCase(casenum__, expected__, new StringWeight().getMinimum(L));
        }
        case 3: {
            int[] L                   = {25, 25, 25, 25};
            int expected__            = 1826;

            return verifyCase(casenum__, expected__, new StringWeight().getMinimum(L));
        }
        case 4: {
            int[] L                   = {14, 6, 30, 2, 5, 61};
            int expected__            = 1229;

            return verifyCase(casenum__, expected__, new StringWeight().getMinimum(L));
        }

        // custom cases

/*      case 5: {
            int[] L                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new StringWeight().getMinimum(L));
        }*/
/*      case 6: {
            int[] L                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new StringWeight().getMinimum(L));
        }*/
/*      case 7: {
            int[] L                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new StringWeight().getMinimum(L));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
