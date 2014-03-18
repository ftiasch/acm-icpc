import java.math.*;
import java.util.*;

public class EnclosingTriangle {
    public long getNumber(int m, int[] x, int[] y) {
        int n = x.length;
        Point[] points = new Point[n];
        for (int i = 0; i < n; ++ i) {
            points[i] = new Point(x[i], y[i]);
        }
        Point[] polygon = new Point[m << 2];
        for (int i = 0; i < m; ++ i) {
            polygon[i] = new Point(i, 0);
            polygon[m + i] = new Point(m, i);
            polygon[m + m + i] = new Point(m - i, m);
            polygon[m + m + m + i] = new Point(0, m - i);
        }
        return solve(points, polygon);
    }

    long solve(Point[] points, Point[] polygon) {
        int n = polygon.length;
        int[] next = new int[n];
        for (int i = 0, j = 1; i < n; ++ i) {
            while (check(points, polygon[i], polygon[(i + j) % n])) {
                j ++;
            }
            next[i] = j;
            if (j > 1) {
                j --;
            }
        }
        long[] sum = new long[(n << 1) + 1];
        for (int i = (n << 1) - 1; i >= 0; -- i) {
            sum[i] = sum[i + 1] + next[i % n];
        }
        long result = 0;
        for (int i = 0, j = 1, k = 1; i < n; ++ i) {
            while ((n - k) % n >= next[(i + k) % n]) {
                k ++;
            }
            while (j < next[i] && (k + n - j) % n >= next[(i + j) % n]) {
                j ++;
            }
            int d = (k + n - j) % n;
            int c = next[i] - j;
            result += sum[i + j] - sum[i + next[i]] - (long)c * (d + d - c + 1) / 2;
            if (next[i] - 1 + next[(i + next[i] - 1) % n] > n) {
                result -= 2;
            }
            if (j > 1) {
                j --;
            }
            if (k > 1) {
                k --;
            }
        }
        assert result % 3 == 0;
        return result / 3;
    }

    boolean check(Point[] points, Point a, Point b) {
        for (Point point : points) {
            if (b.subtract(a).det(point.subtract(a)) < 0) {
                return false;
            }
        }
        return true;
    }

    class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Point subtract(Point other) {
            return new Point(x - other.x, y - other.y);
        }

        long det(Point other) {
            return (long)x * other.y - (long)y * other.x;
        }
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            EnclosingTriangleHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                EnclosingTriangleHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class EnclosingTriangleHarness {
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
            int m                     = 4;
            int[] x                   = { 1, 2, 3 };
            int[] y                   = { 1, 3, 2 };
            long expected__           = 19;

            return verifyCase(casenum__, expected__, new EnclosingTriangle().getNumber(m, x, y));
        }
        case 1: {
            int m                     = 7;
            int[] x                   = { 1, 1, 6, 6 };
            int[] y                   = { 1, 6, 1, 6 };
            long expected__           = 0;

            return verifyCase(casenum__, expected__, new EnclosingTriangle().getNumber(m, x, y));
        }
        case 2: {
            int m                     = 4;
            int[] x                   = { 2 };
            int[] y                   = { 2 };
            long expected__           = 224;

            return verifyCase(casenum__, expected__, new EnclosingTriangle().getNumber(m, x, y));
        }
        case 3: {
            int m                     = 10;
            int[] x                   = { 2, 6, 7, 6 };
            int[] y                   = { 7, 7, 9, 3 };
            long expected__           = 81;

            return verifyCase(casenum__, expected__, new EnclosingTriangle().getNumber(m, x, y));
        }
        case 4: {
            int m                     = 15;
            int[] x                   = { 7, 6, 5, 4, 3 };
            int[] y                   = { 1, 4, 7, 10, 13 };
            long expected__           = 283;

            return verifyCase(casenum__, expected__, new EnclosingTriangle().getNumber(m, x, y));
        }

        // custom cases

        case 5: {
            int m                     = 3;
            int[] x                   = {1};
            int[] y                   = {1};
            long expected__           = 82;

            return verifyCase(casenum__, expected__, new EnclosingTriangle().getNumber(m, x, y));
        }
/*      case 6: {
            int m                     = ;
            int[] x                   = ;
            int[] y                   = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new EnclosingTriangle().getNumber(m, x, y));
        }*/
/*      case 7: {
            int m                     = ;
            int[] x                   = ;
            int[] y                   = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new EnclosingTriangle().getNumber(m, x, y));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
