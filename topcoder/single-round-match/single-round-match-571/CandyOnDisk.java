import java.math.*;
import java.util.*;

public class CandyOnDisk {
    static double INFINITY = 1e11;

    boolean solve(int[] x, int[] y, int[] r, int sx, int sy, int tx, int ty) {
        if (sx == tx && sy == ty) {
            return true;
        }
        int n = x.length;
        double[][] distance = new double[n][n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                distance[i][j] = Math.hypot(x[i] - x[j], y[i] - y[j]);
            }
        }
        double[] min = new double[n];
        double[] max = new double[n];
        for (int i = 0; i < n; ++ i) {
            double d = Math.hypot(sx - x[i], sy - y[i]);
            if (d < r[i]) {
                min[i] = max[i] = d;
            } else {
                min[i] = r[i];
                max[i] = 0;
            }
        }
        boolean changed;
        do {
            changed = false;
            for (int i = 0; i < n; ++ i) {
                if (min[i] <= max[i]) {
                    for (int j = 0; j < n; ++ j) {
                        if (x[i] != x[j] || y[i] != y[j]) {
                            double d = distance[i][j];
                            if (Math.max(min[i], d - r[j]) <= Math.min(max[i], d + r[j])) {
                                if (Math.max(d - r[i], 0) < min[j]) {
                                    min[j] = Math.max(d - r[i], 0);
                                    changed = true;
                                }
                                if (Math.min(r[j], d + r[i]) > max[j]) {
                                    max[j] = Math.min(r[j], d + r[i]);
                                    changed = true;
                                }
                            }
                        }
                    }
                }
            }
        } while (changed);
        for (int i = 0; i < n; ++ i) {
            double d = Math.hypot(tx - x[i], ty - y[i]);
            if (min[i] < d && d < max[i]) {
                return true;
            }
        }
        return false;
    }

    public String ableToAchieve(int[] x, int[] y, int[] r, int sx, int sy, int tx, int ty) {
        return solve(x, y, r, sx, sy, tx, ty) ? "YES" : "NO";
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            CandyOnDiskHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                CandyOnDiskHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class CandyOnDiskHarness {
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

    static boolean compareOutput(String expected, String result) { return expected.equals(result); }
    static String formatResult(String res) {
        return String.format("\"%s\"", res);
    }

    static int verifyCase(int casenum, String expected, String received) {
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
            int[] x                   = {0, 4};
            int[] y                   = {0, 0};
            int[] r                   = {3, 3};
            int sx                    = -1;
            int sy                    = -2;
            int tx                    = 6;
            int ty                    = 1;
            String expected__         = "YES";

            return verifyCase(casenum__, expected__, new CandyOnDisk().ableToAchieve(x, y, r, sx, sy, tx, ty));
        }
        case 1: {
            int[] x                   = {0, 3};
            int[] y                   = {0, 0};
            int[] r                   = {5, 3};
            int sx                    = -4;
            int sy                    = 0;
            int tx                    = -2;
            int ty                    = 0;
            String expected__         = "YES";

            return verifyCase(casenum__, expected__, new CandyOnDisk().ableToAchieve(x, y, r, sx, sy, tx, ty));
        }
        case 2: {
            int[] x                   = {0};
            int[] y                   = {0};
            int[] r                   = {1};
            int sx                    = 0;
            int sy                    = 0;
            int tx                    = 571;
            int ty                    = 571;
            String expected__         = "NO";

            return verifyCase(casenum__, expected__, new CandyOnDisk().ableToAchieve(x, y, r, sx, sy, tx, ty));
        }
        case 3: {
            int[] x                   = {0};
            int[] y                   = {0};
            int[] r                   = {1};
            int sx                    = 571;
            int sy                    = 571;
            int tx                    = 571;
            int ty                    = 571;
            String expected__         = "YES";

            return verifyCase(casenum__, expected__, new CandyOnDisk().ableToAchieve(x, y, r, sx, sy, tx, ty));
        }
        case 4: {
            int[] x                   = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
            int[] y                   = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
            int[] r                   = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
            int sx                    = 2;
            int sy                    = 2;
            int tx                    = 19;
            int ty                    = 19;
            String expected__         = "YES";

            return verifyCase(casenum__, expected__, new CandyOnDisk().ableToAchieve(x, y, r, sx, sy, tx, ty));
        }
        case 5: {
            int[] x                   = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
            int[] y                   = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
            int[] r                   = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
            int sx                    = 2;
            int sy                    = 2;
            int tx                    = 19;
            int ty                    = 19;
            String expected__         = "NO";

            return verifyCase(casenum__, expected__, new CandyOnDisk().ableToAchieve(x, y, r, sx, sy, tx, ty));
        }

        // custom cases

        case 6: {
            int[] x                   = {0};
            int[] y                   = {0};
            int[] r                   = {1000000000};
            int sx                    = 700000000;
            int sy                    = 700000000;
            int tx                    = 699999999;
            int ty                    = 700000001;
            String expected__         = "NO";

            return verifyCase(casenum__, expected__, new CandyOnDisk().ableToAchieve(x, y, r, sx, sy, tx, ty));
        }
        case 7: {
            int[] x                   = {0, 1};
            int[] y                   = {0, 0};
            int[] r                   = {1000000000, 1000000000};
            int sx                    = 0;
            int sy                    = 0;
            int tx                    = 1000000000;
            int ty                    = 0;
            String expected__         = "YES";

            return verifyCase(casenum__, expected__, new CandyOnDisk().ableToAchieve(x, y, r, sx, sy, tx, ty));
        }
/*      case 8: {
            int[] x                   = ;
            int[] y                   = ;
            int[] r                   = ;
            int sx                    = ;
            int sy                    = ;
            int tx                    = ;
            int ty                    = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new CandyOnDisk().ableToAchieve(x, y, r, sx, sy, tx, ty));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
