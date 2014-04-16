import java.math.*;
import java.util.*;

public class SimilarNames {
    static int MOD = (int)1e9 + 7;

    public int count(String[] names, int[] info1, int[] info2) {
        int n = names.length;
        int m = info1.length;
        names = Arrays.copyOf(names, n + 1);
        names[n] = "";
        Arrays.sort(names);
        ArrayList <ArrayList <Integer>> children = new ArrayList <ArrayList <Integer>>();
        for (int i = 0; i <= n; ++ i) {
            children.add(new ArrayList <Integer>());
        }
        for (int i = 1; i <= n; ++ i) {
            int p = i - 1;
            while (!names[i].startsWith(names[p])) {
                p --;
            }
            children.get(p).add(i);
        }
        long leaves = (1L << n) - 1;
        for (int i = 0; i < m; ++ i) {
            leaves &= ~(1L << info1[i]);
        }
        int[] included = new int[n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < m; ++ j) {
                if (info2[j] == i) {
                    included[i] |= 1 << j;
                }
            }
        }
        long[] including = new long[1 << m];
        for (int mask = 0; mask < 1 << m; ++ mask) {
            for (int i = 0; i < m; ++ i) {
                if ((mask >> i & 1) == 1) {
                    including[mask] |= 1L << info1[i];
                    including[mask] |= 1L << info2[i];
                }
            }
        }
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; ++ i) {
            factorial[i] = (int)((long)factorial[i - 1] * i % MOD);
        }
        int all = (1 << m) - 1;
        int[] size = new int[n + 1];
        int[][] ways = new int[n + 1][1 << m];
        for (int u = n; u >= 0; -- u) {
            size[u] = 1;
            int[] subtrees = new int[1 << m];
            subtrees[0] = 1;
            for (int v : children.get(u)) {
                size[u] += size[v];
                int[] newSubtrees = new int[1 << m];
                for (int mask = 0; mask < 1 << m; ++ mask) {
                    for (int newMask = all ^ mask; ; newMask = newMask - 1 & (all ^ mask)) {
                        if ((including[mask] & including[newMask]) == 0) {
                            newSubtrees[mask | newMask] += (int)((long)subtrees[mask] * ways[v][newMask] % MOD);
                            if (newSubtrees[mask | newMask] >= MOD) {
                                newSubtrees[mask | newMask] -= MOD;
                            }
                        }
                        if (newMask == 0) {
                            break;
                        }
                    }
                }
                subtrees = newSubtrees;
            }
            for (int i = 0; i <= n; ++ i) {
                if (u > 0 || i == n) {
                    int newMask = 0;
                    for (int j = 0; j < m; ++ j) {
                        if (info1[j] == i) {
                            newMask |= 1 << j;
                        }
                    }
                    for (int mask = 0; mask < 1 << m; ++ mask) {
                        if (i == n || ((mask & included[i]) == 0 && (mask & newMask) > 0)) {
                            int newNewMask = mask & ~newMask;
                            int freeCount = size[u] - 1 - Long.bitCount(including[newNewMask]);
                            int newCount = Long.bitCount(including[mask] & ~including[newNewMask] & leaves);
                            if (freeCount >= newCount) {
                                ways[u][mask] += (int)((long)subtrees[newNewMask] * factorial[freeCount] % MOD * inverse(factorial[freeCount - newCount]) % MOD);
                                if (ways[u][mask] >= MOD) {
                                    ways[u][mask] -= MOD;
                                }
                            }
                        }
                    }
                }
            }
        }
        int left = Long.bitCount((1L << n) - 1 & ~including[(1 << m) - 1]);
        return (int)((long)ways[0][all] * factorial[left] % MOD);
    }

    int inverse(int a) {
        return a == 1 ? 1 : (int)((long)(MOD - MOD / a) * inverse(MOD % a) % MOD);
    }

    void debug(Object...os) {
        System.err.println(Arrays.deepToString(os));
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
        if (args.length == 0) {
            SimilarNamesHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                SimilarNamesHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class SimilarNamesHarness {
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
            String[] names            = {"kenta", "kentaro", "ken"};
            int[] info1               = {0};
            int[] info2               = {1};
            int expected__            = 3;

            return verifyCase(casenum__, expected__, new SimilarNames().count(names, info1, info2));
        }
        case 1: {
            String[] names            = {"hideo", "hideto", "hideki", "hide"};
            int[] info1               = {0, 0};
            int[] info2               = {1, 2};
            int expected__            = 6;

            return verifyCase(casenum__, expected__, new SimilarNames().count(names, info1, info2));
        }
        case 2: {
            String[] names            = {"aya", "saku", "emi", "ayane", "sakura", "emika", "sakurako"};
            int[] info1               = {0, 1, 3, 5};
            int[] info2               = {1, 2, 4, 6};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new SimilarNames().count(names, info1, info2));
        }
        case 3: {
            String[] names            = {"taro", "jiro", "hanako"};
            int[] info1               = {0, 1};
            int[] info2               = {1, 0};
            int expected__            = 0;

            return verifyCase(casenum__, expected__, new SimilarNames().count(names, info1, info2));
        }
        case 4: {
            String[] names            = {"alice", "bob", "charlie"};
            int[] info1               = {};
            int[] info2               = {};
            int expected__            = 6;

            return verifyCase(casenum__, expected__, new SimilarNames().count(names, info1, info2));
        }
        case 5: {
            String[] names            = {"ryota", "ryohei", "ryotaro", "ryo", "ryoga", "ryoma", "ryoko", "ryosuke", "ciel", "lun",
 "ryuta", "ryuji", "ryuma", "ryujiro", "ryusuke", "ryutaro", "ryu", "ryuhei", "ryuichi", "evima"};
            int[] info1               = {17, 5, 6, 13, 5};
            int[] info2               = {9, 2, 14, 17, 14};
            int expected__            = 994456648;

            return verifyCase(casenum__, expected__, new SimilarNames().count(names, info1, info2));
        }

        // custom cases

        case 6: {
            String[] names            = {"a", "ab", "ac"};
            int[] info1               = {0, 0};
            int[] info2               = {1, 2};
            int expected__            = 2;

            return verifyCase(casenum__, expected__, new SimilarNames().count(names, info1, info2));
        }
/*      case 7: {
            String[] names            = ;
            int[] info1               = ;
            int[] info2               = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new SimilarNames().count(names, info1, info2));
        }*/
/*      case 8: {
            String[] names            = ;
            int[] info1               = ;
            int[] info2               = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new SimilarNames().count(names, info1, info2));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
