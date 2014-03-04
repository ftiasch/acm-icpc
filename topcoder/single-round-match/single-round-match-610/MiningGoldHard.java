import java.math.*;
import java.util.*;

public class MiningGoldHard {
    public int GetMaximumGold(int n, int m, int[] event_i, int[] event_j, int[] event_di, int[] event_dj) {
        return (n + m) * event_i.length - solve(n, event_i, event_di) - solve(m, event_j, event_dj);
    }

    int solve(int m, int[] events, int[] deltas) {
        int n = events.length;
        List <Point> ends = new ArrayList <Point>();
        ends.add(new Point(-1, events[n - 1] + 1));
        ends.add(new Point(events[n - 1], 0));
        ends.add(new Point(m + 1, m + 1 - events[n - 1]));
        for (int i = n - 2; i >= 0; -- i) {
            List <Point> newEnds = new ArrayList <Point>();
            if (deltas[i] > 0) {
                int low = 0;
                while (low + 1 < ends.size() && ends.get(low).y > ends.get(low + 1).y) {
                    low ++;
                }
                for (int j = 0; j <= low; ++ j) {
                    Point p = ends.get(j);
                    newEnds.add(new Point(p.x - deltas[i], p.y));
                }
                if (true) {
                    Point p = ends.get(low);
                    newEnds.add(new Point(p.x + deltas[i], p.y));
                }
                for (int j = low + 1; j < ends.size(); ++ j) {
                    Point p = ends.get(j);
                    newEnds.add(new Point(p.x + deltas[i], p.y));
                }
                ends = newEnds;
            }
            newEnds = new ArrayList <Point>();
            for (int j = 0; j < ends.size(); ++ j) {
                Point p = ends.get(j);
                newEnds.add(new Point(p.x, p.y + Math.abs(p.x - events[i])));
                if (p.x < events[i] && j + 1 < ends.size()) {
                    Point q = ends.get(j + 1);
                    if (events[i] < q.x) {
                        newEnds.add(new Point(events[i], p.y + (q.y - p.y) / (q.x - p.x) * (events[i] - p.x)));
                    }
                }
            }
            ends = newEnds;
        }
        int result = Integer.MAX_VALUE;
        for (Point end : ends) {
            if (0 <= end.x && end.x <= m) {
                result = Math.min(result, (int)end.y);
            }
        }
        return result;
    }

    class Point {
        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return String.format("(%d,%d)", x, y);
        }

        long x, y;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            MiningGoldHardHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                MiningGoldHardHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class MiningGoldHardHarness {
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
            int N                     = 3;
            int M                     = 3;
            int[] event_i             = {1};
            int[] event_j             = {1};
            int[] event_di            = {};
            int[] event_dj            = {};
            int expected__            = 6;

            return verifyCase(casenum__, expected__, new MiningGoldHard().GetMaximumGold(N, M, event_i, event_j, event_di, event_dj));
        }
        case 1: {
            int N                     = 3;
            int M                     = 3;
            int[] event_i             = {0, 2};
            int[] event_j             = {0, 2};
            int[] event_di            = {1};
            int[] event_dj            = {1};
            int expected__            = 10;

            return verifyCase(casenum__, expected__, new MiningGoldHard().GetMaximumGold(N, M, event_i, event_j, event_di, event_dj));
        }
        case 2: {
            int N                     = 4;
            int M                     = 2;
            int[] event_i             = {1, 4, 4};
            int[] event_j             = {1, 2, 0};
            int[] event_di            = {1, 1};
            int[] event_dj            = {1, 1};
            int expected__            = 15;

            return verifyCase(casenum__, expected__, new MiningGoldHard().GetMaximumGold(N, M, event_i, event_j, event_di, event_dj));
        }
        case 3: {
            int N                     = 6;
            int M                     = 6;
            int[] event_i             = {0, 2, 1, 5, 6, 4};
            int[] event_j             = {4, 3, 1, 6, 2, 0};
            int[] event_di            = {2, 3, 1, 5, 6};
            int[] event_dj            = {2, 4, 0, 5, 1};
            int expected__            = 63;

            return verifyCase(casenum__, expected__, new MiningGoldHard().GetMaximumGold(N, M, event_i, event_j, event_di, event_dj));
        }
        case 4: {
            int N                     = 72;
            int M                     = 90;
            int[] event_i             = {9, 9, 42, 64, 37, 4, 67, 65, 20, 18, 25, 45, 19, 57, 34, 29, 20, 43, 17, 46, 61, 1, 18, 53, 54, 23, 9, 69, 57, 44, 34, 50, 37, 4, 26, 1, 8, 4, 66};
            int[] event_j             = {37, 47, 48, 69, 56, 22, 40, 52, 43, 46, 64, 24, 48, 54, 54, 56, 32, 77, 50, 8, 7, 90, 55, 34, 40, 89, 57, 44, 21, 59, 89, 21, 69, 46, 0, 89, 31, 3, 50};
            int[] event_di            = {56, 5, 21, 5, 22, 5, 45, 4, 44, 20, 68, 63, 37, 14, 43, 31, 70, 28, 51, 38, 20, 59, 72, 66, 16, 20, 39, 72, 11, 71, 21, 51, 60, 42, 40, 10, 32, 70};
            int[] event_dj            = {77, 73, 42, 80, 43, 24, 81, 68, 40, 86, 1, 76, 43, 10, 43, 53, 40, 26, 18, 70, 60, 68, 29, 17, 66, 2, 87, 71, 90, 33, 11, 76, 69, 17, 65, 21, 4, 19};
            int expected__            = 5810;

            return verifyCase(casenum__, expected__, new MiningGoldHard().GetMaximumGold(N, M, event_i, event_j, event_di, event_dj));
        }

        // custom cases

/*      case 5: {
            int N                     = ;
            int M                     = ;
            int[] event_i             = ;
            int[] event_j             = ;
            int[] event_di            = ;
            int[] event_dj            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new MiningGoldHard().GetMaximumGold(N, M, event_i, event_j, event_di, event_dj));
        }*/
/*      case 6: {
            int N                     = ;
            int M                     = ;
            int[] event_i             = ;
            int[] event_j             = ;
            int[] event_di            = ;
            int[] event_dj            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new MiningGoldHard().GetMaximumGold(N, M, event_i, event_j, event_di, event_dj));
        }*/
/*      case 7: {
            int N                     = ;
            int M                     = ;
            int[] event_i             = ;
            int[] event_j             = ;
            int[] event_di            = ;
            int[] event_dj            = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new MiningGoldHard().GetMaximumGold(N, M, event_i, event_j, event_di, event_dj));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
