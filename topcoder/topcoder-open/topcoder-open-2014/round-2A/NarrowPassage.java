import java.math.*;
import java.util.*;

public class NarrowPassage {
    int[] getRank(int[] a) {
        int n = a.length;
        int[] rank = new int[n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                if (a[j] < a[i]) {
                    rank[i] ++;
                }
            }
        }
        return rank;
    }

    int getDest(int l, int divider, int rank) {
        return rank < divider ? 0 : l;
    }

    public int minDist(int l, int[] a, int[] b) {
        int n = a.length;
        int[] aRank = getRank(a);
        int[] bRank = getRank(b);
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++ i) {
            for (int j = i; j < n; ++ j) {
                int cost = 0;
                for (int k = 0; k < n && cost < Integer.MAX_VALUE; ++ k) {
                    if (aRank[k] < i) {
                        if (bRank[k] < i) {
                            cost += a[k] + b[k];
                        } else {
                            cost = Integer.MAX_VALUE;
                        }
                    } else if (aRank[k] <= j) {
                        if (aRank[k] == bRank[k]) {
                            cost += Math.abs(a[k] - b[k]);
                        } else {
                            cost = Integer.MAX_VALUE;
                        }
                    } else {
                        if (bRank[k] > j) {
                            cost += l - a[k] + l - b[k];
                        } else {
                            cost = Integer.MAX_VALUE;
                        }
                    }
                }
                result = Math.min(result, cost);
            }
        }
        for (int i = 0; i <= n; ++ i) {
            for (int j = 0; j <= n; ++ j) {
                int cost = 0;
                for (int k = 0; k < n; ++ k) {
                    int aDest = getDest(l, i, aRank[k]);
                    int bDest = getDest(l, j, bRank[k]);
                    cost += Math.abs(aDest - a[k]) + Math.abs(aDest - bDest) + Math.abs(bDest - b[k]);
                }
                result = Math.min(result, cost);
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
            NarrowPassageHarness.run_test(-1);
        } else {
            for (int i=0; i<args.length; ++i)
                NarrowPassageHarness.run_test(Integer.valueOf(args[i]));
        }
    }
// END CUT HERE
}

// BEGIN CUT HERE
class NarrowPassageHarness {
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
            int L                     = 5;
            int[] a                   = {1, 2};
            int[] b                   = {3, 4};
            int expected__            = 4;

            return verifyCase(casenum__, expected__, new NarrowPassage().minDist(L, a, b));
        }
        case 1: {
            int L                     = 10;
            int[] a                   = {3, 9};
            int[] b                   = {8, 6};
            int expected__            = 14;

            return verifyCase(casenum__, expected__, new NarrowPassage().minDist(L, a, b));
        }
        case 2: {
            int L                     = 265467;
            int[] a                   = {133548, 103861, 29821, 199848, 92684, 219824, 215859, 62821, 172409, 109235, 38563, 148854, 24742, 174068, 205005, 75922, 87316, 5542, 57484, 40792, 25229, 152216, 21547, 22203, 84712, 231522, 235703, 184895, 100787, 174440, 156904, 84898, 185568, 108732, 260098, 89488, 221604, 104555, 165775, 90444, 81952, 149671, 209674, 22185, 45420, 41928, 16098, 65324, 90870, 35243};
            int[] b                   = {150289, 135139, 69841, 227226, 177427, 230314, 199175, 81572, 220468, 151049, 40009, 145963, 115246, 252932, 263651, 38434, 120096, 69576, 29789, 115046, 33310, 260771, 5723, 80733, 107864, 142447, 235490, 242149, 124564, 134602, 245962, 7078, 215816, 219864, 190499, 210237, 212894, 142760, 126472, 201935, 119308, 120211, 235235, 19446, 87314, 17286, 61990, 102050, 261812, 257};
            int expected__            = 7148670;

            return verifyCase(casenum__, expected__, new NarrowPassage().minDist(L, a, b));
        }
        case 3: {
            int L                     = 1000000;
            int[] a                   = {706292, 756214, 490048, 228791, 567805, 353900, 640393, 562496, 217533, 934149, 938644, 127480, 777134, 999144, 41485, 544051, 417987, 767415, 971662, 959022, 670563, 34065, 518183, 750574, 546576, 207758, 159932, 429345, 670513, 271901, 476062, 392721, 774733, 502586, 915436, 120280, 951729, 699859, 581770, 268966, 79392, 888601, 378829, 350198, 939459, 644983, 605862, 721305, 269232, 137587};
            int[] b                   = {322468, 673534, 83223, 551733, 341310, 485064, 885415, 927526, 159402, 28144, 441619, 305530, 883149, 413745, 932694, 214862, 677401, 104356, 836580, 300580, 409942, 748444, 744205, 119051, 999286, 462508, 984346, 887773, 856655, 245559, 418763, 840266, 999775, 962927, 779570, 488394, 760591, 326325, 206948, 13999, 285467, 401562, 786209, 169847, 171326, 2901, 296531, 572035, 364920, 939046};
            int expected__            = 45670501;

            return verifyCase(casenum__, expected__, new NarrowPassage().minDist(L, a, b));
        }

        // custom cases

/*      case 4: {
            int L                     = ;
            int[] a                   = ;
            int[] b                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new NarrowPassage().minDist(L, a, b));
        }*/
/*      case 5: {
            int L                     = ;
            int[] a                   = ;
            int[] b                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new NarrowPassage().minDist(L, a, b));
        }*/
/*      case 6: {
            int L                     = ;
            int[] a                   = ;
            int[] b                   = ;
            int expected__            = ;

            return verifyCase(casenum__, expected__, new NarrowPassage().minDist(L, a, b));
        }*/
        default:
            return -1;
        }
    }
}

// END CUT HERE
