import java.util.*;
import java.math.*;

public class ThreePoints {
    int[] generate(int n, int zero, int mul, int add, int mod) {
        int[] ret = new int[n];
        ret[0] = zero;
        for (int i = 1; i < n; ++ i) {
            ret[i] = (int)(((long)ret[i - 1] * mul + add) % mod);
        }
        return ret;
    }

    class Point implements Comparable <Point> {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Point o) {
            if (x != o.x) {
                return x - o.x;
            }
            return y - o.y;
        }
    }

    int indexOf(int[] ys, int y) {
        int low = 0;
        int high = ys.length;
        while (high - low > 1) {
            int middle = low + high >> 1;
            if (ys[middle] > y) {
                high = middle;
            } else {
                low = middle;
            }
        }
        return low;
    }

    class FenwichTree {
        int n;
        long[] count;

        FenwichTree(int n) {
            this.n = n;
            this.count = new long[n];
        }

        void add(int k, long v) {
            for (; k >= 0; k -= ~k & k + 1) {
                count[k] += v;
            }
        }

        long ask(int k) {
            long ret = 0;
            for (; k < n; k += ~k & k + 1) {
                ret += count[k];
            }
            return ret;
        }
    }

    public long countColoring(int n, int xzero, int xmul, int xadd, int xmod, int yzero, int ymul, int yadd, int ymod) {
        int[] xs = generate(n, xzero, xmul, xadd, xmod);
        int[] ys = generate(n, yzero, ymul, yadd, ymod);

        Point[] points = new Point[n];
        for (int i = 0; i < n; ++ i) {
            points[i] = new Point(xs[i], ys[i]);
        }
        Arrays.sort(points);

        Arrays.sort(ys);
        long answer = 0;
        FenwichTree up = new FenwichTree(n);
        FenwichTree up2 = new FenwichTree(n);
        for (int i = n - 1; i >= 0; -- i) {
            int y = indexOf(ys, points[i].y);
            long size = up.ask(y);
            answer += (long)size * (size - 1) / 2;
            answer -= up2.ask(y);
            up.add(y, 1);
            up2.add(y, size);
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            ThreePointsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                ThreePointsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class ThreePointsHarness {
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
            int N                     = 9;
            int xzero                 = 3;
            int xmul                  = 8;
            int xadd                  = 6;
            int xmod                  = 11;
            int yzero                 = 5;
            int ymul                  = 7;
            int yadd                  = 8;
            int ymod                  = 11;
            long expected__           = 8;

            return verifyCase(casenum__, expected__, new ThreePoints().countColoring(N, xzero, xmul, xadd, xmod, yzero, ymul, yadd, ymod));
        }
        case 1: {
            int N                     = 4;
            int xzero                 = 9;
            int xmul                  = 6;
            int xadd                  = 8;
            int xmod                  = 10;
            int yzero                 = 4;
            int ymul                  = 8;
            int yadd                  = 5;
            int ymod                  = 10;
            long expected__           = 2;

            return verifyCase(casenum__, expected__, new ThreePoints().countColoring(N, xzero, xmul, xadd, xmod, yzero, ymul, yadd, ymod));
        }
        case 2: {
            int N                     = 20;
            int xzero                 = 30;
            int xmul                  = 3;
            int xadd                  = 71;
            int xmod                  = 100;
            int yzero                 = 78;
            int ymul                  = 12;
            int yadd                  = 50;
            int ymod                  = 100;
            long expected__           = 263;

            return verifyCase(casenum__, expected__, new ThreePoints().countColoring(N, xzero, xmul, xadd, xmod, yzero, ymul, yadd, ymod));
        }
        case 3: {
            int N                     = 300000;
            int xzero                 = 99097861;
            int xmul                  = 102766912;
            int xadd                  = 95284952;
            int xmod                  = 123456789;
            int yzero                 = 443104491;
            int ymul                  = 971853214;
            int yadd                  = 569775557;
            int ymod                  = 987654321;
            long expected__           = 749410681185726L;

            return verifyCase(casenum__, expected__, new ThreePoints().countColoring(N, xzero, xmul, xadd, xmod, yzero, ymul, yadd, ymod));
        }

        // custom cases

/*      case 4: {
            int N                     = ;
            int xzero                 = ;
            int xmul                  = ;
            int xadd                  = ;
            int xmod                  = ;
            int yzero                 = ;
            int ymul                  = ;
            int yadd                  = ;
            int ymod                  = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new ThreePoints().countColoring(N, xzero, xmul, xadd, xmod, yzero, ymul, yadd, ymod));
        }*/
/*      case 5: {
            int N                     = ;
            int xzero                 = ;
            int xmul                  = ;
            int xadd                  = ;
            int xmod                  = ;
            int yzero                 = ;
            int ymul                  = ;
            int yadd                  = ;
            int ymod                  = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new ThreePoints().countColoring(N, xzero, xmul, xadd, xmod, yzero, ymul, yadd, ymod));
        }*/
/*      case 6: {
            int N                     = ;
            int xzero                 = ;
            int xmul                  = ;
            int xadd                  = ;
            int xmod                  = ;
            int yzero                 = ;
            int ymul                  = ;
            int yadd                  = ;
            int ymod                  = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new ThreePoints().countColoring(N, xzero, xmul, xadd, xmod, yzero, ymul, yadd, ymod));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
