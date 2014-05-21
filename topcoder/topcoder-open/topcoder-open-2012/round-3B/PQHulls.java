import java.math.*;
import java.util.*;

public class PQHulls {
    final static int MOD = (int)1e9 + 7;

    int[] generate(int n, int zero, int mul, int add, int mod) {
        int[] ret = new int[n];
        ret[0] = zero;
        for (int i = 1; i < n; ++ i) {
            ret[i] = (int)(((long)ret[i - 1] * mul + add) % mod);
        }
        return ret;
    }

    int signum(long x) {
        if (x == 0) {
            return 0;
        }
        return x < 0 ? -1 : 1;
    }

    class Point implements Comparable <Point> {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Point subtract(Point o) {
            return new Point(x - o.x, y - o.y);
        }

        int quadrant() {
            if (y != 0) {
                return y < 0 ? 0 : 1;
            }
            return x > 0 ? 0 : 1;
        }

        long det(Point o) {
            return (long)x * o.y - (long)y * o.x;
        }

        public int compareTo(Point o) {
            if (quadrant() != o.quadrant()) {
                return quadrant() - o.quadrant();
            }
            return signum(-det(o));
        }

        boolean equals(Point o) {
            return x == o.x && y == o.y;
        }

        public String toString() {
            return String.format("(%d, %d)", x, y);
        }
    }

    int[] power;

    int count(Point[] points) {
        int n = points.length;
        int ret = power[n] - 1;
        for (int i = 0, total = 0; i < n; ++ i) {
            total = Math.max(total - 1, 1);
            while (total < n && points[i].det(points[(i + total) % n]) > 0) {
                total ++;
            }
            ret -= power[total - 1];
            ret %= MOD;
        }
        return (ret + MOD) % MOD;
    }

    int count(Point[] points, Point special) {
        int n = points.length;
        int ret = power[n - 1];
        int count = 0;
        for (int i = 0, total = 0; i < n; ++ i) {
            while (total < n && points[i].det(points[(i + total) % n]) >= 0) {
                if (points[(i + total) % n].equals(special)) {
                    count ++;
                }
                total ++;
            }
            if (count > 0) {
                if (points[i].equals(special)) {
                    ret -= power[total - 1];
                    ret %= MOD;
                } else {
                    ret -= power[total - 2];
                    ret %= MOD;
                }
            }
            if (points[i].equals(special)) {
                count --;
            }
            total --;
        }
        return (ret + MOD) % MOD;
    }

    int work(Point[] points, Point q) {
        int n = points.length;
        Arrays.sort(points);
        int ret = count(points);
        Point o = new Point(0, 0);
        for (int i = 0; i < n; ++ i) {
            if (points[i].subtract(q).det(o.subtract(q)) <= 0) {
                continue;
            }
            ArrayList <Point> newPoints = new ArrayList <Point>();
            for (int j = 0; j < n; ++ j) {
                if (points[i].subtract(q).det(points[j].subtract(q)) >= 0) {
                    newPoints.add(points[j]);
                }
            }
            ret -= count((Point[])newPoints.toArray(new Point[0]), points[i]);
            ret %= MOD;
        }
        return (ret + MOD) % MOD;
    }

    public int countSubsets(int n, int xzero, int xmul, int xadd, int xmod, int yzero, int ymul, int yadd, int ymod) {
        int[] xs = generate(n + 2, xzero, xmul, xadd, xmod);
        int[] ys = generate(n + 2, yzero, ymul, yadd, ymod);
        Point[] points = new Point[n];
        Point p = new Point(xs[n], ys[n]);
        Point q = new Point(xs[n + 1], ys[n + 1]).subtract(p);
        for (int i = 0; i < n; ++ i) {
            points[i] = new Point(xs[i], ys[i]).subtract(p);
        }
        power = new int[n + 1];
        power[0] = 1;
        for (int i = 1; i <= n; ++ i) {
            power[i] = power[i - 1] * 2 % MOD;
        }
        return work(points, q);
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            PQHullsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                PQHullsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class PQHullsHarness {
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
            int N                     = 4;
            int X0                    = 3;
            int XMul                  = 3;
            int XAdd                  = 3;
            int XMod                  = 10;
            int Y0                    = 0;
            int YMul                  = 3;
            int YAdd                  = 2;
            int YMod                  = 7;
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new PQHulls().countSubsets(N, X0, XMul, XAdd, XMod, Y0, YMul, YAdd, YMod));
        }
        case 1: {
            int N                     = 5;
            int X0                    = 1;
            int XMul                  = 5;
            int XAdd                  = 6;
            int XMod                  = 8;
            int Y0                    = 5;
            int YMul                  = 5;
            int YAdd                  = 3;
            int YMod                  = 9;
            int expected__            = 5;

            return verifyCase(casenum__, expected__, new PQHulls().countSubsets(N, X0, XMul, XAdd, XMod, Y0, YMul, YAdd, YMod));
        }
        case 2: {
            int N                     = 5;
            int X0                    = 4;
            int XMul                  = 1;
            int XAdd                  = 1;
            int XMod                  = 8;
            int Y0                    = 5;
            int YMul                  = 4;
            int YAdd                  = 4;
            int YMod                  = 6;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new PQHulls().countSubsets(N, X0, XMul, XAdd, XMod, Y0, YMul, YAdd, YMod));
        }
        case 3: {
            int N                     = 99;
            int X0                    = 9461448;
            int XMul                  = 38301228;
            int XAdd                  = 33476602;
            int XMod                  = 42996440;
            int Y0                    = 10502745;
            int YMul                  = 35649230;
            int YAdd                  = 12271470;
            int YMod                  = 65500929;
            int expected__            = 181248946;

            return verifyCase(casenum__, expected__, new PQHulls().countSubsets(N, X0, XMul, XAdd, XMod, Y0, YMul, YAdd, YMod));
        }
        case 4: {
            int N                     = 2000;
            int X0                    = 6927240;
            int XMul                  = 2020343;
            int XAdd                  = 10323527;
            int XMod                  = 10690663;
            int Y0                    = 28749177;
            int YMul                  = 29744699;
            int YAdd                  = 60134478;
            int YMod                  = 78767213;
            int expected__            = 143010383;

            return verifyCase(casenum__, expected__, new PQHulls().countSubsets(N, X0, XMul, XAdd, XMod, Y0, YMul, YAdd, YMod));
        }

        // custom cases

      case 5: {
            int N                     = 10;
            int X0                    = 13;
            int XMul                  = 5;
            int XAdd                  = 13;
            int XMod                  = 19;
            int Y0                    = 6;
            int YMul                  = 1;
            int YAdd                  = 4;
            int YMod                  = 7;
            int expected__            = 128;

            return verifyCase(casenum__, expected__, new PQHulls().countSubsets(N, X0, XMul, XAdd, XMod, Y0, YMul, YAdd, YMod));
        }
/*      case 6: {
            int N                     = ;
            int X0                    = ;
            int XMul                  = ;
            int XAdd                  = ;
            int XMod                  = ;
            int Y0                    = ;
            int YMul                  = ;
            int YAdd                  = ;
            int YMod                  = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new PQHulls().countSubsets(N, X0, XMul, XAdd, XMod, Y0, YMul, YAdd, YMod));
        }*/
/*      case 7: {
            int N                     = ;
            int X0                    = ;
            int XMul                  = ;
            int XAdd                  = ;
            int XMod                  = ;
            int Y0                    = ;
            int YMul                  = ;
            int YAdd                  = ;
            int YMod                  = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new PQHulls().countSubsets(N, X0, XMul, XAdd, XMod, Y0, YMul, YAdd, YMod));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
