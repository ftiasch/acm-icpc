import java.math.*;
import java.util.*;

public class EvenPaths {
    final static int M = 16 + 1;

    short[] ones;

    long popcount(long mask) {
        long all = (1 << 17) - 1;
        return ones[(int)(mask & all)] + ones[(int)(mask >> 17 & all)] + ones[(int)(mask >> 34)];
    }

    long[] transform(long[] a, int oper) {
        for (int i = oper == 1 ? 0 : M - 1; 0 <= i && i < M; i += oper) {
            for (int mask = 0; mask < 1 << M; ++ mask) {
                int newMask = mask ^ (1 << i);
                if (mask < newMask) {
                    a[mask] += oper * a[newMask];
                }
            }
        }
        return a;
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

    void debugBit(long mask, int n) {
        for (int i = 0; i < n; ++ i) {
            System.err.print(mask >> i & 1);
        }
        System.err.println();
    }

    public long theCount(String[] maze, String rooms) {
        int n = maze.length;

        ones = new short[1 << 17];
        for (int mask = 1; mask < 1 << 17; ++ mask) {
            ones[mask] = ones[mask >> 1];
            if ((mask & 1) == 1) {
                ones[mask] ++;
            }
        }

        // topological sorting
        int[] order = new int[n];
        if (true) {
            int[] degree = new int[n];
            for (int u = 0; u < n; ++ u) {
                for (int v = 0; v < n; ++ v) {
                    if (maze[u].charAt(v) == 'Y') {
                        degree[v] ++;
                    }
                }
            }
            int tail = 0;
            for (int u = 0; u < n; ++ u) {
                if (degree[u] == 0) {
                    order[tail ++] = u;
                }
            }
            for (int head = 0; head < tail; ++ head) {
                int u = order[head];
                for (int v = 0; v < n; ++ v) {
                    if (maze[u].charAt(v) == 'Y') {
                        degree[v] --;
                        if (degree[v] == 0) {
                            order[tail ++] = v;
                        }
                    }
                }
            }
        }

        int[] id = new int[n];
        Arrays.fill(id, -1);
        int m = 0;
        for (int i = 0; i < n; ++ i) {
            int u = order[i];
            if (rooms.charAt(u) == '?') {
                id[u] = m ++;
            }
        }

        long[] froms = new long[n];
        long[] tos = new long[n];
        for (int u = 0; u < n; ++ u) {
            for (int v = 0; v < n; ++ v) {
                if (maze[u].charAt(v) == 'Y') {
                    froms[v] |= 1L << u;
                    tos[u] |= 1L << v;
                }
            }
        }

        int m1 = m >> 1;
        int m2 = m - m1;
        long[] leftWays = new long[1 << M];
        for (int roomMask = 0; roomMask < 1 << m1; ++ roomMask) {
            int pathMask = 0;
            long ways = 0;
            for (int i = 0; i < n; ++ i) {
                int u = order[i];
                ways ^= (popcount(ways & froms[u]) & 1L) << u;
                if (u == 0) {
                    ways ^= 1L << u;
                }
                if (id[u] != -1 && id[u] < m1 && (roomMask >> id[u] & 1) == 1) {
                    ways &= ~(1L << u);
                }
                if (id[u] >= m1 && (ways >> u & 1) == 1) {
                    pathMask |= 1 << id[u] - m1;
                    ways &= ~(1L << u);
                }
            }
            if ((ways >> 1 & 1) == 1) {
                pathMask |= 1 << 16;
            }
            leftWays[pathMask] ++;
        }

        long[] rightWays = new long[1 << M];
        for (int roomMask = 0; roomMask < 1 << m2; ++ roomMask) {
            long ways = 0;
            for (int i = n - 1; i >= 0; -- i) {
                int u = order[i];
                ways ^= (popcount(ways & tos[u]) & 1L) << u;
                if (u == 1) {
                    ways ^= 1L << u;
                }
                if (id[u] >= m1 && (roomMask >> id[u] - m1 & 1) == 1) {
                    ways &= ~(1L << u);
                }
            }
            int pathMask = 1 << 16;
            for (int u = 0; u < n; ++ u) {
                if (id[u] >= m1 && (ways >> u & 1) == 1) {
                    pathMask |= 1 << id[u] - m1;
                }
            }
            rightWays[pathMask] ++;
        }

        leftWays = transform(leftWays, 1);
        rightWays = transform(rightWays, 1);
        long[] allWays = new long[1 << M];
        for (int mask = 0; mask < 1 << M; ++ mask) {
            allWays[mask] = leftWays[mask] * rightWays[mask];
        }
        allWays = transform(allWays, -1);

        long answer = 0;
        for (int mask = 0; mask < 1 << M; ++ mask) {
            if (popcount(mask) % 2 == 0) {
                answer += allWays[mask];
            }
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            EvenPathsHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                EvenPathsHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class EvenPathsHarness {
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
            String[] maze             = {"NYY", "NNN", "NYN"};
            String rooms              = "--?";
            long expected__           = 1;

            return verifyCase(casenum__, expected__, new EvenPaths().theCount(maze, rooms));
        }
        case 1: {
            String[] maze             = {"NYYNN", "NNNNY", "NYNNN", "YNNNN", "NNNNN"};
            String rooms              = "--???";
            long expected__           = 4;

            return verifyCase(casenum__, expected__, new EvenPaths().theCount(maze, rooms));
        }
        case 2: {
            String[] maze             = {"NNNNN", "NNYYN", "YNNNY", "NNNNN", "NNNNN"};
            String rooms              = "--???";
            long expected__           = 8;

            return verifyCase(casenum__, expected__, new EvenPaths().theCount(maze, rooms));
        }
        case 3: {
            String[] maze             = {"NNYNYNNY", "NNNNNNNN", "NNNNNYYY", "NYNNNNNN", "NNNNNNNN", "NYNYNNNN", "NYNNYNNN", "NNNYYNYN"};
            String rooms              = "----???-";
            long expected__           = 4;

            return verifyCase(casenum__, expected__, new EvenPaths().theCount(maze, rooms));
        }
        case 4: {
            String[] maze             = {"NYNNNNYYYYYYYNYNYNNN",
"NNNNNNNNNNNNNNNNNNNN",
"NNNNYNYYNYNNNNYNYNYN",
"NNNNNNNNNNNNNNNNYYNN",
"NNNYNNNNNNYYNNYYNNNY",
"NYNNYNYYYYNYNYNYYYNN",
"NNNYYNNNYNYYNNYNYYNY",
"NNNYNNYNYNYNYNYYYYYN",
"NYNYYNNNNNNYYNNYYNNN",
"NNNNNNNNNNYNYNNNYYNN",
"NYNNNNNNNNNYNNNNYNNY",
"NNNNNNNNNNNNNNNNNNNN",
"NNNYNNNNNNNNNNYNNYNN",
"NNNYYNYNNYNNNNNYYYNN",
"NNNNNNNNNNNNNNNNNYNN",
"NYNYNNNNNNNNNNYNNNNN",
"NNNNNNNNNNNNNNNNNNNN",
"NYNNNNNNNNNNNNNNNNNN",
"NYNNNNNNNYNYYNNYYYNN",
"NYNNNNNNNNNNYNYNYYNN"};
            String rooms              = "---??-??--??--?-?---";
            long expected__           = 136;

            return verifyCase(casenum__, expected__, new EvenPaths().theCount(maze, rooms));
        }
        case 5: {
            String[] maze             = {"NNYNNNNYNYYYNYNNYYNYNNYYNYNNNYYYNNNNYNNNYYNNNNYNNY",
"NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
"NNNYNNNNNYNNNYNNYNNYNNYYYNYNYNNYYYNYNYYNYYNNNNYNNN",
"NNNNNNNNNYNNNNYNNNNNNNNYNNNNYNNNNNNNNNNYNNNNNNNNYN",
"NYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
"NYNNYNNNNNNNNNYNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNYNNN",
"NNNYYNNYNNYNNYNYNNNYYNYYNNNNYNNNYNNNNNYYNYNYYNNYYN",
"NYNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNYN",
"NYNYYNNYNYNYNNNNNNNNNNNNNNNNNYNNNNNNNNNYNNNNNYYNNN",
"NNNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNYNNYN",
"NYNNYNNYNYNNNNYNNNNNNYNNNNNNNNNNNNNNNNNNNYNNNNNNNN",
"NYNNNNNYNNNNNNNNNNNYNNNYNNNNYNNNNNNNNNNNNNNNNNYNNN",
"NYNNNYNYNYNNNNNNNNYYNYYNNNNYNNNNNNYNNYYNNNNNYNYNYN",
"NNNNYYNNNNNNNNYNNYNYYYNYNNNNNNNYNNYNNNNNYNNNNNNYNN",
"NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYN",
"NYNYNYNNNYYNNNYNNNNNYYYNYNNNNYNNNNNNNYNYNYNNNYYNYN",
"NNNNYYNYYYYYNNYNNYNYYNNNNNYNYYNNYNYNNYYYNYYYNNNNYN",
"NNNNNNNNYNNYNNNNNNNNNNNNNNNNYYNNYNYNNNNYNNNNNYNYNN",
"NNNYNYNYYYYYNNNNNNNNYNYNYNNNYNYNYNYNNNNNYYYNYNNYYN",
"NYNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYN",
"NNNYYYNYNYNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNYNNN",
"NNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNYN",
"NNNNNYNNYYYNNNYNNNNYNNNNNNNNNYNNYNYNNYNYNNNNNNNYNN",
"NYNNYNNNNNNNNNYNNNNYNYNNNNNNNNNNNNNNNNNNNYNNNYYNNN",
"NNNNNYNYYYNNNNYNNYNNNNNNNNNNNYNYNNNNNNNNNNNNNYYYNN",
"NNNNYYNYYYYNYNNNYYNYNYYNNNYYNYNNYNNYNYYNYYNYYNYYNY",
"NYNNNNNNNYYNNNNNNNNNNNNYNNNNYYNNNNYNNNYNNYNNNYNNYN",
"NNNYNYNYNYNNNNNNNNNYNYYNNNYNNYNYYNNNNNNNYNNNNYNNYN",
"NYNNNYNNNYNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNYNNN",
"NYNYNNNYNYYNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNYNNNYYNNN",
"NYNNNNNNYNNNNYYYNYNYNNNNNNNNYYNNNNNNNNNNNNNNNYYNNN",
"NNNYYNNNNNYYNNYNNNNNNYNYNNNNYYNNNNYNNNNYNNNNNNYNNN",
"NYNYNYNNNNNYNNNNNNNYNNNNNNNNNNNNNNNNNYYNNYNNNYNNYN",
"NYNNNNNNNYYNNNNYYYNNNNYNYNYNYNNYYNNYNYNNNNNNNNNNYN",
"NNNNYNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNYNNYN",
"NYNYYNNYNYNNNNNYNNNYNYNNYNNNNNNNYNNNNNYNNYNYNNNNNN",
"NYNNNNNNYNNNNYYNNNYNNYYNNYYYYYNNNYNNNNNYNYYNNNNNNY",
"NYNNNYNNNYYNNNNNNNNNNNNYNNNNNNNNNNYNNNNNNNNNNNYNNN",
"NNNNNYNNYYNYNNNNNNNNNYNYNNNNYNNNNNNNNNNNNYNNNNNNNN",
"NNNNNYNYNNYNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNYNNNNNNNN",
"NNNNYNNNYNNNNNNYNNNYNYNYYNNNNYNNNNNNNNNNNYNNNYYNNN",
"NNNNYNNYNYNNNNYNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNYYNYN",
"NYNNYYNNYNNNNNNNNNNYYYNNNNNNNNNNNNNNNNNNNNNYNNNNNN",
"NNNYYNNYYNNNNNYYNNNYNNNNYNYNNNNNNNNNNNNYNNNNNYNNNN",
"NNNNNYNYYNYNNNNNYNNYYYYNYNYYYYYNYNYYNNNNYNNNNYYYYN",
"NNNNNNNYNNNNNNYNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNYN",
"NYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYN",
"NNNYYNNNYYNYNNNNNNNNNYNNNNNNYNNNNNYNNNNNNYNNNNYNNN",
"NYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
"NYNNNNYYYNYYYYNYNNNNNNNNNNNYNYNNNNNNNNNYNYNYNNNNYN"};
            String rooms              = "---??--??-????-??????--?????-?-?-??--?-??--??????-";
            long expected__           = 2165839872L;

            return verifyCase(casenum__, expected__, new EvenPaths().theCount(maze, rooms));
        }

        // custom cases

/*      case 6: {
            String[] maze             = ;
            String rooms              = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new EvenPaths().theCount(maze, rooms));
        }*/
/*      case 7: {
            String[] maze             = ;
            String rooms              = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new EvenPaths().theCount(maze, rooms));
        }*/
/*      case 8: {
            String[] maze             = ;
            String rooms              = ;
            long expected__           = ;

            return verifyCase(casenum__, expected__, new EvenPaths().theCount(maze, rooms));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
