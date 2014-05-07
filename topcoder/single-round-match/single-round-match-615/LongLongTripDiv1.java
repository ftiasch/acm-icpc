import java.math.*;
import java.util.*;

public class LongLongTripDiv1 {
    public String isAble(int n, int[] a, int[] b, int[] c, long t) {
        int m = a.length;
        int modulo = Integer.MAX_VALUE;
        for (int i = 0; i < m; ++ i) {
            if (a[i] == 0 || b[i] == 0) {
                modulo = Math.min(modulo, c[i] * 2);
            }
        }
        if (modulo == Integer.MAX_VALUE) {
            return "Impossible";
        }
        long[][] distance = new long[n][modulo];
        for (int i = 0; i < n; ++ i) {
            Arrays.fill(distance[i], Long.MAX_VALUE);
        }
        distance[0][0] = 0;
        PriorityQueue <Pair> queue = new PriorityQueue <Pair>();
        queue.add(new Pair(0, 0, 0));
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            if (p.d == distance[p.v][p.r]) {
                for (int i = 0; i < m; ++ i) {
                    int[] v = new int[]{a[i], b[i]};
                    for (int j = 0; j < 2; ++ j) {
                        int nr = (int)((p.d + c[i]) % modulo);
                        if (p.v == v[j] && p.d + c[i] < distance[v[j ^ 1]][nr]) {
                            distance[v[j ^ 1]][nr] = p.d + c[i];
                            queue.add(new Pair(v[j ^ 1], nr, distance[v[j ^ 1]][nr]));
                        }
                    }
                }
            }
        }
        return distance[n - 1][(int)(t % modulo)] <= t ? "Possible" : "Impossible";
    }

    class Pair implements Comparable {
        @Override
        public int compareTo(Object other) {
            Pair o = (Pair)other;
            return d - o.d < 0 ? -1 : 1;
        }

        Pair(int v, int r, long d) {
            this.v = v;
            this.r = r;
            this.d = d;
        }

        int  v, r;
        long d;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            LongLongTripDiv1Harness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                LongLongTripDiv1Harness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class LongLongTripDiv1Harness {
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
            int N                     = 3;
            int[] A                   = {0,0,1};
            int[] B                   = {2,1,2};
            int[] D                   = {7,6,5};
            long T                    = 11;
            String expected__         = "Possible";

            return verifyCase(casenum__, expected__, new LongLongTripDiv1().isAble(N, A, B, D, T));
        }
        case 1: {
            int N                     = 3;
            int[] A                   = {0,0,1};
            int[] B                   = {2,1,2};
            int[] D                   = {7,6,5};
            long T                    = 25;
            String expected__         = "Possible";

            return verifyCase(casenum__, expected__, new LongLongTripDiv1().isAble(N, A, B, D, T));
        }
        case 2: {
            int N                     = 2;
            int[] A                   = {0};
            int[] B                   = {1};
            int[] D                   = {1};
            long T                    = 9;
            String expected__         = "Possible";

            return verifyCase(casenum__, expected__, new LongLongTripDiv1().isAble(N, A, B, D, T));
        }
        case 3: {
            int N                     = 2;
            int[] A                   = {1};
            int[] B                   = {0};
            int[] D                   = {1};
            long T                    = 1000000000000000000L;
            String expected__         = "Impossible";

            return verifyCase(casenum__, expected__, new LongLongTripDiv1().isAble(N, A, B, D, T));
        }
        case 4: {
            int N                     = 4;
            int[] A                   = {0,0,1};
            int[] B                   = {2,1,2};
            int[] D                   = {10,10,10};
            long T                    = 1000;
            String expected__         = "Impossible";

            return verifyCase(casenum__, expected__, new LongLongTripDiv1().isAble(N, A, B, D, T));
        }
        case 5: {
            int N                     = 9;
            int[] A                   = {4,8,5,8,3,6,2,6,7,6,6};
            int[] B                   = {2,7,1,5,1,3,1,1,5,4,2};
            int[] D                   = {6580,8822,1968,673,1394,9337,5486,1746,5229,4092,195};
            long T                    = 937186357646035002L;
            String expected__         = "Impossible";

            return verifyCase(casenum__, expected__, new LongLongTripDiv1().isAble(N, A, B, D, T));
        }

        // custom cases

/*      case 6: {
            int N                     = ;
            int[] A                   = ;
            int[] B                   = ;
            int[] D                   = ;
            long T                    = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new LongLongTripDiv1().isAble(N, A, B, D, T));
        }*/
/*      case 7: {
            int N                     = ;
            int[] A                   = ;
            int[] B                   = ;
            int[] D                   = ;
            long T                    = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new LongLongTripDiv1().isAble(N, A, B, D, T));
        }*/
/*      case 8: {
            int N                     = ;
            int[] A                   = ;
            int[] B                   = ;
            int[] D                   = ;
            long T                    = ;
            String expected__         = ;

            return verifyCase(casenum__, expected__, new LongLongTripDiv1().isAble(N, A, B, D, T));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
