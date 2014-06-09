import java.math.*;
import java.util.*;

public class CatchTheBeat {
    public int maxCatched(int n, int x0, int y0, int a, int b, int c, int d, int mod1, int mod2, int offset) {
        int[] t = generate(n, y0, c, d, mod2);
        int[] x = generate(n, x0, a, b, mod1);
        for (int i = 0; i < n; ++ i) {
            x[i] -= offset;
        }
        List <Pair> pairs = new ArrayList <Pair>();
        for (int i = 0; i < n; ++ i) {
            if (Math.abs(x[i]) <= t[i]) {
                pairs.add(new Pair((long)t[i] - x[i], (long)t[i] + x[i]));
            }
        }
        Collections.sort(pairs);
        n = pairs.size();
        long[] highest = new long[n + 1];
        Arrays.fill(highest, Long.MAX_VALUE);
        highest[0] = Long.MIN_VALUE;
        int result = 0;
        for (int i = 0; i < n; ++ i) {
            long y = pairs.get(i).y;
            int low = 0;
            int high = n;
            while (low < high) {
                int middle = low + high + 1 >> 1;
                if (highest[middle] <= y) {
                    low = middle;
                } else {
                    high = middle - 1;
                }
            }
            low ++;
            result = Math.max(result, low);
            highest[low] = Math.min(highest[low], y);
        }
        return result;
    }

    class Pair implements Comparable {
        Pair(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Object other) {
            Pair p = (Pair)other;
            if (x != p.x) {
                return signum(x - p.x);
            }
            return signum(y - p.y);
        }

        public int signum(long number) {
            if (number == 0) {
                return 0;
            }
            return number < 0 ? -1 : 1;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", x, y);
        }

        long x, y;
    }

    int[] generate(int n, int x0, int a, int b, int mod) {
        int[] x = new int[n];
        x[0] = x0;
        for (int i = 1; i < n; ++ i) {
            x[i] = (int)(((long)x[i - 1] * a + b) % mod);
        }
        return x;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            CatchTheBeatHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                CatchTheBeatHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class CatchTheBeatHarness {
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
            int n                     = 3;
            int x0                    = 0;
            int y0                    = 0;
            int a                     = 1;
            int b                     = 1;
            int c                     = 1;
            int d                     = 1;
            int mod1                  = 100;
            int mod2                  = 100;
            int offset                = 1;
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new CatchTheBeat().maxCatched(n, x0, y0, a, b, c, d, mod1, mod2, offset));
        }
        case 1: {
            int n                     = 1;
            int x0                    = 0;
            int y0                    = 1234;
            int a                     = 0;
            int b                     = 0;
            int c                     = 0;
            int d                     = 0;
            int mod1                  = 1000000000;
            int mod2                  = 1000000000;
            int offset                = 1000;
            int expected__            = 1;

            return verifyCase(casenum__, expected__, new CatchTheBeat().maxCatched(n, x0, y0, a, b, c, d, mod1, mod2, offset));
        }
        case 2: {
            int n                     = 1;
            int x0                    = 0;
            int y0                    = 999;
            int a                     = 0;
            int b                     = 0;
            int c                     = 0;
            int d                     = 0;
            int mod1                  = 1000000000;
            int mod2                  = 1000000000;
            int offset                = 1000;
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new CatchTheBeat().maxCatched(n, x0, y0, a, b, c, d, mod1, mod2, offset));
        }
        case 3: {
            int n                     = 100;
            int x0                    = 0;
            int y0                    = 0;
            int a                     = 1;
            int b                     = 1;
            int c                     = 1;
            int d                     = 1;
            int mod1                  = 3;
            int mod2                  = 58585858;
            int offset                = 1;
            int expected__            = 66;

            return verifyCase(casenum__, expected__, new CatchTheBeat().maxCatched(n, x0, y0, a, b, c, d, mod1, mod2, offset));
        }
        case 4: {
            int n                     = 500000;
            int x0                    = 123456;
            int y0                    = 0;
            int a                     = 1;
            int b                     = 0;
            int c                     = 1;
            int d                     = 1;
            int mod1                  = 1000000000;
            int mod2                  = 1000000000;
            int offset                = 0;
            int expected__            = 376544;

            return verifyCase(casenum__, expected__, new CatchTheBeat().maxCatched(n, x0, y0, a, b, c, d, mod1, mod2, offset));
        }
        case 5: {
            int n                     = 500000;
            int x0                    = 0;
            int y0                    = 0;
            int a                     = 0;
            int b                     = 0;
            int c                     = 0;
            int d                     = 0;
            int mod1                  = 1;
            int mod2                  = 1;
            int offset                = 0;
            int expected__            = 500000;

            return verifyCase(casenum__, expected__, new CatchTheBeat().maxCatched(n, x0, y0, a, b, c, d, mod1, mod2, offset));
        }
        case 6: {
            int n                     = 10;
            int x0                    = 999999957;
            int y0                    = 79;
            int a                     = 993948167;
            int b                     = 24597383;
            int c                     = 212151897;
            int d                     = 999940854;
            int mod1                  = 999999986;
            int mod2                  = 999940855;
            int offset                = 3404;
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new CatchTheBeat().maxCatched(n, x0, y0, a, b, c, d, mod1, mod2, offset));
        }

        // custom cases

/*      case 7: {
            int n                     = ;
            int x0                    = ;
            int y0                    = ;
            int a                     = ;
            int b                     = ;
            int c                     = ;
            int d                     = ;
            int mod1                  = ;
            int mod2                  = ;
            int offset                = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new CatchTheBeat().maxCatched(n, x0, y0, a, b, c, d, mod1, mod2, offset));
        }*/
/*      case 8: {
            int n                     = ;
            int x0                    = ;
            int y0                    = ;
            int a                     = ;
            int b                     = ;
            int c                     = ;
            int d                     = ;
            int mod1                  = ;
            int mod2                  = ;
            int offset                = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new CatchTheBeat().maxCatched(n, x0, y0, a, b, c, d, mod1, mod2, offset));
        }*/
/*      case 9: {
            int n                     = ;
            int x0                    = ;
            int y0                    = ;
            int a                     = ;
            int b                     = ;
            int c                     = ;
            int d                     = ;
            int mod1                  = ;
            int mod2                  = ;
            int offset                = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new CatchTheBeat().maxCatched(n, x0, y0, a, b, c, d, mod1, mod2, offset));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
