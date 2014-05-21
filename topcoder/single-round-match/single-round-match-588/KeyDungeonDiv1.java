import java.math.*;
import java.util.*;

public class KeyDungeonDiv1 {
    public int maxKeys(int[] doorR, int[] doorG, int[] roomR, int[] roomG, int[] roomW, int[] keys) {
        int n = doorR.length;
        int[] sum = new int[1 << n];
        for (int mask = 0; mask < 1 << n; ++ mask) {
            sum[mask] = keys[0] + keys[1] + keys[2];
            for (int i = 0; i < n; ++ i) {
                if ((mask >> i & 1) == 1) {
                    sum[mask] += roomR[i] + roomG[i] + roomW[i] - doorR[i] - doorG[i];
                }
            }
        }
        int sumR = keys[0];
        for (int i = 0; i < n; ++ i) {
            sumR += roomR[i];
        }
        int[][] maxW = new int[1 << n][sumR + 1];
        for (int mask = 0; mask < 1 << n; ++ mask) {
            Arrays.fill(maxW[mask], -1);
        }
        maxW[0][keys[0]] = keys[2];
        int result = 0;
        for (int mask = 0; mask < 1 << n; ++ mask) {
            for (int r = 0; r <= sumR; ++ r) {
                if (maxW[mask][r] >= 0) {
                    result = Math.max(result, sum[mask]);
                    int w = maxW[mask][r];
                    int g = sum[mask] - r - w;
                    assert g >= 0;
                    for (int i = 0; i < n; ++ i) {
                        if ((mask >> i & 1) == 0) {
                            int newW = w - Math.max(doorR[i] - r, 0) - Math.max(doorG[i] - g, 0);
                            if (newW >= 0) {
                                int newMask = mask | 1 << i;
                                int newR = Math.max(r - doorR[i], 0) + roomR[i];
                                maxW[newMask][newR] = Math.max(maxW[newMask][newR], newW + roomW[i]);
                            }
                        }
                    }
                }
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
            KeyDungeonDiv1Harness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                KeyDungeonDiv1Harness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class KeyDungeonDiv1Harness {
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
            int[] doorR               = {1, 2, 3};
            int[] doorG               = {0, 4, 9};
            int[] roomR               = {0, 0, 10};
            int[] roomG               = {0, 8, 9};
            int[] roomW               = {1, 0, 8};
            int[] keys                = {3, 1, 2};
            int expected__            = 8;

            return verifyCase(casenum__, expected__, new KeyDungeonDiv1().maxKeys(doorR, doorG, roomR, roomG, roomW, keys));
        }
        case 1: {
            int[] doorR               = {1, 1, 1, 2};
            int[] doorG               = {0, 2, 3, 1};
            int[] roomR               = {2, 1, 0, 4};
            int[] roomG               = {1, 3, 3, 1};
            int[] roomW               = {1, 0, 2, 1};
            int[] keys                = {0, 4, 0};
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new KeyDungeonDiv1().maxKeys(doorR, doorG, roomR, roomG, roomW, keys));
        }
        case 2: {
            int[] doorR               = {2, 0, 4};
            int[] doorG               = {3, 0, 4};
            int[] roomR               = {0, 0, 9};
            int[] roomG               = {0, 0, 9};
            int[] roomW               = {8, 5, 9};
            int[] keys                = {0, 0, 0};
            int expected__            = 27;

            return verifyCase(casenum__, expected__, new KeyDungeonDiv1().maxKeys(doorR, doorG, roomR, roomG, roomW, keys));
        }
        case 3: {
            int[] doorR               = {5, 3, 0, 0};
            int[] doorG               = {0, 1, 2, 1};
            int[] roomR               = {0, 9, 2, 4};
            int[] roomG               = {2, 9, 2, 0};
            int[] roomW               = {0, 9, 1, 1};
            int[] keys                = {1, 1, 0};
            int expected__            = 32;

            return verifyCase(casenum__, expected__, new KeyDungeonDiv1().maxKeys(doorR, doorG, roomR, roomG, roomW, keys));
        }
        case 4: {
            int[] doorR               = {9,5,10,8,4,3,0,8,4,1,3,9};
            int[] doorG               = {9,10,0,8,9,4,3,8,1,8,10,4};
            int[] roomR               = {1,2,0,2,3,3,5,3,1,3,0,5};
            int[] roomG               = {5,2,5,0,5,2,3,4,0,0,5,2};
            int[] roomW               = {1,5,1,2,0,4,4,0,3,3,1,3};
            int[] keys                = {5,0,1};
            int expected__            = 16;

            return verifyCase(casenum__, expected__, new KeyDungeonDiv1().maxKeys(doorR, doorG, roomR, roomG, roomW, keys));
        }

        // custom cases

/*      case 5: {
            int[] doorR               = ;
            int[] doorG               = ;
            int[] roomR               = ;
            int[] roomG               = ;
            int[] roomW               = ;
            int[] keys                = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new KeyDungeonDiv1().maxKeys(doorR, doorG, roomR, roomG, roomW, keys));
        }*/
/*      case 6: {
            int[] doorR               = ;
            int[] doorG               = ;
            int[] roomR               = ;
            int[] roomG               = ;
            int[] roomW               = ;
            int[] keys                = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new KeyDungeonDiv1().maxKeys(doorR, doorG, roomR, roomG, roomW, keys));
        }*/
/*      case 7: {
            int[] doorR               = ;
            int[] doorG               = ;
            int[] roomR               = ;
            int[] roomG               = ;
            int[] roomW               = ;
            int[] keys                = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new KeyDungeonDiv1().maxKeys(doorR, doorG, roomR, roomG, roomW, keys));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
