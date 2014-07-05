import java.math.*;
import java.util.*;

public class CliqueGraph {
    public long calcSum(int n, int[] v, int[] sizes) {
        int m = sizes.length;
        int[] indices = new int[m + 1];
        indices[0] = 0;
        for (int i = 0; i < m; ++ i) {
            indices[i + 1] = indices[i] + sizes[i];
        }
        List <List <Integer>> groups = new ArrayList <List <Integer>>();
        for (int i = 0; i < n; ++ i) {
            groups.add(new ArrayList <Integer>());
        }
        for (int i = 0; i < m; ++ i) {
            for (int j = indices[i]; j < indices[i + 1]; ++ j) {
                groups.get(v[j]).add(i);
            }
        }
        long result = 0;
        for (int source = 0; source < n; ++ source) {
            int[] distance = new int[n];
            Arrays.fill(distance, -1);
            Queue <Integer> queue = new LinkedList <Integer>();
            distance[source] = 0;
            queue.offer(source);
            boolean[] used = new boolean[m];
            Arrays.fill(used, false);
            while (!queue.isEmpty()) {
                int u = queue.poll();
                for (int g : groups.get(u)) {
                    if (!used[g]) {
                        used[g] = true;
                        for (int i = indices[g]; i < indices[g + 1]; ++ i) {
                            if (distance[v[i]] == -1) {
                                distance[v[i]] = distance[u] + 1;
                                queue.offer(v[i]);
                            }
                        }
                    }
                }
            }
            for (int i = source; i < n; ++ i) {
                result += distance[i];
            }
        }
        return result;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            CliqueGraphHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                CliqueGraphHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class CliqueGraphHarness {
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
            int N                     = 4;
            int[] V                   = {0,1,2,0,3};
            int[] sizes               = {3,2};
            long expected__           = 8;

            return verifyCase(casenum__, expected__, new CliqueGraph().calcSum(N, V, sizes));
        }
        case 1: {
            int N                     = 5;
            int[] V                   = {0,1,2,3,1,2,4};
            int[] sizes               = {4,3};
            long expected__           = 12;

            return verifyCase(casenum__, expected__, new CliqueGraph().calcSum(N, V, sizes));
        }
        case 2: {
            int N                     = 15;
            int[] V                   = {1,3,5,7,9,11,13,0 ,2,3,6,7,10,11,14,0 ,4,5,6,7,12,13,14,0 ,8,9,10,11,12,13,14,0};
            int[] sizes               = {8,8,8,8};
            long expected__           = 130;

            return verifyCase(casenum__, expected__, new CliqueGraph().calcSum(N, V, sizes));
        }

        // custom cases

/*      case 3: {
            int N                     = ;
            int[] V                   = ;
            int[] sizes               = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new CliqueGraph().calcSum(N, V, sizes));
        }*/
/*      case 4: {
            int N                     = ;
            int[] V                   = ;
            int[] sizes               = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new CliqueGraph().calcSum(N, V, sizes));
        }*/
/*      case 5: {
            int N                     = ;
            int[] V                   = ;
            int[] sizes               = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new CliqueGraph().calcSum(N, V, sizes));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
