import java.math.*;
import java.util.*;

public class Excavations {
    @SuppressWarnings("unchecked")
    public long count(int[] kind, int[] depth, int[] found, int m) {
        int n = kind.length;
        ArrayList[] depths = new ArrayList[50];
        for (int i = 0; i < 50; ++ i) {
            depths[i] = new ArrayList <Integer>();
        }
        for (int i = 0; i < n; ++ i) {
            int count = 1;
            for (int j = 0; j < n; ++ j) {
                if (depth[j] < depth[i]) {
                    count ++;
                }
            }
            depths[kind[i] - 1].add(count);
        }
        for (int i = 0; i < 50; ++ i) {
            Collections.sort(depths[i]);
        }
        ArrayList <Integer> forbidden = new ArrayList <Integer>();
        long[][] ways = new long[m + 1][51];
        ways[0][0] = 1;
        for (int k = 0; k < 50; ++ k) {
            boolean flag = false;
            for (int kk : found) {
                flag |= k == kk - 1;
            }
            if (flag) {
                long[][][] auxiliary = new long[2][][];
                auxiliary[0] = ways;
                auxiliary[1] = new long[m + 1][51];
                for (Object obj : depths[k]) {
                    int d = (Integer)obj;
                    long[][][] newAuxiliary = new long[2][m + 1][51];
                    for (int t = 0; t < 2; ++ t) {
                        for (int x = 0; x <= m; ++ x) {
                            for (int h = 0; h <= 50; ++ h) {
                                newAuxiliary[t][x][h] += auxiliary[t][x][h];
                                if (x + 1 <= m) {
                                    newAuxiliary[t | 1][x + 1][t == 0 ? Math.max(h, d) : h] += auxiliary[t][x][h];
                                }
                            }
                        }
                    }
                    auxiliary = newAuxiliary;
                }
                ways = auxiliary[1];
            } else {
                for (Object obj : depths[k]) {
                    int d = (Integer)obj;
                    forbidden.add(d);
                }
            }
        }
        Collections.sort(forbidden);
        long[][] binom = new long[n + 1][n + 1];
        for (int i = 0; i <= n; ++ i) {
            binom[i][0] = 1;
            for (int j = 1; j <= i; ++ j) {
                binom[i][j] = binom[i - 1][j - 1] + binom[i - 1][j];
            }
        }
        long answer = 0;
        for (int h = 0; h <= 50; ++ h) {
            for (int x = 0; x < m; ++ x) {
                for (int i = 0; i < forbidden.size(); ++ i) {
                    if (h < forbidden.get(i)) {
                        answer += ways[x][h] * binom[forbidden.size() - i - 1][m - x - 1];
                    }
                }
            }
            answer += ways[m][h];
        }
        return answer;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            ExcavationsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                ExcavationsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class ExcavationsHarness {
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

    static boolean compareOutput(long expected, long result) { return expected == result; }
    static String formatResult(long res) {
        return String.format("%d", res);
    }

    static int verifyCase(int casenum, long expected, long received) {
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
            int[] kind                = {1, 1, 2, 2};
            int[] depth               = {10, 15, 10, 20};
            int[] found               = {1};
            int K                     = 2;
            long expected__           = 3;

            return verifyCase(casenum__, expected__, new Excavations().count(kind, depth, found, K));
        }
        case 1: {
            int[] kind                = {1, 1, 2, 2};
            int[] depth               = {10, 15, 10, 20};
            int[] found               = {1, 2};
            int K                     = 2;
            long expected__           = 4;

            return verifyCase(casenum__, expected__, new Excavations().count(kind, depth, found, K));
        }
        case 2: {
            int[] kind                = {1, 2, 3, 4};
            int[] depth               = {10, 10, 10, 10};
            int[] found               = {1, 2};
            int K                     = 3;
            long expected__           = 0;

            return verifyCase(casenum__, expected__, new Excavations().count(kind, depth, found, K));
        }
        case 3: {
            int[] kind                = {1, 2, 2, 3, 1, 3, 2, 1, 2};
            int[] depth               = {12512, 12859, 125, 1000, 99, 114, 125, 125, 114};
            int[] found               = {1, 2, 3};
            int K                     = 7;
            long expected__           = 35;

            return verifyCase(casenum__, expected__, new Excavations().count(kind, depth, found, K));
        }
        case 4: {
            int[] kind                = {50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50};
            int[] depth               = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3};
            int[] found               = {50};
            int K                     = 18;
            long expected__           = 9075135300L;

            return verifyCase(casenum__, expected__, new Excavations().count(kind, depth, found, K));
        }

        // custom cases

/*      case 5: {
            int[] kind                = ;
            int[] depth               = ;
            int[] found               = ;
            int K                     = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new Excavations().count(kind, depth, found, K));
        }*/
/*      case 6: {
            int[] kind                = ;
            int[] depth               = ;
            int[] found               = ;
            int K                     = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new Excavations().count(kind, depth, found, K));
        }*/
/*      case 7: {
            int[] kind                = ;
            int[] depth               = ;
            int[] found               = ;
            int K                     = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new Excavations().count(kind, depth, found, K));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
